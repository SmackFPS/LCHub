package com.arrayprolc.reward;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.arrayprolc.item.ItemTools;

public class RewardList {

	public static Reward[] list(Player p){
		Reward[] rList = {
			new Reward(p, ItemTools.setName(new ItemStack(Material.TRAPPED_CHEST), "§d§lTreasure Chest §7 - Right Click", "§7§oReward-ception!")),
			new Reward(p, ItemTools.setName(new ItemStack(Material.TRAPPED_CHEST), "§d§lTreasure Chest §7 - Right Click", "§7§oReward-ception!")),
			
			
		};
		
		return rList;
	}
	
}
