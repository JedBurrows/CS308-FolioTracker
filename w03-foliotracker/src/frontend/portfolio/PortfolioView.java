package frontend.portfolio;

import frontend.portfolio.listener.AddStockActionListener;
import frontend.portfolio.listener.RefreshButtonListener;
import frontend.portfolio.listener.TableRowMouseListener;
import frontend.stock.Stock;
import frontend.stock.IStock;

import javax.swing.*;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import java.awt.*;
import java.text.DecimalFormat;
import java.util.Observable;
import java.util.Observer;

public class PortfolioView extends JPanel implements Observer, IPortfolioView {
    private AddStockActionListener addButtonListener;
    private TableRowMouseListener rowListener;
    private IPortfolio portfolio;
    private JTextField tickerSymbolTextField;
    private JTextField numberOfSharesTextField;
    private IPortfolioTableModel stockPortfolioTableModel;
    private JLabel totalValueLabel;
    private DecimalFormat df;
    private JPanel mainPanel;
    private JTable table;

    public PortfolioView(IPortfolio portfolioModel) {

        portfolio = portfolioModel;
        portfolio.addObserver(this);

        this.addButtonListener = new AddStockActionListener(portfolioModel, this);
        stockPortfolioTableModel = new PortfolioTableModel(this.portfolio);

        this.rowListener = new TableRowMouseListener(stockPortfolioTableModel, portfolioModel, this);

        df = new DecimalFormat("#,###.##");
        df.setDecimalSeparatorAlwaysShown(true);

        setup();
    }

    private void setup() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        // Main components
        mainPanel.add(topPanel(), BorderLayout.NORTH);
        mainPanel.add(centerPanel(), BorderLayout.CENTER);
        mainPanel.add(bottomPanel(), BorderLayout.SOUTH);

        super.add(mainPanel);
    }

    private JPanel topPanel() {
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));

        JLabel tickerSymbolLabel = new JLabel("Ticker Symbol:");
        tickerSymbolTextField = new JTextField(5);

        JLabel numberOfShares = new JLabel("Number of Shares:");
        numberOfSharesTextField = new JTextField(5);

        JButton addButton = new JButton("Add");
        addButton.addActionListener(addButtonListener);

        JButton refreshButton = new JButton("Refresh");
        refreshButton.addActionListener(new RefreshButtonListener(this, stockPortfolioTableModel));

        topPanel.add(tickerSymbolLabel);
        topPanel.add(tickerSymbolTextField);
        topPanel.add(numberOfShares);
        topPanel.add(numberOfSharesTextField);

        topPanel.add(addButton);
        topPanel.add(refreshButton);

        return topPanel;
    }

    private JPanel centerPanel() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new FlowLayout());


        table = new JTable(stockPortfolioTableModel);
        TableRowSorter<TableModel> sorter = new TableRowSorter<>(table.getModel());
        table.setRowSorter(sorter);

        JScrollPane scrollableTable = new JScrollPane(table);

        table.addMouseListener(rowListener);

        mainPanel.add(scrollableTable);
        return mainPanel;
    }


    @Override
    public void clearTextboxes() {
        tickerSymbolTextField.setText("");
        numberOfSharesTextField.setText("");
    }

    private JPanel bottomPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        totalValueLabel = new JLabel("Total value: 0");

        panel.add(totalValueLabel);
        return panel;
    }

    @Override
    public IStock getStock() {
        return new Stock(tickerSymbolTextField.getText().trim(), "Stock", Integer.valueOf(numberOfSharesTextField.getText()));
    }

    @Override
    public void refreshTotalValueLabel() {
        totalValueLabel.setText("Total Value: " + df.format(portfolio.getTotalValue()));
    }

    @Override
    public void update(Observable o, Object arg) {
        stockPortfolioTableModel.fireTableDataChanged();
        refreshTotalValueLabel();
    }

    @Override
    public void displayError(String message) {
        JOptionPane.showMessageDialog(mainPanel, message);
    }

    @Override
    public IPortfolio getPortfolio() {
        return portfolio;
    }

    @Override
    public String getTickerInput() {
        return tickerSymbolTextField.getText().trim();
    }

    @Override
    public String shareNumberInput() {
        return numberOfSharesTextField.getText().trim();
    }
}
