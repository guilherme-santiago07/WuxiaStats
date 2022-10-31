package me.dev.zennyel.database;

import me.dev.zennyel.Stats;
import me.dev.zennyel.stats.Cultivator;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.sql.*;
import java.util.UUID;

public final class MySQL {

    private static Connection connection;

    public static Connection openConnection() {
        String password = Stats.getInstance().getConfig().getString("Sql.password");
        String user = Stats.getInstance().getConfig().getString("Sql.user");
        String host = Stats.getInstance().getConfig().getString("Sql.host");
        String port = Stats.getInstance().getConfig().getString("Sql.port");
        String database = Stats.getInstance().getConfig().getString("Sql.database");
        String type = "jdbc:mysql://";
        String url = type+host+":"+port+"/"+database;

        try {
            return DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void createTable(){
        connection = openConnection();
        String query = "CREATE TABLE IF NOT EXISTS cultivator(id LONGTEXT,level INT, health DOUBLE, mana DOUBLE, power DOUBLE, points INT,xp DOUBLE);";
        try(Statement stm = connection.createStatement()){
            stm.execute(query);
        }catch (SQLException e){e.printStackTrace();}
    }

    public static void insertCultivator(Player p, Cultivator cultivator){
        connection = openConnection();
        String id =  p.getUniqueId().toString();
        double dois = cultivator.getLevel();
        double tres  = cultivator.getHealth();
        double quatro = cultivator.getMana();
        double cinco = cultivator.getPower();
        int seis = cultivator.getPoints();
        double sete = cultivator.getExperience();
        String query = "INSERT INTO `cultivator`(`id`, `level`, `health`, `mana`, `power`, `points`, `xp`) VALUES ('" + id + "'," + dois + "," + tres + "," + quatro + "," + cinco + "," + seis + "," + sete + ");";
        try(PreparedStatement ps = connection.prepareStatement(query)){
            ps.execute(query);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static void updateCultivator(Player p, Cultivator cultivator){
        connection = openConnection();
        String id =  p.getUniqueId().toString();
        double dois = cultivator.getLevel();
        double tres  = cultivator.getHealth();
        double quatro = cultivator.getMana();
        double cinco = cultivator.getPower();
        int seis = cultivator.getPoints();
        double sete = cultivator.getExperience();
        String query = "UPDATE `cultivator` SET  `level` =" + dois + ", `health` = " + tres + ", `mana` = "+quatro + ", `power` = " +cinco + ", `points` = " + seis + ", `xp` = " + sete + " WHERE `id` = '" + id + "';";
        try(PreparedStatement ps = connection.prepareStatement(query)){
            ps.execute(query);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }


    public static Cultivator getCultivator(Player p){
        Cultivator c;
        String query = "SELECT level, health, power, mana, points, xp FROM cultivator WHERE id = '"+ p.getUniqueId().toString() + "';";
        connection = openConnection();
        try(Statement stm = connection.createStatement()){
            ResultSet rs = stm.executeQuery(query);
            while (rs.next()){
                int level = rs.getInt("level");
                double health = rs.getDouble("health");
                double power = rs.getDouble("power");
                double mana = rs.getDouble("mana");
                int points = rs.getInt("points");
                double xp = rs.getDouble("xp");
                c = new Cultivator(level, health, mana, power, points, xp);
                return c;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }


    public static void setConnection(Connection con) {
        connection = con;
    }

    public static Connection getConnection() {
        return connection;
    }



}
