package frontend.portfolio.listener;

import frontend.portfolio.IPortfolioTableModel;
import frontend.portfolio.IPortfolioView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RefreshButtonListener implements ActionListener {

    private final IPortfolioTableModel stockPortfolioTableModel;
    private final IPortfolioView portfolioView;

    public RefreshButtonListener(IPortfolioView IPortfolioView, IPortfolioTableModel stockPortfolioTableModel) {
        this.stockPortfolioTableModel = stockPortfolioTableModel;
        this.portfolioView = IPortfolioView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    	portfolioView.refreshTotalValueLabel();
        stockPortfolioTableModel.fireTableDataChanged();
    }
}
