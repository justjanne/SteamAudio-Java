package de.kuschku.steamaudio.demo;

import com.sun.jna.Pointer;
import com.sun.jna.ptr.PointerByReference;
import de.kuschku.steamaudio.lib.compute.ComputeApi;
import de.kuschku.steamaudio.lib.compute.IPLComputeDeviceType;
import de.kuschku.steamaudio.lib.context.IPLContext;
import de.kuschku.steamaudio.lib.environment.EnvironmentApi;
import de.kuschku.steamaudio.lib.geometry.IPLVector3;
import de.kuschku.steamaudio.lib.scene.IPLFinalizeSceneProgressCallback;
import de.kuschku.steamaudio.lib.scene.IPLMaterial;
import de.kuschku.steamaudio.lib.scene.IPLTriangle;
import de.kuschku.steamaudio.lib.scene.SceneApi;
import de.kuschku.steamaudio.lib.simsettings.IPLSimulationSettings;
import de.kuschku.steamaudio.lib.util.IPLerror;

import java.nio.IntBuffer;
import java.util.logging.Logger;

public class Main {
    public static void main(String... args) throws Exception {
        Logger logger = Logger.getLogger(Main.class.getName());

        ComputeApi computeApi = ComputeApi.getInstance();
        SceneApi sceneApi = SceneApi.getInstance();
        EnvironmentApi environmentApi = EnvironmentApi.getInstance();

        IPLContext.ByValue context = new IPLContext.ByValue();
        context.logCallback = message -> logger.info(message.getString(0));
        PointerByReference device = new PointerByReference();
        IPLerror computeError =
                computeApi.iplCreateComputeDevice(IPLComputeDeviceType.Enum.IPL_COMPUTEDEVICE_GPU.wrap(), 32, device);
        System.out.println(computeError.getEnumValue().name());
        System.out.println(device.getValue());
        IPLSimulationSettings.ByValue simulationSettings = new IPLSimulationSettings.ByValue();
        PointerByReference scenePointer = new PointerByReference();
        IPLerror sceneError = sceneApi.iplCreateScene(context, device.getValue(), simulationSettings, 1, scenePointer);
        Pointer scene = scenePointer.getValue();
        System.out.println(sceneError.getEnumValue().name());
        System.out.println(Pointer.nativeValue(scene));

        PointerByReference staticMeshPointer = new PointerByReference();
        IPLerror meshError = sceneApi.iplCreateStaticMesh(scene, 4, 2, staticMeshPointer);
        Pointer staticMesh = staticMeshPointer.getValue();
        System.out.println(meshError.getEnumValue().name());
        System.out.println(Pointer.nativeValue(staticMesh));

        IntBuffer materialIndices = IntBuffer.allocate(2);
        materialIndices.put(0);
        materialIndices.put(0);
        sceneApi.iplSetStaticMeshMaterials(scene, staticMesh, materialIndices);
        IPLVector3 vector3Ref = new IPLVector3.ByReference();
        IPLVector3[] vector3s = vector3Ref.toSmartArray(3);
        vector3s[2].x = 0;
        vector3s[2].y = 0;
        vector3s[2].z = 0;

        vector3s[2].x = 1;
        vector3s[2].y = 1;
        vector3s[2].z = 0;

        vector3s[1].x = 0;
        vector3s[1].y = 1;
        vector3s[1].z = 0;

        vector3s[0].x = 1;
        vector3s[0].y = 0;
        vector3s[0].z = 0;
        sceneApi.iplSetStaticMeshVertices(scene, staticMesh, vector3Ref);

        IPLTriangle triangleRef = new IPLTriangle.ByReference();
        IPLTriangle[] triangles = triangleRef.toSmartArray(3);
        triangles[0].indices[0] = 1;
        triangles[0].indices[1] = 3;
        triangles[0].indices[2] = 0;
        triangles[1].indices[0] = 0;
        triangles[1].indices[1] = 2;
        triangles[1].indices[2] = 1;
        sceneApi.iplSetStaticMeshTriangles(scene, staticMesh, triangleRef);

        IPLMaterial.ByValue material = new IPLMaterial.ByValue();
        sceneApi.iplSetSceneMaterial(scene, 0, material);

        sceneApi.iplFinalizeScene(scene, System.out::println);
        sceneApi.iplDumpSceneToObjFile(scene, "file.obj");
    }
}
