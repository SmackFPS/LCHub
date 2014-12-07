package me.mike1665.commands;

import me.mike1665.Main.Main;
import me.mike1665.ammo.MeowAmmoManager;
import me.mike1665.particles18.ParticleLib18;

import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class AmmoTest implements Listener{

	public static Main plugin;

	public static void setup(Main instance) {
		plugin = instance;
	}
	
	public static boolean onCommand(CommandSender sender, Command cmd, String label, String[] a) {
		if(!(sender instanceof Player)) return false;
		Player p = (Player) sender;
		if (cmd.getName().equalsIgnoreCase("giveammo")) {
			MeowAmmoManager.giveMeowAmmo(p, 20);
			p.sendMessage("This " + MeowAmmoManager.balaceMeowAmmo(p));
		}
		if (cmd.getName().equalsIgnoreCase("giveammo2")) {
			ItemStack snow = new ItemStack(Material.SNOW_BALL);
        	ItemMeta sno = snow.getItemMeta();
        	sno.setDisplayName(ChatColor.GREEN + "MeowBall " + ChatColor.DARK_RED + MeowAmmoManager.balaceMeowAmmo(p));
        	snow.setItemMeta(sno);
        	p.getInventory().addItem(snow);
		}
		return false;
	}

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
	      if ((disName(player.getItemInHand()) != null) && (disName(player.getItemInHand()).equalsIgnoreCase(ChatColor.GREEN + "MeowBall " + ChatColor.DARK_RED + MeowAmmoManager.balaceMeowAmmo(player)))){
	    	  player.sendMessage("debug");
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
