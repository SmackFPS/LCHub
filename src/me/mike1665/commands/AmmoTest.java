package me.mike1665.commands;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.UUID;

import me.mike1665.Main.Main;
import me.mike1665.ammo.GadgetAmmo;
import me.mike1665.ammo.MeowAmmoManager;
import me.mike1665.eventhandlers.PaintballGun;
import me.mike1665.particlelib.ParticleEffect;
import me.mike1665.particles18.ParticleLib18;
import net.lightcraftmc.fusebox.util.UtilBlock;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.lightcraftmc.fusebox.strings.MessageType;
import net.lightcraftmc.fusebox.strings.StringManager;

public class AmmoTest implements Listener{

	private static Main plugin;
	private ArrayList<UUID> _coolDown = new ArrayList<UUID>();
	private HashSet<Projectile> _balls = new HashSet<Projectile>();



	public static void setup() {
		plugin = Main.getInstance();
	}
	
	public static boolean onCommand(CommandSender sender, Command cmd, String label, String[] a) {
		if(!(sender instanceof Player)) return false;
		Player p = (Player) sender;
		if (cmd.getName().equalsIgnoreCase("321") && p.isOp()) {
			GadgetAmmo.addGadgetAmo(p, "QuakeGun", 5);
			p.sendMessage("This " + GadgetAmmo.balanceGadgetAmo(p, "QuakeGun"));
		}
		if (cmd.getName().equalsIgnoreCase("3211") && p.isOp()) {
			ItemStack snow = new ItemStack(Material.DIAMOND_HOE, 1);
        	ItemMeta sno = snow.getItemMeta();
        	sno.setDisplayName(ChatColor.GREEN + "QuakeGun " + ChatColor.DARK_RED + GadgetAmmo.balanceGadgetAmo(p, "QuakeGun"));
        	snow.setItemMeta(sno);
        	p.getInventory().addItem(snow);
		}
		if (cmd.getName().equalsIgnoreCase("ammotest") && p.isOp()) {
			if(p.getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.GREEN + "MeowBall " + ChatColor.DARK_RED + MeowAmmoManager.balaceMeowAmmo(p))) {
				p.sendMessage("Check worked!");
			}
		}
		return false;
	}

	/*@SuppressWarnings("deprecation")
	@EventHandler
	public void projectiles(ProjectileHitEvent event){ 	  
	  Projectile projectile = event.getEntity();
	  if ((projectile instanceof Snowball)){
		  
	    Snowball snowball = (Snowball)projectile;
	    if ((snowball.getShooter() instanceof Player)){
	    	
	      final Player player = (Player)snowball.getShooter();     
	      if(player.getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.GREEN + "MeowBall " + ChatColor.DARK_RED + MeowAmmoManager.balaceMeowAmmo(player))) {
	    		  
	    	  this._coolDown.add(player.getUniqueId());
	    		  Bukkit.getScheduler().runTaskLater(Main.getInstance(), new Runnable() {
			
	    			  @Override
	    			  public void run() {
	    				  AmmoTest.this._coolDown.remove(player.getUniqueId());
				
	    			  }
	    		  }, 10L);
	    		  
	    		  Snowball s = (Snowball)event.getEntity();
	    		  s.getWorld().playSound(s.getLocation(), Sound.CAT_MEOW, 1.0F, 1.0F);
	    		  ParticleLib18 lava = new ParticleLib18(me.mike1665.particles18.ParticleLib18.ParticleType.LAVA, 0.0F, 20, 0);
	    		  ParticleLib18 hearts = new ParticleLib18(me.mike1665.particles18.ParticleLib18.ParticleType.HEART, 0.0F, 2, 0);
	    		  ParticleLib18 largesmoke = new ParticleLib18(me.mike1665.particles18.ParticleLib18.ParticleType.SMOKE_LARGE, 0.0F, 10, 0);
	    		  lava.sendToLocation(s.getLocation());
	    		  hearts.sendToLocation(s.getLocation());
	    		  largesmoke.sendToLocation(s.getLocation());
	    		  s.getWorld().playEffect(s.getLocation(), Effect.MOBSPAWNER_FLAMES, 1);
	    		  s.getWorld().playEffect(s.getLocation(), Effect.MOBSPAWNER_FLAMES, 1);
	    		  MeowAmmoManager.takeMeowAmmo(player, 1);
	    		  ItemStack snow = new ItemStack(Material.SNOW_BALL, 2);
	    		  ItemMeta sno = snow.getItemMeta();
	    		  sno.setDisplayName(ChatColor.GREEN + "MeowBall " + ChatColor.DARK_RED + MeowAmmoManager.balaceMeowAmmo(player));
	    		  snow.setItemMeta(sno);
	    		  player.getInventory().setItemInHand(snow);
	      }
	    }
	  }
	} */
	
	@EventHandler
	public void snowballParticle(ProjectileHitEvent event) {
		if (!this._balls.remove(event.getEntity())) {
			return;
		}
		Location loc = event.getEntity().getLocation().add(event.getEntity().getVelocity());
		loc.getWorld().playSound(loc, Sound.CAT_MEOW, 1.0F, 1.0F);
		ParticleLib18 lava = new ParticleLib18(me.mike1665.particles18.ParticleLib18.ParticleType.LAVA, 0.0F, 20, 0);
	    ParticleLib18 hearts = new ParticleLib18(me.mike1665.particles18.ParticleLib18.ParticleType.HEART, 0.0F, 2, 0);
		ParticleLib18 largesmoke = new ParticleLib18(me.mike1665.particles18.ParticleLib18.ParticleType.SMOKE_LARGE, 0.0F, 10, 0);
		lava.sendToLocation(loc.add(0, +1, 0));
		hearts.sendToLocation(loc.add(0, +1, 0));
		largesmoke.sendToLocation(loc.add(0, +1, 0));
		loc.getWorld().playEffect(loc.add(0, -1, 0) , Effect.MOBSPAWNER_FLAMES, 1);
		loc.getWorld().playEffect(loc.add(0, -1, 0), Effect.MOBSPAWNER_FLAMES, 1);
	}
	
	@EventHandler
	public void throwSnowball(PlayerInteractEvent e) {
		final Player p = e.getPlayer();
		if (UtilBlock.usable(e.getClickedBlock())){
			return;
		}
		if (e.getPlayer().getItemInHand() == null) {
			return;
		}
	    if (e.getPlayer().getItemInHand().getType() != Material.BLAZE_ROD) {
	        return;
	    }
		if (this._coolDown.contains(p.getUniqueId())) {
			return;
		}
		
		if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
			if ((disName(p.getItemInHand()) != null) && (disName(p.getItemInHand()).equalsIgnoreCase(ChatColor.GREEN + "MeowBall " + ChatColor.DARK_RED + MeowAmmoManager.balaceMeowAmmo(p)))){
				Player player = e.getPlayer();
				e.setCancelled(true);
		
				this._coolDown.add(p.getUniqueId());
				Bukkit.getScheduler().runTaskLater(Main.getInstance(), new Runnable() {
			
					@Override
					public void run() {
						AmmoTest.this._coolDown.remove(p.getUniqueId());
				
					}
				}, 5L);
		
				Projectile proj = player.launchProjectile(Snowball.class);
				proj.setVelocity(proj.getVelocity().multiply(2));
				this._balls.add(proj);
				player.getWorld().playSound(player.getLocation(), Sound.CHICKEN_EGG_POP, 1.5F, 1.5F);
				MeowAmmoManager.takeMeowAmmo(player, 1);
				if (MeowAmmoManager.balaceMeowAmmo(player) < 1){
					player.getInventory().setItemInHand(null);
					player.sendMessage(StringManager.getPrefix(MessageType.ERROR) + ChatColor.AQUA+ "You ran out of ammo!");
					return;
				}
	    		ItemStack snow = new ItemStack(Material.BLAZE_ROD, 1);
	    		ItemMeta sno = snow.getItemMeta();
	    		sno.setDisplayName(ChatColor.GREEN + "MeowBall " + ChatColor.DARK_RED + MeowAmmoManager.balaceMeowAmmo(player));
	    		snow.setItemMeta(sno);
	    		player.getInventory().setItemInHand(snow);
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
