package com.dream.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.dream.bean.Entity;

public class Market {

	public static void main(String[] args) {
		
		

		List<Entity> dataFromFile = Utility.getDataFromFile();
		List<Entity> buyEntity = new ArrayList<>();
		List<Entity> sellEntity = new ArrayList<>();
		System.out.println("------------------------Incoming report------------------------");
		System.out.println("-------Day wise-------");
		
		// Report 1
		System.out.println("Get incoming amount:" + Utility.getIncomingAmount(dataFromFile));

		// Report 2
		System.out.println("Get outgoing amount:" + Utility.getOutgoingAmount(dataFromFile));

		for (Entity objEntity : dataFromFile) {
			if ("S".equals(objEntity.getType())) {
				sellEntity.add(objEntity);
			} else {
				buyEntity.add(objEntity);
			}
		}

		// Report 3
		Collections.sort(buyEntity, new TradeComparator());
		System.out.println("Buy Rank 1 ->" + buyEntity.get(0));

		Collections.sort(sellEntity, new TradeComparator());
		System.out.println("Sell Rank 1 ->" + sellEntity.get(0));

	}

}
