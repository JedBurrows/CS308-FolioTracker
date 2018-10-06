package frontend.main.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import frontend.main.IPortfolioManagerView;
import frontend.portfolio.IPortfolio;

import javax.swing.*;

public class CloseButtonActionListener implements ActionListener {

    private IPortfolioManagerView portfolioManagerView;

    public CloseButtonActionListener(IPortfolioManagerView portfolioManagerView) {
        this.portfolioManagerView = portfolioManagerView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {


        if (portfolioManagerView.getConformation("Would you like to save the changes you've made before closing?")) {

            try {

                IPortfolio portfolio = portfolioManagerView.getSelectedPortfolio();
                portfolio.saveFile();

            } catch (IOException e1) {
                JOptionPane.showMessageDialog(null, "Error saving file");
                e1.printStackTrace();
            }

        }

        portfolioManagerView.closeSelectedTab();

    }
}
