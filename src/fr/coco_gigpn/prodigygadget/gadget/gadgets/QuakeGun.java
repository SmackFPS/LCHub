package fr.coco_gigpn.prodigygadget.gadget.gadgets;

import java.util.HashMap;

import me.mike1665.Main.Main;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Pig;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.scheduler.BukkitRunnable;

import fr.coco_gigpn.prodigygadget.gadget.GadgetManager;
import fr.coco_gigpn.prodigygadget.gadget.GadgetManager.GadgetType;
import fr.coco_gigpn.prodigygadget.util.Configs;
import fr.coco_gigpn.prodigygadget.util.Messages;
import fr.coco_gigpn.prodigygadget.util.UtilBlock;
import fr.coco_gigpn.prodigygadget.util.UtilGear;
import fr.coco_gigpn.prodigygadget.util.UtilItem;

public class QuakeGun implements Listener {

	private HashMap<Player, Long> _active = new HashMap<Player, Long>();
	private HashMap<Player, Double> _time = new HashMap<Player, Double>();
	HashMap<Player, BukkitRunnable> _cdRunnable = new HashMap<Player, BukkitRunnable>();

	public static void playQuakeGun(final Player player) {
		final Pig p1 = (Pig) player.getWorld().spawnEntity(
				player.getLocation(), EntityType.PIG);
		final Pig p2 = (Pig) player.getPlayer().getWorld()
				.spawnEntity(player.getLocation(), EntityType.PIG);
		final Pig p3 = (Pig) player.getPlayer().getWorld()
				.spawnEntity(player.getLocation(), EntityType.PIG);
		final Pig p4 = (Pig) player.getPlayer().getWorld()
				.spawnEntity(player.getLocation(), EntityType.PIG);
		final Pig p5 = (Pig) player.getPlayer().getWorld()
				.spawnEntity(player.getLocation(), EntityType.PIG);
		final Firework fw = (Firework) player.getWorld().spawnEntity(
				player.getLocation(), EntityType.FIREWORK);
		fw.setVelocity(player.getLocation().getDirection());
		FireworkMeta meta = fw.getFireworkMeta();
		meta.addEffect(FireworkEffect.builder().withColor(Color.BLUE).build());
		fw.setFireworkMeta(meta);

		fw.setPassenger(p1);

		final Firework fw2 = (Firework) player.getWorld().spawnEntity(
				player.getLocation().add(1, 2D, 0.0D), EntityType.FIREWORK);
		fw2.setVelocity(player.getLocation().getDirection());
		FireworkMeta meta2 = fw2.getFireworkMeta();
		meta2.addEffect(FireworkEffect.builder().withColor(Color.FUCHSIA)
				.build());
		fw2.setFireworkMeta(meta2);

		fw2.setPassenger(p2);

		final Firework fw3 = (Firework) player.getWorld().spawnEntity(
				player.getLocation().add(0, 2D, 1.0D), EntityType.FIREWORK);
		fw3.setVelocity(player.getLocation().getDirection());
		FireworkMeta meta3 = fw3.getFireworkMeta();
		meta3.addEffect(FireworkEffect.builder().withColor(Color.AQUA).build());
		fw3.setFireworkMeta(meta3);

		fw3.setPassenger(p3);

		final Firework fw4 = (Firework) player.getWorld().spawnEntity(
				player.getLocation().add(0, 2D, -1.0D), EntityType.FIREWORK);
		fw4.setVelocity(player.getLocation().getDirection());
		FireworkMeta meta4 = fw4.getFireworkMeta();
		meta4.addEffect(FireworkEffect.builder().withColor(Color.PURPLE)
				.build());
		fw4.setFireworkMeta(meta4);

		fw4.setPassenger(p4);

		final Firework fw5 = (Firework) player.getWorld().spawnEntity(
				player.getLocation().add(-1, 2D, 0D), EntityType.FIREWORK);
		fw5.setVelocity(player.getLocation().getDirection());
		FireworkMeta meta5 = fw5.getFireworkMeta();
		meta5.addEffect(FireworkEffect.builder().withColor(Color.NAVY).build());
		fw5.setFireworkMeta(meta3);

		fw5.setPassenger(p5);


		Bukkit.getServer()
				.getScheduler()
				.scheduleSyncDelayedTask(Main.schedule,
						new Runnable() {
							public void run() {
								fw.eject();
								fw2.eject();
								fw3.eject();
								fw4.eject();
								fw5.eject();
								p1.remove();
								p2.remove();
								p3.remove();
								p4.remove();
								p5.remove();

							}
						}, 1 * 20);

		player.playSound(player.getLocation(), Sound.ITEM_BREAK, 1.0F, 2F);

		Bukkit.getServer()
				.getScheduler()
				.scheduleSyncDelayedTask(Main.schedule,
						new Runnable() {
							public void run() {

								GadgetManager.removeGadget(player);
							}
						}, 3 * 20);
		if (!GadgetManager.hasGadget(player)){
	    	GadgetManager.addGadget(player, GadgetType.QUAKEGUN);
	    	}
	}

	public static void activate(Player p) {
		p.getOpenInventory().close();
		if (Configs.gadgetEnabledPosition == true) {
		p.getInventory().setItem((Configs.gadgetPosition - 1), new ItemStack(Material.AIR));
		p.getInventory().setItem((Configs.gadgetPosition - 1), UtilItem.create(Material.DIAMOND_HOE, 1, Messages.quakegunName.replace("&", "§")));
		} else {
			p.getInventory().addItem(UtilItem.create(Material.DIAMOND_HOE, 1, Messages.quakegunName.replace("&", "§")));
		}
	}

	@EventHandler
	public void Activate(final PlayerInteractEvent event) {
		final Player player = event.getPlayer();
		if ((event.getAction() != Action.RIGHT_CLICK_AIR)
				&& (event.getAction() != Action.RIGHT_CLICK_BLOCK)) {
			return;
		}
		if (UtilBlock.usable(event.getClickedBlock())) {
			return;
		}
		if (!UtilGear.isMat(event.getPlayer().getItemInHand(),
				Material.DIAMOND_HOE)) {
			return;
		}
		ItemStack quakegun = new ItemStack(Material.DIAMOND_HOE);
		UtilItem.setName(quakegun, Messages.quakegunName.replace("&", "§"));
		if (!player.getItemInHand().getItemMeta().equals(quakegun.getItemMeta())) {
			return;
		}
		 if(Configs.OnebyOneGadget == true && GadgetManager.hasGadget(player) && !_time.containsKey(player)) {
		    	player.sendMessage(Messages.onegadgetbygadgetmessage.replace("&", "§"));
		    } else {
		    	
		    	event.setCancelled(true);
		
		if (_time.containsKey(player)) {
			double t = arrondi(_time.get(player), 1);
	    	String time = Double.toString(t);
	    	player.sendMessage(Messages.cooldownMessage.replace("&", "§").replace("%gadget", Messages.quakegunName.replace("&", "§")).replace("%time", time));
			return;
		}

		_time.put(player, Configs.quakegunCooldown);
		_cdRunnable.put(player, new BukkitRunnable() {
			public void run() {
				_time.put(player, _time.get(player) - 0.1D);
				if (_time.get(player) < 0.01) {
					_time.remove(player);
					_cdRunnable.remove(player);
					cancel();
				}
			}
		});
		_cdRunnable.get(player).runTaskTimer(Main.schedule, 2, 2);

		this._active.put(player, Long.valueOf(System.currentTimeMillis()));

		playQuakeGun(player);
		    }

	}

	public static double arrondi(double A, int B) {
		return (double) ((int) (A * Math.pow(10, B) + .5)) / Math.pow(10, B);
	}

}
