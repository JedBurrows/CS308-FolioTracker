package frontend.stock.listener;

import java.awt.event.ActionListener;
import frontend.stock.IStock;
import frontend.stock.IStockView;

public class BuyStockActionListener extends AbstractStockActionListener implements ActionListener {

    public BuyStockActionListener(IStockView stockView, IStock stock) {
        super(stockView, stock);
    }

    @Override
    public String getErrorMessage() {
        return "Please enter a non-negative real value";
    }

    @Override
    public void action() {
        if (getStockView().confirmationDialogue("Are you sure you want to Buy Stock?")) {
            getStock().addShares(getStockView().getAmountBuySell());
        }
    }
}
