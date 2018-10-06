package frontend.main.listener;

import frontend.main.IPortfolioManager;
import frontend.main.IPortfolioManagerView;
import frontend.main.PortfolioFileChooser;
import frontend.portfolio.IPortfolio;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

public class OpenPortfolioActionListener implements ActionListener {
    private IPortfolioManager portfolioManager;
    private IPortfolioManagerView portfolioManagerView;

    public OpenPortfolioActionListener(IPortfolioManager portfolioManager, IPortfolioManagerView portfolioManagerView) {
        this.portfolioManager = portfolioManager;
        this.portfolioManagerView = portfolioManagerView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        IPortfolio portfolio = getPortfolioFromChooser();
        if (portfolio != null) {
            portfolioManager.add(portfolio);
        }
    }

    private IPortfolio getPortfolioFromChooser() {
        PortfolioFileChooser fc = new PortfolioFileChooser();
        IPortfolio portfolio = null;

        try {
            portfolio = fc.getOpenedPortfolio();
        } catch (FileNotFoundException e) {
        	portfolioManagerView.displayMessage("File not found!");
        } catch (IOException e) {
        	portfolioManagerView.displayMessage("File couldn't be read!");
        } catch (ClassNotFoundException e) {
        	portfolioManagerView.displayMessage("Wrong file type!");
        }

        return portfolio;
    }
}
