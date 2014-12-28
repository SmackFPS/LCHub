package me.jrl1004.lightcraft.gadgets.arcs;

import java.util.ArrayList;

import net.lightcraftmc.fusebox.configuration.Configuration;
import net.lightcraftmc.fusebox.util.UtilLocation;
import net.lightcraftmc.fusebox.util.particles18.ParticleLib18.ParticleType;

import org.bukkit.Location;

import com.lightcraftmc.hub.main.Main;

public class ArcDataSaver {
	private static Configuration config;
	private static ArrayList<ParticleArc> existingArcs = new ArrayList<ParticleArc>();

	private ArcDataSaver() {
		setup();
		startAllArcs();
	}

	public static void addArcToSaveList(ParticleArc arc) {
		existingArcs.add(arc);
	}
	
	private static void setup() {
		config = new Configuration(Main.getInstance(), "arc.yml");
		existingArcs.addAll(getSavedSets());
	}

	public static void saveArcsToFile(ParticleArc... arcs) {
		int i = 0;
		config.GetConfig().set("Arc", null);
		for(ParticleArc arc : arcs){
			String path = "Arc." + i;
			config.GetConfig().set(path + ".Location.Start", UtilLocation.locationToString(arc.getStart()));
			config.GetConfig().set(path + ".Location.End", UtilLocation.locationToString(arc.getEnd()));
			config.GetConfig().set(path + ".Height", arc.getArcHeight());
			config.GetConfig().set(path + ".Seconds", arc.getArcTime());
			config.GetConfig().set(path + ".Particle", arc.getType().name());
			i++;
		}
		config.SaveConfig();
	}

	private static ArrayList<ParticleArc> getSavedSets() {
		ArrayList<ParticleArc> arcs = new ArrayList<ParticleArc>();
		for(String path : config.GetConfig().getConfigurationSection("Arc").getKeys(false)){
			path = "Arc." + path;
			ParticleType type = ParticleType.valueOf(config.GetConfig().getString(path + ".Particle"));
			Location start = UtilLocation.getLocation(config.GetConfig().getString(path + ".Location.Start"));
			Location end = UtilLocation.getLocation(config.GetConfig().getString(path + ".Location.End"));
			double height = config.GetConfig().getDouble(path + ".Height");
			double time = config.GetConfig().getDouble(path + ".Seconds");
			ParticleArc arc = new ParticleArc(type, start, end, height,
					time);
			arcs.add(arc);
		}
		return arcs;
	}

	public static void startAllArcs() {
		for (ParticleArc arc : existingArcs)
			arc.beginArcing();
	}
}
