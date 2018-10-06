package frontend.main;

import frontend.main.listener.*;
import frontend.portfolio.*;

import javax.swing.*;
import java.awt.*;
import java.util.*;

public class PortfolioManagerView extends Observable implements Observer, IPortfolioManagerView {


    private static final String TITLE = "Stocks";

    private final OpenPortfolioActionListener openPortfolioActionListener;
    private JTabbedPane tabbedPane;
    private IPortfolioManager portfolioManager;
    private JButton deleteButton;
    private JButton closeButton;
    private JMenuItem saveMenuItem;
    private JFrame frame;

    public PortfolioManagerView() {
        portfolioManager = new PortfolioManager();
        portfolioManager.addObserver(this);

        openPortfolioActionListener = new OpenPortfolioActionListener(portfolioManager, this);
    }

    @Override
    public void createAndShowGUI() {
        frame = new JFrame(TITLE);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem openMenuItem = new JMenuItem("Open");
        openMenuItem.addActionListener(openPortfolioActionListener);
        JMenuItem createPortfolioMenuItem = new JMenuItem("New");

        createPortfolioMenuItem.addActionListener(new NewPortfolioActionListener(portfolioManager));

        saveMenuItem = new JMenuItem("Save");
        SavePortfolioActionListener savePortfolioActionListener = new SavePortfolioActionListener(this);
        saveMenuItem.addActionListener(savePortfolioActionListener);

        fileMenu.add(createPortfolioMenuItem);
        fileMenu.add(openMenuItem);
        fileMenu.add(saveMenuItem);
        menuBar.add(fileMenu);

        //Main Panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        //Tabs
        tabbedPane = new JTabbedPane();
        tabbedPane.addChangeListener(new TabChangeListener(this));

        mainPanel.add(tabbedPane, BorderLayout.CENTER);
        mainPanel.add(bottomPanel(), BorderLayout.SOUTH);
        frame.add(mainPanel);

        frame.setJMenuBar(menuBar);
        frame.setMinimumSize(new Dimension(700, 600));
        frame.setResizable(false);
        frame.setVisible(true);
    }

    /**
     * Creates the bottom panel for the GUI <br>
     * effects: Returns panel if created successfully <br>
     */
    private JPanel bottomPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // Button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));

        closeButton = new JButton("Close");
        CloseButtonActionListener closeButtonListener = new CloseButtonActionListener(this);
        closeButton.addActionListener(closeButtonListener);

        buttonPanel.add(closeButton);

        deleteButton = new JButton("Delete");
        DeletePortfolioActionListener deleteActionListener = new DeletePortfolioActionListener(portfolioManager, this);
        deleteButton.addActionListener(deleteActionListener);

        buttonPanel.add(deleteButton);

        enableButtons(false);

        panel.add(buttonPanel);

        return panel;
    }

    @Override
    public IPortfolio getSelectedPortfolio() {
        IPortfolioView view = (IPortfolioView) tabbedPane.getSelectedComponent();
        return view != null ? view.getPortfolio() : null;
    }


    @Override
    public void update(Observable o, Object arg) {

        // Portfolio updated
        if (o == portfolioManager) {

            syncTabsWithPortfolioManager();

            if (isTabNotSelected()) {
                enableButtons(false);
            } else {
                enableButtons(true);
            }

        }
    }

    private boolean isTabNotSelected() {
        return tabbedPane.getSelectedIndex() < 0 || portfolioManager.size() == 0;
    }

    /**
     * Synchronise the tabs with PortfolioManager
     */
    private void syncTabsWithPortfolioManager() {
        tabbedPane.removeAll();

        for (IPortfolio portfolio : portfolioManager) {
            tabbedPane.addTab(portfolio.getName(), new PortfolioView(portfolio));
        }

    }

    @Override
    public void closeSelectedTab() {
        portfolioManager.remove(getSelectedPortfolio());
    }

    @Override
    public void updateTitle() {

        IPortfolio selectPortfolio = getSelectedPortfolio();
        String title = "Stocks";
        if (selectPortfolio != null) {
            title = selectPortfolio.getName() + " - PortfolioManager";
        }

        frame.setTitle(title);
    }

    /**
     * Enables Create, Save Menu item and Delete buttons
     *
     * @param enable
     */
    private void enableButtons(boolean enable) {
        closeButton.setEnabled(enable);
        deleteButton.setEnabled(enable);
        saveMenuItem.setEnabled(enable);
    }

    @Override
    public boolean getConformation(String text){
        int dialogResult = JOptionPane.showConfirmDialog(null, text);
        return dialogResult == JOptionPane.YES_OPTION;
    }

    @Override
    public void displayMessage(String text){
         JOptionPane.showMessageDialog(null, text);
    }
}
