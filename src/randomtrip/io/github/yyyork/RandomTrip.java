package randomtrip.io.github.yyyork;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;


public class RandomTrip extends JavaPlugin {
    public static JavaPlugin instance;
    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();
        saveResource("enableworld.yml", false);
        saveResource("traveldatabase.yml", true);
        FileManager.loadAll();
        Bukkit.getPluginManager().registerEvents(new EventHandler(), this);
        Objects.requireNonNull(Bukkit.getPluginCommand("randomtrip")).setExecutor(new CommandHandler());
        this.getLogger().info("§a[RandomTrip]§e插件作者 大鸭梨 , QQ 2264117533");
        this.getLogger().info("§a[RandomTrip]§eRandomTrip v1.0 开启成功！");
    }

    @Override
    public void onDisable() {
        Bukkit.getScheduler().cancelTasks(this);
    }
}
