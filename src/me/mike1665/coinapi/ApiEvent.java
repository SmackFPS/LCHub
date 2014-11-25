package me.mike1665.coinapi;

import me.mike1665.Main.Main;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

public class ApiEvent implements Listener {
	
	private static Main plugin;
	
	public static void initialize(Main plugin){
		ApiEvent.plugin = plugin;
	}
	static Scoreboard board;
	
	@SuppressWarnings("deprecation")
	public static void scoreboard(Player p){
		ScoreboardManager manager = Bukkit.getScoreboardManager();
		board = manager.getNewScoreboard();

		Objective objective = board.registerNewObjective("Test", "Test2");
		objective.setDisplayName(ChatColor.RED + " ❤ " + ChatColor.GREEN +"" + ChatColor.BOLD + "LightCraft" + "" + ChatColor.RED + " ❤");
		objective.setDisplaySlot(DisplaySlot.SIDEBAR);

		Team team = board.registerNewTeam("Team");
		team.setDisplayName(ChatColor.RED + "");

		int a = LcTokensAPI.balancePoints(p);
		int b = LcCoinsAPI.balancePoints(p);
		
		for (Player checkstaff : Bukkit.getOnlinePlayers()) {
			if (checkstaff.isOp()) {
				Score staffyes = objective.getScore(Bukkit.getOfflinePlayer(ChatColor.GREEN + "" + ChatColor.BOLD + "YES"));
				staffyes.setScore(13);
				break;
			} else if (!checkstaff.isOp()){
				Score staffno = objective.getScore(Bukkit.getOfflinePlayer(ChatColor.DARK_RED + "" + ChatColor.BOLD + "NO"));
				staffno.setScore(13);
				break;
			}
		}
		
		Score topline = objective.getScore(Bukkit.getOfflinePlayer("§-------------"));
		Score score = objective.getScore(Bukkit.getOfflinePlayer(ChatColor.LIGHT_PURPLE + "Tokens "));
		Score pixels = objective.getScore(Bukkit.getOfflinePlayer(ChatColor.WHITE + "" + a));
		Score spacing = objective.getScore(Bukkit.getOfflinePlayer(ChatColor.LIGHT_PURPLE + " "));
		Score score2 = objective.getScore(Bukkit.getOfflinePlayer(ChatColor.GREEN + "Coins "));
		Score coins = objective.getScore(Bukkit.getOfflinePlayer(ChatColor.WHITE + "" + b));
		Score spacing3 = objective.getScore(Bukkit.getOfflinePlayer(ChatColor.DARK_AQUA + ""));
		Score staff = objective.getScore(Bukkit.getOfflinePlayer(ChatColor.BLUE + "Staff Online?"));
		Score botmline = objective.getScore(Bukkit.getOfflinePlayer(ChatColor.AQUA + "-------------"));

		topline.setScore(21);
		score.setScore(20);
		pixels.setScore(19);
		spacing.setScore(18);
		score2.setScore(17);
		coins.setScore(16);
		spacing3.setScore(15);
		staff.setScore(14);
		botmline.setScore(12);
		p.setScoreboard(board);

	}
	@EventHandler(priority=EventPriority.HIGHEST)
	public void onJoin(final PlayerJoinEvent e) {
		final Player p = e.getPlayer();
		
		if (!plugin.getConfig().contains(p.getUniqueId().toString())) {
			plugin.getConfig().set(p.getUniqueId() + ".Tokens", 50);
			plugin.getConfig().set(p.getUniqueId() + ".Coins", 500);
			plugin.saveConfig();
			scoreboard(p);
		}
		
		scoreboard(p);
	}
	
	@EventHandler
	public void pickup(PlayerPickupItemEvent e) {
		scoreboard(e.getPlayer());
	}
	
	public static void updatescore(Player players) {
		scoreboard(players);
	}
	
	/*@SuppressWarnings("deprecation")
	@EventHandler
	public void onKill(EntityDeathEvent e) {
		Player player = (Player) e.getEntity();
        if(player.getKiller() instanceof Player) {
            Player killer = player.getKiller();
			PixlPointsAPI.givePoints(killer, 1);
			killer.getScoreboard().getObjective(DisplaySlot.SIDEBAR).getScore(Bukkit.getOfflinePlayer(ChatColor.GOLD + "Points ")).setScore(PixlPointsAPI.balancePoints(killer));
        }
	}*/
}
