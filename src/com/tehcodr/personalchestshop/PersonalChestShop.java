package com.tehcodr.personalchestshop;

import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.config.Configuration;

import com.nijikokun.register.listeners.server;
import com.nijikokun.register.payment.Methods;

import java.io.File;
import java.util.logging.Logger;
import org.bukkit.event.Event.Priority;
import org.bukkit.event.Event.Type;

public class PersonalChestShop extends JavaPlugin { /* A lot of the code here is recycled from Register, to ensure that the economy will work, including listeners, such as Server. */
	Logger log = Logger.getLogger("Minecraft");
	public Configuration config;
	public String preferred;
	public PluginDescriptionFile info;

	private String getPreferred() {
		return config.getString("economy.preferred");
	}

	private boolean hasPreferred() {
		return Methods.setPreferred(getPreferred());
	}

	@Override
	public void onDisable() {
		Methods.reset();
	}

    @Override
    public void onLoad() {
    	config = new Configuration(new File("bukkit.yml"));
    	info = this.getDescription();
    	config.load();

    	if (!hasPreferred()) {
    		log.info("[" + info.getName() + "] Preferred method [" + getPreferred() + "] not found, using first found.");

    		Methods.setVersion(info.getVersion());
    		Methods.setMethod(this.getServer().getPluginManager());
    	}

    	if (Methods.getMethod() != null)
    		log.info("[" + info.getName() + "] Payment method found (" + Methods.getMethod().getName() + " version: " + Methods.getMethod().getVersion() + ")");

    	log.info("[" + info.getName() + "] version " + info.getVersion()+ " is enabled. Uses Register for economy.");
    }
    
    @Override
    public void onEnable() {
    	this.getServer().getPluginManager().registerEvent(Type.PLUGIN_ENABLE, new server(this), Priority.Low, this);
    	this.getServer().getPluginManager().registerEvent(Type.PLUGIN_DISABLE, new server(this), Priority.Low, this);
    }
}
