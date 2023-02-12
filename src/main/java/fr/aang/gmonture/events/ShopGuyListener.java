package fr.aang.gmonture.events;

import org.bukkit.Bukkit;
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
                                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "cmd " + player.getName() + " buy " + monture.getPrice() + " send monture buy " + player.getName() + " " + monture.getName());
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