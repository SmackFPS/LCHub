package com.arrayprolc.reward;

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
			new Reward(p, ItemTools.setName(new ItemStack(Material.DOUBLE_PLANT), "§2Coin Bomb")),
			//FEEL FREE TO ADD REWARDS HERE
		};
		
		return rList;
	}
	
}
