/**
 * <h1>Virtual Surround Effect</h1>
 * <p>
 * Functionality for using Virtual Surround Effect objects. Phonon includes support for _virtual surround_. This
 * involves taking multi-channel speaker-based audio data (stereo, quadraphonic, 5.1, or 7.1) and rendering audio for
 * each speaker using binaural rendering. In other words, the audio signal for each speaker is rendered as if it were
 * emanating from a point in space corresponding to the speaker's position. This allows users to experience, say, a 7.1
 * surround sound mix over regular stereo headphones.
 * <p>
 * Virtual Surround also works as a fast way to get approximate binaural rendering. All sound sources can be panned to
 * some surround format (say 7.1); after they are mixed, the 7.1 surround mix can be rendered using virtual surround.
 * This can save CPU cycles, at the cost of spatialization accuracy.
 */
package de.kuschku.steamaudio.lib.virtualsurround;