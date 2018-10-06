package frontend.main;

import frontend.portfolio.IPortfolio;
import frontend.portfolio.Portfolio;
import org.junit.Test;

import static org.junit.Assert.*;

public class IPortfolioManagerTest {

    @Test
    public void get() throws Exception {
        PortfolioManager pm = new PortfolioManager();
        Portfolio p1 = new Portfolio();
        pm.add(p1);
        assertTrue(pm.get(0).equals(p1));
    }

    @Test
    public void add() throws Exception {
        PortfolioManager pm = new PortfolioManager();
        Portfolio p1 = new Portfolio();
        assertTrue(pm.add((p1)));
    }

    @Test
    public void remove() throws Exception {
        PortfolioManager pm = new PortfolioManager();
        Portfolio p1 = new Portfolio();
        pm.add(p1);
        assertTrue(pm.remove(p1));
    }

    @Test
    public void size() throws Exception {
        PortfolioManager pm = new PortfolioManager();
        Portfolio p1 = new Portfolio();
        pm.add(p1);
        assertTrue(pm.size() == 1);
    }

    @Test
    public void containsTest() throws Exception {
        PortfolioManager pm = new PortfolioManager();
        Portfolio p1 = new Portfolio();
        pm.add(p1);
        assertTrue(pm.contains(p1));
    }

    @Test
    public void isEmpty(){
        PortfolioManager pm = new PortfolioManager();
        Portfolio p1 = new Portfolio();
        pm.add(p1);
        assertFalse(pm.isEmpty());
    }

    @Test
    public void clear(){
        PortfolioManager pm = new PortfolioManager();
        Portfolio p1 = new Portfolio();
        pm.add(p1);
        pm.clear();
        assertTrue(pm.isEmpty());
    }

}