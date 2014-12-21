package me.mike1665.eventhandlers;

import me.mike1665.Main.Main;
import me.mike1665.ammo.AmmoManager;
import me.mike1665.ammo.BatBlasterAmmoManager;
import me.mike1665.ammo.EnderDogeAmmoManager;
import me.mike1665.ammo.FireWorksAmmoManager;
import me.mike1665.ammo.FunCreeperAmmoManager;
import me.mike1665.ammo.GadgetAmmo;
import me.mike1665.ammo.KittyCannonAmmoManager;
import me.mike1665.ammo.MeowAmmoManager;
import me.mike1665.chest.RandomManager;
import me.mike1665.chest.TreasureChestManager;
import me.mike1665.click.MusicClick;
import me.mike1665.click.WardrobeClick;
import me.mike1665.coinapi.ApiEvent;
import me.mike1665.coinapi.LcCoinsAPI;
import me.mike1665.coinapi.LcTokensAPI;
import me.mike1665.commands.AmmoTest;
import me.mike1665.commands.MountUnlocked;
import me.mike1665.commands.SQLBan;
import me.mike1665.commands.StatsCommand;
import me.mike1665.commands.UnlockAllArmor;
import me.mike1665.effects.EffectManager;
import me.mike1665.extra.ExtraManager;
import me.mike1665.funstuff.BowTeleport;
import me.mike1665.funstuff.DiscoBall;
import me.mike1665.funstuff.MagicClock;
import me.mike1665.funstuff.PvPSword;
import me.mike1665.hubstuff.UpdateScore;
import me.mike1665.menu.MountMenu;
import me.mike1665.menu.WardrobeMenu;
import me.mike1665.mount.MountManager;
import me.mike1665.mount.mounts.AngelRider;
import me.mike1665.mount.mounts.DarkRider;
import me.mike1665.mount.mounts.GhostRider;
import me.mike1665.mount.mounts.NyanRider;
import me.mike1665.mount.mounts.PoseidonRider;
import me.mike1665.parkour.CourseOne;
import me.mike1665.particle.ParticleManager;
import me.mike1665.utils.UtilEnt;
import me.mike1665.utils.UtilLocation;
import me.mike1665.utils.UtilityBlock;
import me.mike1665.wardrobe.WardrobeManager;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;

import com.arrayprolc.event.ColouredWardrobe;


public class MikeEventSetup {
	
	public static void setupEvents(){
		PluginManager pm = Bukkit.getServer().getPluginManager();
		FunCreeperAmmoManager.initialize();
		MeowAmmoManager.initialize();
		BatBlasterAmmoManager.initialize();
		EnderDogeAmmoManager.initialize();
		FireWorksAmmoManager.initialize();
		KittyCannonAmmoManager.initialize();
		DarkRider.initialize();
		GhostRider.initialize();
		PoseidonRider.initialize();
		NyanRider.initialize();
		AngelRider.initialize();
	    MountManager.registerEvents();
		MusicClick.initalize();
		LcTokensAPI.initialize();
		LcCoinsAPI.initialize();
		ApiEvent.initialize();
		MountMenu.initialize();
		CourseOne.initialize();
		StatsCommand.setup();
		MountUnlocked.setup();
		SQLBan.setup();
		AmmoTest.setup();
		UnlockAllArmor.setup();
	    GadgetAmmo.initialize();
	    MountMenu.initialize();
		pm.registerEvents(new WardrobeClick(), Main.getInstance());
		pm.registerEvents(new BowTeleport(), Main.getInstance());
		pm.registerEvents(new PvPSword(), Main.getInstance());
		pm.registerEvents(new MagicClock(), Main.getInstance());
		pm.registerEvents(new DiscoBall(), Main.getInstance());
		pm.registerEvents(new UpdateScore(), Main.getInstance());
		pm.registerEvents(new AmmoTest(), Main.getInstance());
		pm.registerEvents(new AmmoManager(), Main.getInstance());
	    pm.registerEvents(new GhostRider(), Main.getInstance());
	    pm.registerEvents(new DarkRider(), Main.getInstance());
	    pm.registerEvents(new AngelRider(), Main.getInstance());
	    pm.registerEvents(new PoseidonRider(), Main.getInstance());
		WardrobeManager.initialize();
		WardrobeMenu.initialize();
		RandomManager.initialize();
	    EffectManager.registerEvents();
	    ExtraManager.registerEvents();
	    ParticleManager.registerEvents();
		Bukkit.getPluginManager().registerEvents(new TreasureChestManager(), Main.getInstance());
	    Bukkit.getPluginManager().registerEvents(new UtilEnt(), Main.getInstance());
	    Bukkit.getPluginManager().registerEvents(new UtilityBlock(), Main.getInstance());
	    Bukkit.getPluginManager().registerEvents(new UtilLocation(), Main.getInstance());
	    Bukkit.getPluginManager().registerEvents(new ParticleManager(), Main.getInstance());
	    pm.registerEvents(new ColouredWardrobe(), Main.getInstance());
	}

}
