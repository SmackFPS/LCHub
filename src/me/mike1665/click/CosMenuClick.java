package me.mike1665.click;

import com.arrayprolc.rank.RankManager;
import com.arrayprolc.rank.ServerRank;
import com.arrayprolc.strings.MessageType;
import com.arrayprolc.strings.StringManager;

import me.mike1665.extra.ExtraManager;
import me.mike1665.menu.BuyGadgets;
import me.mike1665.menu.CosmeticsMenu;
import me.mike1665.menu.MountMenu;
import me.mike1665.menu.MusicMenu;
import me.mike1665.menu.ParticleMenu;

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
      if (event.getCurrentItem().getItemMeta().getDisplayName().equals("�aParticles"))
      {
        p.playSound(p.getLocation(), Sound.DOOR_OPEN, 10.0F, 10.0F);
        ParticleMenu.m.displayMenu(p);
        return;
      }
      if (event.getCurrentItem().getItemMeta().getDisplayName().equals("�cMounts"))
      {
        p.playSound(p.getLocation(), Sound.DOOR_OPEN, 10.0F, 10.0F);
        ExtraManager.Activate(p, me.mike1665.effects.EffectManager.EffectType.Helix);
        return;
      }
      if (event.getCurrentItem().getItemMeta().getDisplayName().equals("�8Hub Purchaseables"))
      {
        p.playSound(p.getLocation(), Sound.DOOR_OPEN, 10.0F, 10.0F);
        p.openInventory(BuyGadgets.buygadmenu(p));
        return;
      }
      if (event.getCurrentItem().getItemMeta().getDisplayName().equals("�6Pets"))
      {
        p.sendMessage(ChatColor.GRAY + "[" + ChatColor.LIGHT_PURPLE + "Hub" + ChatColor.GRAY + "] " + ChatColor.RED + "COMING SOON");
        p.playSound(p.getLocation(), Sound.DOOR_CLOSE, 10.0F, 10.0F);
        p.closeInventory();
        return;
      }
      if (event.getCurrentItem().getItemMeta().getDisplayName().equals("�3JukeBox"))
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
    }
  }
}


/* Location:           A:\LC\Lobby\plugins\HubPlugin.jar
 * Qualified Name:     me.mike1665.click.CosMenuClick
 * JD-Core Version:    0.7.0.1
 */