package com.arrayprolc.reward;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.arrayprolc.item.ItemTools;

public class RewardList {

	public static Reward[] list(Player p){
		Reward[] rList = {
			new Reward(p, ItemTools.setName(new ItemStack(Material.TRAPPED_CHEST), "§d§lTreasure Chest §7 - Right Click", "§7§oReward-ception!")),
			new Reward(p,"g-reward " + p.getName() + " coin 1000", ItemTools.setName(new ItemStack(Material.GOLD_NUGGET), "§9+1000 Coins!")),
			new Reward(p,"g-reward " + p.getName() + " token 30", ItemTools.setName(new ItemStack(Material.BRICK), "§9+30 Tokens!")),
			new Reward(p, ItemTools.setName(new ItemStack(Material.DOUBLE_PLANT, 1), "§2Coin Bomb")),
			new Reward(p, ItemTools.setName(new ItemStack(Material.SNOW_BALL, 16), ChatColor.GREEN + "MeowBall")),
			new Reward(p, ItemTools.setName(new ItemStack(Material.ENDER_PEARL, 16), ChatColor.DARK_AQUA + "EnderDoge")),
			new Reward(p, ItemTools.setName(new ItemStack(Material.TRIPWIRE_HOOK, 5), ChatColor.DARK_BLUE + "Entity Hook")),
			new Reward(p, ItemTools.setName(new ItemStack(Material.FIREWORK, 32), ChatColor.DARK_BLUE + "Firework Launcher")),
			new Reward(p, ItemTools.setName(new ItemStack(Material.TNT, 10), ChatColor.RED + "Fun Bomb")),
			new Reward(p, ItemTools.setName(new ItemStack(Material.ENDER_PEARL, 10), ChatColor.GREEN + "Ender Ride")),

		};
		
		return rList;
	}
	
}
