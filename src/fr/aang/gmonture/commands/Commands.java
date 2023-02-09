package fr.aang.gmonture.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.aang.gmonture.Main;
import fr.aang.gmonture.guy.MontureGuy;
import fr.aang.gmonture.guy.ShopGuy;

public class Commands implements CommandExecutor {
	
private Main	_main;
	
	public Commands(Main main) {
		_main = main;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		if (sender instanceof Player) {
			
			if (args.length == 1 && args[0].equals("reload") && sender.hasPermission("gmonture.reload")) {
				_main.reload();
				sender.sendMessage("§a[GMonture] Reload success");
				System.out.println(ChatColor.GREEN + "[GMonture] Reload success");
				return true;
			}
			
			if (args.length == 1 && args[0].equals("wolf")) {
				
				//MountManager.rideWolf((Player)sender);
			}
			
		}
		else {
			
			Player player = null;
			if (args.length > 0)
				player = Bukkit.getPlayer(args[0]);
			
			if (player != null) {
				
				if (args.length == 2) {
					
					if (!_main.getConf().isDisableWorld(player.getWorld().getName())) {
						
						if (args[1].equals("shop")) {
							ShopGuy.open(_main, player);
							return true;
						}
						else if (args[1].equals("inv")) {
							MontureGuy.open(_main, player);
							return true;
						}
					}
					else {
						player.sendMessage(_main.getConf().getMessages().disable);
					}
				}
			}
		}
		return false;
	}
}
