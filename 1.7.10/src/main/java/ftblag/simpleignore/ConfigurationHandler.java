package ftblag.simpleignore;

import java.io.File;
import java.util.List;

import com.google.common.collect.Lists;

import net.minecraftforge.common.config.Configuration;

public class ConfigurationHandler {

    public static Configuration config;
    public static List<String> filter;

    public static void load(final File configFile) {
        if (config == null) {
            config = new Configuration(configFile);
            loadConfig();
        }
    }

    public static void loadConfig() {
        filter = Lists
                .newArrayList(config.getStringList("filter", "general", new String[0], "Filter with word removes."));
        if (config.hasChanged())
            config.save();
    }
}
