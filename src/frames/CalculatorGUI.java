package frames;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;

import financial.FixedIncome;
import financial.InstrumentFinancial;
import financial.Portfolio;
import financial.PrivateFund;
import financial.PublicFund;
import utilities.GraphicConstants;
import utilities.GraphicSettings;
import utilities.StringConstants;
import utilities.TextFieldFocusListener;

public class CalculatorGUI extends JFrame{
	
	private Portfolio portfolio;
	// northPanel
	private JTextField mTotalFundsTextField;
	private JButton mTotalFundsButton;
	// centerPanel
	private JLabel mTotalFundsLabel;
	private JLabel mTotalPositionLabel;
	private JLabel mTotalFeesLabel;
	private JLabel mTotalCashLabel;
	private JLabel mTotalEARLabel;
	private DefaultTableModel mAssetModel;
	private JTable mAssetTable;
	private JScrollPane mScrollPane;
	// southPanel
	private JLabel mAlertLabel;
	private JTextField mCodeTextField;
	private JTextField mNameTextField;
	private JTextField mEARTextField;
	private JTextField mTotalTextField;
	private JButton mAddPrivateFundButton;
	private JButton mAddPublicFundButton;
	private JButton mAddFixedIncomeButton;
	private JButton mRemoveButton;
	private JButton mRemoveAllButton;

	public CalculatorGUI() {
		super("华泰证券投资组合收益计算器");
		this.initializeVariables();
		this.createGUI();
		this.addListeners();
	}
	
	private void initializeVariables() {
		this.portfolio = new Portfolio(0.00);
		// northPanel
		this.mTotalFundsTextField = new JTextField();
		this.mTotalFundsButton = new JButton("设置投资资金规模(万元)");
		// centerPanel
		this.mTotalFundsLabel = new JLabel();
		this.mTotalPositionLabel = new JLabel();
		this.mTotalFeesLabel = new JLabel();
		this.mTotalCashLabel = new JLabel();
		this.mTotalEARLabel = new JLabel();
		this.mAssetModel = new DefaultTableModel();
		this.mAssetModel.addColumn("产品代码");
		this.mAssetModel.addColumn("产品名称");
		this.mAssetModel.addColumn("产品类别");
		this.mAssetModel.addColumn("产品年化收益率(%)");
		this.mAssetModel.addColumn("产品实际投入(万元，含管理费)");
		this.mAssetTable = new JTable(mAssetModel);
		this.mScrollPane = new JScrollPane(mAssetTable, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		this.updateDisplay();
		// southPanel
		this.mAlertLabel = new JLabel("欢迎使用");
		this.mCodeTextField = new JTextField();
		this.mNameTextField = new JTextField();
		this.mEARTextField = new JTextField();
		this.mTotalTextField = new JTextField();
		this.mAddPrivateFundButton = new JButton("添加私募类产品");
		this.mAddPublicFundButton = new JButton("添加公募类产品");
		this.mAddFixedIncomeButton = new JButton("添加固收类产品");
		this.mRemoveButton = new JButton("移除此产品");
		this.mRemoveButton.setEnabled(false);
		this.mRemoveAllButton = new JButton("一键清空");
	}
	
	private void updateDisplay() {
		this.mTotalFundsLabel.setText(
				StringConstants.TotalFunds +
				Math.round(portfolio.getTotalFunds() * 100.00) / 100.00 +
				StringConstants.WanYuan);
		this.mTotalPositionLabel.setText(
				StringConstants.TotalPosition +
				Math.round(portfolio.getTotalPosition() * 100.00) / 100.00 +
				StringConstants.WanYuan);
		this.mTotalFeesLabel.setText(
				StringConstants.TotalFees +
				Math.round(portfolio.getTotalFees() * 100.00) / 100.00 +
				StringConstants.WanYuan);
		this.mTotalCashLabel.setText(
				StringConstants.TotalCash +
				Math.round(portfolio.getTotalCash() * 100.00) / 100.00 +
				StringConstants.WanYuan);
		this.mTotalEARLabel.setText(
				StringConstants.TotalEAR +
				Math.round(portfolio.getTotalEAR() * 100.00) / 100.00 +
				StringConstants.BaiFenShu);
	}
	
	private void createGUI() {
		this.setSize(1000, 600);
		this.setLocation(0, 180);
		this.add(this.createNorthPanel(), BorderLayout.NORTH);
		this.add(this.createCenterPanel(), BorderLayout.CENTER);
		this.add(this.createSouthPanel(), BorderLayout.SOUTH);
		this.setVisible(true);
	}
	
	private JPanel createNorthPanel() {
		JPanel northPanel = new JPanel(new BorderLayout());
		JLabel companyLabel = new JLabel("华泰证券无锡分公司");
		JLabel calculatorLabel = new JLabel("投资组合预期年化收益率计算器");
		JPanel northSouthPanel = new JPanel();
		GraphicSettings.setFont(GraphicConstants.fontLarge, companyLabel);
		GraphicSettings.setFont(GraphicConstants.fontMedium, calculatorLabel);
		GraphicSettings.setSize(240, 35, mTotalFundsTextField);
		GraphicSettings.setTextAlignment(companyLabel, calculatorLabel);
		northSouthPanel.add(mTotalFundsTextField);
		northSouthPanel.add(mTotalFundsButton);
		northPanel.add(companyLabel, BorderLayout.NORTH);
		northPanel.add(calculatorLabel, BorderLayout.CENTER);
		northPanel.add(northSouthPanel, BorderLayout.SOUTH);
		return northPanel;
	}
	
	// TODO asset list
	private JPanel createCenterPanel() {
		JPanel centerPanel = new JPanel(new BorderLayout());
		JPanel centerNorthPanel = new JPanel();
		centerNorthPanel.add(mTotalFundsLabel);
		centerNorthPanel.add(mTotalPositionLabel);
		centerNorthPanel.add(mTotalFeesLabel);
		centerNorthPanel.add(mTotalCashLabel);
		centerNorthPanel.add(mTotalEARLabel);
		centerPanel.add(centerNorthPanel, BorderLayout.NORTH);
		centerPanel.add(mScrollPane, BorderLayout.CENTER);
		return centerPanel;
	}
	
	private JPanel createSouthPanel() {
		JPanel southPanel = new JPanel(new BorderLayout());
		JPanel southCenterPanel = new JPanel();
		JPanel southSouthPanel = new JPanel();
		GraphicSettings.setSize(240, 35, mCodeTextField, mNameTextField, mEARTextField, mTotalTextField);
		GraphicSettings.setTextAlignment(mAlertLabel);
		southCenterPanel.add(mCodeTextField);
		southCenterPanel.add(mNameTextField);
		southCenterPanel.add(mEARTextField);
		southCenterPanel.add(mTotalTextField);
		southSouthPanel.add(mAddPrivateFundButton);
		southSouthPanel.add(mAddPublicFundButton);
		southSouthPanel.add(mAddFixedIncomeButton);
		southSouthPanel.add(mRemoveButton);
		southSouthPanel.add(mRemoveAllButton);
		southPanel.add(mAlertLabel, BorderLayout.NORTH);
		southPanel.add(southCenterPanel, BorderLayout.CENTER);
		southPanel.add(southSouthPanel, BorderLayout.SOUTH);
		return southPanel;
	}
	
	private void addListeners() {
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		this.mTotalFundsTextField.addFocusListener(
			new TextFieldFocusListener("请输入投资资金规模", this.mTotalFundsTextField));
		
		this.mCodeTextField.addFocusListener(
				new TextFieldFocusListener("请输入产品代码", this.mCodeTextField));
		
		this.mNameTextField.addFocusListener(
				new TextFieldFocusListener("请输入产品名称", this.mNameTextField));
		
		this.mEARTextField.addFocusListener(
				new TextFieldFocusListener("请输入产品年化收益率(百分数)", this.mEARTextField));
		
		this.mTotalTextField.addFocusListener(
				new TextFieldFocusListener("请输入产品投入金额(含管理费用)", this.mTotalTextField));
		
		this.mTotalFundsButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					double inTotalFunds = Double.parseDouble(mTotalFundsTextField.getText());
					portfolio.setTotalFunds(inTotalFunds);
					updateDisplay();
				} catch (Exception exc) {
					
				}
			}
			
		});
		
		this.mAddPrivateFundButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String inCode = mCodeTextField.getText();
				String inName = mNameTextField.getText();
				try {
					double inEAR = Double.parseDouble(mEARTextField.getText()) / 100.00;
					try {
						double inTotal = Double.parseDouble(mTotalTextField.getText());
						InstrumentFinancial newAsset = new PrivateFund(inCode, inName, inEAR, inTotal);
						mAssetModel.addRow(new String[] {
								inCode, inName, "私募基金类", Double.toString(Math.round(inEAR * 10000.00) / 100.00) + StringConstants.BaiFenShu, Double.toString(Math.round(inTotal * 100.00) / 100.00) + "万元"
						});
						portfolio.add(newAsset);
						updateDisplay();
					} catch (Exception exc) {
						
					}
				} catch (Exception exc) {
					
				}
			}
			
		});
		
		this.mAddPublicFundButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String inCode = mCodeTextField.getText();
				String inName = mNameTextField.getText();
				try {
					double inEAR = Double.parseDouble(mEARTextField.getText()) / 100.00;
					try {
						double inTotal = Double.parseDouble(mTotalTextField.getText());
						InstrumentFinancial newAsset = new PublicFund(inCode, inName, inEAR, inTotal);
						mAssetModel.addRow(new String[] {
								inCode, inName, "公募基金类", Double.toString(Math.round(inEAR * 10000.00) / 100.00) + StringConstants.BaiFenShu, Double.toString(Math.round(inTotal * 100.00) / 100.00) + "万元"
						});
						portfolio.add(newAsset);
						updateDisplay();
					} catch (Exception exc) {
						
					}
				} catch (Exception exc) {
					
				}
			}
			
		});
		
		this.mAddFixedIncomeButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String inCode = mCodeTextField.getText();
				String inName = mNameTextField.getText();
				try {
					double inEAR = Double.parseDouble(mEARTextField.getText()) / 100.00;
					try {
						double inTotal = Double.parseDouble(mTotalTextField.getText());
						InstrumentFinancial newAsset = new FixedIncome(inCode, inName, inEAR, inTotal);
						mAssetModel.addRow(new String[] {
								inCode, inName, "固定收益类", Double.toString(Math.round(inEAR * 10000.00) / 100.00) + StringConstants.BaiFenShu, Double.toString(Math.round(inTotal * 100.00) / 100.00) + "万元"
						});
						portfolio.add(newAsset);
						updateDisplay();
					} catch (Exception exc) {
						
					}
				} catch (Exception exc) {
					
				}
			}
			
		});
		
		this.mRemoveAllButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				while (mAssetModel.getRowCount() > 0) {
					mAssetModel.removeRow(0);
				}
				portfolio.removeAll();
				updateDisplay();
			}
			
		});
	}

}
