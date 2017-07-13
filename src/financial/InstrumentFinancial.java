package financial;

public class InstrumentFinancial {
	
	private String mCode; 				// 产品代码
	private String mName; 				// 产品名称
	private Double mEAR; 				// 产品年化收益率（单位：%）
	private Double mTotal;				// 产品实际投入总金额（实际仓位+管理费，单位：万元）
	private Double mFee;				// 产品管理费（单位：万元）
	private Double mPosition;			// 产品实际仓位（单位：万元）
	private Double mEarning;            // 产品预期回报
	private Double mYear;				// 产品投资时长（单位：年）

	public InstrumentFinancial(String inCode, String inName, double inEAR, double inTotal, double inFee, double inYear) {
		this.mCode = inCode;
		this.mName = inName;
		this.mEAR = inEAR;
		this.mTotal = inTotal;
		this.mFee = inFee;
		this.mYear = inYear;
		this.updatePosition();
		this.updateEarning();
	}
	
	private void updatePosition() {
		this.mPosition = mTotal - mFee;
	}
	
	private void updateEarning() {
		this.mEarning = mPosition * mEAR * mYear;
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
	
	public double getEarning() {
		return mEarning;
	}
}
