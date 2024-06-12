import java.awt.event.*;
import javax.swing.*;
import javax.swing.JPanel;
import java.util.*;
import javax.swing.*;
import javax.swing.JFrame;
import java.awt.*;
import java.awt.event.*;

public class Road extends JPanel {

    ArrayList<Vehicles> veh = new ArrayList<Vehicles>();

    final int laneHeight = 120;
    final int roadWidth = 1370;
    int carcount = 0;
    String trflight = "green";

    public Road() {
        super();
    }

    public void addveh(Vehicles v) {
        veh.add(v);

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        // g.drawLine(0,0,500,800);
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), laneHeight * 3);
        g.setColor(Color.YELLOW);
        g.fillRect(1100, 0, 5, (laneHeight * 3) + 20);
        g.setColor(Color.black);
        g.fillRect(1075, (laneHeight * 3) + 20, 55, 110);
        if (trflight == "green") {
            g.setColor(Color.green);
            g.fillOval(1080, (laneHeight * 3) + 75, 45, 45);
        } else if (trflight == "red") {
            g.setColor(Color.red);
            g.fillOval(1080, (laneHeight * 3) + 25, 45, 45);
        }
        g.setColor(Color.WHITE);
        for (int i = laneHeight; i < laneHeight * 4; i = i + laneHeight) {
            for (int j = 0; j < getWidth(); j = j + 40) {
                g.fillRect(j, i, 30, 5);
            }
        }
        for (int i = 0; i < veh.size(); i++) {
            veh.get(i).createVehicle(g);
        }
    }

    public void step() {
        for (int i = 0; i < veh.size(); i++) {
            Vehicles v = veh.get(i);
            if (((v.getX() + v.getWidth()) <= 1090 || v.getX() + v.getWidth() >= 1110 || trflight != "red")) {
                if (collision(v.getX() + v.getSpeed() + 5, v.getY(), v.getLane(), v) == false) {
                    v.setX(v.getX() + v.getSpeed());
                    if (collision(0, v.getY(), v.getLane(), v) == false) {
                        if (v.getX() >= roadWidth) {
                            v.setX(0);
                            carcount++;
                        }
                    }
                } else if (v.getLane() < 3 && collision(v.getX(), v.getY() + laneHeight, v.getLane() + 1, v) == false) {
                    v.setY(v.getY() + laneHeight);
                    v.setLane(v.getLane() + 1);
                } else if (v.getLane() > 1 && collision(v.getX(), v.getY() - laneHeight, v.getLane() - 1, v) == false) {
                    v.setY(v.getY() - laneHeight);
                    v.setLane(v.getLane() - 1);
                }
            }
        }
    }

    public boolean collision(int x, int y, int lane, Vehicles v) {
        for (int i = 0; i < veh.size(); i++) {
            Vehicles ve = veh.get(i);
            if (lane == ve.getLane() && (ve.equals(v) == false) && x < (ve.getX() +
                    ve.getWidth())
                    && (x + v.getWidth()) > ve.getX() /* || ((x + v.getWidth()) >= 1100 && trflight == "red") */) {
                return true;
            }
        }
        return false;
    }

    public int getCount() {
        return carcount;
    }

    public void setCount() {
        carcount = 0;
    }
}
