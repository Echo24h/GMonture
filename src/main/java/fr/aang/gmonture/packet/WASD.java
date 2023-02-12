package fr.aang.gmonture.packet;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketEvent;
import fr.aang.gmonture.Main;
import fr.aang.gmonture.VehiclePlayer;
import org.bukkit.Location;
import org.bukkit.entity.*;

public class WASD {

    private Main _main;

    public WASD(Main main, ProtocolManager manager) {

        _main = main;

        manager.addPacketListener(new PacketAdapter(main, PacketType.Play.Client.STEER_VEHICLE) {

            public void onPacketReceiving(PacketEvent event) {

                if (event.isCancelled()) return; // probably best to ignore it if blocked by other plugins
                Entity entity = event.getPlayer().getVehicle();
                if (entity == null) return;

                VehiclePlayer vehicle = _main.getVehicles().get(entity.getUniqueId());
                if (vehicle == null || !vehicle.isControl()) return;

                Location pLoc = event.getPlayer().getLocation();
                entity.setRotation(pLoc.getYaw(), 0);

                vehicle.x_speed = event.getPacket().getFloat().read(0);
                vehicle.z_speed = event.getPacket().getFloat().read(1);
                vehicle.y_speed = event.getPacket().getBooleans().read(0);

                vehicle.direction = pLoc.getDirection();

            }
        });
    }
}
