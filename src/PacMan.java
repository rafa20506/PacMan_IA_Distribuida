import java.awt.Point;
import java.awt.*;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;



public class PacMan extends JPanel{
    private int pacmananimpos = 1;
    private Point pos;
    private Color color;
    private final int alto = 22;
    private final int ancho = 22;
    private int direccion;
    private Image pacman1, pacman2up, pacman2left, pacman2right, pacman2down;
    private Image pacman3up, pacman3down, pacman3left, pacman3right;
    private Image pacman4up, pacman4down, pacman4left, pacman4right;

    public PacMan(Point pos, Color color){
        this.pos = pos;
        this.color = color;
        direccion = 2;
        loadImages();
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
    public void setDir(int dir){
        this.direccion=dir;
    }
    public void dibujar(Graphics2D g2d){
        g2d.setColor(color);
        //g2d.fillOval((int)pos.getX() * 22, (int)pos.getY() * 22, ancho, alto);
        g2d.fillOval((int)pos.getX() , (int)pos.getY(), ancho, alto);
        
    }
    public void drawPacman(Graphics2D g2d) {

        if (direccion==1) {
            drawPacnanLeft(g2d);
        } else if (direccion==2) {
            drawPacmanRight(g2d);
        } else if (direccion==3) {
            drawPacmanUp(g2d);
        } else {
            drawPacmanDown(g2d);
        }
    }


    public void drawPacmanUp(Graphics2D g2d) {

        switch (pacmananimpos) {
            case 1:
                g2d.drawImage(pacman2up,(int) pos.getX() + 1,(int) pos.getY() + 1,this);
                pacmananimpos++;
                break;
            case 2:
                g2d.drawImage(pacman3up, (int) pos.getX() + 1,(int) pos.getY() + 1,this);
                pacmananimpos++;
                break;
            case 3:
                g2d.drawImage(pacman4up, (int) pos.getX() + 1,(int) pos.getY() + 1,this);
                pacmananimpos++;
                break;
            default:
                g2d.drawImage(pacman1, (int) pos.getX() + 1,(int) pos.getY() + 1,this);
                pacmananimpos=1;
                break;
        }
    }

    private void drawPacmanDown(Graphics2D g2d) {

        switch (pacmananimpos) {
            case 1:
                g2d.drawImage(pacman2down, (int) pos.getX() + 1,(int) pos.getY() + 1,this);
                pacmananimpos++;
                break;
            case 2:
                g2d.drawImage(pacman3down, (int) pos.getX() + 1,(int) pos.getY() + 1,this);
                pacmananimpos++;
                break;
            case 3:
                g2d.drawImage(pacman4down, (int) pos.getX() + 1,(int) pos.getY() + 1,this);
                pacmananimpos++;
                break;
            default:
                g2d.drawImage(pacman1, (int) pos.getX() + 1,(int) pos.getY() + 1,this);
                pacmananimpos=1;
                break;
        }
    }

    private void drawPacnanLeft(Graphics2D g2d) {

        switch (pacmananimpos) {
            case 1:
                g2d.drawImage(pacman2left, (int) pos.getX() + 1,(int) pos.getY() + 1,this);
                pacmananimpos++;
                break;
            case 2:
                g2d.drawImage(pacman3left, (int) pos.getX() + 1,(int) pos.getY() + 1,this);
                pacmananimpos++;
                break;
            case 3:
                g2d.drawImage(pacman4left, (int) pos.getX() + 1,(int) pos.getY() + 1,this);
                pacmananimpos++;
                break;
            default:
                g2d.drawImage(pacman1, (int) pos.getX() + 1,(int) pos.getY() + 1,this);
                pacmananimpos=1;
                break;
        }
    }

    private void drawPacmanRight(Graphics2D g2d) {

        switch (pacmananimpos) {
            case 1:
                g2d.drawImage(pacman2right, (int) pos.getX() + 1,(int) pos.getY() + 1,this);
                pacmananimpos++;
                break;
            case 2:
                g2d.drawImage(pacman3right, (int) pos.getX() + 1,(int) pos.getY() + 1,this);
                pacmananimpos++;
                break;
            case 3:
                g2d.drawImage(pacman4right, (int) pos.getX() + 1,(int) pos.getY() + 1,this);
                pacmananimpos++;
                break;
            default:
                g2d.drawImage(pacman1, (int) pos.getX() + 1,(int) pos.getY() + 1,this);
                pacmananimpos=1;
                break;
        }
    }

    private void loadImages() {
        pacman1 = new ImageIcon(getClass().getResource("images/pacman.gif")).getImage();
        pacman2up = new ImageIcon(getClass().getResource("images/up1.gif")).getImage();
        pacman3up = new ImageIcon(getClass().getResource("images/up2.gif")).getImage();
        pacman4up = new ImageIcon(getClass().getResource("images/up3.gif")).getImage();
        pacman2down = new ImageIcon(getClass().getResource("images/down1.gif")).getImage();
        pacman3down = new ImageIcon(getClass().getResource("images/down2.gif")).getImage();
        pacman4down = new ImageIcon(getClass().getResource("images/down3.gif")).getImage();
        pacman2left = new ImageIcon(getClass().getResource("images/left1.gif")).getImage();
        pacman3left = new ImageIcon(getClass().getResource("images/left2.gif")).getImage();
        pacman4left = new ImageIcon(getClass().getResource("images/left3.gif")).getImage();
        pacman2right = new ImageIcon(getClass().getResource("images/right1.gif")).getImage();
        pacman3right = new ImageIcon(getClass().getResource("images/right2.gif")).getImage();
        pacman4right = new ImageIcon(getClass().getResource("images/right3.gif")).getImage();

    }

}