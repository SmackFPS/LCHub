package com.lightcraftmc.event.handlers;

import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.ItemStack;

import com.lightcraftmc.particlelib.ParticleEffect;
import com.lightcraftmc.particles18.ParticleLib18;

public class BuyMeowBalls implements Listener{
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void projectiles(ProjectileHitEvent event)
	{
	  Projectile projectile = event.getEntity();
	  if ((projectile instanceof Snowball))
	  {
	    Snowball snowball = (Snowball)projectile;
	    if ((snowball.getShooter() instanceof Player))
	    {
	      Player player = (Player)snowball.getShooter();
	      if ((disName(player.getItemInHand()) != null) && (disName(player.getItemInHand()).equalsIgnoreCase(ChatColor.GREEN + "MeowBall"))){
	    	  Snowball s = (Snowball)event.getEntity();
		      s.getWorld().playSound(s.getLocation(), Sound.CAT_MEOW, 1.0F, 1.0F);
	          ParticleLib18 lava = new ParticleLib18(com.lightcraftmc.particles18.ParticleLib18.ParticleType.LAVA, 0.0F, 20, 0);
	          ParticleLib18 hearts = new ParticleLib18(com.lightcraftmc.particles18.ParticleLib18.ParticleType.HEART, 0.0F, 2, 0);
	          ParticleLib18 largesmoke = new ParticleLib18(com.lightcraftmc.particles18.ParticleLib18.ParticleType.SMOKE_LARGE, 0.0F, 10, 0);


		     //ParticleEffect.LAVA.display(s.getLocation(), 0.0F, 0.0F, 0.0F, 0.0F, 20);
	          lava.sendToLocation(s.getLocation());
	          hearts.sendToLocation(s.getLocation());
	          largesmoke.sendToLocation(s.getLocation());

		      s.getWorld().playEffect(s.getLocation(), Effect.MOBSPAWNER_FLAMES, 1);
		      s.getWorld().playEffect(s.getLocation(), Effect.MOBSPAWNER_FLAMES, 1);
		     // ParticleEffect.HEART.display(s.getLocation(), 0.5F, 0.5F, 0.5F, 0.0F, 2);
		     // ParticleEffect.LARGE_SMOKE.display(s.getLocation(), 0.5F, 0.5F, 0.5F, 0.0F, 10);
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
