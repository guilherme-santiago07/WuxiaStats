package me.dev.zennyel.commands;

import me.dev.zennyel.gui.StatsGUI;
import me.dev.zennyel.stats.Cultivator;
import me.dev.zennyel.stats.CultivatorAPI;
import me.lemonypancakes.bukkit.common.actionbarapi.ActionBarAPI;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.boss.BossBar;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class StatsCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player))
            return false;
        Player p = ((Player) sender).getPlayer();
        Cultivator c = CultivatorAPI.getCultivator(p);
        p.openInventory(new StatsGUI(c).getInventory());
        return false;
    }

}
