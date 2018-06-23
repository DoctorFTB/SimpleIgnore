package ftblag.simpleignore;

import java.util.Set;

import cpw.mods.fml.client.IModGuiFactory;
import cpw.mods.fml.client.config.GuiConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;

public class GuiFactory implements IModGuiFactory {

    @Override
    public void initialize(final Minecraft minecraftInstance) {
    }

    @Override
    public Set<IModGuiFactory.RuntimeOptionCategoryElement> runtimeGuiCategories() {
        return null;
    }

    public class Gui extends GuiConfig {
        public Gui(GuiScreen parent) {
            super(parent, new ConfigElement(ConfigurationHandler.config.getCategory("general")).getChildElements(),
                    SimpleIgnore.MODID, false, false, "Simple Ignore configuration");
        }
    }

    @Override
    public Class<? extends GuiScreen> mainConfigGuiClass() {
        return Gui.class;
    }

    @Override
    public RuntimeOptionGuiHandler getHandlerFor(RuntimeOptionCategoryElement element) {
        return null;
    }

}
