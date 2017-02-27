package de.kuschku.steamaudio.lib;

import de.kuschku.steamaudio.lib.audiobuffer.IPLAmbisonicsOrdering;
import de.kuschku.steamaudio.lib.compute.IPLComputeDeviceType;
import de.kuschku.steamaudio.lib.environment.Environment;
import de.kuschku.steamaudio.lib.geometry.IPLSphere;
import de.kuschku.steamaudio.lib.geometry.IPLVector3;
import de.kuschku.steamaudio.lib.probes.ProbeBatch;
import de.kuschku.steamaudio.lib.probes.ProbeBox;
import de.kuschku.steamaudio.lib.probes.ProbeManager;
import de.kuschku.steamaudio.lib.scene.*;
import de.kuschku.steamaudio.lib.simulation.SimulationData;
import de.kuschku.steamaudio.lib.util.PointerHandle;
import org.bridj.BridJ;
import org.bridj.CRuntime;
import org.bridj.IntValuedEnum;
import org.bridj.Pointer;
import org.bridj.ann.Library;
import org.bridj.ann.Ptr;
import org.bridj.ann.Runtime;

import java.nio.ByteBuffer;

import static de.kuschku.steamaudio.lib.util.NativeUtil.pointerToString;
import static de.kuschku.steamaudio.lib.util.PointerHandle.reference;
import static org.bridj.Pointer.*;

@SuppressWarnings("unused")
public class SteamAudio {
    @Library("steamaudio")
    @Runtime(CRuntime.class)
    public static class binauralrenderer {
        static {
            BridJ.register();
        }

        public static void iplDestroyBinauralRenderer(Pointer<Pointer<?>> renderer) {
            iplDestroyBinauralRenderer(getPeer(renderer));
        }

        protected native static void iplDestroyBinauralRenderer(@Ptr long renderer);
    }

    @Library("steamaudio")
    @Runtime(CRuntime.class)
    public static class panningeffect {
        static {
            BridJ.register();
        }

        public static void iplDestroyPanningEffect(Pointer<Pointer<?>> effect) {
            iplDestroyPanningEffect(getPeer(effect));
        }

        protected native static void iplDestroyPanningEffect(@Ptr long effect);
    }

    @Library("steamaudio")
    @Runtime(CRuntime.class)
    public static class binauraleffect {
        static {
            BridJ.register();
        }

        public static void iplDestroyBinauralEffect(Pointer<Pointer<?>> effect) {
            iplDestroyBinauralEffect(getPeer(effect));
        }

        protected native static void iplDestroyBinauralEffect(@Ptr long effect);

    }

    @Library("steamaudio")
    @Runtime(CRuntime.class)
    public static class virtualsurround {
        static {
            BridJ.register();
        }

        public static void iplDestroyVirtualSurroundEffect(Pointer<Pointer<?>> effect) {
            iplDestroyVirtualSurroundEffect(getPeer(effect));
        }

        protected native static void iplDestroyVirtualSurroundEffect(@Ptr long effect);
    }

    @Library("steamaudio")
    @Runtime(CRuntime.class)
    public static class ambisonicspanning {
        static {
            BridJ.register();
        }

        public static void iplDestroyAmbisonicsPanningEffect(Pointer<Pointer<?>> effect) {
            iplDestroyAmbisonicsPanningEffect(getPeer(effect));
        }

        protected native static void iplDestroyAmbisonicsPanningEffect(@Ptr long effect);
    }

    @Library("steamaudio")
    @Runtime(CRuntime.class)
    public static class ambisonics {
        static {
            BridJ.register();
        }

        public static void iplDestroyAmbisonicsBinauralEffect(Pointer<Pointer<?>> effect) {
            iplDestroyAmbisonicsBinauralEffect(getPeer(effect));
        }

        protected native static void iplDestroyAmbisonicsBinauralEffect(@Ptr long effect);
    }

    @Library("steamaudio")
    @Runtime(CRuntime.class)
    public static class envrenderer {
        static {
            BridJ.register();
        }

        public static void iplDestroyEnvironmentalRenderer(Pointer<Pointer<?>> renderer) {
            iplDestroyEnvironmentalRenderer(getPeer(renderer));
        }

        protected native static void iplDestroyEnvironmentalRenderer(@Ptr long renderer);
    }

    @Library("steamaudio")
    @Runtime(CRuntime.class)
    public static class simulation {
        static {
            BridJ.register();
        }

        public static void iplDestroySimulationData(Pointer<Pointer<?>> simulationData) {
            iplDestroySimulationData(getPeer(simulationData));
        }

        protected native static void iplDestroySimulationData(@Ptr long simulationData);

        public static int iplGetNumIrSamples(SimulationData simulationData) {
            return iplGetNumIrSamples(getPeer(reference(simulationData)));
        }

        protected native static int iplGetNumIrSamples(@Ptr long simulationData);

        public static int iplGetNumIrChannels(SimulationData simulationData) {
            return iplGetNumIrChannels(getPeer(reference(simulationData)));
        }

        protected native static int iplGetNumIrChannels(@Ptr long simulationData);

        public static void iplGetSimulationResult(SimulationData simulationData, int sourceIndex, int channel,
                                                  Pointer<Float> buffer) {
            iplGetSimulationResult(getPeer(reference(simulationData)), sourceIndex, channel, getPeer(buffer));
        }

        protected native static void iplGetSimulationResult(@Ptr long simulationData, int sourceIndex, int channel,
                                                            @Ptr long buffer);
    }

    @Library("steamaudio")
    @Runtime(CRuntime.class)
    public static class directsound {
        static {
            BridJ.register();
        }

    }

    @Library("steamaudio")
    @Runtime(CRuntime.class)
    public static class conveffect {
        static {
            BridJ.register();
        }

        public static void iplDestroyConvolutionEffect(Pointer<Pointer<?>> effect) {
            iplDestroyConvolutionEffect(getPeer(effect));
        }

        protected native static void iplDestroyConvolutionEffect(@Ptr long effect);

        public static void iplSetConvolutionEffectName(PointerHandle effect, String name) {
            iplSetConvolutionEffectName(getPeer(reference(effect)), getPeer(pointerToString(name)));
        }

        protected native static void iplSetConvolutionEffectName(@Ptr long effect, @Ptr long name);
    }

    @Library("steamaudio")
    @Runtime(CRuntime.class)
    public static class probes {
        static {
            BridJ.register();
        }

        public static void iplDestroyProbeBox(Pointer<Pointer<?>> probeBox) {
            iplDestroyProbeBox(getPeer(probeBox));
        }

        protected native static void iplDestroyProbeBox(@Ptr long probeBox);

        public static int iplGetProbeSpheres(ProbeBox probeBox, Pointer<IPLSphere> probeSpheres) {
            return iplGetProbeSpheres(getPeer(reference(probeBox)), getPeer(probeSpheres));
        }

        protected native static int iplGetProbeSpheres(@Ptr long probeBox, @Ptr long probeSpheres);

        public static int iplSaveProbeBox(ProbeBox probeBox, ByteBuffer data) {
            return iplSaveProbeBox(getPeer(reference(probeBox)), getPeer(pointerToBytes(data)));
        }

        protected native static int iplSaveProbeBox(@Ptr long probeBox, @Ptr long data);

        public static IPLerror iplLoadProbeBox(ByteBuffer data, int size, Pointer<Pointer<?>> probeBox) {
            return IPLerror.fromValue(iplLoadProbeBox(getPeer(pointerToBytes(data)), size, getPeer(probeBox)));
        }

        protected native static int iplLoadProbeBox(@Ptr long data, int size, @Ptr long probeBox);

        public static IPLerror iplCreateProbeBatch(Pointer<Pointer<?>> probeBatch) {
            return IPLerror.fromValue(iplCreateProbeBatch(getPeer(probeBatch)));
        }

        protected native static int iplCreateProbeBatch(@Ptr long probeBatch);

        public static void iplDestroyProbeBatch(Pointer<Pointer<?>> probeBatch) {
            iplDestroyProbeBatch(getPeer(probeBatch));
        }

        protected native static void iplDestroyProbeBatch(@Ptr long probeBatch);

        public static void iplAddProbeToBatch(ProbeBatch probeBatch, ProbeBox probeBox, int probeIndex) {
            iplAddProbeToBatch(getPeer(reference(probeBatch)), getPeer(reference(probeBox)), probeIndex);
        }

        protected native static void iplAddProbeToBatch(@Ptr long probeBatch, @Ptr long probeBox, int probeIndex);

        public static void iplFinalizeProbeBatch(ProbeBatch probeBatch) {
            iplFinalizeProbeBatch(getPeer(reference(probeBatch)));
        }

        protected native static void iplFinalizeProbeBatch(@Ptr long probeBatch);

        public static int iplSaveProbeBatch(ProbeBatch probeBatch, ByteBuffer data) {
            return iplSaveProbeBatch(getPeer(reference(probeBatch)), getPeer(pointerToBytes(data)));
        }

        protected native static int iplSaveProbeBatch(@Ptr long probeBatch, @Ptr long data);

        public static IPLerror iplLoadProbeBatch(ByteBuffer data, int size, Pointer<Pointer<?>> probeBatch) {
            return IPLerror.fromValue(iplLoadProbeBatch(getPeer(pointerToBytes(data)), size, getPeer(probeBatch)));
        }

        protected native static int iplLoadProbeBatch(@Ptr long data, int size, @Ptr long probeBatch);

        public static IPLerror iplCreateProbeManager(Pointer<Pointer<?>> probeManager) {
            return IPLerror.fromValue(iplCreateProbeManager(getPeer(probeManager)));
        }

        protected native static int iplCreateProbeManager(@Ptr long probeManager);

        public static void iplDestroyProbeManager(Pointer<Pointer<?>> probeManager) {
            iplDestroyProbeManager(getPeer(probeManager));
        }

        protected native static void iplDestroyProbeManager(@Ptr long probeManager);

        public static void iplAddProbeBatch(ProbeManager probeManager, ProbeBatch probeBatch) {
            iplAddProbeBatch(getPeer(reference(probeManager)), getPeer(reference(probeBatch)));
        }

        protected native static void iplAddProbeBatch(@Ptr long probeManager, @Ptr long probeBatch);

        public static void iplRemoveProbeBatch(ProbeManager probeManager, ProbeBatch probeBatch) {
            iplRemoveProbeBatch(getPeer(reference(probeManager)), getPeer(reference(probeBatch)));
        }

        protected native static void iplRemoveProbeBatch(@Ptr long probeManager, @Ptr long probeBatch);
    }

    @Library("steamaudio")
    @Runtime(CRuntime.class)
    public static class baking {
        static {
            BridJ.register();
        }

        public static native void iplCancelBake();

        public static void iplDeleteBakedDataByName(ProbeBox probeBox, String sourceName) {
            iplDeleteBakedDataByName(getPeer(reference(probeBox)), getPeer(pointerToString(sourceName)));
        }

        protected native static void iplDeleteBakedDataByName(@Ptr long probeBox, @Ptr long sourceName);

        public static int iplGetBakedDataSizeByName(ProbeBox probeBox, String sourceName) {
            return iplGetBakedDataSizeByName(getPeer(reference(probeBox)), getPeer(pointerToString(sourceName)));
        }

        protected native static int iplGetBakedDataSizeByName(@Ptr long probeBox, @Ptr long sourceName);
    }

    @Library("steamaudio")
    @Runtime(CRuntime.class)
    public static class audiobuffer {
        static {
            BridJ.register();
        }

        public static IPLerror iplCreateAmbisonicsRotator(IPLAmbisonicsOrdering order, Pointer<Pointer<?>> rotator) {
            return IPLerror.fromValue(iplCreateAmbisonicsRotator((int) order.value(), getPeer(rotator)));
        }

        protected native static int iplCreateAmbisonicsRotator(int order, @Ptr long rotator);

        public static void iplDestroyAmbisonicsRotator(Pointer<Pointer<?>> rotator) {
            iplDestroyAmbisonicsRotator(getPeer(rotator));
        }

        protected native static void iplDestroyAmbisonicsRotator(@Ptr long rotator);
    }

    @Library("steamaudio")
    @Runtime(CRuntime.class)
    public static class environment {
        static {
            BridJ.register();
        }

        public static void iplDestroyEnvironment(Pointer<Pointer<?>> environment) {
            iplDestroyEnvironment(getPeer(environment));
        }

        protected native static void iplDestroyEnvironment(@Ptr long environment);

        public static void iplSetNumBounces(Environment environment, int numBounces) {
            iplSetNumBounces(getPeer(reference(environment)), numBounces);
        }

        protected native static void iplSetNumBounces(@Ptr long environment, int numBounces);
    }

    @Library("steamaudio")
    @Runtime(CRuntime.class)
    public static class geometry {
        static {
            BridJ.register();
        }

    }

    @Library("steamaudio")
    @Runtime(CRuntime.class)
    public static class compute {
        static {
            BridJ.register();
        }

        public static IPLerror iplCreateComputeDevice(IntValuedEnum<IPLComputeDeviceType> deviceType,
                                                      int numComputeUnits, Pointer<Pointer<?>> device) {
            return IPLerror
                    .fromValue(iplCreateComputeDevice((int) deviceType.value(), numComputeUnits, getPeer(device)));
        }

        protected native static int iplCreateComputeDevice(int deviceType, int numComputeUnits, @Ptr long device);

        public static void iplDestroyComputeDevice(Pointer<Pointer<?>> device) {
            iplDestroyComputeDevice(getPeer(device));
        }

        protected native static void iplDestroyComputeDevice(@Ptr long device);
    }

    @Library("steamaudio")
    @Runtime(CRuntime.class)
    public static class scene {
        static {
            BridJ.register();
        }

        public static void iplDestroyScene(Pointer<Pointer<?>> scene) {
            iplDestroyScene(getPeer(scene));
        }

        protected native static void iplDestroyScene(@Ptr long scene);

        public static void iplSetRayTracerCallbacks(Scene scene, IPLClosestHitCallback closestHitCallback,
                                                    IPLAnyHitCallback anyHitCallback, Pointer<?> userData) {
            iplSetRayTracerCallbacks(getPeer(reference(scene)), getPeer(getPointer(closestHitCallback)),
                    getPeer(getPointer(anyHitCallback)), getPeer(userData));
        }

        protected native static void iplSetRayTracerCallbacks(@Ptr long scene, @Ptr long closestHitCallback,
                                                              @Ptr long anyHitCallback, @Ptr long userData);

        public static IPLerror iplCreateStaticMesh(Scene scene, int numVertices, int numTriangles,
                                                   Pointer<Pointer<?>> staticMesh) {
            return IPLerror.fromValue(
                    iplCreateStaticMesh(getPeer(reference(scene)), numVertices, numTriangles, getPeer(staticMesh)));
        }

        protected native static int iplCreateStaticMesh(@Ptr long scene, int numVertices, int numTriangles,
                                                        @Ptr long staticMesh);

        public static void iplDestroyStaticMesh(Pointer<Pointer<?>> staticMesh) {
            iplDestroyStaticMesh(getPeer(staticMesh));
        }

        protected native static void iplDestroyStaticMesh(@Ptr long staticMesh);

        public static void iplSetStaticMeshVertices(Scene scene, StaticMesh staticMesh, Pointer<IPLVector3> vertices) {
            iplSetStaticMeshVertices(getPeer(reference(scene)), getPeer(reference(staticMesh)), getPeer(vertices));
        }

        protected native static void iplSetStaticMeshVertices(@Ptr long scene, @Ptr long staticMesh,
                                                              @Ptr long vertices);

        public static void iplSetStaticMeshTriangles(Scene scene, StaticMesh staticMesh,
                                                     Pointer<IPLTriangle> triangles) {
            iplSetStaticMeshTriangles(getPeer(reference(scene)), getPeer(reference(staticMesh)), getPeer(triangles));
        }

        protected native static void iplSetStaticMeshTriangles(@Ptr long scene, @Ptr long staticMesh,
                                                               @Ptr long triangles);

        public static void iplSetStaticMeshMaterials(Scene scene, StaticMesh staticMesh,
                                                     Pointer<Integer> materialIndices) {
            iplSetStaticMeshMaterials(getPeer(reference(scene)), getPeer(reference(staticMesh)),
                    getPeer(materialIndices));
        }

        protected native static void iplSetStaticMeshMaterials(@Ptr long scene, @Ptr long staticMesh,
                                                               @Ptr long materialIndices);

        public static void iplFinalizeScene(Scene scene, IPLFinalizeSceneProgressCallback progressCallback) {
            iplFinalizeScene(getPeer(reference(scene)), getPeer(getPointer(progressCallback)));
        }

        protected native static void iplFinalizeScene(@Ptr long scene, @Ptr long progressCallback);

        public static IPLerror iplSaveFinalizedScene(Scene scene, String fileName) {
            return IPLerror
                    .fromValue(iplSaveFinalizedScene(getPeer(reference(scene)), getPeer(pointerToString(fileName))));
        }

        protected native static int iplSaveFinalizedScene(@Ptr long scene, @Ptr long fileName);

        public static void iplDumpSceneToObjFile(Scene scene, String fileBaseName) {
            iplDumpSceneToObjFile(getPeer(reference(scene)), getPeer(pointerToString(fileBaseName)));
        }

        protected native static void iplDumpSceneToObjFile(@Ptr long scene, @Ptr long fileBaseName);
    }

    @Library("steamaudio")
    @Runtime(CRuntime.class)
    public static class context {
        static {
            BridJ.register();
        }

        public static native void iplInitializeCrashHandler();

        public static native void iplTerminateCrashHandler();
    }
}