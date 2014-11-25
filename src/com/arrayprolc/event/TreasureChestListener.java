package com.arrayprolc.event;

import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;

import me.mike1665.Main.Main;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.ItemStack;

import com.arrayprolc.reward.Reward;
import com.arrayprolc.strings.MessageType;
import com.arrayprolc.strings.StringManager;

public class TreasureChestListener implements Listener {

	public static ArrayList<UUID> noPickup = new ArrayList<UUID>();
	public Main plugin;

	public TreasureChestListener(Main instance){
		plugin = instance;
	}
	
	@EventHandler
	public void onInteract(PlayerInteractEvent e){
		if(e.getAction().toString().contains("RIGHT")){
			if(e.getPlayer().getItemInHand().getType().equals(Material.TRAPPED_CHEST)){
				e.setCancelled(true);
				ItemStack i = e.getPlayer().getItemInHand();
				i.setAmount(i.getAmount() - 1);
				e.getPlayer().setItemInHand(i);
				Bukkit.broadcastMessage(StringManager.getMessage("§a§l" + e.getPlayer().getName() + " is opening a tresure chest!", MessageType.TREASURE));
				Reward[] rewards = { new Reward(e.getPlayer(), new ItemStack(Material.DIRT)), new Reward(e.getPlayer(), new ItemStack(Material.DIAMOND)), new Reward(e.getPlayer(), new ItemStack(Material.DIAMOND)), new Reward(e.getPlayer(), new ItemStack(Material.DIAMOND)), new Reward(e.getPlayer(), "say Console Test!", new ItemStack(Material.EMERALD))};
				TreasureChest tc = new TreasureChest(e.getPlayer(), pickFourRewards(rewards));
			}
		}
	}
	
	@EventHandler
	public void pickup(PlayerPickupItemEvent e){
		if(noPickup.contains(e.getItem().getUniqueId())){
			e.setCancelled(true);
		}
	}
	
	public static Reward[] pickFourRewards(Reward[] r){
		ArrayList<Reward> r3 = new ArrayList<Reward>();
		for(int i = 0; i < 4; i++){
			Reward rew = r[randInt(0, r.length)];
			r3.add(rew);
		}
		Reward[] r2 = r3.toArray(new Reward[r3.size()]);
		return r2;
	}
	static Random rand = new Random();
	public static int randInt(int min, int max) {
	    int randomNum = rand.nextInt((max - min) + 1) + min;

	    return randomNum;
	}
}
