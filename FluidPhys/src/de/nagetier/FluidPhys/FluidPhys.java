package de.nagetier.FluidPhys;

import java.util.logging.Logger;

import org.bukkit.plugin.java.JavaPlugin;

public class FluidPhys extends JavaPlugin 
{	
	
	static Logger log;
	
	public void onEnable()
	{
		log = this.getLogger();
		log.info("Trying to start Fluid Physics...");
		
		//Load plugins
		getServer().getPluginManager().registerEvents(new DoorPhys(log), this);
	}
	
	public void onDisable()
	{
		log.info("Stopping Fluid Physics....");
	}
	
}
