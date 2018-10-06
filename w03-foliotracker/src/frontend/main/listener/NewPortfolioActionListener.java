package frontend.main.listener;

import frontend.main.IPortfolioManager;
import frontend.main.PortfolioSavePrompt;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class NewPortfolioActionListener implements ActionListener {
    private IPortfolioManager portfolioManager;

    public NewPortfolioActionListener(IPortfolioManager portfolioManager) {
        this.portfolioManager = portfolioManager;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        PortfolioSavePrompt portfolioFileSaver = new PortfolioSavePrompt();

        try {
            portfolioFileSaver.createAndShowGUI(portfolioManager);
        } catch (IOException e1) {
            e1.printStackTrace();
            System.out.println("Failed creating");
        } catch (ClassNotFoundException e1) {
            System.out.println("Failed creating");
            e1.printStackTrace();
        }
    }
}
