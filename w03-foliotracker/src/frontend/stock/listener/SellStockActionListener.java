package frontend.stock.listener;

import java.awt.event.ActionListener;

import frontend.stock.IStock;
import frontend.stock.IStockView;

public class SellStockActionListener extends AbstractStockActionListener implements ActionListener {

    public SellStockActionListener(IStockView stockView, IStock stock) {
        super(stockView, stock);
    }

    @Override
    public String getErrorMessage() {
        return "Please enter a non-negative real value";
    }

    @Override
    public void action() {
        if (getStockView().confirmationDialogue("Are you sure you want to Sell Stock?")) {
        	
        	if(getStockView().getAmountBuySell() > getStock().getNumberOfShares()){
            	getStockView().popupError("You cannot sell more shares than you own!");
            	return;
            }
            getStock().removeShares(getStockView().getAmountBuySell());
        }
    }
}
