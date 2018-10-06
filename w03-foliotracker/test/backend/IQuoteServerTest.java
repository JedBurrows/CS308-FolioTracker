package backend;

import org.junit.Test;

import static org.junit.Assert.*;

public class IQuoteServerTest {

    @Test
    public void getStockPrice() throws Exception {
        IQuoteServer quoteServer = new StrathQuoteServer();

    }

    @Test
    public void isValidStockTicker() throws Exception {
        IQuoteServer quoteServer = new StrathQuoteServer();

        assertTrue(quoteServer.isValidStockTicker("GOOG"));
    }

    @Test
    public void isValidStockTicker2() throws Exception {
        IQuoteServer quoteServer = new StrathQuoteServer();

        assertFalse(quoteServer.isValidStockTicker("AAAAAAA"));
    }

}