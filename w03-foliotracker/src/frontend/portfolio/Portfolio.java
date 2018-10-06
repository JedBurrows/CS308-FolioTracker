package frontend.portfolio;

import frontend.stock.IStock;
import utils.CoolObservable;
import utils.PortfolioSerializer;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.*;

public class Portfolio extends CoolObservable implements IPortfolio, Serializable {
    private final Set<IStock> stockSet;
    private String name;
    private File fileLocation;

    public Portfolio() {
        this("Default", new HashSet<>());
    }

    public Portfolio(String name) {
        this(name, new HashSet<>());
    }

    public Portfolio(String name, Set<IStock> stockSet) {
        this.stockSet = stockSet;
        this.name = name;
    }

    @Override
    public boolean addStock(IStock stock) {
        if (!stockSet.add(stock)) {
        	boolean stockUpdated = updateStock(stock);
        	assert (stockUpdated) : "The stock must exist in the set, but its quantity is not updated. The stock is " + stock; 	
            return stockUpdated;
        }

        setChangedAndNotifyObservers();
        return true;
    }

    private boolean updateStock(IStock stock) {
        for (IStock cStock : stockSet) {
            if (cStock.equals(stock)) {
                cStock.addShares(stock.getNumberOfShares());
                setChangedAndNotifyObservers();
                return true;
            }
        }
        
        return false;
    }

    @Override
    public boolean removeStock(IStock stock) {
        if (stockSet.remove(stock)) {
            setChangedAndNotifyObservers();
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, stockSet);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;

        if (!(obj instanceof Portfolio)) {
            return false;
        }

        Portfolio portfolio = (Portfolio) obj;
        return stockSet.equals(portfolio.stockSet);
    }


    @Override
    public IStock getStock(IStock stock) {
        for (IStock cStock : stockSet) {
            if (cStock.equals(stock)) {
                return cStock;
            }
        }

        return null;
    }

    @Override
    public List<IStock> getStocks() {
        return new ArrayList<>(stockSet);
    }

    @Override
    public double getTotalValue() {
        double value = 0;
        for (IStock stock : stockSet) {
        	
        	assert(checkNumberOfShares(stock) && checkSharePrice(stock)) : "The number of shares should only be positive or 0"
        			+ " and price of the stock should always be positive. The numbe of shares is " + stock.getNumberOfShares()
        			+ "and the share price is " + stock.getPricePerShare();
        	
            value += stock.getValueOfHolding();
        }

        return value;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void saveFile() throws IOException {
        PortfolioSerializer.saveToDisk(fileLocation, this);
    }

    @Override
    public boolean deleteFile() {
        return fileLocation.delete();
    }

    @Override
    public File getFileLocation() {
        return fileLocation;
    }

    @Override
    public void setFileLocation(File fileLocation) {
        this.fileLocation = fileLocation;
        this.name = fileLocation.getName();
    }

    @Override
    public boolean isValidName(String name) {
        return name.matches("[\\w\\s_]+");
    }

    @Override
    public String toString() {
        return "Portfolio " + this.name;
    }

	@Override
	public boolean contains(IStock stock) {
		return stockSet.contains(stock);
	}
	
	private boolean checkNumberOfShares(IStock stock) {
		return stock.getNumberOfShares() >= 0;
		
	}
	
	private boolean checkSharePrice(IStock stock) {
		return stock.getPricePerShare() > 0;
	}

}