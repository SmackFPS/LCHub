package me.mike1665.funstuff;

import me.mike1665.Main.Main;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

public class DiscoBall
  implements Listener
{
  public Main plugin;
  
  public DiscoBall()
  {
    this.plugin = Main.getInstance().getInstance();
  }
  
  @EventHandler(priority=EventPriority.HIGHEST)
  public void onPlayerPlaceBlock(PlayerInteractEvent event)
  {
    Player player = event.getPlayer();
    if (((event.getAction() == Action.RIGHT_CLICK_AIR) || (event.getAction() == Action.RIGHT_CLICK_BLOCK)) && 
      (disName(player.getItemInHand()) != null) && (disName(player.getItemInHand()).equalsIgnoreCase(ChatColor.DARK_BLUE + "Disco Ball")))
    {
      spawnDisco(player);
      player.setItemInHand(null);
    }
  }
  
  public void spawnDisco(Player p)
  {
    final Location l = p.getEyeLocation();
    l.add(0.0D, 5.0D, 0.0D).getBlock().setType(Material.DIAMOND_BLOCK);
    


    p.sendMessage("Tried to run effect");
    new BukkitRunnable()
    {
      int count = 1;
      
      public void run()
      {
        l.getBlock().setType(Material.AIR);
        
        count -= 1;
        if (count == 0) {
          cancel();
        }
      }
    }.runTaskTimer(plugin, 800L, 1L);
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
 * Qualified Name:     me.mike1665.funstuff.DiscoBall
 * JD-Core Version:    0.7.0.1
 */