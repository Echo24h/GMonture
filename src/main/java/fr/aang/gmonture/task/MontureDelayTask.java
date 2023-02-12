package fr.aang.gmonture.task;

import java.util.Map;
import java.util.UUID;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class MontureDelayTask extends BukkitRunnable {

    private int 						_timer;
    private Player 						_player;
    private Map<UUID, MontureDelayTask>	_map;

    public MontureDelayTask(Map<UUID, MontureDelayTask> map, int timer, Player player) {
        _timer = timer;
        _player = player;
        _map = map;
    }

    @Override
    public void run() {
        if (_timer <= 0) {
            _map.remove(_player.getUniqueId());
            cancel();
        }

        _timer--;
    }

    public int getTime() {
        return _timer;
    }
}