package fr.aang.gmonture;

import java.util.ArrayList;

import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import fr.aang.gmonture.task.MontureDelay;
import fr.aang.gmonture.task.MontureDelayTask;
import fr.aang.gmonture.utils.Utils;

public class Monture {
	
	private List<UUID>	_players = new ArrayList<UUID>();
	private	String		_perm = null;
	
	private int				_slot = -1;
	private int				_price = -1;
	private String			_name = null;
	private	Material		_icon = null;
	private MontureDelay	_delay = null;
	
	private EntityType _entity_type = null;
	
	public Monture(Main main, String entity_type) {
		
		_delay = new MontureDelay(main);
		
		_entity_type = Utils.getEntityByName(entity_type);
		if (_entity_type == null)
			System.out.println(ChatColor.RED + "Entity " + entity_type + " doesn't exist");
	}
	
	// SETTERS
	
	public void addPlayer(UUID player_uuid) {
		_players.add(player_uuid);
	}
	
	public void	setPerm(String perm) {
		_perm = perm;
	}

	public void setName(String name) {
		_name = name;
	}
	
	public void	setSlot(int slot) {
		_slot = slot;
	}
	
	public void setPrice(int price) {
		_price = price;
	}
	
	public void setIcon(String material_name) {
		_icon = Utils.getMaterialByName(material_name);
		if (_icon == null)
			System.out.println(ChatColor.RED + "Material " + material_name + " doesn't exist");
	}
	
	public void addDelay(Player player, int delay) {
		_delay.add(player, delay);
	}

	// GETTERS
	
	public Player getPlayer(Player player) {
		
		UUID uuid = player.getUniqueId();
		
		for (int i = 0; i < _players.size(); i++) {
			if (_players.get(i).equals(uuid))
				return Bukkit.getPlayer(_players.get(i));
		}
		return null;
	}
	
	public String getPerm() {
		return _perm;
	}
	
	public String getName() {
		return _name;
	}
	
	public EntityType getEntityType() {
		return _entity_type;
	}
	
	public int getSlot() {
		return _slot;
	}
	
	public int getPrice() {
		return _price;
	}
	
	public Material getIcon() {
		return _icon;
	}
	
	public int getDelay(Player player) {
		
		MontureDelayTask task = _delay.getTask(player);
		
		if (task != null) {
			return task.getTime();
		}
		return 0;
	}
	
	public void ride(Player player) {
		Entity entity = player.getLocation().getWorld().spawnEntity(player.getLocation(), _entity_type);
		
		entity.addPassenger(player);
	}
}
