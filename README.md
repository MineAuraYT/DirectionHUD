# DirectionHUD — Minecraft 26.2 (Fabric)

A deliberately lightweight, client-side HUD mod.

## Current features
- Small static compass direction: N, NE, E, SE, S, SW, W, NW.
- Player coordinates (X, Y, Z).
- Current biome name.
- Renders as a compact text HUD near the top-left of the screen.
- No keybinds, menus, networking, background threads, or extra runtime dependencies beyond Fabric API.

## Build on Windows
Requirements: JDK 25, Gradle, Fabric Loom-compatible internet access.

From PowerShell in this folder:
```powershell
gradle build
```
The remapped JAR should appear in `build/libs/`. Copy the non-`-sources` JAR into `.minecraft/mods` alongside Fabric API.

## Notes
This is an initial 26.2 source project. It has not been compiled or tested in-game in this environment. Build it and verify the compass directions in-game before publishing.
