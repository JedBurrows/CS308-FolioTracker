package frontend.stock.listener;

import frontend.stock.IFocusView;

import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

public class ViewWindowFocusListener implements WindowFocusListener {

    private final IFocusView view;

    public ViewWindowFocusListener(IFocusView view) {
        this.view = view;
    }

    @Override
    public void windowGainedFocus(WindowEvent e) {
        view.onFocus();
    }

    @Override
    public void windowLostFocus(WindowEvent e) {
        view.onUnfocus();
    }
}
