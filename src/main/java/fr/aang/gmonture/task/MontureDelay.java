package fr.aang.gmonture.task;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.entity.Player;

import fr.aang.gmonture.Main;

public class MontureDelay {

    private Map<UUID, MontureDelayTask>	_map = new HashMap<UUID, MontureDelayTask>();
    private Main						_main;

    public MontureDelay(Main main) {
        _main = main;
    }

    public void add(Player player, int delay) {

        if (_map.containsKey(player.getUniqueId()) == false) {

            MontureDelayTask task = new MontureDelayTask(_map, delay, player);
            task.runTaskTimer(_main, 0, 20);
            _map.put(player.getUniqueId(), task);
        }
    }

    public MontureDelayTask getTask(Player player) {
        if (_map.get(player.getUniqueId()) != null)
            return _map.get(player.getUniqueId());
        return null;
    }
}
