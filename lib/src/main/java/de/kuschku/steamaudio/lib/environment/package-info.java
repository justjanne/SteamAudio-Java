/**
 * <h1>Environment</h1>
 * <p>
 * Functions for controlling an Environment object, which is used to export data to the audio engine. A typical usage
 * scenario for Phonon involves a game engine that must specify geometry and material information to DSP effects that
 * are applied by the audio engine. The Environment object is the mechanism for doing so. It is the only object that
 * must be passed from the game engine into the audio engine, and it encapsulates all information that DSP effects may
 * need from the game engine. If you are not using physics-based sound propagation features, you still need to create an
 * Environment object. After you create an Environment object, how you pass it from the game engine to the audio engine
 * depends on how your game engine and audio engine are designed.
 */
package de.kuschku.steamaudio.lib.environment;