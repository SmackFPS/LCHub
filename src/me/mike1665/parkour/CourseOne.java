package me.mike1665.parkour;

import java.util.ArrayList;
import java.util.Random;

import me.mike1665.Main.Main;
import me.mike1665.coinapi.LcCoinsAPI;
import me.mike1665.coinapi.LcTokensAPI;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.block.Sign;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;

public class CourseOne implements Listener {

	private static Main plugin;

	ArrayList<Player> join = new ArrayList<Player>();
	ArrayList<Player> cp = new ArrayList<Player>();
	String tag = ChatColor.RED + "" + ChatColor.BOLD + "Parkour "
			+ ChatColor.RESET + "" + ChatColor.DARK_GRAY + "> ";

	public static void initialize(Main plugin2) {
		CourseOne.plugin = plugin2;
	}

	@EventHandler
	public void parkoursign(SignChangeEvent e) {
		if (e.getLine(0).equalsIgnoreCase("[parkour1]")) {

			e.setLine(0, ChatColor.BOLD + "[Parkour]");
			e.setLine(1, ChatColor.RED + "Course 1");
			e.setLine(2, ChatColor.AQUA + "Click to join");

		}
		if (e.getLine(0).equalsIgnoreCase("[leaveparkour]")) {
			e.setLine(0, ChatColor.BOLD + "[Leave]");
			e.setLine(1, ChatColor.DARK_RED + "Course 1");
		}
		if (e.getLine(0).equalsIgnoreCase("[cp]")) {
			e.setLine(0, ChatColor.AQUA + "[Checkpoint]");
			e.setLine(1, ChatColor.BLUE + "Checkpoint 1");
		}
		if (e.getLine(0).equalsIgnoreCase("[rewards]")) {
			e.setLine(0, ChatColor.GREEN + "" + ChatColor.BOLD + "[Rewards]");
			e.setLine(1, ChatColor.DARK_BLUE + "Course 1");
			e.setLine(2, ChatColor.DARK_PURPLE + "What will");
			e.setLine(3, ChatColor.DARK_PURPLE + "you win?");
		}
	}

	@EventHandler
	public void signparkour(PlayerInteractEvent e) {
		if (!(e.getAction() == Action.RIGHT_CLICK_BLOCK))
			return;
		if (e.getClickedBlock().getState() instanceof Sign) {
			Sign s = (Sign) e.getClickedBlock().getState();
			if (s.getLine(1).equalsIgnoreCase(ChatColor.RED + "Course 1")) {
				Player player = e.getPlayer();
				join.add(player);
				teleport(player);
				player.sendMessage(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD
						+ ChatColor.STRIKETHROUGH + "----------"
						+ ChatColor.RESET + "" + ChatColor.LIGHT_PURPLE + ""
						+ ChatColor.BOLD + " Parkour " + ChatColor.LIGHT_PURPLE
						+ "" + ChatColor.BOLD + ChatColor.STRIKETHROUGH
						+ "----------");
				player.sendMessage(tag + ChatColor.GREEN
						+ "Welcome to the parkour course!");
				player.sendMessage(tag + ChatColor.GREEN
						+ "Here you will face challenges in this course");
				player.sendMessage(tag + ChatColor.GREEN
						+ "There are checkpoints to help you through");
				player.sendMessage(tag + ChatColor.GREEN
						+ "So you won't have to start over!");
				player.sendMessage(tag + ChatColor.GREEN
						+ "If you win this parkour you get to win");
				player.sendMessage(tag + ChatColor.GREEN
						+ "Choice of 1000 Coins OR 100 Pixels!");
				player.sendMessage(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD
						+ "" + ChatColor.STRIKETHROUGH
						+ "-----------------------------");

			}
			if (s.getLine(1).equalsIgnoreCase(ChatColor.DARK_RED + "Course 1")) {
				Player player = e.getPlayer();
				remove(player);

			}
			if (s.getLine(1).equalsIgnoreCase(ChatColor.BLUE + "Checkpoint 1")) {
				Player player = e.getPlayer();
				cp.add(player);
				player.sendMessage(tag + ChatColor.AQUA + ChatColor.BOLD
						+ "Checkpoint 1 set!");

			} else if (s.getLine(1).equalsIgnoreCase(
					ChatColor.DARK_BLUE + "Course 1")) {
				Player player = e.getPlayer();
				Random object = new Random();
				int rewards;
				if (cp.contains(player) || join.contains(player)) {
					for (int counter = 1; counter <= 1; counter++) {
						rewards = 1 + object.nextInt(2);

						if (rewards == 1) {
							LcCoinsAPI.givePoints(player, 1000);
							player.playSound(player.getLocation(),
									Sound.LEVEL_UP, 10, 10);
							Bukkit.broadcastMessage(tag + ChatColor.GREEN + ""
									+ ChatColor.BOLD + player.getName()
									+ " Won 1000 Coins for winning parkour!");
							me.mike1665.coinapi.ApiEvent.scoreboard(player);
							cp.remove(player);
							teleport(player);

						}

						else if (rewards == 2) {
							LcTokensAPI.givePoints(player, 100);
							player.playSound(player.getLocation(),
									Sound.LEVEL_UP, 10, 10);
							Bukkit.broadcastMessage(tag + ChatColor.GREEN + ""
									+ ChatColor.BOLD + player.getName()
									+ " Won 100 Tokens for winning parkour!");
							me.mike1665.coinapi.ApiEvent.scoreboard(player);
							cp.remove(player);
							teleport(player);
						}

					}
				} else if (!cp.contains(player) || !join.contains(player)) {
					player.sendMessage(tag + "You must be in parkour!");
					return;
				}
 			}
			return;
		}
	}

	@EventHandler
	public void blockbreak(BlockBreakEvent e) {
		Player p = e.getPlayer();
		if (join.contains(p) || cp.contains(p)) {
			teleport(p);
			p.sendMessage(tag + ChatColor.DARK_RED
					+ "Please dont break blocks in the parkour course!");

		} else if (!join.contains(p)) {
			return;
		}
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void parkourreturn(EntityDamageEvent event) {
		if (join.contains(event.getEntity()) && !cp.contains(event.getEntity())) {
			if (event.getCause() == EntityDamageEvent.DamageCause.LAVA) {
				if ((event.getEntity() instanceof Player)) {
					teleport((Player) event.getEntity());
					((CommandSender) event.getEntity()).sendMessage(tag
							+ ChatColor.GOLD
							+ "Uh-oh You died! Teleported to the beginning!");
					event.setCancelled(true);
					event.getEntity().setFireTicks(0);
					event.getEntity().eject();
					event.getEntity().leaveVehicle();
					event.setDamage(0);
					
					return;
				}
			}

		} else if (cp.contains(event.getEntity())
				&& join.contains(event.getEntity())) {
			if (event.getCause() == EntityDamageEvent.DamageCause.LAVA) {
				if ((event.getEntity() instanceof Player)) {
					checkpoint((Player) event.getEntity());
					((CommandSender) event.getEntity())
							.sendMessage(tag
									+ ChatColor.GOLD
									+ "Lucky! You have a checkpoint! Sent to checkpoint!");
					event.setCancelled(true);
					event.getEntity().setFireTicks(0);
					event.getEntity().eject();
					event.getEntity().leaveVehicle();
					event.setDamage(0);
					return;
				}
			}
		}
	}
	
	@EventHandler
    public void onPlayerMove(PlayerMoveEvent event){
        Player p = event.getPlayer();
        Location loc = p.getLocation();
        if (loc.getBlockY() <= 0){
            if (join.contains(p) && !cp.contains(p)) {
            	teleport(p);
            } else if (join.contains(p) && cp.contains(p)) {
            	checkpoint(p);
            }
        }
    }  

	public void remove(Player p) {
		if (join.contains(p)) {
			join.remove(p);
			p.sendMessage(tag + ChatColor.YELLOW + "You left.");

		}
		if (cp.contains(p)) {
			cp.remove(p);

		} else if (!join.contains(p) || !cp.contains(p)) {
			p.sendMessage(tag + "You are not in parkour.");
		}

	}

	public void teleport(Player p) {
		World w = p.getWorld();
		double yaw = plugin.getConfig().getDouble("parkour1.yaw");
		double pitch = plugin.getConfig().getDouble("parkour1.pitch");
		double x = plugin.getConfig().getDouble("parkour1.x");
		double y = plugin.getConfig().getDouble("parkour1.y");
		double z = plugin.getConfig().getDouble("parkour1.z");
		Location parkour1 = new Location(w, x, y, z);
		parkour1.setPitch((float) pitch);
		parkour1.setYaw((float) yaw);
		p.teleport(parkour1);
	}

	public void checkpoint(Player p) {
		World w = p.getWorld();
		double yaw = plugin.getConfig().getDouble("cp1.yaw");
		double pitch = plugin.getConfig().getDouble("cp1.pitch");
		double x = plugin.getConfig().getDouble("cp1.x");
		double y = plugin.getConfig().getDouble("cp1.y");
		double z = plugin.getConfig().getDouble("cp1.z");
		Location parkour1 = new Location(w, x, y, z);
		parkour1.setPitch((float) pitch);
		parkour1.setYaw((float) yaw);
		p.teleport(parkour1);
	}
}
