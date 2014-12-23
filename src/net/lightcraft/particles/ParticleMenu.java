package net.lightcraft.particles;

import java.util.Arrays;
import java.util.HashMap;

import me.mike1665.particles18.ParticleLib18.ParticleType;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.arrayprolc.rank.RankManager;
import com.arrayprolc.rank.ServerRank;

public class ParticleMenu
{
  public static final HashMap<Player, Inventory> gui = new HashMap<Player, Inventory>();

  public static void openMenu(Player p)
  {
    if (gui.containsKey(p)) {
      return;
    }

    Inventory particle_gui = Bukkit.createInventory(null, 36, "Particle Menu");
    gui.put(p, particle_gui);

    ItemStack heart = new ItemStack(Material.RED_ROSE);
    ItemStack note = new ItemStack(Material.NOTE_BLOCK);
    ItemStack witch = new ItemStack(Material.POTION);
    ItemStack critical = new ItemStack(Material.NETHER_STAR);
    ItemStack colorful = new ItemStack(Material.QUARTZ);
    ItemStack smoke = new ItemStack(Material.COAL);
    ItemStack happy = new ItemStack(Material.EMERALD);
    ItemStack angry = new ItemStack(Material.FIREBALL);
    ItemStack flame = new ItemStack(Material.BLAZE_POWDER);
    ItemStack water = new ItemStack(Material.WATER_BUCKET);
    ItemStack lava = new ItemStack(Material.LAVA_BUCKET);
    ItemStack enchantment = new ItemStack(Material.ENCHANTED_BOOK);
    ItemStack swirl = new ItemStack(Material.STRING);
    ItemStack redstone = new ItemStack(Material.REDSTONE);
    ItemStack stop = new ItemStack(Material.TNT);
    ItemStack noperm = new ItemStack(Material.INK_SACK, 1, (short)8);

    ItemMeta heartMeta = heart.getItemMeta();
    ItemMeta noteMeta = note.getItemMeta();
    ItemMeta witchMeta = witch.getItemMeta();
    ItemMeta criticalMeta = critical.getItemMeta();
    ItemMeta colorfulMeta = colorful.getItemMeta();
    ItemMeta smokeMeta = smoke.getItemMeta();
    ItemMeta happyMeta = happy.getItemMeta();
    ItemMeta angryMeta = angry.getItemMeta();
    ItemMeta flameMeta = flame.getItemMeta();
    ItemMeta waterMeta = water.getItemMeta();
    ItemMeta lavaMeta = lava.getItemMeta();
    ItemMeta enchantmentMeta = enchantment.getItemMeta();
    ItemMeta swirlMeta = swirl.getItemMeta();
    ItemMeta redstoneMeta = redstone.getItemMeta();
    ItemMeta stopMeta = stop.getItemMeta();
    ItemMeta nopermMeta = noperm.getItemMeta();

    heartMeta.setDisplayName("Heart");
    noteMeta.setDisplayName("Note");
    witchMeta.setDisplayName("Witch Magic");
    criticalMeta.setDisplayName("Crit");
    colorfulMeta.setDisplayName("Rainbow");
    smokeMeta.setDisplayName("Smoke");
    happyMeta.setDisplayName("Happy Villager");
    angryMeta.setDisplayName("Angry Village");
    flameMeta.setDisplayName("Flames");
    waterMeta.setDisplayName("Water");
    lavaMeta.setDisplayName("Lava");
    enchantmentMeta.setDisplayName("Enchantment");
    swirlMeta.setDisplayName("Swirls");
    redstoneMeta.setDisplayName("Redstone");
    stopMeta.setDisplayName("Turn Off");
    nopermMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&cLocked"));
    nopermMeta.setLore(Arrays.asList(
    		"",
    		ChatColor.translateAlternateColorCodes('&', "&6Particles can only be unlocked"),
    		ChatColor.translateAlternateColorCodes('&', "&6through chests!"),
    		"",
    		ChatColor.translateAlternateColorCodes('&', "&bVIP's Get all particles!"),
    		""));

    heart.setItemMeta(heartMeta);
    note.setItemMeta(noteMeta);
    witch.setItemMeta(witchMeta);
    critical.setItemMeta(criticalMeta);
    colorful.setItemMeta(colorfulMeta);
    smoke.setItemMeta(smokeMeta);
    happy.setItemMeta(happyMeta);
    angry.setItemMeta(angryMeta);
    flame.setItemMeta(flameMeta);
    water.setItemMeta(waterMeta);
    lava.setItemMeta(lavaMeta);
    enchantment.setItemMeta(enchantmentMeta);
    swirl.setItemMeta(swirlMeta);
    redstone.setItemMeta(redstoneMeta);
    stop.setItemMeta(stopMeta);
    noperm.setItemMeta(nopermMeta);

    if (UnlockedParticle.hasUnlockedParticle(p, ParticleType.HEART) || RankManager.hasRank(p, ServerRank.VIP))
      particle_gui.setItem(1, heart);
    else {
      particle_gui.setItem(1, noperm);
    }
    if (UnlockedParticle.hasUnlockedParticle(p, ParticleType.NOTE) || RankManager.hasRank(p, ServerRank.VIP))
      particle_gui.setItem(2, note);
    else {
      particle_gui.setItem(2, noperm);
    }
    if (UnlockedParticle.hasUnlockedParticle(p, ParticleType.FLAME) || RankManager.hasRank(p, ServerRank.VIP))
      particle_gui.setItem(3, flame);
    else {
      particle_gui.setItem(3, noperm);
    }
    if (UnlockedParticle.hasUnlockedParticle(p, ParticleType.DRIP_WATER) || RankManager.hasRank(p, ServerRank.VIP))
      particle_gui.setItem(4, water);
    else {
      particle_gui.setItem(4, noperm);
    }
    if (UnlockedParticle.hasUnlockedParticle(p, ParticleType.DRIP_LAVA) || RankManager.hasRank(p, ServerRank.VIP))
      particle_gui.setItem(5, lava);
    else {
      particle_gui.setItem(5, noperm);
    }
    if (UnlockedParticle.hasUnlockedParticle(p, ParticleType.ENCHANTMENT_TABLE) || RankManager.hasRank(p, ServerRank.VIP))
      particle_gui.setItem(6, enchantment);
    else {
      particle_gui.setItem(6, noperm);
    }
    if (UnlockedParticle.hasUnlockedParticle(p, ParticleType.SPELL) || RankManager.hasRank(p, ServerRank.VIP))
      particle_gui.setItem(7, swirl);
    else {
      particle_gui.setItem(7, noperm);
    }
    if (UnlockedParticle.hasUnlockedParticle(p, ParticleType.REDSTONE) || RankManager.hasRank(p, ServerRank.VIP))
      particle_gui.setItem(10, redstone);
    else {
      particle_gui.setItem(10, noperm);
    }
    if (UnlockedParticle.hasUnlockedParticle(p, ParticleType.SPELL_WITCH) || RankManager.hasRank(p, ServerRank.VIP))
      particle_gui.setItem(11, witch);
    else {
      particle_gui.setItem(11, noperm);
    }
    if (UnlockedParticle.hasUnlockedParticle(p, ParticleType.CRIT_MAGIC) || RankManager.hasRank(p, ServerRank.VIP))
      particle_gui.setItem(12, critical);
    else {
      particle_gui.setItem(12, noperm);
    }
    if (UnlockedParticle.hasUnlockedParticle(p, ParticleType.SPELL_MOB) || RankManager.hasRank(p, ServerRank.VIP))
      particle_gui.setItem(13, colorful);
    else {
      particle_gui.setItem(13, noperm);
    }
    if (UnlockedParticle.hasUnlockedParticle(p, ParticleType.SMOKE_NORMAL) || RankManager.hasRank(p, ServerRank.VIP))
      particle_gui.setItem(14, smoke);
    else {
      particle_gui.setItem(14, noperm);
    }
    if (UnlockedParticle.hasUnlockedParticle(p, ParticleType.VILLAGER_HAPPY) || RankManager.hasRank(p, ServerRank.VIP))
      particle_gui.setItem(15, happy);
    else {
      particle_gui.setItem(15, noperm);
    }
    if (UnlockedParticle.hasUnlockedParticle(p, ParticleType.VILLAGER_ANGRY) || RankManager.hasRank(p, ServerRank.VIP))
      particle_gui.setItem(16, angry);
    else {
      particle_gui.setItem(16, noperm);
    }
    if (RankManager.hasRank(p, ServerRank.PLAYER))
      particle_gui.setItem(31, stop);
    else {
      particle_gui.setItem(31, noperm);
    }

    if ((RankManager.hasRank(p, ServerRank.VIP)) && (!p.isOp())) {
      particle_gui.setItem(1, heart);
      particle_gui.setItem(2, note);
      particle_gui.setItem(3, flame);
      particle_gui.setItem(4, water);
      particle_gui.setItem(5, lava);
      particle_gui.setItem(6, enchantment);
      particle_gui.setItem(7, swirl);
      particle_gui.setItem(10, redstone);
      particle_gui.setItem(11, witch);
      particle_gui.setItem(12, critical);
      particle_gui.setItem(13, colorful);
      particle_gui.setItem(14, smoke);
      particle_gui.setItem(15, happy);
      particle_gui.setItem(16, angry);
      particle_gui.setItem(31, stop);
    }

    for (Player player : gui.keySet()) {
      player.openInventory(particle_gui);
      gui.remove(p);
    }
  }
}
