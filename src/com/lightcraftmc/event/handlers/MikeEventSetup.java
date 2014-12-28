package com.lightcraftmc.event.handlers;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;

import com.lightcraftmc.ammo.AmmoManager;
import com.lightcraftmc.ammo.BatBlasterAmmoManager;
import com.lightcraftmc.ammo.EnderDogeAmmoManager;
import com.lightcraftmc.ammo.FireWorksAmmoManager;
import com.lightcraftmc.ammo.FunCreeperAmmoManager;
import com.lightcraftmc.ammo.GadgetAmmo;
import com.lightcraftmc.ammo.KittyCannonAmmoManager;
import com.lightcraftmc.ammo.MeowAmmoManager;
import com.lightcraftmc.coinapi.ApiEvent;
import com.lightcraftmc.coinapi.LcCoinsAPI;
import com.lightcraftmc.coinapi.LcTokensAPI;
import com.lightcraftmc.command.AmmoTest;
import com.lightcraftmc.command.MountUnlocked;
import com.lightcraftmc.command.StatsCommand;
import com.lightcraftmc.command.UnlockAllArmor;
import com.lightcraftmc.effects.EffectManager;
import com.lightcraftmc.event.ColouredWardrobe;
import com.lightcraftmc.event.handlers.menu.AdminGadgetsClick;
import com.lightcraftmc.event.handlers.menu.BuyGadgetsClick;
import com.lightcraftmc.event.handlers.menu.CosMenuClick;
import com.lightcraftmc.event.handlers.menu.GadjetsMenuClick;
import com.lightcraftmc.event.handlers.menu.MountMenuClick;
import com.lightcraftmc.event.handlers.menu.MusicClick;
import com.lightcraftmc.event.handlers.menu.PlayerGadjetsClick;
import com.lightcraftmc.event.handlers.menu.VipGadjetsClick;
import com.lightcraftmc.event.handlers.menu.WardrobeClick;
import com.lightcraftmc.extra.ExtraManager;
import com.lightcraftmc.fusebox.util.UtilServer;
import com.lightcraftmc.hub.gadgets.BowTeleport;
import com.lightcraftmc.hub.gadgets.FunCreepers;
import com.lightcraftmc.hub.gadgets.GagdetManager;
import com.lightcraftmc.hub.gadgets.MagicClock;
import com.lightcraftmc.hub.gadgets.PvPSword;
import com.lightcraftmc.hub.gadgets.SpawnCreeper;
import com.lightcraftmc.hub.main.Main;
import com.lightcraftmc.hubstuff.DoubleJump;
import com.lightcraftmc.hubstuff.LaunchPad;
import com.lightcraftmc.hubstuff.UpdateScore;
import com.lightcraftmc.menu.AdminGadgets;
import com.lightcraftmc.menu.BuyGadgets;
import com.lightcraftmc.menu.CosmeticsMenu;
import com.lightcraftmc.menu.GadjetsMenu;
import com.lightcraftmc.menu.MountMenu;
import com.lightcraftmc.menu.MusicMenu;
import com.lightcraftmc.menu.PlayerGadjets;
import com.lightcraftmc.menu.VipGadjets;
import com.lightcraftmc.menu.WardrobeMenu;
import com.lightcraftmc.mount.MountManager;
import com.lightcraftmc.mount.mounts.AngelRider;
import com.lightcraftmc.mount.mounts.DarkRider;
import com.lightcraftmc.mount.mounts.GhostRider;
import com.lightcraftmc.mount.mounts.NyanRider;
import com.lightcraftmc.mount.mounts.PoseidonRider;
import com.lightcraftmc.parkour.CourseOne;
import com.lightcraftmc.particle.ParticleManager;
import com.lightcraftmc.particles.ParticleClick;
import com.lightcraftmc.particles.UnlockedParticle;
import com.lightcraftmc.particles.staff.StaffJoin;
import com.lightcraftmc.treasurechests.RandomManager;
import com.lightcraftmc.treasurechests.TreasureChestManager;
import com.lightcraftmc.wardrobe.WardrobeManager;


public class MikeEventSetup {
	
	
	
	public static void disable(){
		  for (Player players : UtilServer.getPlayers()) {
			  EffectManager.removeEffect(players, false);
		  }
	}
	
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
		AmmoTest.setup();
		UnlockAllArmor.setup();
	    GadgetAmmo.initialize();
	    MountMenu.initialize();
	    UnlockedParticle.initialize();
		pm.registerEvents(new WardrobeClick(), Main.getInstance());
		pm.registerEvents(new BowTeleport(), Main.getInstance());
		pm.registerEvents(new PvPSword(), Main.getInstance());
		pm.registerEvents(new MagicClock(), Main.getInstance());
		pm.registerEvents(new UpdateScore(), Main.getInstance());
		pm.registerEvents(new AmmoTest(), Main.getInstance());
		pm.registerEvents(new AmmoManager(), Main.getInstance());
	    pm.registerEvents(new GhostRider(), Main.getInstance());
	    pm.registerEvents(new DarkRider(), Main.getInstance());
	    pm.registerEvents(new AngelRider(), Main.getInstance());
	    pm.registerEvents(new PoseidonRider(), Main.getInstance());
	   
	    pm.registerEvents(new TreasureChestManager(), Main.getInstance());
	    RandomManager.initialize();
	    
	    
		WardrobeManager.initialize();
		WardrobeMenu.initialize();
	    EffectManager.registerEvents();
	    ExtraManager.registerEvents();
	    ParticleManager.registerEvents();
	    Bukkit.getPluginManager().registerEvents(new ParticleManager(), Main.getInstance());
	    Bukkit.getPluginManager().registerEvents(new StaffJoin(), Main.getInstance());
	    Bukkit.getPluginManager().registerEvents(new ParticleClick(), Main.getInstance());


	    
	    pm.registerEvents(new ColouredWardrobe(), Main.getInstance());
		AdminGadgets.init();
		GadjetsMenu.init();
		PlayerGadjets.init();
		VipGadjets.init();
		GagdetManager.registerEvents();
		AmmoManager.registerEvents();
		pm.registerEvents(new BuyGadgets(), Main.getInstance());
		pm.registerEvents(new EnderDoge(), Main.getInstance());
		pm.registerEvents(new CatWorks(), Main.getInstance());
		pm.registerEvents(new MeowBall(), Main.getInstance());
		pm.registerEvents(new VipGadjetsClick(), Main.getInstance());
		pm.registerEvents(new VipGadjets(), Main.getInstance());
		pm.registerEvents(new GadjetsMenuClick(), Main.getInstance());
		pm.registerEvents(new GadjetsMenu(), Main.getInstance());
		pm.registerEvents(new PlayerGadjetsClick(), Main.getInstance());
		pm.registerEvents(new PlayerGadjets(), Main.getInstance());
		pm.registerEvents(new AdminGadgetsClick(), Main.getInstance());
		pm.registerEvents(new AdminGadgets(), Main.getInstance());
		pm.registerEvents(new MelonBlock(), Main.getInstance());
		pm.registerEvents(new EntityHook(), Main.getInstance());
		pm.registerEvents(new RespawnEvent(), Main.getInstance());
		pm.registerEvents(new TNTFun(), Main.getInstance());
		pm.registerEvents(new FireworkLauncher(), Main.getInstance());
		pm.registerEvents(new PaintballGun(), Main.getInstance());
		pm.registerEvents(new BatBlaster(), Main.getInstance());
		pm.registerEvents(new ApiEvent(), Main.getInstance());
		pm.registerEvents(new LcTokensAPI(), Main.getInstance());
		pm.registerEvents(new CoinBomb(), Main.getInstance());
		pm.registerEvents(new LcCoinsAPI(), Main.getInstance());
		pm.registerEvents(new CosmeticsMenu(), Main.getInstance());
		pm.registerEvents(new CosMenuClick(), Main.getInstance());
		pm.registerEvents(new CourseOne(), Main.getInstance());
		pm.registerEvents(new MountMenuClick(), Main.getInstance());
		pm.registerEvents(new DoubleJump(), Main.getInstance());
		pm.registerEvents(new LaunchPad(), Main.getInstance());
		pm.registerEvents(new BuyMeowBalls(), Main.getInstance());
		pm.registerEvents(new BuyGadgetsClick(), Main.getInstance());
		pm.registerEvents(new BuyGadgets(), Main.getInstance());
		pm.registerEvents(new BuyEnderDoge(), Main.getInstance());
		pm.registerEvents(new PixlBomb(), Main.getInstance());
		pm.registerEvents(new SpawnCreeper(), Main.getInstance());
		pm.registerEvents(new FunCreepers(), Main.getInstance());
		pm.registerEvents(new MusicMenu(), Main.getInstance());
		pm.registerEvents(new MusicClick(), Main.getInstance());
	}

}
