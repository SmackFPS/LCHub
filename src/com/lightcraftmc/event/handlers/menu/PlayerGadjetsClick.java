package com.lightcraftmc.event.handlers.menu;

import me.mike1665.menu.GadjetsMenu;
import me.mike1665.menu.PlayerGadjets;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;

public class PlayerGadjetsClick
  implements Listener
{
  @EventHandler
  public void onClick(InventoryClickEvent event)
  {
    Player p = (Player)event.getWhoClicked();
    if (event.getInventory().getName().equalsIgnoreCase(PlayerGadjets.plrgadmenu.getName()))
    {
      event.setCancelled(true);
      if (event.getCurrentItem() == null) {
        return;
      }
      if (!event.getCurrentItem().hasItemMeta()) {
        return;
      }
      if (!event.getCurrentItem().getItemMeta().hasDisplayName()) {
        return;
      }
      if (!event.getCurrentItem().hasItemMeta()) {
        return;
      }
      if (event.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.GREEN + "Ender Ride"))
      {
        ItemStack ender = new ItemStack(Material.ENDER_PEARL, 1);
        ItemMeta e = ender.getItemMeta();
        e.setDisplayName(ChatColor.GREEN + "Ender Ride");
        ender.setItemMeta(e);
        p.getInventory().addItem(new ItemStack[] { ender });
        p.closeInventory();
      }
      if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§cMelon Block"))
      {
        ItemStack ender = new ItemStack(Material.MELON_BLOCK, 1);
        ItemMeta e = ender.getItemMeta();
        e.setDisplayName(ChatColor.RED + "Melon Block");
        ender.setItemMeta(e);
        p.getInventory().addItem(new ItemStack[] { ender });
        p.closeInventory();
      }
      if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§1Firework Launcher"))
      {
        ItemStack ender = new ItemStack(Material.FIREWORK, 64);
        ItemMeta e = ender.getItemMeta();
        e.setDisplayName(ChatColor.BLUE + "Firework Launcher");
        ender.setItemMeta(e);
        p.getInventory().addItem(new ItemStack[] { ender });
        p.closeInventory();
      }
      if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§cGo Back"))
      {
        p.openInventory(GadjetsMenu.gadmenu);
        p.playSound(p.getLocation(), Sound.DOOR_CLOSE, 10.0F, 10.0F);
      }
    }
  }
}


/* Location:           A:\LC\Lobby\plugins\HubPlugin.jar
 * Qualified Name:     me.mike1665.click.PlayerGadjetsClick
 * JD-Core Version:    0.7.0.1
 */