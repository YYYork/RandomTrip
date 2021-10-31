package randomtrip.io.github.yyyork;

public class MyConfigFile {

    public static double getTravelRandomMinDistance(){
        return FileManager.config_file.getDouble("travel-random-min-distance");
    }

    public static double getTravelRandomMaxDistance(){
        return FileManager.config_file.getDouble("travel-random-max-distance");
    }

    public static double getDistanceRange(){
        return FileManager.config_file.getDouble("destination-range");
    }

    public static long getCollingTime(){ return FileManager.config_file.getLong("cooling-Time"); }

    public static long getVipCollingTime(){ return FileManager.config_file.getLong("vip-cooling-Time"); }
}
