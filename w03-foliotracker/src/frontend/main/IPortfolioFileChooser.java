package frontend.main;

import frontend.portfolio.IPortfolio;

import java.io.IOException;

public interface IPortfolioFileChooser {

    IPortfolio getOpenedPortfolio() throws IOException,ClassNotFoundException;
}
