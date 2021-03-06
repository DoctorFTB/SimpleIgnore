package ftblag.simpleignore;

import java.util.regex.Pattern;

import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.event.ConfigChangedEvent.OnConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod(modid = SimpleIgnore.MODID, name = "Simple Ignore", version = "@VERSION@", acceptableRemoteVersions = "*", clientSideOnly = true, guiFactory = "ftblag.simpleignore.GuiFactory", acceptedMinecraftVersions = "[1.12.2]")
public class SimpleIgnore {

    public static final String MODID = "simpleignore";

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        ConfigurationHandler.load(event.getSuggestedConfigurationFile());
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent e) {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onConfigChanged(OnConfigChangedEvent event) {
        if (event.getModID().equalsIgnoreCase(MODID))
            ConfigurationHandler.loadConfig();
    }

    @SubscribeEvent
    public void chat(ClientChatReceivedEvent e) {
        if (!ConfigurationHandler.filter.isEmpty())
            for (String filter : ConfigurationHandler.filter)
                if (Pattern.compile(filter).matcher(e.getMessage().getUnformattedText()).find()) {
                    e.setCanceled(true);
                    return;
                }
    }
}
