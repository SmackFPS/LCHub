package me.mike1665.funstuff;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.arrayprolc.strings.MessageType;
import com.arrayprolc.strings.StringManager;

public class PvPSword implements Listener {

	public static ArrayList<Player> pvp = new ArrayList<Player>();

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerRespawn(PlayerJoinEvent event) {
		Player p = event.getPlayer();
		ItemStack sword = new ItemStack(Material.DIAMOND_SWORD, 1);
		ItemMeta pvp = sword.getItemMeta();
		pvp.setDisplayName(ChatColor.LIGHT_PURPLE + "PvP Sword "
				+ ChatColor.GRAY + "(Right-Click To PVP)");
		ArrayList<String> Lore = new ArrayList<String>();
		Lore.add("Make sure others have this enabled!");
		pvp.setLore(Lore);
		sword.setItemMeta(pvp);
		p.getInventory().setItem(3, sword);
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerPlaceBlock(PlayerInteractEvent event) {
		final Player player = event.getPlayer();
		if (event.getAction() == Action.RIGHT_CLICK_AIR
				|| event.getAction() == Action.RIGHT_CLICK_BLOCK) {
			if ((disName(player.getItemInHand()) != null)
					&& (disName(player.getItemInHand())
							.equalsIgnoreCase(ChatColor.LIGHT_PURPLE
									+ "PvP Sword " + ChatColor.GRAY
									+ "(Right-Click To PVP)"))) {
				if (!pvp.contains(player)) {
					pvp.add(player);
					player.getItemInHand().addEnchantment(Enchantment.DAMAGE_ALL, 1);
					player.sendMessage(StringManager.getPrefix(MessageType.INFO) + "PVP Sword Activated");
				} else if (pvp.contains(player)) {
					pvp.remove(player);
					player.getItemInHand().removeEnchantment(Enchantment.DAMAGE_ALL);
					player.sendMessage(StringManager.getPrefix(MessageType.INFO) + "PVP Sword Deactivated");
				}
			}
		}
	}

	@EventHandler
	public void pvp(EntityDamageByEntityEvent e) {
		if (((e.getEntity() instanceof Player))
				&& ((e.getDamager() instanceof Player))) {
			Player dr = (Player) e.getDamager();
			Player dd = (Player) e.getEntity();
			if ((!pvp.contains(dr)) || (!pvp.contains(dd))) {
				dr.sendMessage(StringManager.getPrefix(MessageType.ERROR) + "You or the player have PVP Disabled!");
				dr.playSound(dr.getLocation(), Sound.ANVIL_LAND, 1.0F, 2.0F);
				e.setCancelled(true);
			} if ((pvp.contains(dr)) || (pvp.contains(dd))) {
				dr.playSound(dr.getLocation(), Sound.ORB_PICKUP, 1.0F, 2.0F);
				e.setCancelled(false);
			}
		}
	}

	public String disName(ItemStack i) {
		if (i == null) {
			return null;
		}
		if (!i.hasItemMeta()) {
			return null;
		}
		if (!i.getItemMeta().hasDisplayName()) {
			return null;
		}
		return i.getItemMeta().getDisplayName();
	}
}
