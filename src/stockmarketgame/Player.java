/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stockmarketgame;

import java.util.ArrayList;

/**
 *
 * @author S531749
 */
public class Player {

    private String name;
    private double initialSpent;

    private ArrayList<Integer> owned;

    public Player(String name) {
        this.name = name;
        owned = new ArrayList();
    }

    public String getName() {
        return name;
    }

    public double getInitialSpent() {
        return initialSpent;
    }

    public void setInitialSpent(double initialSpent) {
        this.initialSpent = initialSpent;
    }

    public void addOwned(int amount){
        owned.add(amount);
    }

   public int getAmountOwned(int index){
       return owned.get(index);
   }
   
   public double getValueOfStocks(){
       double value = 0;
       for (int i = 0; i < Variables.stocks.size(); i++) {
           value += (owned.get(i) * Variables.stocks.get(i).getPps()); 
       }
       return value;
   }
   
   public double madeMoney(){
       return Math.signum(getValueOfStocks() - initialSpent);
   }
   
   public double percentChange(){
       return (getValueOfStocks()-initialSpent)/initialSpent;
   }

}
