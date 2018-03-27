/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stockmarketgame;

import java.awt.Toolkit;
import java.util.ArrayList;

/**
 *
 * @author S531749
 */
public class StockMarketGame {

    /**
     * @param args the command line arguments
     */
    static ArrayList<Player> players;

    public static void main(String[] args) {
        // TODO code application logic here
        run();
    }

    public static void run() {
        Variables.initStocks();
        createPlayers();
        runMarket();
    }

    public static void createPlayers() {
        players = new ArrayList();
        for (int i = 0; i < Variables.numPlayers; i++) {
            boolean done = false;
            RegisterFrame f = new RegisterFrame();

            while (!f.finished) {
                System.out.print("");
            }
            players.add(f.getPlayer());
            f.priceChange.stop();
            f.frame.setVisible(false);
            f.frame.dispose();
            //System.out.println(players.get(i).getName());
        }
    }

    public static void runMarket() {
        StatsPage statFrame = new StatsPage(players);
        Toolkit tk = Toolkit.getDefaultToolkit();
        
        statFrame.updatePanels();
        
        try{
            Thread.sleep(1000);
        }catch(Exception e){
            System.out.println(e);
        }
        
        //Inflation period
        for (int i = 0; i < Variables.timeIncrease; i++) {
            statFrame.updateTime(true);
            inflation();
            statFrame.updatePanels();
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                System.out.println(e);
            }
            
            if(isTimeToBeep(i)){
                tk.beep();
            }
        }
        
        //Start Alarm
        Alarm alarm = new Alarm(Variables.alarmFilePath);
        
        //Deflation Period
        for (int i = 0; i < Variables.timeDecrease; i++) {
            statFrame.updateTime(false);
            deflation();
            statFrame.updatePanels();
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        alarm.stop();
    }

    public static void inflation() {
        for (int i = 0; i < Variables.stocks.size(); i++) {
            double percentToChange = Variables.percentIncrease / Variables.timeIncrease;
            double amountToChange = Variables.stocks.get(i).getInitVal() * percentToChange
                    + (Math.random()*2)-1;
            Variables.stocks.get(i).addToPps(amountToChange);
        }
    }

    public static void deflation() {
        for (int i = 0; i < Variables.stocks.size(); i++) {
            double percentToChange = Variables.percentDecrease / Variables.timeDecrease;
            double amountToChange = Variables.stocks.get(i).getInitVal() * percentToChange
                    + (Math.random()*2)-1; 
            Variables.stocks.get(i).addToPps(-amountToChange);
        }
    }
    
    public static boolean isTimeToBeep(int time){
        int[] timesAway = {1,2,3,6,11,21};
        for (int i = 0; i < timesAway.length; i++) {
            if(time == (Variables.timeIncrease - timesAway[i])){
                return true;
            }
        }
        return false;
    }
}
