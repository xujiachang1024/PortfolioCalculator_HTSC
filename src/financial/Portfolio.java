package financial;

import java.util.ArrayList;
import java.util.List;

public class Portfolio {
	
	private Double mTotalFunds;						// 投资资金总规模（单位：万元）
	private Double mCashLowerLimit;					// 空仓规模下限（单位：万元）
	private Double mTotalPosition; 					// 实际仓位总金额（单位：万元）
	private Double mTotalFees;						// 管理费用总金额（单位：万元）
	private Double mTotalCash;						// 空仓规模（单位：万元）
	private Double mTotalEarning;					// 投资组合预期回报（单位：万元）
	private Double mTotalEAR;						// 投资组合预期年化收益率（单位：%)
	private Double mTotalPrivateFund;				// 私募基金类产品投入金额（单位：万元）
	private Double mTotalPublicFund;				// 公募基金类产品投入金额（单位：万元）
	private Double mTotalFixedIncome;				// 固定收益类产品投入金额（单位：万元）
	private Double mTotalCommodity;					// 大宗商品类产品投入金额（单位：万元）
	private Double mTotalCurrency;					// 货币类产品投入金额（单位：万元）
	private Double mTotalOther;						// 另类产品投入金额（单位：万元）
	private List<InstrumentFinancial> mAllAssets;	// 投资组合列表

	public Portfolio(double inTotalFunds, double inCashLowerLimit) {
		this.mTotalFunds = inTotalFunds;
		this.mCashLowerLimit = inCashLowerLimit;
		this.mTotalPosition = 0.00;
		this.mTotalFees = 0.00;
		this.mTotalEarning = 0.00;
		this.mTotalPrivateFund = 0.00;
		this.mTotalPublicFund = 0.00;
		this.mTotalFixedIncome = 0.00;
		this.mTotalCommodity = 0.00;
		this.mTotalCurrency = 0.00;
		this.mTotalOther = 0.00;
		this.mAllAssets = new ArrayList<InstrumentFinancial>();
		this.updateMemebrs();
	}
	
	private void updateMemebrs() {
		this.updateTotalCash();
		this.updateTotalEAR();
	}
	
	private void updateTotalCash() {
		// 空仓规模 = 投入规模 - 持仓规模 - 管理费用
		this.mTotalCash = mTotalFunds - mTotalPosition - mTotalFees;
	}
	
	private void updateTotalEAR() {
		if (mTotalFunds == (double)0) {
			this.mTotalEAR = 0.00;
		}
		else {
			// 组合年化收益率 = （持仓规模 + 空仓规模 + 组合预期回报）/（投入本金）- 1 
			this.mTotalEAR = ((mTotalPosition + mTotalCash + mTotalEarning) / mTotalFunds - 1) * 100.00;
		}
	}
	
	public void setTotalFunds(double inTotalFunds) {
		this.mTotalFunds = inTotalFunds;
		this.updateMemebrs();
	}
	
	public void setCashLowerLimit(double inCashLowerLimit) {
		this.mCashLowerLimit = inCashLowerLimit;
		this.updateMemebrs();
	}
	
	public void add(InstrumentFinancial newAsset) {
		this.mAllAssets.add(newAsset);
		this.mTotalPosition += newAsset.getPosition();
		this.mTotalFees += newAsset.getFee();
		this.mTotalEarning += newAsset.getEarning();
		this.updateMemebrs();
		if (newAsset instanceof PrivateFund) {
			this.mTotalPrivateFund += newAsset.getTotal();
		}
		else if (newAsset instanceof PublicFund) {
			this.mTotalPublicFund += newAsset.getTotal();
		}
		else if (newAsset instanceof FixedIncome) {
			this.mTotalFixedIncome += newAsset.getTotal();
		}
		else if (newAsset instanceof Commodity) {
			this.mTotalCommodity += newAsset.getTotal();
		}
		else if (newAsset instanceof Currency) {
			this.mTotalCurrency += newAsset.getTotal();
		}
		else if (newAsset instanceof Other) {
			this.mTotalOther += newAsset.getTotal();
		}
	}
	
	public boolean remove(int index) {
		InstrumentFinancial oldAsset = mAllAssets.remove(index);
		this.mTotalPosition -= oldAsset.getPosition();
		this.mTotalFees -= oldAsset.getFee();
		this.mTotalEarning -= oldAsset.getEarning();
		this.updateMemebrs();
		if (oldAsset instanceof PrivateFund) {
			this.mTotalPrivateFund -= oldAsset.getTotal();
		}
		if (oldAsset instanceof PublicFund) {
			this.mTotalPublicFund -= oldAsset.getTotal();
		}
		if (oldAsset instanceof FixedIncome) {
			this.mTotalFixedIncome -= oldAsset.getTotal();
		}
		if (oldAsset instanceof Commodity) {
			this.mTotalCommodity -= oldAsset.getTotal();
		}
		if (oldAsset instanceof Currency) {
			this.mTotalCurrency -= oldAsset.getTotal();
		}
		if (oldAsset instanceof Other) {
			this.mTotalOther -= oldAsset.getTotal();
		}
		return true;
	}
	
	public boolean remove(InstrumentFinancial oldAsset) {
		if (this.mAllAssets.remove(oldAsset)) {
			this.mTotalPosition -= oldAsset.getPosition();
			this.mTotalFees -= oldAsset.getFee();
			this.mTotalEarning -= oldAsset.getEarning();
			this.updateMemebrs();
			if (oldAsset instanceof PrivateFund) {
				this.mTotalPrivateFund -= oldAsset.getTotal();
			}
			if (oldAsset instanceof PublicFund) {
				this.mTotalPublicFund -= oldAsset.getTotal();
			}
			if (oldAsset instanceof FixedIncome) {
				this.mTotalFixedIncome -= oldAsset.getTotal();
			}
			if (oldAsset instanceof Commodity) {
				this.mTotalCommodity -= oldAsset.getTotal();
			}
			if (oldAsset instanceof Currency) {
				this.mTotalCurrency -= oldAsset.getTotal();
			}
			if (oldAsset instanceof Other) {
				this.mTotalOther -= oldAsset.getTotal();
			}
			return true;
		}
		return false;
	}
	
	public void removeAll() {
		this.mTotalPosition = 0.00;
		this.mTotalFees = 0.00;
		this.mTotalEarning = 0.00;
		this.mAllAssets = new ArrayList<InstrumentFinancial>();
		this.updateMemebrs();
	}
	
	public double getTotalFunds() {
		return mTotalFunds;
	}
	
	public double getCashLowerLimit() {
		return mCashLowerLimit;
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
	
	public double getPrivateFundPercentage() {
		if (mTotalPosition + mTotalFees == 0) {
			return 0.00;
		}
		else {
			double percentage = mTotalPrivateFund / (mTotalPosition + mTotalFees);
			return percentage * 100.00;
		}
	}
	
	public double getPublicFundPercentage() {
		if (mTotalPosition + mTotalFees == 0) {
			return 0.00;
		}
		else {
			double percentage = mTotalPublicFund / (mTotalPosition + mTotalFees);
			return percentage * 100.00;
		}
	}
	
	public double getFixedIncomePercentage() {
		if (mTotalPosition + mTotalFees == 0) {
			return 0.00;
		}
		else {
			double percentage = mTotalFixedIncome / (mTotalPosition + mTotalFees) ;
			return percentage * 100.00;
		}
	}
	
	public double getCommodityPercentage() {
		if (mTotalPosition + mTotalFees == 0) {
			return 0.00;
		}
		else {
			double percentage = mTotalCommodity / (mTotalPosition + mTotalFees);
			return percentage * 100.00;
		}
	}
	
	public double getCurrencyPercentage() {
		if (mTotalPosition + mTotalFees == 0) {
			return 0.00;
		}
		else {
			double percentage = mTotalCurrency / (mTotalPosition + mTotalFees);
			return percentage * 100.00;
		}
	}
	
	public double getOtherPercentage() {
		if (mTotalPosition + mTotalFees == 0) {
			return 0.00;
		}
		else {
			double percentage = mTotalOther / (mTotalPosition + mTotalFees);
			return percentage * 100.00;
		}
	}
	
	public List<InstrumentFinancial> getAllAssets() {
		return mAllAssets;
	}
}
