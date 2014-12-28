package me.mike1665.click;

import com.arrayprolc.rank.RankManager;
import com.arrayprolc.rank.ServerRank;

import net.lightcraftmc.fusebox.util.strings.MessageType;
import net.lightcraftmc.fusebox.util.strings.StringManager;
import me.mike1665.extra.ExtraManager;
import me.mike1665.menu.BuyGadgets;
import me.mike1665.menu.CosmeticsMenu;
import me.mike1665.menu.MountMenu;
import me.mike1665.menu.MusicMenu;
import me.mike1665.menu.ParticleMenu;
import me.mike1665.menu.WardrobeMenu;
import me.mike1665.particle.CircleParticle;

import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class CosMenuClick
  implements Listener
{
  @EventHandler
  public void onClick(InventoryClickEvent event)
  {
    Player p = (Player)event.getWhoClicked();
    if (event.getInventory().getName().equalsIgnoreCase(CosmeticsMenu.name))
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
      if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§aParticles"))
      {
        p.playSound(p.getLocation(), Sound.DOOR_OPEN, 10.0F, 10.0F);
        net.lightcraft.particles.ParticleMenu.openMenu(p);
        return;
      }
      if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§cMounts"))
      {
        p.playSound(p.getLocation(), Sound.DOOR_OPEN, 10.0F, 10.0F);
        p.openInventory(MountMenu.getMountShop(p));
        return;
      }
      if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§8Gadgets"))
      {
        p.playSound(p.getLocation(), Sound.DOOR_OPEN, 10.0F, 10.0F);
        p.openInventory(BuyGadgets.buygadmenu(p));
        return;
      }
      if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§6Pets"))
      {
        p.sendMessage(ChatColor.GRAY + "[" + ChatColor.LIGHT_PURPLE + "Hub" + ChatColor.GRAY + "] " + ChatColor.RED + "COMING SOON");
        p.playSound(p.getLocation(), Sound.DOOR_CLOSE, 10.0F, 10.0F);
        p.closeInventory();
        return;
      }
      if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§3JukeBox"))
      {
        if (RankManager.hasRank(p, ServerRank.VIP))
        {
          p.playSound(p.getLocation(), Sound.DOOR_OPEN, 10.0F, 10.0F);
          p.openInventory(MusicMenu.musicmenu(p));
          return;
        }
        p.closeInventory();
        p.playSound(p.getLocation(), Sound.ANVIL_LAND, 10.0F, 10.0F);
        p.sendMessage(StringManager.getPrefix(MessageType.ERROR) + "You need to purchase VIP for this feature!");
      }
      
      if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§cWardobe"))
      {
        p.openInventory(WardrobeMenu.getWardrobeShop(p));
      }
    }
  }
}


/* Location:           A:\LC\Lobby\plugins\HubPlugin.jar
 * Qualified Name:     me.mike1665.click.CosMenuClick
 * JD-Core Version:    0.7.0.1
 */