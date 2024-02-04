package play.mcdev.tv.coinsystem.util;

import org.bukkit.entity.Player;
import play.mcdev.tv.coinsystem.Main;

import java.sql.*;
import java.util.UUID;

import static play.mcdev.tv.coinsystem.Main.*;
import static play.mcdev.tv.coinsystem.util.Utilities.*;

public class Database {
    public static Connection connection;
    public static void connectDB(){
        try{
            connection = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database, username, password);
            Main.getPlugin().getLogger().info("MySQL connection successfully connected!");
        }catch (SQLException e){
            Main.getPlugin().getLogger().severe("Error: Could not connect MySQL: " + e.getMessage());
        }
    }
    public static void disconnectDB(){
        if(connection != null){
            try {
                connection.close();
                Main.getPlugin().getLogger().info("MySQL connection closed!");
            }catch (SQLException e){
                Main.getPlugin().getLogger().severe("Error while closing MySQL connection " + e.getMessage());
            }
        }
    }
    public static void createTablePlayers(){
        try {
            PreparedStatement statement = connection.prepareStatement("CREATE TABLE IF NOT EXISTS players (id INT AUTO_INCREMENT PRIMARY KEY, player VARCHAR(50) NOT NULL, coins INT NOT NULL)");
            statement.executeUpdate();
            Main.getPlugin().getLogger().info("Table 'players' created or already exists!");
        }catch (SQLException e){
            Main.getPlugin().getLogger().info("Error while creating the table: " + e.getMessage());
            e.printStackTrace();
        }
    }
    public static void addCoins(Player player, String uuid, String coins, int icoins){
        try {
            PreparedStatement checkstatement = connection.prepareStatement("SELECT * FROM players WHERE player = ?");
            checkstatement.setString(1, uuid);
            ResultSet result = checkstatement.executeQuery();
            if(result.next()){
                PreparedStatement updateStatement = connection.prepareStatement("UPDATE players SET coins = coins + " +  coins + " WHERE player = ?");
                updateStatement.setString(1, uuid);
                updateStatement.executeUpdate();
                Main.getPlugin().getLogger().info("Player " + uuid + " updated in database!");
            }else {
                PreparedStatement insertStatement = connection.prepareStatement("INSERT INTO players (player, coins) VALUES (?, ?)");
                insertStatement.setString(1, uuid);
                insertStatement.setInt(2, icoins);
                insertStatement.executeUpdate();
                Main.getPlugin().getLogger().info("Player " + uuid + " has been added succsessfully to database!");
            }
        }catch (SQLException exception){
            Main.getPlugin().getLogger().severe("Error while adding/updating player to database: " + uuid);
            exception.printStackTrace();
        }
        player.sendMessage(prefix + "§7Du hast §e" + coins + " Coins §7erhalten!");
    }
}
