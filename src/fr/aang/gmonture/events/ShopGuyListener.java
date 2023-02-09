package fr.aang.gmonture.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import fr.aang.gmonture.Main;
import fr.aang.gmonture.Monture;

public class ShopGuyListener implements Listener {

	private Main	_main;
	
	public ShopGuyListener(Main main) {
		_main = main;
	}
	
	@EventHandler
	public void onClick(InventoryClickEvent event) {
		
		Player player = (Player) event.getWhoClicked();
		ItemStack current = event.getCurrentItem();
		
		if (current != null && current.getType() != null) {
			
			if (event.getView().getTitle().equals("§8Elevage")) {
				
				event.setCancelled(true);
				player.closeInventory();
				player.updateInventory();
				
				for (int i = 0; i < _main.getMontures().size(); i++) {
					
					Monture monture = _main.getMontures().get(i);
					
					if (monture.getSlot() == event.getRawSlot()) {
						
						if (player.hasPermission(monture.getPerm())) {
							
							if (monture.getPlayer(player) == null) {
								
								if (_main.getEconomy().getBalance(player) >= monture.getPrice()) {
									
									_main.getEconomy().withdrawPlayer(player,monture.getPrice());
									_main.getData().addPlayer(monture, player);
									player.sendMessage(_main.getConf().getMessages().buy.replace("{name}", monture.getName()).replace("{price}", "" + monture.getPrice()));
								}
								else {
									player.sendMessage(_main.getConf().getMessages().money);
								}
							}
						}
						else {
							player.sendMessage(_main.getConf().getMessages().permission);
						}
					}
				}
			}
		}
		
	}
}