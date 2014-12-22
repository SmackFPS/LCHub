package me.jrl1004.lightcraft.gadgets.arcs;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import me.mike1665.Main.Main;
import net.lightcraftmc.fusebox.util.particles18.ParticleLib18.ParticleType;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.YamlConfiguration;

public class ArcDataSaver {
	private static File saveFile;
	private static YamlConfiguration config;
	private static ArrayList<ParticleArc> existingArcs = new ArrayList<ParticleArc>();
	private static boolean _configLoaded = false;

	private ArcDataSaver() {
	}

	public static void addArcToSaveList(ParticleArc arc) {
		existingArcs.add(arc);
	}
	
	private static void setup() {
		if (saveFile == null)
			saveFile = new File(Main.getInstance().getDataFolder(), "ARCS");
		if (!saveFile.exists())
			try {
				saveFile.createNewFile();
			} catch (Exception e) {
				e.printStackTrace();
			}
		config = YamlConfiguration.loadConfiguration(saveFile);
		if (!_configLoaded) {
			existingArcs.addAll(getSavedSets());
			_configLoaded = true;
		}
	}

	public static void saveArcsToFile(ParticleArc... arcs) {
		if(!_configLoaded) setup();
		if (arcs.length == 0)
			return;
		ArrayList<List<String>> arcData = new ArrayList<List<String>>();
		for (ParticleArc arc : arcs) {
			List<String> data = new ArrayList<String>();
			data.add(arc.getType().toString());
			Location l = arc.getStart();
			data.add(l.getWorld().getName());
			data.add(l.getBlockX() + "|" + l.getBlockY() + "|" + l.getBlockZ());
			l = arc.getEnd();
			data.add(l.getBlockX() + "|" + l.getBlockY() + "|" + l.getBlockZ());
			arcData.add(data);
			data.add(Double.toString(arc.getArcHeight()));
			data.add(Double.toString(arc.getArcTime()));
		}
		for (int i = 0; i < arcData.size(); i++) {
			config.set(Integer.toString(i), arcData.get(i));
		}
		save();
	}

	private static void save() {
		if(!_configLoaded) setup();
		try {
			config.save(saveFile);
		} catch (Exception e) {
			e.printStackTrace();
			Main.getInstance().getLogger().severe("Failed to save ARCS file");
		}
	}

	private static ArrayList<ParticleArc> getSavedSets() {
		Set<String> sections = config.getKeys(false);
		ArrayList<ParticleArc> arcs = new ArrayList<ParticleArc>();
		if (sections.size() == 0)
			return arcs;
		for (String s : sections) {
			try {
				List<String> list = config.getStringList(s);
				if (list.size() < 6)
					continue;
				ParticleType type = ParticleType.valueOf(list.get(0));
				World w = Bukkit.getWorld(list.get(1));
				String[] loc = list.get(2).split("|");
				Location start = new Location(w, Double.parseDouble(loc[0]),
						Double.parseDouble(loc[1]), Double.parseDouble(loc[2]));
				loc = list.get(3).split("|");
				Location end = new Location(w, Double.parseDouble(loc[0]),
						Double.parseDouble(loc[1]), Double.parseDouble(loc[2]));
				double height = Double.parseDouble(list.get(4));
				double seconds = Double.parseDouble(list.get(5));
				ParticleArc arc = new ParticleArc(type, start, end, height,
						seconds);
				arcs.add(arc);
			} catch (Exception exc) {
				continue;
			}
		}
		return arcs;
	}

	public static void startAllArcs() {
		if(_configLoaded) setup();
		if (existingArcs.size() == 0)
			return;
		for (ParticleArc arc : existingArcs)
			arc.beginArcing();
	}
}
