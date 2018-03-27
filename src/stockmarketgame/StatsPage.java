/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stockmarketgame;

import java.awt.Color;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author S531749
 */
public class StatsPage extends JFrame {

    ArrayList<StatsPanel> panels;
    DayPanel dayPanel;

    public StatsPage(ArrayList<Player> players) {
        super(Variables.gameName);
        setUp(players);
    }

    public void setUp(ArrayList<Player> players) {
        setSize(1000, 700);
        setIconImage(Variables.logo.getImage());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Box b = Box.createVerticalBox();

        dayPanel = new DayPanel();
        b.add(dayPanel);

        panels = new ArrayList();
        for (int i = 0; i < players.size(); i++) {
            StatsPanel panel = new StatsPanel(players.get(i));
            panels.add(panel);
            b.add(panel);
            //System.out.println("added");
        }

        b.setForeground(Variables.fg);
        b.setBackground(Variables.bg);
        add(b);

        setForeground(Variables.fg);
        setBackground(Variables.bg);
        setVisible(true);
    }

    public void updatePanels() {
        for (int i = 0; i < panels.size(); i++) {
            panels.get(i).update();
        }
    }

    public void updateTime(boolean inflate) {
        if (inflate) {
            dayPanel.addTimeInflation();
        } else {
            dayPanel.addTimeDeflation();
        }
        dayPanel.update();
    }
}

class DayPanel extends JPanel {

    JTextArea dayText;
    long milliseconds;

    public DayPanel() {
        super();
        setUp();
    }

    public void setUp() {
        milliseconds = Variables.startTime;
        dayText = new JTextArea(1, 20);
        dayText.setFont(Variables.f64);
        dayText.setForeground(Variables.fg);
        dayText.setBackground(Variables.bg);
        update();
        add(dayText);

        setForeground(Variables.fg);
        setBackground(Variables.bg);
    }

    public void update() {
        dayText.setText("              " + dayFromMilli(milliseconds));
    }

    public void addTimeInflation() {
        long difference = Variables.crashTime - Variables.startTime;
        double difPerSec = difference / Variables.timeIncrease;
        milliseconds += difPerSec;
    }

    public void addTimeDeflation() {
        long difference = Variables.finishTime - Variables.crashTime;
        double difPerSec = difference / Variables.timeDecrease;
        milliseconds += difPerSec;
    }

    public String dayFromMilli(long milliseconds) {
        Date day = new Date(milliseconds);
        String dayStr = day.toString();
        String[] parts = dayStr.split(" ");
        String dayOfWeek = "";
        switch (parts[0]) {
            case "Mon":
                dayOfWeek = "Monday";
                break;
            case "Tue":
                dayOfWeek = "Tuesday";
                break;
            case "Wed":
                dayOfWeek = "Wednesday";
                break;
            case "Thu":
                dayOfWeek = "Thursday";
                break;
            case "Fri":
                dayOfWeek = "Friday";
                break;
            case "Sat":
                dayOfWeek = "Saturday";
                break;
            case "Sun":
                dayOfWeek = "Sunday";
                break;
            default:
                dayOfWeek = "";
                break;
        }

        String good = dayOfWeek + " " + parts[1] + " " + parts[2] + ", " + parts[5];
        good = good.trim();
        return good;
    }
}

class StatsPanel extends JPanel {

    Player player;
    DecimalFormat out;
    JTextArea curValText;

    public StatsPanel(Player player) {
        super();
        this.player = player;
        setUp();
    }

    public void setUp() {
        String s = stringToLength(player.getName() + ":", 15);
        out = new DecimalFormat("$0.00");
        s += "Initial Value: " + out.format(player.getInitialSpent());
        JLabel label = new JLabel(s);
        label.setFont(Variables.f40);
        label.setForeground(Variables.fg);
        label.setBackground(Variables.bg);
        add(label);

        curValText = new JTextArea(1, 4);
        update();
        curValText.setFont(Variables.f40);
        curValText.setBackground(Variables.bg);
        add(curValText);

        setForeground(Variables.fg);
        setBackground(Variables.bg);
    }

    public void update() {
        String s = "Percent Change: ";
        double change = player.percentChange();
        int percent = (int) Math.round(change * 100);
        s += percent + "%";
        s += "      Current Value: " + out.format(player.getValueOfStocks());
        curValText.setText(s);

        if (change > 0) {
            curValText.setForeground(Color.GREEN);
        } else if (change == 0) {
            curValText.setForeground(Color.YELLOW);
        } else {
            curValText.setForeground(Color.RED);
        }
    }

    public String stringToLength(String in, int length) {
        while (in.length() < length) {
            in += " ";
        }
        return in;
    }
}
