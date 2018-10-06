package frontend.stock;

import backend.StrathQuoteServer;

import java.util.Observer;

public interface IStock {

	/**
     * effects: Gets the ticker symbol of this <br>
     *
     * @return ticker symbol
     */
    String getTickerSymbol();

    /**
     * effects: Gets the stock name of this <br>
     *
     * @return stock name
     */
    String getStockName();

    /**
     * requires: stockName != null <br>
     * modifies: this <br>
     * effects: Sets the stock name of this <br>
     *
     * @param stockName
     */
    void setStockName(String stockName);

    /**
     * effects: Returns the number of shares of this <br>
     *
     * @return number of shares
     */
    int getNumberOfShares();

    /**
     * requires: numberOfShares > 0 <br>
     * effects: Replaces the number of shares of this with the value provided <br>
     * modifies: this <br>
     *
     * @param numberOfShares
     */
    void setNumberOfShares(int numberOfShares);

    /**
     * requires: numberOfShares >= 0 <br>
     * modifies: this <br>
     * effects: Add shares to this <br>
     *
     * @param numberOfShares of shares
     */
    void addShares(int numberOfShares);

    /**
     * requires: numberOfShares >= 0 <br>
     * modifies: this <br>
     * effects: Removes shares from this <br>
     *
     * @param numberOfShares
     */
    boolean removeShares(int numberOfShares);

    /**
     * effects: Provides the value of holding
     *
     * @return valueOfHolding field value
     */
    double getValueOfHolding();

    /**
     * effects: Gets the price of a single share <br>
     *
     * @return valueOfHolding field value
     */
    double getPricePerShare();

    /**
     * Getting price per stock share.
     * requires: pricePerShare >= 0 <br>
     * modifies: this <br>
     * @param pricePerShare
     */
    void setPricePerShare(double pricePerShare);
    /**
     * Observe changes in the object
     * effects: Sets the price per share <br>
     * modifies: this <br>
     * @param o
     */
    void addObserver(Observer o);

    /**
     * Updates stock price <br>
     * effects: Gets the current stock price from the server <br>
     * modifies: this <br>
     */
    void updateStockPrice() throws StrathQuoteServer.WebsiteDataException, StrathQuoteServer.NoSuchTickerException;
}
