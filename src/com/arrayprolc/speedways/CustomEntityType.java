package com.arrayprolc.speedways;

import net.minecraft.server.v1_8_R1.Entity;
import net.minecraft.server.v1_8_R1.EntityWitherSkull;

import org.bukkit.entity.EntityType;

public enum CustomEntityType
{

	SEEKERSKULL("WitherSkull", 51, EntityType.WITHER_SKULL, EntityWitherSkull.class, SeekerSkull.class);

	private String name;
	private int id;
	private EntityType entityType;
	private Class<? extends Entity> nmsClass;
	private Class<? extends Entity> customClass;

	private CustomEntityType(String name, int id, EntityType entityType, Class<? extends Entity> nmsClass, Class<? extends Entity> customClass)
	{
		this.name = name;
		this.id = id;
		this.entityType = entityType;
		this.nmsClass = nmsClass;
		this.customClass = customClass;
	}

	public String getName()
	{
		return this.name;
	}

	public int getID()
	{
		return this.id;
	}

	public EntityType getEntityType()
	{
		return this.entityType;
	}

	public Class<? extends Entity> getNMSClass()
	{
		return this.nmsClass;
	}

	public Class<? extends Entity> getCustomClass()
	{
		return this.customClass;
	}

}