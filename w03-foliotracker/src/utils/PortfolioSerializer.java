package utils;

import frontend.portfolio.IPortfolio;
import frontend.portfolio.Portfolio;

import java.io.*;

public class PortfolioSerializer {

    /**
     * requires: file != null && portfolio != null
     * effects: Serializes a portfolio object and saves it at file
     *
     * @param file
     * @param portfolio
     * @throws IOException
     */
    public static void saveToDisk(File file, IPortfolio portfolio) throws IOException {

        FileOutputStream fileOutputStream = new FileOutputStream(file);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

        objectOutputStream.writeObject(portfolio);

        objectOutputStream.close();
        fileOutputStream.close();
    }

    /**
     * Save a file to disk
     * requires: file != null
     * effects: Inflate IPortfolio object from a serialized object
     *
     * @param file
     * @throws IOException
     */
    public static IPortfolio openFromFile(File file) throws IOException, ClassNotFoundException {
        FileInputStream fileOutputStream = new FileInputStream(file);
        ObjectInputStream objectOutputStream = new ObjectInputStream(fileOutputStream);

        IPortfolio portfolio1 = (Portfolio) objectOutputStream.readObject();
        portfolio1.setFileLocation(file);

        objectOutputStream.close();
        fileOutputStream.close();

        return portfolio1;
    }
}
