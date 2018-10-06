package frontend.stock.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import frontend.stock.IStock;
import frontend.stock.IStockView;

public abstract class AbstractStockActionListener implements ActionListener {

    private IStock stock;
    private IStockView IStockView;

    public AbstractStockActionListener(IStockView stockView, IStock stock) {
        this.stock = stock;
        this.IStockView = stockView;
    }

    public abstract String getErrorMessage();

    public abstract void action();

    @Override
    public void actionPerformed(ActionEvent e) {
        if (validateText(IStockView.getText())) {
            action();
            IStockView.clearText();
        } else {
            IStockView.popupError(getErrorMessage());
        }
    }


    private boolean validateText(String text) {
        try {
            int value = Integer.parseInt(text);
            return value >= 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public IStock getStock() {
        return stock;
    }

    public void setStock(IStock stock) {
        this.stock = stock;
    }

    public IStockView getStockView() {
        return IStockView;
    }
}
