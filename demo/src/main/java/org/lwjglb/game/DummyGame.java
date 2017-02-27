package org.lwjglb.game;

import de.kuschku.steamaudio.lib.SteamAudio;
import de.kuschku.steamaudio.lib.compute.ComputeDevice;
import de.kuschku.steamaudio.lib.compute.IPLComputeDeviceType;
import de.kuschku.steamaudio.lib.context.Context;
import de.kuschku.steamaudio.lib.geometry.IPLVector3;
import de.kuschku.steamaudio.lib.scene.IPLMaterial;
import de.kuschku.steamaudio.lib.scene.IPLTriangle;
import de.kuschku.steamaudio.lib.scene.StaticMesh;
import de.kuschku.steamaudio.lib.simulation.IPLSimulationSettings;
import de.kuschku.steamaudio.lib.util.ErrorUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bridj.Pointer;
import org.joml.Vector2f;
import org.joml.Vector3f;
import org.lwjgl.openal.AL11;
import org.lwjglb.engine.*;
import org.lwjglb.engine.graph.*;
import org.lwjglb.engine.graph.lights.DirectionalLight;
import org.lwjglb.engine.graph.particles.FlowParticleEmitter;
import org.lwjglb.engine.graph.particles.Particle;
import org.lwjglb.engine.graph.weather.Fog;
import org.lwjglb.engine.items.GameItem;
import org.lwjglb.engine.items.SkyBox;
import org.lwjglb.engine.loaders.obj.OBJLoader;
import org.lwjglb.engine.sound.SoundBuffer;
import org.lwjglb.engine.sound.SoundListener;
import org.lwjglb.engine.sound.SoundManager;
import org.lwjglb.engine.sound.SoundSource;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.lwjgl.glfw.GLFW.*;

public class DummyGame implements IGameLogic {

    private static final float MOUSE_SENSITIVITY = 0.2f;
    private static final float CAMERA_POS_STEP = 0.10f;
    private static Logger logger = LogManager.getLogger(DummyGame.class);
    private static Logger audioLogger = LogManager.getLogger("AudioEngine");
    private final Vector3f cameraInc;
    private final Renderer renderer;
    private final SoundManager soundMgr;
    private final Camera camera;
    private Scene scene;
    private Hud hud;
    private float angleInc;

    private float lightAngle;

    private FlowParticleEmitter particleEmitter;

    private MouseBoxSelectionDetector selectDetector;

    private boolean leftButtonPressed;
    private GameItem[] gameItems;

    public DummyGame() {
        renderer = new Renderer();
        hud = new Hud();
        soundMgr = new SoundManager();
        camera = new Camera();
        cameraInc = new Vector3f(0.0f, 0.0f, 0.0f);
        angleInc = 0;
        lightAngle = 45;
    }

    @Override
    public void init(Window window) throws Exception {
        logger.info("Initializing HUD");
        hud.init(window);

        logger.info("Initializing Renderer");
        renderer.init(window);

        logger.info("Initializing Soundmanager");
        soundMgr.init();

        leftButtonPressed = false;

        logger.info("Initializing Scene");
        scene = new Scene();

        logger.info("Initializing Game");
        float reflectance = 1f;

        float blockScale = 0.5f;
        float skyBoxScale = 100.0f;
        float extension = 2.0f;

        float startx = extension * (-skyBoxScale + blockScale);
        float startz = extension * (skyBoxScale - blockScale);
        float starty = -1.0f;
        float inc = blockScale * 2;

        float posx = startx;
        float posz = startz;
        float incy = 0.0f;

        selectDetector = new MouseBoxSelectionDetector();

        logger.info("Initializing Textures and Models");
        Vector3f[] positions = new Vector3f[]{new Vector3f(0, -1, 0), new Vector3f(1, -1, 0), new Vector3f(-1, -1, 0),
                new Vector3f(0, -1, 1), new Vector3f(0, -1, -1),

                new Vector3f(2, 0, 0), new Vector3f(-2, 0, 0), new Vector3f(1, 0, 1), new Vector3f(1, 0, -1),
                new Vector3f(-1, 0, 1), new Vector3f(-1, 0, -1), new Vector3f(0, 0, 2), new Vector3f(0, 0, -2),};

        int instances = positions.length;
        Mesh mesh = OBJLoader.loadMesh("/models/cube.obj", instances);
        mesh.setBoundingRadius(1);
        Texture texture = new Texture("/textures/terrain_textures.png", 2, 1);
        Material material = new Material(texture, reflectance);
        material.scattering(1.0f);
        material.highFreqAbsorption(0.75f);
        material.midFreqAbsorption(0.5f);
        material.lowFreqAbsorption(0.25f);
        mesh.setMaterial(material);
        gameItems = new GameItem[instances];
        int triangleCount = 0;
        int vertexCount = 0;
        Set<IPLMaterial> materials = new HashSet<>();
        for (int i = 0; i < positions.length; i++) {
            int scale = 3;
            GameItem gameItem = new GameItem(mesh);
            gameItem.setScale(scale);
            gameItem.setPosition(positions[i].x * 2 * scale - 2 * scale, positions[i].y * 2 * scale,
                    positions[i].z * 2 * scale);
            triangleCount += gameItem.getMesh().getIPLTriangleCount();
            vertexCount += gameItem.getMesh().getIPLVertexCount();
            materials.add(gameItem.getMesh().getMaterial().getAudioMaterial());
            gameItems[i] = gameItem;
        }

        int triangleIndex = 0;
        int vertexIndex = 0;

        audioLogger.info(String.format("Allocating Memory for %d Triangles", triangleCount));
        Pointer<IPLTriangle> triangles = Pointer.allocateArray(IPLTriangle.class, triangleCount);

        audioLogger.info(String.format("Allocating Memory for %d Vertices", vertexCount));
        Pointer<IPLVector3> vertices = Pointer.allocateArray(IPLVector3.class, vertexCount);

        audioLogger.info("Transforming Scene for Audio Engine");
        List<IPLMaterial> materialList = new ArrayList<>(materials);
        Pointer<Integer> materialIndices = Pointer.allocateArray(Integer.class, triangleCount);

        for (GameItem gameItem : gameItems) {
            Pointer<IPLVector3> vectorsTmp = gameItem.getMesh().getIPLVertices();
            for (int i = 0; i < gameItem.getMesh().getIPLVertexCount(); i++) {
                vertices.get(i + vertexIndex).x(vectorsTmp.get(i).x());
                vertices.get(i + vertexIndex).y(vectorsTmp.get(i).y());
                vertices.get(i + vertexIndex).z(vectorsTmp.get(i).z());
            }
            Pointer<IPLTriangle> trianglesTmp = gameItem.getMesh().getIPlTriangles();
            for (int i = 0; i < gameItem.getMesh().getIPLTriangleCount(); i++) {
                triangles.get(i + triangleIndex).indices().setInts(trianglesTmp.get(i).indices().getInts());
            }
            IPLMaterial audioMaterial = gameItem.getMesh().getMaterial().getAudioMaterial();
            int index = materialList.indexOf(audioMaterial);
            for (int i = 0; i < gameItem.getMesh().getIPLTriangleCount(); i++) {
                materialIndices.set(triangleIndex + i, index);
            }
            triangleIndex += gameItem.getMesh().getIPLTriangleCount();
            vertexIndex += gameItem.getMesh().getIPLVertexCount();
        }

        audioLogger.info("Initializing Audio Engine");
        initAudioEngine(triangles, triangleCount, vertices, vertexCount, materialList, materialIndices);

        logger.info("Initializing Game Items");
        scene.setGameItems(gameItems);

        logger.info("Initializing Particle Engine");
        // Particles
        int maxParticles = 200;
        Vector3f particleSpeed = new Vector3f(0, 1, 0);
        particleSpeed.mul(2.5f);
        long ttl = 4000;
        long creationPeriodMillis = 300;
        float range = 0.2f;
        float scale = 0.2f;
        Mesh partMesh = OBJLoader.loadMesh("/models/particle.obj", maxParticles);
        Texture particleTexture = new Texture("/textures/particle_anim.png", 4, 4);
        Material partMaterial = new Material(particleTexture, reflectance);
        partMesh.setMaterial(partMaterial);
        Particle particle = new Particle(partMesh, particleSpeed, ttl, 100);
        particle.setScale(scale);
        particleEmitter = new FlowParticleEmitter(particle, maxParticles, creationPeriodMillis);
        particleEmitter.setActive(true);
        particleEmitter.setPositionRndRange(range);
        particleEmitter.setSpeedRndRange(range);
        particleEmitter.setAnimRange(10);
        this.scene.setParticleEmitters(new FlowParticleEmitter[]{particleEmitter});

        logger.info("Initializing Shadows");
        // Shadows
        scene.setRenderShadows(false);

        logger.info("Initializing Fog");
        // Fog
        Vector3f fogColour = new Vector3f(0.5f, 0.5f, 0.5f);
        scene.setFog(new Fog(true, fogColour, 0.02f));

        logger.info("Initializing Skybox");
        // Setup  SkyBox
        SkyBox skyBox = new SkyBox("/models/skybox.obj", new Vector3f(0.65f, 0.65f, 0.65f));
        skyBox.setScale(skyBoxScale);
        scene.setSkyBox(skyBox);

        logger.info("Initializing Lights");
        // Setup Lights
        setupLights();

        logger.info("Initializing Camera");
        camera.getPosition().x = 0.25f;
        camera.getPosition().y = 6.5f;
        camera.getPosition().z = 6.5f;
        camera.getRotation().x = 25;
        camera.getRotation().y = -1;

        logger.info("Initializing Sounds");
        // Sounds
        this.soundMgr.init();
        this.soundMgr.setAttenuationModel(AL11.AL_EXPONENT_DISTANCE);
        setupSounds();
    }

    private void initAudioEngine(Pointer<IPLTriangle> triangles, int triangleCount, Pointer<IPLVector3> vertices,
                                 int vertexCount, List<IPLMaterial> materials, Pointer<Integer> materialIndices)
            throws ErrorUtil.SteamAudioException {
        new Thread(() -> {
            try {
                audioLogger.info("Initializing Context");
                Context context = new Context();
                context.logCallback(logger::warn);
                SteamAudio.context.iplInitializeCrashHandler();

                audioLogger.info("Initializing OpenCL");
                ComputeDevice computeDevice = new ComputeDevice(IPLComputeDeviceType.IPL_COMPUTEDEVICE_GPU, 32);
                IPLSimulationSettings simulationSettings = new IPLSimulationSettings();

                audioLogger.info("Initializing Scene");
                de.kuschku.steamaudio.lib.scene.Scene scene =
                        new de.kuschku.steamaudio.lib.scene.Scene(context, computeDevice, simulationSettings,
                                materials.size());

                audioLogger.info(String.format("Loading %d materials", materials.size()));
                for (int i = 0; i < materials.size(); i++) {
                    scene.setSceneMaterial(i, materials.get(i));
                }

                audioLogger.info("Initializing Mesh");
                StaticMesh mesh = new StaticMesh(scene, vertexCount, triangleCount);
                mesh.setVertices(vertices);
                mesh.setTriangles(triangles);
                mesh.setMaterials(materialIndices);

                audioLogger.info("Finalizing Scene");
                scene.finalize(logger::info);

                audioLogger.info("Saving Scene");
                scene.save("scene.file");

                audioLogger.info("Exporting Scene");
                scene.dumpToObjFile("scene.obj");
            } catch (Exception e) {
                audioLogger.error(e);
                e.printStackTrace();
            }
        }).start();
    }

    private void setupSounds() throws Exception {
        /*
        SoundBuffer buffBack = new SoundBuffer("/sounds/background.ogg");
        soundMgr.addSoundBuffer(buffBack);
        SoundSource sourceBack = new SoundSource(true, true);
        sourceBack.setBuffer(buffBack.getBufferId());
        soundMgr.addSoundSource(Sounds.MUSIC.toString(), sourceBack);
        */

        SoundBuffer buffBeep = new SoundBuffer("/sounds/beep.ogg");
        soundMgr.addSoundBuffer(buffBeep);
        SoundSource sourceBeep = new SoundSource(false, true);
        sourceBeep.setBuffer(buffBeep.getBufferId());
        soundMgr.addSoundSource(Sounds.BEEP.toString(), sourceBeep);

        SoundBuffer buffFire = new SoundBuffer("/sounds/fire.ogg");
        soundMgr.addSoundBuffer(buffFire);
        SoundSource sourceFire = new SoundSource(true, false);
        Vector3f pos = particleEmitter.getBaseParticle().getPosition();
        sourceFire.setPosition(pos);
        sourceFire.setBuffer(buffFire.getBufferId());
        soundMgr.addSoundSource(Sounds.FIRE.toString(), sourceFire);
        sourceFire.play();

        soundMgr.setListener(new SoundListener(new Vector3f(0, 0, 0)));

        //sourceBack.play();
    }

    private void setupLights() {
        SceneLight sceneLight = new SceneLight();
        scene.setSceneLight(sceneLight);

        // Ambient Light
        sceneLight.setAmbientLight(new Vector3f(0.3f, 0.3f, 0.3f));
        sceneLight.setSkyBoxLight(new Vector3f(1.0f, 1.0f, 1.0f));

        // Directional Light
        float lightIntensity = 1.0f;
        Vector3f lightDirection = new Vector3f(0, 1, 1);
        DirectionalLight directionalLight = new DirectionalLight(new Vector3f(1, 1, 1), lightDirection, lightIntensity);
        directionalLight.setShadowPosMult(10);
        directionalLight.setOrthoCords(-10.0f, 10.0f, -10.0f, 10.0f, -1.0f, 20.0f);
        sceneLight.setDirectionalLight(directionalLight);
    }

    @Override
    public void input(Window window, MouseInput mouseInput) {
        cameraInc.set(0, 0, 0);
        if (window.isKeyPressed(GLFW_KEY_W)) {
            cameraInc.z = -1;
        } else if (window.isKeyPressed(GLFW_KEY_S)) {
            cameraInc.z = 1;
        }
        if (window.isKeyPressed(GLFW_KEY_A)) {
            cameraInc.x = -1;
        } else if (window.isKeyPressed(GLFW_KEY_D)) {
            cameraInc.x = 1;
        }
        if (window.isKeyPressed(GLFW_KEY_Z)) {
            cameraInc.y = -1;
        } else if (window.isKeyPressed(GLFW_KEY_X)) {
            cameraInc.y = 1;
        }
        if (window.isKeyPressed(GLFW_KEY_LEFT)) {
            angleInc -= 0.05f;
            soundMgr.playSoundSource(Sounds.BEEP.toString());
        } else if (window.isKeyPressed(GLFW_KEY_RIGHT)) {
            angleInc += 0.05f;
            soundMgr.playSoundSource(Sounds.BEEP.toString());
        } else {
            angleInc = 0;
        }

    }

    @Override
    public void update(float interval, MouseInput mouseInput, Window window) {
        if (mouseInput.isRightButtonPressed()) {
            // Update camera based on mouse
            Vector2f rotVec = mouseInput.getDisplVec();
            camera.moveRotation(rotVec.x * MOUSE_SENSITIVITY, rotVec.y * MOUSE_SENSITIVITY, 0);
        }

        // Update camera position
        Vector3f prevPos = new Vector3f(camera.getPosition());
        camera.movePosition(cameraInc.x * CAMERA_POS_STEP, cameraInc.y * CAMERA_POS_STEP,
                cameraInc.z * CAMERA_POS_STEP);
        // Check if there has been a collision. If true, set the y position to
        // the maximum height
        float height = -Float.MAX_VALUE;
        if (camera.getPosition().y <= height) {
            camera.setPosition(prevPos.x, prevPos.y, prevPos.z);
        }

        lightAngle += angleInc;
        if (lightAngle < 0) {
            lightAngle = 0;
        } else if (lightAngle > 180) {
            lightAngle = 180;
        }
        float zValue = (float) Math.cos(Math.toRadians(lightAngle));
        float yValue = (float) Math.sin(Math.toRadians(lightAngle));
        Vector3f lightDirection = this.scene.getSceneLight().getDirectionalLight().getDirection();
        lightDirection.x = 0;
        lightDirection.y = yValue;
        lightDirection.z = zValue;
        lightDirection.normalize();

        particleEmitter.update((long) (interval * 1000));

        // Update view matrix
        camera.updateViewMatrix();

        // Update sound listener position;
        soundMgr.updateListenerPosition(camera);

        boolean aux = mouseInput.isLeftButtonPressed();
        if (aux && !this.leftButtonPressed &&
                this.selectDetector.selectGameItem(gameItems, window, mouseInput.getCurrentPos(), camera)) {
            this.hud.incCounter();
        }
        this.leftButtonPressed = aux;
    }

    @Override
    public void render(Window window) {
        renderer.render(window, camera, scene);
        hud.render(window);
    }

    @Override
    public void cleanup() {
        renderer.cleanup();
        soundMgr.cleanup();

        scene.cleanup();
        if (hud != null) {
            hud.cleanup();
        }
    }

    private enum Sounds {
        MUSIC, BEEP, FIRE
    }
}
