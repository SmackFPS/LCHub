package fr.coco_gigpn.prodigygadget.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.server.v1_8_R1.EntityFireworks;
import net.minecraft.server.v1_8_R1.PacketPlayOutEntityStatus;
import net.minecraft.server.v1_8_R1.World;

import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_8_R1.entity.CraftPlayer;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.FireworkMeta;

public class UtilFirework extends EntityFireworks {

	private static final Random rng = new Random();

	static Player[] players = UtilServer.getPlayers();

	public UtilFirework(World world, Player... p) {
		super(world);
		players = p;
		this.a(0.25F, 0.25F);
	}

	boolean gone = false;

	@Override
	public void h() {
		if (gone) {
			return;
		}

		if (!this.world.isStatic) {
			gone = true;

			if (players != null) {
				if (players.length > 0) {
					for (Player player : players) {
						(((CraftPlayer) player).getHandle()).playerConnection
								.sendPacket(new PacketPlayOutEntityStatus(this,
										(byte) 17));
					}

					this.die();
					return;
				}
			}

			world.broadcastEntityEffect(this, (byte) 17);
			this.die();
		}
	}

	public static void spawn(Location location, FireworkEffect effect,
			Player... players) {
		try {
			UtilFirework firework = new UtilFirework(
					((CraftWorld) location.getWorld()).getHandle(), players);
			FireworkMeta meta = ((Firework) firework.getBukkitEntity())
					.getFireworkMeta();
			meta.addEffect(effect);
			((Firework) firework.getBukkitEntity()).setFireworkMeta(meta);
			firework.setPosition(location.getX(), location.getY(),
					location.getZ());

			if ((((CraftWorld) location.getWorld()).getHandle())
					.addEntity(firework)) {
				firework.setInvisible(true);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unused")
	public static void launchRandomFirework(Location loc, Player players) {

		List<Type> types = new ArrayList<Type>();
		types.add(Type.BALL);
		types.add(Type.BALL_LARGE);
		types.add(Type.BURST);
		types.add(Type.CREEPER);
		types.add(Type.STAR);
		Color c1 = Color.fromRGB(rng.nextInt(255), rng.nextInt(255),
				rng.nextInt(255));
		Color c2 = Color.fromRGB(rng.nextInt(255), rng.nextInt(255),
				rng.nextInt(255));
		Color fade = Color.fromRGB(rng.nextInt(255), rng.nextInt(255),
				rng.nextInt(255));
		try {
			UtilFirework fireworker = new UtilFirework(
					((CraftWorld) loc.getWorld()).getHandle(), players);
			Firework firework = (Firework) fireworker.getBukkitEntity();
			FireworkMeta meta = firework.getFireworkMeta();
			FireworkEffect.Builder effect = FireworkEffect.builder();
			effect.withColor(c1, c2);
			effect.withFade(fade);

			// Rand style based on the user perms
			effect.with(types.get(rng.nextInt(types.size())));

			if (rng.nextBoolean())
				effect.withFlicker();

			if (rng.nextBoolean())
				effect.withTrail();
			meta.addEffect(effect.build());
			if (meta == null)
				return;
			((Firework) firework).setFireworkMeta(meta);

			fireworker.setPosition(loc.getX(), loc.getY(), loc.getZ());
			if ((((CraftWorld) loc.getWorld()).getHandle())
					.addEntity(fireworker)) {
				fireworker.setInvisible(true);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	@SuppressWarnings("unused")
	public static void launchRandomFirework(Location loc) {

		List<Type> types = new ArrayList<Type>();
		types.add(Type.BALL);
		types.add(Type.BALL_LARGE);
		types.add(Type.BURST);
		types.add(Type.CREEPER);
		types.add(Type.STAR);
		Color c1 = Color.fromRGB(rng.nextInt(255), rng.nextInt(255),
				rng.nextInt(255));
		Color c2 = Color.fromRGB(rng.nextInt(255), rng.nextInt(255),
				rng.nextInt(255));
		Color fade = Color.fromRGB(rng.nextInt(255), rng.nextInt(255),
				rng.nextInt(255));
		try {
			UtilFirework fireworker = new UtilFirework(
					((CraftWorld) loc.getWorld()).getHandle(), players);
			Firework firework = (Firework) fireworker.getBukkitEntity();
			FireworkMeta meta = firework.getFireworkMeta();
			FireworkEffect.Builder effect = FireworkEffect.builder();
			effect.withColor(c1, c2);
			effect.withFade(fade);

			// Rand style based on the user perms
			effect.with(types.get(rng.nextInt(types.size())));

			if (rng.nextBoolean())
				effect.withFlicker();

			if (rng.nextBoolean())
				effect.withTrail();
			meta.addEffect(effect.build());
			if (meta == null)
				return;
			((Firework) firework).setFireworkMeta(meta);

			fireworker.setPosition(loc.getX(), loc.getY(), loc.getZ());
			if ((((CraftWorld) loc.getWorld()).getHandle())
					.addEntity(fireworker)) {
				fireworker.setInvisible(true);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	@SuppressWarnings("unused")
	public static void launchRandomBurstFirework(Location loc) {

		List<Type> types = new ArrayList<Type>();

		types.add(Type.BURST);

		Color c1 = Color.fromRGB(rng.nextInt(255), rng.nextInt(255),
				rng.nextInt(255));
		Color c2 = Color.fromRGB(rng.nextInt(255), rng.nextInt(255),
				rng.nextInt(255));
		Color fade = Color.fromRGB(rng.nextInt(255), rng.nextInt(255),
				rng.nextInt(255));
		try {
			UtilFirework fireworker = new UtilFirework(
					((CraftWorld) loc.getWorld()).getHandle(), players);
			Firework firework = (Firework) fireworker.getBukkitEntity();
			FireworkMeta meta = firework.getFireworkMeta();
			FireworkEffect.Builder effect = FireworkEffect.builder();
			effect.withColor(c1, c2);
			effect.withFade(fade);

			// Rand style based on the user perms
			effect.with(types.get(rng.nextInt(types.size())));

			if (rng.nextBoolean())
				effect.withFlicker();

			if (rng.nextBoolean())
				effect.withTrail();
			meta.addEffect(effect.build());
			if (meta == null)
				return;
			((Firework) firework).setFireworkMeta(meta);

			fireworker.setPosition(loc.getX(), loc.getY(), loc.getZ());
			if ((((CraftWorld) loc.getWorld()).getHandle())
					.addEntity(fireworker)) {
				fireworker.setInvisible(true);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}


	@SuppressWarnings("unused")
	public static void launchRandomBurstFirework(Location loc, Player players) {

		List<Type> types = new ArrayList<Type>();

		types.add(Type.BURST);

		Color c1 = Color.fromRGB(rng.nextInt(255), rng.nextInt(255),
				rng.nextInt(255));
		Color c2 = Color.fromRGB(rng.nextInt(255), rng.nextInt(255),
				rng.nextInt(255));
		Color fade = Color.fromRGB(rng.nextInt(255), rng.nextInt(255),
				rng.nextInt(255));
		try {
			UtilFirework fireworker = new UtilFirework(
					((CraftWorld) loc.getWorld()).getHandle(), players);
			Firework firework = (Firework) fireworker.getBukkitEntity();
			FireworkMeta meta = firework.getFireworkMeta();
			FireworkEffect.Builder effect = FireworkEffect.builder();
			effect.withColor(c1, c2);
			effect.withFade(fade);

			// Rand style based on the user perms
			effect.with(types.get(rng.nextInt(types.size())));

			if (rng.nextBoolean())
				effect.withFlicker();

			if (rng.nextBoolean())
				effect.withTrail();
			meta.addEffect(effect.build());
			if (meta == null)
				return;
			((Firework) firework).setFireworkMeta(meta);

			fireworker.setPosition(loc.getX(), loc.getY(), loc.getZ());
			if ((((CraftWorld) loc.getWorld()).getHandle())
					.addEntity(fireworker)) {
				fireworker.setInvisible(true);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}