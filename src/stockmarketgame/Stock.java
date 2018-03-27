/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stockmarketgame;

/**
 *
 * @author S531749
 */
public class Stock {

    private String name;
    final private double init;
    private double pps;
    private double peak;

    public Stock(String name, double pps) {
        this.name = name;
        this.pps = pps;
        init = pps;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPps() {
        return pps;
    }

    public void setPps(double pps) {
        this.pps = pps;
    }

    public double getInitVal() {
        return init;
    }

    public double getPeak() {
        return peak;
    }

    public void addToPps(double amount) {
        pps += amount;
        if (amount > 0) {
            peak = pps;
        }
        if(pps < 0){
            pps = 0;
        }
        //System.out.println(name + ": " + pps);
    }
}
