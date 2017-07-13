package financial;

public class PublicFund extends InstrumentFinancial{

	public PublicFund(String inCode, String inName, double inEAR, double inTotal, double inYear) {
		super(inCode, inName, inEAR, inTotal, inTotal / 101.00, inYear);
		// TODO Auto-generated constructor stub
	}

}
