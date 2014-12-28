package com.lightcraftmc.hub.gadgets;

import java.util.ArrayList;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;

public class MagicClock
  implements Listener
{
  private ArrayList<String> usingClock = new ArrayList();
  
  @EventHandler(priority=EventPriority.HIGHEST)
  public void onPlayerRespawn(PlayerJoinEvent event)
  {
    Player p = event.getPlayer();
    ItemStack sword = new ItemStack(Material.WATCH, 1);
    ItemMeta pvp = sword.getItemMeta();
    pvp.setDisplayName(ChatColor.GREEN + "Players " + ChatColor.YELLOW + ChatColor.BOLD + ">> " + ChatColor.RESET + ChatColor.GREEN + "Activated");
    usingClock.remove(event.getPlayer());
    ArrayList<String> Lore = new ArrayList();
    Lore.add(ChatColor.BLUE + "The Magic Clock!");
    pvp.setLore(Lore);
    sword.setItemMeta(pvp);
    p.getInventory().setItem(8, sword);
    for (Player pl : Bukkit.getServer().getOnlinePlayers()) {
      if (pl != event.getPlayer()) {
        if (usingClock.contains(pl.getName())) {
          pl.hidePlayer(event.getPlayer());
        } else {
          pl.showPlayer(event.getPlayer());
        }
      }
    }
  }
  
  @EventHandler(priority=EventPriority.HIGHEST)
  public void onPlayerPlaceBlock(PlayerInteractEvent event)
  {
    Player player = event.getPlayer();
    if ((event.getAction() == Action.RIGHT_CLICK_AIR) || (event.getAction() == Action.RIGHT_CLICK_BLOCK)) {
      if ((disName(player.getItemInHand()) != null) && (disName(player.getItemInHand()).equalsIgnoreCase(ChatColor.GREEN + "Players " + ChatColor.YELLOW + ChatColor.BOLD + ">> " + ChatColor.RESET + ChatColor.GREEN + "Activated")))
      {
        usingClock.add(player.getName());
        ItemStack i = player.getItemInHand();
        ItemMeta i2 = i.getItemMeta();
        i2.setDisplayName(ChatColor.GREEN + "Players " + ChatColor.YELLOW + ChatColor.BOLD + ">> " + ChatColor.RESET + ChatColor.RED + "Deactivated");
        i.setItemMeta(i2);
        player.setItemInHand(i);
        for (Player p : Bukkit.getServer().getOnlinePlayers()) {
          if (p != player.getPlayer()) {
            player.hidePlayer(p);
          }
        }
      }
      else if ((disName(player.getItemInHand()) != null) && (disName(player.getItemInHand()).equalsIgnoreCase(ChatColor.GREEN + "Players " + ChatColor.YELLOW + ChatColor.BOLD + ">> " + ChatColor.RESET + ChatColor.RED + "Deactivated")))
      {
        usingClock.remove(player.getName());
        ItemStack i = player.getItemInHand();
        ItemMeta i2 = i.getItemMeta();
        for (Player p : Bukkit.getServer().getOnlinePlayers()) {
          try
          {
            player.showPlayer(p);
          }
          catch (NullPointerException localNullPointerException) {}
        }
        i2.setDisplayName(ChatColor.GREEN + "Players " + ChatColor.YELLOW + ChatColor.BOLD + ">> " + ChatColor.RESET + ChatColor.GREEN + "Activated");
        i.setItemMeta(i2);
        player.setItemInHand(i);
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
 * Qualified Name:     me.mike1665.funstuff.MagicClock
 * JD-Core Version:    0.7.0.1
 */