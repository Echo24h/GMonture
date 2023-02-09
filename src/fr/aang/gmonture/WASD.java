package fr.aang.gmonture;

public class WASD {
	
	protocolManager.addPacketListener(new PacketAdapter(this, ListenerPriority.HIGHEST, PacketType.Play.Client.STEER_VEHICLE) {
		
        @Override
        public void onPacketReceiving(PacketEvent event) {
            PacketContainer packet = event.getPacket();
            Player player = event.getPlayer();

            Entity b = player.getVehicle();
            if (b instanceof Player) {
                return;
            }

            if(packet.getFloat().read(1)>0){
                Bukkit.broadcastMessage("W");
            }
            if(packet.getFloat().read(1)<0){
                Bukkit.broadcastMessage("S");
            }
            if(packet.getFloat().read(0)>0){
                Bukkit.broadcastMessage("A");
            }
            if(packet.getFloat().read(0)<0){
                Bukkit.broadcastMessage("D");
            }
            if(packet.getBooleans().read(0)){
                Bukkit.broadcastMessage("Jump");
            }
        }
    });
}
