package me.dev.zennyel.listeners;

import me.dev.zennyel.utils.TitleAPI;
import me.dev.zennyel.database.MySQL;
import me.dev.zennyel.stats.Cultivator;
import me.dev.zennyel.stats.CultivatorAPI;
import me.dev.zennyel.stats.CultivatorManager;
import org.bukkit.Bukkit;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.*;
import org.bukkit.event.server.PluginDisableEvent;

public class PlayerListeners implements Listener {

    @EventHandler(priority = EventPriority.LOW)
    public void onJoin(PlayerJoinEvent e){
        Player p = e.getPlayer();

        if(MySQL.getCultivator(p) == null) {
            if (!CultivatorAPI.getCultivatorHashMap().containsKey(p)) {
                Cultivator c = new Cultivator(1, 20, 20, 5, 1, 0);
                CultivatorAPI.putCultivator(p, c);
                MySQL.insertCultivator(p, CultivatorAPI.getCultivator(p));
                return;
            }
        }
        Cultivator cultivator = MySQL.getCultivator(p);
        CultivatorAPI.putCultivator(p, cultivator);
    }
    @EventHandler
    public void onLeave(PlayerQuitEvent e){
        Player p = e.getPlayer();
        MySQL.updateCultivator(p, CultivatorAPI.getCultivator(p));
    }
    @EventHandler
    public void onRegainHealth(EntityRegainHealthEvent e){
        e.setCancelled(true);
    }
    @EventHandler
    public void onDamage(EntityDamageEvent e){

        if(!(e.getEntity() instanceof Player))
            return;
        Player p = (Player) e.getEntity();
        Cultivator cultivator = CultivatorAPI.getCultivator(p);
        double actualHealth = cultivator.getActualHealth();

        if(actualHealth == 0){
            p.setHealth(1);
        }

        if(actualHealth < 20){
            p.setHealth(cultivator.getActualHealth());
            CultivatorManager.takeDamage(e.getDamage(), cultivator);
            TitleAPI.sendTitle(p, 20, 20, 20, "", "§c❤ " + actualHealth);
        }else {
            CultivatorManager.takeDamage(e.getDamage(), cultivator);
            TitleAPI.sendTitle(p, 20, 20, 20, "", "§c❤ " + actualHealth);
            e.setDamage(0);
        }
    }

    @EventHandler
    public void inventoryInteract(InventoryClickEvent e){
        Player p = (Player) e.getWhoClicked();
        if(e.getInventory().getName().equalsIgnoreCase("§8§lSTATS MENU")){
            e.setCancelled(true);
        }
    }
    @EventHandler
    public void onDeath(PlayerDeathEvent e){
        Player p = (Player) e.getEntity();
        Cultivator cultivator = CultivatorAPI.getCultivator(p);
        cultivator.setActualHealth(cultivator.getHealth());
    }


    @EventHandler
    public void onDamageEntity(EntityDamageByEntityEvent e){
        if(!(e.getEntity() instanceof Player))
            return;
        Player p = (Player) e.getEntity();
        if(CultivatorAPI.getCultivator(p) == null)
            return;
        Cultivator c = CultivatorAPI.getCultivator(p);
        double power = c.getPower()*2;
        e.setDamage((int) (e.getDamage() + power));
    }

}
