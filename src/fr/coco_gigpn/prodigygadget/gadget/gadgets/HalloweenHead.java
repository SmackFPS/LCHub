package fr.coco_gigpn.prodigygadget.gadget.gadgets;

import java.util.HashMap;

import me.mike1665.Main.Main;
import net.minecraft.server.v1_8_R1.EntityItem;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.craftbukkit.v1_8_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_8_R1.inventory.CraftItemStack;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import fr.coco_gigpn.prodigygadget.gadget.GadgetManager;
import fr.coco_gigpn.prodigygadget.gadget.GadgetManager.GadgetType;
import fr.coco_gigpn.prodigygadget.morph.MorphManager;
import fr.coco_gigpn.prodigygadget.util.Configs;
import fr.coco_gigpn.prodigygadget.util.Messages;
import fr.coco_gigpn.prodigygadget.util.UtilBlock;
import fr.coco_gigpn.prodigygadget.util.UtilFirework;
import fr.coco_gigpn.prodigygadget.util.UtilGear;
import fr.coco_gigpn.prodigygadget.util.UtilInventory;
import fr.coco_gigpn.prodigygadget.util.UtilItem;
import fr.coco_gigpn.prodigygadget.util.UtilLag;
import fr.coco_gigpn.prodigygadget.util.UtilMath;

public class HalloweenHead implements Listener {

	private HashMap<Player, Long> _active = new HashMap<Player, Long>();
	private HashMap<Player, Double> _time = new HashMap<Player, Double>();
	HashMap<Player, BukkitRunnable> _cdRunnable = new HashMap<Player, BukkitRunnable>();
	public static HashMap<Player, Integer> halloweenhead = new HashMap<Player, Integer>();

	@SuppressWarnings("deprecation")
	public static void activateHalloweenHead(final Player p) {

		if(!MorphManager.hasMorph(p)) {

			if (!halloweenhead.containsKey(p)) {


				if (p.isValid()) {

					UtilInventory.saveArmor(p);
					p.getInventory().setHelmet(new ItemStack(Material.PUMPKIN));
					p.updateInventory();

					final int i = Bukkit.getScheduler()
							.runTaskTimer(Main.schedule, new Runnable() {

								int step = 1;

								public void run() {

									if ((step % 2) == 0) {

										p.getInventory().setChestplate(UtilInventory.getColorArmor(Material.LEATHER_CHESTPLATE, Color.ORANGE));
										p.getInventory().setLeggings(UtilInventory.getColorArmor(Material.LEATHER_LEGGINGS, Color.ORANGE));
										p.getInventory().setBoots(UtilInventory.getColorArmor(Material.LEATHER_BOOTS, Color.ORANGE));

									} else {

										p.getInventory().setChestplate(UtilInventory.getColorArmor(Material.LEATHER_CHESTPLATE, Color.WHITE));
										p.getInventory().setLeggings(UtilInventory.getColorArmor(Material.LEATHER_LEGGINGS, Color.WHITE));
										p.getInventory().setBoots(UtilInventory.getColorArmor(Material.LEATHER_BOOTS, Color.WHITE));
									}
									if (UtilLag.ServerisLag() && Configs.stopFireworkOnLag == true) {
										return;
									} else {
										FireworkEffect effect = FireworkEffect.builder().flicker(true).trail(true).withColor(Color.ORANGE).withFade(Color.PURPLE).with(Type.BURST).flicker(true).trail(true).build();
										UtilFirework.spawn(p.getLocation().add(0 , 2.2 , 0), effect, p);

									}
									step++;

								}
							}, 10L, 10L).getTaskId();

					halloweenhead.put(p, i);

					Bukkit.getServer().getScheduler()
					.runTaskLater(Main.schedule, new Runnable() {
						public void run() {
							if (halloweenhead.containsKey(p)) {
								Bukkit.getServer().getScheduler().cancelTask(halloweenhead.get(p));
								halloweenhead.remove(p);
								UtilInventory.restoreArmor(p);
								UtilInventory.forsakeArmor(p);
								Location loc = p.getLocation().add(0 , 2 , 0);
								loc.getWorld().playSound(loc, Sound.EXPLODE, 1, 1);
								for (int k =0 ; k < 20 ; k++) {
									EntityItem ei = new EntityItem(
											((CraftWorld)loc.getWorld()).getHandle(),
											loc.getX(),
											loc.getY(),
											loc.getZ(),
											CraftItemStack.asNMSCopy(new ItemStack(Material.PUMPKIN))) {
										public boolean a(EntityItem entityitem) {
											return false;
										}
									};
									ei.pickupDelay = 20;
									((Item)ei.getBukkitEntity()).setVelocity(new Vector(UtilMath.randomRange(-0.5f, 0.5f), UtilMath.randomRange(0.2f, 0.5f), UtilMath.randomRange(-0.5f, 0.5f)));
									((CraftWorld)loc.getWorld()).getHandle().addEntity(ei);
									((Item) ei.getBukkitEntity()).setMetadata("unpickable", new FixedMetadataValue(
											Main.schedule	, "unpickable"));
									UtilItem.EntityToRemove(ei.getBukkitEntity(), 6, true);

								}
							}
						}
					}, 20 * 20L);

				} else {
					stopCurrently(p);
				}

			} else {
				stopCurrently(p);
				activateHalloweenHead(p);
			}

		}

	}


	public static void stopCurrently(Player p ) {
		Bukkit.getServer().getScheduler().cancelTask(halloweenhead.get(p));
		halloweenhead.remove(p);
		UtilInventory.restoreArmor(p);
		UtilInventory.forsakeArmor(p);
	}


	@EventHandler
	public void invInteract(InventoryClickEvent e)
	{
		if ((halloweenhead.containsKey(e.getWhoClicked()))) {
			e.setCancelled(true);
			e.getWhoClicked().getOpenInventory().close();
		}
	}

	@EventHandler
	public void OnplayerInteract(PlayerInteractEvent e) {
		if ((halloweenhead.containsKey(e.getPlayer()))) {
			e.setCancelled(true);
		}
	}

	@EventHandler
	public void Onplayerquit(PlayerQuitEvent event) {
		Player player = event.getPlayer();
		if (halloweenhead.containsKey(player)) {
			stopCurrently(player);
		}
		if(UtilInventory.armor.containsKey(player)) {
			UtilInventory.restoreArmor(player);
			UtilInventory.forsakeArmor(player);
		}
	}

	@EventHandler
	public void onLeave(PlayerKickEvent e)
	{
		Player player = e.getPlayer();
		if (halloweenhead.containsKey(player)) {
			stopCurrently(player);
		}
		if(UtilInventory.armor.containsKey(player)) {
			UtilInventory.restoreArmor(player);
			UtilInventory.forsakeArmor(player);
		} 
	}

	@EventHandler
	public void PlayerDamage(EntityDamageByEntityEvent event) {

		if (!(event.getEntityType() == EntityType.PLAYER)) return;
		if (!(event.getDamager().getType() == EntityType.PLAYER)) return;
		Player newTarget = (Player) event.getEntity();

		Player damager = (Player) event.getDamager();	

		if (halloweenhead.containsKey(damager) && (!halloweenhead.containsKey(newTarget))) {

			stopCurrently(damager);
			activateHalloweenHead(newTarget);
			event.setCancelled(true);
		}



	}

	@EventHandler
	public void onPlayerInteractEntity(PlayerInteractEntityEvent event)
	{
		if ((event.getRightClicked() instanceof Player))
		{
			Player newTarget = (Player) event.getRightClicked();

			Player damager = event.getPlayer();

			if (halloweenhead.containsKey(damager) && (!halloweenhead.containsKey(newTarget))) {

				stopCurrently(damager);
				activateHalloweenHead(newTarget);
				event.setCancelled(true);
			}


		}
	}

	public static void activate(Player p){
		p.getOpenInventory().close();
		if (Configs.gadgetEnabledPosition == true) {
			p.getInventory().setItem((Configs.gadgetPosition - 1), new ItemStack(Material.AIR));
			p.getInventory().setItem((Configs.gadgetPosition - 1), UtilItem.create(Material.PUMPKIN, 1, Messages.halloweenheadName.replace("&", "§")));
		} else {
			p.getInventory().addItem(UtilItem.create(Material.PUMPKIN, 1, Messages.halloweenheadName.replace("&", "§")));
		}
	}



	@SuppressWarnings("deprecation")
	@EventHandler
	public void Activate(final PlayerInteractEvent event)
	{
		final Player player = event.getPlayer();
		if ((event.getAction() != Action.RIGHT_CLICK_AIR) && (event.getAction() != Action.RIGHT_CLICK_BLOCK)) {
			return;
		}
		if (UtilBlock.usable(event.getClickedBlock())) {
			return;
		}
		if (!UtilGear.isMat(event.getPlayer().getItemInHand(), Material.PUMPKIN)) {
			return;
		}
		ItemStack halloweenhead = new ItemStack(Material.PUMPKIN);
		UtilItem.setName(halloweenhead, Messages.halloweenheadName.replace("&", "§"));
		if (!player.getItemInHand().getItemMeta().equals(halloweenhead.getItemMeta())) {
			return;
		}

		if(Configs.OnebyOneGadget == true && GadgetManager.hasGadget(player) && !_time.containsKey(player)) {
			player.sendMessage(Messages.onegadgetbygadgetmessage.replace("&", "§"));
		} else {
			event.setCancelled(true);
			if(_time.containsKey(player)){
				double t = arrondi(_time.get(player), 1);
				String time = Double.toString(t);
				player.sendMessage(Messages.cooldownMessage.replace("&", "§").replace("%gadget", Messages.halloweenheadName.replace("&", "§")).replace("%time", time));
				return;
			}

			event.setCancelled(true);
			player.updateInventory();
			_time.put(player, Configs.halloweenheadCooldown);
			_cdRunnable.put(player, new BukkitRunnable() {
				public void run() {
					_time.put(player, _time.get(player) - 0.1D);
					if (_time.get(player) < 0.01) {
						_time.remove(player);
						_cdRunnable.remove(player);
						GadgetManager.removeGadget(player);
						cancel();
					}
				}
			});
			_cdRunnable.get(player).runTaskTimer(Main.schedule, 2, 2);

			this._active.put(player, Long.valueOf(System.currentTimeMillis()));

			activateHalloweenHead(player);

			if (!GadgetManager.hasGadget(player)){
				GadgetManager.addGadget(player, GadgetType.HALLOWEENHEAD);
			}
		}

	}

	public static double arrondi(double A, int B) {
		return (double) ( (int) (A * Math.pow(10, B) + .5)) / Math.pow(10, B);
	}

}

