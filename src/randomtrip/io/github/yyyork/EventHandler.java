package randomtrip.io.github.yyyork;


import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Boat;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.event.vehicle.VehicleEnterEvent;
import org.bukkit.event.vehicle.VehicleExitEvent;

public class EventHandler implements Listener {
    public TimingCheck tc;

    @org.bukkit.event.EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();
        MyTravelDatabaseFile.setDefaultLastTravelTime(player); //玩家加入游戏的时候，设置默认的最新时间
    }

    @org.bukkit.event.EventHandler
    public void onPlayerEnterBoat(VehicleEnterEvent event){
        Player player;
        Entity entity = event.getEntered();
        if(entity instanceof Player){
            player = (Player)entity;
        }else{
            return; //不是玩家就忽略
        }
        if(!(player.hasPermission("randomtrip.go")||player.hasPermission("randomtrip.vipgo"))){
            SoundsHelper.playBeginSounds(player);
            player.sendMessage("§8[§6阿巴阿巴航海系统§8]§4您无法开始航海！因为您没有资格！");
            return;
        }
        if(player.getWorld().getName().equals("acidisland_world")&&event.getVehicle() instanceof Boat&&event.getVehicle().getLocation().getBlock().getType().equals(Material.WATER)&&!MyUtils.isTravelStart(player)){
            if(MyUtils.isInCoolingTime(player)){
                SoundsHelper.playBeginSounds(player);
                if(player.hasPermission("randomtrip.vipgo")){
                    player.sendMessage("§8[§6阿巴阿巴航海系统§8]§4尊贵的会员，您的航海正处于冷却时间之中,还需§e"+MyUtils.getRemainingCoolingTime(player)+"§4秒可以再次开始航海！");
                }else {
                    player.sendMessage("§8[§6阿巴阿巴航海系统§8]§4您的航海正处于冷却时间之中,还需§e"+MyUtils.getRemainingCoolingTime(player)+"§4秒可以再次开始航海！");
                }
                return;
            }
            SoundsHelper.playBeginSounds(player);
            player.sendMessage("§8[§6阿巴阿巴航海系统§8]§7检测到您刚刚在水上上船了,您要开始航海了吗？");
            MyUtils.setTravelFrom(player);
            MyUtils.setRandomDistanceGoal(player);
            tc = new TimingCheck();
            tc.startCheck(player);
        }
    }

    @org.bukkit.event.EventHandler
    public void onPlayerLeaveBoat(VehicleExitEvent event){
        Player player;
        Entity entity = event.getExited();
        if(entity instanceof Player){
            player = (Player)entity;
        }else{
            return; //不是玩家就忽略
        }
        if(player.getWorld().getName().equals("acidisland_world")&&event.getVehicle() instanceof Boat&&MyUtils.isTravelStart(player)){
            player.sendMessage("§8[§6阿巴阿巴航海系统§8]§c检测到您刚刚下船了，您要结束航海了吗？");
            SoundsHelper.playEndSounds(player);
            MyUtils.deletePlayer(player,tc);
        }
    }

    @org.bukkit.event.EventHandler
    public void onPlayerTeleport(PlayerTeleportEvent event){
        Player player = event.getPlayer();
        if(MyUtils.isTravelStart(player)){
            player.sendMessage("§8[§6阿巴阿巴航海系统§8]§c检测到您刚刚传送了，您要结束航海了吗？");
            SoundsHelper.playEndSounds(player);
            MyUtils.deletePlayer(player,tc);
        }
    }

    @org.bukkit.event.EventHandler
    public void onPlayerDie(PlayerDeathEvent event){
        Player player = event.getEntity();
        if(MyUtils.isTravelStart(player)){
            player.sendMessage("§8[§6阿巴阿巴航海系统§8]§c您死亡了，航行结束。");
            Bukkit.getScheduler().runTaskLater(RandomTrip.instance,()->SoundsHelper.playEndSounds(player),30);
            MyUtils.deletePlayer(player,tc);
        }
    }

    @org.bukkit.event.EventHandler
    public void onPlayerLeaveGame(PlayerQuitEvent event){
        Player player = event.getPlayer();
        if(MyUtils.isTravelStart(player)){
            SoundsHelper.playEndSounds(player);
            MyUtils.deletePlayer(player,tc);
        }
    }
}
