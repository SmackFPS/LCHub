package me.mike1665.eventhandlers;

import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.FireworkEffect.Builder;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;

import net.lightcraftmc.fusebox.util.strings.MessageType;
import net.lightcraftmc.fusebox.util.strings.StringManager;

public class FireworkLauncher implements Listener{
	
	public final Random random = new Random();
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerPlaceBlock(PlayerInteractEvent event) {
		final Player player = event.getPlayer();
		  if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
		 if ((disName(player.getItemInHand()) != null) && (disName(player.getItemInHand()).equalsIgnoreCase(ChatColor.DARK_BLUE + "Firework Launcher"))){
			 	final org.bukkit.entity.Firework fw = (org.bukkit.entity.Firework) player.getWorld().spawnEntity(player.getLocation(), EntityType.FIREWORK);
				Builder builder = FireworkEffect.builder();
				FireworkMeta m = fw.getFireworkMeta();
				builder.trail(random.nextBoolean()).flicker(random.nextBoolean());
				builder.withColor(Color.fromRGB(random.nextInt(256), random.nextInt(256), random.nextInt(256)));
				builder.with(Type.values()[random.nextInt(Type.values().length)]);
				builder.withFade(Color.fromRGB(random.nextInt(256), random.nextInt(256), random.nextInt(256)));
				m.addEffect(builder.build());
				m.setPower(random.nextInt(3)+1);
				fw.setFireworkMeta(m);
				event.setCancelled(true);
				player.getItemInHand().setAmount(player.getItemInHand().getAmount() - 1);
					if (player.getItemInHand().getAmount() == 1) {
						player.setItemInHand(null);
						player.sendMessage(StringManager.getPrefix(MessageType.GADGETS) + "Purchase more with your coins!");
					}
		 		}
			}
		}
	
	public String disName(ItemStack i)
	{
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