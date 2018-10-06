package frontend.main;

import frontend.portfolio.IPortfolio;
import java.util.*;

/**
 * PortfolioManager contains the collection that keeps track of Portfolios
 */
public class PortfolioManager extends Observable implements IPortfolioManager {

    private List<IPortfolio> portfolioList;

    public PortfolioManager() {
        portfolioList = new ArrayList<>();
    }

    @Override
    public IPortfolio get(int id) {
        return portfolioList.get(id);
    }

    @Override
    public boolean add(IPortfolio portfolio) {
        boolean success = portfolioList.add(portfolio);

        if (success) updated();

        return success;
    }

    @Override
    public boolean remove(Object portfolio) {
        boolean success = portfolioList.remove(portfolio);

        if (success) updated();

        return success;
    }

    private void updated() {
        setChanged();
        notifyObservers();
    }

    @Override
    public int size() {
        return portfolioList.size();
    }

    @Override
    public boolean isEmpty() {
        return portfolioList.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return portfolioList.contains(o);
    }


    @Override
    public Iterator<IPortfolio> iterator() {
        return portfolioList.iterator();
    }

    @Override
    public void clear() {
        portfolioList.clear();
    }

}
