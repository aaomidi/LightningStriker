package dev.nationcraft.org.LightningStriker;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class LightningStriker extends JavaPlugin {

    public String prefix;
    public final static Logger logger = Logger.getLogger("minecraft");

    @Override
    public void onDisable() {
        PluginDescriptionFile pdf = this.getDescription();
        logger.log(Level.INFO, "{0}, version {1} coded by {2} has been Disabled!", new Object[]{pdf.getName(), pdf.getVersion(), pdf.getAuthors()});
    }

    @Override
    public void onEnable() {
        saveDefaultConfig();
        PluginDescriptionFile pdf = this.getDescription();
        getServer().getPluginManager().registerEvents(this, this);
        logger.log(Level.INFO, "{0}, version {1} coded by {2} has been Enabled!", new Object[]{pdf.getName(), pdf.getVersion(), pdf.getAuthors()});
    }

    @EventHandler
    public void onPlayerInteractBlock(PlayerInteractEvent e) {
        if (e.getPlayer().getItemInHand().getTypeId() == Material.DIAMOND_AXE.getId()) {
            if (e.getPlayer().getItemInHand().getEnchantmentLevel(Enchantment.DAMAGE_ALL) == 2) {
                if (e.getClickedBlock().getType() != Material.AIR) {
                    if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
                        if (e.getPlayer().getLevel() >= 1) {
                            int exp = e.getPlayer().getLevel();
                            e.getPlayer().setLevel(exp--);
                            e.getPlayer().getWorld().strikeLightning(e.getClickedBlock().getLocation());
                        }
                    }
                }
            }
        }
    }
}