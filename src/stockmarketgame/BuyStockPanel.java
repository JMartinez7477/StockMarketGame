/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stockmarketgame;

import java.text.DecimalFormat;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

/**
 *
 * @author S531749
 */
public class BuyStockPanel extends JPanel {

    double purchaseValue;
    String company;
    double totalPrice;
    int amountPurchased;
    //boolean changed;

    public BuyStockPanel(String company, double purchaseValue) {
        super();
        this.company = company;
        this.purchaseValue = purchaseValue;
        setUp();
    }
    
    public BuyStockPanel(Stock stock){
        this(stock.getName(), stock.getPps());
    }

    public void setUp() {
        //Company Name Label
        JLabel label = new JLabel(stringToLength((company+":"), 20) +  "Price Per Share");
        label.setFont(Variables.f30);
        label.setForeground(Variables.fg);
        label.setBackground(Variables.bg);

        this.add(label);

        //Price
        JTextField price = new JTextField(("$" + purchaseValue), 4);
        price.setFont(Variables.f30);
        price.setForeground(Variables.fg);
        price.setBackground(Variables.bg);
        price.setEditable(false);

        this.add(price);

        //Quantity
        JLabel label2 = new JLabel("  Quantity");
        label2.setFont(Variables.f30);
        label2.setForeground(Variables.fg);
        label2.setBackground(Variables.bg);

        this.add(label2);

        JTextField quantity = new JTextField("0", 3);
        quantity.setFont(Variables.f30);
        quantity.setForeground(Variables.fg);
        quantity.setBackground(Variables.bg);
        Document quantityDoc = quantity.getDocument();

        JTextArea report = new JTextArea(1, 4);
        report.setFont(Variables.f30);
        report.setForeground(Variables.fg);
        report.setBackground(Variables.bg);
        report.setText("  Price: $0.00");
        report.setEditable(false);

        quantityDoc.addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
                try {
                    report.setText(quantityToPrice(quantityDoc.getText(0, quantityDoc.getLength())));
                    //changed = true;
                } catch (BadLocationException ex) {
                    System.out.println(ex);
                }
            }

            public void insertUpdate(DocumentEvent e) {
                try {
                    report.setText(quantityToPrice(quantityDoc.getText(0, quantityDoc.getLength())));
                    //changed = true;
                } catch (BadLocationException ex) {
                    System.out.println(ex);
                }
            }

            public void removeUpdate(DocumentEvent e) {
                try {
                    report.setText(quantityToPrice(quantityDoc.getText(0, quantityDoc.getLength())));
                    //changed = true;
                } catch (BadLocationException ex) {
                    System.out.println(ex);
                }
            }
        });

        this.add(quantity);
        this.add(report);

        this.setForeground(Variables.fg);
        this.setBackground(Variables.bg);
    }

    public String quantityToPrice(String quanity) {
        if (quanity.equals("0") || quanity.isEmpty()) {
            return "  Price: $0.00";
        } else {
            int shares = Integer.parseInt(quanity);
            amountPurchased = shares;
            totalPrice = shares * purchaseValue;
            DecimalFormat format = new DecimalFormat("0.00");
            return "  Price: $" + format.format(totalPrice);
        }
    }
    
    public double getTotalPrice(){
        //changed = false;
        return totalPrice;
    }
    
    public int getAmountPurchased(){
        return amountPurchased;
    }
    
    public String stringToLength(String in, int length){
        while(in.length() < length){
            in += " ";
        }
        return in;
    }
}
