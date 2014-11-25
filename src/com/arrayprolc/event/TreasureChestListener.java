package com.arrayprolc.event;

import java.util.ArrayList;
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
				Reward[] rewards = { new Reward(e.getPlayer(), new ItemStack(Material.DIRT)), new Reward(e.getPlayer(), new ItemStack(Material.DIAMOND)), new Reward(e.getPlayer(), "say Console Test!", new ItemStack(Material.EMERALD))};
				TreasureChest tc = new TreasureChest(e.getPlayer(), rewards);
			}
		}
	}
	
	@EventHandler
	public void pickup(PlayerPickupItemEvent e){
		if(noPickup.contains(e.getItem().getUniqueId())){
			e.setCancelled(true);
		}
	}
}
