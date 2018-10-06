import frontend.main.IPortfolioManagerView;
import frontend.main.PortfolioManagerView;

import javax.swing.*;

public class Driver {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                IPortfolioManagerView IPortfolioManagerView = new PortfolioManagerView();
                IPortfolioManagerView.createAndShowGUI();
            }
        });
    }
}
