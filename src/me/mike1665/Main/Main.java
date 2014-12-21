package me.mike1665.Main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.UUID;

import me.jrl1004.lightcraft.utils.JRLEventSetup;
import me.mike1665.ammo.AmmoManager;
import me.mike1665.block.BlockRestore;
import me.mike1665.chest.TreasureChestManager;
import me.mike1665.click.AdminGadgetsClick;
import me.mike1665.click.BuyGadgetsClick;
import me.mike1665.click.CosMenuClick;
import me.mike1665.click.GadjetsMenuClick;
import me.mike1665.click.MountMenuClick;
import me.mike1665.click.MusicClick;
import me.mike1665.click.PlayerGadjetsClick;
import me.mike1665.click.VipGadjetsClick;
import me.mike1665.coinapi.ApiEvent;
import me.mike1665.coinapi.LcCoinsAPI;
import me.mike1665.coinapi.LcTokensAPI;
import me.mike1665.commands.AmmoTest;
import me.mike1665.commands.GiveAmmo;
import me.mike1665.commands.MountUnlocked;
import me.mike1665.commands.StatsCommand;
import me.mike1665.commands.UnlockAllArmor;
import me.mike1665.effects.EffectManager;
import me.mike1665.eventhandlers.BatBlaster;
import me.mike1665.eventhandlers.BuyEnderDoge;
import me.mike1665.eventhandlers.BuyMeowBalls;
import me.mike1665.eventhandlers.CatWorks;
import me.mike1665.eventhandlers.CoinBomb;
import me.mike1665.eventhandlers.EnderDoge;
import me.mike1665.eventhandlers.EnderRide;
import me.mike1665.eventhandlers.EntityHook;
import me.mike1665.eventhandlers.FireworkLauncher;
import me.mike1665.eventhandlers.MelonBlock;
import me.mike1665.eventhandlers.MeowBall;
import me.mike1665.eventhandlers.MikeEventSetup;
import me.mike1665.eventhandlers.PaintballGun;
import me.mike1665.eventhandlers.PixlBomb;
import me.mike1665.eventhandlers.RespawnEvent;
import me.mike1665.eventhandlers.TNTFun;
import me.mike1665.funstuff.SpawnCreeper;
import me.mike1665.gadgets.FunCreepers;
import me.mike1665.gadgets.GagdetManager;
import me.mike1665.hubstuff.DoubleJump;
import me.mike1665.hubstuff.LaunchPad;
import me.mike1665.hubstuff.NoHunger;
import me.mike1665.menu.AdminGadgets;
import me.mike1665.menu.BuyGadgets;
import me.mike1665.menu.CosmeticsMenu;
import me.mike1665.menu.GadjetsMenu;
import me.mike1665.menu.MountMenu;
import me.mike1665.menu.MusicMenu;
import me.mike1665.menu.PlayerGadjets;
import me.mike1665.menu.VipGadjets;
import me.mike1665.menu.WardrobeMenu;
import me.mike1665.mount.MountManager;
import me.mike1665.parkour.CourseOne;
import me.mike1665.update.Updater;
import me.mike1665.utils.UtilEnt;
import me.mike1665.utils.UtilServer;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.PluginMessageListener;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.util.Vector;

import com.arrayprolc.bungeehook.BungeeHooks;
import com.arrayprolc.bungeehook.PartyHooks;
import com.arrayprolc.bungeehook.Servers;
import com.arrayprolc.command.ArrayCommandHandler;
import com.arrayprolc.event.ArrayEventSetup;
import com.arrayprolc.event.ColouredWardrobe;
import com.arrayprolc.event.WardrobeType;
import com.arrayprolc.rank.RankManager;
import com.arrayprolc.strings.MessageType;
import com.arrayprolc.strings.StringManager;
import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

public class Main extends JavaPlugin implements Listener, PluginMessageListener {

	public Scoreboard board;
	public static Location _spawn;
	String tag = ChatColor.RED + "" + ChatColor.BOLD + "Parkour " + ChatColor.RESET + "" + ChatColor.DARK_GRAY + "> ";
	public static Main instance;
	public static Main schedule;
	protected HashMap<UUID, Vector> velocities;
	protected HashMap<UUID, Location> positions;
	protected HashMap<UUID, Boolean> onGround;
	public ApiEvent ae = new ApiEvent();

	public void onEnable() {

		PluginManager pm = getServer().getPluginManager();
		instance = this;
		schedule = this;
	    Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this, new Updater(this), 1L, 1L);
		ArrayEventSetup.setupEvents();
		ArrayCommandHandler.setup();
		loadListeners();
		JRLEventSetup.setupEvents();
		MikeEventSetup.setupEvents();
		try{
			bungee();
		}catch(Exception e){ e.printStackTrace(); 
		}
	}
	
	  public void onDisable(){
		  for (Player players : UtilServer.getPlayers()) {
			  EffectManager.removeEffect(players, false);
			  MountManager.removeCurrentPet(players, false);
			  
		      if (TreasureChestManager.isInTreasureChest(players))
		      {
		        Iterator localIterator2;
		        for (Iterator localIterator1 = UtilEnt.flyingEntities.keySet().iterator(); localIterator1.hasNext(); 
		          localIterator2.hasNext())
		        {
		          Player p = (Player)localIterator1.next();
		          ArrayList entList = (ArrayList)UtilEnt.flyingEntities.get(p);
		          localIterator2 = entList.iterator(); 
		          Entity e = (Entity) localIterator2.next();
		          if (e.isValid()) {
		            e.remove();
		          }

		        }

		        BlockRestore.restore(players);
		        TreasureChestManager.chest.remove(players.getUniqueId());
		        TreasureChestManager.treasureChest.remove(players.getUniqueId());
		        TreasureChestManager.playerpos.remove(players);
		      }
		  }
	  }

	@SuppressWarnings("deprecation")
	public void bungee(){

		Bukkit.getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
		Bukkit.getServer().getMessenger().registerIncomingPluginChannel(this, "BungeeCord", this);
		System.out.println("Initializing Bungee Hooks");
		BungeeHooks.players.put("lobby", Bukkit.getOnlinePlayers().length);

	}
	@SuppressWarnings("deprecation")
	public static void requestPlayerList(){
		if(Bukkit.getOnlinePlayers().length > 0){
		for(String srv : Servers.servers){
			ByteArrayDataOutput out = ByteStreams.newDataOutput();
			out.writeUTF("PlayerList");  
			out.writeUTF(srv);
			Bukkit.getOnlinePlayers()[0].sendPluginMessage(Bukkit.getServer().getPluginManager().getPlugin("HubPlugin"), "BungeeCord", out.toByteArray());
		}
		}
	}

	@SuppressWarnings("unused")
	@Override
	public void onPluginMessageReceived(String channel, Player player, byte[] message) {
		try{
		ByteArrayDataInput in = ByteStreams.newDataInput(message);
		if (!channel.equals("BungeeCord")) {
			return;
		}
		String build = "";
		if(!in.readUTF().equalsIgnoreCase("PlayerList")) return;
		String server = in.readUTF();
		String builder = in.readUTF();
		boolean removeOne = false;
		for(String s : builder.split(", ")){
			if(s.equalsIgnoreCase("")){
				removeOne = true;
			}
		}
		int r = builder.split(", ").length;
		BungeeHooks.players.remove(server);
		if(removeOne) r = r - 1;
		BungeeHooks.players.put(server, r);
		}catch(Exception ex){
		}
	} 

	private void loadListeners() {
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(this, this);
		AdminGadgets.init();
		GadjetsMenu.init();
		PlayerGadjets.init();
		VipGadjets.init();
		GagdetManager.registerEvents();
		AmmoManager.registerEvents();
		pm.registerEvents(new BuyGadgets(), this);
		pm.registerEvents(new EnderDoge(), this);
		pm.registerEvents(new CatWorks(this), this);
		pm.registerEvents(new MeowBall(), this);
		pm.registerEvents(new VipGadjetsClick(), this);
		pm.registerEvents(new VipGadjets(), this);
		pm.registerEvents(new GadjetsMenuClick(), this);
		pm.registerEvents(new GadjetsMenu(), this);
		pm.registerEvents(new PlayerGadjetsClick(), this);
		pm.registerEvents(new PlayerGadjets(), this);
		pm.registerEvents(new AdminGadgetsClick(this), this);
		pm.registerEvents(new AdminGadgets(), this);
		pm.registerEvents(new EnderRide(), this);
		pm.registerEvents(new MelonBlock(), this);
		pm.registerEvents(new EntityHook(), this);
		pm.registerEvents(new RespawnEvent(), this);
		pm.registerEvents(new TNTFun(this), this);
		pm.registerEvents(new FireworkLauncher(), this);
		pm.registerEvents(new PaintballGun(this), this);
		pm.registerEvents(new BatBlaster(), this);
		pm.registerEvents(new ApiEvent(), this);
		pm.registerEvents(new LcTokensAPI(), this);
		pm.registerEvents(new CoinBomb(this), this);
		pm.registerEvents(new LcCoinsAPI(), this);
		pm.registerEvents(new CosmeticsMenu(), this);
		pm.registerEvents(new CosMenuClick(), this);
		pm.registerEvents(new CourseOne(), this);
		pm.registerEvents(new MountMenuClick(), this);
		pm.registerEvents(new DoubleJump(), this);
		pm.registerEvents(new NoHunger(), this);
		pm.registerEvents(new LaunchPad(), this);
		pm.registerEvents(new BuyMeowBalls(), this);
		pm.registerEvents(new BuyGadgetsClick(), this);
		pm.registerEvents(new BuyGadgets(), this);
		pm.registerEvents(new BuyEnderDoge(), this);
		pm.registerEvents(new PixlBomb(this), this);
		pm.registerEvents(new SpawnCreeper(this), this);
		pm.registerEvents(new FunCreepers(), this);
		pm.registerEvents(new MusicMenu(), this);
		pm.registerEvents(new MusicClick(), this);
	}
	public void saveFile() {
		this.saveConfig();
	}

	@SuppressWarnings("deprecation")
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] a) {

		if (ArrayCommandHandler.command(sender, cmd, label, a))
			return true;
		if (StatsCommand.command(sender, cmd, label, a))
			return true;
		if (MountUnlocked.onCommand(sender, cmd, label, a))
			return true;
		if (AmmoTest.onCommand(sender, cmd, label, a))
			return true;
		if (GiveAmmo.onCommand(sender, cmd, label, a))
			return true;
		if (UnlockAllArmor.onCommand(sender, cmd, label, a))
			return true;
		
		if(!(sender instanceof Player)) return false;
		Player player = (Player) sender;
		if (cmd.getName().equalsIgnoreCase("gadgets")) {
			player.openInventory(me.mike1665.menu.GadjetsMenu.gadmenu);
		}
		if (cmd.getName().equalsIgnoreCase("cosmenu")) {
			player.openInventory(me.mike1665.menu.CosmeticsMenu.cosmenu((Player) sender));
		}
		if (cmd.getName().equalsIgnoreCase("mountmenu")) {
			player.openInventory(MountMenu.getMountShop(player));
		}
		
		if (cmd.getName().equalsIgnoreCase("gadmenu")) {
			player.openInventory(BuyGadgets.buygadmenu(player));
		}
		
		if (cmd.getName().equalsIgnoreCase("wardrobe")) {
			player.openInventory(WardrobeMenu.getWardrobeShop(player));
		}
		
		if (cmd.getName().equalsIgnoreCase("colorhelm")) {
			try{
			ColouredWardrobe.openColorWardrobe(player, WardrobeType.CHESTPLATE);
			}
			catch(Exception ex){
				
			}
		}

		if (cmd.getName().equalsIgnoreCase("parkour")) {
			player.sendMessage(ChatColor.RED + "          :" + ChatColor.RED + " Parkour Version 1" + ChatColor.RED + " :");
			player.sendMessage(ChatColor.RED + "          :" + ChatColor.GREEN + " Developed by @SwaggyYolo " + ChatColor.RED + ":");
			player.sendMessage(tag + ChatColor.YELLOW + " /setparkour1 - Sets the spawn for Parkour 1. ");
			player.sendMessage(tag + ChatColor.YELLOW + " /parkour1 - Teleport to the set point. ");
			player.sendMessage(tag + ChatColor.YELLOW + " /cp1 - Sets Checkpoint for Course 1. ");

		}
		if (cmd.getName().equalsIgnoreCase("setparkour1") && player.isOp()) {
			getConfig().set("parkour1.world", player.getLocation().getWorld().getName());
			getConfig().set("parkour1.x", player.getLocation().getX());
			getConfig().set("parkour1.y", player.getLocation().getY());
			getConfig().set("parkour1.z", player.getLocation().getZ());
			getConfig().set("parkour1.direction", player.getLocation().getDirection());
			getConfig().set("parkour1.yaw", player.getLocation().getYaw());
			getConfig().set("parkour1.pitch", player.getLocation().getPitch());

			saveConfig();
			player.sendMessage(tag + ChatColor.GREEN + "Parkour 1 spawn set!");
			return true;
		}
		if (cmd.getName().equalsIgnoreCase("setmountspawn") && player.isOp()) {
			getConfig().set("mount.world", player.getLocation().getWorld().getName());
			getConfig().set("mount.x", player.getLocation().getX());
			getConfig().set("mount.y", player.getLocation().getY());
			getConfig().set("mount.z", player.getLocation().getZ());

			saveConfig();
			player.sendMessage(ChatColor.GREEN + "Mount spawn set!");
			return true;
		}
		if (cmd.getName().equalsIgnoreCase("cp1") && player.isOp()) {
			getConfig().set("cp1.world", player.getLocation().getWorld().getName());
			getConfig().set("cp1.x", player.getLocation().getX());
			getConfig().set("cp1.y", player.getLocation().getY());
			getConfig().set("cp1.z", player.getLocation().getZ());
			getConfig().set("cp1.direction", player.getLocation().getDirection());
			getConfig().set("cp1.yaw", player.getLocation().getYaw());
			getConfig().set("cp1.pitch", player.getLocation().getPitch());

			saveConfig();
			player.sendMessage(StringManager.getMessage("Checkpoint set for parkour 1!", MessageType.PARKOUR));
			return true;
		}
		if (cmd.getName().equalsIgnoreCase("parkour1")) {
			if (getConfig().getConfigurationSection("parkour1") == null) {
				player.sendMessage(StringManager.getPrefix(MessageType.ERROR) + "Parkour spawn not yet set!");
				return true;
			}
			org.bukkit.World w = Bukkit.getServer().getWorld(getConfig().getString("parkour1.world"));
			double yaw = getConfig().getDouble("parkour1.yaw");
			double pitch = getConfig().getDouble("parkour1.pitch");
			double x = getConfig().getDouble("parkour1.x");
			double y = getConfig().getDouble("parkour1.y");
			double z = getConfig().getDouble("parkour1.z");
			Location parkour1 = new Location(w, x, y, z);
			parkour1.setPitch((float) pitch);
			parkour1.setYaw((float) yaw);
			player.teleport(parkour1);
			player.sendMessage(StringManager.getPrefix(MessageType.PARKOUR) + "Teleported to " + ChatColor.AQUA + "Parkour1");
		}

		if (cmd.getName().equalsIgnoreCase("addstaff") && player.isOp()) {
			if (player.isOp()) {
				this.getConfig().set(player.getUniqueId() + ".Administrator", true);
				this.saveFile();
				player.sendMessage(StringManager.getPrefix(MessageType.SUCCESS) + ChatColor.BLUE + "Added " + ChatColor.YELLOW + player.getUniqueId().toString() + ChatColor.BLUE + " to socreboard staff!");
			}
		}

		if (cmd.getName().equalsIgnoreCase("givetokens") && player.isOp()) {
			if (a.length >= 1) {
				if (a.length == 1) {
					int tempValue;
					try {
						tempValue = Integer.parseInt(a[0]);
					} catch (NumberFormatException e) {
						player.sendMessage(StringManager.getPrefix(MessageType.ERROR) + "Value must be a number!");
						return true;
					}
					LcTokensAPI.givePoints(player, tempValue);
					ApiEvent.updatescore(player);
					player.sendMessage(StringManager.getPrefix(MessageType.TRANSACTION) + "" + a[0] + ChatColor.AQUA + " Tokens recieved!");

				} else if (a.length == 2) {
					LcTokensAPI.givePoints(Bukkit.getOfflinePlayer(a[0]), Integer.parseInt(a[1]));
					Player tempPlayer = this.getServer().getPlayer(a[0]);
					if (tempPlayer != null) {
						ApiEvent.updatescore(tempPlayer);
						tempPlayer.sendMessage(StringManager.getPrefix(MessageType.TRANSACTION) + "" + a[1] + ChatColor.AQUA + " Tokens recieved!");
					}

				}

			} else {
				player.sendMessage(ChatColor.RED + "Something Failed!");
			}
		}
		if (cmd.getName().equalsIgnoreCase("givecoins") && player.isOp()) {
			if (a.length >= 1) {
				if (a.length == 1) {
					int tempValue;
					try {
						tempValue = Integer.parseInt(a[0]);
					} catch (NumberFormatException e) {
						player.sendMessage(StringManager.getPrefix(MessageType.ERROR) + "Value must be a number!");
						return true;
					}
					LcCoinsAPI.givePoints(player, tempValue);
					ApiEvent.updatescore(player);
					player.sendMessage(StringManager.getPrefix(MessageType.TRANSACTION) + a[0] + ChatColor.AQUA + " Coins recieved!");

				} else if (a.length == 2) {
					LcCoinsAPI.givePoints(Bukkit.getOfflinePlayer(a[0]), Integer.parseInt(a[1]));
					Player tempPlayer = this.getServer().getPlayer(a[0]);
					if (tempPlayer != null) {
						ApiEvent.updatescore(tempPlayer);
						tempPlayer.sendMessage(StringManager.getPrefix(MessageType.TRANSACTION) + "" + a[1] + ChatColor.AQUA + " Coins recieved!");
					}

				}

			}
		}

		return false;
	}

	public static Location GetSpawn() {
		return _spawn.clone();
	}

	public static Main getInstance() {
		return instance;
	}
}
