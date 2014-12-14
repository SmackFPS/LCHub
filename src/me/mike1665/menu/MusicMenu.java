package me.mike1665.menu;

import java.util.ArrayList;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class MusicMenu
  implements Listener
{
  public static String name = "§7§nMusic Menu";
  
  public static ItemStack createItem(Material material, int amount, short shrt, String displayname, String lore)
  {
    ItemStack item = new ItemStack(material, amount, shrt);
    ItemMeta meta = item.getItemMeta();
    meta.setDisplayName(displayname);
    ArrayList<String> Lore = new ArrayList();
    Lore.add(lore);
    meta.setLore(Lore);
    
    item.setItemMeta(meta);
    return item;
  }
  
  public static Inventory musicmenu(Player p)
  {
    Inventory musicmenu = Bukkit.createInventory(null, 36, name);
    
    musicmenu.setItem(
      4, 
      createItem(Material.BEACON, 1, (short)0, "§cGo Back", 
      "§6Go back"));
    



    musicmenu.setItem(
      19, 
      createItem(Material.RECORD_3, 1, (short)0, "§b§lBlocks Disk", 
      ""));
    musicmenu.setItem(
      20, 
      createItem(Material.RECORD_4, 1, (short)0, "§b§lChirp Disk", 
      ""));
    musicmenu.setItem(
      21, 
      createItem(Material.RECORD_5, 1, (short)0, "§b§lFar Disk", 
      ""));
    musicmenu.setItem(
      22, 
      createItem(Material.RECORD_6, 1, (short)0, "§b§lMall Disk", 
      ""));
    musicmenu.setItem(
      23, 
      createItem(Material.RECORD_7, 1, (short)0, "§b§lMellohi Disk", 
      ""));
    musicmenu.setItem(
      24, 
      createItem(Material.RECORD_8, 1, (short)0, "§b§lStal Disk", 
      ""));
    musicmenu.setItem(
      25, 
      createItem(Material.RECORD_9, 1, (short)0, "§b§lStrad Disk", 
      ""));
    musicmenu.setItem(
      28, 
      createItem(Material.RECORD_10, 1, (short)0, "§b§lWard Disk", 
      ""));
    musicmenu.setItem(
      29, 
      createItem(Material.RECORD_11, 1, (short)0, "§b§l11 Disk", 
      ""));
    musicmenu.setItem(
      30, 
      createItem(Material.RECORD_12, 1, (short)0, "§b§lWait Disk", 
      ""));
    musicmenu.setItem(
      31, 
      createItem(Material.GOLD_RECORD, 1, (short)0, "§b§l13 Disk", 
      ""));
    musicmenu.setItem(
      32, 
      createItem(Material.GREEN_RECORD, 1, (short)0, "§b§lCat Disk", 
      ""));
    
    return musicmenu;
  }
}


/* Location:           A:\LC\Lobby\plugins\HubPlugin.jar
 * Qualified Name:     me.mike1665.menu.MusicMenu
 * JD-Core Version:    0.7.0.1
 */