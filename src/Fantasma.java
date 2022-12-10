import java.awt.*;
import java.util.ArrayList;

public abstract class  Fantasma {
    protected int posX;
    protected int posY;
    protected int velocidad;
    protected Color color;
    protected final int ancho;
    protected final int alto;
    protected Point pos;
    protected int[][] mapa;
    ArrayList<Point> mejor;
    public Fantasma(int posX, int posY, int velocidad, Color color, int[][] mapa){
        this.posX = posX;
        this.posY = posY;
        this.pos = new Point(posX, posY);
        this.velocidad = velocidad;
        this.color = color;
        this.mapa = mapa;
        ancho = 22;
        alto = 22;
        mejor = new ArrayList<>();
        }
    public abstract void buscar(Point p);
    public abstract void calcular(Point p);
    protected abstract boolean rutear(ArrayList<Point> ruta, Point p, Point aux, boolean res);
    protected boolean esValido(int x, int y) {
        return (x >= 0 && x < mapa[0].length) && (y >= 0 && y < mapa.length) && mapa[y][x] != 0;
    }
    protected boolean enciclado(Point p, ArrayList<Point> puntos) {
        boolean res = false;
        for (Point a : puntos) {
            if (a.equals(p)) {
                res = true;
                break;
            }
        }
        return res;
    }
    public boolean capturo(Point p) {
        return pos.equals(p);
    }

    public void setPos(int x, int y) {
        pos.x = x;
        pos.y = y;
    }

    public void setPos(Point a) {
        pos = a;
    }

    public Point getPos() {
        return pos;
    }

    public void dibujar(Graphics2D g2d) {
        g2d.setColor(color);
        g2d.fillRect((int) pos.getX() * 22, (int) pos.getY() * 22, ancho, alto);
        for (Point a: mejor
             ) {
            g2d.fillRect((int) a.getX() * 22+8, (int) a.getY() * 22+8, 5, 5);
        }

    }
}
