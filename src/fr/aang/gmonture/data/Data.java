package fr.aang.gmonture.data;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import fr.aang.gmonture.Main;
import fr.aang.gmonture.Monture;
import fr.aang.gmonture.utils.Utils;

public class Data {

	private Main				_main;
	private File				_file;
	private YamlConfiguration	_yaml;
	
	public Data(Main main, String file_name) {
		_main = main;
		_yaml = loadConfig(file_name);
		fillData();
	}
	
	private YamlConfiguration loadConfig(String file_name) {
		
		if(!_main.getDirectory().exists()) {
			_main.getDirectory().mkdir();
		}
		
		_file = new File(_main.getDataFolder(), file_name);
		
		if (!_file.exists()) {
			_main.saveResource(file_name, false);
		}
		
		return YamlConfiguration.loadConfiguration(_file);
	}
	
	private void save() {
        try {
        	_yaml.save(_file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	public void fillData() {
		
		for (int i = 0; i < _main.getMontures().size(); i++) {
			
			Monture monture = _main.getMontures().get(i);
			
			List<String> players = _yaml.getStringList(Utils.getNameByMaterial(monture.getIcon()));
			
			for (int o = 0; o < players.size(); o++)
				monture.addPlayer(UUID.fromString(players.get(o)));
		}
		
	}
	
	public void addPlayer(Monture monture, Player player) {
		
		monture.addPlayer(player.getUniqueId());
		
		String monture_id = Utils.getNameByMaterial(monture.getIcon());
		if (monture_id == null)
			System.out.println(ChatColor.RED + "Data: impossible d'enregistrer le joueur, le materiel n'existe pas");
		else {
			
			List<String> list = null;
			if (_yaml.isSet(monture_id)) {
				//_yaml.getStringList(monture_id).add((player.getUniqueId().toString()));
				list = _yaml.getStringList(monture_id);
			}
			else {
				list = new ArrayList<>();
			}
			list.add(player.getUniqueId().toString());
			_yaml.set(monture_id, list);
			save();
		}
		System.out.println("[Gmonture] §e" + player.getName() + "§f à acheté §e" + monture.getName());
	}
	
	
}
