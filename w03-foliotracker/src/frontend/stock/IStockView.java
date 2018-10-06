package frontend.stock;

import java.util.Observable;

public interface IStockView {
    /**
     * Create a pop-up window when stock record is clicked <br>
     */
    void createAndShowGUI();

    void closeView();

    void clearText();

    String getText();

    void update(Observable o, Object arg);

    int getAmountBuySell();

    void onFocus();

    void onUnfocus();

    void popupError(String text);
    
    boolean confirmationDialogue(String text);

    String getStockName();
}
