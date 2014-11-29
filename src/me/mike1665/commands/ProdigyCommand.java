package me.mike1665.commands;

import me.mike1665.Main.Main;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import fr.coco_gigpn.prodigygadget.effect.EffectManager;
import fr.coco_gigpn.prodigygadget.gadget.gadgets.AxeLauncher;
import fr.coco_gigpn.prodigygadget.gadget.gadgets.BatBlaster;
import fr.coco_gigpn.prodigygadget.gadget.gadgets.DiamondParty;
import fr.coco_gigpn.prodigygadget.gadget.gadgets.DiscoBall;
import fr.coco_gigpn.prodigygadget.gadget.gadgets.EnderBall;
import fr.coco_gigpn.prodigygadget.gadget.gadgets.FireworksPopper;
import fr.coco_gigpn.prodigygadget.gadget.gadgets.FlowerPopper;
import fr.coco_gigpn.prodigygadget.gadget.gadgets.Ghost;
import fr.coco_gigpn.prodigygadget.gadget.gadgets.GravityStation;
import fr.coco_gigpn.prodigygadget.gadget.gadgets.Grenade;
import fr.coco_gigpn.prodigygadget.gadget.gadgets.HalloweenHead;
import fr.coco_gigpn.prodigygadget.gadget.gadgets.HumanBomb;
import fr.coco_gigpn.prodigygadget.gadget.gadgets.PaintballGun;
import fr.coco_gigpn.prodigygadget.gadget.gadgets.PartyPopper;
import fr.coco_gigpn.prodigygadget.gadget.gadgets.PuppieLover;
import fr.coco_gigpn.prodigygadget.gadget.gadgets.QuakeGun;
import fr.coco_gigpn.prodigygadget.gadget.gadgets.Rocket;
import fr.coco_gigpn.prodigygadget.gadget.gadgets.SlimeHat;
import fr.coco_gigpn.prodigygadget.gadget.gadgets.SmokeBomb;
import fr.coco_gigpn.prodigygadget.gadget.gadgets.TntPopper;
import fr.coco_gigpn.prodigygadget.gadget.gadgets.Tornado;
import fr.coco_gigpn.prodigygadget.menu.menulist.Extra;
import fr.coco_gigpn.prodigygadget.menu.menulist.Gadgets;
import fr.coco_gigpn.prodigygadget.menu.menulist.Mounts;
import fr.coco_gigpn.prodigygadget.menu.menulist.Particles;
import fr.coco_gigpn.prodigygadget.menu.menulist.Pets;
import fr.coco_gigpn.prodigygadget.morph.MorphManager;
import fr.coco_gigpn.prodigygadget.morph.morphs.Astronot;
import fr.coco_gigpn.prodigygadget.morph.morphs.Blaze;
import fr.coco_gigpn.prodigygadget.morph.morphs.Cactus;
import fr.coco_gigpn.prodigygadget.morph.morphs.Enderman;
import fr.coco_gigpn.prodigygadget.morph.morphs.Ghast;
import fr.coco_gigpn.prodigygadget.morph.morphs.Vampire;
import fr.coco_gigpn.prodigygadget.morph.morphs.Witch;
import fr.coco_gigpn.prodigygadget.mount.MountManager;
import fr.coco_gigpn.prodigygadget.mount.mounts.AngelRider;
import fr.coco_gigpn.prodigygadget.mount.mounts.DarkRider;
import fr.coco_gigpn.prodigygadget.mount.mounts.GhostRider;
import fr.coco_gigpn.prodigygadget.mount.mounts.NyanRider;
import fr.coco_gigpn.prodigygadget.mount.mounts.PoseidonRider;
import fr.coco_gigpn.prodigygadget.particle.ParticleManager;
import fr.coco_gigpn.prodigygadget.particle.particles.CircleParticle;
import fr.coco_gigpn.prodigygadget.pet.PetManager;
import fr.coco_gigpn.prodigygadget.util.Configs;
import fr.coco_gigpn.prodigygadget.util.Messages;
import fr.coco_gigpn.prodigygadget.util.UtilInventory;
import fr.coco_gigpn.prodigygadget.util.UtilItem;
import fr.coco_gigpn.prodigygadget.util.UtilLag;
import fr.coco_gigpn.prodigygadget.util.UtilServer;

public class ProdigyCommand {
	
	static Main plugin;

	public static void setup(Main instance) {
		plugin = instance;
	}
	
	  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	  {
	    if ((cmd.getName().equalsIgnoreCase("prodigypack")) || 
	      (cmd.getName().equalsIgnoreCase("propack")))
	    {
	      Player p = (Player)sender;
	      if (args.length == 0) {
	        if ((p.hasPermission("prodigypack.help")) || 
	          (p.hasPermission("prodigypack.*")) || (p.isOp()))
	        {
	          p.sendMessage("§6>>> §2ProdigyPack Help §6<<<");
	          p.sendMessage("§2§l/propack give §7Give you the pack to open the menu");
	          p.sendMessage("§2§l/propack open §7to open the main menu");
	          p.sendMessage("§2§l/propack open:gadget §7to open the gadget menu");
	          p.sendMessage("§2§l/propack open:particle §7to open the particle menu");
	          p.sendMessage("§2§l/propack open:pet §7to open the pet menu");
	          p.sendMessage("§2§l/propack open:mount §7to open the pet menu");
	          p.sendMessage("§2§l/propack gadget:<gadget>");
	          p.sendMessage("§2§l/propack pet:<pet>");
	          p.sendMessage("§2§l/propack mount:<mount>");
	          p.sendMessage("§2§l/propack particle:<particle>");
	          p.sendMessage("§2§l/propack extra:<extra>");
	          p.sendMessage("§2§l/propack morph:<morph>");
	          p.sendMessage("§2§l/propack remove:<gadget,mount,particle,morph>");
	          p.sendMessage("§2§l/propack clear §7To clear all animation in the server");
	          p.sendMessage("§2§l/propack reload §7to reload the config file");
	          
	          EffectManager.addEffect(p, EffectManager.EffectType.Fountain);
	        }
	        else
	        {
	          p.sendMessage(Messages.nopermissioneffect.replace("&", "§"));
	        }
	      }
	      if (args.length == 1)
	      {
	        if (args[0].equalsIgnoreCase("help")) {
	          if ((p.hasPermission("prodigypack.help")) || 
	            (p.hasPermission("prodigypack.*")) || (p.isOp()))
	          {
	            p.sendMessage("§6>>> §2ProdigyPack Help §6<<<");
	            p.sendMessage("§2§l/propack give §7Give you the pack to open the menu");
	            p.sendMessage("§2§l/propack open §7to open the main menu");
	            p.sendMessage("§2§l/propack open:gadget §7to open the gadget menu");
	            p.sendMessage("§2§l/propack open:particle §7to open the particle menu");
	            p.sendMessage("§2§l/propack open:pet §7to open the pet menu");
	            p.sendMessage("§2§l/propack reload §7to reload the config file");
	          }
	          else
	          {
	            p.sendMessage(Messages.nopermissioneffect.replace("&", "§"));
	          }
	        }
	        if (args[0].equalsIgnoreCase("give")) {
	          if ((p.hasPermission("propack.give")) || 
	            (p.hasPermission("prodigypack.*")) || (p.isOp()))
	          {
	            ItemStack prodigypack = UtilItem.create(Configs.itemID, 1, 
	              (byte)Configs.itemData, 
	              Messages.prodigypackName.replace("&", "§"));
	            if (!p.getInventory().contains(prodigypack)) {
	              p.getInventory().addItem(new ItemStack[] { prodigypack });
	            }
	          }
	          else
	          {
	            p.sendMessage(Messages.nopermissioneffect.replace("&", "§"));
	          }
	        }
	        if (args[0].equalsIgnoreCase("tps")) {
	          if ((p.hasPermission("propack.tps")) || 
	            (p.hasPermission("prodigypack.*")) || (p.isOp())) {
	            p.sendMessage(Double.toString(UtilLag.getTPS()));
	          } else {
	            p.sendMessage(Messages.nopermissioneffect.replace("&", "§"));
	          }
	        }
	        if (args[0].equalsIgnoreCase("open")) {
	          if ((p.hasPermission("propack.open")) || 
	            (p.hasPermission("prodigypack.*")) || (p.isOp())) {
	            fr.coco_gigpn.prodigygadget.menu.menulist.Main.openMainGui(p);
	          } else {
	            p.sendMessage(Messages.nopermissioneffect.replace("&", "§"));
	          }
	        }
	        if (args[0].equalsIgnoreCase("open:gadget")) {
	          if ((p.hasPermission("propack.open.gadget")) || 
	            (p.hasPermission("prodigypack.*")) || (p.isOp())) {
	            Gadgets.openGadgetGui(p);
	          } else {
	            p.sendMessage(Messages.nopermissioneffect.replace("&", "§"));
	          }
	        }
	        if (args[0].equalsIgnoreCase("open:particle")) {
	          if ((p.hasPermission("propack.open.particle")) || 
	            (p.hasPermission("prodigypack.*")) || (p.isOp())) {
	            Particles.openParticleGui(p);
	          } else {
	            p.sendMessage(Messages.nopermissioneffect.replace("&", "§"));
	          }
	        }
	        if (args[0].equalsIgnoreCase("open:extra")) {
	          if ((p.hasPermission("propack.open.extra")) || 
	            (p.hasPermission("prodigypack.*")) || (p.isOp())) {
	            Extra.openCloudGui(p);
	          } else {
	            p.sendMessage(Messages.nopermissioneffect.replace("&", "§"));
	          }
	        }
	        if (args[0].equalsIgnoreCase("open:pet")) {
	          if ((p.hasPermission("propack.open.pet")) || 
	            (p.hasPermission("prodigypack.*")) || (p.isOp())) {
	            Pets.openMainGui(p);
	          } else {
	            p.sendMessage(Messages.nopermissioneffect.replace("&", "§"));
	          }
	        }
	        if (args[0].equalsIgnoreCase("open:mount")) {
	          if ((p.hasPermission("propack.open.mount")) || 
	            (p.hasPermission("prodigypack.*")) || (p.isOp())) {
	            Mounts.openPetGui(p);
	          } else {
	            p.sendMessage(Messages.nopermissioneffect.replace("&", "§"));
	          }
	        }
	        if (args[0].equalsIgnoreCase("open:morph")) {
	          if ((p.hasPermission("propack.open.morph")) || 
	            (p.hasPermission("prodigypack.*")) || (p.isOp())) {
	            Mounts.openPetGui(p);
	          } else {
	            p.sendMessage(Messages.nopermissioneffect.replace("&", "§"));
	          }
	        }
	        if (args[0].equalsIgnoreCase("reload")) {
	          if ((p.hasPermission("propack.reload")) || 
	            (p.hasPermission("prodigypack.*")) || (p.isOp())) {
	            try
	            {
	              plugin.config.load();
	              plugin.msg.load();
	              p.closeInventory();
	              p.sendMessage(Messages.configreloaded.replace("&", "§"));
	            }
	            catch (InvalidConfigurationException e)
	            {
	              e.printStackTrace();
	            }
	          } else {
	            p.sendMessage(Messages.nopermissioneffect.replace("&", "§"));
	          }
	        }
	        if (args[0].equalsIgnoreCase("gadget:diamondparty")) {
	          if ((p.hasPermission("propack.gadget.diamondparty")) || 
	            (p.hasPermission("propack.gadget.*")) || 
	            (p.hasPermission("prodigypack.*")) || (p.isOp())) {
	            DiamondParty.activate(p);
	          } else {
	            p.sendMessage(Messages.nopermissioneffect.replace("&", "§"));
	          }
	        }
	        if (args[0].equalsIgnoreCase("gadget:batblaster")) {
	          if ((p.hasPermission("prodigypack.use.batblaster")) || 
	            (p.hasPermission("prodigypack.use.*")) || 
	            (p.hasPermission("prodigypack.*")) || (p.isOp())) {
	            BatBlaster.activate(p);
	          } else {
	            p.sendMessage(Messages.nopermissioneffect.replace("&", "§"));
	          }
	        }
	        if (args[0].equalsIgnoreCase("gadget:paintballgun")) {
	          if ((p.hasPermission("propack.gadget.paintballgun")) || 
	            (p.hasPermission("propack.gadget.*")) || 
	            (p.hasPermission("prodigypack.*")) || (p.isOp())) {
	            PaintballGun.activate(p);
	          } else {
	            p.sendMessage(Messages.nopermissioneffect.replace("&", "§"));
	          }
	        }
	        if (args[0].equalsIgnoreCase("gadget:discoball")) {
	          if ((p.hasPermission("propack.gadget.discoball")) || 
	            (p.hasPermission("propack.gadget.*")) || 
	            (p.hasPermission("prodigypack.*")) || (p.isOp())) {
	            DiscoBall.activate(p);
	          } else {
	            p.sendMessage(Messages.nopermissioneffect.replace("&", "§"));
	          }
	        }
	        if (args[0].equalsIgnoreCase("gadget:fireworkpopper")) {
	          if ((p.hasPermission("propack.gadget.fireworkpopper")) || 
	            (p.hasPermission("propack.gadget.*")) || 
	            (p.hasPermission("prodigypack.*")) || (p.isOp())) {
	            FireworksPopper.activate(p);
	          } else {
	            p.sendMessage(Messages.nopermissioneffect.replace("&", "§"));
	          }
	        }
	        if (args[0].equalsIgnoreCase("gadget:humanbomb")) {
	          if ((p.hasPermission("propack.gadget.humanbomb")) || 
	            (p.hasPermission("prodigypack.use.*")) || 
	            (p.hasPermission("prodigypack.*")) || (p.isOp())) {
	            HumanBomb.activate(p);
	          } else {
	            p.sendMessage(Messages.nopermissioneffect.replace("&", "§"));
	          }
	        }
	        if (args[0].equalsIgnoreCase("gadget:rocket")) {
	          if ((p.hasPermission("propack.gadget.rocket")) || 
	            (p.hasPermission("propack.gadget.*")) || 
	            (p.hasPermission("prodigypack.*")) || (p.isOp())) {
	            Rocket.activate(p);
	          } else {
	            p.sendMessage(Messages.nopermissioneffect.replace("&", "§"));
	          }
	        }
	        if (args[0].equalsIgnoreCase("gadget:quakegun")) {
	          if ((p.hasPermission("propack.gadget.quakegun")) || 
	            (p.hasPermission("propack.gadget.*")) || 
	            (p.hasPermission("prodigypack.*")) || (p.isOp())) {
	            QuakeGun.activate(p);
	          } else {
	            p.sendMessage(Messages.nopermissioneffect.replace("&", "§"));
	          }
	        }
	        if (args[0].equalsIgnoreCase("gadget:slimehat")) {
	          if ((p.hasPermission("propack.gadget.slimehat")) || 
	            (p.hasPermission("propack.gadget.*")) || 
	            (p.hasPermission("prodigypack.*")) || (p.isOp())) {
	            SlimeHat.activate(p);
	          } else {
	            p.sendMessage(Messages.nopermissioneffect.replace("&", "§"));
	          }
	        }
	        if (args[0].equalsIgnoreCase("gadget:halloweenhead")) {
	          if ((p.hasPermission("propack.gadget.halloweenhead")) || 
	            (p.hasPermission("propack.gadget.*")) || 
	            (p.hasPermission("prodigypack.*")) || (p.isOp())) {
	            HalloweenHead.activate(p);
	          } else {
	            p.sendMessage(Messages.nopermissioneffect.replace("&", "§"));
	          }
	        }
	        if (args[0].equalsIgnoreCase("gadget:grenade")) {
	          if ((p.hasPermission("propack.gadget.grenade")) || 
	            (p.hasPermission("propack.gadget.*")) || 
	            (p.hasPermission("prodigypack.*")) || (p.isOp())) {
	            Grenade.activate(p);
	          } else {
	            p.sendMessage(Messages.nopermissioneffect.replace("&", "§"));
	          }
	        }
	        if (args[0].equalsIgnoreCase("gadget:partypopper")) {
	          if ((p.hasPermission("propack.gadget.partypopper")) || 
	            (p.hasPermission("propack.gadget.*")) || 
	            (p.hasPermission("prodigypack.*")) || (p.isOp())) {
	            PartyPopper.activate(p);
	          } else {
	            p.sendMessage(Messages.nopermissioneffect.replace("&", "§"));
	          }
	        }
	        if (args[0].equalsIgnoreCase("gadget:tntpopper")) {
	          if ((p.hasPermission("propack.gadget.tntpopper")) || 
	            (p.hasPermission("propack.gadget.*")) || 
	            (p.hasPermission("prodigypack.*")) || (p.isOp())) {
	            TntPopper.activate(p);
	          } else {
	            p.sendMessage(Messages.nopermissioneffect.replace("&", "§"));
	          }
	        }
	        if (args[0].equalsIgnoreCase("gadget:smokebomb")) {
	          if ((p.hasPermission("propack.gadget.smokebomb")) || 
	            (p.hasPermission("propack.gadget.*")) || 
	            (p.hasPermission("prodigypack.*")) || (p.isOp())) {
	            SmokeBomb.activate(p);
	          } else {
	            p.sendMessage(Messages.nopermissioneffect.replace("&", "§"));
	          }
	        }
	        if (args[0].equalsIgnoreCase("gadget:ghost")) {
	          if ((p.hasPermission("propack.gadget.ghost")) || 
	            (p.hasPermission("propack.gadget.*")) || 
	            (p.hasPermission("prodigypack.*")) || (p.isOp())) {
	            Ghost.activate(p);
	          } else {
	            p.sendMessage(Messages.nopermissioneffect.replace("&", "§"));
	          }
	        }
	        if (args[0].equalsIgnoreCase("gadget:tornado")) {
	          if ((p.hasPermission("propack.gadget.tornadogadget")) || 
	            (p.hasPermission("propack.gadget.*")) || 
	            (p.hasPermission("prodigypack.*")) || (p.isOp())) {
	            Tornado.activate(p);
	          } else {
	            p.sendMessage(Messages.nopermissioneffect.replace("&", "§"));
	          }
	        }
	        if (args[0].equalsIgnoreCase("gadget:axelauncher")) {
	          if ((p.hasPermission("propack.gadget.axelauncher")) || 
	            (p.hasPermission("propack.gadget.*")) || 
	            (p.hasPermission("prodigypack.*")) || (p.isOp())) {
	            AxeLauncher.activate(p);
	          } else {
	            p.sendMessage(Messages.nopermissioneffect.replace("&", "§"));
	          }
	        }
	        if (args[0].equalsIgnoreCase("gadget:flowerpopper")) {
	          if ((p.hasPermission("propack.gadget.flowerpopper")) || 
	            (p.hasPermission("propack.gadget.*")) || 
	            (p.hasPermission("prodigypack.*")) || (p.isOp())) {
	            FlowerPopper.activate(p);
	          } else {
	            p.sendMessage(Messages.nopermissioneffect.replace("&", "§"));
	          }
	        }
	        if (args[0].equalsIgnoreCase("gadget:enderball")) {
	          if ((p.hasPermission("propack.gadget.enderball")) || 
	            (p.hasPermission("propack.gadget.*")) || 
	            (p.hasPermission("prodigypack.*")) || (p.isOp())) {
	            EnderBall.activate(p);
	          } else {
	            p.sendMessage(Messages.nopermissioneffect.replace("&", "§"));
	          }
	        }
	        if (args[0].equalsIgnoreCase("gadget:gravitystation")) {
	          if ((p.hasPermission("propack.gadget.gravitystation")) || 
	            (p.hasPermission("propack.gadget.*")) || 
	            (p.hasPermission("prodigypack.*")) || (p.isOp())) {
	            GravityStation.activate(p);
	          } else {
	            p.sendMessage(Messages.nopermissioneffect.replace("&", "§"));
	          }
	        }
	        if (args[0].equalsIgnoreCase("gadget:puppielover")) {
	          if ((p.hasPermission("propack.gadget.puppielover")) || 
	            (p.hasPermission("propack.gadget.*")) || 
	            (p.hasPermission("prodigypack.*")) || (p.isOp())) {
	            PuppieLover.activate(p);
	          } else {
	            p.sendMessage(Messages.nopermissioneffect.replace("&", "§"));
	          }
	        }
	        if (args[0].equalsIgnoreCase("particle:heart")) {
	          if ((p.hasPermission("propack.particle.heart")) || 
	            (p.hasPermission("propack.particle.*")) || 
	            (p.hasPermission("prodigypack.*")) || (p.isOp())) {
	            CircleParticle.Activate(p, ParticleManager.ParticleType.Heart);
	          } else {
	            p.sendMessage(Messages.nopermissioneffect.replace("&", "§"));
	          }
	        }
	        if (args[0].equalsIgnoreCase("particle:music")) {
	          if ((p.hasPermission("propack.particle.music")) || 
	            (p.hasPermission("propack.particle.*")) || 
	            (p.hasPermission("prodigypack.*")) || (p.isOp())) {
	            CircleParticle.Activate(p, ParticleManager.ParticleType.Music);
	          } else {
	            p.sendMessage(Messages.nopermissioneffect.replace("&", "§"));
	          }
	        }
	        if (args[0].equalsIgnoreCase("particle:flame")) {
	          if ((p.hasPermission("propack.particle.flame")) || 
	            (p.hasPermission("propack.particle.*")) || 
	            (p.hasPermission("prodigypack.*")) || (p.isOp())) {
	            CircleParticle.Activate(p, ParticleManager.ParticleType.Flame);
	          } else {
	            p.sendMessage(Messages.nopermissioneffect.replace("&", "§"));
	          }
	        }
	        if (args[0].equalsIgnoreCase("particle:water")) {
	          if ((p.hasPermission("propack.particle.water")) || 
	            (p.hasPermission("propack.particle.*")) || 
	            (p.hasPermission("prodigypack.*")) || (p.isOp())) {
	            CircleParticle.Activate(p, ParticleManager.ParticleType.Water);
	          } else {
	            p.sendMessage(Messages.nopermissioneffect.replace("&", "§"));
	          }
	        }
	        if (args[0].equalsIgnoreCase("particle:lava")) {
	          if ((p.hasPermission("propack.particle.lava")) || 
	            (p.hasPermission("propack.particle.*")) || 
	            (p.hasPermission("prodigypack.*")) || (p.isOp())) {
	            CircleParticle.Activate(p, ParticleManager.ParticleType.Lava);
	          } else {
	            p.sendMessage(Messages.nopermissioneffect.replace("&", "§"));
	          }
	        }
	        if (args[0].equalsIgnoreCase("particle:witch")) {
	          if ((p.hasPermission("propack.particle.witch")) || 
	            (p.hasPermission("propack.particle.*")) || 
	            (p.hasPermission("prodigypack.*")) || (p.isOp())) {
	            CircleParticle.Activate(p, ParticleManager.ParticleType.Witch);
	          } else {
	            p.sendMessage(Messages.nopermissioneffect.replace("&", "§"));
	          }
	        }
	        if (args[0].equalsIgnoreCase("particle:spark")) {
	          if ((p.hasPermission("propack.particle.spark")) || 
	            (p.hasPermission("propack.particle.*")) || 
	            (p.hasPermission("prodigypack.*")) || (p.isOp())) {
	            CircleParticle.Activate(p, ParticleManager.ParticleType.Spark);
	          } else {
	            p.sendMessage(Messages.nopermissioneffect.replace("&", "§"));
	          }
	        }
	        if (args[0].equalsIgnoreCase("particle:enchant")) {
	          if ((p.hasPermission("propack.particle.enchant")) || 
	            (p.hasPermission("propack.particle.*")) || 
	            (p.hasPermission("prodigypack.*")) || (p.isOp())) {
	            CircleParticle.Activate(p, ParticleManager.ParticleType.Enchantement);
	          } else {
	            p.sendMessage(Messages.nopermissioneffect.replace("&", "§"));
	          }
	        }
	        if (args[0].equalsIgnoreCase("particle:portal")) {
	          if ((p.hasPermission("propack.particle.portal")) || 
	            (p.hasPermission("propack.particle.*")) || 
	            (p.hasPermission("prodigypack.*")) || (p.isOp())) {
	            CircleParticle.Activate(p, ParticleManager.ParticleType.Portal);
	          } else {
	            p.sendMessage(Messages.nopermissioneffect.replace("&", "§"));
	          }
	        }
	        if (args[0].equalsIgnoreCase("particle:angryvillager")) {
	          if ((p.hasPermission("propack.particle.angryvillager")) || 
	            (p.hasPermission("propack.particle.*")) || 
	            (p.hasPermission("prodigypack.*")) || (p.isOp())) {
	            CircleParticle.Activate(p, ParticleManager.ParticleType.AngryVillager);
	          } else {
	            p.sendMessage(Messages.nopermissioneffect.replace("&", "§"));
	          }
	        }
	        if (args[0].equalsIgnoreCase("particle:slime")) {
	          if ((p.hasPermission("propack.particle.slime")) || 
	            (p.hasPermission("propack.particle.*")) || 
	            (p.hasPermission("prodigypack.*")) || (p.isOp())) {
	            CircleParticle.Activate(p, ParticleManager.ParticleType.Slime);
	          } else {
	            p.sendMessage(Messages.nopermissioneffect.replace("&", "§"));
	          }
	        }
	        if (args[0].equalsIgnoreCase("particle:snow")) {
	          if ((p.hasPermission("propack.particle.snow")) || 
	            (p.hasPermission("propack.particle.*")) || 
	            (p.hasPermission("prodigypack.*")) || (p.isOp())) {
	            CircleParticle.Activate(p, ParticleManager.ParticleType.Snow);
	          } else {
	            p.sendMessage(Messages.nopermissioneffect.replace("&", "§"));
	          }
	        }
	        if (args[0].equalsIgnoreCase("particle:snowshovel")) {
	          if ((p.hasPermission("propack.particle.snowshovel")) || 
	            (p.hasPermission("propack.particle.*")) || 
	            (p.hasPermission("prodigypack.*")) || (p.isOp())) {
	            CircleParticle.Activate(p, ParticleManager.ParticleType.SnowShovel);
	          } else {
	            p.sendMessage(Messages.nopermissioneffect.replace("&", "§"));
	          }
	        }
	        if (args[0].equalsIgnoreCase("particle:rainbow")) {
	          if ((p.hasPermission("propack.particle.rainbow")) || 
	            (p.hasPermission("propack.particle.*")) || 
	            (p.hasPermission("prodigypack.*")) || (p.isOp())) {
	            CircleParticle.Activate(p, ParticleManager.ParticleType.Rainbow);
	          } else {
	            p.sendMessage(Messages.nopermissioneffect.replace("&", "§"));
	          }
	        }
	        if (args[0].equalsIgnoreCase("particle:magic")) {
	          if ((p.hasPermission("propack.particle.magic")) || 
	            (p.hasPermission("propack.particle.*")) || 
	            (p.hasPermission("prodigypack.*")) || (p.isOp())) {
	            CircleParticle.Activate(p, ParticleManager.ParticleType.MagicCrit);
	          } else {
	            p.sendMessage(Messages.nopermissioneffect.replace("&", "§"));
	          }
	        }
	        if (args[0].equalsIgnoreCase("mount:angelrider")) {
	          if ((p.hasPermission("propack.mount.angelrider")) || 
	            (p.hasPermission("propack.mount.*")) || 
	            (p.hasPermission("prodigypack.*")) || (p.isOp())) {
	            AngelRider.playAngelRider(p);
	          } else {
	            p.sendMessage(Messages.nopermissioneffect.replace("&", "§"));
	          }
	        }
	        if (args[0].equalsIgnoreCase("mount:ghostrider")) {
	          if ((p.hasPermission("propack.mount.ghostrider")) || 
	            (p.hasPermission("propack.mount.*")) || 
	            (p.hasPermission("prodigypack.*")) || (p.isOp())) {
	            GhostRider.playAngelRider(p);
	          } else {
	            p.sendMessage(Messages.nopermissioneffect.replace("&", "§"));
	          }
	        }
	        if (args[0].equalsIgnoreCase("mount:darkrider")) {
	          if ((p.hasPermission("propack.mount.darkrider")) || 
	            (p.hasPermission("propack.mount.*")) || 
	            (p.hasPermission("prodigypack.*")) || (p.isOp())) {
	            DarkRider.playAngelRider(p);
	          } else {
	            p.sendMessage(Messages.nopermissioneffect.replace("&", "§"));
	          }
	        }
	        if (args[0].equalsIgnoreCase("mount:poseidonrider")) {
	          if ((p.hasPermission("propack.mount.poseidonrider")) || 
	            (p.hasPermission("propack.mount.*")) || 
	            (p.hasPermission("prodigypack.*")) || (p.isOp())) {
	            PoseidonRider.playAngelRider(p);
	          } else {
	            p.sendMessage(Messages.nopermissioneffect.replace("&", "§"));
	          }
	        }
	        if (args[0].equalsIgnoreCase("mount:nyanrider")) {
	          if ((p.hasPermission("propack.mount.nyanrider")) || 
	            (p.hasPermission("propack.mount.*")) || 
	            (p.hasPermission("prodigypack.*")) || (p.isOp())) {
	            NyanRider.playAngelRider(p);
	          } else {
	            p.sendMessage(Messages.nopermissioneffect.replace("&", "§"));
	          }
	        }
	        if (args[0].equalsIgnoreCase("pet:wolf")) {
	          if ((p.hasPermission("propack.pet.wolf")) || 
	            (p.hasPermission("propack.pet.*")) || 
	            (p.hasPermission("prodigypack.*")) || (p.isOp())) {
	            PetManager.ActivatePet(p, PetManager.PetType.Wolf);
	          } else {
	            p.sendMessage(Messages.nopermissioneffect.replace("&", "§"));
	          }
	        }
	        if (args[0].equalsIgnoreCase("pet:pig")) {
	          if ((p.hasPermission("propack.pett.pig")) || 
	            (p.hasPermission("propack.pet.*")) || 
	            (p.hasPermission("prodigypack.*")) || (p.isOp())) {
	            PetManager.ActivatePet(p, PetManager.PetType.Pig);
	          } else {
	            p.sendMessage(Messages.nopermissioneffect.replace("&", "§"));
	          }
	        }
	        if (args[0].equalsIgnoreCase("pet:sheep")) {
	          if ((p.hasPermission("propack.pet.sheep")) || 
	            (p.hasPermission("propack.pet.*")) || 
	            (p.hasPermission("prodigypack.*")) || (p.isOp())) {
	            PetManager.ActivatePet(p, PetManager.PetType.Sheep);
	          } else {
	            p.sendMessage(Messages.nopermissioneffect.replace("&", "§"));
	          }
	        }
	        if (args[0].equalsIgnoreCase("pet:ocelot")) {
	          if ((p.hasPermission("propack.pet.ocelot")) || 
	            (p.hasPermission("propack.pet.*")) || 
	            (p.hasPermission("prodigypack.*")) || (p.isOp())) {
	            PetManager.ActivatePet(p, PetManager.PetType.Ocelot);
	          } else {
	            p.sendMessage(Messages.nopermissioneffect.replace("&", "§"));
	          }
	        }
	        if (args[0].equalsIgnoreCase("pet:cow")) {
	          if ((p.hasPermission("propack.pet.cow")) || 
	            (p.hasPermission("propack.pet.*")) || 
	            (p.hasPermission("prodigypack.*")) || (p.isOp())) {
	            PetManager.ActivatePet(p, PetManager.PetType.Cow);
	          } else {
	            p.sendMessage(Messages.nopermissioneffect.replace("&", "§"));
	          }
	        }
	        if (args[0].equalsIgnoreCase("extra:cloud")) {
	          if ((p.hasPermission("propack.extra.cloud")) || 
	            (p.hasPermission("propack.extra.*")) || 
	            (p.hasPermission("prodigypack.*")) || (p.isOp())) {
	            EffectManager.addEffect(p, EffectManager.EffectType.Cloud);
	          } else {
	            p.sendMessage(Messages.nopermissioneffect.replace("&", "§"));
	          }
	        }
	        if (args[0].equalsIgnoreCase("extra:cloudlightning")) {
	          if ((p.hasPermission("propack.extra.cloudlightning")) || 
	            (p.hasPermission("propack.extra.*")) || 
	            (p.hasPermission("prodigypack.*")) || (p.isOp())) {
	            EffectManager.addEffect(p, EffectManager.EffectType.CloudLight);
	          } else {
	            p.sendMessage(Messages.nopermissioneffect.replace("&", "§"));
	          }
	        }
	        if (args[0].equalsIgnoreCase("extra:cloudsnow")) {
	          if ((p.hasPermission("propack.extra.cloudsnow")) || 
	            (p.hasPermission("propack.extra.*")) || 
	            (p.hasPermission("prodigypack.*")) || (p.isOp())) {
	            EffectManager.addEffect(p, EffectManager.EffectType.CloudSnow);
	          } else {
	            p.sendMessage(Messages.nopermissioneffect.replace("&", "§"));
	          }
	        }
	        if (args[0].equalsIgnoreCase("extra:helix")) {
	          if ((p.hasPermission("propack.extra.helix")) || 
	            (p.hasPermission("propack.extra.*")) || 
	            (p.hasPermission("prodigypack.*")) || (p.isOp())) {
	            EffectManager.addEffect(p, EffectManager.EffectType.Helix);
	          } else {
	            p.sendMessage(Messages.nopermissioneffect.replace("&", "§"));
	          }
	        }
	        if (args[0].equalsIgnoreCase("extra:tornado")) {
	          if ((p.hasPermission("propack.extra.tornado")) || 
	            (p.hasPermission("propack.extra.*")) || 
	            (p.hasPermission("prodigypack.*")) || (p.isOp())) {
	            EffectManager.addEffect(p, EffectManager.EffectType.Tornado);
	          } else {
	            p.sendMessage(Messages.nopermissioneffect.replace("&", "§"));
	          }
	        }
	        if (args[0].equalsIgnoreCase("extra:shield")) {
	          if ((p.hasPermission("propack.extra.shield")) || 
	            (p.hasPermission("propack.extra.*")) || 
	            (p.hasPermission("prodigypack.*")) || (p.isOp())) {
	            EffectManager.addEffect(p, EffectManager.EffectType.Shield);
	          } else {
	            p.sendMessage(Messages.nopermissioneffect.replace("&", "§"));
	          }
	        }
	        if (args[0].equalsIgnoreCase("extra:flamelilly")) {
	          if ((p.hasPermission("propack.extra.flamelilly")) || 
	            (p.hasPermission("propack.extra.*")) || 
	            (p.hasPermission("prodigypack.*")) || (p.isOp())) {
	            EffectManager.addEffect(p, EffectManager.EffectType.FlameLilly);
	          } else {
	            p.sendMessage(Messages.nopermissioneffect.replace("&", "§"));
	          }
	        }
	        if (args[0].equalsIgnoreCase("extra:fountain")) {
	          if ((p.hasPermission("propack.extra.fountain")) || 
	            (p.hasPermission("propack.extra.*")) || 
	            (p.hasPermission("prodigypack.*")) || (p.isOp())) {
	            EffectManager.addEffect(p, EffectManager.EffectType.Fountain);
	          } else {
	            p.sendMessage(Messages.nopermissioneffect.replace("&", "§"));
	          }
	        }
	        if (args[0].equalsIgnoreCase("extra:vortex")) {
	          if ((p.hasPermission("propack.extra.vortex")) || 
	            (p.hasPermission("propack.extra.*")) || 
	            (p.hasPermission("prodigypack.*")) || (p.isOp())) {
	            EffectManager.addEffect(p, EffectManager.EffectType.Vortex);
	          } else {
	            p.sendMessage(Messages.nopermissioneffect.replace("&", "§"));
	          }
	        }
	        if (args[0].equalsIgnoreCase("extra:hourglass")) {
	          if ((p.hasPermission("propack.extra.hourglass")) || 
	            (p.hasPermission("propack.extra.*")) || 
	            (p.hasPermission("prodigypack.*")) || (p.isOp())) {
	            EffectManager.addEffect(p, EffectManager.EffectType.HourGlass);
	          } else {
	            p.sendMessage(Messages.nopermissioneffect.replace("&", "§"));
	          }
	        }
	        if (args[0].equalsIgnoreCase("morph:vampire")) {
	          if ((p.hasPermission("propack.morph.vampire")) || 
	            (p.hasPermission("propack.morph.*")) || 
	            (p.hasPermission("prodigypack.*")) || (p.isOp())) {
	            Vampire.playDraculaToPlayer(p);
	          } else {
	            p.sendMessage(Messages.nopermissioneffect.replace("&", "§"));
	          }
	        }
	        if (args[0].equalsIgnoreCase("morph:cactus")) {
	          if ((p.hasPermission("propack.morph.cactus")) || 
	            (p.hasPermission("propack.morph.*")) || 
	            (p.hasPermission("prodigypack.*")) || (p.isOp())) {
	            Cactus.playCactusToPlayer(p);
	          } else {
	            p.sendMessage(Messages.nopermissioneffect.replace("&", "§"));
	          }
	        }
	        if (args[0].equalsIgnoreCase("morph:witch")) {
	          if ((p.hasPermission("propack.morph.witch")) || 
	            (p.hasPermission("propack.morph.*")) || 
	            (p.hasPermission("prodigypack.*")) || (p.isOp())) {
	            Witch.playWitchToPlayer(p);
	          } else {
	            p.sendMessage(Messages.nopermissioneffect.replace("&", "§"));
	          }
	        }
	        if (args[0].equalsIgnoreCase("morph:blaze")) {
	          if ((p.hasPermission("propack.morph.blaze")) || 
	            (p.hasPermission("propack.morph.*")) || 
	            (p.hasPermission("prodigypack.*")) || (p.isOp())) {
	            Blaze.playBlazeToPlayer(p);
	          } else {
	            p.sendMessage(Messages.nopermissioneffect.replace("&", "§"));
	          }
	        }
	        if (args[0].equalsIgnoreCase("morph:astronot")) {
	          if ((p.hasPermission("propack.morph.astronot")) || 
	            (p.hasPermission("propack.morph.*")) || 
	            (p.hasPermission("prodigypack.*")) || (p.isOp())) {
	            Astronot.playAstronotToPlayer(p);
	          } else {
	            p.sendMessage(Messages.nopermissioneffect.replace("&", "§"));
	          }
	        }
	        if (args[0].equalsIgnoreCase("morph:ghast")) {
	          if ((p.hasPermission("propack.morph.ghast")) || 
	            (p.hasPermission("propack.morph.*")) || 
	            (p.hasPermission("prodigypack.*")) || (p.isOp())) {
	            Ghast.playGhastToPlayer(p);
	          } else {
	            p.sendMessage(Messages.nopermissioneffect.replace("&", "§"));
	          }
	        }
	        if (args[0].equalsIgnoreCase("morph:enderman")) {
	          if ((p.hasPermission("propack.morph.enderman")) || 
	            (p.hasPermission("propack.morph.*")) || 
	            (p.hasPermission("prodigypack.*")) || (p.isOp())) {
	            Enderman.playEndermanToPlayer(p);
	          } else {
	            p.sendMessage(Messages.nopermissioneffect.replace("&", "§"));
	          }
	        }
	        if (args[0].equalsIgnoreCase("remove:particle")) {
	          EffectManager.removeEffect(p);
	        }
	        if (args[0].equalsIgnoreCase("remove:gadget")) {
	          UtilInventory.removeCurrentGadget(p);
	        }
	        if (args[0].equalsIgnoreCase("remove:pet")) {
	          PetManager.removeCurrentPet(p, true);
	        }
	        if (args[0].equalsIgnoreCase("remove:mount")) {
	          MountManager.removeCurrentPet(p, true);
	        }
	        if (args[0].equalsIgnoreCase("remove:morph")) {
	          MorphManager.removeMorph(p, true);
	        }
	        if (args[0].equalsIgnoreCase("clear")) {
	          if ((p.hasPermission("prodigypack.clear")) || 
	            (p.hasPermission("prodigypack.*")) || (p.isOp()))
	          {
	            p.sendMessage("§cAll things was cleared !");
	            for (Player players : UtilServer.getPlayers())
	            {
	              EffectManager.removeEffect(players);
	              MorphManager.removeMorph(players, false);
	              PetManager.removeCurrentPet(players, false);
	              MountManager.removeCurrentPet(players, false);
	            }
	          }
	          else
	          {
	            p.sendMessage(Messages.nopermissioneffect.replace("&", "§"));
	          }
	        }
	      }
	    }
	    return true;
	  }

}
