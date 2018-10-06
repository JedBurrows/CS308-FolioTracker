package frontend.main.listener;

import frontend.main.IPortfolioManager;
import frontend.main.IPortfolioManagerView;
import frontend.portfolio.IPortfolio;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeletePortfolioActionListener implements ActionListener {
    private IPortfolioManager portfolioManager;
    private IPortfolioManagerView portfolioView;

    /**
     * Deletes an action listener to the current portfolio <br>
     * <p>
     * requires: portfolioManager != null && portfolioManagerView != null <br>
     * effects: deletes a portfolioActionListener object and sets portfolioManager object and portfolioManagerView object <br>
     */
    public DeletePortfolioActionListener(IPortfolioManager portfolioManager, IPortfolioManagerView portfolioView) {
        this.portfolioManager = portfolioManager;
        this.portfolioView = portfolioView;
    }

    /**
     * Invoked when an action occurs. <br>
     * requires: actionEvent performed <br>
     * effects: Performs the actionEvent to remove on the portfolioManager object <br>
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (portfolioView.getConformation("Are you sure you want to delete?")) {
            IPortfolio portfolio = portfolioView.getSelectedPortfolio();
            boolean result = portfolio.deleteFile();

            String message = "Portfolio deleted successfully";
            if (!result) {
                message = "Error occurred while deleting portfolio";
            }
            portfolioView.displayMessage(message);

            portfolioManager.remove(portfolio);
        }
    }

}
