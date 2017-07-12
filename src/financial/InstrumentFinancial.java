package financial;

public class InstrumentFinancial {
	
	private String mCode; 				// 产品代码
	private String mName; 				// 产品名称
	private Double mEAR; 				// 产品年化收益率（单位：%）
	private Double mTotal;				// 产品实际投入总金额（实际仓位+管理费，单位：万元）
	private Double mFee;				// 产品管理费（单位：万元）
	private Double mPosition;			// 产品实际仓位（单位：万元）
	private Double mExpectedRevenue; 	// 产品预期年化收益（本金+回报）

	public InstrumentFinancial(String inCode, String inName, double inEAR, double inTotal, double inFee) {
		this.mCode = inCode;
		this.mName = inName;
		this.mEAR = inEAR;
		this.mTotal = inTotal;
		this.mFee = inFee;
		this.updatePosition();
		this.updateExpectedRevenue();
	}
	
	private void setCode(String inCode) {
		this.mCode = inCode;
	}
	
	private void setName(String inName) {
		this.mName = inName;
	}
	
	private void setEAR(double inEAR) {
		this.mEAR = inEAR;
	}
	
	private void setTotal(double inTotal) {
		this.mTotal = inTotal;
	}
	
	private void setFee(double inFee) {
		this.mFee = inFee;
	}
	
	private void updatePosition() {
		this.mPosition = mTotal - mFee;
	}
	
	private void updateExpectedRevenue() {
		this.mExpectedRevenue = mPosition * (1 + mEAR);
	}
	
	public String getCode() {
		return mCode;
	}
	
	public String getName() {
		return mName;
	}
	
	public double getEAR() {
		return mEAR;
	}
	
	public double getTotal() {
		return mTotal;
	}
	
	public double getFee() {
		return mFee;
	}
	
	public double getPosition() {
		return mPosition;
	}
	
	public double getExpectedRevenue() {
		return mExpectedRevenue;
	}
}
