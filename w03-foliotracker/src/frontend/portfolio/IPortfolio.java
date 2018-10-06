package frontend.portfolio;

import frontend.stock.IStock;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Observer;

public interface IPortfolio {
    /**
     * Adds stock to the portfolio <br>
     * If the stock has already exists in the set, call updatePortfolios() <br>
     *
     * requires: stock != null <br>
     * modifies: this <br>
     * effects: if stock is not in the set, stock is added to the set, view is updated, true is returned <br>
     * 			if stock is already in the set and number of shares is updated successfully by invoking updateStockPrice(), true is returned <br>
     * 			if number of shares is not updated successfully, false is returned <br>
     * @param stock
     * @return true if stock has been added
     */
    boolean addStock(IStock stock);

    /*
    /**
     * If stock is present in the portfolio, update the number of shares <br>
     * requires: stock != null <br>
     * modifies: this <br>
     * effects: if stock exists in the set, add shares to the existing number of shares and update the view <br>
     * 			if stock doesn't exist in the set return false <br>
     * @param stock
     * @return true if stock is found in the set
     */
    /*
    boolean updateStock(IStock stock);
    */

    /**
     * Removes stock from the portfolio <br>
     * requires: stock != null <br>
     * modifies: this <br>
     * effects: if stock is present in the set it is removed from the set, true is returned <br>
     *  		if stock is not present in the set, false is returned <br>
     *
     * @param stock
     * @return true if stock is removed from the set
     */
    boolean removeStock(IStock stock);


    /**
     * Returns specific stock from the portfolio <br>
     * requires: stock != null <br>
     * effects: if stock set contains stock, the stock object is returned <br>
     *  		if stock is not present in the set null is returned <br>
     * @param stock
     * @return stock object
     */
    IStock getStock(IStock stock);

    /**
     * Returns the list of all the stocks in portfolio <br>
     * effects: the set of stocks in the portfolio is converted to the list and returned <br>
     * @return List of stock instances
     */
    List<IStock> getStocks();

    /**
     * Calculates the total value of stocks <br>
     * effects: the value of all the stocks in portfolio is added up and returned <br>
     * @return value of all stocks in portfolio
     */
    double getTotalValue();

    /**
     * Getter for name field <br>
     * effects: sets or updtes the hidden field to the boolean value provided <br>
     * @return name of the portfolio
     */
    String getName();

    void addObserver(Observer o);

    void saveFile() throws IOException;

    /**
     * Save file to disk
     * @return
     */
    boolean deleteFile();

    File getFileLocation();

    void setFileLocation(File file);

    boolean isValidName(String name);

	boolean contains(IStock stock);
}
