package com.lightcraftmc.event.handlers;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

import me.mike1665.coinapi.LcTokensAPI;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.util.Vector;

import com.lightcraftmc.hub.main.Main;

import net.lightcraftmc.fusebox.util.strings.MessageType;
import net.lightcraftmc.fusebox.util.strings.StringManager;

public class PixlBomb implements Listener {

	public Main plugin;
	PixlBomb thisClass = this;
	Logger log = Logger.getLogger("Minecraft");
	List<UUID> firedEmys = new ArrayList<UUID>();
	
	public PixlBomb() {
		this.plugin = Main.getInstance();
	}

		@EventHandler(priority=EventPriority.HIGH)
		public void onPlayerUse(PlayerInteractEvent event) {
			final Player p = event.getPlayer();       
				if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
					if ((disName(p.getItemInHand()) != null) && (disName(p.getItemInHand()).equalsIgnoreCase(ChatColor.LIGHT_PURPLE + "Token Bomb"))){
						launchBomb(p);
						p.getItemInHand().setAmount(p.getItemInHand().getAmount() - 1);
					      if (p.getItemInHand().getAmount() == 1) {
					    	  p.setItemInHand(null);
					      }
					}
				}
			}

	public void launchBomb(Player p) {
	    Bukkit.broadcastMessage(StringManager.getPrefix(MessageType.TREASURE) + p.getName() + " §a§ljust launched a GemBomb!");

	    ItemStack baseItem = new ItemStack(Material.AIR);
	    final ItemStack firedItem = new ItemStack(Material.AIR);
	    baseItem.setType(Material.DIAMOND_BLOCK);
	    firedItem.setType(Material.NETHER_STAR);
	    final Item base = p.getWorld().dropItem(p.getLocation(), baseItem);
	    base.setPickupDelay(999999999);

	    Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable()
	    {
	      int times = 0;
	      boolean oposite = false;
	      boolean running = true;

	      public void run()
	      {
	        if (this.running) {
	          if (this.times < 10) {
	            base.getWorld().playSound(base.getLocation(), Sound.FIZZ, 1.0F, 1.0F);

	            this.times += 1;
	          } else if (this.times >= 10) {
	            if (!this.oposite)
	            {
	              this.oposite = true;

	              Item emy1 = base.getWorld().dropItem(base.getLocation().add(3.0D, 0.0D, 0.0D), firedItem);
	              Item emy2 = base.getWorld().dropItem(base.getLocation().add(-3.0D, 0.0D, 0.0D), firedItem);
	              Item emy3 = base.getWorld().dropItem(base.getLocation().add(0.0D, 0.0D, 3.0D), firedItem);
	              Item emy4 = base.getWorld().dropItem(base.getLocation().add(0.0D, 0.0D, -3.0D), firedItem);

	              emy1.setVelocity(new Vector(0.4D, 1.0D, 0.0D));
	              emy2.setVelocity(new Vector(-0.4D, 1.0D, 0.0D));
	              emy3.setVelocity(new Vector(0.0D, 1.0D, 0.4D));
	              emy4.setVelocity(new Vector(0.0D, 1.0D, -0.4D));

	              PixlBomb.this.firedEmys.add(emy1.getUniqueId());
	              PixlBomb.this.firedEmys.add(emy2.getUniqueId());
	              PixlBomb.this.firedEmys.add(emy3.getUniqueId());
	              PixlBomb.this.firedEmys.add(emy4.getUniqueId());

	              Firework fw = (Firework)base.getWorld().spawn(base.getLocation().subtract(0.0D, 2.0D, 0.0D), Firework.class);
	              FireworkEffect effect = FireworkEffect.builder().trail(true).flicker(false).withColor(Color.AQUA, Color.PURPLE).with(FireworkEffect.Type.BURST).build();
	              FireworkMeta fwm = fw.getFireworkMeta();

	              fwm.clearEffects();
	              fwm.addEffect(effect);
	              fwm.setPower(0);

	              fw.setFireworkMeta(fwm);
	              this.times += 1;
	            }
	            else if (this.oposite)
	            {
	              this.oposite = false;

	              Item emy1 = base.getWorld().dropItem(base.getLocation().add(2.0D, 0.0D, 2.0D), firedItem);
	              Item emy2 = base.getWorld().dropItem(base.getLocation().add(-2.0D, 0.0D, -2.0D), firedItem);
	              Item emy3 = base.getWorld().dropItem(base.getLocation().add(-2.0D, 0.0D, 2.0D), firedItem);
	              Item emy4 = base.getWorld().dropItem(base.getLocation().add(2.0D, 0.0D, -2.0D), firedItem);

	              emy1.setVelocity(new Vector(0.4D, 1.0D, 0.4D));
	              emy2.setVelocity(new Vector(-0.4D, 1.0D, -0.4D));
	              emy3.setVelocity(new Vector(-0.4D, 1.0D, 0.4D));
	              emy4.setVelocity(new Vector(0.4D, 1.0D, -0.4D));

	              PixlBomb.this.firedEmys.add(emy1.getUniqueId());
	              PixlBomb.this.firedEmys.add(emy2.getUniqueId());
	              PixlBomb.this.firedEmys.add(emy3.getUniqueId());
	              PixlBomb.this.firedEmys.add(emy4.getUniqueId());

	              Firework fw = (Firework)base.getWorld().spawn(base.getLocation().subtract(0.0D, 2.0D, 0.0D), Firework.class);
	              FireworkEffect effect = FireworkEffect.builder().trail(true).flicker(false).withColor(Color.AQUA, Color.PURPLE).with(FireworkEffect.Type.BURST).build();
	              FireworkMeta fwm = fw.getFireworkMeta();

	              fwm.clearEffects();
	              fwm.addEffect(effect);
	              fwm.setPower(0);

	              fw.setFireworkMeta(fwm);
	              this.times += 1;
	            }
	          }
	        }
	        else {
	          base.teleport(base.getLocation().subtract(0.0D, 300.0D, 0.0D));
	        }

	        Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable()
	        {
	          public void run() {
	            running = false;
	          }
	        }
	        , 600);
	      }
	    }
	    , 6L, 6L);
	  }

	@EventHandler
	public void onGemPickup(PlayerPickupItemEvent event) {
		Player p = event.getPlayer();

		if (this.firedEmys.contains(event.getItem().getUniqueId())) {
			event.setCancelled(true);
			LcTokensAPI.givePoints(p, 1);
			p.playSound(p.getLocation(), Sound.ORB_PICKUP, 2.0F, 2.0F);
			event.getItem().teleport(
					event.getItem().getLocation().subtract(0.0D, 300.0D, 0.0D));
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