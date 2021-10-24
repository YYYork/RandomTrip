package randomtrip.io.github.yyyork;

import java.util.List;

public class MyEnableWorldFile {
    public static List<String> getEnabledWorlds(){
        return FileManager.enableworld_file.getStringList("Enabled-worlds");
    }
}
