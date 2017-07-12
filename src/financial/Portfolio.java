package financial;

import java.util.ArrayList;
import java.util.List;

public class Portfolio {
	
	private Double mTotalFunds;						// 投资资金总规模（单位：万元）
	private Double mTotalPosition; 					// 实际仓位总金额（单位：万元）
	private Double mTotalFees;						// 管理费用总金额（单位：万元）
	private Double mTotalCash;						// 空仓规模（单位：万元）
	private Double mTotalExpectedRevenue;			// 投资组合预期收益（单位：万元）
	private Double mTotalEAR;						// 投资组合预期年化收益率（单位：%)
	private List<InstrumentFinancial> mAllAssets;	// 投资组合列表

	public Portfolio(double inTotalFunds) {
		this.mTotalFunds = inTotalFunds;
		this.mTotalPosition = 0.00;
		this.mTotalFees = 0.00;
		this.mTotalExpectedRevenue = 0.00;
		this.mAllAssets = new ArrayList<InstrumentFinancial>();
		this.updateMemebrs();
	}
	
	private void updateMemebrs() {
		this.mTotalCash = mTotalFunds - mTotalPosition - mTotalFees;
		if (mTotalFunds == (double)0) {
			this.mTotalEAR = 0.00;
		}
		else {
			this.mTotalEAR = ((mTotalExpectedRevenue + mTotalCash) / mTotalFunds - 1) * 100.00;
		}
	}
	
	public void setTotalFunds(double inTotalFunds) {
		this.mTotalFunds = inTotalFunds;
		this.updateMemebrs();
	}
	
	public void add(InstrumentFinancial newAsset) {
		this.mAllAssets.add(newAsset);
		this.mTotalPosition += newAsset.getPosition();
		this.mTotalFees += newAsset.getFee();
		this.mTotalExpectedRevenue += newAsset.getRevenue();
		this.updateMemebrs();
	}
	
	public boolean remove(InstrumentFinancial oldAsset) {
		if (this.mAllAssets.remove(oldAsset)) {
			this.mTotalPosition -= oldAsset.getPosition();
			this.mTotalFees -= oldAsset.getFee();
			this.mTotalExpectedRevenue -= oldAsset.getRevenue();
			this.updateMemebrs();
			return true;
		}
		return false;
	}
	
	public void removeAll() {
		this.mTotalPosition = 0.00;
		this.mTotalFees = 0.00;
		this.mTotalExpectedRevenue = 0.00;
		this.mAllAssets = new ArrayList<InstrumentFinancial>();
		this.updateMemebrs();
	}
	
	public double getTotalFunds() {
		return mTotalFunds;
	}
	
	public double getTotalPosition() {
		return mTotalPosition;
	}
	
	public double getTotalFees() {
		return mTotalFees;
	}
	
	public double getTotalCash() {
		return mTotalCash;
	}
	
	public double getTotalEAR() {
		return mTotalEAR;
	}
	
	public List<InstrumentFinancial> getAllAssets() {
		return mAllAssets;
	}
}
