package frontend.main;

import frontend.portfolio.IPortfolio;
import utils.PortfolioSerializer;

import javax.swing.*;
import java.io.*;

public class PortfolioFileChooser implements IPortfolioFileChooser {
    private JFileChooser fc;
    private static final String FILE_PATH = System.getProperty("user.home") + "/Documents/PortfolioManager/";

    public PortfolioFileChooser() {
        fc = new JFileChooser(FILE_PATH);
    }

    @Override
    public IPortfolio getOpenedPortfolio() throws IOException, ClassNotFoundException {
        int returnValue = fc.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();

            return PortfolioSerializer.openFromFile(file);

        } else if (returnValue == JFileChooser.CANCEL_OPTION) {
        }
        // avoid returning null
        return null;
    }
}
