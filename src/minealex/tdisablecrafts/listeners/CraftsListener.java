package minealex.tdisablecrafts.listeners;

import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.Material;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.event.EventPriority;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Event;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Recipe;
import org.bukkit.plugin.Plugin;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

@SuppressWarnings("unused")
public class CraftsListener implements Listener {
    private final Plugin plugin;
    private final FileConfiguration config;

    public CraftsListener(Plugin plugin) {
        this.plugin = plugin;
        this.config = plugin.getConfig();
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onCraftItem(final CraftItemEvent event) {
        final Player player = (Player) event.getWhoClicked();

        if (player.hasPermission("tdc.craft.*")) {
            return; // Allow unlimited crafting
        }

        ItemStack result = event.getRecipe().getResult();
        Material resultMaterial = result.getType();

        if (config.getStringList("blocked-crafts").contains(resultMaterial.toString())) {
            if (!player.hasPermission("tdc.craft." + resultMaterial.toString().toLowerCase())) {
                event.setResult(Event.Result.DENY);
            }
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPrepareItemCraft(final PrepareItemCraftEvent event) {
        final Player player = (Player) event.getView().getPlayer();

        if (player.hasPermission("tdc.craft.*")) {
            return; // Allow unlimited crafting
        }

        Recipe recipe = event.getRecipe();
        if (recipe == null) {
            return;
        }
        ItemStack result = recipe.getResult();

        Material resultMaterial = result.getType();

        if (config.getStringList("blocked-crafts").contains(resultMaterial.toString())) {
            if (!player.hasPermission("tdc.craft." + resultMaterial.toString().toLowerCase())) {
                event.getInventory().setResult(new ItemStack(Material.AIR));
            }
        }
    }
}
