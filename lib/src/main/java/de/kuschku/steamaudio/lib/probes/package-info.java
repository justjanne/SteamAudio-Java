/**
 * <h1>Acoustic Probes</h1>
 * <p>
 * Functions for creating and manipulating acoustic probes. Acoustic probes are points are which Phonon samples the
 * acoustics of a scene when baking. The functions in this module allow the game engine to generate probes in specific
 * regions of the scene, store them for baking and run-time use, and visualize them in the game engine's editor.
 * Every probe has a position, and a radius of influence. The baked data corresponding to a probe is only used within
 * its radius of influence. Each probe is associated with a reverb (parametric, convolution, or both), as well as zero
 * or more acoustic responses from various sound sources.
 */
package de.kuschku.steamaudio.lib.probes;