package backend;

public interface IQuoteServer {

    /**
     * retrieve the latest market value of a stock
     * <p>
     * requires: tickerSymbol != null <br>
     * effects: returns a current value for tickerSymbol as a dollar amount,
     * with a period separating dollars and cents (eg, "120.50" for
     * one hundred and twenty dollars and fifty cents) <br>
     * unless tickerSymbol is not a valid NYSE or NASDAQ symbol, when
     * throws NoSuchTickerException <br>
     * or unless an error connecting to the website or some other
     * error occurs, when throws WebsiteDataException <br>
     * The amount returned may contain commas, for example, "2,243.87"
     * <br>
     */
    double getStockPrice(String tickerSymbol) throws StrathQuoteServer.NoSuchTickerException, StrathQuoteServer.WebsiteDataException;


    /**
     * Checks if a ticker symber is valid
     * <p>
     * requires: tickerSymbol != null <br>
     * effects: Returns true if the string passed in is a valid ticker symbol
     * <br>
     */
    boolean isValidStockTicker(String tickerSymbol);

}