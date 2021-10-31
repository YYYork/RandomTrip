package randomtrip.io.github.yyyork;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class MyParticle {
    public static List<double[]> generateLine(double startX, double startZ, double endX, double endZ, int resolution) {
        double startY = 0 ;
        double XStep = (endX - startX) / (double) resolution;
        // X 方向的「单元」
        double ZStep = (endZ - startZ) / (double) resolution;
        // Z 方向的「单元」
        double YStep = 256 / (double) resolution;
        // Y 方向的「单元」
        List<double[]> result = new ArrayList<>();
        for (int i = 0; i <= resolution; i++) {
            double[] point = new double[3];
            // {x,y,z} 这样的数组
            point[0] = startX;
            point[1] = startY;
            point[2] = startZ;
            result.add(point);
            // 加入点阵
            startX += XStep;
            startY += YStep;
            startZ += ZStep;
            // 移动到下一个单元

        }
        return result;
    }

    MyParticle(Location base){

        List<double[]> AC = generateLine(2.5981, 1.5, -2.5981, 1.5, 300);
        List<double[]> CE = generateLine(-2.5981, 1.5, 0, -3, 300);
        List<double[]> EA = generateLine(0, -3, 2.5981, 1.5, 300);
        List<double[]> BD = generateLine(0, 3, -2.5981, -1.5, 300);
        List<double[]> DF = generateLine(-2.5981, -1.5, 2.5981, -1.5, 300);
        List<double[]> FB = generateLine(2.5981, -1.5, 0, 3, 300);


        World world = base.getWorld();

        for (double[] point : AC) {
            world.spawnParticle(Particle.FLAME, base.add(point[0], point[1], point[2]), 1);
            base.subtract(point[0], point[1], point[2]);
        }

        for (double[] point : CE) {
            world.spawnParticle(Particle.FLAME, base.add(point[0], point[1], point[2]), 1);
            base.subtract(point[0], point[1], point[2]);
        }

        for (double[] point : EA) {
            world.spawnParticle(Particle.FLAME, base.add(point[0], point[1], point[2]), 1);
            base.subtract(point[0], point[1], point[2]);
        }

        for (double[] point : BD) {
            world.spawnParticle(Particle.FLAME, base.add(point[0], point[1], point[2]), 1);
            base.subtract(point[0], point[1], point[2]);
        }

        for (double[] point : DF) {
            world.spawnParticle(Particle.FLAME, base.add(point[0], point[1], point[2]), 1);
            base.subtract(point[0], point[1], point[2]);
        }

        for (double[] point : FB) {
            world.spawnParticle(Particle.FLAME, base.add(point[0], point[1], point[2]), 1);
            base.subtract(point[0], point[1], point[2]);
        }

    }
}
