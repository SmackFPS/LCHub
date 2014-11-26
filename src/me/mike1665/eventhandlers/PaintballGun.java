package me.mike1665.eventhandlers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.UUID;

import me.mike1665.Main.Main;
import me.mike1665.particlelib.ParticleEffect;
import me.mike1665.utils.UtilBlock;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.inventory.ItemStack;

public class PaintballGun implements Listener{
	
	private Main plugin;
	private HashSet<Projectile> _balls = new HashSet<Projectile>();
	private ArrayList<UUID> _coolDown = new ArrayList<UUID>();
	
	public PaintballGun(Main main) {
		this.plugin = main;
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void Paint(ProjectileHitEvent event) {
		if (!this._balls.remove(event.getEntity())) {
			return;
		}
			Location loc = event.getEntity().getLocation().add(event.getEntity().getVelocity());
			loc.getWorld().playEffect(loc, Effect.STEP_SOUND, Material.OBSIDIAN.getId());
		
			byte color = 2;
			double r = Math.random();
			if (r > 0.8D) color = 4;
			else if (r > 0.6D) color = 5;
			else if (r > 0.4D) color = 9;
			else if (r > 0.2D) color = 14;
		
			for (Block block : UtilBlock.getInRadius(loc, 3.0D).keySet()) {
				if (block.getType() == Material.PORTAL) {
					return;
				}
				if (block.getType() == Material.CACTUS) {
					return;
				}
			}
			for (Block block : UtilBlock.getInRadius(loc, 1.5D).keySet()) {
			
				if (UtilBlock.solid(block)) {
				
					Location bLoc = block.getLocation().add(0.0D, 1.0D, 0.0D);
				
					if (block.getType() == Material.CARPET) {
						setBlockToRestore (block, 35, color, 4L, event.getEntity().getShooter());
					}
					if (block.getType() != Material.WOOL) {
						setBlockToRestore (block, 35, color, 4L, event.getEntity().getShooter());
						
						for (int k = 0; k < 7; k++)
							ParticleEffect.FIREWORKS_SPARK.display(bLoc, 0.0F, 0.0F, 0.0F, 0.1F, 1);
				}
			}
		}
	}
	
	@EventHandler
	public void Shoot(PlayerInteractEvent event) {
		final Player p = event.getPlayer();
		if (UtilBlock.usable(event.getClickedBlock())){
			return;
		}
		if (event.getPlayer().getItemInHand() == null) {
			return;
		}
	    if (event.getPlayer().getItemInHand().getType() != Material.DIAMOND_BARDING) {
	        return;
	    }
		if (this._coolDown.contains(p.getUniqueId())) {
			return;
		}
		
		if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
			if ((disName(p.getItemInHand()) != null) && (disName(p.getItemInHand()).equalsIgnoreCase(ChatColor.LIGHT_PURPLE + "Paintball Gun"))){
				Player player = event.getPlayer();
				event.setCancelled(true);
		
				this._coolDown.add(p.getUniqueId());
				Bukkit.getScheduler().runTaskLater(this.plugin, new Runnable() {
			
					@Override
					public void run() {
						PaintballGun.this._coolDown.remove(p.getUniqueId());
				
					}
				}, 5L);
		
				Projectile proj = player.launchProjectile(Snowball.class);
				proj.setVelocity(proj.getVelocity().multiply(2));
				this._balls.add(proj);
				player.getWorld().playSound(player.getLocation(), Sound.CHICKEN_EGG_POP, 1.5F, 1.5F);
			}
		}
		
	}
	
	@SuppressWarnings("deprecation")
	public void setBlockToRestore(final Block block, int id, byte color, long timeInSecs, Entity shooter)
	  {
		if(!(shooter instanceof Player)) return;
		final Player p = (Player)shooter;
		
	    final int BeforeId = block.getTypeId();
	    final byte BeforeData = block.getData();
	    for(Player p2 : Bukkit.getOnlinePlayers()) p2.sendBlockChange(block.getLocation(), id, color);
	    Bukkit.getScheduler().runTaskLater(this.plugin, new Runnable()
	    {
	      public void run()
	      {
	      for(Player p2 : Bukkit.getOnlinePlayers()) if(p != p2) p2.sendBlockChange(block.getLocation(), BeforeId, BeforeData);
	      }
	    }
	    , timeInSecs * 5L);
	  }
	
	  @EventHandler
	  public void Teleport(PlayerTeleportEvent event) {
	    if (event.getCause() == PlayerTeleportEvent.TeleportCause.ENDER_PEARL)
	      event.setCancelled(true);
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
