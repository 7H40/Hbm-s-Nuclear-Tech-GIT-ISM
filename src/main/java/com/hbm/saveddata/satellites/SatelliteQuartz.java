package com.hbm.saveddata.satellites;

import com.hbm.itempool.ItemPoolsSatellite;

public class SatelliteQuartz extends SatelliteMiner {

	static {
		registerCargo(SatelliteQuartz.class, ItemPoolsSatellite.POOL_SAT_QUARTZ);
	}
}
