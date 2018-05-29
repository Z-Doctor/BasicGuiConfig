package zdoctor.basicguiconfig;

import java.lang.reflect.Field;

import net.minecraftforge.fml.client.SplashProgress;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLLoadCompleteEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import zdoctor.basicguiconfig.proxy.CommonProxy;

@Mod(modid = ModMain.MODID, version = "1.0")
public class ModMain {
	public static final String MODID = "basicguiconfig";

	@Instance
	public static ModMain mod = new ModMain();

	@SidedProxy(clientSide = "zdoctor.biomeconfig.proxy.ClientProxy", serverSide = "zdoctor.biomeconfig.proxy.ServerProxy")
	public static CommonProxy proxy;

	@EventHandler
	public void preInit(FMLPreInitializationEvent e) {
		proxy.preInit(e);
	}

	@EventHandler
	public void init(FMLInitializationEvent e) {
		proxy.init(e);
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent e) {
		proxy.postInit(e);
	}

	@EventHandler
	public void fmlFinished(FMLLoadCompleteEvent e) {
		if (FMLCommonHandler.instance().getSide() == Side.CLIENT) {
			Thread t = new Thread() {
				Field done;

				@Override
				public synchronized void start() {
					try {
						done = SplashProgress.class.getDeclaredField("done");
						done.setAccessible(true);
					} catch (NoSuchFieldException | SecurityException e) {
						e.printStackTrace();
					}
					super.start();
				}

				@Override
				public void run() {
					try {
						while (!done.getBoolean(SplashProgress.class)) {

						}
					} catch (IllegalArgumentException | IllegalAccessException e) {
						e.printStackTrace();
					}
					GuiConfigHandler.fmlPostInit();
				}
			};
			t.start();
		}
	}

}