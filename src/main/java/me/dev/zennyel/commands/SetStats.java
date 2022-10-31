package me.dev.zennyel.commands;

import me.dev.zennyel.stats.Cultivator;
import me.dev.zennyel.stats.CultivatorAPI;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetStats implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player))
        return false;

        Player p = ((Player) sender).getPlayer();
        Cultivator c = CultivatorAPI.getCultivator(p);
        int modifier = Integer.parseInt(args[2]);
        Player target = Bukkit.getPlayer(args[0]);

        if(args.length > 3){
            p.sendMessage("§6§l[WuciaCraft] §6Use /setstats <player> <stats> <level>");
            return false;
        }

        if(!Bukkit.getOnlinePlayers().contains(target)){
            p.sendMessage("§6§l[WuciaCraft] §6Jogador offline ou inexistente!");
            return false;
        }

        switch (args[1]){
            case "level":
                c.setLevel(modifier);
                p.sendMessage("§6§l[WuxiaCraft] §6Você definiu o level de " + target.getName() + " com sucesso!" );
            break;
            case "health":
                c.setHealth(modifier);
                c.setActualHealth(modifier);
                p.sendMessage("§6§l[WuxiaCraft] §6Você definiu a vitalidade de " + target.getName() + " com sucesso!" );
                break;
            case "points":
                c.setPoints(modifier);
                p.sendMessage("§6§l[WuxiaCraft] §6Você definiu os pontos de " + target.getName() + " com sucesso!" );
                break;
            case "strength":
                c.setPower(modifier);
                p.sendMessage("§6§l[WuxiaCraft] §6Você definiu a força de " + target.getName() + " com sucesso!" );
                break;
            case "mana":
                c.setMana(modifier);
                c.setActualMana(modifier);
                p.sendMessage("§6§l[WuxiaCraft] §6Você definiu a mana de " + target.getName() + " com sucesso!" );
                break;
        }
        CultivatorAPI.putCultivator(p, c);
        return false;
    }
}
