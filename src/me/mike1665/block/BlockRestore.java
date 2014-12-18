package me.mike1665.block;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.UUID;

import me.mike1665.chest.BuildManager;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public class BlockRestore
{
  public static HashMap<UUID, ArrayList<Save>> saves = new HashMap();

  public static void init(Player p)
  {
    if (!saves.containsKey(p.getUniqueId()))
    {
      saves.put(p.getUniqueId(), new ArrayList());
    }
  }

  public static void save(Player p, Location l)
  {
    init(p);
    ((ArrayList)saves.get(p.getUniqueId())).add(new Save(l.getBlock()));
  }

  public static void restore(Player p)
  {
    Iterator iter = ((ArrayList)saves.get(p.getUniqueId())).iterator();
    while (iter.hasNext())
    {
      Save save = (Save)iter.next();
      save.restore();
    }
    BuildManager.treasureChestLocation.remove(p.getUniqueId());
    ((ArrayList)saves.get(p.getUniqueId())).clear();
    saves.remove(p.getUniqueId());
  }
}