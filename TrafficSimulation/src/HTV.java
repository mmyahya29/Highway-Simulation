import java.awt.Color;
import java.awt.Graphics;

public class HTV extends Vehicles {
    public HTV(int nx, int ny,int nlane) {
        super(nx, ny, nlane);
        width = 150;
        height = 90;
        speed = 1;
    }

    public void createVehicle(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(x, y, width, height);
    }
}