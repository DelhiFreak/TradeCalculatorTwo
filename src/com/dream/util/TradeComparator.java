package com.dream.util;

import java.util.Comparator;

import com.dream.bean.Entity;

public class TradeComparator implements Comparator<Entity> {
	public int compare(Entity p1, Entity p2) {
		float amount1 = p1.getTradeAmount();
		float amount2 = p2.getTradeAmount();

		if (amount1 == amount2)
			return 0;
		else if (amount1 < amount2)
			return 1;
		else
			return -1;
	}
}
