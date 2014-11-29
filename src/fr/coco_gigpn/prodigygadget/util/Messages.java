package fr.coco_gigpn.prodigygadget.util;

import java.io.File;
import java.util.Arrays;

public class Messages extends UtilConfig {

	@ConfigOptions(name = "Name.menu.main")
	public static String mainMenuPrefix = "&7Pack list";
	@ConfigOptions(name = "Name.menu.particle")
	public static String particleMenuPrefix = "&7Particule List";
	@ConfigOptions(name = "Name.menu.gadget")
	public static String gadgetMenuPrefix = "&7Gadget list";
	@ConfigOptions(name = "Name.menu.pet")
	public static String petMenuPrefix = "&7Pet list";
	@ConfigOptions(name = "Name.menu.mount")
	public static String  mountMenuPrefix = "&7Mount list";
	@ConfigOptions(name = "Name.menu.extra")
	public static String extraMenuPrefix = "&7Extra Effect list";
	@ConfigOptions(name = "Name.menu.morph")
	public static String morphMenuPrefix = "&7Morph list";

	@ConfigOptions(name = "Name.inventory.iconName.gadget")
	public static String gadgetName = "&a&lGadget";
	@ConfigOptions(name = "Name.inventory.iconName.pet")
	public static String petMenuName = "&a&lPet";
	@ConfigOptions(name = "Name.inventory.iconName.mount")
	public static String mountName = "&a&lMount";
	@ConfigOptions(name = "Name.inventory.iconName.particle")
	public static String particleName = "&a&lParticle";
	@ConfigOptions(name = "Name.inventory.iconName.extra")
	public static String extraName = "&a&lExtra";
	@ConfigOptions(name = "Name.inventory.iconName.morph")
	public static String morphName = "&a&lMorph";
	
	@ConfigOptions(name = "Name.mount.angelrider")
	public static String angelriderPrefix = "&b&lAngelrider";
	@ConfigOptions(name = "Name.mount.ghostrider")
	public static String ghostriderPrefix = "&4&lGhostrider";
	@ConfigOptions(name = "Name.mount.poseidonrider")
	public static String poseidonriderPrefix = "&3&lPoseidonrider";
	@ConfigOptions(name = "Name.mount.darkrider")
	public static String darkriderPrefix = "&7&lDarkrider";
	@ConfigOptions(name = "Name.mount.nyanrider")
	public static String nyanriderPrefix = "&dNyanrider";
	

	@ConfigOptions(name = "Name.inventory.mount.angelrider")
	public static String angelriderName = "&b&lAngelRider";
	@ConfigOptions(name = "Name.inventory.mount.ghostrider")
	public static String ghostriderName = "&4&lGhostRider";
	@ConfigOptions(name = "Name.inventory.mount.nyanrider")
	public static String nyanriderName = "&5&lNyanRider";
	@ConfigOptions(name = "Name.inventory.mount.darkrider")
	public static String darkriderName = "&7&lDarkRider";
	@ConfigOptions(name = "Name.inventory.mount.poseidonrider")
	public static String poseidonriderName = "&3&lPoseidonRider";

	@ConfigOptions(name = "Name.inventory.pet.wolf")
	public static String wolfName = "&f&lWolf pet";
	@ConfigOptions(name = "Name.inventory.pet.cow")
	public static String cowName = "&6&lCow pet";
	@ConfigOptions(name = "Name.inventory.pet.pig")
	public static String pigName = "&d&lPig pet";
	@ConfigOptions(name = "Name.inventory.pet.ocelot")
	public static String ocelotName = "&6&lOcelot pet";
	@ConfigOptions(name = "Name.inventory.pet.sheep")
	public static String sheepName = "&5&lSheep pet";

	@ConfigOptions(name = "Name.inventory.morph.astronot")
	public static String astronotName = "&1&lAstronot morph";
	@ConfigOptions(name = "Name.inventory.morph.cactus")
	public static String cactusName = "&2&lCactus morph";
	@ConfigOptions(name = "Name.inventory.morph.vampire")
	public static String vampireName = "&4&lVampire morph";
	@ConfigOptions(name = "Name.inventory.morph.blaze")
	public static String blazeName = "&6&lBlaze morph";
	@ConfigOptions(name = "Name.inventory.morph.witch")
	public static String witchmorphName = "&5&lWitch morph";
	@ConfigOptions(name = "Name.inventory.morph.ghast")
	public static String ghastmorphName = "&f&lGhast morph";
	@ConfigOptions(name = "Name.inventory.morph.enderman")
	public static String endermanmorphName = "&7&lEnderman morph";

	@ConfigOptions(name = "Name.inventory.icon.pack")
	public static String packName = "&a&lChoose your pack";
	@ConfigOptions(name = "Name.inventory.icon.closeInventory")
	public static String closeName = "&cClose the menu..";
	@ConfigOptions(name = "Name.inventory.icon.ReturnMainMenu")
	public static String returnMainName = "&aReturn to the main menu";
	@ConfigOptions(name = "Name.inventory.icon.RemoveGadget")
	public static String removegadgetName = "&cRemove your gadget";
	@ConfigOptions(name = "Name.inventory.icon.RemovePet")
	public static String removePetName = "&cRemove your current pet";
	@ConfigOptions(name = "Name.inventory.icon.RemoveMount")
	public static String removeMountName = "&cRemove your current mount";
	@ConfigOptions(name = "Name.inventory.icon.RemovePetAndMount")
	public static String removePetAndMountName = "&cRemove your current pet or mount";

	@ConfigOptions(name = "Name.inventory.icon.extra.lightningCloud")
	public static String lightningName = "&aChoose lightning cloud &aeffect";
	@ConfigOptions(name = "Name.inventory.icon.extra.cloud")
	public static String cloudName = "&aChoose cloud effect";
	@ConfigOptions(name = "Name.inventory.icon.extra.snowCloud")
	public static String snowCloudName = "&aChoose snow cloud effect";
	@ConfigOptions(name = "Name.inventory.icon.extra.bloodHelix")
	public static String helixName = "&aChoose &4Blood helix effect";
	@ConfigOptions(name = "Name.inventory.icon.extra.tornado")
	public static String tornadoName = "&aChoose tornado effect";
	@ConfigOptions(name = "Name.inventory.icon.extra.vortex")
	public static String vortexName = "&aChoose vortex effect";
	@ConfigOptions(name = "Name.inventory.icon.extra.shield")
	public static String shieldName = "&aChoose shield effect";
	@ConfigOptions(name = "Name.inventory.icon.extra.fountain")
	public static String fountainName = "&aChoose fountain effect";
	@ConfigOptions(name = "Name.inventory.icon.extra.hourglass")
	public static String hourglassName = "&aChoose hourGlass &aeffect";
	@ConfigOptions(name = "Name.inventory.icon.extra.flamelilly")
	public static String flamelillyName = "&aChoose flameLilly &aeffect";
	
	@ConfigOptions(name = "Description.inventory.lore.mount.angelrider")
	public static String angelriderDescription = "&7Bring snow on your icy horse";
	@ConfigOptions(name = "Description.inventory.lore.mount.ghostrider")
	public static String ghostriderDescription = "&7Traveling on a monster fire";
	@ConfigOptions(name = "Description.inventory.lore.mount.poseidonrider")
	public static String poseidonriderDescription = "&7Travel the horse poseidon";
	@ConfigOptions(name = "Description.inventory.lore.mount.nyanrider")
	public static String nyanriderDescription = "&7Nyan nyan nyan nyan!";
	@ConfigOptions(name = "Description.inventory.lore.mount.darkrider")
	public static String darkriderDescription = "&7Rot everything that comes near you";


	@ConfigOptions(name = "Description.inventory.lore.morph.vampire")
	public static String vampireDescription = "&7Your thirst for blood is great";
	@ConfigOptions(name = "Description.inventory.lore.morph.witch")
	public static String witchDescription = "&7Poison the players around you";
	@ConfigOptions(name = "Description.inventory.lore.morph.blaze")
	public static String blazeDescription = "&7Set fire wherever you go";
	@ConfigOptions(name = "Description.inventory.lore.morph.cactus")
	public static String cactusDescription = "&7Be careful you could hurt players";
	@ConfigOptions(name = "Description.inventory.lore.morph.astronot")
	public static String astronotDescription = "&7Have you ever dreamed of walking on the moon";
	@ConfigOptions(name = "Description.inventory.lore.morph.ghast")
	public static String ghastDescription = "&7Explode players you touch";
	@ConfigOptions(name = "Description.inventory.lore.morph.enderman")
	public static String endermanDescription = "&7push players to teleport randomly";

	@ConfigOptions(name = "Name.inventory.gadget.batblaster")
	public static String batblasterName = "&3&lBatblaster";
	@ConfigOptions(name = "Name.inventory.gadget.paintball")
	public static String paintballgunName = "&2&lPaintballgun";
	@ConfigOptions(name = "Name.inventory.gadget.quakegun")
	public static String quakegunName = "&5&lQuakeGun";
	@ConfigOptions(name = "Name.inventory.gadget.rocket")
	public static String rocketName = "&4&lRocket";
	@ConfigOptions(name = "Name.inventory.gadget.fireworkpopper")
	public static String fireworkpopperName = "&a&lFirework Popper";
	@ConfigOptions(name = "Name.inventory.gadget.discoball")
	public static String discoballName = "&d&lDisco Ball";
	@ConfigOptions(name = "Name.inventory.gadget.diamondparty")
	public static String diamondpartyName = "&b&lDiamond Party";
	@ConfigOptions(name = "Name.inventory.gadget.humanbomb")
	public static String humanbombName = "&4&lHuman bomb";
	@ConfigOptions(name = "Name.inventory.gadget.slimehat")
	public static String slimehatName = "&2&lSlime Hat";
	@ConfigOptions(name = "Name.inventory.gadget.halloweenhead")
	public static String halloweenheadName = "&6&lHalloween Head";
	@ConfigOptions(name = "Name.inventory.gadget.grenade")
	public static String grenadeName = "&5&lGrenade";
	@ConfigOptions(name = "Name.inventory.gadget.smokebomb")
	public static String smokebombName = "&f&lSmoke&7&lBomb";
	@ConfigOptions(name = "Name.inventory.gadget.partypopper")
	public static String partypopperName = "&f&lParty&d&lPopper";
	@ConfigOptions(name = "Name.inventory.gadget.tntpopper")
	public static String tntpopperName = "&4&lTnt&d&lPopper";
	@ConfigOptions(name = "Name.inventory.gadget.ghost")
	public static String ghostName = "&7&lGhost";
	@ConfigOptions(name = "Name.inventory.gadget.axelauncher")
	public static String axelauncherName = "&e&lAxe Launcher";
	@ConfigOptions(name = "Name.inventory.gadget.tornadoGadget")
	public static String tornadogadgetName = "&f&lTornado";
	@ConfigOptions(name = "Name.inventory.gadget.gravitystation")
	public static String gravitystationName = "&3&lGravity&f&lStation";
	@ConfigOptions(name = "Name.inventory.gadget.flowerpopper")
	public static String flowerpopperName = "&e&lFlower&d&lpopper";
	@ConfigOptions(name = "Name.inventory.gadget.enderball")
	public static String enderballName = "&7&lEnder&4&lBall";
	@ConfigOptions(name = "Name.inventory.gadget.PuppieLover")
	public static String puppieloverName = "&5&lPuppie&d&lLover";



	@ConfigOptions(name = "Description.inventory.lore.gadget.batblaster")
	public static String batblasterDescription = "&7A cloud of bats to push players";
	@ConfigOptions(name = "Description.inventory.lore.gadget.paintball")
	public static String paintballgunDescription = "&7redecorate the walls with the paintball";
	@ConfigOptions(name = "Description.inventory.lore.gadget.quakegun")
	public static String quakegunDescription = "&7Pigs are able to fly!";
	@ConfigOptions(name = "Description.inventory.lore.gadget.rocket")
	public static String  rocketDescription = "&7That's one small step for man, one giant leap for mankind";
	@ConfigOptions(name = "Description.inventory.lore.gadget.fireworkpopper")
	public static String fireworkpopperDescription= "&7The omelets are cooked";
	@ConfigOptions(name = "Description.inventory.lore.gadget.discoball")
	public static String discoballDescription = "&7You will ignite the dance floor";
	@ConfigOptions(name = "Description.inventory.lore.gadget.diamondparty")
	public static String diamondpartyDescription = "&7Diamonds falling from the sky!";
	@ConfigOptions(name = "Description.inventory.lore.gadget.humanbomb")
	public static String humanbombDescription = "&7I am human bomb, you hold it in your hand";
	@ConfigOptions(name = "Description.inventory.lore.gadget.slimehat")
	public static String slimehatDescription = "&7Warning your hat may slide";
	@ConfigOptions(name = "Description.inventory.lore.gadget.halloweenhead")
	public static String halloweenheadDescription = "&7Would you be able to touch another player?";
	@ConfigOptions(name = "Description.inventory.lore.gadget.grenade")
	public static String grenadeDescription = "&7Remember the pin";
	@ConfigOptions(name = "Description.inventory.lore.gadget.smokebomb")
	public static String smokebombDescription = "&7Become an elite force and call your helicopter";
	@ConfigOptions(name = "Description.inventory.lore.gadget.partypopper")
	public static String partypopperDescription = "&7A bouquet of flowers at the top of your head";
	@ConfigOptions(name = "Description.inventory.lore.gadget.tntpopper")
	public static String tntpopperDescription = "&7Show your anger and become the god of tnt";
	@ConfigOptions(name = "Description.inventory.lore.gadget.ghost")
	public static String ghostDescription = "&7Call your phantom army and terrorized the players suddenly snowball";
	@ConfigOptions(name = "Description.inventory.lore.gadget.axelauncher")
	public static String axelauncherDescription = "&7Show your power to decide your opponents";
	@ConfigOptions(name = "Description.inventory.lore.gadget.tornado")
	public static String tornadoDescription = "&7Become mother nature, and fly everything in your way";
	@ConfigOptions(name = "Description.inventory.lore.gadget.flowerpopper")
	public static String flowerpopperDescription = "&7Become a real hippie and sow happiness";
	@ConfigOptions(name = "Description.inventory.lore.gadget.gravitystation")
	public static String gravitystationDescription = "&7Train like real astronaut";
	@ConfigOptions(name = "Description.inventory.lore.gadget.enderball")
	public static String enderballDescription = "&7This ball is magical and dangerous";
	@ConfigOptions(name = "Description.inventory.lore.gadget.puppielover")
	public static String puppieloverDescription = "&7An army of wolf , follow you ! Peace and love !";

	@ConfigOptions(name = "Description.inventory.lore.cooldown")
	public static String cooldownDesc = "&7COOLDOWN: &b%cooldown";
	@ConfigOptions(name = "Description.inventory.lore.AccessStatu")
	public static String AccessDesc = "&7Your access: %access";
	@ConfigOptions(name = "Description.inventory.lore.AccessAuthorizedStatu")
	public static String accessOn = "&2authorized";
	@ConfigOptions(name = "Description.inventory.lore.AccessDenyStatu")
	public static String accessDeny = "&cdeny";

	@ConfigOptions(name = "Message.effect.AlreadyStop")
	public static String alreadystopmessage = "&cYour effect was already stopped";
	@ConfigOptions(name = "Message.pet.AlreadyActived")
	public static String alreadypet = "&cYou already have a pet!";
	@ConfigOptions(name = "Message.pet.AlreadyRemoved")
	public static String alreadypetremoved = "&cYour pet was already removed";
	@ConfigOptions(name = "Message.morph.AlreadyRemoved")
	public static String alreadymorphremoved = "&cYour morph was already removed";


	@ConfigOptions(name = "Message.system.noPermission")
	public static String nopermissioneffect = "&cYou don't have permission !";
	@ConfigOptions(name = "Message.effect.tooManyEffect")
	public static String tooManyEffect = "&cToo many effect are activated!";
	@ConfigOptions(name = "Message.system.configReloaded")
	public static String configreloaded = "&aConfiguration was reloaded !";
	@ConfigOptions(name = "Message.gadget.OneGadgetByOneGadget")
	public static String onegadgetbygadgetmessage = "&cYou must wait to use an other gadget!";

	@ConfigOptions(name = "Name.pets")
	public static String petName = "%player's %ridername  ";
	@ConfigOptions(name = "Message.pet.notYourPet")
	public static String notYourPet = "&cThis is not your pet!";
	@ConfigOptions(name = "Message.gadget.youMustClearArea")
	public static String youMustClearTheArea = "&cYou must clear the area";
	@ConfigOptions(name = "Message.gadget.cooldown")
	public static String cooldownMessage = "&3ProdigyPack > &cYou cannot use &3%gadget &cfor &3%time s";

	@ConfigOptions(name = "Name.item.prodigypack")
	public static String prodigypackName = "&3&l>&b&l>&1&l> &6ProdigyPack &1&l<&b&l<&3&l<";

	@ConfigOptions(name = "Name.inventory.icon.effect.heart")
	public static String heartName = "&aChoose &4&lHeart &aeffect";
	@ConfigOptions(name = "Name.inventory.icon.effect.music")
	public static String musicName = "&aChoose &2&lMusic &aeffect";
	@ConfigOptions(name = "Name.inventory.icon.effect.water")
	public static String waterName = "&aChoose &3&lWater &aeffect";
	@ConfigOptions(name = "Name.inventory.icon.effect.lava")
	public static String lavaName = "&aChoose &cLava &aeffect";
	@ConfigOptions(name = "Name.inventory.icon.effect.witch")
	public static String witchName = "&aChoose &5&lWitch &aeffect";
	@ConfigOptions(name = "Name.inventory.icon.effect.spark")
	public static String sparkName = "&aChoose &f&lSpark &aeffect";
	@ConfigOptions(name = "Name.inventory.icon.effect.flame")
	public static String flameName = "&aChoose &4&lFlame &aeffect";
	@ConfigOptions(name = "Name.inventory.icon.effect.enchant")
	public static String enchantName = "&aChoose &7&lEnchant &aeffect";
	@ConfigOptions(name = "Name.inventory.icon.effect.portalEffect")
	public static String portalName = "&aChoose &6&lPortal &aeffect";
	@ConfigOptions(name = "Name.inventory.icon.effect.snow")
	public static String snowName = "&aChoose &f&lSnow &aeffect";
	@ConfigOptions(name = "Name.inventory.icon.effect.slime")
	public static String slimeName = "&aChoose &2&lSlime &aeffect";
	@ConfigOptions(name = "Name.inventory.icon.effect.angryVillager")
	public static String angryvillagerName = "&aChoose &6&lAngryVillager &aeffect";
	@ConfigOptions(name = "Name.inventory.icon.effect.magic")
	public static String magicName = "&aChoose &3&lMagic &aeffect";
	@ConfigOptions(name = "Name.inventory.icon.effect.snowShovel")
	public static String snowshovelName = "&aChoose &f&lsnowshovel &aeffect";
	@ConfigOptions(name = "Name.inventory.icon.effect.rainbow")
	public static String rainbowName = "&aChoose &d&lrainbow &aeffect";
	@ConfigOptions(name = "Name.inventory.icon.effect.removeEffectOrMorph")
	public static String stopEffectandMorphName = "&cRemove your current effect or morph";
	@ConfigOptions(name = "Name.inventory.icon.effect.removeEffect")
	public static String stopEffectName = "&cRemove your current effect";
	@ConfigOptions(name = "Name.inventory.icon.effect.removeMorph")
	public static String stopMorphName = "&cRemove your current morph";
	@ConfigOptions(name = "Name.inventory.icon.effect.isOn")
	public static String isOnName = "&7[&2&lOn&7]";
	@ConfigOptions(name = "Name.inventory.icon.effect.isOff")
	public static String isOffName = "&7[&4&lOff&7]";


	public Messages(final File file) {
		super(file, Arrays.asList("ProdigyGadgets Message"));
	}

}
