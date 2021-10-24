package randomtrip.io.github.yyyork;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.Random;

public class MyUtils {
    public static Random random;

    public MyUtils(){
        random = new Random();
    }

    public static int getTravelDistance(Player player){
        int x_end = (int)player.getLocation().getX();
        int y_end = (int)player.getLocation().getZ();
        int x_begin = MyTravelDatabaseFile.getXFrom(player);
        int y_begin = MyTravelDatabaseFile.getYFrom(player);
        int distance = (int)(Math.sqrt(Math.pow(x_end-x_begin,2)+Math.pow(y_end-y_begin,2)));   //两点间坐标公式
        return distance;
    }

    public static void setTravelFrom(Player player){
        FileManager.travelDatabase_file.set(player.displayName().toString().toLowerCase()+".isTravelStart",true);
        MyTravelDatabaseFile.setXFrom(player, (int)player.getLocation().getX());
        MyTravelDatabaseFile.setYFrom(player, (int)player.getLocation().getZ());
    }

    public static void setRandomDistanceGoal(Player player){
        Random random = new Random();
        int min = (int)MyConfigFile.getTravelRandomMinDistance();   // 去掉小数的整数距离
        int max = (int)MyConfigFile.getTravelRandomMaxDistance();
        int random_distance = min + random.nextInt(Math.abs(max-min));  //最小为min 最大为max
        MyTravelDatabaseFile.setRandomDistanceGoal(player,random_distance);
    }

    public static void setRandomDestination(Player player){
        Random random = new Random();
        int player_x = (int)player.getLocation().getX();
        int player_y = (int)player.getLocation().getZ();
        int min = (int)MyConfigFile.getTravelRandomMinDistance();   // 去掉小数的整数距离
        int max = (int)MyConfigFile.getTravelRandomMaxDistance();
        int des_x;
        int des_y;
        if(player_x-MyTravelDatabaseFile.getXFrom(player)<0){//玩家方向判断
        des_x = -(min+random.nextInt(Math.abs(max-min)))+player_x;//玩家走x轴负方向，则加一个负数
        }else{
            des_x = (min+random.nextInt(Math.abs(max-min)))+player_y;//玩家走x轴正方向，则加一个正数
        }
        if(player_y-MyTravelDatabaseFile.getYFrom(player)<0){//玩家方向判断
        des_y = -(min+random.nextInt(Math.abs(max-min)))+player_y;//玩家走z轴负方向，则加一个负数
        }else{
            des_y = (min+random.nextInt(Math.abs(max-min)))+player_y;//玩家走z轴正方向，则加一个正数
        } //最小为min 最大为max
        MyTravelDatabaseFile.setRandomDestinationX(player,des_x);
        MyTravelDatabaseFile.setRandomDestinationY(player,des_y);
    }

    public static int distanceOfDestination(Player player){
        int x_des = MyTravelDatabaseFile.getRandomDestinationX(player);
        int y_des = MyTravelDatabaseFile.getRandomDestinationY(player);
        int x_now = (int)player.getLocation().getX();
        int y_now = (int)player.getLocation().getZ();
        int distance = (int)(Math.pow(x_now-x_des,2)+Math.pow(y_now-y_des,2));   //两点间坐标公式
        return distance;
    }

    public  static boolean isNearDestination(Player player){

        if(distanceOfDestination(player)<=Math.pow(MyConfigFile.getDistanceRange(),2)){
            return true;
        }else{
            return false;
        }

    }

    public static void deletePlayer(Player player, TimingCheck tc){
        if(tc.checkRun!=null){
        tc.checkRun.cancel();
        }//玩家删除时删除玩家的计时播报任务
        if(tc.twiceRun!=null){
        tc.twiceRun.cancel();
        }//玩家删除时删除玩家的计时播报任务
        FileManager.travelDatabase_file.set(player.displayName().toString().toLowerCase()+".isTravelStart",false);
    }

    public static boolean isTravelStart(Player player){
        return FileManager.travelDatabase_file.getBoolean(player.displayName().toString().toLowerCase()+".isTravelStart");
    }
}
