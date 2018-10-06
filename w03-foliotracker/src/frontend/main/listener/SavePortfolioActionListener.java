package frontend.main.listener;

import frontend.main.IPortfolioManagerView;
import frontend.portfolio.IPortfolio;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class SavePortfolioActionListener implements ActionListener {

    private IPortfolioManagerView portfolioManagerView;

    public SavePortfolioActionListener(IPortfolioManagerView portfolioManagerView) {
        this.portfolioManagerView = portfolioManagerView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            IPortfolio portfolio = portfolioManagerView.getSelectedPortfolio();
            portfolio.saveFile();
            portfolioManagerView.displayMessage("File has been saved successfully");

        } catch (IOException e1) {
            portfolioManagerView.displayMessage("Error Saving");
            e1.printStackTrace();
        }
    }
}
