package ftblag.simpleignore;

import java.util.regex.Pattern;

import cpw.mods.fml.client.event.ConfigChangedEvent.OnConfigChangedEvent;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.common.MinecraftForge;

@Mod(modid = SimpleIgnore.MODID, name = "Simple Ignore", version = "@VERSION@", acceptableRemoteVersions = "*", guiFactory = "ftblag.simpleignore.GuiFactory", acceptedMinecraftVersions = "[1.7.10]")
public class SimpleIgnore {

    public static final String MODID = "simpleignore";

    @SideOnly(Side.CLIENT)
    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        ConfigurationHandler.load(event.getSuggestedConfigurationFile());
    }

    @SideOnly(Side.CLIENT)
    @Mod.EventHandler
    public void init(FMLInitializationEvent e) {
        MinecraftForge.EVENT_BUS.register(this);
        FMLCommonHandler.instance().bus().register(this);
    }

    @SubscribeEvent
    public void onConfigChanged(OnConfigChangedEvent event) {
        if (event.modID.equalsIgnoreCase(MODID))
            ConfigurationHandler.loadConfig();
    }

    @SubscribeEvent
    public void chat(ClientChatReceivedEvent e) {
        if (!ConfigurationHandler.filter.isEmpty())
            for (String filter : ConfigurationHandler.filter)
                if (Pattern.compile(filter).matcher(e.message.getUnformattedText()).find()) {
                    e.setCanceled(true);
                    return;
                }
    }
}
