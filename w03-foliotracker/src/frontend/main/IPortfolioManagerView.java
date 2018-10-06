package frontend.main;

import frontend.portfolio.IPortfolio;

public interface IPortfolioManagerView {
    /**
     * Creates and shows the initial frames of the GUI <br>
     * modifies: this <br>
     * effects: Creates and show GUI components for PortfolioManager <br>
     */
    void createAndShowGUI();

    /**
     * modifies: this <br>
     * effects: Returns the portfolio that is currently opened <br>
     */
    IPortfolio getSelectedPortfolio();

    /**
     * modifies: this <br>
     * effects: Deletes the current portfolio and makes a new portfolio object with updated values  <br>
     */
    void update(java.util.Observable o, Object arg);

    /**
     * requires: showPortfolioSavePrompt == true || false
     * modifies: this <br>
     * effects: Closes the tab that corresponds to the current portfolio  <br>
     */
    void closeSelectedTab();

    /**
     * effects: Update the window title to the portfolio that is currently open <br>
     */
    void updateTitle();

    boolean getConformation(String text);

    void displayMessage(String text);
}
