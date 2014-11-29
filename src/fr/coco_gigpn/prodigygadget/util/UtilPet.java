package fr.coco_gigpn.prodigygadget.util;
 
import java.lang.reflect.Field;
import java.util.UUID;

import net.minecraft.server.v1_8_R1.EntityInsentient;
import net.minecraft.server.v1_8_R1.PathEntity;
import net.minecraft.server.v1_8_R1.PathfinderGoal;
import net.minecraft.server.v1_8_R1.PathfinderGoalFloat;
import net.minecraft.server.v1_8_R1.PathfinderGoalSelector;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R1.entity.CraftLivingEntity;
import org.bukkit.craftbukkit.v1_8_R1.util.UnsafeList;
import org.bukkit.entity.LivingEntity;
 
public class UtilPet {
 
	private static Field gsa;
	private static Field goalSelector;
	private static Field targetSelector;
	static int xRandom = UtilMath.randomRangeInt(-3, 3);
	static int zRandom = UtilMath.randomRangeInt(-3, 3);
 
	static {
		try {
			gsa = PathfinderGoalSelector.class.getDeclaredField("b");
			gsa.setAccessible(true);
 
			goalSelector = EntityInsentient.class.getDeclaredField("goalSelector");
			goalSelector.setAccessible(true);
 
			targetSelector = EntityInsentient.class.getDeclaredField("targetSelector");
			targetSelector.setAccessible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
 
	@SuppressWarnings("deprecation")
	public static void makePet(LivingEntity e, UUID toFollow) {
		try {
			Object nms_entity = ((CraftLivingEntity) e).getHandle();
			if (nms_entity instanceof EntityInsentient) {
 
				PathfinderGoalSelector goal = (PathfinderGoalSelector) goalSelector.get(nms_entity);
				PathfinderGoalSelector target = (PathfinderGoalSelector) targetSelector.get(nms_entity);
 
				gsa.set(goal, new UnsafeList<Object>());
				gsa.set(target, new UnsafeList<Object>());
 
				goal.a(0, new PathfinderGoalFloat((EntityInsentient) nms_entity));
				goal.a(1, new PathfinderGoalWalktoTile((EntityInsentient) nms_entity, toFollow));
 
			} else {
				throw new IllegalArgumentException(e.getType().getName() + " is not an instance of an EntityInsentient.");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
 
	public static class PathfinderGoalWalktoTile extends PathfinderGoal {
 
		private EntityInsentient entity;
		private PathEntity path;
		private UUID p;
 
		public PathfinderGoalWalktoTile(EntityInsentient entitycreature, UUID p) {
			this.entity = entitycreature;
			this.p = p;
		}
 
		@Override
		public boolean a() {
 
			if (Bukkit.getPlayer(p) == null) {
				return path != null;
			}
 
			Location targetLocation = Bukkit.getPlayer(p).getLocation();
 
			boolean flag = this.entity.getNavigation().c();
 
			this.entity.getNavigation().b(false);
			this.path = this.entity.getNavigation().a(targetLocation.getX() + xRandom, targetLocation.getY(), targetLocation.getZ() + zRandom);
			this.entity.getNavigation().b(flag);
 
			if (this.path != null) {
				this.c();
			}
			return this.path != null;
		}
 
		@Override
		public void c() {
			this.entity.getNavigation().a(this.path, 1.2D);
		}
	}
}