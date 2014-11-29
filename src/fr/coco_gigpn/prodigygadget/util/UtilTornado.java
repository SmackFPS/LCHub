package fr.coco_gigpn.prodigygadget.util;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.List;

import net.minecraft.server.v1_8_R1.EntityItem;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_8_R1.inventory.CraftItemStack;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public class UtilTornado implements Listener {
	
	/**
	* Spawns a tornado at the given location l.
	*
	* @param plugin
	*            - Plugin instance that spawns the tornado.
	* @param location
	*            - Location to spawn the tornado.
	* @param material
	*            - The base material for the tornado.
	* @param data
	*            - Data for the block.
	* @param direction
	*            - The direction the tornado should move in.
	* @param speed
	*            - How fast it moves in the given direction. Warning! A number greater than 0.3 makes it look weird.
	* @param amount_of_blocks
	*            - The max amount of blocks that can exist in the tornado.
	* @param time
	*            - The amount of ticks the tornado should be alive.
	* @param spew
	*            - Defines if the tornado should remove or throw out any block it picks up.
	* @param explode
	*            - This defines if the tornado should "explode" when it dies. Warning! Right now it only creates a huge mess.
	*/
	public static void spawnTornado(
	        final JavaPlugin plugin,
	        final Location  location,
	        final Material  material,
	        final byte      data,
	        final Vector    direction,
	        final double    speed,
	        final int        amount_of_blocks,
	        final long      time,
	        final boolean    spew,
	        final boolean    explode
	) {
	   
	    class VortexBlock {
	 
	        private Entity entity;
	       
	        public boolean removable = true;
	 
	        private float ticker_vertical = 0.0f;
	        private float ticker_horisontal = (float) (Math.random() * 2 * Math.PI);
	 
	       
	        public VortexBlock(Location l, Material m, byte d) {
	 
	            	 EntityItem ei = new EntityItem(
						      ((CraftWorld)l.getWorld()).getHandle(),
						      l.getX(),
						      l.getY(),
						      l.getZ(),
						      CraftItemStack.asNMSCopy(new ItemStack(m))) {
						      public boolean a(EntityItem entityitem) {
						        return false;
						      }
						    };
						    ei.pickupDelay = 20;
						    ((CraftWorld)l.getWorld()).getHandle().addEntity(ei);
	 
						    entity = ei.getBukkitEntity();
	           
	            addMetadata();
	        }
	       
	        public VortexBlock(Entity e) {
	            entity    = e;
	            removable = false;
	            addMetadata();
	        }
	       
	        private void addMetadata() {
	            entity.setMetadata("vortex", new FixedMetadataValue(plugin, "protected"));
	        }
	       
	        public void remove() {
	            if(removable) {
	                entity.remove();
	            }
	            entity.removeMetadata("vortex", plugin);
	        }
	 
	        public HashSet<VortexBlock> tick() {
	           
	            double radius    = Math.sin(verticalTicker()) * 2;
	            float  horisontal = horisontalTicker();
	           
	            Vector v = new Vector(radius * Math.cos(horisontal), 0.5D, radius * Math.sin(horisontal));
	           
	            HashSet<VortexBlock> new_blocks = new HashSet<VortexBlock>();
	           
	           
	            // Pick up other entities
	            List<Entity> entities = entity.getNearbyEntities(1.0D, 1.0D, 1.0D);
	            for(Entity e : entities) {
	                if(!e.hasMetadata("vortex")) {
	                    new_blocks.add(new VortexBlock(e));
	                }
	            }
	           
	            setVelocity(v);
	           
	            return new_blocks;
	        }
	 
	        private void setVelocity(Vector v) {
	            entity.setVelocity(v);
	        }
	 
	        private float verticalTicker() {
	            if (ticker_vertical < 1.0f) {
	                ticker_vertical += 0.05f;
	            }
	            return ticker_vertical;
	        }
	 
	        private float horisontalTicker() {
//	                ticker_horisontal = (float) ((ticker_horisontal + 0.8f) % 2*Math.PI);
	            return (ticker_horisontal += 0.8f);
	        }
	    }
	   
	    // Modify the direction vector using the speed argument.
	    if (direction != null) {
	        direction.normalize().multiply(speed);
	    }
	   
	    // This set will contain every block created to make sure the metadata for each and everyone is removed.
	    final HashSet<VortexBlock> clear = new HashSet<VortexBlock>();
	   
	    final int id = new BukkitRunnable() {
	 
	        private ArrayDeque<VortexBlock> blocks = new ArrayDeque<VortexBlock>();
	 
	        public void run() {
	           
	            if (direction != null) {
	                location.add(direction);
	            }
	 
	            // Spawns 10 blocks at the time.
	            for (int i = 0; i < 10; i++) {
	                checkListSize();
	                VortexBlock vb = new VortexBlock(location, material, data);
	                blocks.add(vb);
	                clear.add(vb);
	            }
	           
	            // Make all blocks in the list spin, and pick up any blocks that get in the way.
	            ArrayDeque<VortexBlock> que = new ArrayDeque<VortexBlock>();
	 
	            for (VortexBlock vb : blocks) {
	                HashSet<VortexBlock> new_blocks = vb.tick();
	                for(VortexBlock temp : new_blocks) {
	                    que.add(temp);
	                }
	            }
	           
	            // Add the new blocks
	            for(VortexBlock vb : que) {
	                checkListSize();
	                blocks.add(vb);
	                clear.add(vb);
	            }
	        }
	       
	        // Removes the oldest block if the list goes over the limit.
	        private void checkListSize() {
	            while(blocks.size() >= amount_of_blocks) {
	                VortexBlock vb = blocks.getFirst();
	                vb.remove();
	                blocks.remove(vb);
	                clear.remove(vb);
	            }
	        }
	    }.runTaskTimer(plugin, 5L, 5L).getTaskId();
	 
	    // Stop the "tornado" after the given time.
	    new BukkitRunnable() {
	        public void run() {
	            for(VortexBlock vb : clear) {
	                vb.remove();
	            }
	            plugin.getServer().getScheduler().cancelTask(id);
	        }
	    }.runTaskLater(plugin, time);
	}

	
	@EventHandler
    public void OnPlayerPickup(PlayerPickupItemEvent event) {
		if(event.getItem().hasMetadata("vortex")) {
			event.setCancelled(true);
		}
	}
}
