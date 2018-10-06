package frontend.portfolio.listener;

import backend.IQuoteServer;
import frontend.portfolio.IPortfolio;
import frontend.portfolio.IPortfolioView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import backend.StrathQuoteServer;

public class AddStockActionListener implements ActionListener {

    private IPortfolio portfolioModel;
    private IPortfolioView portfolioView;

    public AddStockActionListener(IPortfolio portfolioModel, IPortfolioView portfolioView) {
        this.portfolioModel = portfolioModel;
        this.portfolioView = portfolioView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (validateInput()) {
        	portfolioModel.addStock(portfolioView.getStock());
        	portfolioView.clearTextboxes();
        }
    }

    /**
     * Validates user input and provides informative messages if input is incorrect
     *
     * @return true if textfield is not empty and contain valid values
     */
    private boolean validateInput() {
        String tickerSymbol = portfolioView.getTickerInput();
        String shareNumber = portfolioView.shareNumberInput();
        Pattern pattern = Pattern.compile("[a-zA-Z]+");
        IQuoteServer server = new StrathQuoteServer();


        if (tickerSymbol.isEmpty()) {
            portfolioView.displayError("Please enter a stock ticker symbol!");
            return false;
        }

        if (shareNumber.isEmpty()) {
            portfolioView.displayError("Please enter stock number!");
            return false;
        }

        Matcher matcher = pattern.matcher(tickerSymbol);
        if (!matcher.matches()) {
            portfolioView.displayError("Invalid stock ticker symbol!");
            return false;
        }

        try {
            Integer.parseInt(shareNumber);
        } catch (NumberFormatException e) {
            portfolioView.displayError("Please enter a valid number!");
            return false;
        }

        // Check if ticker symbol is valid
        final boolean[] validStock = {false};
        Thread validCheckThread = new Thread(new Runnable() {
            @Override
            public void run() {
                validStock[0] = server.isValidStockTicker(tickerSymbol);
            }

        });
        validCheckThread.start();

        try {

            validCheckThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (!validStock[0]) {
            portfolioView.displayError("Stock ticker symbol doesn't exist!");
            return false;
        }


        return true;
    }
}
