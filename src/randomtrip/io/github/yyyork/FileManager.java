package randomtrip.io.github.yyyork;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class FileManager {
    public static FileConfiguration travelDatabase_file;
    public static FileConfiguration config_file;
    public static FileConfiguration enableworld_file;
    public static void loadAll(){
        JavaPlugin instance = RandomTrip.instance;
        config_file = instance.getConfig();
        File f1 = new File(instance.getDataFolder(),"traveldatabase.yml");
        travelDatabase_file = YamlConfiguration.loadConfiguration(f1);
        File f2 = new File(instance.getDataFolder(),"enableworld.yml");
        enableworld_file = YamlConfiguration.loadConfiguration(f2);
    }
}
