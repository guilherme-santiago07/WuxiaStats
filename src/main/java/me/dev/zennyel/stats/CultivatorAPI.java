package me.dev.zennyel.stats;

import org.bukkit.entity.Player;

import java.util.HashMap;

public final class CultivatorAPI {

    private static HashMap<Player, Cultivator> cultivatorHashMap = new HashMap<Player, Cultivator>();

    public static void putCultivator(Player p, Cultivator c){
        cultivatorHashMap.put(p,c);
    }

    public static Cultivator getCultivator(Player p){
        return cultivatorHashMap.get(p);
    }

    public static HashMap<Player, Cultivator> getCultivatorHashMap() {
        return cultivatorHashMap;
    }

    public static void setCultivatorHashMap(HashMap<Player, Cultivator> cultivatorHashMap) {
        CultivatorAPI.cultivatorHashMap = cultivatorHashMap;
    }
}
