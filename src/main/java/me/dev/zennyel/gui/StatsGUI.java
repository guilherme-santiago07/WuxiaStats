package me.dev.zennyel.gui;

import me.dev.zennyel.stats.Cultivator;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public final class StatsGUI {

    private static Inventory inventory;

    public StatsGUI(Cultivator c){
        inventory = Bukkit.createInventory(null, 9*3, "§8§lSTATS MENU");
        for(int i = 0; i < 9*3; i++){setItem(inventory, "§8*", "", Material.IRON_FENCE, i);}
        setItem(inventory, "§6Adicionar Força", "§7§lForça atual: " + c.getPower(), Material.REDSTONE_BLOCK, 12);
        setItem(inventory, "§6Adicionar Mana", "§7§lMana atual: " + c.getMana(),Material.LAPIS_BLOCK, 14);
        setItem(inventory, "§6Adicionar Vitalidade", "§7§lVitalidade atual: " + c.getHealth(),Material.EMERALD_BLOCK, 16);
        setItem(inventory, "§6PONTOS", "§6§l" + c.getPoints(),Material.NETHER_STAR, 10);
    }

    public static Inventory getInventory() {
        return inventory;
    }

    public static void setItem(Inventory inv, String title, String lore, Material Mat, int position) {
        ItemStack is = new ItemStack(Mat);
        ItemMeta isMeta = is.getItemMeta();
        isMeta.setDisplayName(title);
        ArrayList<String> metaString = new ArrayList<>();
        metaString.add(lore);
        isMeta.setLore(metaString);
        is.setItemMeta(isMeta);
        inv.setItem(position, is);
    }

}
