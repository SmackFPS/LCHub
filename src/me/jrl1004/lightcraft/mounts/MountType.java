package me.jrl1004.lightcraft.mounts;

import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Horse.Color;
import org.bukkit.entity.Horse.Style;
import org.bukkit.entity.Horse.Variant;

public enum MountType {

	DRAGON(Color.BLACK, Style.BLACK_DOTS, Variant.HORSE), FROST(Color.WHITE,
			Style.WHITE, Variant.HORSE), MULE(Color.BLACK, Style.BLACK_DOTS,
			Variant.MULE), UNDEAD(Color.BLACK, Style.BLACK_DOTS,
			Variant.SKELETON_HORSE);

	final Color color;
	final Style style;
	final Variant variant;

	MountType(Color c, Style s, Variant v) {
		this.color = c;
		this.style = s;
		this.variant = v;
	}

	public Color getColor() {
		return this.color;
	}

	public Style getStyle() {
		return this.style;
	}

	public Variant getVariant() {
		return this.variant;
	}

	public String getName(OfflinePlayer player) {
		String name = ChatColor.translateAlternateColorCodes('&', "&a&l"
				+ player.getName() + "'s Mount");
		return name;
	}
}
