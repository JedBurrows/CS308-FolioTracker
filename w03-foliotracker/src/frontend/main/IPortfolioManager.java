package frontend.main;

import frontend.portfolio.IPortfolio;
import frontend.portfolio.Portfolio;

import java.util.Collection;
import java.util.Iterator;
import java.util.Observer;

public interface IPortfolioManager extends Iterable<IPortfolio> {

    /**
     * requires: id >= 0 <br>
     * effects: Returns the portfolio object at the index passed in as param <br>
     *
     * @param id
     * @return
     */
    IPortfolio get(int id);

    /**
     * Adds a new portfolio object to the list of portfolios and updates the view <br>
     *
     * modifies: this <br>
     * effects: Adds a new portfolio object <br>
     */

    boolean add(IPortfolio portfolio);


    int size();


    boolean isEmpty();


    boolean contains(Object o);

    /**
     * Returns an iterator over elements of type Portfolio.
     */
    @Override
    Iterator<IPortfolio> iterator();

    /**
     * Deletes a portfolio and runs updated method
     * <p>
     * requires: portfolio != null <br>
     * modifies: this <br>
     * effects: Removes the portfolio object <br>
     */
    boolean remove(Object o);

    void clear();

	void addObserver(Observer o);
}
