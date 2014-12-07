package me.mike1665.gadgets;

import java.util.HashMap;
import java.util.Random;

import me.mike1665.Main.Main;
import me.mike1665.ammo.FireWorksAmmoManager;
import me.mike1665.ammo.KittyCannonAmmoManager;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Material;
import org.bukkit.FireworkEffect.Builder;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Ocelot;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

public class KittyCannon implements Listener{

	  private final Random random = new Random();
	  private HashMap<Player, Double> cooldown= new HashMap<Player, Double>();
	  HashMap<Player, BukkitRunnable> _cdRunnable = new HashMap<Player, BukkitRunnable>();

  
	@EventHandler(priority=EventPriority.HIGH)
	public void onPlayerUse(PlayerInteractEvent event) {
	  final Player p = event.getPlayer(); 
	  
	  if (p.getItemInHand() == null) {
			return;
		}
	    if (p.getItemInHand().getType() != Material.STICK) {
	        return;
	    }
		if (this.cooldown.containsKey(p)) {
			return;
		}
	  if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
		  if ((disName(p.getItemInHand()) != null) && (disName(p.getItemInHand()).equalsIgnoreCase(ChatColor.GOLD + "Kitty Cannon " + "§7«§b " + KittyCannonAmmoManager.balaceCatAmmo(p) + " §7«§b"))){
			    if (this.cooldown.containsKey(p)) {
				      return;
				    }
			  this.cooldown.put(p, Double.valueOf(2.1D));
			  this._cdRunnable.put(p, new BukkitRunnable() {
				      public void run() {
				        KittyCannon.this.cooldown.put(p, Double.valueOf(((Double)KittyCannon.this.cooldown.get(p)).doubleValue() - 0.1D));
				        if (((Double)KittyCannon.this.cooldown.get(p)).doubleValue() < 0.01D) {
				          KittyCannon.this.cooldown.remove(p);
				          KittyCannon.this._cdRunnable.remove(p);
				          cancel();
				        }
				      }
				    });
				    ((BukkitRunnable)this._cdRunnable.get(p)).runTaskTimer(Main.schedule, 2L, 2L);
				    
	    		Entity ent = (org.bukkit.entity.Ocelot) p.getWorld().spawnEntity(p.getLocation(), EntityType.OCELOT);
	    		final Ocelot ocelot = (Ocelot) ent;
				if (ocelot == null)
				{
					return;
				}
				final int i = random.nextInt(Ocelot.Type.values().length);
				ocelot.setCatType(Ocelot.Type.values()[i]);
				ocelot.setTamed(true);
				ocelot.setBaby();
				ocelot.setVelocity(p.getEyeLocation().getDirection().multiply(2));
				
				KittyCannonAmmoManager.takeCatAmmo(p, 1);
				if (KittyCannonAmmoManager.balaceCatAmmo(p) < 1){
					p.getInventory().setItemInHand(null);
					p.sendMessage("testdsdas");
					return;
				}
	    		ItemStack snow = new ItemStack(Material.STICK, 1);
	    		ItemMeta sno = snow.getItemMeta();
	    		sno.setDisplayName(ChatColor.GOLD + "Kitty Cannon " + "§7«§b " + KittyCannonAmmoManager.balaceCatAmmo(p) + " §7«§b");
	    		snow.setItemMeta(sno);
	    		p.getInventory().setItemInHand(snow);
				new BukkitRunnable() {
					
					int count = 1;
					@Override
					public void run() {
						final Location loc = ocelot.getLocation();
						ocelot.remove();
						final org.bukkit.entity.Firework fw = (org.bukkit.entity.Firework) ocelot.getWorld().spawnEntity(loc.add(0.5, 1.2, 0.5), EntityType.FIREWORK);
						Builder builder = FireworkEffect.builder();
						FireworkMeta m = fw.getFireworkMeta();
						builder.trail(random.nextBoolean()).flicker(random.nextBoolean());
						builder.withColor(Color.fromRGB(random.nextInt(256), random.nextInt(256), random.nextInt(256)));
						builder.with(Type.values()[random.nextInt(Type.values().length)]);
						builder.withFade(Color.fromRGB(random.nextInt(256), random.nextInt(256), random.nextInt(256)));
						m.addEffect(builder.build());
						m.setPower(random.nextInt(3)+1);
						fw.setFireworkMeta(m);
						count --;
						if (count == 0) cancel();
					}
				}.runTaskTimer(Main.schedule, 12, 1);
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
	
	  public static double arrondi(double A, int B) {
		    return (int)(A * Math.pow(10.0D, B) + 0.5D) / Math.pow(10.0D, B);
		  }
	
}
