package me.Vulpine.Nick;


import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class main extends JavaPlugin{
  
	@Override
	public void onEnable(){
		PluginDescriptionFile descFile = this.getDescription();
		
		System.out.println("[Nick] Plugin by " + descFile.getAuthors());
		System.out.println("[Nick] Version " + descFile.getVersion());
		System.out.println("[Nick]" + descFile.getName() + "wurde aktiviert");
		
		Nick nick = new Nick();
		
		this.getCommand("nick").setExecutor(nick);
		
		PluginManager pm = this.getServer().getPluginManager();
		pm.registerEvents(nick, this);
		
	}
	
	@Override
	public void onDisable(){
		PluginDescriptionFile descFile = this.getDescription();
		
		System.out.println("[Nick]" + descFile.getName() + "wurde deaktiviert");
		
	}
	
}
