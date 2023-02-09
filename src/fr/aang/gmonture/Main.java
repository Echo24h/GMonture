package fr.aang.gmonture;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import fr.aang.gmonture.commands.Commands;
import fr.aang.gmonture.config.Config;
import fr.aang.gmonture.data.Data;
import fr.aang.gmonture.events.MontureGuyListener;
import fr.aang.gmonture.events.MontureListener;
import fr.aang.gmonture.events.ShopGuyListener;
import net.milkbowl.vault.economy.Economy;

public class Main extends JavaPlugin {
	
	private Economy					_eco;
	private Config					_config;
	private Data					_data;
	private List<Monture>			_montures = new ArrayList<Monture>();

	@Override
	public void onEnable() {
		
		if (!setupEconomy()) {
			System.out.println(ChatColor.RED + "[GMonture] You must have Vault");
			getServer().getPluginManager().disablePlugin(this);
			return ;
		}
		
		this._config = new Config(this, "config.yml");
		this._data = new Data(this, "data.yml");
		
		getServer().getPluginManager().registerEvents(new MontureListener(this), this);
		getServer().getPluginManager().registerEvents(new MontureGuyListener(this), this);
		getServer().getPluginManager().registerEvents(new ShopGuyListener(this), this);
		
		getCommand("monture").setExecutor(new Commands(this));
	}
	
	private boolean setupEconomy() {
		RegisteredServiceProvider<Economy> economy = getServer().getServicesManager().getRegistration(net.milkbowl.vault.economy.Economy.class);
		if (economy != null)
			_eco = economy.getProvider();
		return (economy != null);
	}
	
	public void reload() {
		_montures.clear();
		this._config = new Config(this, "config.yml");
		this._data = new Data(this, "data.yml");
	}
	
	public File getDirectory() {
		return getDataFolder();
	}
	
	public Economy getEconomy() {
		return _eco;
	}
	
	public Config getConf() {
		return _config;
	}
	
	public Data getData() {
		return _data;
	}
	
	public List<Monture> getMontures() {
		
		return _montures;
	}
	
	
}
