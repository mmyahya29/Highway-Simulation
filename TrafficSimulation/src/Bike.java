import java.awt.Color;
import java.awt.Graphics;

public class Bike extends Vehicles {

    public Bike(int nx, int ny,int nlane) {
        super(nx, ny,nlane);
        width = 60;
        height = 30;
        speed = 5;
    }

    public void createVehicle(Graphics g) {
        g.setColor(Color.green);
        g.fillRect(x, y, width, height);
    }

}
