package fr.coco_gigpn.prodigygadget.gadget.gadgets;

import java.util.ArrayList;
import java.util.HashMap;

import me.mike1665.Main.Main;
import net.minecraft.server.v1_8_R1.EntityItem;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_8_R1.inventory.CraftItemStack;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
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
import fr.coco_gigpn.prodigygadget.util.UtilItem;
import fr.coco_gigpn.prodigygadget.util.UtilLag;
import fr.coco_gigpn.prodigygadget.util.UtilMath;
import fr.coco_gigpn.prodigygadget.util.UtilParticle;
import fr.coco_gigpn.prodigygadget.util.UtilParticle.Particle;

public class DiamondParty implements Listener {

	private HashMap<Player, Long> _active = new HashMap<Player, Long>();
	private HashMap<Player, Double> _time = new HashMap<Player, Double>();
	HashMap<Player, BukkitRunnable> _cdRunnable = new HashMap<Player, BukkitRunnable>();
	private HashMap<Player, ArrayList<Entity>> diamond = new HashMap<Player,  ArrayList<Entity>>();

	public void playDiamondParty(final Player p) {

		if(!MorphManager.hasMorph(p)) {

			diamond.put(p, new ArrayList<Entity>());

			final int id = Bukkit.getServer().getScheduler().runTaskTimer(Main.schedule, new Runnable() {
				public void run() {
					Location loc = new Location(p.getWorld(), p.getLocation().getX(), p.getLocation().getY() + 3, p.getLocation().getZ());
					for (int k =0 ; k < 6 ; k++) {
						EntityItem ei = new EntityItem(
								((CraftWorld)loc.getWorld()).getHandle(),
								loc.getX(),
								loc.getY(),
								loc.getZ(),
								CraftItemStack.asNMSCopy(new ItemStack(Material.DIAMOND))) {
							public boolean a(EntityItem entityitem) {
								return false;
							}
						};
						ei.pickupDelay = 20;
						((Item)ei.getBukkitEntity()).setVelocity(new Vector(UtilMath.randomRange(-0.5f, 0.5f), UtilMath.randomRange(0.2f, 0.5f), UtilMath.randomRange(-0.5f, 0.5f)));
						((CraftWorld)loc.getWorld()).getHandle().addEntity(ei);
						((Item) ei.getBukkitEntity()).setMetadata("diamond", new FixedMetadataValue(
								Main.schedule	, "diamond"));
						((ArrayList<Entity>)diamond.get(p)).add(ei.getBukkitEntity());
					}
					if (UtilLag.ServerisLag() && Configs.stopFireworkOnLag == true) {
						return;
					} else {
						FireworkEffect effect = FireworkEffect.builder().flicker(true).trail(true).withColor(Color.AQUA).withFade(Color.BLUE).with(Type.BURST).flicker(true).trail(true).build();
						UtilFirework.spawn(loc, effect, p);
					}



				}
			}, 10L, 10L).getTaskId();

			Bukkit.getServer().getScheduler().runTaskLater(Main.schedule, new Runnable() {
				public void run() {
					Bukkit.getServer().getScheduler().cancelTask(id);

				}
			}, 20 * 10);

			Bukkit.getServer().getScheduler().runTaskLater(Main.schedule, new Runnable() {
				public void run() {

					if (diamond.containsKey(p)) {
						for(Entity entity : diamond.get(p)) {

							Location cLoc = entity.getLocation();
							if (UtilLag.ServerisLag() && Configs.stopParticleOnLag == true) {
								return;
							} else {
								UtilParticle.sendParticleToLocation(cLoc,Particle.FIREWORKS_SPARK, 0, 0, 0, 0.2f, 2);
							}
							entity.remove();
						}
						GadgetManager.removeGadget(p);

					}
				}
			}, 20 * 13);

			if (!GadgetManager.hasGadget(p)){
				GadgetManager.addGadget(p, GadgetType.DIAMONDPARTY);
			}
		}
	}

	public static void activate(Player p){

		p.getOpenInventory().close();
		if (Configs.gadgetEnabledPosition == true) {
			p.getInventory().setItem((Configs.gadgetPosition - 1), new ItemStack(Material.AIR));
			p.getInventory().setItem((Configs.gadgetPosition - 1), UtilItem.create(Material.DIAMOND, 1, Messages.diamondpartyName.replace("&", "§")));
		} else {
			p.getInventory().addItem(UtilItem.create(Material.DIAMOND, 1, Messages.diamondpartyName.replace("&", "§")));
		}
	}



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
		if (!UtilGear.isMat(event.getPlayer().getItemInHand(), Material.DIAMOND)) {
			return;
		}
		ItemStack diamondparty = new ItemStack(Material.DIAMOND);
		UtilItem.setName(diamondparty, Messages.diamondpartyName.replace("&", "§"));
		if (!player.getItemInHand().getItemMeta().equals(diamondparty.getItemMeta())) {
			return;
		}
		if(Configs.OnebyOneGadget == true && GadgetManager.hasGadget(player) && !_time.containsKey(player)) {
			player.sendMessage(Messages.onegadgetbygadgetmessage.replace("&", "§"));
		} else {
			event.setCancelled(true);

			if(_time.containsKey(player)){
				double t = arrondi(_time.get(player), 1);
				String time = Double.toString(t);
				player.sendMessage(Messages.cooldownMessage.replace("&", "§").replace("%gadget", Messages.diamondpartyName.replace("&", "§")).replace("%time", time));
				return;
			}


			_time.put(player, Configs.diamondPartyCooldown);
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

			playDiamondParty(player);
		}

	}

	public static double arrondi(double A, int B) {
		return (double) ( (int) (A * Math.pow(10, B) + .5)) / Math.pow(10, B);
	}

}
