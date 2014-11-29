package fr.coco_gigpn.prodigygadget.gadget.gadgets;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.UUID;

import me.mike1665.Main.Main;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.EnderPearl;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.inventory.ItemStack;

import fr.coco_gigpn.prodigygadget.util.Configs;
import fr.coco_gigpn.prodigygadget.util.Messages;
import fr.coco_gigpn.prodigygadget.util.UtilBlock;
import fr.coco_gigpn.prodigygadget.util.UtilItem;

public class PaintballGun implements Listener {
	
	private HashSet<Projectile> _balls = new HashSet<Projectile>();
	private ArrayList<UUID> _coolDown = new ArrayList<UUID>();
	
	public static void activate(Player p){
		p.getOpenInventory().close();
		if (Configs.gadgetEnabledPosition == true) {
		p.getInventory().setItem((Configs.gadgetPosition - 1), new ItemStack(Material.AIR));
		p.getInventory().setItem((Configs.gadgetPosition - 1), UtilItem.create(Material.GOLD_BARDING, 1, Messages.paintballgunName.replace("&", "§")));
		} else {
			p.getInventory().addItem(UtilItem.create(Material.GOLD_BARDING, 1, Messages.paintballgunName.replace("&", "§")));
		}
	}
	

	@EventHandler
	  public void onEntityDamageByEntityEvent(EntityDamageByEntityEvent event)
	  {
	    if ((event.getEntityType() == EntityType.ITEM_FRAME) || (event.getEntityType() == EntityType.PAINTING))
	    {
	    
	      if (event.getDamager() instanceof Projectile)
	      {
	    	  if (event.getDamager() instanceof EnderPearl) {
	
			      event.setCancelled(true);
			     
			      return;
	    	  }}}
	    
	  }
	
	 @EventHandler
	  public void onPlayerInteractEntity(PlayerInteractEntityEvent event)
	  {
	    if ((event.getRightClicked() instanceof Horse))
	    {
			
			Player damager = event.getPlayer();
			
			if (damager.getItemInHand().equals(UtilItem.create(Material.GOLD_BARDING, 1, Messages.paintballgunName.replace("&", "§")))) {
				event.setCancelled(true);
			}

			
	    }
	  }
	  
	    
	    

	@EventHandler
	  public void Paint(ProjectileHitEvent event)
	  {
	    if (!_balls.remove(event.getEntity())) {
	      return;
	    }

	    Location loc = event.getEntity().getLocation().add(event.getEntity().getVelocity());
	    loc.getWorld().playEffect(loc, org.bukkit.Effect.STEP_SOUND, 49);
	    
	    byte color = 2;
	    double r = Math.random();
	    if (r > 0.8D) { color = 4;
	    } else if (r > 0.6D) { color = 5;
	    } else if (r > 0.4D) { color = 9;
	    } else if (r > 0.2D) { color = 14;
	    }
	    for (Block block : UtilBlock.getInRadius(loc, 3.0D).keySet())
	    {
	      if (block.getType() == Material.PORTAL) {
	        return;
	      }
	      if (block.getType() == Material.CACTUS) {
	        return;
	      }
	      if (block.getType() == Material.IRON_PLATE) {
		        return;
		  }
	      if (block.getType() == Material.LONG_GRASS) {
		        return;
		  }
	      if (block.getType() == Material.ITEM_FRAME) {
		        return;
		  }
	      if (block.getType() == Material.REDSTONE_LAMP_ON) {
		        return;
		  }
	      if (block.getType() == Material.REDSTONE_LAMP_OFF) {
		        return;
		  }
	      if (block.getType() == Material.ITEM_FRAME) {
		        return;
		  }
	      if (block.getType() == Material.VINE) {
		        return;
		  }
	      if (block.getType() == Material.CROPS) {
		        return;
		  }
	      if (block.getType() == Material.WHEAT) {
		        return;
		  }
	      if (block.getType() == Material.DOUBLE_PLANT) {
		        return;
		  }
	      if (block.getType() == Material.CARROT) {
		        return;
		  }
	      if (block.getType() == Material.SKULL) {
				return;
			}
	    }
	    for (Block block : UtilBlock.getInRadius(loc, 1.5D).keySet())
	    {
	      if (UtilBlock.solid(block)){
	    	  
	    	  if (!UtilBlock.blockToRestore.contains(block)) {

	        if (block.getType() == Material.CARPET) {
	 
	        	UtilBlock.setBlockToRestore(block, 171, color, 4L , null);
	        }
	        if(block.getType() != Material.WOOL){

	        	UtilBlock.setBlockToRestore(block, 35, color, 4L , null);
	        	
	        }
	    	  }
	      }
	    }
	  }
	
	
	  @EventHandler
	  public void Shoot(PlayerInteractEvent event)
	  {
		final Player p = event.getPlayer();
	    if ((event.getAction() != Action.RIGHT_CLICK_AIR) && (event.getAction() != Action.RIGHT_CLICK_BLOCK)) {
	      return;
	    }
	    if (UtilBlock.usable(event.getClickedBlock())) {
	      return;
	    }
	    if (event.getPlayer().getItemInHand() == null) {
	      return;
	    }
	    if (event.getPlayer().getItemInHand().getType() != Material.GOLD_BARDING) {
	      return;
	    }
	    if(_coolDown.contains(p.getUniqueId())){
	    	return;
	    }
	    Player player = event.getPlayer();
	    event.setCancelled(true);
	    
		// ENABLING COOLDOWN
	    _coolDown.add(p.getUniqueId());
        Bukkit.getScheduler().runTaskLater(Main.schedule, new Runnable(){

			public void run() {
				_coolDown.remove(p.getUniqueId());
				
			}
        	
        }, 5);
	    
	    Projectile proj = player.launchProjectile(org.bukkit.entity.EnderPearl.class);
	    proj.setVelocity(proj.getVelocity().multiply(2));
	    this._balls.add(proj);
	    
	    player.getWorld().playSound(player.getLocation(), Sound.CHICKEN_EGG_POP, 1.5F, 1.2F);
	  }
	  
	  @EventHandler
	  public void Teleport(PlayerTeleportEvent event) {
	    if (event.getCause() == PlayerTeleportEvent.TeleportCause.ENDER_PEARL) {
	      event.setCancelled(true);
	    }
	  }
}
