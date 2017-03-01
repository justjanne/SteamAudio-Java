#include <steamaudio.h>

/*
 *  Copyright (C) Impulsonic, Inc. All rights reserved.
 */

#ifndef IPL_PHONON_BRIDGE_H
#define IPL_PHONON_BRIDGE_H

#if (defined(_WIN32) || defined(_WIN64))
#define IPLAPI __declspec(dllexport)
#else
#define IPLAPI __attribute__((visibility("default")))
#endif

#ifdef __cplusplus
extern "C" {
#endif

IPLAPI IPLVector3
__iplCalculateRelativeDirection(IPLVector3 *sourcePosition, IPLVector3 *listenerPosition, IPLVector3 *listenerAhead,
                                IPLVector3 *listenerUp) {
    return iplCalculateRelativeDirection(*sourcePosition, *listenerPosition, *listenerAhead, *listenerUp);
}

IPLAPI IPLerror
__iplCreateScene(IPLContext *context, IPLhandle computeDevice, IPLSimulationSettings *simulationSettings,
                 IPLint32 numMaterials, IPLhandle *scene) {
    return iplCreateScene(*context, computeDevice, *simulationSettings, numMaterials, scene);
}

IPLAPI IPLvoid __iplSetSceneMaterial(IPLhandle scene, IPLint32 materialIndex, IPLMaterial *material) {
    iplSetSceneMaterial(scene, materialIndex, *material);
}

IPLAPI IPLerror
__iplLoadFinalizedScene(IPLContext *context, IPLSimulationSettings *simulationSettings, IPLstring fileName,
                        IPLhandle computeDevice, IPLLoadSceneProgressCallback progressCallback, IPLhandle *scene) {
    return iplLoadFinalizedScene(*context, *simulationSettings, fileName, computeDevice, progressCallback, scene);
}

IPLAPI IPLerror
__iplCreateEnvironment(IPLContext *context, IPLhandle computeDevice, IPLSimulationSettings *simulationSettings,
                       IPLhandle scene, IPLhandle probeManager, IPLhandle *environment) {
    return iplCreateEnvironment(*context, computeDevice, *simulationSettings, scene, probeManager, environment);
}

IPLAPI IPLvoid __iplMixAudioBuffers(IPLint32 numBuffers, IPLAudioBuffer *inputAudio, IPLAudioBuffer *outputAudio) {
    iplMixAudioBuffers(numBuffers, inputAudio, *outputAudio);
}

IPLAPI IPLvoid __iplInterleaveAudioBuffer(IPLAudioBuffer *inputAudio, IPLAudioBuffer *outputAudio) {
    iplInterleaveAudioBuffer(*inputAudio, *outputAudio);
}

IPLAPI IPLvoid __iplDeinterleaveAudioBuffer(IPLAudioBuffer *inputAudio, IPLAudioBuffer *outputAudio) {
    iplDeinterleaveAudioBuffer(*inputAudio, *outputAudio);
}

IPLAPI IPLvoid __iplConvertAudioBufferFormat(IPLAudioBuffer *inputAudio, IPLAudioBuffer *outputAudio) {
    iplConvertAudioBufferFormat(*inputAudio, *outputAudio);
}

IPLAPI IPLvoid __iplSetAmbisonicsRotation(IPLhandle rotator, IPLQuaternion *quaternion) {
    iplSetAmbisonicsRotation(rotator, *quaternion);
}

IPLAPI IPLvoid __iplRotateAmbisonicsAudioBuffer(IPLhandle rotator, IPLAudioBuffer *inputAudio,
                                                IPLAudioBuffer *outputAudio) {
    iplRotateAmbisonicsAudioBuffer(rotator, *inputAudio, *outputAudio);
}

IPLAPI IPLerror
__iplCreateBinauralRenderer(IPLContext *context, IPLRenderingSettings *renderingSettings, IPLbyte *hrtfData,
                            IPLhandle *renderer) {
    return iplCreateBinauralRenderer(*context, *renderingSettings, hrtfData, renderer);
}

IPLAPI IPLerror __iplCreatePanningEffect(IPLhandle renderer, IPLAudioFormat *inputFormat, IPLAudioFormat *outputFormat,
                                         IPLhandle *effect) {
    return iplCreatePanningEffect(renderer, *inputFormat, *outputFormat, effect);
}

IPLAPI IPLvoid __iplApplyPanningEffect(IPLhandle effect, IPLAudioBuffer *inputAudio, IPLVector3 *direction,
                                       IPLAudioBuffer *outputAudio) {
    iplApplyPanningEffect(effect, *inputAudio, *direction, *outputAudio);
}

IPLAPI IPLerror __iplCreateBinauralEffect(IPLhandle renderer, IPLAudioFormat *inputFormat, IPLAudioFormat *outputFormat,
                                          IPLhandle *effect) {
    return iplCreateBinauralEffect(renderer, *inputFormat, *outputFormat, effect);
}

IPLAPI IPLvoid __iplApplyBinauralEffect(IPLhandle effect, IPLAudioBuffer *inputAudio, IPLVector3 *direction,
                                        IPLHrtfInterpolation interpolation, IPLAudioBuffer *outputAudio) {
    iplApplyBinauralEffect(effect, *inputAudio, *direction, interpolation, *outputAudio);
}

IPLAPI IPLerror
__iplCreateVirtualSurroundEffect(IPLhandle renderer, IPLAudioFormat *inputFormat, IPLAudioFormat *outputFormat,
                                 IPLhandle *effect) {
    return iplCreateVirtualSurroundEffect(renderer, *inputFormat, *outputFormat, effect);
}

IPLAPI IPLvoid __iplApplyVirtualSurroundEffect(IPLhandle effect, IPLAudioBuffer *inputAudio,
                                               IPLAudioBuffer *outputAudio) {
    iplApplyVirtualSurroundEffect(effect, *inputAudio, *outputAudio);
}

IPLAPI IPLerror
__iplCreateAmbisonicsPanningEffect(IPLhandle renderer, IPLAudioFormat *inputFormat, IPLAudioFormat *outputFormat,
                                   IPLhandle *effect) {
    return iplCreateAmbisonicsPanningEffect(renderer, *inputFormat, *outputFormat, effect);
}

IPLAPI IPLvoid __iplApplyAmbisonicsPanningEffect(IPLhandle effect, IPLAudioBuffer *inputAudio,
                                                 IPLAudioBuffer *outputAudio) {
    iplApplyAmbisonicsPanningEffect(effect, *inputAudio, *outputAudio);
}

IPLAPI IPLerror
__iplCreateAmbisonicsBinauralEffect(IPLhandle renderer, IPLAudioFormat *inputFormat, IPLAudioFormat *outputFormat,
                                    IPLhandle *effect) {
    return iplCreateAmbisonicsBinauralEffect(renderer, *inputFormat, *outputFormat, effect);
}

IPLAPI IPLvoid __iplApplyAmbisonicsBinauralEffect(IPLhandle effect, IPLAudioBuffer *inputAudio,
                                                  IPLAudioBuffer *outputAudio) {
    iplApplyAmbisonicsBinauralEffect(effect, *inputAudio, *outputAudio);
}

IPLAPI IPLerror
__iplCreateEnvironmentalRenderer(IPLContext *context, IPLhandle environment, IPLRenderingSettings *renderingSettings,
                                 IPLAudioFormat *outputFormat, IPLhandle *renderer) {
    return iplCreateEnvironmentalRenderer(*context, environment, *renderingSettings, *outputFormat, renderer);
}

IPLAPI IPLDirectSoundPath
__iplGetDirectSoundPath(IPLhandle renderer, IPLVector3 *listenerPosition, IPLVector3 *listenerAhead,
                        IPLVector3 *listenerUp, IPLVector3 *sourcePosition, IPLfloat32 sourceRadius,
                        IPLDirectOcclusionMethod occlusionMethod) {
    return iplGetDirectSoundPath(renderer, *listenerPosition, *listenerAhead, *listenerUp, *sourcePosition,
                                 sourceRadius, occlusionMethod);
}

IPLAPI IPLerror __iplCreateConvolutionEffect(IPLhandle renderer, IPLstring name, IPLSimulationType *simulationType,
                                             IPLAudioFormat *inputFormat, IPLAudioFormat *outputFormat,
                                             IPLhandle *effect) {
    return iplCreateConvolutionEffect(renderer, name, *simulationType, *inputFormat, *outputFormat, effect);
}

IPLAPI IPLvoid __iplSetDryAudioForConvolutionEffect(IPLhandle effect, IPLVector3 *sourcePosition,
                                                    IPLAudioBuffer *dryAudio) {
    iplSetDryAudioForConvolutionEffect(effect, *sourcePosition, *dryAudio);
}

IPLAPI IPLvoid
__iplGetWetAudioForConvolutionEffect(IPLhandle effect, IPLVector3 *listenerPosition, IPLVector3 *listenerAhead,
                                     IPLVector3 *listenerUp, IPLAudioBuffer *wetAudio) {
    iplGetWetAudioForConvolutionEffect(effect, *listenerPosition, *listenerAhead, *listenerUp, *wetAudio);
}

IPLAPI IPLvoid
__iplGetMixedEnvironmentalAudio(IPLhandle renderer, IPLVector3 *listenerPosition, IPLVector3 *listenerAhead,
                                IPLVector3 *listenerUp, IPLAudioBuffer *mixedWetAudio) {
    iplGetMixedEnvironmentalAudio(renderer, *listenerPosition, *listenerAhead, *listenerUp, *mixedWetAudio);
}

IPLAPI IPLerror __iplCreateProbeBox(IPLhandle scene, IPLBox box, IPLProbePlacementParams *placementParams,
                                    IPLProbePlacementProgressCallback progressCallback, IPLhandle *probeBox) {
    return iplCreateProbeBox(scene, box, *placementParams, progressCallback, probeBox);
}

IPLAPI IPLvoid __iplBakeReverb(IPLhandle environment, IPLhandle probeBox, IPLBakingSettings *bakingSettings,
                               IPLBakeProgressCallback progressCallback) {
    iplBakeReverb(environment, probeBox, *bakingSettings, progressCallback);
}

IPLAPI IPLvoid
__iplBakePropagation(IPLhandle environment, IPLhandle probeBox, IPLSphere *sourceInfluence, IPLstring sourceName,
                     IPLBakingSettings *bakingSettings, IPLBakeProgressCallback progressCallback) {
    iplBakePropagation(environment, probeBox, *sourceInfluence, sourceName, *bakingSettings, progressCallback);
}

IPLAPI IPLvoid
__iplBakeStaticListener(IPLhandle environment, IPLhandle probeBox, IPLSphere *listenerInfluence, IPLstring listenerName,
                        IPLBakingSettings *bakingSettings, IPLBakeProgressCallback progressCallback) {
    iplBakeStaticListener(environment, probeBox, *listenerInfluence, listenerName, *bakingSettings, progressCallback);
}

IPLAPI IPLerror __iplCreateSimulationData(IPLSimulationSettings *simulationSettings,
                                        IPLRenderingSettings *renderingSettings, IPLhandle* simulationData) {
    return iplCreateSimulationData(*simulationSettings, *renderingSettings, simulationData);
}

IPLAPI IPLvoid __iplGenerateSimulationData(IPLhandle simulationData, IPLhandle environment,
                                         IPLVector3 *listenerPosition, IPLVector3 *listenerAhead, IPLVector3 *listenerUp, IPLVector3* sources) {
    iplGenerateSimulationData(simulationData, environment, *listenerPosition, *listenerAhead, *listenerUp, sources);
}

#ifdef __cplusplus
}
#endif

#endif
