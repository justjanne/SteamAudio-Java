/**
 * <h1>Environmental Renderer</h1>
 * <p>
 * Functions for managing an Environmental Renderer object. An Environmental Renderer object is the primary object used
 * by the audio engine to apply audio effects that depend on scene geometry and materials. It is created and managed by
 * the audio engine, and serves as the primary point of contact between the game engine and the audio engine. It acts as
 * a proxy between an Environment object (which is managed by the game engine), and the various objects managed by the
 * audio engine.
 */
package de.kuschku.steamaudio.lib.envrenderer;