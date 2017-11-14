package com.dream.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.dream.bean.Entity;

/**
 * 
 * @author dream
 *
 */
public class Utility {
	/**
	 * This method return settlement to next working day if it's belong to
	 * Saturday/Sunday
	 * 
	 * @param inputDate
	 * @return
	 */
	public static Date getSettlementDate(Date inputDate) {
		SimpleDateFormat sdf = new SimpleDateFormat("EEE");
		String dayName = sdf.format(inputDate);
		if ("Sat".equals(dayName)) {
			// convert date to calendar
			Calendar c = Calendar.getInstance();
			c.setTime(inputDate);

			// manipulate date
			c.add(Calendar.DATE, 2);
			inputDate = c.getTime();
		} else if ("Sun".equals(dayName)) {
			// convert date to calendar
			Calendar c = Calendar.getInstance();
			c.setTime(inputDate);

			// manipulate date
			c.add(Calendar.DATE, 1);
			inputDate = c.getTime();
		}

		// System.out.println("Just Test :" + sdf.format(inputDate));
		return inputDate;
	}

	public static List<Entity> getDataFromFile() {
		List<Entity> entities = new ArrayList<>();
		FileReader freader;
		try {
			freader = new FileReader("Data.txt");
			BufferedReader br = new BufferedReader(freader);
			String s;
			while ((s = br.readLine()) != null) {
				Entity objEntity = new Entity();
				String data[] = s.split(",");
				objEntity.setName(data[0].trim());
				objEntity.setType(data[1].trim());
				objEntity.setAgreedFx(Float.parseFloat(data[2].trim()));
				objEntity.setCurrency(data[3].trim());
				objEntity.setInstructionDate(getDateFromDDMMYYYY(data[4].trim()));
				objEntity.setSettlementDate(getDateFromDDMMYYYY(data[5].trim()));
				objEntity.setUnits(Integer.parseInt(data[6].trim()));
				objEntity.setPricePerUnit(Float.parseFloat(data[7].trim()));
				entities.add(objEntity);
			}
			freader.close();
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		}
		return entities;
	}

	public static Date getDateFromDDMMYYYY(String inputDateStr) throws ParseException {
		SimpleDateFormat fmt = new SimpleDateFormat("dd MMM yyyy");
		return fmt.parse(inputDateStr);
	}

	public static float getIncomingAmount(List<Entity> entities) {
		float amount = 0;
		for (Entity objEntity : entities) {
			if ("S".equals(objEntity.getType())) {
				amount += objEntity.getTradeAmount();
			}
		}
		return amount;

	}

	public static float getOutgoingAmount(List<Entity> entities) {
		float amount = 0;
		for (Entity objEntity : entities) {
			if ("B".equals(objEntity.getType())) {
				amount += objEntity.getTradeAmount();
			}
		}
		return amount;
	}

	public static Set<Date> getDistinctSettleDate(List<Entity> entities) {
		Set<Date> settleDateSet = new HashSet<>();
		for (Entity objEntity : entities) {
			settleDateSet.add(objEntity.getSettlementDate());
		}

		return settleDateSet;
	}

	public static Map<Date, List<Entity>> getSettleDateMap(List<Entity> entities, Set<Date> dateSet) {
		Map<Date, List<Entity>> map = new HashMap<>();
		for (Date objDate : dateSet) {
			List<Entity> list = new ArrayList<>();
			for (Entity objEntity : entities) {
				if (objDate.equals(objEntity.getSettlementDate())) {
					list.add(objEntity);
				}
			}

			map.put(objDate, list);
		}
		return map;
	}
}
