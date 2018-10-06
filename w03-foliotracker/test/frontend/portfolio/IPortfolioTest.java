package tests;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.*;

import org.junit.Before;
import org.junit.Test;

import frontend.portfolio.IPortfolio;
import frontend.portfolio.Portfolio;
import frontend.stock.IStock;
import frontend.stock.Stock;
import utils.PortfolioSerializer;


public class IPortfolioTest {
	IPortfolio ptf;
	IPortfolio ptf1;
	IPortfolio ptf2;
	IPortfolio ptf3;
	Set<IStock> stockSet;
	Stock stock1;
	Stock stock2;
	
	@Before
	public void setUp() {
		ptf = new Portfolio();
		stockSet = new HashSet<>();
		stock1 = new Stock("GOOG", "stock1", 1, 1.0);
		stock2 = new Stock("AAPL", "stock2", 1, 1.0);
		stockSet.add(stock1);
		stockSet.add(stock2);
		ptf1 = new Portfolio("Test", stockSet);
		ptf2 = new Portfolio("Test", stockSet);
		ptf3 = new Portfolio("Test", stockSet);
	}
	
	// test default constructor
	@Test
    public void testDefaultConstructor() throws Exception {
		IPortfolio ptf = new Portfolio();
		assertEquals("Default", ptf.getName());
    }
	
	// test middle constructor
	@Test
    public void testNameConstructor() throws Exception {
		IPortfolio ptf = new Portfolio("Test");
		assertEquals("Test", ptf.getName());
    }
	
	// test full constructor
	@Test
    public void testFullConstructor() throws Exception {
		assertEquals(new ArrayList<>(stockSet), ptf1.getStocks());
    }
	
	// test reflexive equality
    @Test
    public void testEqualsReflexive () {
        assertTrue(ptf1.equals(ptf1));
    }
    
    // test symmetric equality
    @Test
    public void testEqualsSymetric() {
    	assertTrue(ptf1.equals(ptf2) && ptf2.equals(ptf1));
    }
 
    // test transitive equality
    @Test
    public void testEqualsTransitive() {
        assertTrue(ptf1.equals(ptf2) && ptf2.equals(ptf3) && ptf3.equals(ptf1));
    }
 
    // test consistency in equality
     @Test
    public void testEqualsConsistent() {
        assertTrue(ptf1.equals(ptf2) && ptf1.equals(ptf2));
    }
      
     // test equality to null
    @Test
    public void testEqualsNull () {
        assertFalse(ptf1.equals(null));
    }
    
    // compare with object of different class
    @Test
    public void testEqualsDifferentClasses () {
        assertFalse(ptf1.equals(stock1));
    }
    
    // test toString method
    @Test
    public void testToString() {
    	assertEquals("Portfolio Default", ptf.toString());
    }

	// add stock to the portfolio, retrieve it, and compare
    @Test
    public void testAddStock() throws Exception {
    	assertTrue(ptf.addStock(stock1));
    	assertTrue(ptf.contains(stock1));
    	assertEquals(stock1, ptf.getStock(stock1));
    }
    
	// add stock with the same ticker symbol, should only update the amount
    @Test
    public void testAddStockAgain() throws Exception {
    	assertTrue(ptf.addStock(stock1));
    	assertTrue(ptf.addStock(stock1));
    	assertEquals(2, ptf.getStock(stock1).getNumberOfShares());
    }

    // Removing existing stock should return true and the size of stock set should be zero
    @Test
    public void testRemoveExisingStock() throws Exception {
    	ptf.addStock(stock1);
    	assertTrue(ptf.removeStock(stock1));
    	assertEquals(0, ptf.getStocks().size());
    }
    
    // Removing non-existing stock should return false
    @Test
    public void testRemoveNonexistentStock() throws Exception {
    	assertFalse(ptf.removeStock(stock1));
    }
    
    // get the total value of holding
    @Test
    public void testTotalValue() {
    	assertEquals(2.00, ptf1.getTotalValue(), 0.00);
    }
    
    @Test
    public void testIsValidName() {
    	assertTrue(ptf1.isValidName("testing"));
    }
    
    // attempt to retrieve stock that is not there
    @Test
    public void testNonExistentStock() {
    	assertNull(ptf.getStock(stock1));
    }
    
    // save portfolio as file and then open it from the file and compare two objects
    @Test
    public void testSavingPortfolio() throws IOException, ClassNotFoundException {
        String FILE_PATH = System.getProperty("user.home") + "/Documents/PortfolioManager/" + ptf.getName();
        File file = new File(FILE_PATH);
        file.createNewFile();
        ptf.setFileLocation(file);
        ptf.saveFile();
        assertEquals(ptf, PortfolioSerializer.openFromFile(file));
    }
    
    // test deleting existing portfolio
    @Test
    public void testDeletingExisitngPortfolio() throws IOException, ClassNotFoundException {
        String FILE_PATH = System.getProperty("user.home") + "/Documents/PortfolioManager/" + ptf.getName();
        File file = new File(FILE_PATH);
        file.createNewFile();
        ptf.setFileLocation(file);
        ptf.saveFile();
        assertTrue(ptf.deleteFile());
    }
    
    // getting file location
    @Test
    public void testGetFileLocatio() throws IOException, ClassNotFoundException {
        String FILE_PATH = System.getProperty("user.home") + "/Documents/PortfolioManager/" + ptf.getName();
        File file = new File(FILE_PATH);
        file.createNewFile();
        ptf.setFileLocation(file);
        ptf.saveFile();
        assertEquals(file, ptf.getFileLocation());
    }
    
    
}