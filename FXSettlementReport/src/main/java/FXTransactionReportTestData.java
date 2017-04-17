
/**
* The FXTransactionReportTestData is the main class where FX transactions are passed for the generation of report.
*
* @author  Akhilesh Perla
* @version 1.0
* @since   2017-04-17 
*/
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Currency;

import java.util.List;
import java.util.Map;

import java.util.stream.Collectors;

public class FXTransactionReportTestData {

	public static void main(String args[]) {
		List<FXTransactionDataModel> listData = new ArrayList<FXTransactionDataModel>();
		
		/*
		 * Create the FX transaction details by passing the values
		 * entity,transcationType,agreedFx,currenyType,instructionDate,
		 * settlementDate,units,pricePerUnit
		 */
		FXTransactionDataModel data1 = new FXTransactionDataModel("foo", 'B', 0.50f, Currency.getInstance("SGD"),
				LocalDate.of(2016, 01, 01), LocalDate.of(2016, 01, 02), 200, 100.25);

		FXTransactionDataModel data2 = new FXTransactionDataModel("bar", 'B', 0.22f, Currency.getInstance("AED"),
				LocalDate.of(2016, 01, 05), LocalDate.of(2016, 01, 07), 450, 150.5);
		FXTransactionDataModel data3 = new FXTransactionDataModel("foo", 'S', 0.55f, Currency.getInstance("EUR"),
				LocalDate.of(2017, 01, 01), LocalDate.of(2017, 01, 02), 100, 100.25);
		FXTransactionDataModel data4 = new FXTransactionDataModel("bar", 'S', 0.22f, Currency.getInstance("GBP"),
				LocalDate.of(2017, 01, 01), LocalDate.of(2017, 01, 14), 200, 100.25);
		FXTransactionDataModel data5 = new FXTransactionDataModel("bat", 'B', 0.32f, Currency.getInstance("SGD"),
				LocalDate.of(2017, 01, 01), LocalDate.of(2017, 01, 11), 300, 100.25);
		FXTransactionDataModel data6 = new FXTransactionDataModel("foo1", 'B', 0.50f, Currency.getInstance("SGD"),
				LocalDate.of(2017, 12, 01), LocalDate.of(2017, 01, 02), 400, 300.25);
		FXTransactionDataModel data7 = new FXTransactionDataModel("zoo1", 'B', 0.50f, Currency.getInstance("AED"),
				LocalDate.of(2017, 12, 01), LocalDate.of(2017, 01, 02), 400, 300.25);

		// Add the FX transaction details to a list
		listData.add(data1);
		listData.add(data2);
		listData.add(data3);
		listData.add(data4);
		listData.add(data5);
		listData.add(data6);
		listData.add(data7);

		RankEntities rankentities = new RankEntities();

		/* Calculate the incoming FX transactions settled for each day */
		Map<LocalDate, Double> mapdataBuy = listData.stream().filter(x -> x.getTranscationType() == 'B')
				.collect(Collectors.groupingBy(FXTransactionDataModel::getSettlementDate,
						Collectors.summingDouble(FXTransactionDataModel::getTotalTransaction)));

		System.out.println("Amount in USD settled incoming everyday");
		mapdataBuy.entrySet().forEach(b -> {
			System.out.println(b.getKey() + "     " + b.getValue());
		});
		System.out.println("------------------------------------");
		/* Rank the entities for all the incoming FX transactions settled */
		System.out.println("Below are th ranking of entities based on incoming amount");
		System.out.println("Rank   " + "  Entity    " + " Total Buy Amount");
		rankentities.RankEntitiesByTransaction(listData, 'B').forEach((f) -> {
			System.out.println(f.getRank() + "     " + f.getEntity() + "      " + f.getTotalamount());
		});
		System.out.println("------------------------------------");
		/* Calculate the outgoing FX transactions settled for each day */
		Map<LocalDate, Double> mapdataSell = listData.stream().filter(x -> x.getTranscationType() == 'S')
				.collect(Collectors.groupingBy(FXTransactionDataModel::getSettlementDate,
						Collectors.summingDouble(FXTransactionDataModel::getTotalTransaction)));

		System.out.println("Amount in USD settled outgoing everyday");
		mapdataSell.entrySet().forEach(s -> {
			System.out.println(s.getKey() + "     " + s.getValue());
		});
		System.out.println("------------------------------------");
		/* Rank the entities for all the outgoing FX transactions settled */
		System.out.println("Below are th ranking of entities based on outgoing amount");
		System.out.println("Rank   " + "  Entity    " + " Total Sell Amount");
		rankentities.RankEntitiesByTransaction(listData, 'S').forEach((f) -> {
			System.out.println(f.getRank() + "     " + f.getEntity() + "      " + f.getTotalamount());
		});

	}

}
