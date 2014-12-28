package com.lightcraftmc.particles;

import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import com.lightcraftmc.particles18.ParticleLib18.ParticleType;
import com.lightcraftmc.rank.RankManager;
import com.lightcraftmc.rank.ServerRank;

public class ParticleClick implements Listener{

	  @EventHandler
	  public void onInventoryClick(InventoryClickEvent e) {
	    if (!e.getInventory().getName().equals("Particle Menu")) {
	      return;
	    }

	    Player p = (Player)e.getWhoClicked();
	    e.setCancelled(true);

	    /*if ((!RankManager.hasRank(p, ServerRank.PLAYER))) {
	      p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou do not have permission!"));
	    } else {
	      if ((e.getCurrentItem() == null) || (e.getCurrentItem().getType() == Material.AIR) || (!e.getCurrentItem().hasItemMeta())) {
	        return;
	      } */

	      switch (e.getSlot()) {
	      case 1:
	        if (!UnlockedParticle.hasUnlockedParticle(p, ParticleType.HEART) && !RankManager.hasRank(p, ServerRank.VIP)) {
	          p.getPlayer().playSound(p.getLocation(), Sound.ITEM_BREAK, 1.0F, 1.0F);
	        } else {
	          p.playSound(p.getLocation(), Sound.CAT_PURREOW, 10.0F, 1.0F);
	          Rotation.color(p, ParticleType.HEART);
	          p.closeInventory();
	          p.sendMessage("Heart Particles Activated");
	        }  
	        break;
	      case 2:
	        if (!UnlockedParticle.hasUnlockedParticle(p, ParticleType.NOTE) && !RankManager.hasRank(p, ServerRank.VIP)) {
		          p.getPlayer().playSound(p.getLocation(), Sound.ITEM_BREAK, 1.0F, 1.0F);
	        } else {
	          p.playSound(p.getLocation(), Sound.NOTE_BASS, 10.0F, 1.0F);
	          Rotation.color(p, ParticleType.NOTE);
	          p.closeInventory();
	          p.sendMessage("Note Particles Activated");
	        }
	        break;
	      case 3:
	        if (!UnlockedParticle.hasUnlockedParticle(p, ParticleType.FLAME) && !RankManager.hasRank(p, ServerRank.VIP)) {
		          p.getPlayer().playSound(p.getLocation(), Sound.ITEM_BREAK, 1.0F, 1.0F);
	        } else {
	          p.playSound(p.getLocation(), Sound.FIRE_IGNITE, 10.0F, 1.0F);
	          Rotation.color(p, ParticleType.FLAME);
	          p.closeInventory();
	          p.sendMessage("Flame Particles Activated");
	        }
	        break;
	      case 4:
	        if (!UnlockedParticle.hasUnlockedParticle(p, ParticleType.DRIP_WATER) && !RankManager.hasRank(p, ServerRank.VIP)) {
		          p.getPlayer().playSound(p.getLocation(), Sound.ITEM_BREAK, 1.0F, 1.0F);

	        } else {
	          p.playSound(p.getLocation(), Sound.WATER, 10.0F, 1.0F);
	          Rotation.color(p, ParticleType.DRIP_WATER);
	          p.closeInventory();
	          p.sendMessage("Water Particles Activated");
	        }
	        break;
	      case 5:
	        if (!UnlockedParticle.hasUnlockedParticle(p, ParticleType.DRIP_LAVA) && !RankManager.hasRank(p, ServerRank.VIP)) {
		          p.getPlayer().playSound(p.getLocation(), Sound.ITEM_BREAK, 1.0F, 1.0F);

	        } else {
	          p.playSound(p.getLocation(), Sound.LAVA_POP, 10.0F, 1.0F);
	          Rotation.color(p, ParticleType.DRIP_LAVA);
	          p.closeInventory();
	          p.sendMessage("Lava Particles Activated");
	        }
	        break;
	      case 6:
	        if (!UnlockedParticle.hasUnlockedParticle(p, ParticleType.ENCHANTMENT_TABLE) && !RankManager.hasRank(p, ServerRank.VIP)) {
		          p.getPlayer().playSound(p.getLocation(), Sound.ITEM_BREAK, 1.0F, 1.0F);

	        } else {
	          p.playSound(p.getLocation(), Sound.LEVEL_UP, 10.0F, 1.0F);
	          Rotation.color(p, ParticleType.ENCHANTMENT_TABLE);
	          p.closeInventory();
	          p.sendMessage("Enchantmenu Particles Activated");
	        }
	        break;
	      case 7:
	        if (!UnlockedParticle.hasUnlockedParticle(p, ParticleType.SPELL) && !RankManager.hasRank(p, ServerRank.VIP)) {
		          p.getPlayer().playSound(p.getLocation(), Sound.ITEM_BREAK, 1.0F, 1.0F);

	        } else {
	          p.playSound(p.getLocation(), Sound.FIZZ, 10.0F, 1.0F);
	          Rotation.color(p, ParticleType.SPELL);
	          p.closeInventory();
	          p.sendMessage("Spell Particles Activated");
	        }
	        break;
	      case 10:
	        if (!UnlockedParticle.hasUnlockedParticle(p, ParticleType.REDSTONE) && !RankManager.hasRank(p, ServerRank.VIP)) {
		          p.getPlayer().playSound(p.getLocation(), Sound.ITEM_BREAK, 1.0F, 1.0F);

	        } else {
	          p.playSound(p.getLocation(), Sound.PISTON_EXTEND, 10.0F, 1.0F);
	          Rotation.color(p, ParticleType.REDSTONE);
	          p.closeInventory();
	          p.sendMessage("Redstone Particles Activated");
	        }
	        break;
	      case 11:
	        if (!UnlockedParticle.hasUnlockedParticle(p, ParticleType.SPELL_WITCH) && !RankManager.hasRank(p, ServerRank.VIP)) {
		          p.getPlayer().playSound(p.getLocation(), Sound.ITEM_BREAK, 1.0F, 1.0F);

	        } else {
	          p.playSound(p.getLocation(), Sound.GLASS, 10.0F, 1.0F);
	          Rotation.color(p, ParticleType.SPELL_WITCH);
	          p.closeInventory();
	          p.sendMessage("Witch Magic Particles Activated");
	        }
	        break;
	      case 12:
	        if (!UnlockedParticle.hasUnlockedParticle(p, ParticleType.CRIT_MAGIC) && !RankManager.hasRank(p, ServerRank.VIP)) {
		          p.getPlayer().playSound(p.getLocation(), Sound.ITEM_BREAK, 1.0F, 1.0F);

	        } else {
	          p.playSound(p.getLocation(), Sound.IRONGOLEM_HIT, 10.0F, 1.0F);
	          Rotation.color(p, ParticleType.CRIT_MAGIC);
	          p.closeInventory();
	          p.sendMessage("Critial Particled Activated");
	        }
	        break;
	      case 13:
	        if (!UnlockedParticle.hasUnlockedParticle(p, ParticleType.SPELL_MOB) && !RankManager.hasRank(p, ServerRank.VIP)) {
		          p.getPlayer().playSound(p.getLocation(), Sound.ITEM_BREAK, 1.0F, 1.0F);

	        } else {
	          p.playSound(p.getLocation(), Sound.NOTE_PLING, 10.0F, 1.0F);
	          Rotation.color(p, ParticleType.SPELL_MOB);
	          p.closeInventory();
	          p.sendMessage("Mob Spell Particles Activated");
	        }
	        break;
	      case 14:
	        if (!UnlockedParticle.hasUnlockedParticle(p, ParticleType.SMOKE_NORMAL) && !RankManager.hasRank(p, ServerRank.VIP)) {
		          p.getPlayer().playSound(p.getLocation(), Sound.ITEM_BREAK, 1.0F, 1.0F);

	        } else {
	          p.playSound(p.getLocation(), Sound.FIRE, 10.0F, 1.0F);
	          Rotation.color(p, ParticleType.SMOKE_NORMAL);
	          p.closeInventory();
	          p.sendMessage("Smoke Particles Activated");
	        }
	        break;
	      case 15:
	        if (!UnlockedParticle.hasUnlockedParticle(p, ParticleType.VILLAGER_HAPPY) && !RankManager.hasRank(p, ServerRank.VIP)) {
		          p.getPlayer().playSound(p.getLocation(), Sound.ITEM_BREAK, 1.0F, 1.0F);

	        } else {
	        	p.playSound(p.getLocation(), Sound.VILLAGER_YES, 10.0F, 1.0F);
	        	Rotation.color(p, ParticleType.VILLAGER_HAPPY);
		        p.closeInventory();
		        p.sendMessage("Happy Village Particles Activated");

	        }
	        break;
	      case 16:
	        if (!UnlockedParticle.hasUnlockedParticle(p, ParticleType.VILLAGER_ANGRY) && !RankManager.hasRank(p, ServerRank.VIP)) {
		          p.getPlayer().playSound(p.getLocation(), Sound.ITEM_BREAK, 1.0F, 1.0F);
	        } else {
	          p.playSound(p.getLocation(), Sound.VILLAGER_NO, 10.0F, 1.0F);
	          Rotation.angryColor(p, ParticleType.VILLAGER_ANGRY);
	          p.closeInventory();
	          p.sendMessage("Angry Villager Particles Activated");
	        }
	        break;
	      case 31:
	        if (Rotation.player_particle.containsKey(p)) {
	          Rotation.stopRotation(p);
	          p.playSound(p.getLocation(), Sound.EXPLODE, 10.0F, 1.0F);
	          p.closeInventory();
	          p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&4Effect Cleared!"));
	        } else {
	          p.closeInventory();
	          p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou do not have an effect to clear!"));
	        }
	        break;
	      }
	    }
}
