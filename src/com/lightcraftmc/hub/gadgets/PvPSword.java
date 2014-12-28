package com.lightcraftmc.hub.gadgets;

import net.lightcraftmc.fusebox.util.strings.MessageType;
import net.lightcraftmc.fusebox.util.strings.StringManager;

import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;

public class PvPSword
  implements Listener
{
  public static ArrayList<UUID> pvp = new ArrayList();
  
  public static void giveSword(Player p)
  {
    pvp.remove(p.getUniqueId());
    ItemStack sword = new ItemStack(Material.DIAMOND_SWORD, 1);
    ItemMeta pvp = sword.getItemMeta();
    pvp.setDisplayName(ChatColor.LIGHT_PURPLE + "PvP Sword " + 
      ChatColor.GRAY + "(Right-Click To PVP)");
    ArrayList<String> Lore = new ArrayList();
    Lore.add("Make sure others have this enabled!");
    pvp.setLore(Lore);
    sword.setItemMeta(pvp);
    p.getInventory().setItem(2, sword);
  }
  
  @EventHandler(priority=EventPriority.HIGHEST)
  public void onPlayerPlaceBlock(PlayerInteractEvent event)
  {
    Player player = event.getPlayer();
    if (((event.getAction() == Action.RIGHT_CLICK_AIR) || 
      (event.getAction() == Action.RIGHT_CLICK_BLOCK)) && 
      (disName(player.getItemInHand()) != null)) {
      if (disName(player.getItemInHand()).equalsIgnoreCase(ChatColor.LIGHT_PURPLE + "PvP Sword " + ChatColor.GRAY + "(Right-Click To PVP)")) {
        if (!pvp.contains(player.getUniqueId()))
        {
          pvp.add(player.getUniqueId());
          player.getItemInHand().addEnchantment(Enchantment.DAMAGE_ALL, 1);
          player.sendMessage(StringManager.getPrefix(MessageType.INFO) + "PVP Sword Activated");
        }
        else
        {
          pvp.remove(player.getUniqueId());
          player.getItemInHand().removeEnchantment(Enchantment.DAMAGE_ALL);
          player.sendMessage(StringManager.getPrefix(MessageType.INFO) + "PVP Sword Deactivated");
        }
      }
    }
  }
  
  @EventHandler
  public void pvp(EntityDamageByEntityEvent e)
  {
    if (((e.getEntity() instanceof Player)) && 
      ((e.getDamager() instanceof Player)))
    {
      Player dr = (Player)e.getDamager();
      Player dd = (Player)e.getEntity();
      if ((!pvp.contains(dr.getUniqueId())) && (!pvp.contains(dd.getUniqueId())))
      {
        dr.sendMessage(StringManager.getPrefix(MessageType.ERROR) + "You both have PVP Disabled!");
        dr.playSound(dr.getLocation(), Sound.ANVIL_LAND, 1.0F, 2.0F);
        e.setCancelled(true);
      }
      if ((pvp.contains(dr.getUniqueId())) || (pvp.contains(dd.getUniqueId())))
      {
        dr.playSound(dr.getLocation(), Sound.ORB_PICKUP, 1.0F, 2.0F);
        e.setCancelled(false);
      }
      if ((pvp.contains(dr.getUniqueId())) && (!pvp.contains(dd.getUniqueId())))
      {
        dr.sendMessage(StringManager.getPrefix(MessageType.ERROR) + "The player has PVP Disabled!");
        dr.playSound(dr.getLocation(), Sound.ANVIL_LAND, 1.0F, 2.0F);
        e.setCancelled(true);
      }
      if ((!pvp.contains(dr.getUniqueId())) && (pvp.contains(dd.getUniqueId())))
      {
        dr.sendMessage(StringManager.getPrefix(MessageType.ERROR) + "You have PVP Disabled!");
        dr.playSound(dr.getLocation(), Sound.ANVIL_LAND, 1.0F, 2.0F);
        e.setCancelled(true);
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


/* Location:           A:\LC\Lobby\plugins\HubPlugin.jar
 * Qualified Name:     me.mike1665.funstuff.PvPSword
 * JD-Core Version:    0.7.0.1
 */