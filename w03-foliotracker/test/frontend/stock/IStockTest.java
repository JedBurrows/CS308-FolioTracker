package frontend.stock;

import backend.StrathQuoteServer;
import frontend.portfolio.Portfolio;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class IStockTest {
    private IStock stock1;
    private IStock stock2;
    private IStock stock3;
    private IStock stock4;

    @Before
    public void setup() {
        stock1 = new Stock("GOOGL", "Stock", 50, 20.0);
        stock2 = new Stock("GOOGL", "Stock", 50, 20.0);
        stock3 = new Stock("GOOGL", "Stock", 50, 20.0);
        stock4 = new Stock("EE","Stock",20);
    }

    @Test
    public void getTickerSymbolTestEquals() throws Exception {
        assertTrue("GOOGL".equals(stock1.getTickerSymbol()));
    }

    @Test
    public void testEqualReflexive() throws Exception {
        assertTrue(stock1.equals(stock1));
    }

    @Test
    public void testEqualSymmetric() throws Exception {
        assertTrue(stock1.equals(stock2) && stock2.equals(stock1));
    }

    @Test
    public void testEqualTransitive() throws Exception {
        assertTrue(stock1.equals(stock2) && stock2.equals(stock3) && stock3.equals(stock1));
    }

    @Test
    public void testEqualsNull() throws Exception {
        assertFalse(stock1.equals(null));
    }

    @Test
    public void testEqualsConsistent() throws Exception {
        IStock stock4 = new Stock("GOOGL", "Stock", 50, 20.0);
        assertTrue(stock1.equals(stock4) && stock4.equals(stock1));
    }

    @Test
    public void testEqualsNoStock() throws Exception {
        Object o = new Object();
        assertFalse(stock1.equals(o));
    }

    @Test
    public void testUnequal() throws Exception {
        IStock unequalStock = new Stock("AAPL", "Stock1", 30, 12.3);
        assertFalse(stock1.equals(unequalStock));
    }


    @Test
    public void getStockNameTest() throws Exception {
        assertTrue("Stock".equals(stock1.getStockName()));
    }

    @Test
    public void setStockName() throws Exception { Portfolio p1 = new Portfolio();
    Stock s1 = new Stock("GOOGL", "Stock", 45, 20.0);
        IStock testStock = new Stock("GOOGL", "Stock", 30, 31);
        testStock.setStockName("Test");
        assertTrue(testStock.getStockName().equals("Test"));
    }

    @Test
    public void getNumberOfShares() throws Exception {
        assertTrue(stock1.getNumberOfShares() == 50);
    }

    @Test
    public void setNumberOfShares() throws Exception {
        stock1.setNumberOfShares(5);
        assertEquals(5, stock1.getNumberOfShares());
    }

    @Test
    public void setNumberofSharesNegative() throws Exception{
        stock1.setNumberOfShares(-100);
        assertFalse(stock1.getNumberOfShares() == -100);
    }

    @Test
    public void addShares() throws Exception {
        int stocks = stock1.getNumberOfShares();
        stock1.addShares(1);
        assertEquals(stocks+1, stock1.getNumberOfShares());
    }

    @Test
    public void removeShares() throws Exception {
        stock1.setNumberOfShares(10);
        stock1.removeShares(1);
        assertEquals(9, stock1.getNumberOfShares());
    }

    @Test
    public void removeShares2() throws Exception {
        stock1.setNumberOfShares(0);
        assertFalse(stock1.removeShares(10));
    }

    @Test
    public void getPricePerShare() throws Exception {
        assertTrue(stock1.getPricePerShare() == 20);
    }

    @Test
    public void setPricePerShare() throws Exception {
        stock1.setPricePerShare(1.0);
        assertEquals(1.0, stock1.getPricePerShare(), 0.1);
    }

    @Test
    public void getValueOfHolding() throws Exception {
        assertTrue(stock1.getValueOfHolding() == 1000);
    }

    @Test
    public void equals() throws Exception {
        assertTrue(stock1.equals(stock2));
    }

    @Test
    public void toStringTest() throws Exception{
        assertTrue(stock1.toString().equals("Stock [tickerSymbol=GOOGL, stockName=Stock, numberOfShares=50, pricePerShare=20.0, valueOfHolding=1000.0]"));
    }

    @Test
    public void hashCodeTest() throws Exception{
        assertTrue(stock4.hashCode() == 2239);
    }

}