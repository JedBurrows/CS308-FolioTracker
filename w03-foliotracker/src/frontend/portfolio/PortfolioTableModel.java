package frontend.portfolio;

import backend.StrathQuoteServer;
import frontend.stock.IStock;

import javax.swing.table.AbstractTableModel;
import java.text.DecimalFormat;
import java.util.*;

public class PortfolioTableModel extends AbstractTableModel implements IPortfolioTableModel {

    private Vector<String> columns;
    private Vector<Vector<String>> data;
    private IPortfolio portfolio;
    private DecimalFormat df;

    public PortfolioTableModel(IPortfolio portfolio) {

        this.portfolio = portfolio;
        df = new DecimalFormat("#,###.##");
        df.setDecimalSeparatorAlwaysShown(true);

        data = new Vector<>();
        columns = new Vector<>();

        String[] columnData = {"Ticker Symbol", "Stock Name", "Number of Shares",
                "Price per Share", "Value of Holding"};
        Collections.addAll(columns, columnData);

        refresh();
    }

    @Override
    public void fireTableDataChanged() {
        refresh();
        super.fireTableDataChanged();
    }

    private void refresh() {
        data.clear();

        List<IStock> stocks = portfolio.getStocks();

        for (IStock stock : stocks) {

            try {
                stock.updateStockPrice();
            } catch (StrathQuoteServer.WebsiteDataException e) {
            	e.printStackTrace();
            } catch (StrathQuoteServer.NoSuchTickerException e) {
                e.printStackTrace();
            }

            Vector<String> currentRow = new Vector<>();

            for (int j = 0; j < columns.size(); j++) {
                String property = getStockProperty(stock, j);

                assert(!property.equals("")) : "Property should never be an empty string: " + property;

                currentRow.add(property);
                assert(currentRow.contains(property)) : property + " has not been added to the row.";
            }

            data.add(currentRow);

        }

        assert compareStockSizeAndDataTable(stocks, data) : "The table should contain as many rows as there are stocks in the portfolio. Stock size is : "
                + stocks.size() + " Row number is : " + data.size();
    }

    private String getStockProperty(IStock stock, int i) {

        switch (i) {
            case 0:
                return stock.getTickerSymbol();
            case 1:
                return stock.getStockName();
            case 2:
                return String.valueOf(stock.getNumberOfShares());
            case 3:
                return df.format(stock.getPricePerShare());
            case 4:
                return df.format(stock.getValueOfHolding());
        }

        return "";
    }

    @Override
    public Vector<String> getRow(int stockId) {
        return data.get(stockId);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        return columns.size();
    }

    @Override
    public String getColumnName(int columnIndex) {
        return columns.get(columnIndex);
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return data.get(rowIndex).get(columnIndex);
    }
    
    private boolean compareStockSizeAndDataTable(List<IStock> stocks, Vector<Vector<String>> data) {
		return stocks.size() == data.size();
    }
}
