package de.kuschku.steamaudio.lib.util;

import de.kuschku.steamaudio.lib.audiobuffer.IPLAudioBuffer;

public interface AudioEffect {
    void applyEffect(IPLAudioBuffer in, IPLAudioBuffer out);
}
