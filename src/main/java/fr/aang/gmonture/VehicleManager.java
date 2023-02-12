package fr.aang.gmonture;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class VehicleManager {

    private Main _main;

    private Map<UUID, VehiclePlayer> _vehicles = new HashMap<UUID, VehiclePlayer>();

    public VehicleManager(Main main) {
        _main = main;
    }

    public void add(Monture monture, Player player, Entity entity) {

        VehiclePlayer vehicle = new VehiclePlayer();

        vehicle.setControl(monture.isControl());
        vehicle.player = player;
        vehicle.entity = entity;
        vehicle.fly = monture.isFly();
        if (vehicle.fly)
            vehicle.entity.setGravity(false);
        vehicle.setSpeed(monture.getSpeed());
        vehicle.setJump(monture.getJump());
        UUID uuid = vehicle.entity.getUniqueId();
        _vehicles.put(uuid, vehicle);
        if (vehicle.isControl())
            vehicle.move(_main);
    }

    public VehiclePlayer get(UUID uuid) {
        return _vehicles.get(uuid);
    }

    public void remove(UUID uuid) {
        VehiclePlayer vehicle = _vehicles.get(uuid);
        if (vehicle != null) {
            vehicle.stopTask();
            _vehicles.remove(uuid);
        }
    }


}
