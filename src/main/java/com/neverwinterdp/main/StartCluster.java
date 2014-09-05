package com.neverwinterdp.main;

import com.neverwinterdp.demandspike.DemandSpikeClusterBuilder;

public class StartCluster {
	static DemandSpikeClusterBuilder clusterBuilder;
	public static void main(String[] args) throws Exception {
		clusterBuilder = new DemandSpikeClusterBuilder();
		clusterBuilder.start();
		clusterBuilder.install();
	}
}
