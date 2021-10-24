package randomtrip.io.github.yyyork;

import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.entity.Player;

public class SoundsHelper {
    public static void playBeginSounds(Player player){
        player.playSound(
                player.getLocation(), // Location 对象确定位置
                Sound.ENTITY_ENDER_DRAGON_AMBIENT, // 音效
                SoundCategory.HOSTILE, // 可选，音效分类
                1, // 音量，参考下面的说明
                1 // 播放速度
        );
    }

    public static void playEndSounds(Player player){
        player.playSound(
                player.getLocation(), // Location 对象确定位置
                Sound.ENTITY_GUARDIAN_DEATH_LAND, // 音效
                SoundCategory.HOSTILE, // 可选，音效分类
                1, // 音量，参考下面的说明
                1 // 播放速度
        );
    }

    public static void playSuccessSounds(Player player){
        player.playSound(
                player.getLocation(), // Location 对象确定位置
                Sound.UI_TOAST_CHALLENGE_COMPLETE, // 音效
                SoundCategory.HOSTILE, // 可选，音效分类
                1, // 音量，参考下面的说明
                1 // 播放速度
        );
    }

    public static void playTellDistanceSounds(Player player){
        player.playSound(
                player.getLocation(), // Location 对象确定位置
                Sound.ENTITY_PLAYER_LEVELUP, // 音效
                SoundCategory.HOSTILE, // 可选，音效分类
                1, // 音量，参考下面的说明
                1 // 播放速度
        );
    }
}
