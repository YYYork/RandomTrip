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

}
