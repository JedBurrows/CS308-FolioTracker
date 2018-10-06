package frontend.stock;

import backend.IQuoteServer;
import backend.StrathQuoteServer;
import utils.CoolObservable;

import java.io.Serializable;

public class Stock extends CoolObservable implements IStock, Serializable {

    private String tickerSymbol;
    private String stockName;
    private int numberOfShares;
    private double pricePerShare;
    private double valueOfHolding;
    transient private static IQuoteServer quoteServer = new StrathQuoteServer();

    /**
     * Requires:
     * <ul>
     * </ul>
     * <li>tickerSymbol != null</li>
     * <li>stockName != null<li>
     * <li>numberOfShares >= 0</li>
     * <li>pricePerShare >= 0</li>
     * </ul>
     *
     * @param tickerSymbol
     * @param stockName
     * @param numberOfShares
     * @param pricePerShare
     */
    public Stock(String tickerSymbol, String stockName, int numberOfShares, double pricePerShare) {
        this.tickerSymbol = tickerSymbol.toUpperCase();
        this.stockName = stockName;
        this.numberOfShares = numberOfShares;
        this.pricePerShare = pricePerShare;
        updateValueOfHolding();
    }

    /**
     * Requires:
     * <ul>
     * </ul>
     * <li>tickerSymbol != null</li>
     * <li>stockName != null<li>
     * <li>numberOfShares >= 0</li>
     * </ul>
     *
     * @param tickerSymbol
     * @param stockName
     * @param numberOfShares
     */
    public Stock(String tickerSymbol, String stockName, int numberOfShares) {
        this(tickerSymbol, stockName, numberOfShares, 1.0);
        updateValueOfHolding();
    }

    /**
     * effects: Calculates the value of holding by multiplying number of shares by their price <br>
     * modifies: this <br>
     */
    private void updateValueOfHolding() {
        valueOfHolding = numberOfShares * pricePerShare;
    }

    @Override
    public String getTickerSymbol() {
        return tickerSymbol;
    }

    @Override
    public String getStockName() {
        return stockName;
    }

    @Override
    public void setStockName(String stockName) {
        this.stockName = stockName;
        setChangedAndNotifyObservers();
    }

    @Override
    public int getNumberOfShares() {
        return numberOfShares;
    }

    public void setNumberOfShares(int numberOfShares) {
        if (numberOfShares >= 0) {
            this.numberOfShares = numberOfShares;
        }
    }

    @Override
    public void addShares(int numberOfShares) {
        this.numberOfShares += numberOfShares;
        updateValueOfHolding();
        setChangedAndNotifyObservers();
    }

    @Override
    public boolean removeShares(int numberOfShares) {
        if (this.numberOfShares - numberOfShares < 0) {
            return false;
        }

        this.numberOfShares -= numberOfShares;
        updateValueOfHolding();
        setChangedAndNotifyObservers();
        return true;
    }

    @Override
    public double getPricePerShare() {
        return pricePerShare;
    }

    @Override
    public void setPricePerShare(double pricePerShare) {
        this.pricePerShare = pricePerShare;
        updateValueOfHolding();
        setChangedAndNotifyObservers();
    }

    @Override
    public double getValueOfHolding() {
        return valueOfHolding;
    }

    @Override
    public void updateStockPrice() throws StrathQuoteServer.WebsiteDataException, StrathQuoteServer.NoSuchTickerException {
        this.pricePerShare = quoteServer.getStockPrice(tickerSymbol);

        updateValueOfHolding();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((tickerSymbol == null) ? 0 : tickerSymbol.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Stock other = (Stock) obj;
        if (tickerSymbol == null) {
            if (other.tickerSymbol != null)
                return false;
        } else if (!tickerSymbol.equals(other.tickerSymbol))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Stock [tickerSymbol=" + tickerSymbol + ", stockName=" + stockName + ", numberOfShares=" + numberOfShares
                + ", pricePerShare=" + pricePerShare + ", valueOfHolding=" + valueOfHolding + "]";
    }

}
