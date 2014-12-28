package com.lightcraftmc.event.handlers.menu;

import me.mike1665.ammo.BatBlasterAmmoManager;
import me.mike1665.ammo.EnderDogeAmmoManager;
import me.mike1665.ammo.FireWorksAmmoManager;
import me.mike1665.ammo.FunCreeperAmmoManager;
import me.mike1665.ammo.GadgetAmmo;
import me.mike1665.ammo.KittyCannonAmmoManager;
import me.mike1665.ammo.MeowAmmoManager;
import me.mike1665.coinapi.ApiEvent;
import me.mike1665.coinapi.LcCoinsAPI;
import me.mike1665.coinapi.LcTokensAPI;
import me.mike1665.menu.BuyGadgets;
import me.mike1665.menu.CosmeticsMenu;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.lightcraftmc.hub.gadgets.BowTeleport;
import com.lightcraftmc.hub.gadgets.PvPSword;

public class BuyGadgetsClick
  implements Listener
{
  @EventHandler
  public void onClick(InventoryClickEvent event)
  {
    try
    {
      Player p = (Player)event.getWhoClicked();
      if (event.getInventory().getName().equalsIgnoreCase(BuyGadgets.name))
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
        PvPSword.pvp.remove(p.getUniqueId());
        if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§5Meow Balls")) {
          if ((LcCoinsAPI.hasEnough(p, 500)) && (MeowAmmoManager.balaceMeowAmmo(p) <= 1))
          {
            LcCoinsAPI.takePoints(p, 500);
            MeowAmmoManager.giveMeowAmmo(p, 50);
            ItemStack snow = new ItemStack(Material.BLAZE_ROD, 1);
            ItemMeta sno = snow.getItemMeta();
            sno.setDisplayName(ChatColor.GREEN + "MeowBall " + ChatColor.DARK_RED + MeowAmmoManager.balaceMeowAmmo(p));
            snow.setItemMeta(sno);
            p.getInventory().setItem(2, snow);
            ApiEvent.updatescore(p);
            p.closeInventory();
          }
          else if (MeowAmmoManager.balaceMeowAmmo(p) > 1)
          {
            ItemStack snow = new ItemStack(Material.BLAZE_ROD, 1);
            ItemMeta sno = snow.getItemMeta();
            sno.setDisplayName(ChatColor.GREEN + "MeowBall " + ChatColor.DARK_RED + MeowAmmoManager.balaceMeowAmmo(p));
            snow.setItemMeta(sno);
            if (p.getInventory().contains(Material.FIREWORK)) {
              p.getInventory().addItem(new ItemStack[] { snow });
            } else {
              p.getInventory().setItem(2, snow);
            }
            p.closeInventory();
          }
          else
          {
            p.sendMessage(ChatColor.RED + "You don't have enough money!");
          }
        }
        if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§9Ender Doge")) {
          if ((LcCoinsAPI.hasEnough(p, 500)) && (EnderDogeAmmoManager.balaceEnderDogeAmmo(p) <= 1))
          {
            LcCoinsAPI.takePoints(p, 500);
            EnderDogeAmmoManager.giveEnderDogeAmmo(p, 50);
            ItemStack snow = new ItemStack(Material.FIREWORK_CHARGE, 1);
            ItemMeta sno = snow.getItemMeta();
            sno.setDisplayName(ChatColor.DARK_AQUA + "EnderDoge " + ChatColor.DARK_RED + EnderDogeAmmoManager.balaceEnderDogeAmmo(p));
            snow.setItemMeta(sno);
            if (p.getInventory().contains(Material.FIREWORK)) {
              p.getInventory().addItem(new ItemStack[] { snow });
            } else {
              p.getInventory().setItem(2, snow);
            }
            ApiEvent.updatescore(p);
            p.closeInventory();
          }
          else if (EnderDogeAmmoManager.balaceEnderDogeAmmo(p) > 1)
          {
            ItemStack snow = new ItemStack(Material.FIREWORK_CHARGE, 1);
            ItemMeta sno = snow.getItemMeta();
            sno.setDisplayName(ChatColor.DARK_AQUA + "EnderDoge " + ChatColor.DARK_RED + EnderDogeAmmoManager.balaceEnderDogeAmmo(p));
            snow.setItemMeta(sno);
            if (p.getInventory().contains(Material.FIREWORK)) {
              p.getInventory().addItem(new ItemStack[] { snow });
            } else {
              p.getInventory().setItem(2, snow);
            }
            p.closeInventory();
          }
          else
          {
            p.sendMessage(ChatColor.RED + "You don't have enough money!");
          }
        }
        if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§5Fireworks")) {
          if ((LcCoinsAPI.hasEnough(p, 800)) && (FireWorksAmmoManager.balaceFireWorkAmmo(p) <= 1))
          {
            LcCoinsAPI.takePoints(p, 800);
            FireWorksAmmoManager.giveFireWorkAmmo(p, 50);
            ItemStack snow = new ItemStack(Material.FIREWORK, 1);
            ItemMeta sno = snow.getItemMeta();
            sno.setDisplayName(ChatColor.RED + "Fireworks " + ChatColor.DARK_RED + FireWorksAmmoManager.balaceFireWorkAmmo(p));
            snow.setItemMeta(sno);
            if (p.getInventory().contains(Material.FIREWORK)) {
              p.getInventory().addItem(new ItemStack[] { snow });
            } else {
              p.getInventory().setItem(2, snow);
            }
            ApiEvent.updatescore(p);
            p.closeInventory();
          }
          else if (FireWorksAmmoManager.balaceFireWorkAmmo(p) > 1)
          {
            ItemStack snow = new ItemStack(Material.FIREWORK, 1);
            ItemMeta sno = snow.getItemMeta();
            sno.setDisplayName(ChatColor.RED + "Fireworks " + ChatColor.DARK_RED + FireWorksAmmoManager.balaceFireWorkAmmo(p));
            snow.setItemMeta(sno);
            p.getInventory().setItem(2, snow);
            p.closeInventory();
          }
          else
          {
            p.sendMessage(ChatColor.RED + "You don't have enough money!");
          }
        }
        if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§aFun Creepers")) {
          if ((LcTokensAPI.hasEnough(p, 300)) && (FunCreeperAmmoManager.balaceCreeperAmmo(p) <= 1))
          {
            LcTokensAPI.takePoints(p, 300);
            FunCreeperAmmoManager.giveCreeperAmmo(p, 10);
            ItemStack snow = new ItemStack(Material.FIREWORK_CHARGE, 1);
            ItemMeta sno = snow.getItemMeta();
            sno.setDisplayName(ChatColor.AQUA + "Fun Creeper " + ChatColor.DARK_RED + FunCreeperAmmoManager.balaceCreeperAmmo(p));
            snow.setItemMeta(sno);
            p.getInventory().setItem(2, snow);
            ApiEvent.updatescore(p);
            p.closeInventory();
          }
          else if (FunCreeperAmmoManager.balaceCreeperAmmo(p) > 1)
          {
            ItemStack snow = new ItemStack(Material.FIREWORK_CHARGE, 1);
            ItemMeta sno = snow.getItemMeta();
            sno.setDisplayName(ChatColor.AQUA + "Fun Creeper " + ChatColor.DARK_RED + FunCreeperAmmoManager.balaceCreeperAmmo(p));
            snow.setItemMeta(sno);
            p.getInventory().setItem(2, snow);
            p.closeInventory();
          }
          else
          {
            p.sendMessage(ChatColor.RED + "You don't have enough money!");
          }
        }
        if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§5Bat Blaster")) {
          if ((LcCoinsAPI.hasEnough(p, 800)) && (BatBlasterAmmoManager.balaceBatAmmo(p) <= 1))
          {
            LcCoinsAPI.takePoints(p, 800);
            BatBlasterAmmoManager.giveBatAmmo(p, 50);
            ItemStack snow = new ItemStack(Material.IRON_BARDING, 1);
            ItemMeta sno = snow.getItemMeta();
            sno.setDisplayName(ChatColor.DARK_GRAY + "Bat Blaster " + ChatColor.DARK_RED + BatBlasterAmmoManager.balaceBatAmmo(p));
            snow.setItemMeta(sno);
            p.getInventory().setItem(2, snow);
            ApiEvent.updatescore(p);
            p.closeInventory();
          }
          else if (BatBlasterAmmoManager.balaceBatAmmo(p) > 1)
          {
            ItemStack snow = new ItemStack(Material.IRON_BARDING, 1);
            ItemMeta sno = snow.getItemMeta();
            sno.setDisplayName(ChatColor.DARK_GRAY + "Bat Blaster " + ChatColor.DARK_RED + BatBlasterAmmoManager.balaceBatAmmo(p));
            snow.setItemMeta(sno);
            p.getInventory().setItem(2, snow);
            p.closeInventory();
          }
          else
          {
            p.sendMessage(ChatColor.RED + "You don't have enough money!");
          }
        }
        if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§dPaintball Gun"))
        {
          p.sendMessage("In dev.");
          p.closeInventory();
        }
        if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§bKitty Cannon")) {
          if ((LcTokensAPI.hasEnough(p, 100)) && (KittyCannonAmmoManager.balaceCatAmmo(p) <= 1))
          {
            LcTokensAPI.takePoints(p, 100);
            KittyCannonAmmoManager.giveCatAmmo(p, 50);
            ItemStack snow = new ItemStack(Material.STICK, 1);
            ItemMeta sno = snow.getItemMeta();
            sno.setDisplayName(ChatColor.GOLD + "Kitty Cannon " + ChatColor.DARK_RED + KittyCannonAmmoManager.balaceCatAmmo(p));
            snow.setItemMeta(sno);
            p.getInventory().setItem(2, snow);
            ApiEvent.updatescore(p);
            p.closeInventory();
          }
          else if (KittyCannonAmmoManager.balaceCatAmmo(p) > 1)
          {
            ItemStack snow = new ItemStack(Material.STICK, 1);
            ItemMeta sno = snow.getItemMeta();
            sno.setDisplayName(ChatColor.GOLD + "Kitty Cannon " + ChatColor.DARK_RED + KittyCannonAmmoManager.balaceCatAmmo(p));
            snow.setItemMeta(sno);
            p.getInventory().setItem(2, snow);
            p.closeInventory();
          }
          else
          {
            p.sendMessage(ChatColor.RED + "You don't have enough money!");
          }
        }
        
        if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§aPig Quake Gun")) {
            if ((LcTokensAPI.hasEnough(p, 200)) && (GadgetAmmo.balanceGadgetAmo(p, "QuakeGunAmmo") <= 1))
            {
              LcTokensAPI.takePoints(p, 200);
              GadgetAmmo.addGadgetAmo(p, "QuakeGunAmmo", 50);
              ItemStack snow = new ItemStack(Material.DIAMOND_HOE, 1);
              ItemMeta sno = snow.getItemMeta();
              sno.setDisplayName(ChatColor.GREEN + "QuakeGun " + ChatColor.DARK_RED + GadgetAmmo.balanceGadgetAmo(p, "QuakeGunAmmo"));
              snow.setItemMeta(sno);
              p.getInventory().setItem(2, snow);
              ApiEvent.updatescore(p);
              p.closeInventory();
            }
            else if (GadgetAmmo.balanceGadgetAmo(p, "QuakeGunAmmo") > 1)
            {
              ItemStack snow = new ItemStack(Material.DIAMOND_HOE, 1);
              ItemMeta sno = snow.getItemMeta();
              sno.setDisplayName(ChatColor.GREEN + "QuakeGun " + ChatColor.DARK_RED + GadgetAmmo.balanceGadgetAmo(p, "QuakeGunAmmo"));
              snow.setItemMeta(sno);
              p.getInventory().setItem(2, snow);
              p.closeInventory();
            }
            else
            {
              p.sendMessage(ChatColor.RED + "You don't have enough money!");
            }
          }
        if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§2Coin Bomb")) {
          if (LcCoinsAPI.hasEnough(p, 300) && (GadgetAmmo.balanceGadgetAmo(p, "CoinBomb") <= 1))
          {
            LcCoinsAPI.takePoints(p, 300);
            GadgetAmmo.addGadgetAmo(p, "CoinBomb", 1);
            ItemStack ender = new ItemStack(Material.DOUBLE_PLANT, 1);
            ItemMeta e = ender.getItemMeta();
            e.setDisplayName(ChatColor.DARK_GREEN + "Coin Bomb");
            ender.setItemMeta(e);
            p.getInventory().setItem(2, ender);
            ApiEvent.updatescore(p);
            p.closeInventory();
          }
          else if (GadgetAmmo.balanceGadgetAmo(p, "CoinBomb") > 1) {
              ItemStack snow = new ItemStack(Material.DOUBLE_PLANT, 1);
              ItemMeta sno = snow.getItemMeta();
              sno.setDisplayName(ChatColor.DARK_GREEN + "Coin Bomb");
              snow.setItemMeta(sno);
              p.getInventory().setItem(2, snow);
              p.closeInventory();
          	} 
          else
          {
            p.sendMessage(ChatColor.RED + "You don't have enough money!");
          }
          }
        if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§dToken Bomb")) {
            if (LcTokensAPI.hasEnough(p, 200) && (GadgetAmmo.balanceGadgetAmo(p, "TokenBomb") <= 1))
            {
              LcCoinsAPI.takePoints(p, 200);
              GadgetAmmo.addGadgetAmo(p, "TokenBomb", 1);
              ItemStack ender = new ItemStack(Material.NETHER_STAR, 1);
              ItemMeta e = ender.getItemMeta();
              e.setDisplayName(ChatColor.LIGHT_PURPLE + "Token Bomb");
              ender.setItemMeta(e);
              p.getInventory().setItem(2, ender);
              ApiEvent.updatescore(p);
              p.closeInventory();
            }
            else if (GadgetAmmo.balanceGadgetAmo(p, "TokenBomb") > 1) {
                ItemStack snow = new ItemStack(Material.NETHER_STAR, 1);
                ItemMeta sno = snow.getItemMeta();
                sno.setDisplayName(ChatColor.LIGHT_PURPLE + "Token Bomb");
                snow.setItemMeta(sno);
                p.getInventory().setItem(2, snow);
                p.closeInventory();
            	} 
            else
            {
              p.sendMessage(ChatColor.RED + "You don't have enough money!");
            }
            }
        if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§d§lTreasure chest")) {
          if (LcTokensAPI.hasEnough(p, 500))
          {
            LcTokensAPI.takePoints(p, 500);
            ItemStack ender = new ItemStack(Material.TRAPPED_CHEST, 1);
            ItemMeta e = ender.getItemMeta();
            e.setDisplayName(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "Treasure Chest");
            ender.setItemMeta(e);
            if (p.getInventory().contains(Material.TRAPPED_CHEST)) {
              p.getInventory().addItem(new ItemStack[] { ender });
            } else {
              p.getInventory().setItem(2, ender);
            }
            p.closeInventory();
          }
          else
          {
            p.sendMessage(ChatColor.RED + "You don't have enough money!");
          }
        }
        if (event.getCurrentItem().getItemMeta().getDisplayName().contains("Sword")) {
          PvPSword.giveSword(p);
        }
        if (event.getCurrentItem().getItemMeta().getDisplayName().contains("Bow")) {
          BowTeleport.giveBow(p);
        }
        if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§cGo Back"))
        {
          p.openInventory(CosmeticsMenu.cosmenu(p));
          p.playSound(p.getLocation(), Sound.DOOR_CLOSE, 10.0F, 10.0F);
          return;
        }
        if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§4§lRemove Current Gadget"))
        {
          p.getInventory().setItem(2, null);
          p.playSound(p.getLocation(), Sound.ANVIL_LAND, 10.0F, 10.0F);
          return;
        }
      }
    }
    catch (Exception localException) {}
  }
}


/* Location:           A:\LC\Lobby\plugins\HubPlugin.jar
 * Qualified Name:     me.mike1665.click.BuyGadgetsClick
 * JD-Core Version:    0.7.0.1
 */