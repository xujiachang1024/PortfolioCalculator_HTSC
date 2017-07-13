package financial;

public class Currency extends InstrumentFinancial {

	public Currency(String inCode, String inName, double inEAR, double inTotal, double inYear) {
		super(inCode, inName, inEAR, inTotal, 0.00, inYear);
	}

}
