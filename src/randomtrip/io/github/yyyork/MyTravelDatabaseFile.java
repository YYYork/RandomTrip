package randomtrip.io.github.yyyork;

import org.bukkit.entity.Player;

public class MyTravelDatabaseFile {

    public static int getXFrom(Player player){
        return FileManager.travelDatabase_file.getInt
                (player.displayName().toString().toLowerCase() +".XFrom");
    }

    public static int getYFrom(Player player){
        return FileManager.travelDatabase_file.getInt
                (player.displayName().toString().toLowerCase() +".YFrom");
    }

    public static int getRandomDistanceGoal(Player player){
        return FileManager.travelDatabase_file.getInt
                (player.displayName().toString().toLowerCase() +".RandomDistance");
    }

    public static int getRandomDestinationX(Player player){
        return FileManager.travelDatabase_file.getInt
                (player.displayName().toString().toLowerCase() +".RandomDestinationX");
    }

    public static int getRandomDestinationY(Player player){
        return FileManager.travelDatabase_file.getInt
                (player.displayName().toString().toLowerCase() +".RandomDestinationY");
    }

    public static void setXFrom(Player player,int x){
        FileManager.travelDatabase_file.set
                (player.displayName().toString().toLowerCase() +".XFrom",x);
    }

    public static void setYFrom(Player player,int y){
        FileManager.travelDatabase_file.set
                (player.displayName().toString().toLowerCase() +".YFrom",y);
    }

    public static void setRandomDistanceGoal(Player player,int distanceGoal){
        FileManager.travelDatabase_file.set
                (player.displayName().toString().toLowerCase() +".RandomDistance",distanceGoal);
    }

    public static void setRandomDestinationX(Player player,int desX){
        FileManager.travelDatabase_file.set
                (player.displayName().toString().toLowerCase() +".RandomDestinationX",desX);
    }

    public static void setRandomDestinationY(Player player,int desY){
        FileManager.travelDatabase_file.set
                (player.displayName().toString().toLowerCase() +".RandomDestinationY",desY);
    }
}
