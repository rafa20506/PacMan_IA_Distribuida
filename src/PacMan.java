import javax.imageio.ImageIO;
import java.awt.Point;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class PacMan{
    private Point pos;
    private Color color;
    private final int alto = 22;
    private final int ancho = 22;

    public PacMan(Point pos, Color color){
        this.pos = pos;
        this.color = color;
    }

    public Color getColor(){
        return color;
    }
    public void setColor(Color color){
        this.color = color;
    }

    public Point getPos() {
        return pos;
    }

    public void setPos(Point pos) {
        this.pos = pos;
    }
    public void dibujar(Graphics2D g2d){
        g2d.setColor(color);
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File("images/pacman.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        g2d.drawImage(img, (int) pos.getX() * 22, (int) pos.getY() * 22, null);
        //g2d.fillOval((int)pos.getX() * 22, (int)pos.getY() * 22, ancho, alto);
    }
}