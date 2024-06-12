import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;

import javax.imageio.ImageIO;

public class LTV extends Vehicles {

    Image myimage;

    public LTV(int nx, int ny, int nlane) {
        super(nx, ny, nlane);
        width = 100;
        height = 60;
        speed = 3;
        try {
            myimage = ImageIO.read(new File("istockphoto-1138785358-612x612.jpg"));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void createVehicle(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillRect(x, y, width, height);
        // g.drawImage(myimage, x, y, null);
    }

}
