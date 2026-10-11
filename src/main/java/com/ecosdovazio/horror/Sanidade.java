package com.ecosdovazio.horror;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Sanidade de 0 a 100 por jogador, guardada na memória enquanto o mundo está aberto.
 * Ao fechar e abrir o mundo, volta para 100 (e ganha os 3 minutos de graça de novo).
 */
public final class Sanidade {
    private Sanidade() {}

    private static final Map<UUID, Double> VALORES = new HashMap<>();

    public static double obter(ServerPlayer p) {
        return VALORES.getOrDefault(p.getUUID(), 100.0);
    }

    public static void definir(ServerPlayer p, double valor) {
        VALORES.put(p.getUUID(), Mth.clamp(valor, 0.0, 100.0));
    }

    public static void reduzir(ServerPlayer p, double quanto) {
        definir(p, obter(p) - quanto);
    }

    public static void aumentar(ServerPlayer p, double quanto) {
        definir(p, obter(p) + quanto);
    }

    static void limpar() {
        VALORES.clear();
    }
}
