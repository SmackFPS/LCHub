package fr.coco_gigpn.prodigygadget.util;

import java.io.File;
import java.util.Arrays;

public class Configs extends UtilConfig  {


	@ConfigOptions(name = "Configuration.stopParticlesOnLag")
	public static boolean stopParticleOnLag = true;
	@ConfigOptions(name = "Configuration.stopFireworksOnLag")
	public static boolean stopFireworkOnLag = true;

	@ConfigOptions(name = "Configuration.UsingGadgetOnebyOne")
	public static boolean OnebyOneGadget = true;

	@ConfigOptions(name = "Configuration.itemOnJoinEnabled")
	public static boolean itemonjoin = true;
	@ConfigOptions(name = "Configuration.itemOnJoinPositionEnabled")
	public static boolean itemOnJoinEnabledPosition = false;
	@ConfigOptions(name = "Configuration.pack.id")
	public static int itemID = 138;
	@ConfigOptions(name = "Configuration.pack.data")
	public static int itemData = 0;
	@ConfigOptions(name = "Configuration.GadgetIcon.id")
	public static int gadgetID = 261;
	@ConfigOptions(name = "Configuration.GadgetIcon.data")
	public static int gadgetDATA = 0;
	@ConfigOptions(name = "Configuration.ParticleIcon.id")
	public static int particleID = 399;
	@ConfigOptions(name = "Configuration.ParticleIcon.data")
	public static int particleDATA = 0;
	@ConfigOptions(name = "Configuration.MorphIcon.id")
	public static int morphID = 397;
	@ConfigOptions(name = "Configuration.MorphIcon.data")
	public static int morphDATA = 2;
	@ConfigOptions(name = "Configuration.MountIcon.id")
	public static int mountID = 417;
	@ConfigOptions(name = "Configuration.MountIcon.data")
	public static int mountDATA = 0;
	@ConfigOptions(name = "Configuration.ExtraIcon.id")
	public static int extraID = 388;
	@ConfigOptions(name = "Configuration.ExtraIcon.data")
	public static int extraDATA = 0;
	@ConfigOptions(name = "Configuration.PetIcon.id")
	public static int petID = 421;
	@ConfigOptions(name = "Configuration.PetIcon.data")
	public static int petDATA = 0;
	@ConfigOptions(name = "Configuration.itemOnJoinPosition")
	public static int itemOnJoinPosition = 9;
	@ConfigOptions(name = "Configuration.canDropProdigyPack")
	public static boolean canDropProdigypack = false;
	@ConfigOptions(name = "Configuration.canDropGadgets")
	public static boolean canDropGadgets = false;

	@ConfigOptions(name = "Configuration.removeGadgetOnleft")
	public static boolean removeGadgetOnleft = true;
	@ConfigOptions(name = "Configuration.gadgetEnabledPosition")
	public static boolean gadgetEnabledPosition = true;
	@ConfigOptions(name = "Configuration.gadgetPosition")
	public static int gadgetPosition = 5;

	@ConfigOptions(name = "Configuration.gadget.discoball.laserEnable")
	public static boolean showLaser = true;
	@ConfigOptions(name = "Configuration.mount.mountsCanPlaceBlocs")
	public static boolean MountPlaceBlock = true;
	@ConfigOptions(name = "Configuration.morph.morphsHaveInfiniteDuration")
	public static boolean MorphInfiniteDuration = false;


	@ConfigOptions(name = "Configuration.gadget.batblaster.cooldown")
	public static double batblasterCooldown = 4;
	@ConfigOptions(name = "Configuration.gadget.diamondparty.cooldown")
	public static double diamondPartyCooldown = 60;
	@ConfigOptions(name = "Configuration.gadget.discoball.cooldown")
	public static double discoballCooldown = 60;
	@ConfigOptions(name = "Configuration.gadget.fireworkpopper.cooldown")
	public static double fireworkpopperCooldown = 40;
	@ConfigOptions(name = "Configuration.gadget.grenade.cooldown")
	public static double grenadeCooldown = 10;
	@ConfigOptions(name = "Configuration.gadget.halloweenhead.cooldown")
	public static double halloweenheadCooldown = 60;
	@ConfigOptions(name = "Configuration.gadget.humanbomb.cooldown")
	public static double humanbombCooldown = 30;
	@ConfigOptions(name = "Configuration.gadget.rocket.cooldown")
	public static double rocketCooldown = 60;
	@ConfigOptions(name = "Configuration.gadget.quakegun.cooldown")
	public static double quakegunCooldown = 4;
	@ConfigOptions(name = "Configuration.gadget.slimehat.cooldown")
	public static double slimehatCooldown = 40;
	@ConfigOptions(name = "Configuration.gadget.partypopper.cooldown")
	public static double partypopperCooldown = 40;
	@ConfigOptions(name = "Configuration.gadget.tntpopper.cooldown")
	public static double tntpopperCooldown = 40;
	@ConfigOptions(name = "Configuration.gadget.smokebomb.cooldown")
	public static double smokebombCooldown = 30;
	@ConfigOptions(name = "Configuration.gadget.ghost.cooldown")
	public static double ghostCooldown = 60;
	@ConfigOptions(name = "Configuration.gadget.axelauncher.cooldown")
	public static double axelauncherCooldown = 3;
	@ConfigOptions(name = "Configuration.gadget.tornado.cooldown")
	public static double tornadogadgetCooldown = 60;
	@ConfigOptions(name = "Configuration.gadget.flowerpopper.cooldown")
	public static double flowerpopperCooldown = 60;
	@ConfigOptions(name = "Configuration.gadget.gravitystation.cooldown")
	public static double gravitystationCooldown = 60;
	@ConfigOptions(name = "Configuration.gadget.enderball.cooldown")
	public static double enderballCooldown = 60;
	@ConfigOptions(name = "Configuration.gadget.puppielover.cooldown")
	public static double puppieloverCooldown = 60;


	@ConfigOptions(name = "Configuration.morph.astronot.duration")
	public static int astronotDuration = 20;
	@ConfigOptions(name = "Configuration.morph.blaze.duration")
	public static int blazeDuration = 20;
	@ConfigOptions(name = "Configuration.morph.cactus.duration")
	public static int cactusDuration = 20;
	@ConfigOptions(name = "Configuration.morph.vampire.duration")
	public static int vampireDuration = 20;
	@ConfigOptions(name = "Configuration.morph.witch.duration")
	public static int witchDuration = 20;
	@ConfigOptions(name = "Configuration.morph.enderman.duration")
	public static int endermanDuration = 20;
	@ConfigOptions(name = "Configuration.morph.ghast.duration")
	public static int ghastDuration = 20;


	public Configs(final File file) {
		super(file, Arrays.asList("Prodigy Gadgets Configuration"));
	}
}
