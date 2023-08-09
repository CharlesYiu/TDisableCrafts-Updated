package minealex.tdisablecrafts;

import org.bukkit.plugin.Plugin;
import org.bukkit.event.Listener;

import minealex.tdisablecrafts.commands.Commands;
import minealex.tdisablecrafts.listeners.CraftsListener;
import org.bukkit.plugin.java.JavaPlugin;
import java.io.File;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.Material;

@SuppressWarnings("unused")
public final class TDisableCrafts extends JavaPlugin
{
	public void onEnable() {
	    // Registrar eventos
	    this.getServer().getPluginManager().registerEvents((Listener)new CraftsListener(this), this);
	    
	    // Generar config.yml si no existe
	    generateConfig();
	    
	    // Registrar comando
	    getCommand("tdc").setExecutor(new Commands(this));
	}

    
    public void onDisable() {
    }
    
    // Método para generar config.yml si no existe
    private void generateConfig() {
        File configFile = new File(getDataFolder(), "config.yml");
        
        if (!configFile.exists()) {
            saveDefaultConfig();
            getLogger().info("Generated config.yml");
        }
    }
}

