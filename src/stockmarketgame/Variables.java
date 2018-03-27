package stockmarketgame;

import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.util.ArrayList;
import javax.swing.ImageIcon;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author S531749
 */
public class Variables {

    static final String[] fonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
    static Font f30 = new Font(fonts[200], Font.BOLD, 30);
    static Font f36 = new Font(fonts[200], Font.BOLD, 36);
    static Font f40 = new Font(fonts[200], Font.BOLD, 40);
    static Font f48 = new Font(fonts[200], Font.BOLD, 48);
    static Font f56 = new Font(fonts[200], Font.BOLD, 56);
    static Font f64 = new Font(fonts[200], Font.BOLD, 64);

    static Color fg = new Color(255, 255, 255);
    static Color bg = new Color(0, 0, 0);
    
    static String gameName = "100 Worthy Project";
    static ImageIcon logo = new ImageIcon("money2.jpg");
    static String alarmFilePath = "Imperial.wav";
    static double budget = 10000;
    static int numPlayers = 2;
    
    static double percentIncrease = 8.00;
    static double timeIncrease = 100;
    
    static double percentDecrease = 8.90;
    static double timeDecrease = 12;
    
    static long startTime = -1546279171000L;//12am Jan 1 1921
    static long crashTime = -1268128771000L;//8am Oct 25 1929 
    static long finishTime = -1267783171000L;//8am Oct 29 1929

    static ArrayList<Stock> stocks = new ArrayList();
    public static void initStocks() {
        stocks.add(new Stock("Ford", 20.11));
        stocks.add(new Stock("Carnegie Steel", 26.56));
        stocks.add(new Stock("Rockefeller Oil", 17.42));
        stocks.add(new Stock("Coca Cola", 13.78));
        stocks.add(new Stock("JP Morgan", 19.27));
    }
}
