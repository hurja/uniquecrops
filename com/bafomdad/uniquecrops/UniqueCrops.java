package com.bafomdad.uniquecrops;

import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;

import com.bafomdad.uniquecrops.core.UCConfig;
import com.bafomdad.uniquecrops.core.UCTab;
import com.bafomdad.uniquecrops.gui.GuiHandler;
import com.bafomdad.uniquecrops.proxies.CommonProxy;

@Mod(modid=UniqueCrops.MOD_ID, name=UniqueCrops.MOD_NAME, version=UniqueCrops.VERSION, dependencies = "after:forge@[" + UniqueCrops.FORGE_VER + ",);")
public class UniqueCrops {

	public static final String MOD_ID = "uniquecrops";
	public static final String MOD_NAME = "Unique Crops";
	public static final String VERSION = "@VERSION@";
	public static final String FORGE_VER = "14.21.1.2443";
	
	@SidedProxy(clientSide="com.bafomdad.uniquecrops.proxies.ClientProxy", serverSide="com.bafomdad.uniquecrops.proxies.CommonProxy")
	public static CommonProxy proxy;
	
	@Mod.Instance(MOD_ID)
	public static UniqueCrops instance;
	
	public static UCTab TAB = new UCTab();
	
	public static boolean baublesLoaded = Loader.isModLoaded("baubles");
	public static boolean ieLoaded = Loader.isModLoaded("immersiveengineering");
	public static boolean ctLoaded = Loader.isModLoaded("crafttweaker");
	
	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		
		NetworkRegistry.INSTANCE.registerGuiHandler(instance, new GuiHandler());

		proxy.preInit(event);
		proxy.checkResource();
		proxy.initAllModels();
	}
	
	@Mod.EventHandler
	public void init(FMLInitializationEvent event) {
		
		proxy.registerColors();
		proxy.init(event);
		proxy.initEntityRender();
	}
	
	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		
		proxy.postInit(event);
	}
}
