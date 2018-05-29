package zdoctor.basicguiconfig.proxy;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import zdoctor.basicguiconfig.EasyConfig;
import zdoctor.basicguiconfig.EasyConfig.BooleanProperty;
import zdoctor.basicguiconfig.EasyConfig.IntegerProperty;
import zdoctor.basicguiconfig.EasyConfig.StringProperty;
import zdoctor.basicguiconfig.EasyConfigGui;

public abstract class CommonProxy {

	public EasyConfigGui config;
	public BooleanProperty prop;
	public IntegerProperty prop1;
	public StringProperty prop2;

	public void preInit(FMLPreInitializationEvent e) {
		config = new EasyConfigGui(e);
		prop = new EasyConfig.BooleanProperty(config, "Booleans", "Bool1", false);
		prop1 = new EasyConfig.IntegerProperty(config, "Ints", "Int1", 50, 0, 100);
		prop2 = new EasyConfig.StringProperty(config, "Strings", "String1", "Test");
		config.close();
	}

	public void init(FMLInitializationEvent e) {

	}

	public void postInit(FMLPostInitializationEvent e) {
	}

}