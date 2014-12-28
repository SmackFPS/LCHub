package com.lightcraftmc.event.handlers.menu;

import me.mike1665.menu.AdminGadgets;
import me.mike1665.menu.GadjetsMenu;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.entity.Sheep;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import com.lightcraftmc.hub.main.Main;

public class AdminGadgetsClick
  implements Listener
{
  private Main plugin;
  
  public AdminGadgetsClick()
  {
    plugin = Main.getInstance();
  }
  
  @EventHandler
  public void onClick(InventoryClickEvent event)
  {
    Player p = (Player)event.getWhoClicked();
    if (event.getInventory().getName().equalsIgnoreCase(AdminGadgets.admingadmenu.getName()))
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
      if (!event.getCurrentItem().getItemMeta().hasDisplayName()) {
        return;
      }
      if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§aTwerking Zombie"))
      {
        p.sendMessage(ChatColor.GRAY + "[" + ChatColor.YELLOW + "Gadgets" + ChatColor.GRAY + "] " + ChatColor.AQUA + "Coming soon!");
        p.closeInventory();
        return;
      }
      if (event.getCurrentItem().getItemMeta().getDisplayName().contains("Rainbow Sheep"))
      {
        p.sendMessage(ChatColor.GRAY + "[" + ChatColor.YELLOW + "Gadgets" + ChatColor.GRAY + "] " + ChatColor.AQUA + "Spawned a Rainbow Sheep");
        p.closeInventory();
        spawnSHEEP(p);
        return;
      }
      if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§1Wear Rainbow Armor"))
      {
        p.sendMessage(ChatColor.GRAY + "[" + ChatColor.YELLOW + "Gadgets" + ChatColor.GRAY + "] " + ChatColor.AQUA + "Rainbow Armor Active. To Turn off do - /caoff");
        p.closeInventory();
        return;
      }
      if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§cGo Back"))
      {
        p.openInventory(GadjetsMenu.gadmenu);
        p.playSound(p.getLocation(), Sound.DOOR_CLOSE, 10.0F, 10.0F);
        return;
      }
    }
  }
  
  public void spawnSHEEP(Player player)
  {
    final Sheep sheep = (Sheep)player.getWorld().spawn(player.getLocation(), Sheep.class);
    sheep.setCustomName(ChatColor.AQUA + "Swag Sheep");
    sheep.setColor(DyeColor.RED);
    Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, 
      new Runnable()
      {
        int num = 1;
        
        public void run()
        {
          if (num == 1)
          {
            sheep.setColor(DyeColor.RED);
            num += 1;
          }
          else if (num == 2)
          {
            sheep.setColor(DyeColor.ORANGE);
            num += 1;
          }
          else if (num == 3)
          {
            sheep.setColor(DyeColor.YELLOW);
            num += 1;
          }
          else if (num == 4)
          {
            sheep.setColor(DyeColor.LIME);
            num += 1;
          }
          else if (num == 5)
          {
            sheep.setColor(DyeColor.BLUE);
            num += 1;
          }
          else if (num == 6)
          {
            sheep.setColor(DyeColor.PURPLE);
            num += 1;
          }
          else if (num == 7)
          {
            sheep.setColor(DyeColor.MAGENTA);
            num = 1;
          }
        }
      }, 0L, 2L);
  }
}


/* Location:           A:\LC\Lobby\plugins\HubPlugin.jar
 * Qualified Name:     me.mike1665.click.AdminGadgetsClick
 * JD-Core Version:    0.7.0.1
 */