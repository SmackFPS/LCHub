package fr.coco_gigpn.prodigygadget.menu;

import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.plugin.PluginManager;
import fr.coco_gigpn.prodigygadget.effect.EffectManager;
import fr.coco_gigpn.prodigygadget.effect.EffectManager.EffectType;
import fr.coco_gigpn.prodigygadget.extra.ExtraManager;
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
import fr.coco_gigpn.prodigygadget.menu.menulist.Main;
import fr.coco_gigpn.prodigygadget.menu.menulist.Morphs;
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
import fr.coco_gigpn.prodigygadget.particle.ParticleManager.ParticleType;
import fr.coco_gigpn.prodigygadget.particle.particles.CircleParticle;
import fr.coco_gigpn.prodigygadget.pet.PetManager;
import fr.coco_gigpn.prodigygadget.pet.PetManager.PetType;
import fr.coco_gigpn.prodigygadget.util.Messages;
import fr.coco_gigpn.prodigygadget.util.UtilInventory;

public class MenuManager implements Listener {



	@EventHandler
	public void onInventoryClick(InventoryClickEvent e) {

		if (!e.getInventory().getName().equals(Messages.mainMenuPrefix.replace("&", "§")) 
				&& !e.getInventory().getName().equals(Messages.gadgetMenuPrefix.replace("&", "§")) 
				&& !e.getInventory().getName().equals(Messages.particleMenuPrefix.replace("&", "§")) 
				&& !e.getInventory().getName().equals(Messages.extraMenuPrefix.replace("&", "§")) 
				&& !e.getInventory().getName().equals(Messages.mountMenuPrefix.replace("&", "§"))
				&& !e.getInventory().getName().equals(Messages.morphMenuPrefix.replace("&", "§"))
				&& !e.getInventory().getName().equals(Messages.petMenuPrefix.replace("&", "§"))) return;

		Player p = (Player) e.getWhoClicked(); 
		e.setCancelled(true);

		if (e.getCurrentItem() == null || !e.getCurrentItem().hasItemMeta()) {
			p.closeInventory();
			p.playSound(p.getLocation(), Sound.ITEM_BREAK, 1, 1);
			return;
		} 


		switch (e.getCurrentItem().getType()) {

		case PISTON_BASE:
			if (e.getCurrentItem().getItemMeta().getDisplayName().equals(Messages.closeName.replace("&", "§"))) {
				p.closeInventory();
			}
			if (e.getCurrentItem().getItemMeta().getDisplayName().equals(Messages.returnMainName.replace("&", "§"))) {
				p.closeInventory();
				Main.openMainGui(p);
			}
			break;

		case WHEAT:
			if (e.getCurrentItem().getItemMeta().getDisplayName().equals(Messages.cowName.replace("&", "§"))) {
				if (p.hasPermission("propack.pet.cow")|| p.hasPermission("propack.pet.*") || p.hasPermission("prodigypack.*") || p.isOp()) {
					PetManager.removeCurrentPet(p, false);
					PetManager.ActivatePet(p, PetType.Cow);
					p.closeInventory();
				}  else {
					p.sendMessage(Messages.nopermissioneffect.replace("&", "§"));
				}

			}
			break;
		case RAW_BEEF:
			if (e.getCurrentItem().getItemMeta().getDisplayName().equals(Messages.pigName.replace("&", "§"))) {
				if (p.hasPermission("propack.pet.pig")|| p.hasPermission("propack.pet.*") || p.hasPermission("prodigypack.*") || p.isOp()) {
					PetManager.removeCurrentPet(p, false);
					PetManager.ActivatePet(p, PetType.Pig);
					p.closeInventory();
				} else {
					p.sendMessage(Messages.nopermissioneffect.replace("&", "§"));
				}

			}
			break;
		case RAW_FISH:
			if (e.getCurrentItem().getItemMeta().getDisplayName().equals(Messages.shieldName.replace("&", "§"))) {
				if (p.hasPermission("propack.extra.shield")|| p.hasPermission("propack.extra.*") || p.hasPermission("prodigypack.*") || p.isOp()) {
					EffectManager.addEffect(p, EffectType.Shield);
					p.closeInventory();
				} else {
					p.sendMessage(Messages.nopermissioneffect.replace("&", "§"));
				}

			}
			break;

		case CLAY_BALL:
			if (e.getCurrentItem().getItemMeta().getDisplayName().equals(Messages.sheepName.replace("&", "§"))) {
				if (p.hasPermission("propack.pet.sheep")|| p.hasPermission("propack.pet.*") || p.hasPermission("prodigypack.*") || p.isOp()) {
					PetManager.removeCurrentPet(p, false);
					PetManager.ActivatePet(p, PetType.Sheep);
					p.closeInventory();
				} else {
					p.sendMessage(Messages.nopermissioneffect.replace("&", "§"));
				}

			}
			break;
		case MONSTER_EGG:
			if (e.getCurrentItem().getItemMeta().getDisplayName().equals(Messages.angelriderName.replace("&", "§"))) {
				if (p.hasPermission("propack.mount.angelrider")|| p.hasPermission("propack.mount.*") || p.hasPermission("prodigypack.*") || p.isOp()) {
					AngelRider.playAngelRider(p);
					p.closeInventory();
				} else {
					p.sendMessage(Messages.nopermissioneffect.replace("&", "§"));
				}
			}

			if (e.getCurrentItem().getItemMeta().getDisplayName().equals(Messages.ghostriderName.replace("&", "§"))) {
				if (p.hasPermission("propack.mount.ghostrider")|| p.hasPermission("propack.mount.*") || p.hasPermission("prodigypack.*") || p.isOp()) {
					MountManager.removeCurrentPet(p, false);
					GhostRider.playAngelRider(p);
					p.closeInventory();
				} else {
					p.sendMessage(Messages.nopermissioneffect.replace("&", "§"));
				}
			}

			if (e.getCurrentItem().getItemMeta().getDisplayName().equals(Messages.nyanriderName.replace("&", "§"))) {
				if (p.hasPermission("propack.mount.nyanrider")|| p.hasPermission("propack.mount.*") || p.hasPermission("prodigypack.*") || p.isOp()) {
					MountManager.removeCurrentPet(p, false);
					NyanRider.playAngelRider(p);
					p.closeInventory();
				} else {
					p.sendMessage(Messages.nopermissioneffect.replace("&", "§"));
				}
			}
			if (e.getCurrentItem().getItemMeta().getDisplayName().equals(Messages.darkriderName.replace("&", "§"))) {
				if (p.hasPermission("propack.mount.darkrider")|| p.hasPermission("propack.mount.*") || p.hasPermission("prodigypack.*") || p.isOp()) {
					MountManager.removeCurrentPet(p, false);
					DarkRider.playAngelRider(p);
					p.closeInventory();
				} else {
					p.sendMessage(Messages.nopermissioneffect.replace("&", "§"));
				}
			}
			if (e.getCurrentItem().getItemMeta().getDisplayName().equals(Messages.poseidonriderName.replace("&", "§"))) {
				if (p.hasPermission("propack.mount.poseidonrider")|| p.hasPermission("propack.mount.*") || p.hasPermission("prodigypack.*") || p.isOp()) {
					MountManager.removeCurrentPet(p, false);
					PoseidonRider.playAngelRider(p);
					p.closeInventory();
				} else {
					p.sendMessage(Messages.nopermissioneffect.replace("&", "§"));
				}
			}
			if (e.getCurrentItem().getItemMeta().getDisplayName().equals(Messages.rainbowName.replace("&", "§"))) {
				if (p.hasPermission("propack.particle.rainbow")|| p.hasPermission("propack.particle.*") || p.hasPermission("prodigypack.*") || p.isOp()) {
					CircleParticle.Activate(p, ParticleType.Rainbow);
					p.playSound(p.getLocation(), Sound.ORB_PICKUP, 1, 1);
					p.closeInventory();
				}  else {
					p.sendMessage(Messages.nopermissioneffect.replace("&", "§"));
				}

			}

			break;
		case DOUBLE_PLANT:
			if (e.getCurrentItem().getItemMeta().getDisplayName().equals(Messages.partypopperName.replace("&", "§"))) {
				if (p.hasPermission("propack.gadget.partypopper")|| p.hasPermission("propack.gadget.*") || p.hasPermission("prodigypack.*") || p.isOp()) {
					PartyPopper.activate(p);
					p.closeInventory();
				}  else {
					p.sendMessage(Messages.nopermissioneffect.replace("&", "§"));
				}
			}

			break;
		case FIREBALL:
			if (e.getCurrentItem().getItemMeta().getDisplayName().equals(Messages.angryvillagerName.replace("&", "§"))) {
				if (p.hasPermission("propack.particle.angryvillager") || p.hasPermission("propack.particle.*") || p.hasPermission("prodigypack.*") || p.isOp()) {
					CircleParticle.Activate(p, ParticleType.AngryVillager);
					p.playSound(p.getLocation(), Sound.VILLAGER_YES, 1, 1);
					p.closeInventory();
				}  else {
					p.sendMessage(Messages.nopermissioneffect.replace("&", "§"));
				}


			} 
			if (e.getCurrentItem().getItemMeta().getDisplayName().equals(Messages.removegadgetName.replace("&", "§"))) {
				UtilInventory.removeCurrentGadget(p);
				Main.openMainGui(p);
			}
			break;
		case DIAMOND_SWORD:
			if (e.getCurrentItem().getItemMeta().getDisplayName().equals(Messages.magicName.replace("&", "§"))) {
				if (p.hasPermission("propack.particle.magic")|| p.hasPermission("propack.particle.*") || p.hasPermission("prodigypack.*") || p.isOp()) {
					CircleParticle.Activate(p, ParticleType.MagicCrit);
					p.playSound(p.getLocation(), Sound.CREEPER_HISS, 1, 1);
					p.closeInventory();
				}  else {
					p.sendMessage(Messages.nopermissioneffect.replace("&", "§"));
				}


			}
			break;
		case RED_ROSE:
			if (e.getCurrentItem().getItemMeta().getDisplayName().equals(Messages.flowerpopperName.replace("&", "§"))) {
				if (p.hasPermission("propack.gadget.flowerpopper") || p.hasPermission("propack.gadget.*") || p.hasPermission("prodigypack.*") || p.isOp()) {
					FlowerPopper.activate(p);
					p.closeInventory();
				}  else {
					p.sendMessage(Messages.nopermissioneffect.replace("&", "§"));
				}

			} 
			break;
		case REDSTONE:

			if (e.getCurrentItem().getItemMeta().getDisplayName().equals(Messages.heartName.replace("&", "§"))) {
				if (p.hasPermission("propack.particle.heart") || p.hasPermission("propack.particle.*") || p.hasPermission("prodigypack.*") || p.isOp()) {
					CircleParticle.Activate(p, ParticleType.Heart);
					p.playSound(p.getLocation(), Sound.CAT_PURREOW, 1, 1);
					p.closeInventory();
				}  else {
					p.sendMessage(Messages.nopermissioneffect.replace("&", "§"));
				}


			} 


			if (e.getCurrentItem().getItemMeta().getDisplayName().equals(Messages.helixName.replace("&", "§"))) {
				if (p.hasPermission("propack.extra.helix") || p.hasPermission("propack.extra.*") || p.hasPermission("prodigypack.*") || p.isOp()) {
					p.closeInventory();
					ExtraManager.Activate(p, EffectType.Helix);
					p.playSound(p.getLocation(), Sound.FIZZ, 1, 1);
				} else {
					p.sendMessage(Messages.nopermissioneffect.replace("&", "§"));
				}


			} 

			break;
		case ENCHANTMENT_TABLE:

			if (e.getCurrentItem().getItemMeta().getDisplayName().equals(Messages.enchantName.replace("&", "§"))) {
				if (p.hasPermission("propack.particle.enchant")|| p.hasPermission("propack.particle.*") || p.hasPermission("prodigypack.*") || p.isOp()) {
					CircleParticle.Activate(p, ParticleType.Enchantement);
					p.playSound(p.getLocation(), Sound.FIREWORK_LAUNCH, 1, 1);
					p.closeInventory();
				}  else {
					p.sendMessage(Messages.nopermissioneffect.replace("&", "§"));
				}


			}

			break;
		case ENDER_PORTAL_FRAME:
			if (e.getCurrentItem().getItemMeta().getDisplayName().equals(Messages.gravitystationName.replace("&", "§"))) {
				if (p.hasPermission("propack.gadget.gravitystation")|| p.hasPermission("propack.gadget.*") || p.hasPermission("prodigypack.*") || p.isOp()) {
					GravityStation.activate(p);
					p.closeInventory();
				}  else {
					p.sendMessage(Messages.nopermissioneffect.replace("&", "§"));
				}

			}
			break;
		case NOTE_BLOCK:

			if (e.getCurrentItem().getItemMeta().getDisplayName().equals(Messages.musicName.replace("&", "§"))) {
				if (p.hasPermission("propack.particle.music")|| p.hasPermission("propack.particle.*") || p.hasPermission("prodigypack.*") || p.isOp()) {
					CircleParticle.Activate(p, ParticleType.Music);
					p.playSound(p.getLocation(), Sound.ORB_PICKUP, 1, 1);
					p.closeInventory();
				}  else {
					p.sendMessage(Messages.nopermissioneffect.replace("&", "§"));
				}

			}

			break;
		case STAINED_GLASS:
			if (e.getCurrentItem().getItemMeta().getDisplayName().equals(Messages.astronotName.replace("&", "§"))) {
				if (p.hasPermission("propack.morph.astronot")|| p.hasPermission("propack.morph.*") || p.hasPermission("prodigypack.*") || p.isOp()) {
					Astronot.playAstronotToPlayer(p);
					p.closeInventory();
				} else {
					p.sendMessage(Messages.nopermissioneffect.replace("&", "§"));
				} }  
		case BLAZE_POWDER:
			if (e.getCurrentItem().getItemMeta().getDisplayName().equals(Messages.blazeName.replace("&", "§"))) {
				if (p.hasPermission("propack.morph.blaze")|| p.hasPermission("propack.morph.*") || p.hasPermission("prodigypack.*") || p.isOp()) {
					Blaze.playBlazeToPlayer(p);
					p.closeInventory();
				} else {
					p.sendMessage(Messages.nopermissioneffect.replace("&", "§"));
				} }  
			if (e.getCurrentItem().getItemMeta().getDisplayName().equals(Messages.flameName.replace("&", "§"))) {
				if (p.hasPermission("propack.particle.flame")|| p.hasPermission("propack.particle.*") || p.hasPermission("prodigypack.*") || p.isOp()) {
					CircleParticle.Activate(p, ParticleType.Flame);
					p.playSound(p.getLocation(), Sound.FIRE_IGNITE, 1, 1);
					p.closeInventory();
				} else {
					p.sendMessage(Messages.nopermissioneffect.replace("&", "§"));
				} }  


			break;
		case WATER_BUCKET:

			if (e.getCurrentItem().getItemMeta().getDisplayName().equals(Messages.waterName.replace("&", "§"))) {
				if (p.hasPermission("prodigypack.use.water")|| p.hasPermission("propack.particle.*") || p.hasPermission("prodigypack.*") || p.isOp()) {
					CircleParticle.Activate(p, ParticleType.Water);
					p.playSound(p.getLocation(), Sound.WATER, 1, 1);
					p.closeInventory();
				} else {
					p.sendMessage(Messages.nopermissioneffect.replace("&", "§"));
				} } 

			break;
		case LAVA_BUCKET:

			if (e.getCurrentItem().getItemMeta().getDisplayName().equals(Messages.lavaName.replace("&", "§"))) {
				if (p.hasPermission("propack.particle.lava")|| p.hasPermission("propack.particle.*") || p.hasPermission("prodigypack.*") || p.isOp()) {
					CircleParticle.Activate(p, ParticleType.Lava);
					p.playSound(p.getLocation(), Sound.LAVA_POP, 1, 1);
					p.closeInventory();
				}  else {
					p.sendMessage(Messages.nopermissioneffect.replace("&", "§"));
				}

			} 

			break;
		case NETHER_STAR:

			if (e.getCurrentItem().getItemMeta().getDisplayName().equals(Messages.sparkName.replace("&", "§"))) {
				if (p.hasPermission("propack.particle.spark")|| p.hasPermission("propack.particle.*") || p.hasPermission("prodigypack.*") || p.isOp()) {
					CircleParticle.Activate(p, ParticleType.Spark);
					p.closeInventory();
					p.playSound(p.getLocation(), Sound.FIZZ, 1, 1);
				} else {
					p.sendMessage(Messages.nopermissioneffect.replace("&", "§"));
				}



			} 
			break;
		case CACTUS:
			if (e.getCurrentItem().getItemMeta().getDisplayName().equals(Messages.cactusName.replace("&", "§"))) {
				if (p.hasPermission("propack.morph.cactus")|| p.hasPermission("propack.morph.*") || p.hasPermission("prodigypack.*") || p.isOp()) {
					Cactus.playCactusToPlayer(p);
					p.closeInventory();
				} else {
					p.sendMessage(Messages.nopermissioneffect.replace("&", "§"));
				} }  
			break;

		case SPIDER_EYE:

			if (e.getCurrentItem().getItemMeta().getDisplayName().equals(Messages.witchmorphName.replace("&", "§"))) {
				if (p.hasPermission("propack.morph.witch")|| p.hasPermission("propack.morph.*") || p.hasPermission("prodigypack.*") || p.isOp()) {
					Witch.playWitchToPlayer(p);
					p.closeInventory();
				} else {
					p.sendMessage(Messages.nopermissioneffect.replace("&", "§"));
				} }  

			if (e.getCurrentItem().getItemMeta().getDisplayName().equals(Messages.witchName.replace("&", "§"))) {
				if (p.hasPermission("propack.particle.witch")|| p.hasPermission("propack.particle.*") || p.hasPermission("prodigypack.*") || p.isOp()) {
					CircleParticle.Activate(p, ParticleType.Witch);
					p.playSound(p.getLocation(), Sound.FIREWORK_LAUNCH, 1, 1);
					p.closeInventory();
				}   else {
					p.sendMessage(Messages.nopermissioneffect.replace("&", "§"));
				}
			}

			break;
		case ENDER_PEARL:
			if (e.getCurrentItem().getItemMeta().getDisplayName().equals(Messages.stopEffectName.replace("&", "§"))) {
				EffectManager.removeEffect(p);
				if(e.getInventory().getName().equals(Messages.extraMenuPrefix.replace("&", "§"))) {
					Extra.openCloudGui(p);
				}
				if(e.getInventory().getName().equals(Messages.particleMenuPrefix.replace("&", "§"))) {
					Particles.openParticleGui(p);
				}
			}
			if (e.getCurrentItem().getItemMeta().getDisplayName().equals(Messages.stopMorphName.replace("&", "§"))) {
				MorphManager.removeMorph(p , true);
				Morphs.openMorphGui(p);

			}
			if (e.getCurrentItem().getItemMeta().getDisplayName().equals(Messages.stopEffectandMorphName.replace("&", "§"))) {
				MorphManager.removeMorph(p , true);
				EffectManager.removeEffect(p);
				Main.openMainGui(p);

			}
			break;
		case EYE_OF_ENDER:
			if (e.getCurrentItem().getItemMeta().getDisplayName().equals(Messages.portalName.replace("&", "§"))) {
				if (p.hasPermission("propack.particle.portal")|| p.hasPermission("propack.particle.*") || p.hasPermission("prodigypack.*") || p.isOp()) {
					CircleParticle.Activate(p, ParticleType.Portal);
					p.playSound(p.getLocation(), Sound.ORB_PICKUP, 1, 1);
					p.closeInventory();
				}  else {
					p.sendMessage(Messages.nopermissioneffect.replace("&", "§"));
				}


			}
			if (e.getCurrentItem().getItemMeta().getDisplayName().equals(Messages.vortexName.replace("&", "§"))) {
				if (p.hasPermission("propack.extra.vortex")|| p.hasPermission("propack.extra.*") || p.hasPermission("prodigypack.*") || p.isOp()) {
					EffectManager.addEffect(p, EffectType.Vortex);
					p.playSound(p.getLocation(), Sound.BLAZE_BREATH, 1, 2);
					p.closeInventory();
				}  else {
					p.sendMessage(Messages.nopermissioneffect.replace("&", "§"));
				}


			}

			if (e.getCurrentItem().getItemMeta().getDisplayName().equals(Messages.enderballName.replace("&", "§"))) {
				if (p.hasPermission("propack.gadget.enderball")|| p.hasPermission("propack.gadget.*") || p.hasPermission("prodigypack.*") || p.isOp()) {
					EnderBall.activate(p);
					p.closeInventory();
				}  else {
					p.sendMessage(Messages.nopermissioneffect.replace("&", "§"));
				}

			}
			if (e.getCurrentItem().getItemMeta().getDisplayName().equals(Messages.endermanmorphName.replace("&", "§"))) {
				if (p.hasPermission("propack.morph.enderman")|| p.hasPermission("propack.morph.*") || p.hasPermission("prodigypack.*") || p.isOp()) {
					Enderman.playEndermanToPlayer(p);
					p.closeInventory();
				}  else {
					p.sendMessage(Messages.nopermissioneffect.replace("&", "§"));
				}
			}
			break;
		case BOOK_AND_QUILL:
			if (e.getCurrentItem().getItemMeta().getDisplayName().equals(Messages.hourglassName.replace("&", "§"))) {
				if (p.hasPermission("propack.extra.hourglass")|| p.hasPermission("propack.extra.*") || p.hasPermission("prodigypack.*") || p.isOp()) {
					EffectManager.addEffect(p, EffectType.HourGlass);
					p.playSound(p.getLocation(), Sound.BAT_TAKEOFF, 1, 2);
					p.closeInventory();
				}  else {
					p.sendMessage(Messages.nopermissioneffect.replace("&", "§"));
				}


			}
			break;
		case WATER_LILY:
			if (e.getCurrentItem().getItemMeta().getDisplayName().equals(Messages.flamelillyName.replace("&", "§"))) {
				if (p.hasPermission("propack.extra.flamelilly")|| p.hasPermission("propack.extra.*") || p.hasPermission("prodigypack.*") || p.isOp()) {
					EffectManager.addEffect(p, EffectType.FlameLilly);
					p.playSound(p.getLocation(), Sound.BLAZE_BREATH, 1, 2);
					p.closeInventory();
				}  else {
					p.sendMessage(Messages.nopermissioneffect.replace("&", "§"));
				}


			}
			break;
		case GHAST_TEAR:
			if (e.getCurrentItem().getItemMeta().getDisplayName().equals(Messages.ghastmorphName.replace("&", "§"))) {
				if (p.hasPermission("propack.morph.ghast")|| p.hasPermission("propack.morph.*") || p.hasPermission("prodigypack.*") || p.isOp()) {
					Ghast.playGhastToPlayer(p);
					p.closeInventory();
				}  else {
					p.sendMessage(Messages.nopermissioneffect.replace("&", "§"));
				}
			}
			break;
		case SNOW_BALL:
			if (e.getCurrentItem().getItemMeta().getDisplayName().equals(Messages.snowName.replace("&", "§"))) {
				if (p.hasPermission("propack.particle.snow")|| p.hasPermission("propack.particle.*") || p.hasPermission("prodigypack.*") || p.isOp()) {
					CircleParticle.Activate(p, ParticleType.Snow);
					p.playSound(p.getLocation(), Sound.STEP_SNOW, 1, 1);
					p.closeInventory();
				}  else {
					p.sendMessage(Messages.nopermissioneffect.replace("&", "§"));
				}

			}
			break;
		case MAGMA_CREAM:
			if (e.getCurrentItem().getItemMeta().getDisplayName().equals(Messages.removePetAndMountName.replace("&", "§"))) {
				if(PetManager.HasPet(p)) {
					PetManager.removeCurrentPet(p ,true);
					Main.openMainGui(p);
				}
				if(MountManager.HasPet(p)) {
					MountManager.removeCurrentPet(p, true);
					Main.openMainGui(p);
				}
			}
			if (e.getCurrentItem().getItemMeta().getDisplayName().equals(Messages.removeMountName.replace("&", "§"))) {
				MountManager.removeCurrentPet(p, true);
				Mounts.openPetGui(p);
			}
			if (e.getCurrentItem().getItemMeta().getDisplayName().equals(Messages.removePetName.replace("&", "§"))) {
				PetManager.removeCurrentPet(p, true);
				Pets.openMainGui(p);
			}
			break;
		case ENCHANTED_BOOK:
			if (e.getCurrentItem().getItemMeta().getDisplayName().equals(Messages.grenadeName.replace("&", "§"))) {
				if (p.hasPermission("propack.gadget.grenade")|| p.hasPermission("propack.gadget.*") || p.hasPermission("prodigypack.*") || p.isOp()) {
					Grenade.activate(p);
					p.closeInventory();
				}  else {
					p.sendMessage(Messages.nopermissioneffect.replace("&", "§"));
				}
			}

			break;
		case WEB:
			if (e.getCurrentItem().getItemMeta().getDisplayName().equals(Messages.tornadogadgetName.replace("&", "§"))) {
				if (p.hasPermission("propack.gadget.tornado")|| p.hasPermission("propack.gadget.*") || p.hasPermission("prodigypack.*") || p.isOp()) {
					Tornado.activate(p);
					p.closeInventory();
				}  else {
					p.sendMessage(Messages.nopermissioneffect.replace("&", "§"));
				}
			}
			break;
		case SUGAR:
			if (e.getCurrentItem().getItemMeta().getDisplayName().equals(Messages.smokebombName.replace("&", "§"))) {
				if (p.hasPermission("propack.gadget.smokebomb")|| p.hasPermission("propack.gadget.*") || p.hasPermission("prodigypack.*") || p.isOp()) {
					SmokeBomb.activate(p);
					p.closeInventory();
				}  else {
					p.sendMessage(Messages.nopermissioneffect.replace("&", "§"));
				}
			}

			break;
		case FEATHER:
			if (e.getCurrentItem().getItemMeta().getDisplayName().equals(Messages.ghostName.replace("&", "§"))) {
				if (p.hasPermission("propack.gadget.ghost")|| p.hasPermission("propack.gadget.*") || p.hasPermission("prodigypack.*") || p.isOp()) {
					Ghost.activate(p);
					p.closeInventory();
				}  else {
					p.sendMessage(Messages.nopermissioneffect.replace("&", "§"));
				}
			}


			break;
		case BONE:
			if (e.getCurrentItem().getItemMeta().getDisplayName().equals(Messages.wolfName.replace("&", "§"))) {
				if (p.hasPermission("propack.pet.wolf")|| p.hasPermission("propack.pet.*") || p.hasPermission("prodigypack.*") || p.isOp()) {
					PetManager.removeCurrentPet(p, false);
					PetManager.ActivatePet(p, PetType.Wolf);
					p.closeInventory();
				}

			}
			if (e.getCurrentItem().getItemMeta().getDisplayName().equals(Messages.puppieloverName.replace("&", "§"))) {
				if (p.hasPermission("propack.gadget.puppielover")|| p.hasPermission("propack.gadget.*") || p.hasPermission("prodigypack.*") || p.isOp()) {
					PuppieLover.activate(p);
					p.closeInventory();
				}

			}
			break;
		case IRON_BARDING:

			if (e.getCurrentItem().getItemMeta().getDisplayName().equals(Messages.batblasterName.replace("&", "§"))) {
				if (p.hasPermission("propack.gadget.batblaster")|| p.hasPermission("propack.gadget.*") || p.hasPermission("prodigypack.*") || p.isOp()) {
					BatBlaster.activate(p);
					p.closeInventory();
				}  else {
					p.sendMessage(Messages.nopermissioneffect.replace("&", "§"));
				}
			}
			break;
		case GOLD_BARDING:

			if (e.getCurrentItem().getItemMeta().getDisplayName().equals(Messages.paintballgunName.replace("&", "§"))) {
				if (p.hasPermission("propack.gadget.paintballgun")|| p.hasPermission("propack.gadget.*") || p.hasPermission("prodigypack.*") || p.isOp()) {
					PaintballGun.activate(p);
				}  else {
					p.sendMessage(Messages.nopermissioneffect.replace("&", "§"));
				}
			}
			p.closeInventory();
			break;
		case REDSTONE_TORCH_ON:

			if (e.getCurrentItem().getItemMeta().getDisplayName().equals(Messages.rocketName.replace("&", "§"))) {
				if (p.hasPermission("propack.gadget.rocket")|| p.hasPermission("propack.gadget.*") || p.hasPermission("prodigypack.*") || p.isOp()) {
					Rocket.activate(p);
					p.closeInventory();
				} else {
					p.sendMessage(Messages.nopermissioneffect.replace("&", "§"));
				}
			}

			break;
		case FIREWORK:

			if (e.getCurrentItem().getItemMeta().getDisplayName().equals(Messages.fireworkpopperName.replace("&", "§"))) {
				if (p.hasPermission("propack.gadget.fireworkpopper")|| p.hasPermission("propack.gadget.*") || p.hasPermission("prodigypack.*") || p.isOp()) {
					FireworksPopper.activate(p);
					p.closeInventory();
				} else {
					p.sendMessage(Messages.nopermissioneffect.replace("&", "§"));
				}
			}

			break;
		case SKULL_ITEM:
			if (e.getCurrentItem().getItemMeta().getDisplayName().equals(Messages.vampireName.replace("&", "§"))) {
				if (p.hasPermission("propack.morph.vampire")|| p.hasPermission("propack.morph.*") || p.hasPermission("prodigypack.*") || p.isOp()) {
					Vampire.playDraculaToPlayer(p);
					p.closeInventory();
				} else {
					p.sendMessage(Messages.nopermissioneffect.replace("&", "§"));
				}
			}
			break;
		case IRON_AXE:
			if (e.getCurrentItem().getItemMeta().getDisplayName().equals(Messages.axelauncherName.replace("&", "§"))) {
				if (p.hasPermission("propack.gadget.axelauncher")|| p.hasPermission("propack.gadget.*") || p.hasPermission("prodigypack.*") || p.isOp()) {
					AxeLauncher.activate(p);
					p.closeInventory();
				} else {
					p.sendMessage(Messages.nopermissioneffect.replace("&", "§"));
				}
			}

			break;
		case DIAMOND_HOE:

			if (e.getCurrentItem().getItemMeta().getDisplayName().equals(Messages.quakegunName.replace("&", "§"))) {
				if (p.hasPermission("propack.gadget.quakegun")|| p.hasPermission("propack.gadgete.*") || p.hasPermission("prodigypack.*") || p.isOp()) {
					QuakeGun.activate(p);
					p.closeInventory();
				} else {
					p.sendMessage(Messages.nopermissioneffect.replace("&", "§"));
				}
			}

			break;
		case BEACON:

			if (e.getCurrentItem().getItemMeta().getDisplayName().equals(Messages.discoballName.replace("&", "§"))) {
				if (p.hasPermission("propack.gadget.discoball")|| p.hasPermission("propack.gadget.*") || p.hasPermission("prodigypack.*") || p.isOp()) {
					DiscoBall.activate(p);
					p.closeInventory();
				} else {
					p.sendMessage(Messages.nopermissioneffect.replace("&", "§"));
				}
			}

			break;
		case DIAMOND:

			if (e.getCurrentItem().getItemMeta().getDisplayName().equals(Messages.diamondpartyName.replace("&", "§"))) {
				if (p.hasPermission("propack.gadget.diamondparty")|| p.hasPermission("propack.gadget.*") || p.hasPermission("prodigypack.*") || p.isOp()) {
					DiamondParty.activate(p);
					p.closeInventory();
				} else {
					p.sendMessage(Messages.nopermissioneffect.replace("&", "§"));
				}
			}

			break;
		case TNT:

			if (e.getCurrentItem().getItemMeta().getDisplayName().equals(Messages.humanbombName.replace("&", "§"))) {
				if (p.hasPermission("propack.gadget.humanbomb")|| p.hasPermission("propack.gadget.*") || p.hasPermission("prodigypack.*") || p.isOp()) {
					HumanBomb.activate(p);
					p.closeInventory();
				} else {
					p.sendMessage(Messages.nopermissioneffect.replace("&", "§"));
				}
			}

			break;
		case SULPHUR:
			if (e.getCurrentItem().getItemMeta().getDisplayName().equals(Messages.tntpopperName.replace("&", "§"))) {
				if (p.hasPermission("propack.gadget.tntpopper")|| p.hasPermission("propack.gadget.*") || p.hasPermission("prodigypack.*") || p.isOp()) {
					TntPopper.activate(p);
					p.closeInventory();
				} else {
					p.sendMessage(Messages.nopermissioneffect.replace("&", "§"));
				}
			}

			break;
		case SLIME_BALL:
			if (e.getCurrentItem().getItemMeta().getDisplayName().equals(Messages.slimeName.replace("&", "§"))) {
				if (p.hasPermission("propack.particle.slime")|| p.hasPermission("propack.gadget.*") || p.hasPermission("prodigypack.*") || p.isOp()) {
					CircleParticle.Activate(p, ParticleType.Slime);
					p.playSound(p.getLocation(), Sound.SLIME_ATTACK, 1, 1);
					p.closeInventory();
				}  else {
					p.sendMessage(Messages.nopermissioneffect.replace("&", "§"));
				}

			}

			if (e.getCurrentItem().getItemMeta().getDisplayName().equals(Messages.slimehatName.replace("&", "§"))) {
				if (p.hasPermission("propack.gadget.slimehat")|| p.hasPermission("propack.gadget.*") || p.hasPermission("prodigypack.*") || p.isOp()) {
					SlimeHat.activate(p);
					p.closeInventory();
				} else {
					p.sendMessage(Messages.nopermissioneffect.replace("&", "§"));
				}
			}

			break;

		case QUARTZ:

			if (e.getCurrentItem().getItemMeta().getDisplayName().equals(Messages.snowshovelName.replace("&", "§"))) {
				if (p.hasPermission("propack.particle.snowshovel")|| p.hasPermission("propack.particle.*") || p.hasPermission("prodigypack.*") || p.isOp()) {
					CircleParticle.Activate(p, ParticleType.SnowShovel);
					p.playSound(p.getLocation(), Sound.STEP_SNOW, 1, 1);
					p.closeInventory();
				}  else {
					p.sendMessage(Messages.nopermissioneffect.replace("&", "§"));
				}


			}
			if (e.getCurrentItem().getItemMeta().getDisplayName().equals(Messages.cloudName.replace("&", "§"))) {
				if (p.hasPermission("propack.extra.cloud") || p.hasPermission("propack.extra.*") || p.hasPermission("prodigypack.*") || p.isOp()) {
					p.playSound(p.getLocation(), Sound.FIZZ, 1, 1);
					ExtraManager.Activate(p, EffectType.Cloud);
					p.closeInventory();
				}  else {
					p.sendMessage(Messages.nopermissioneffect.replace("&", "§"));
				}


			} 
			break;
		case STRING:
			if (e.getCurrentItem().getItemMeta().getDisplayName().equals(Messages.tornadoName.replace("&", "§"))) {
				if (p.hasPermission("propack.extra.tornado") || p.hasPermission("propack.extra.*") || p.hasPermission("prodigypack.*") || p.isOp()) {
					ExtraManager.Activate(p, EffectType.Tornado);
					p.closeInventory();
					p.playSound(p.getLocation(), Sound.FIZZ, 1, 1);
				}  else {
					p.sendMessage(Messages.nopermissioneffect.replace("&", "§"));
				}


			} 
			break;
		case INK_SACK:
			if (e.getCurrentItem().getItemMeta().getDisplayName().equals(Messages.ocelotName.replace("&", "§"))) {
				if (p.hasPermission("propack.pet.ocelot")|| p.hasPermission("propack.extra.*") || p.hasPermission("prodigypack.*") || p.isOp()) {
					PetManager.removeCurrentPet(p, false);
					PetManager.ActivatePet(p, PetType.Ocelot);
					p.closeInventory();
				} else {
					p.sendMessage(Messages.nopermissioneffect.replace("&", "§"));
				}
			}
			if (e.getCurrentItem().getItemMeta().getDisplayName().equals(Messages.lightningName.replace("&", "§"))) {
				if (p.hasPermission("propack.extra.cloudlightning") || p.hasPermission("propack.extra.*") || p.hasPermission("prodigypack.*") || p.isOp()) {
					ExtraManager.Activate(p, EffectType.CloudLight);
					p.playSound(p.getLocation(), Sound.AMBIENCE_THUNDER, 1, 1);
					p.closeInventory();
				} else {
					p.sendMessage(Messages.nopermissioneffect.replace("&", "§"));
				}
			}
			if (e.getCurrentItem().getItemMeta().getDisplayName().equals(Messages.snowCloudName.replace("&", "§"))) {
				if (p.hasPermission("propack.extra.cloudsnow") || p.hasPermission("propack.extra.*") || p.hasPermission("prodigypack.*") || p.isOp()) {
					ExtraManager.Activate(p, EffectType.CloudSnow);
					p.playSound(p.getLocation(), Sound.STEP_SNOW, 1, 1);
					p.closeInventory();
				} else {
					p.sendMessage(Messages.nopermissioneffect.replace("&", "§"));
				}
			}
			
			if (e.getCurrentItem().getItemMeta().getDisplayName().equals(Messages.fountainName.replace("&", "§"))) {
				if (p.hasPermission("propack.extra.fountain") || p.hasPermission("propack.extra.*") || p.hasPermission("prodigypack.*") || p.isOp()) {
					ExtraManager.Activate(p, EffectType.Fountain);
					p.playSound(p.getLocation(), Sound.WATER, 1, 1);
					p.closeInventory();
				} else {
					p.sendMessage(Messages.nopermissioneffect.replace("&", "§"));
				}
			}



			break;
		case PUMPKIN:
			if (e.getCurrentItem().getItemMeta().getDisplayName().equals(Messages.halloweenheadName.replace("&", "§"))) {
				if (p.hasPermission("propack.gadget.halloweenhead")|| p.hasPermission("propack.gadget.*") || p.hasPermission("prodigypack.*") || p.isOp()) {
					HalloweenHead.activate(p);
					p.closeInventory();
				} else {
					p.sendMessage(Messages.nopermissioneffect.replace("&", "§"));
				}
			}
			break;

		default:   
			p.closeInventory();
			break;

		}

		if (e.getCurrentItem().getItemMeta().getDisplayName().equals(Messages.mountName.replace("&", "§"))) {
			Mounts.openPetGui(p);
		} 

		if (e.getCurrentItem().getItemMeta().getDisplayName().equals(Messages.particleName.replace("&", "§"))) {
			Particles.openParticleGui(p);
		}

		if (e.getCurrentItem().getItemMeta().getDisplayName().equals(Messages.petMenuName.replace("&", "§"))) {
			Pets.openMainGui(p);
		}

		if (e.getCurrentItem().getItemMeta().getDisplayName().equals(Messages.morphName.replace("&", "§"))) {
			Morphs.openMorphGui(p);
		}

		if (e.getCurrentItem().getItemMeta().getDisplayName().equals(Messages.gadgetName.replace("&", "§"))) {
			Gadgets.openGadgetGui(p);
		}

		if (e.getCurrentItem().getItemMeta().getDisplayName().equals(Messages.extraName.replace("&", "§"))) {
			Extra.openCloudGui(p);
		}
	}

	public static void registerEvents(me.mike1665.Main.Main plugin) {

		PluginManager pm = plugin.getServer().getPluginManager();
		pm.registerEvents(new MenuManager() , plugin);

	}

}
