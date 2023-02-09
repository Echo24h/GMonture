package fr.aang.gmonture.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import fr.aang.gmonture.Main;
import fr.aang.gmonture.Monture;

public class MontureGuyListener implements Listener {

	private Main	_main;
	
	public MontureGuyListener(Main main) {
		_main = main;
	}
	
	@EventHandler
	public void onClick(InventoryClickEvent event) {
		
		Player player = (Player) event.getWhoClicked();
		ItemStack current = event.getCurrentItem();
		
		if (current != null && current.getType() != null) {
			
			if (event.getView().getTitle().equals("§8Montures")) {
				
				event.setCancelled(true);
				player.closeInventory();
				player.updateInventory();
				
				for (int i = 0; i < _main.getMontures().size(); i++) {
					
					Monture monture = _main.getMontures().get(i);
					
					if (monture.getSlot() == event.getRawSlot()) {
						
						if (player.hasPermission(monture.getPerm())) {
							
							if (monture.getPlayer(player) != null) {

								if (monture.getDelay(player) <= 0) {
									monture.ride(player);
								}
								else {
									player.sendMessage(_main.getConf().getMessages().dead);
								}
							}
							else {
								player.sendMessage("§e[⚐] §cAchetez cette monture au §eSpawn");
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
