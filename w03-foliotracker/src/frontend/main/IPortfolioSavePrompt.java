package frontend.main;

import java.io.IOException;

public interface IPortfolioSavePrompt {

    /**
     * Open FileChooser that allows for saving <br>
     *
     * @throws IOException
     * @throws ClassNotFoundException
     */
    void createAndShowGUI() throws IOException, ClassNotFoundException;

    /**
     * requires: portfolioManager != null <br>
     * effects: Open FileChooser that allows for saving and adds it to the portfolioManager passed in. <br>
     *
     * @param portfolioManager
     * @throws IOException
     * @throws ClassNotFoundException
     */
    void createAndShowGUI(IPortfolioManager portfolioManager)  throws IOException, ClassNotFoundException;
}
