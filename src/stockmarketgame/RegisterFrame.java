/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stockmarketgame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import javax.swing.AbstractAction;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.text.Document;

/**
 *
 * @author S531749
 */
public class RegisterFrame {

    JFrame frame;

    JPanel nameEnter;
    Document nameDoc;

    Box stocksChooser;
    ArrayList<BuyStockPanel> stockPanels;
    Timer priceChange;
    JTextArea totalPrice;

    JButton purchaseButton;
    double spent;

    Player player;
    boolean finished;

    public RegisterFrame() {
        setUpScreen();
        updateTotal();
    }

    public void setUpScreen() {
        frame = new JFrame(Variables.gameName);
        frame.setSize(1000, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setIconImage(Variables.logo.getImage());
        setUpNamePanel();
        Box b = Box.createVerticalBox();
        b.add(nameEnter);

        setUpStockPanels();
        setUpButton();
        b.add(stocksChooser);

        
        //b.add(purchaseButton);
        
        b.setBackground(Variables.bg);
        frame.add(b);
        frame.setBackground(Variables.bg);
        frame.setVisible(true);
    }

    public void setUpNamePanel() {
        nameEnter = new JPanel();

        JLabel label = new JLabel("Name: ");
        label.setFont(Variables.f56);
        label.setForeground(Variables.fg);
        nameEnter.add(label);

        JTextField nameEnterField = new JTextField("", 18);
        nameEnterField.setFont(Variables.f48);
        nameEnterField.setForeground(Variables.fg);
        nameEnterField.setBackground(Variables.bg);

        nameEnter.add(nameEnterField);
        nameEnter.setForeground(Variables.fg);
        nameEnter.setBackground(Variables.bg);

        try {
            nameDoc = nameEnterField.getDocument();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void setUpStockPanels() {
        stocksChooser = Box.createVerticalBox();

        stockPanels = new ArrayList();
        
        for (int i = 0; i < Variables.stocks.size(); i++) {
            stockPanels.add(new BuyStockPanel(Variables.stocks.get(i)));
            
        }

        for (int i = 0; i < stockPanels.size(); i++) {
            stocksChooser.add(stockPanels.get(i));
        }

        JPanel pricePanel = new JPanel();
        DecimalFormat out = new DecimalFormat("$0.00");
        JLabel label = new JLabel(stringToLength("Budget: " + out.format(Variables.budget) + "      Total Price: ", 20));
        label.setFont(Variables.f40);
        label.setForeground(Variables.fg);
        label.setBackground(Variables.bg);
        pricePanel.add(label);
        
        totalPrice = new JTextArea(1, 4);
        totalPrice.setFont(Variables.f40);
        totalPrice.setForeground(Variables.fg);
        totalPrice.setBackground(Variables.bg);
        totalPrice.setText("$0.00");
        totalPrice.setEditable(false);

        pricePanel.add(totalPrice);
        pricePanel.setForeground(Variables.fg);
        pricePanel.setBackground(Variables.bg);

        stocksChooser.add(pricePanel);
        stocksChooser.setForeground(Variables.fg);
        stocksChooser.setBackground(Variables.bg);
    }

    public void setUpButton() {
        JPanel p = new JPanel();
        purchaseButton = new JButton(new AbstractAction("Purchase") {
            public void actionPerformed(ActionEvent e) {
                
                if (spent < Variables.budget) {
                    //System.out.println("less than");
                    String name = "";
                    try {
                        name = nameDoc.getText(0, nameDoc.getLength());
                    } catch (Exception ex) {
                        System.out.println(ex);
                    }
                    player = new Player(name);
                    for (int i = 0; i < stockPanels.size(); i++) {
                        player.addOwned(stockPanels.get(i).getAmountPurchased());
                    }
                    player.setInitialSpent(spent);
                    finished = true;
                }
            }
        });
        purchaseButton.setFont(Variables.f40);
        p.add(purchaseButton);
        p.setForeground(Variables.fg);
        p.setBackground(Variables.bg);
        
        stocksChooser.add(p);
        stocksChooser.setForeground(Variables.fg);
        stocksChooser.setBackground(Variables.bg);
    }

    public void updateTotal() {
        int delay = 50;

        ActionListener update = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                double total = 0;
                for (int i = 0; i < stockPanels.size(); i++) {
                    total += stockPanels.get(i).getTotalPrice();
                }
                spent = total;
                DecimalFormat out = new DecimalFormat("0.00");
                totalPrice.setText("$" + out.format(total));
            }
        };
        priceChange = new Timer(delay, update);
        priceChange.start();
    }

    public String stringToLength(String in, int length) {
        while (in.length() < length) {
            in = " " + in;
        }
        return in;
    }
    
    public Player getPlayer(){
        return player;
    }
}
