package com.arrayprolc.event;

import me.mike1665.Main.Main;

import org.bukkit.Bukkit;

import com.arrayprolc.tools.BoxPercentage;

public class TPSMeter {
	public static int tps = 0;
	public static void setupTPSMeter(Main instance){
		Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(instance, new Runnable()
		{
			long sec;
			long currentSec;
			int ticks;
			int delay;

			@Override
			public void run()
			{
				sec = (System.currentTimeMillis() / 1000);

				if(currentSec == sec)
				{
					// this code block triggers each tick

					ticks++;
				}
				else
				{
					// this code block triggers each second

					currentSec = sec;
					tps = (tps == 0 ? ticks : ((tps + ticks) / 2));
					ticks = 0;

					if((++delay % 300) == 0)
					{
						// this code block triggers each 5 minutes

						delay = 0;

					}
				}
			}
		}, 0, 1); // do not change the "1" value, the other one is just initial delay, I recommend 0 = start instantly.

	}
	
	public static String getServerTPS(){
		double percent = tps/20;
		if(tps <= 0){
			percent = 1;
		}
		return "§9Performance > §f" + BoxPercentage.generateBoxPercentage(tps/20) + " §7(" + percent + "%)";
	}
}
