package frontend.portfolio.listener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Observer;
import java.util.Vector;
import javax.swing.*;

import frontend.portfolio.IPortfolio;
import frontend.portfolio.IPortfolioTableModel;
import frontend.stock.Stock;
import frontend.stock.IStock;
import frontend.stock.IStockView;
import frontend.stock.StockView;

public class TableRowMouseListener implements MouseListener {

    private IPortfolioTableModel tableModel;
    private IPortfolio portfolio;
    private Observer portfolioView;

    public TableRowMouseListener(IPortfolioTableModel tableModel, IPortfolio portfolio, Observer portfolioView) {
        this.tableModel = tableModel;
        this.portfolio = portfolio;
        this.portfolioView = portfolioView;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    	
        if (e.getClickCount() == 2) {

            JTable table = (JTable) e.getSource();

            int selectedRow = table.getSelectedRow();
            selectedRow = table.convertRowIndexToModel(selectedRow);

            Vector<String> row = tableModel.getRow(selectedRow);

            // get ticker
            String ticker = row.get(0);

            // retrieve stock object
            IStock tempStock = new Stock(ticker, "Stock", 0);

            final IStock stock = portfolio.getStock(tempStock);
            
            assert stock != null : "Stock is  not supposed to be null, but it is null";

            stock.addObserver(portfolioView);

            // create the view
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    IStockView IStockView = new StockView(portfolio, stock);
                    IStockView.createAndShowGUI();
                }
            });
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

}
