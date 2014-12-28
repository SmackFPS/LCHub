package com.lightcraftmc.event.handlers.menu;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.lightcraftmc.menu.CosmeticsMenu;
import com.lightcraftmc.mount.MountManager;
import com.lightcraftmc.mount.mounts.AngelRider;
import com.lightcraftmc.mount.mounts.DarkRider;
import com.lightcraftmc.mount.mounts.GhostRider;
import com.lightcraftmc.mount.mounts.NyanRider;
import com.lightcraftmc.mount.mounts.PoseidonRider;

public class MountMenuClick
  implements Listener
{
  @EventHandler
  public void onClick(InventoryClickEvent event)
  {
    Player p = (Player)event.getWhoClicked();
    try
    {
      if (event.getInventory().getName().equalsIgnoreCase("§8[§aP§8] §1Mount Menu"))
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
        if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§b§lAngel Mount"))
        {
          if (!AngelRider.playAngelRider(p)) {}
          p.closeInventory();
          return;
        }
        if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§5§lNyan Mount"))
        {
          if (!NyanRider.playNyanRider(p)) {}
          p.closeInventory();
          return;
        }
        if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§4§lGhost Mount"))
        {
          if (!GhostRider.playGhostRider(p)) {}
          p.closeInventory();
          return;
        }
        if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§f§lDark Mount"))
        {
          if (!DarkRider.playDarkRider(p)) {}
          p.closeInventory();
          return;
        }
        if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§9§lPoseidon Mount"))
        {
          if (!PoseidonRider.playPoseidonRider(p)) {}
          p.closeInventory();
          return;
        }
        if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§4Remove Mount"))
        {
          MountManager.removeCurrentPet(p, false);
          p.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "Mounts" + ChatColor.RESET + ChatColor.DARK_GRAY + "> " + ChatColor.RED + "Mount removed.");
          return;
        }
        if (event.getCurrentItem().getItemMeta().getDisplayName().contains("Go Back"))
        {
          p.closeInventory();
          p.openInventory(CosmeticsMenu.cosmenu(p));
          return;
        }
      }
    }
    catch (Exception localException) {}
  }
}


/* Location:           A:\LC\Lobby\plugins\HubPlugin.jar
 * Qualified Name:     com.lightcraftmc.click.MountMenuClick
 * JD-Core Version:    0.7.0.1
 */