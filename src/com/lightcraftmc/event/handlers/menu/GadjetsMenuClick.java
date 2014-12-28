package com.lightcraftmc.event.handlers.menu;

import me.mike1665.menu.AdminGadgets;
import me.mike1665.menu.CosmeticsMenu;
import me.mike1665.menu.GadjetsMenu;
import me.mike1665.menu.PlayerGadjets;
import me.mike1665.menu.VipGadjets;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class GadjetsMenuClick
  implements Listener
{
  @EventHandler
  public void onClick(InventoryClickEvent event)
  {
    Player p = (Player)event.getWhoClicked();
    if (event.getInventory().getName().equalsIgnoreCase(GadjetsMenu.gadmenu.getName()))
    {
      event.setCancelled(true);
      if (event.getCurrentItem() == null) {
        return;
      }
      if (!event.getCurrentItem().hasItemMeta()) {
        return;
      }
      if (!event.getCurrentItem().hasItemMeta()) {
        return;
      }
      if (!event.getCurrentItem().hasItemMeta()) {
        return;
      }
      if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§6Vip Gadgets")) {
        if ((p.hasPermission("vip.gad")) || (p.isOp()))
        {
          p.openInventory(VipGadjets.vipgadmenu);
          p.playSound(p.getLocation(), Sound.DOOR_OPEN, 10.0F, 10.0F);
        }
        else
        {
          p.sendMessage(ChatColor.GRAY + "[" + ChatColor.YELLOW + "Gadgets" + ChatColor.GRAY + "] " + ChatColor.AQUA + "Please purchase VIP at lightcraftmc.com!");
          p.closeInventory();
        }
      }
      if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§4Admin Gadgets")) {
        if ((p.hasPermission("admin.gad")) || (p.isOp()))
        {
          p.openInventory(AdminGadgets.admingadmenu);
          p.playSound(p.getLocation(), Sound.DOOR_OPEN, 10.0F, 10.0F);
        }
        else
        {
          p.sendMessage(ChatColor.GRAY + "[" + ChatColor.YELLOW + "Gadgets" + ChatColor.GRAY + "] " + ChatColor.AQUA + "Apply for admin @ lightcraftmc.com!");
          p.closeInventory();
        }
      }
      if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§aRegular Gadgets"))
      {
        p.openInventory(PlayerGadjets.plrgadmenu);
        p.playSound(p.getLocation(), Sound.DOOR_OPEN, 10.0F, 10.0F);
      }
      if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§bBack to Hub Menu"))
      {
        p.closeInventory();
        p.openInventory(CosmeticsMenu.cosmenu((Player)event.getWhoClicked()));
        p.playSound(p.getLocation(), Sound.DOOR_CLOSE, 10.0F, 10.0F);
      }
    }
  }
}


/* Location:           A:\LC\Lobby\plugins\HubPlugin.jar
 * Qualified Name:     me.mike1665.click.GadjetsMenuClick
 * JD-Core Version:    0.7.0.1
 */