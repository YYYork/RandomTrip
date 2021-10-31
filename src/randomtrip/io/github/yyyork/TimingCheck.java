package randomtrip.io.github.yyyork;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.hover.content.Text;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

public class TimingCheck {
    public BukkitTask checkRun;
    public BukkitTask twiceRun;
    public BukkitTask playParticles;

    private static void sendJSON(Player player){
        BaseComponent teleport = new TextComponent("您发现了一个新时空波！点我前往！（可能会导致死亡）");
        teleport.setBold(true);
        teleport.setUnderlined(true);
        teleport.setColor(ChatColor.GREEN);
        teleport.setHoverEvent(new HoverEvent(
                HoverEvent.Action.SHOW_TEXT, // 动作：显示文本
                new Text("§e点击前往§8(§c有死亡风险§8)")
        ));
        teleport.setClickEvent(new ClickEvent(
                ClickEvent.Action.RUN_COMMAND, // 动作：执行指令
                "/randomtrip:randomtrip go" // 执行传送
        ));
        player.sendMessage(teleport);
    }

    public  void startCheck(Player player){
        checkRun = new BukkitRunnable(){
            @Override
            public void run(){
                while(MyUtils.isTravelStart(player)){

                int distance = MyUtils.getTravelDistance(player);
                int goaldistance = MyTravelDatabaseFile.getRandomDistanceGoal(player);

                if(distance<goaldistance){
                    Bukkit.getScheduler().runTask(RandomTrip.instance, () -> {
                    SoundsHelper.playTellDistanceSounds(player);
                    player.sendMessage
                            ("§8[§6阿巴阿巴航海系统§8]§7您已经航行了§b"+distance+"§7米");
                    player.sendMessage
                            ("§8[§6阿巴阿巴航海系统§8]§7您还需要航行§e"+(goaldistance-distance)+"§7米");});
                }else{
                    MyUtils.setRandomDestination(player);
                    twiceCheck(player);
                    break;
                }
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }}
        }.runTaskAsynchronously(RandomTrip.instance);
    }

    private void twiceCheck(Player player){
        SoundsHelper.playTellDistanceSounds(player);
        player.sendMessage
                ("§8[§6阿巴阿巴航海系统§8]§7航行距离达到，开始寻找时空波。");

        MyUtils.setRandomDestination(player);

        player.sendMessage
                ("§8[§6阿巴阿巴航海系统§8]§7已发现一个时空波！坐标在 §ex="
                        +MyTravelDatabaseFile.getRandomDestinationX(player)
                        +" §ez="
                        +MyTravelDatabaseFile.getRandomDestinationY(player)
                        +" §7附近，快速前往吧！");
        twiceRun = new BukkitRunnable(){
            @Override
            public void run(){
                while(MyUtils.isTravelStart(player)){
                    int distance = MyUtils.getTravelDistance(player);
                if(MyUtils.isNearDestinationToPlayParticle(player)){
                    if(playParticles==null){
                        Location base = new Location(player.getWorld(),MyTravelDatabaseFile.getRandomDestinationX(player),0,MyTravelDatabaseFile.getRandomDestinationY(player));
                        playParticles(base,player);
                    }
                }
                if(MyUtils.isNearDestination(player)){
                    //在附近
                    Bukkit.getScheduler().runTask(RandomTrip.instance, () -> {
                        SoundsHelper.playSuccessSounds(player);
                        player.sendMessage
                                ("§8§l=========================================");
                        player.sendMessage
                                ("§8[§6阿巴阿巴航海系统§8]§7您到达了时空波附近！");
                        sendJSON(player);
                        player.sendMessage
                                ("§8§l=========================================");});
                    break;//在附近，结束循环
                }else{
                    Bukkit.getScheduler().runTask(RandomTrip.instance, () -> {
                    SoundsHelper.playTellDistanceSounds(player);

                    player.sendMessage
                            ("§8[§6阿巴阿巴航海系统§8]§7您已经航行了§b"+distance+"§7米");

                    player.sendMessage
                            ("§8[§6阿巴阿巴航海系统§8]§7您的时空波坐标在 §ex="
                                    +MyTravelDatabaseFile.getRandomDestinationX(player)
                                    +" §ez="
                                    +MyTravelDatabaseFile.getRandomDestinationY(player)
                                    +" §7附近，快速前往吧！");});
                }//不在附近
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();

                    }
            }}
        }.runTaskAsynchronously(RandomTrip.instance);
    }

    private void playParticles(Location base,Player player){
        playParticles = new BukkitRunnable(){
            @Override
            public void run(){
                while(MyUtils.isTravelStart(player)){
                Bukkit.getScheduler().runTask(RandomTrip.instance, () -> new MyParticle(base));
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
            } }
        }.runTaskAsynchronously(RandomTrip.instance);
    }
}
