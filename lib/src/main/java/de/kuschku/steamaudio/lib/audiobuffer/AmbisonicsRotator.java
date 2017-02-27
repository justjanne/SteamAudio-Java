package de.kuschku.steamaudio.lib.audiobuffer;

import de.kuschku.steamaudio.lib.SteamAudio;
import de.kuschku.steamaudio.lib.SteamAudioBridge;
import de.kuschku.steamaudio.lib.geometry.IPLQuaternion;
import de.kuschku.steamaudio.lib.util.AudioEffect;
import de.kuschku.steamaudio.lib.util.ErrorUtil;
import de.kuschku.steamaudio.lib.util.PointerHandle;

public class AmbisonicsRotator extends PointerHandle implements AudioEffect {
    public AmbisonicsRotator(IPLAmbisonicsOrdering ambisonicsOrdering) throws ErrorUtil.SteamAudioException {
        super(SteamAudio.audiobuffer::iplCreateAmbisonicsRotator, ambisonicsOrdering);
        setOnDelete(SteamAudio.audiobuffer::iplDestroyAmbisonicsRotator);
    }

    public void setRotation(IPLQuaternion rotation) {
        SteamAudioBridge.audiobuffer.__iplSetAmbisonicsRotation(this, rotation);
    }

    @Override
    public void applyEffect(IPLAudioBuffer in, IPLAudioBuffer out) {
        SteamAudioBridge.audiobuffer.__iplRotateAmbisonicsAudioBuffer(this, in, out);
    }
}
