package fr.coco_gigpn.prodigygadget.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import me.mike1665.Main.Main;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.material.MaterialData;
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionType;

import fr.coco_gigpn.prodigygadget.util.UtilParticle.Particle;

public class UtilItem
{

	public static ItemStack getNamedSkull(String nick)
	{
		@SuppressWarnings("deprecation")
		ItemStack skull = new ItemStack(397, 1, (short)3);
		SkullMeta meta = (SkullMeta)skull.getItemMeta();

		meta.setOwner(nick);
		skull.setItemMeta(meta);

		return skull;
	}

	public static ItemStack setLore(ItemStack item, String... lore)
	{
		return setLore(item , Arrays.asList(lore));
	}

	public static ItemStack setLore(ItemStack item, List< String > lore)
	{
		ItemMeta meta = item.getItemMeta();
		meta.setLore(lore);
		item.setItemMeta(meta);

		return item;
	}

	public static boolean compare(ItemStack item1, ItemStack item2)
	{
		if ((item1 == null) || (item2 == null)) {
			return false;
		}
		int amount = item1.getAmount();
		short durability = item1.getDurability();

		item1.setAmount(item2.getAmount());
		item1.setDurability(item2.getDurability());

		boolean value = item1.equals(item2);

		item1.setAmount(amount);
		item1.setDurability(durability);

		return value;
	}

	@SuppressWarnings("deprecation")
	public static ItemStack create(Material material, int amount, byte data, String displayname, String lore1, String lore2, String lore3, String lore4)
	{
		ItemStack item = new MaterialData(material, data).toItemStack(amount);

		ItemMeta itemMeta = item.getItemMeta();
		ArrayList<String> lore = new ArrayList<String>();

		if (displayname != null) {
			itemMeta.setDisplayName(displayname);
		}

		if (lore1 != null) {
			lore.add(lore1);
		}
		if (lore2 != null) {
			lore.add(lore2);
		}
		if (lore3 != null) {
			lore.add(lore3);
		}
		if (lore4 != null) {
			lore.add(lore4);
		}

		itemMeta.setLore(lore);
		item.setItemMeta(itemMeta);

		return item;
	}

	@SuppressWarnings("deprecation")
	public static ItemStack create(int material, int amount, byte data, String displayname, String lore1, String lore2, String lore3, String lore4)
	{
		ItemStack item = new MaterialData(material, data).toItemStack(amount);

		ItemMeta itemMeta = item.getItemMeta();
		ArrayList<String> lore = new ArrayList<String>();

		if (displayname != null) {
			itemMeta.setDisplayName(displayname);
		}

		if (lore1 != null) {
			lore.add(lore1);
		}
		if (lore2 != null) {
			lore.add(lore2);
		}
		if (lore3 != null) {
			lore.add(lore3);
		}
		if (lore4 != null) {
			lore.add(lore4);
		}

		itemMeta.setLore(lore);
		item.setItemMeta(itemMeta);

		return item;
	}

	public static ItemStack create(Material material) {
		return create(material, 1, (byte)0, null, null, null, null, null);
	}

	public static ItemStack create(Material material, int amount) {
		return create(material, amount, (byte)0, null, null, null, null, null);
	}

	public static ItemStack create(Material material, int amount, String displayname) {
		return create(material, amount, (byte)0, displayname, null, null, null, null);
	}

	public static ItemStack create(Material material, int amount, String displayname, String lore1) {
		return create(material, amount, (byte)0, displayname, lore1, null, null, null);
	}

	public static ItemStack create(Material material, int amount, String displayname, String lore1, String lore2) {
		return create(material, amount, (byte)0, displayname, lore1, lore2, null, null);
	}

	public static ItemStack create(Material material, int amount, String displayname, String lore1, String lore2, String lore3) {
		return create(material, amount, (byte)0, displayname, lore1, lore2, lore3, null);
	}

	public static ItemStack create(Material material, byte data)
	{
		return create(material, 1, data, null, null, null, null, null);
	}

	public static ItemStack create(Material material, int amount, byte data) {
		return create(material, amount, data, null, null, null, null, null);
	}

	public static ItemStack create(Material material, int amount, byte data, String displayname) {
		return create(material, amount, data, displayname, null, null, null, null);
	}

	public static ItemStack create(Material material, int amount, byte data, String displayname, String lore1) {
		return create(material, amount, data, displayname, lore1, null, null, null);
	}

	public static ItemStack create(Material material, int amount, byte data, String displayname, String lore1, String lore2) {
		return create(material, amount, data, displayname, lore1, lore2, null, null);
	}

	public static ItemStack create(Material material, int amount, byte data, String displayname, String lore1, String lore2, String lore3) {
		return create(material, amount, data, displayname, lore1, lore2, lore3, null);
	}

	/*
	 * 
	 * Int type
	 * 
	 */

	public static ItemStack create(int material) {
		return create(material, 1, (byte)0, null, null, null, null, null);
	}

	public static ItemStack create(int material, int amount) {
		return create(material, amount, (byte)0, null, null, null, null, null);
	}

	public static ItemStack create(int material, int amount, String displayname) {
		return create(material, amount, (byte)0, displayname, null, null, null, null);
	}

	public static ItemStack create(int material, int amount, String displayname, String lore1) {
		return create(material, amount, (byte)0, displayname, lore1, null, null, null);
	}

	public static ItemStack create(int material, int amount, String displayname, String lore1, String lore2) {
		return create(material, amount, (byte)0, displayname, lore1, lore2, null, null);
	}

	public static ItemStack create(int material, int amount, String displayname, String lore1, String lore2, String lore3) {
		return create(material, amount, (byte)0, displayname, lore1, lore2, lore3, null);
	}

	public static ItemStack create(int material, byte data)
	{
		return create(material, 1, data, null, null, null, null, null);
	}

	public static ItemStack create(int material, int amount, byte data) {
		return create(material, amount, data, null, null, null, null, null);
	}

	public static ItemStack create(int material, int amount, byte data, String displayname) {
		return create(material, amount, data, displayname, null, null, null, null);
	}

	public static ItemStack create(int material, int amount, byte data, String displayname, String lore1) {
		return create(material, amount, data, displayname, lore1, null, null, null);
	}

	public static ItemStack create(int material, int amount, byte data, String displayname, String lore1, String lore2) {
		return create(material, amount, data, displayname, lore1, lore2, null, null);
	}

	public static ItemStack create(int material, int amount, byte data, String displayname, String lore1, String lore2, String lore3) {
		return create(material, amount, data, displayname, lore1, lore2, lore3, null);
	}

	public static ItemStack potion(ItemStack item, PotionType potionType)
	{
		Potion potion = new Potion(potionType);
		potion.apply(item);
		return item;
	}

	public static ItemStack enchant(ItemStack item, Enchantment enchantment1, int level1, Enchantment enchantment2, int level2, Enchantment enchantment3, int level3)
	{
		ItemMeta itemMeta = item.getItemMeta();

		if (enchantment1 != null) {
			itemMeta.addEnchant(enchantment1, level1, true);
		}
		if (enchantment2 != null) {
			itemMeta.addEnchant(enchantment2, level2, true);
		}
		if (enchantment3 != null) {
			itemMeta.addEnchant(enchantment3, level3, true);
		}

		item.setItemMeta(itemMeta);

		return item;
	}

	public static ItemStack enchant(ItemStack item, Enchantment enchantment1, int level1) {
		return enchant(item, enchantment1, level1, null, 0, null, 0);
	}

	public static ItemStack enchant(ItemStack item, Enchantment enchantment1, int level1, Enchantment enchantment2, int level2) {
		return enchant(item, enchantment1, level1, enchantment2, level2, null, 0);
	}

	public static ItemStack recolourLeather(ItemStack item, Color colour)
	{
		ItemStack i = item;
		if ((i.getItemMeta() instanceof LeatherArmorMeta)) {
			LeatherArmorMeta lam = (LeatherArmorMeta)i.getItemMeta();
			lam.setColor(colour);
			i.setItemMeta(lam);
		}
		return i;
	}

	public static ItemStack recolorLeather(ItemStack item, Color color) {
		return recolourLeather(item, color);
	}

	public static void clearArmor(Player player){
		player.getInventory().setHelmet(null);
		player.getInventory().setChestplate(null);
		player.getInventory().setLeggings(null);
		player.getInventory().setBoots(null);
	}

	public static ItemStack setName(ItemStack is, String name){
		ItemMeta m = is.getItemMeta();
		m.setDisplayName(name);
		is.setItemMeta(m);
		return is;
	}

	public static void EntityToRemove(final Entity e,  int time , final boolean withEffect) {
		Bukkit.getScheduler().runTaskLater(Main.schedule, new Runnable(){

			@Override
			public void run() {
				if(e.isValid()) {
					e.remove();
					if (withEffect == true) {
						e.getWorld().playSound(e.getLocation(), Sound.FUSE, 0.1f, 1);
						UtilParticle.sendParticleToLocation(e.getLocation(), Particle.LARGE_SMOKE, 0, 0, 0, 0, 3);
					}
				}
			}

		}, time*10);

	}


}