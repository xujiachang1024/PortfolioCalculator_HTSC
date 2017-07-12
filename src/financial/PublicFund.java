package financial;

public class PublicFund extends InstrumentFinancial{

	public PublicFund(String inCode, String inName, double inEAR, double inTotal) {
		super(inCode, inName, inEAR, inTotal, inTotal / 101.00);
		// TODO Auto-generated constructor stub
	}

}
