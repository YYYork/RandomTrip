package randomtrip.io.github.yyyork;

import org.bukkit.entity.Player;

import java.util.Date;
import java.util.Objects;

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

    public static long getLastTravelTime(Player player){
        return FileManager.travelDatabase_file.getLong
                (player.displayName().toString().toLowerCase() +".LastTravelTime");
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

    public static void setLastTravelTime(Player player){
        Date date = new Date();
        long time = date.getTime();
        FileManager.travelDatabase_file.set
                (player.displayName().toString().toLowerCase() +".LastTravelTime",time);
    }

    public static void setDefaultLastTravelTime(Player player){
        FileManager.travelDatabase_file.set
                (player.displayName().toString().toLowerCase() +".LastTravelTime",0);
    }
}
