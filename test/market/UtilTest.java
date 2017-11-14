package market;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.junit.Test;

import com.dream.bean.Entity;
import com.dream.util.TradeBuyComparator;
import com.dream.util.TradeComparator;
import com.dream.util.Utility;

public class UtilTest {

	@Test
	public void test() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetSettlementDate() {
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = fmt.parse("2017-11-11");
			// Sat, Sun
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Utility.getSettlementDate(date);
	}

	@Test
	public void testFile() {
		List<Entity> dataFromFile = Utility.getDataFromFile();
		List<Entity> buyEntity = new ArrayList<>();
		List<Entity> sellEntity = new ArrayList<>();
		// System.out.println(dataFromFile.size());
		for (Entity objEntity : dataFromFile) {
			objEntity.setSettlementDate(Utility.getSettlementDate(objEntity.getSettlementDate()));
		}

		Set<Date> distinctSettleDate = Utility.getDistinctSettleDate(dataFromFile);
		System.out.println("------------------------Incoming report------------------------");
		System.out.println("-------Day wise-------");
		Map<Date, List<Entity>> dateListMap = Utility.getSettleDateMap(dataFromFile, distinctSettleDate);
		for (Entry<Date, List<Entity>> entry : dateListMap.entrySet()) {
			Date key = entry.getKey();
			System.out.print("Date : " + new SimpleDateFormat("dd MMM yyyy").format(key));
			System.out.println(", Total(USD):  " + Utility.getIncomingAmount(entry.getValue()));
		}

		for (Entity objEntity : dataFromFile) {
			if ("S".equals(objEntity.getType())) {
				sellEntity.add(objEntity);
			} else {
				buyEntity.add(objEntity);
			}
		}

		Collections.sort(sellEntity, new TradeComparator());
		System.out.println("-------Ranking-------");
		int j = 0;
		for (Entity objEntity : sellEntity) {
			System.out.println(++j + " | " + objEntity.getName());
		}

		System.out.println("------------------------Outcoming report------------------------");
		System.out.println("-------Day wise-------");
		for (Entry<Date, List<Entity>> entry : dateListMap.entrySet()) {
			Date key = entry.getKey();
			System.out.print("Date : " + new SimpleDateFormat("dd MMM yyyy").format(key));
			System.out.println(", Total(USD):  " + Utility.getOutgoingAmount(entry.getValue()));
		}

		Collections.sort(buyEntity, new TradeBuyComparator());
		System.out.println("-------Ranking-------");
		int i = 0;
		for (Entity objEntity : buyEntity) {
			System.out.println(++i + " | " + objEntity.getName());
		}
 }
}
