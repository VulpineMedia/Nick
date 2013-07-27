package me.Vulpine.Nick;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.kitteh.tag.PlayerReceiveNameTagEvent;
import org.kitteh.tag.TagAPI;

public class Nick implements CommandExecutor, Listener  {

  private Map<String, String> names = new HashMap<String, String>();
	
	@Override
	public boolean onCommand(CommandSender cs, Command cmd, String label, String[] args) {
		if (!(cs instanceof Player)) {
			cs.sendMessage("§cComputer können sich nicht umbennen");
			return true;
	
		}

		if (!(cs.hasPermission("nick.use"))) {
			   cs.sendMessage("§cDeine Message!");
			   return true;
			}
		String name = cs.getName();
		Player player = (Player) cs;
		if (args.length == 0) {
			if (names.containsKey(name)) {
				this.names.remove(name);	
				cs.sendMessage("§dDein Name wurde resettet");
				player.setDisplayName(name);
				player.setPlayerListName(name);
				TagAPI.refreshPlayer(player);
				cs.sendMessage("§dDein Name wurde resettet");
			} else {
				cs.sendMessage("§6Dein Name ist noch normal");
			}
		} else {
			String name_ = args[0];
			if (name_.length() > 16) {
				name_ = name_.substring(0, 16);		
			}	
			name_= ChatColor.translateAlternateColorCodes('&', name_);
			this.names.put(name, name_);
			player.setDisplayName(name_);
			player.setPlayerListName(name_);
			TagAPI.refreshPlayer(player);
			cs.sendMessage("§6Du heisst jetzt " + name_);
			
		}	
		return true;

	}
	
	@EventHandler
	public void onTag(PlayerReceiveNameTagEvent event) {
		String name = event.getNamedPlayer().getName();
		if (this.names.containsKey(name)) {
			event.setTag(this.names.get(name));
		}
	
	}


}
