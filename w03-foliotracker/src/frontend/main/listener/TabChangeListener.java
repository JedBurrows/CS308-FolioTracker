package frontend.main.listener;

import frontend.main.IPortfolioManagerView;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class TabChangeListener implements ChangeListener {

    private IPortfolioManagerView portfolioManagerView;

    public TabChangeListener(IPortfolioManagerView portfolioManagerView) {
        this.portfolioManagerView = portfolioManagerView;
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        portfolioManagerView.updateTitle();
    }
}
