package fr.aang.gmonture.events;

import fr.aang.gmonture.Monture;
import fr.aang.gmonture.VehiclePlayer;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

import fr.aang.gmonture.Main;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.vehicle.VehicleExitEvent;
import org.spigotmc.event.entity.EntityDismountEvent;

public class MontureListener implements Listener {

    private Main	_main;

    public MontureListener(Main main) {
        _main = main;
    }

    @EventHandler
    public void onVehicleFallDamage(EntityDamageEvent event) {

        if (event.getCause() == EntityDamageEvent.DamageCause.FIRE_TICK) {

            VehiclePlayer vehicle = _main.getVehicles().get(event.getEntity().getUniqueId());

            if (vehicle != null) {
                event.setCancelled(true);
            }
        }
    }

    //Bypass WorldGuard
    @EventHandler (priority = EventPriority.MONITOR)
    public void onVehicleSpawn(EntitySpawnEvent event) {
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (event.getLocation().equals(player.getLocation())) {
                event.setCancelled(false);
            }
        }
    }

    @EventHandler (priority = EventPriority.MONITOR)
    public void onVehicleExit(EntityDismountEvent event) {

        VehiclePlayer vehicle = _main.getVehicles().get(event.getDismounted().getUniqueId());

        if (vehicle == null) return;

        if (event.getEntity() instanceof Player) {
            event.setCancelled(false);
            event.getEntity().getLocation().getWorld().spawnParticle(Particle.HEART, event.getEntity().getLocation().add(0, 0.5, 0), 1);
            _main.getVehicles().remove(vehicle.entity.getUniqueId());
            vehicle.entity.remove();
        }
    }

    @EventHandler
    public void onHorseExit(VehicleExitEvent event) {

        VehiclePlayer vehicle = _main.getVehicles().get(event.getVehicle().getUniqueId());

        if (vehicle == null) return;

        if (event.getVehicle().getPassengers().size() != 1) return ;

        if (event.getVehicle().getPassengers().get(0) instanceof Player) {

            Player player = (Player)event.getVehicle().getPassengers().get(0);
            event.setCancelled(false);
            player.getWorld().spawnParticle(Particle.HEART, player.getLocation().add(0, 0.5, 0), 1);
            _main.getVehicles().remove(vehicle.entity.getUniqueId());
            vehicle.entity.remove();

        }

        if (event.getVehicle().getPassengers().get(0) instanceof Player) {

        }
    }

    @EventHandler
    public void onVehicleDead(EntityDeathEvent event) {

        VehiclePlayer vehicle = _main.getVehicles().get(event.getEntity().getUniqueId());

        if (vehicle == null) return;

        Monture monture = getMontureByType(vehicle.entity.getType());
        if (monture == null) return ;

        monture.addDelay(vehicle.player, _main.getConf().getDeadDelay());
        _main.getVehicles().remove(vehicle.entity.getUniqueId());
        vehicle.entity.remove();
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {

        if (event.getWhoClicked() instanceof Player) {

            Entity entity = event.getWhoClicked().getVehicle();

            if (entity != null) {

                if (_main.getVehicles().get(entity.getUniqueId()) != null
                && (entity.getType() == EntityType.HORSE
                || entity.getType() == EntityType.ZOMBIE_HORSE
                || entity.getType() == EntityType.SKELETON_HORSE)) {
                    if (event.getCurrentItem() != null
                            && (event.getCurrentItem().getType() == Material.SADDLE
                    || event.getCurrentItem().getType() == Material.LEATHER_HORSE_ARMOR
                    || event.getCurrentItem().getType() == Material.IRON_HORSE_ARMOR
                    || event.getCurrentItem().getType() == Material.GOLDEN_HORSE_ARMOR
                    || event.getCurrentItem().getType() == Material.DIAMOND_HORSE_ARMOR)) {
                        event.setCancelled(true);
                        event.getWhoClicked().closeInventory();
                        ((Player) event.getWhoClicked()).updateInventory();
                    }
                }

            }
        }

    }

    private Monture getMontureByType(EntityType type) {

        for (int i = 0; i < _main.getMontures().size(); i++) {
            if (_main.getMontures().get(i).getEntityType() == type)
                return _main.getMontures().get(i);
        }
        return null;
    }

}
