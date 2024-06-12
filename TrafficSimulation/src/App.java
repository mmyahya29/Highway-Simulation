import java.util.*;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.lang.Thread;

public class App extends Thread implements ActionListener {
    JFrame frame;
    JButton start, stop, addhtv, addltv, addbike, l1, l2, l3, red, green;
    JLabel highwayoutput, highwaydensity;
    Container c;
    Road road;
    int running;
    int lane;

    Graphics g;

    int density = 0;

    int CAR_COUNT;
    long starttime = 0;

    public App() {
        frame = new JFrame("Traffic Simulation");
        frame.setBounds(-10, 0, 1600, 750);
        frame.setResizable(false);

        // frame.setLayout(road.BorderLayout.CENTER);
        road = new Road();
        frame.add(road, BorderLayout.CENTER);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        c = frame.getContentPane();
        c.setBounds(0, 360, 1600, 750 - 360);

        highwayoutput = new JLabel("Output: " + 0);
        c.add(highwayoutput);
        highwayoutput.setBounds(1130, 380, 200, 70);
        highwayoutput.repaint();

        highwaydensity = new JLabel("Density" + 0);
        c.add(highwaydensity);
        highwaydensity.setBounds(1130, 460, 200, 70);
        highwaydensity.repaint();

        start = new JButton("Start");
        c.add(start);
        start.setBounds(0, 360, 150, 170);
        start.addActionListener(this);
        start.repaint();

        stop = new JButton("Stop");
        c.add(stop);
        stop.setBounds(0, 530, 150, 170);
        stop.addActionListener(this);
        stop.repaint();

        addhtv = new JButton("Add HTV");
        c.add(addhtv);
        addhtv.setBounds(170, 360, 200, 113);
        addhtv.addActionListener(this);
        addhtv.repaint();

        addltv = new JButton("Add LTV");
        c.add(addltv);
        addltv.setBounds(170, 473, 200, 113);
        addltv.addActionListener(this);
        addltv.repaint();

        addbike = new JButton("Add Bike");
        c.add(addbike);
        addbike.setBounds(170, 586, 200, 114);
        addbike.addActionListener(this);
        addbike.repaint();

        l1 = new JButton("Lane 1");
        c.add(l1);
        l1.setBounds(390, 360, 200, 113);
        l1.addActionListener(this);
        l1.repaint();

        l2 = new JButton("Lane 2");
        c.add(l2);
        l2.setBounds(390, 473, 200, 113);
        l2.addActionListener(this);
        l2.repaint();

        l3 = new JButton("Lane 3");
        c.add(l3);
        l3.setBounds(390, 586, 200, 114);
        l3.addActionListener(this);
        l3.repaint();

        red = new JButton("RED");
        c.add(red);
        red.setBounds(600, 360, 100, 50);
        red.addActionListener(this);
        red.repaint();

        green = new JButton("Green");
        c.add(green);
        green.setBounds(600, 410, 100, 50);
        green.addActionListener(this);
        green.repaint();
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == start) {
            if (running == 0) {
                running = 1;
                Thread t = new Thread(this);
                t.start();
                road.setCount();
                starttime = System.currentTimeMillis();
            }
        } else if (e.getSource() == stop) {
            running = 0;
            road.setCount();
        } else if (e.getSource() == l1) {
            lane = 1;
        } else if (e.getSource() == l2) {
            lane = 2;
        } else if (e.getSource() == l3) {
            lane = 3;
        } else if (e.getSource() == addhtv) {
            if (lane == 1) {
                HTV tsthtv = new HTV(10, 15, 1);
                road.addveh(tsthtv);
                frame.repaint();
                density++;

            } else if (lane == 2) {
                HTV tsthtv = new HTV(10, 135, 2);
                road.addveh(tsthtv);
                frame.repaint();
                density++;

            } else if (lane == 3) {
                HTV tsthtv = new HTV(10, 255, 3);
                road.addveh(tsthtv);
                frame.repaint();
                density++;
            }
        } else if (e.getSource() == addltv) {
            if (lane == 1) {
                LTV tstltv = new LTV(10, 30, 1);
                road.addveh(tstltv);
                frame.repaint();
                density++;

            } else if (lane == 2) {
                LTV tstltv = new LTV(10, 150, 2);
                road.addveh(tstltv);
                frame.repaint();
                density++;

            } else if (lane == 3) {
                LTV tstltv = new LTV(10, 270, 3);
                road.addveh(tstltv);
                frame.repaint();
                density++;

            }
        } else if (e.getSource() == addbike) {
            if (lane == 1) {
                Bike tstbike = new Bike(10, 50, 1);
                road.addveh(tstbike);
                frame.repaint();
                density++;

            } else if (lane == 2) {
                Bike tstbike = new Bike(10, 170, 2);
                road.addveh(tstbike);
                frame.repaint();
                density++;

            } else if (lane == 3) {
                Bike tstbike = new Bike(10, 290, 3);
                road.addveh(tstbike);
                frame.repaint();
                density++;
            }
        } else if (e.getSource() == red) {
            road.trflight = "red";
        } else if (e.getSource() == green) {
            road.trflight = "green";
        }
    }

    public void run() {
        while (running == 1) {
            road.step();
            CAR_COUNT = road.getCount();
            double tp = CAR_COUNT / (double) (System.currentTimeMillis() + starttime);
            highwayoutput.setText("Output: " + tp);
            highwaydensity.setText("Density: " + density);
            frame.repaint();
            try {
                Thread.sleep(10);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws Exception {
        App obj = new App();
    }
}
