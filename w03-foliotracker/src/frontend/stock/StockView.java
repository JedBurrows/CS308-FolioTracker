package frontend.stock;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.text.DecimalFormat;
import java.util.Observable;
import java.util.Observer;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.*;

import backend.StrathQuoteServer;
import frontend.stock.listener.*;
import frontend.portfolio.IPortfolio;

public class StockView implements Observer, IFocusView, IStockView {

    private static final String TITLE = "Edit Stock";
    private static int CURRENT_PRICE_UPDATE_SECONDS = 10;
    private Timer timer;
    private IStock stock;
    private IPortfolio portfolio;
    private JFrame mainFrame;
    private JTextField initialValueTextField;
    private JLabel ownedSharesLabel;
    private JLabel currentValue;
    private JTextField stockNameTextField;


    public StockView(IPortfolio portfolio, IStock stock) {
        this.stock = stock;
        this.portfolio = portfolio;
        this.timer = new Timer();
        stock.addObserver(this);
    }

    @Override
    public void createAndShowGUI() {
        mainFrame = new JFrame(TITLE);
        mainFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        mainFrame.addWindowFocusListener(new ViewWindowFocusListener(this));

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        mainPanel.add(createCentralPanel(), BorderLayout.CENTER);
        mainPanel.add(createBottomPanel(), BorderLayout.SOUTH);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        mainFrame.add(mainPanel);
        mainFrame.setMinimumSize(new Dimension(300, 200));
        mainFrame.setResizable(false);
        mainFrame.setVisible(true);
        mainFrame.setLocationRelativeTo(null);
    }

    /**
     * Create a pop-up window when stock record is clicked <br>
     *
     * @return central view panel
     */
    private JPanel createCentralPanel() {
        // create components
        JLabel tickerSymbolLabel = new JLabel(stock.getTickerSymbol());
        stockNameTextField = new JTextField(stock.getStockName());
        //stockNameTextField.addKeyListener(new StockNameKeyListener(this, stock));

        DecimalFormat df = new DecimalFormat("#,###.##");

        currentValue = new JLabel("Current Value: " + df.format(stock.getValueOfHolding()));

        JLabel numberOfShares = new JLabel("Shares owned: ");
        ownedSharesLabel = new JLabel(String.valueOf(stock.getNumberOfShares()));

        JLabel initialValue = new JLabel("Number to sell/buy");
        initialValueTextField = new JTextField(10);
        initialValueTextField.setText("0");

        JLabel totalGain = new JLabel("Total Gain:");

        // create central panel
        JPanel centralPanel = new JPanel();
        centralPanel.setLayout(new GridLayout(5, 1));

        JPanel row1 = new JPanel();
        row1.setLayout(new GridLayout(0, 2));
        row1.add(tickerSymbolLabel);
        row1.add(stockNameTextField);

        JPanel row2 = new JPanel();
        row2.setLayout(new GridLayout(1, 10));
        row2.add(currentValue);

        JPanel row3 = new JPanel();
        row3.setLayout(new GridLayout(0, 2));
        row3.add(numberOfShares);
        row3.add(ownedSharesLabel);

        JPanel row4 = new JPanel();
        row4.setLayout(new GridLayout(0, 2));
        row4.add(initialValue);
        row4.add(initialValueTextField);

        JPanel row5 = new JPanel();
        row5.setLayout(new GridLayout(0, 2));
        row5.add(totalGain);

        centralPanel.add(row1);
        centralPanel.add(row2);
        centralPanel.add(row3);
        centralPanel.add(row4);
        centralPanel.add(row5);

        return centralPanel;
    }

    /**
     * Create a pop-up window when stock record is clicked <br>
     *
     * @return bottom view panel
     */
    private JPanel createBottomPanel() {
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout());

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));

        // buy button
        Button buyButton = new Button("Buy");
        //String text = mainFrame
        BuyStockActionListener buyButtonListener = new BuyStockActionListener(this, stock);
        buyButton.addActionListener(buyButtonListener);

        // cancel button
        Button cancelButton = new Button("Sell");
        SellStockActionListener sellButtonListener = new SellStockActionListener(this, stock);
        cancelButton.addActionListener(sellButtonListener);

        // delete button
        Button deleteButton = new Button("Delete");
        DeleteStockActionListener deleteButtonListener = new DeleteStockActionListener(this, stock, portfolio);
        deleteButton.addActionListener(deleteButtonListener);
        
        // save button
        Button saveButton = new Button("Save");
        SaveStockActionListener saveButtonListener = new SaveStockActionListener(this, stock);
        saveButton.addActionListener(saveButtonListener);

        buttonPanel.add(buyButton);
        buttonPanel.add(cancelButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(saveButton);

        bottomPanel.add(buttonPanel);

        return bottomPanel;
    }

    @Override
    public void closeView() {
        timer.cancel();
        mainFrame.dispose();
    }

    @Override
    public void clearText() {
        initialValueTextField.setText("");
    }

    @Override
    public String getText(){
        return initialValueTextField.getText();
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o == stock) {
            ownedSharesLabel.setText(String.valueOf(stock.getNumberOfShares()));
            currentValue.setText("Current price: " + String.valueOf(stock.getPricePerShare()));
        }
    }

    /**
     * Updates the current stock price
     */
    private void updateCurrentSharePrice() {
        try {
            stock.updateStockPrice();

            // Cancel timer is view is not active
            if (!mainFrame.isActive()) {
                timer.cancel();
            }
        } catch (StrathQuoteServer.WebsiteDataException | StrathQuoteServer.NoSuchTickerException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getAmountBuySell() {
        String text = initialValueTextField.getText();
        return Integer.parseInt(text);
    }

    @Override
    public void onFocus() {
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                updateCurrentSharePrice();
            }
        }, 0, CURRENT_PRICE_UPDATE_SECONDS * 1000);
    }

    @Override
    public void onUnfocus() {
        timer.cancel();
    }

    @Override
    public void popupError(String text){
        JOptionPane.showMessageDialog(mainFrame, text);
    }

    @Override
    public String getStockName() {
        return stockNameTextField.getText();
    }

	@Override
	public boolean confirmationDialogue(String text) {
		int dialogResult = JOptionPane.showConfirmDialog(null, text);
        return dialogResult == JOptionPane.YES_OPTION;
	}
}
