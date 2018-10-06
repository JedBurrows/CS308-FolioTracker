package frontend.portfolio;

import frontend.stock.IStock;

import java.util.Observable;

public interface IPortfolioView {
    void clearTextboxes();

    IStock getStock();

    void refreshTotalValueLabel();

    void update(Observable o, Object arg);

    void displayError(String message);

    IPortfolio getPortfolio();

    String getTickerInput();

    String shareNumberInput();

}
