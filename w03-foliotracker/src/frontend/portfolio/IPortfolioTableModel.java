package frontend.portfolio;

import javax.swing.table.TableModel;
import java.util.Vector;

public interface IPortfolioTableModel extends TableModel {
    void fireTableDataChanged();

    Vector<String> getRow(int stockId);

    @Override
    int getRowCount();

    @Override
    int getColumnCount();

    @Override
    String getColumnName(int columnIndex);

    @Override
    Class<?> getColumnClass(int columnIndex);

    @Override
    boolean isCellEditable(int rowIndex, int columnIndex);

    @Override
    Object getValueAt(int rowIndex, int columnIndex);

	//void refreshStockPrices();
}
