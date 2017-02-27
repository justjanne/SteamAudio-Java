package de.kuschku.steamaudio.lib;

import de.kuschku.steamaudio.lib.ambisonics.AmbisonicsBinauralEffect;
import de.kuschku.steamaudio.lib.ambisonicspanning.AmbisonicsPanningEffect;
import de.kuschku.steamaudio.lib.audiobuffer.AmbisonicsRotator;
import de.kuschku.steamaudio.lib.audiobuffer.IPLAudioBuffer;
import de.kuschku.steamaudio.lib.audiobuffer.IPLAudioFormat;
import de.kuschku.steamaudio.lib.baking.IPLBakeProgressCallback;
import de.kuschku.steamaudio.lib.baking.IPLBakingSettings;
import de.kuschku.steamaudio.lib.binauraleffect.BinauralEffect;
import de.kuschku.steamaudio.lib.binauraleffect.IPLHrtfInterpolation;
import de.kuschku.steamaudio.lib.binauralrenderer.BinauralRenderer;
import de.kuschku.steamaudio.lib.compute.ComputeDevice;
import de.kuschku.steamaudio.lib.context.IPLContext;
import de.kuschku.steamaudio.lib.directsound.IPLDirectOcclusionMethod;
import de.kuschku.steamaudio.lib.directsound.IPLDirectSoundPath;
import de.kuschku.steamaudio.lib.environment.Environment;
import de.kuschku.steamaudio.lib.envrenderer.EnvironmentalRenderer;
import de.kuschku.steamaudio.lib.geometry.IPLBox;
import de.kuschku.steamaudio.lib.geometry.IPLQuaternion;
import de.kuschku.steamaudio.lib.geometry.IPLSphere;
import de.kuschku.steamaudio.lib.geometry.IPLVector3;
import de.kuschku.steamaudio.lib.panningeffect.PanningEffect;
import de.kuschku.steamaudio.lib.probes.IPLProbePlacementParams;
import de.kuschku.steamaudio.lib.probes.IPLProbePlacementProgressCallback;
import de.kuschku.steamaudio.lib.probes.ProbeBox;
import de.kuschku.steamaudio.lib.probes.ProbeManager;
import de.kuschku.steamaudio.lib.rendersettings.IPLRenderingSettings;
import de.kuschku.steamaudio.lib.scene.IPLLoadSceneProgressCallback;
import de.kuschku.steamaudio.lib.scene.IPLMaterial;
import de.kuschku.steamaudio.lib.scene.Scene;
import de.kuschku.steamaudio.lib.simulation.IPLSimulationSettings;
import de.kuschku.steamaudio.lib.simulation.IPLSimulationType;
import de.kuschku.steamaudio.lib.simulation.SimulationData;
import de.kuschku.steamaudio.lib.util.PointerHandle;
import de.kuschku.steamaudio.lib.virtualsurround.VirtualSurroundEffect;
import org.bridj.BridJ;
import org.bridj.CRuntime;
import org.bridj.IntValuedEnum;
import org.bridj.Pointer;
import org.bridj.ann.Library;
import org.bridj.ann.Ptr;
import org.bridj.ann.Runtime;

import java.nio.ByteBuffer;

import static de.kuschku.steamaudio.lib.util.NativeUtil.pointerToString;
import static org.bridj.Pointer.*;

@SuppressWarnings("unused")
public class SteamAudioBridge {
    @Library("steamaudio_bridge")
    @Runtime(CRuntime.class)
    public static class binauralrenderer {
        static {
            BridJ.register();
        }

        public static IPLerror __iplCreateBinauralRenderer(IPLContext context, IPLRenderingSettings renderingSettings,
                                                           ByteBuffer hrtfData, Pointer<Pointer<?>> renderer) {
            return IPLerror.fromValue(
                    __iplCreateBinauralRenderer(getPeer(getPointer(context)), getPeer(getPointer(renderingSettings)),
                            getPeer(pointerToBytes(hrtfData)), getPeer(renderer)));
        }

        protected native static int __iplCreateBinauralRenderer(@Ptr long context, @Ptr long renderingSettings,
                                                                @Ptr long hrtfData, @Ptr long renderer);
    }

    @Library("steamaudio_bridge")
    @Runtime(CRuntime.class)
    public static class panningeffect {
        static {
            BridJ.register();
        }

        public static IPLerror __iplCreatePanningEffect(BinauralRenderer renderer, IPLAudioFormat inputFormat,
                                                        IPLAudioFormat outputFormat, Pointer<Pointer<?>> effect) {
            return IPLerror.fromValue(__iplCreatePanningEffect(getPeer(PointerHandle.reference(renderer)),
                    getPeer(getPointer(inputFormat)), getPeer(getPointer(outputFormat)), getPeer(effect)));
        }

        protected native static int __iplCreatePanningEffect(@Ptr long renderer, @Ptr long inputFormat,
                                                             @Ptr long outputFormat, @Ptr long effect);

        public static void __iplApplyPanningEffect(PanningEffect effect, IPLAudioBuffer inputAudio,
                                                   IPLVector3 direction, IPLAudioBuffer outputAudio) {
            __iplApplyPanningEffect(getPeer(PointerHandle.reference(effect)), getPeer(getPointer(inputAudio)),
                    getPeer(getPointer(direction)), getPeer(getPointer(outputAudio)));
        }

        protected native static void __iplApplyPanningEffect(@Ptr long effect, @Ptr long inputAudio,
                                                             @Ptr long direction, @Ptr long outputAudio);
    }

    @Library("steamaudio_bridge")
    @Runtime(CRuntime.class)
    public static class binauraleffect {
        static {
            BridJ.register();
        }

        public static IPLerror __iplCreateBinauralEffect(BinauralRenderer renderer, IPLAudioFormat inputFormat,
                                                         IPLAudioFormat outputFormat, Pointer<Pointer<?>> effect) {
            return IPLerror.fromValue(__iplCreateBinauralEffect(getPeer(PointerHandle.reference(renderer)),
                    getPeer(getPointer(inputFormat)), getPeer(getPointer(outputFormat)), getPeer(effect)));
        }

        protected native static int __iplCreateBinauralEffect(@Ptr long renderer, @Ptr long inputFormat,
                                                              @Ptr long outputFormat, @Ptr long effect);

        public static void __iplApplyBinauralEffect(BinauralEffect effect, IPLAudioBuffer inputAudio,
                                                    IPLVector3 direction,
                                                    IntValuedEnum<IPLHrtfInterpolation> interpolation,
                                                    IPLAudioBuffer outputAudio) {
            __iplApplyBinauralEffect(getPeer(PointerHandle.reference(effect)), getPeer(getPointer(inputAudio)),
                    getPeer(getPointer(direction)), (int) interpolation.value(), getPeer(getPointer(outputAudio)));
        }

        protected native static void __iplApplyBinauralEffect(@Ptr long effect, @Ptr long inputAudio,
                                                              @Ptr long direction, int interpolation,
                                                              @Ptr long outputAudio);
    }

    @Library("steamaudio_bridge")
    @Runtime(CRuntime.class)
    public static class virtualsurround {
        static {
            BridJ.register();
        }

        public static IPLerror __iplCreateVirtualSurroundEffect(BinauralRenderer renderer, IPLAudioFormat inputFormat,
                                                                IPLAudioFormat outputFormat,
                                                                Pointer<Pointer<?>> effect) {
            return IPLerror.fromValue(__iplCreateVirtualSurroundEffect(getPeer(PointerHandle.reference(renderer)),
                    getPeer(getPointer(inputFormat)), getPeer(getPointer(outputFormat)), getPeer(effect)));
        }

        protected native static int __iplCreateVirtualSurroundEffect(@Ptr long renderer, @Ptr long inputFormat,
                                                                     @Ptr long outputFormat, @Ptr long effect);

        public static void __iplApplyVirtualSurroundEffect(VirtualSurroundEffect effect, IPLAudioBuffer inputAudio,
                                                           IPLAudioBuffer outputAudio) {
            __iplApplyVirtualSurroundEffect(getPeer(PointerHandle.reference(effect)), getPeer(getPointer(inputAudio)),
                    getPeer(getPointer(outputAudio)));
        }

        protected native static void __iplApplyVirtualSurroundEffect(@Ptr long effect, @Ptr long inputAudio,
                                                                     @Ptr long outputAudio);
    }

    @Library("steamaudio_bridge")
    @Runtime(CRuntime.class)
    public static class ambisonicspanning {
        static {
            BridJ.register();
        }

        public static IPLerror __iplCreateAmbisonicsPanningEffect(BinauralRenderer renderer, IPLAudioFormat inputFormat,
                                                                  IPLAudioFormat outputFormat,
                                                                  Pointer<Pointer<?>> effect) {
            return IPLerror.fromValue(__iplCreateAmbisonicsPanningEffect(getPeer(PointerHandle.reference(renderer)),
                    getPeer(getPointer(inputFormat)), getPeer(getPointer(outputFormat)), getPeer(effect)));
        }

        protected native static int __iplCreateAmbisonicsPanningEffect(@Ptr long renderer, @Ptr long inputFormat,
                                                                       @Ptr long outputFormat, @Ptr long effect);

        public static void __iplApplyAmbisonicsPanningEffect(AmbisonicsPanningEffect effect, IPLAudioBuffer inputAudio,
                                                             IPLAudioBuffer outputAudio) {
            __iplApplyAmbisonicsPanningEffect(getPeer(PointerHandle.reference(effect)), getPeer(getPointer(inputAudio)),
                    getPeer(getPointer(outputAudio)));
        }

        protected native static void __iplApplyAmbisonicsPanningEffect(@Ptr long effect, @Ptr long inputAudio,
                                                                       @Ptr long outputAudio);
    }

    @Library("steamaudio_bridge")
    @Runtime(CRuntime.class)
    public static class ambisonics {
        static {
            BridJ.register();
        }

        public static IPLerror __iplCreateAmbisonicsBinauralEffect(BinauralRenderer renderer,
                                                                   IPLAudioFormat inputFormat,
                                                                   IPLAudioFormat outputFormat,
                                                                   Pointer<Pointer<?>> effect) {
            return IPLerror.fromValue(__iplCreateAmbisonicsBinauralEffect(getPeer(PointerHandle.reference(renderer)),
                    getPeer(getPointer(inputFormat)), getPeer(getPointer(outputFormat)), getPeer(effect)));
        }

        protected native static int __iplCreateAmbisonicsBinauralEffect(@Ptr long renderer, @Ptr long inputFormat,
                                                                        @Ptr long outputFormat, @Ptr long effect);

        public static void __iplApplyAmbisonicsBinauralEffect(AmbisonicsBinauralEffect effect,
                                                              IPLAudioBuffer inputAudio, IPLAudioBuffer outputAudio) {
            __iplApplyAmbisonicsBinauralEffect(getPeer(PointerHandle.reference(effect)),
                    getPeer(getPointer(inputAudio)), getPeer(getPointer(outputAudio)));
        }

        protected native static void __iplApplyAmbisonicsBinauralEffect(@Ptr long effect, @Ptr long inputAudio,
                                                                        @Ptr long outputAudio);
    }

    @Library("steamaudio_bridge")
    @Runtime(CRuntime.class)
    public static class envrenderer {
        static {
            BridJ.register();
        }

        public static IPLerror __iplCreateEnvironmentalRenderer(IPLContext context, Environment environment,
                                                                IPLRenderingSettings renderingSettings,
                                                                IPLAudioFormat outputFormat,
                                                                Pointer<Pointer<?>> renderer) {
            return IPLerror.fromValue(__iplCreateEnvironmentalRenderer(getPeer(getPointer(context)),
                    getPeer(PointerHandle.reference(environment)), getPeer(getPointer(renderingSettings)),
                    getPeer(getPointer(outputFormat)), getPeer(renderer)));
        }

        protected native static int __iplCreateEnvironmentalRenderer(@Ptr long context, @Ptr long environment,
                                                                     @Ptr long renderingSettings,
                                                                     @Ptr long outputFormat, @Ptr long renderer);
    }

    @Library("steamaudio_bridge")
    @Runtime(CRuntime.class)
    public static class simulation {
        static {
            BridJ.register();
        }

        public static IPLerror __iplCreateSimulationData(IPLSimulationSettings simulationSettings,
                                                         IPLRenderingSettings renderingSettings,
                                                         Pointer<Pointer<?>> simulationData) {
            return IPLerror.fromValue(
                    __iplCreateSimulationData(simulationSettings, renderingSettings, getPeer(simulationData)));
        }

        protected native static int __iplCreateSimulationData(IPLSimulationSettings simulationSettings,
                                                              IPLRenderingSettings renderingSettings,
                                                              @Ptr long simulationData);

        public static void __iplGenerateSimulationData(SimulationData simulationData, Environment environment,
                                                       IPLVector3 listenerPosition, IPLVector3 listenerAhead,
                                                       IPLVector3 listenerUp, Pointer<IPLVector3> sources) {
            __iplGenerateSimulationData(getPeer(PointerHandle.reference(simulationData)),
                    getPeer(PointerHandle.reference(environment)), getPeer(getPointer(listenerPosition)),
                    getPeer(getPointer(listenerAhead)), getPeer(getPointer(listenerUp)), getPeer(sources));
        }

        protected native static void __iplGenerateSimulationData(@Ptr long simulationData, @Ptr long environment,
                                                                 @Ptr long listenerPosition, @Ptr long listenerAhead,
                                                                 @Ptr long listenerUp, @Ptr long sources);
    }

    @Library("steamaudio_bridge")
    @Runtime(CRuntime.class)
    public static class directsound {
        static {
            BridJ.register();
        }

        public static IPLDirectSoundPath __iplGetDirectSoundPath(BinauralRenderer renderer, IPLVector3 listenerPosition,
                                                                 IPLVector3 listenerAhead, IPLVector3 listenerUp,
                                                                 IPLVector3 sourcePosition, float sourceRadius,
                                                                 IntValuedEnum<IPLDirectOcclusionMethod> occlusionMethod) {
            return __iplGetDirectSoundPath(getPeer(PointerHandle.reference(renderer)),
                    getPeer(getPointer(listenerPosition)), getPeer(getPointer(listenerAhead)),
                    getPeer(getPointer(listenerUp)), getPeer(getPointer(sourcePosition)), sourceRadius,
                    (int) occlusionMethod.value());
        }

        protected native static IPLDirectSoundPath __iplGetDirectSoundPath(@Ptr long renderer,
                                                                           @Ptr long listenerPosition,
                                                                           @Ptr long listenerAhead,
                                                                           @Ptr long listenerUp,
                                                                           @Ptr long sourcePosition, float sourceRadius,
                                                                           int occlusionMethod);
    }

    @Library("steamaudio_bridge")
    @Runtime(CRuntime.class)
    public static class conveffect {
        static {
            BridJ.register();
        }

        public static IPLerror __iplCreateConvolutionEffect(EnvironmentalRenderer renderer, String name,
                                                            IntValuedEnum<IPLSimulationType> simulationType,
                                                            IPLAudioFormat inputFormat, IPLAudioFormat outputFormat,
                                                            Pointer<Pointer<?>> effect) {
            return IPLerror.fromValue(__iplCreateConvolutionEffect(getPeer(PointerHandle.reference(renderer)),
                    getPeer(pointerToString(name)), (int) simulationType.value(), getPeer(getPointer(inputFormat)),
                    getPeer(getPointer(outputFormat)), getPeer(effect)));
        }

        protected native static int __iplCreateConvolutionEffect(@Ptr long renderer, @Ptr long name, int simulationType,
                                                                 @Ptr long inputFormat, @Ptr long outputFormat,
                                                                 @Ptr long effect);

        public static void __iplSetDryAudioForConvolutionEffect(PointerHandle effect, IPLVector3 sourcePosition,
                                                                IPLAudioBuffer dryAudio) {
            __iplSetDryAudioForConvolutionEffect(getPeer(PointerHandle.reference(effect)),
                    getPeer(getPointer(sourcePosition)), getPeer(getPointer(dryAudio)));
        }

        protected native static void __iplSetDryAudioForConvolutionEffect(@Ptr long effect, @Ptr long sourcePosition,
                                                                          @Ptr long dryAudio);

        public static void __iplGetWetAudioForConvolutionEffect(PointerHandle effect, IPLVector3 listenerPosition,
                                                                IPLVector3 listenerAhead, IPLVector3 listenerUp,
                                                                IPLAudioBuffer wetAudio) {
            __iplGetWetAudioForConvolutionEffect(getPeer(PointerHandle.reference(effect)),
                    getPeer(getPointer(listenerPosition)), getPeer(getPointer(listenerAhead)),
                    getPeer(getPointer(listenerUp)), getPeer(getPointer(wetAudio)));
        }

        protected native static void __iplGetWetAudioForConvolutionEffect(@Ptr long effect, @Ptr long listenerPosition,
                                                                          @Ptr long listenerAhead, @Ptr long listenerUp,
                                                                          @Ptr long wetAudio);

        public static void __iplGetMixedEnvironmentalAudio(EnvironmentalRenderer renderer, IPLVector3 listenerPosition,
                                                           IPLVector3 listenerAhead, IPLVector3 listenerUp,
                                                           IPLAudioBuffer mixedWetAudio) {
            __iplGetMixedEnvironmentalAudio(getPeer(PointerHandle.reference(renderer)),
                    getPeer(getPointer(listenerPosition)), getPeer(getPointer(listenerAhead)),
                    getPeer(getPointer(listenerUp)), getPeer(getPointer(mixedWetAudio)));
        }

        protected native static void __iplGetMixedEnvironmentalAudio(@Ptr long renderer, @Ptr long listenerPosition,
                                                                     @Ptr long listenerAhead, @Ptr long listenerUp,
                                                                     @Ptr long mixedWetAudio);
    }

    @Library("steamaudio_bridge")
    @Runtime(CRuntime.class)
    public static class probes {
        static {
            BridJ.register();
        }

        public static IPLerror __iplCreateProbeBox(Scene scene, IPLBox box, IPLProbePlacementParams placementParams,
                                                   IPLProbePlacementProgressCallback progressCallback,
                                                   Pointer<Pointer<?>> probeBox) {
            return IPLerror.fromValue(
                    __iplCreateProbeBox(getPeer(PointerHandle.reference(scene)), getPeer(getPointer(box)),
                            getPeer(getPointer(placementParams)), getPeer(Pointer.getPointer(progressCallback)),
                            getPeer(probeBox)));
        }

        protected native static int __iplCreateProbeBox(@Ptr long scene, @Ptr long box, @Ptr long placementParams,
                                                        @Ptr long progressCallback, @Ptr long probeBox);
    }

    @Library("steamaudio_bridge")
    @Runtime(CRuntime.class)
    public static class baking {
        static {
            BridJ.register();
        }

        public static void __iplBakeReverb(Environment environment, ProbeBox probeBox, IPLBakingSettings bakingSettings,
                                           Pointer<IPLBakeProgressCallback> progressCallback) {
            __iplBakeReverb(getPeer(PointerHandle.reference(environment)), getPeer(PointerHandle.reference(probeBox)),
                    getPeer(getPointer(bakingSettings)), getPeer(progressCallback));
        }

        protected native static void __iplBakeReverb(@Ptr long environment, @Ptr long probeBox,
                                                     @Ptr long bakingSettings, @Ptr long progressCallback);

        public static void __iplBakePropagation(Environment environment, ProbeBox probeBox, IPLSphere sourceInfluence,
                                                String sourceName, IPLBakingSettings bakingSettings,
                                                IPLBakeProgressCallback progressCallback) {
            __iplBakePropagation(getPeer(PointerHandle.reference(environment)),
                    getPeer(PointerHandle.reference(probeBox)), getPeer(getPointer(sourceInfluence)),
                    getPeer(pointerToString(sourceName)), getPeer(getPointer(bakingSettings)),
                    getPeer(getPointer(progressCallback)));
        }

        protected native static void __iplBakePropagation(@Ptr long environment, @Ptr long probeBox,
                                                          @Ptr long sourceInfluence, @Ptr long sourceName,
                                                          @Ptr long bakingSettings, @Ptr long progressCallback);

        public static void __iplBakeStaticListener(Environment environment, ProbeBox probeBox,
                                                   IPLSphere listenerInfluence, String listenerName,
                                                   IPLBakingSettings bakingSettings,
                                                   IPLBakeProgressCallback progressCallback) {
            __iplBakeStaticListener(getPeer(PointerHandle.reference(environment)),
                    getPeer(PointerHandle.reference(probeBox)), getPeer(getPointer(listenerInfluence)),
                    getPeer(pointerToString(listenerName)), getPeer(getPointer(bakingSettings)),
                    getPeer(getPointer(progressCallback)));
        }

        protected native static void __iplBakeStaticListener(@Ptr long environment, @Ptr long probeBox,
                                                             @Ptr long listenerInfluence, @Ptr long listenerName,
                                                             @Ptr long bakingSettings, @Ptr long progressCallback);
    }

    @Library("steamaudio_bridge")
    @Runtime(CRuntime.class)
    public static class audiobuffer {
        static {
            BridJ.register();
        }

        public static void __iplMixAudioBuffers(int numBuffers, Pointer<IPLAudioBuffer> inputAudio,
                                                IPLAudioBuffer outputAudio) {
            __iplMixAudioBuffers(numBuffers, getPeer(inputAudio), getPeer(getPointer(outputAudio)));
        }

        protected native static void __iplMixAudioBuffers(int numBuffers, @Ptr long inputAudio, @Ptr long outputAudio);

        public static native void __iplInterleaveAudioBuffer(@Ptr long inputAudio, @Ptr long outputAudio);

        public static native void __iplDeinterleaveAudioBuffer(@Ptr long inputAudio, @Ptr long outputAudio);

        public static native void __iplConvertAudioBufferFormat(@Ptr long inputAudio, @Ptr long outputAudio);

        public static void __iplSetAmbisonicsRotation(AmbisonicsRotator rotator, IPLQuaternion quaternion) {
            __iplSetAmbisonicsRotation(getPeer(PointerHandle.reference(rotator)), getPeer(getPointer(quaternion)));
        }

        protected native static void __iplSetAmbisonicsRotation(@Ptr long rotator, @Ptr long quaternion);

        public static void __iplRotateAmbisonicsAudioBuffer(AmbisonicsRotator rotator, IPLAudioBuffer inputAudio,
                                                            IPLAudioBuffer outputAudio) {
            __iplRotateAmbisonicsAudioBuffer(getPeer(PointerHandle.reference(rotator)), getPeer(getPointer(inputAudio)),
                    getPeer(getPointer(outputAudio)));
        }

        protected native static void __iplRotateAmbisonicsAudioBuffer(@Ptr long rotator, @Ptr long inputAudio,
                                                                      @Ptr long outputAudio);
    }

    @Library("steamaudio_bridge")
    @Runtime(CRuntime.class)
    public static class environment {
        static {
            BridJ.register();
        }

        public static IPLerror __iplCreateEnvironment(IPLContext context, ComputeDevice computeDevice,
                                                      IPLSimulationSettings simulationSettings, Scene scene,
                                                      ProbeManager probeManager, Pointer<Pointer<?>> environment) {
            return IPLerror.fromValue(__iplCreateEnvironment(getPeer(getPointer(context)),
                    getPeer(PointerHandle.reference(computeDevice)), getPeer(getPointer(simulationSettings)),
                    getPeer(PointerHandle.reference(scene)), getPeer(PointerHandle.reference(probeManager)),
                    getPeer(environment)));
        }

        protected native static int __iplCreateEnvironment(@Ptr long context, @Ptr long computeDevice,
                                                           @Ptr long simulationSettings, @Ptr long scene,
                                                           @Ptr long probeManager, @Ptr long environment);
    }

    @Library("steamaudio_bridge")
    @Runtime(CRuntime.class)
    public static class geometry {
        static {
            BridJ.register();
        }

        public static native IPLVector3 __iplCalculateRelativeDirection(@Ptr long sourcePosition,
                                                                        @Ptr long listenerPosition,
                                                                        @Ptr long listenerAhead, @Ptr long listenerUp);
    }

    @Library("steamaudio_bridge")
    @Runtime(CRuntime.class)
    public static class scene {
        static {
            BridJ.register();
        }

        public static IPLerror __iplCreateScene(IPLContext context, ComputeDevice computeDevice,
                                                IPLSimulationSettings simulationSettings, int numMaterials,
                                                Pointer<Pointer<?>> scene) {
            return IPLerror.fromValue(
                    __iplCreateScene(getPeer(getPointer(context)), getPeer(PointerHandle.reference(computeDevice)),
                            getPeer(getPointer(simulationSettings)), numMaterials, getPeer(scene)));
        }

        protected native static int __iplCreateScene(@Ptr long context, @Ptr long computeDevice,
                                                     @Ptr long simulationSettings, int numMaterials, @Ptr long scene);

        public static void __iplSetSceneMaterial(Scene scene, int materialIndex, IPLMaterial material) {
            __iplSetSceneMaterial(getPeer(PointerHandle.reference(scene)), materialIndex,
                    getPeer(getPointer(material)));
        }

        protected native static void __iplSetSceneMaterial(@Ptr long scene, int materialIndex, @Ptr long material);

        public static IPLerror __iplLoadFinalizedScene(IPLContext context, IPLSimulationSettings simulationSettings,
                                                       String fileName, ComputeDevice computeDevice,
                                                       IPLLoadSceneProgressCallback progressCallback,
                                                       Pointer<Pointer<?>> scene) {
            return IPLerror.fromValue(
                    __iplLoadFinalizedScene(context, simulationSettings, getPeer(pointerToString(fileName)),
                            getPeer(PointerHandle.reference(computeDevice)), getPeer(getPointer(progressCallback)),
                            getPeer(scene)));
        }

        protected native static int __iplLoadFinalizedScene(IPLContext context,
                                                            IPLSimulationSettings simulationSettings,
                                                            @Ptr long fileName, @Ptr long computeDevice,
                                                            @Ptr long progressCallback, @Ptr long scene);
    }
}