package frontend.main;

import frontend.portfolio.IPortfolio;
import frontend.portfolio.Portfolio;

import javax.swing.*;
import java.io.*;

public class PortfolioSavePrompt implements IPortfolioSavePrompt {

    private IPortfolio portfolio;
    private JFileChooser fc;
    private static final String FILE_PATH = System.getProperty("user.home") + "/Documents/PortfolioManager/";

    public PortfolioSavePrompt(IPortfolio portfolio) {
        fc = new JFileChooser(FILE_PATH);
        this.portfolio = portfolio;
    }

    public PortfolioSavePrompt() {
        this(new Portfolio());
    }

    @Override
    public void createAndShowGUI() throws IOException, ClassNotFoundException {

        int returnValue = fc.showSaveDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {

            boolean success = getSelectedFile();
            if (!success) {
                createAndShowGUI();
            }

        } else if (returnValue == JFileChooser.CANCEL_OPTION) {
            System.out.println("Cancelled");
        }
    }

    @Override
    public void createAndShowGUI(IPortfolioManager portfolioManager) throws IOException, ClassNotFoundException {
        int returnValue = fc.showSaveDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {

            boolean success = getSelectedFile();
            if (!success) {
                createAndShowGUI(portfolioManager);
                return;
            }

            portfolioManager.add(portfolio);

        } else if (returnValue == JFileChooser.CANCEL_OPTION) {
            System.out.println("Cancelled");

        }

    }

    private boolean getSelectedFile() throws IOException, ClassNotFoundException {
        File file = fc.getSelectedFile();

        if (!portfolio.isValidName((file.getName()))) {
            JOptionPane.showMessageDialog(null, "File name must only contain alphanumeric characters");

            return false;
        }

        portfolio.setFileLocation(file);
        portfolio.saveFile();

        return true;
    }

}
