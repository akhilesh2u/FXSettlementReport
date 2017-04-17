import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FXTransactionReport {

	public boolean generateFXTransactionReport(List<FXTransactionDataModel> listData){
		
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
		rankentities.rankEntitiesByTransaction(listData, 'B').forEach((f) -> {
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
		rankentities.rankEntitiesByTransaction(listData, 'S').forEach((f) -> {
			System.out.println(f.getRank() + "     " + f.getEntity() + "      " + f.getTotalamount());
		});
		
		return true;
	}
}
