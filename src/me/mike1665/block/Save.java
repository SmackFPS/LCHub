package me.mike1665.block;

import me.mike1665.particles18.ParticleLib18;
import me.mike1665.utils.UtilBlock;
import me.mike1665.utils.UtilityBlock;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;

public class Save
{
  private Location location;
  private Material type;
  private byte data;

  public Save(Block block)
  {
    UtilityBlock.blockToRestore.add(block);
    this.location = block.getLocation();
    this.type = block.getType();
    this.data = block.getData();
  }

  public void restore()
  {
    Block block = this.location.getBlock();
	ParticleLib18 res = new ParticleLib18(me.mike1665.particles18.ParticleLib18.ParticleType.SMOKE_LARGE, 0.1000000014901161D, 2, 0.3D);
	res.sendToLocation(this.location);
	UtilityBlock.blockToRestore.remove(block);
    block.setType(this.type);
    block.setData(this.data);
  }
}