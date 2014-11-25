package com.arrayprolc.reward;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Reward {

	public Player p;
	public ItemStack i;
	public String consoleCommand;
	public boolean isItemStack;
	public ItemStack displayItem;

	public Reward(Player pl, ItemStack item){ p = pl; this.i = item; isItemStack = true; displayItem = item; }

	public Reward(Player pl, String consoleCommand, ItemStack displayItem){ p = pl; this.consoleCommand = consoleCommand; isItemStack = false; this.displayItem = displayItem; }
	
	public void giveReward(){
		if(isItemStack){
			p.getInventory().addItem(i);
			return;
		}
		Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), consoleCommand);
	}



}
