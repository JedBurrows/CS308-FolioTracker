package frontend.stock.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import frontend.portfolio.IPortfolio;
import frontend.stock.IStock;
import frontend.stock.IStockView;

import javax.swing.*;

public class DeleteStockActionListener implements ActionListener {

    private IStock stock;
    private IPortfolio portfolio;
    private IStockView IStockView;

    public DeleteStockActionListener(IStockView IStockView, IStock stock, IPortfolio portfolio) {
        this.stock = stock;
        this.portfolio = portfolio;
        this.IStockView = IStockView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //int dialogResult = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this Stock?");
        if (IStockView.confirmationDialogue("Are you sure you want to delete this Stock?")) {
            IStockView.closeView();
            portfolio.removeStock(stock);
        }
    }
}
