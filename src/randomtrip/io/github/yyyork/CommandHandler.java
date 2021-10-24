package randomtrip.io.github.yyyork;

import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Random;

public class CommandHandler implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender Sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if(!(Sender instanceof Player)){
            Sender.sendMessage("§8[§6阿巴阿巴航海系统§8]§c该指令只能在游戏内使用");
        }
        if(args.length==1){
            if(args[0].equals("go")){
                Player player = (Player)Sender;

                if(!MyUtils.isTravelStart(player)){
                    player.sendMessage("§8[§6阿巴阿巴航海系统§8]§c您没有开始航行。");
                    return true;
                }//若开始了航行，则判定玩家是否到达过目标，又是否到达了目的地附近
                int distance = MyUtils.getTravelDistance(player);
                if(distance<MyTravelDatabaseFile.getRandomDistanceGoal(player)){
                    player.sendMessage("§8[§6阿巴阿巴航海系统§8]§c您还未发现空间波！");
                    return true;
                }
                if(!MyUtils.isNearDestination(player)){
                    player.sendMessage("§8[§6阿巴阿巴航海系统§8]§c您似乎不在目的地附近！");
                    return true;
                }
                List<String> worlds = MyEnableWorldFile.getEnabledWorlds();
                Random random = new Random();
                int index = random.nextInt(worlds.size());
                String world = worlds.get(index);
                String telworld[] = world.split("#");
                String worldname = telworld[0];
                int chance = new Integer(telworld[1]);
                if(random.nextInt(100)<=chance){
                    player.sendMessage("§8[§6阿巴阿巴航海系统§8]§a你发现了一个真正的时空波！正在传送！");
                    Bukkit.getScheduler().runTaskLater(RandomTrip.instance,()->player.playSound(
                            player.getLocation(), // Location 对象确定位置
                            Sound.UI_TOAST_CHALLENGE_COMPLETE, // 音效
                            SoundCategory.HOSTILE, // 可选，音效分类
                            1, // 音量，参考下面的说明
                            1 // 播放速度
                    ),30);
                    Location location = new Location(Bukkit.getWorld(worldname),34,84,-301);
                    player.teleport(location);
                    return true;
                }else{
                    player.setHealth(0);
                    player.sendMessage("§8[§6阿巴阿巴航海系统§8]§4很遗憾，这个时空波是假的。");
                    Bukkit.getScheduler().runTaskLater(RandomTrip.instance,()->player.playSound(
                            player.getLocation(), // Location 对象确定位置
                            Sound.ENTITY_ENDERMAN_DEATH, // 音效
                            SoundCategory.HOSTILE, // 可选，音效分类
                            1, // 音量，参考下面的说明
                            1 // 播放速度
                    ),30);
                    return true;
                }
            }
        }else{
            Sender.sendMessage("§8[§6阿巴阿巴航海系统§8]§c指令输入有误");
        }
        return true;
    }
}
