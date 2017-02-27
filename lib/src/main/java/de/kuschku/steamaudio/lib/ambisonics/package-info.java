/**
 * <h1>Ambisonics Binaural Effect</h1>
 * <p>
 * Functionality for rendering Ambisonics data using HRTF-based binaural rendering. Ambisonics is a powerful format for
 * encoding 3D sound fields, and exchanging them. Phonon can encode data into Ambisonics using the Panning Effect: to
 * spatialize a sound source and create an Ambisonics track, use the Panning Effect with {@link
 * de.kuschku.steamaudio.lib.audiobuffer.IPLAudioFormat#channelLayoutType} set to {@link
 * de.kuschku.steamaudio.lib.audiobuffer.IPLChannelLayoutType#IPL_CHANNELLAYOUTTYPE_AMBISONICS}.
 * <p>
 * Phonon can also decode and render Ambisonics data, using Ambisonics binaural rendering. This involves recreating the
 * 3D sound field as perceived by each ear. This is a powerful and intuitive way of listening to Ambisonics data. It is
 * extremely useful for rendering audio tracks recorded for 360 video projects.
 * <p>
 * Ambisonics also allows 3D audio rendering in VR to be significantly accelerated: instead of applying object-based
 * binaural rendering to each source individually, the sources can be encoded into Ambisonics first, then mixed, and
 * finally the mix can be rendered using Ambisonics binaural rendering. This saves CPU cycles, at the cost of some
 * spatialization accuracy.
 */
package de.kuschku.steamaudio.lib.ambisonics;