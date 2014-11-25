package com.arrayprolc.reward;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.arrayprolc.item.ItemTools;

public class RewardList {

	public static Reward[] list(Player p){
		Reward[] rList = {
			new Reward(p, ItemTools.setName(new ItemStack(Material.TRAPPED_CHEST), "§d§lTreasure Chest §7 - Right Click", "§7§oReward-ception!")),
			new Reward(p, ItemTools.setName(new ItemStack(Material.GOLD_NUGGET), "§9+150 Coins!")),
			new Reward(p, ItemTools.setName(new ItemStack(Material.GOLD_NUGGET), "g-reward " + p.getName() + " coin 200", "§9+200 Coins!")),
			new Reward(p, ItemTools.setName(new ItemStack(Material.GOLD_NUGGET), "g-reward " + p.getName() + " coin 50",  "§9+50 Coins!")),
			new Reward(p, ItemTools.setName(new ItemStack(Material.GOLD_NUGGET), "g-reward " + p.getName() + " coin 150",  "§9+150 Coins!")),
			new Reward(p, ItemTools.setName(new ItemStack(Material.GOLD_NUGGET), "g-reward " + p.getName() + " coin 200",  "§9+200 Coins!")),
			new Reward(p, ItemTools.setName(new ItemStack(Material.GOLD_NUGGET), "g-reward " + p.getName() + " coin 50",  "§9+50 Coins!")),
			new Reward(p, ItemTools.setName(new ItemStack(Material.GOLD_NUGGET), "g-reward " + p.getName() + " token 10",  "§9+10 Tokens!")),
			new Reward(p, ItemTools.setName(new ItemStack(Material.DOUBLE_PLANT), "§2Coin Bomb")),
			//FEEL FREE TO ADD REWARDS HERE
			
		};
		
		return rList;
	}
	
}
