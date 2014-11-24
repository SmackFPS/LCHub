package me.mike1665.eventhandlers;

import me.mike1665.particlelib.ParticleEffect;

import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.EnderPearl;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.ItemStack;

public class BuyEnderDoge implements Listener{
	
	@EventHandler
	public void projectiles(ProjectileHitEvent event)
	{
	  Projectile projectile = event.getEntity();
	  if ((projectile instanceof EnderPearl))
	  {
	    EnderPearl enderpearl = (EnderPearl)projectile;
	    if ((enderpearl.getShooter() instanceof Player))
	    {
	      Player player = (Player)enderpearl.getShooter();
	      if ((disName(player.getItemInHand()) != null) && (disName(player.getItemInHand()).equalsIgnoreCase(ChatColor.BLUE + "EnderDoge"))){
	    	  EnderPearl e = (EnderPearl)event.getEntity();
		      e.getWorld().playSound(e.getLocation(), Sound.WOLF_BARK, 1.0F, 1.0F);
		      ParticleEffect.PORTAL.display(e.getLocation(), 0.5F, 0.5F, 0.5F, 0.0F, 20);
		      ParticleEffect.FIREWORKS_SPARK.display(e.getLocation(), 0.5F, 0.5F, 0.5F, 0.0F, 10);
		      player.getItemInHand().setAmount(player.getItemInHand().getAmount() - 1);
		      if (player.getItemInHand().getAmount() == 1) {
		    	  player.setItemInHand(null);
		      }
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