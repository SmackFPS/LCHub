package me.mike1665.menu;

import com.arrayprolc.item.ItemTools;
import com.arrayprolc.rank.RankManager;
import com.arrayprolc.rank.ServerRank;
import java.util.ArrayList;
import me.mike1665.coinapi.LcCoinsAPI;
import me.mike1665.coinapi.LcTokensAPI;
import org.apache.commons.lang.WordUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class PlayerOptions
  implements Listener
{
  public static String name = "§8[§aP§8] §1Hub Menu";
  
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
  
  public static Inventory optionmenu(Player p)
  {
    int a = LcTokensAPI.balancePoints(p);
    int b = LcCoinsAPI.balancePoints(p);
    
    Inventory optionmenu = Bukkit.createInventory(null, 54, name);
    
    optionmenu.setItem(1, ItemTools.setName(new ItemStack(Material.DOUBLE_PLANT), "§5Economy", 
      new String[] {
      "", 
      "Your economy status:", 
      "", 
      "§d§lTokens - " + a, 
      "", 
      "§a§lCoins - " + b, 
      "", 
      "§cPurchae more on the website!" }));
    


    optionmenu.setItem(4, ItemTools.setName(new ItemStack(Material.BOOK), "§a§bStats", 
      new String[] {
      "", 
      "Your server status:", 
      "", 
      ChatColor.YELLOW+ "" + ChatColor.BOLD + ">>  " + ChatColor.GRAY + "Name: " + ChatColor.LIGHT_PURPLE + p.getName().toString(), 
      "", 
      ChatColor.YELLOW+ "" + ChatColor.BOLD + ">>  " + ChatColor.GRAY + "Time played: " + ChatColor.LIGHT_PURPLE + "Coming soon....", 
      "" }));
    


    optionmenu.setItem(7, ItemTools.setName(new ItemStack(Material.NETHER_STAR), "§eRank", 
      new String[] {
      "", 
      "§eYou current rank:", 
      "", 
      "§7§lRank - " + RankManager.getColor(RankManager.getRank(p)) + WordUtils.capitalize(RankManager.getRank(p).toString().toLowerCase()), 
      "", 
      "§cPurchase a rank on the website!" }));
    


    optionmenu.setItem(
      21, 
      createItem(Material.CHEST, 1, (short)0, "§aGadgets", 
      "§1Choose a fun Gadget to show off to everyone!"));
    optionmenu.setItem(
      19, 
      createItem(Material.IRON_BARDING, 1, (short)0, "§cMounts", 
      "§3Choose an eye-pooping mount!"));
    optionmenu.setItem(
      23, 
      createItem(Material.ENDER_PEARL, 1, (short)0, "§8Hub Purchaseables", 
      "§bPurchase some fun Gadgets!"));
    optionmenu.setItem(
      25, 
      createItem(Material.EGG, 1, (short)0, "§6Pets", 
      "§6Want a friend?"));
    optionmenu.setItem(
      40, 
      createItem(Material.RECORD_10, 1, (short)0, "§3JukeBox", 
      "§eNeed something entertaining?"));
    return optionmenu;
  }
}


/* Location:           A:\LC\Lobby\plugins\HubPlugin.jar
 * Qualified Name:     me.mike1665.menu.PlayerOptions
 * JD-Core Version:    0.7.0.1
 */