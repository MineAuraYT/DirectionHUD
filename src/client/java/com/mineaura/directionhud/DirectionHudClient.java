package com.mineaura.directionhud;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElementRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.hud.VanillaHudElements;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.Identifier;

public final class DirectionHudClient implements ClientModInitializer {
    private static final String MOD_ID = "directionhud";
    private static final String[] DIRECTIONS = {
            "N", "NE", "E", "SE", "S", "SW", "W", "NW"
    };

    @Override
    public void onInitializeClient() {
        HudElementRegistry.attachElementBefore(
                VanillaHudElements.CHAT,
                Identifier.fromNamespaceAndPath(MOD_ID, "direction_hud"),
                (graphics, deltaTracker) -> {
                    Minecraft client = Minecraft.getInstance();
                    if (client.player == null || client.level == null) return;

                    var player = client.player;
                    int directionIndex = Math.floorMod(
                            (int) Math.floor((player.getYRot() + 180.0F + 22.5F) / 45.0F),
                            8
                    );
                    String direction = DIRECTIONS[directionIndex];

                    BlockPos pos = player.blockPosition();
                    String coordinates = String.format(
                            "X: %d  Y: %d  Z: %d",
                            pos.getX(), pos.getY(), pos.getZ()
                    );

                    String biome = client.level.getBiome(pos)
                            .unwrapKey()
                            .map(key -> key.identifier().getPath().replace('_', ' '))
                            .orElse("Unknown");

                    var font = client.font;
                    int x = 8;
                    int y = 8;
                    graphics.text(font, "Direction: " + direction, x, y, 0xFFFFFFFF, true);
                    graphics.text(font, coordinates, x, y + 11, 0xFFE0D5FF, true);
                    graphics.text(font, "Biome: " + biome, x, y + 22, 0xFFBFA8FF, true);
                }
        );
    }
}
