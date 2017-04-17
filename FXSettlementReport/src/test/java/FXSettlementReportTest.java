
/*
* @author Akhiles Perla
* 
* */
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;

import junit.framework.TestCase;

public class FXSettlementReportTest extends TestCase {
	
	private List<FXTransactionDataModel> listData = new ArrayList<FXTransactionDataModel>();

	public FXSettlementReportTest(String testName) {
		super(testName);
	}

	protected void setUp() throws Exception {
		super.setUp();
		
		
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
		listData.add(data7);	}

	protected void tearDown() throws Exception {
		super.tearDown();
		listData= null;
	}

	public void testGenerateFXTransactionReport() {
		boolean success = true;
		FXTransactionReport fxTransactionReport = new FXTransactionReport();
		boolean status = fxTransactionReport.generateFXTransactionReport(listData);
		assertEquals(status, success);
	}

}