package frontend.stock.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import frontend.stock.IStock;
import frontend.stock.IStockView;

public class SaveStockActionListener implements ActionListener {
    private IStock stock;
    private IStockView stockView;
	
	public SaveStockActionListener(IStockView stockView, IStock stock) {
		this.stock = stock;
        this.stockView = stockView;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		stock.setStockName(stockView.getStockName());
		stockView.closeView();
	}
}
