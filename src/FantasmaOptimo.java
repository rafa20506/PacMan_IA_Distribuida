import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.awt.Point;

public class FantasmaOptimo extends Fantasma {
    public FantasmaOptimo(int posX, int posY, int velocidad, Color color, int[][] mapa) {
        super(posX, posY, velocidad,color,mapa);
    }
    public void buscar(Point p) {
        calcular(p);
        if (!mejor.isEmpty()) {
            pos = mejor.remove(1);
        } else {
            pos = mejor.remove(1);
        }
    }
    @Override
    public void calcular(Point p) {
        int mejorDistancia = Integer.MAX_VALUE;
        ArrayList<Point> ruta = new ArrayList();
        ruta.add(pos);
        mejor = new ArrayList();
        Point aux = pos;
        boolean res = false;
        res = rutear(ruta, p, aux, res);
        System.out.println(res);
    }

    @Override
    public boolean rutear(ArrayList<Point> ruta, Point p, Point aux, boolean res) {

        if (!p.equals(aux)) {
            if (mejor.size() == 0 || mejor.size() >= ruta.size()) {
                if (esValido((int) aux.getX() + 1, (int) aux.getY())
                        && !enciclado(new Point((int) aux.getX() + 1, (int) aux.getY()), ruta)) {
                    ruta.add(new Point((int) aux.getX() + 1, (int) aux.getY()));
                    res = rutear(ruta, p, new Point((int) aux.getX() + 1, (int) aux.getY()), res);
                    ruta.remove(ruta.size() - 1);
                }
                if (esValido((int) aux.getX(), (int) aux.getY() + 1)
                        && !enciclado(new Point((int) aux.getX(), (int) aux.getY() + 1), ruta)) {
                    ruta.add(new Point((int) aux.getX(), (int) aux.getY() + 1));
                    res = rutear(ruta, p, new Point((int) aux.getX(), (int) aux.getY() + 1), res);
                    ruta.remove(ruta.size() - 1);
                }
                if (esValido((int) aux.getX() - 1, (int) aux.getY())
                        && !enciclado(new Point((int) aux.getX() - 1, (int) aux.getY()), ruta)) {
                    ruta.add(new Point((int) aux.getX() - 1, (int) aux.getY()));
                    res = rutear(ruta, p, new Point((int) aux.getX() - 1, (int) aux.getY()), res);
                    ruta.remove(ruta.size() - 1);
                }
                if (esValido((int) aux.getX(), (int) aux.getY() - 1)
                        && !enciclado(new Point((int) aux.getX(), (int) aux.getY() - 1), ruta)) {
                    ruta.add(new Point((int) aux.getX(), (int) aux.getY() - 1));
                    res = rutear(ruta, p, new Point((int) aux.getX(), (int) aux.getY() - 1), res);
                    ruta.remove(ruta.size() - 1);
                }
            }
        } else {
            res = true;
            if (mejor.size() == 0 || mejor.size() > ruta.size()) {
                mejor = new ArrayList<>(ruta);
                System.out.println(ruta.size());//deben apuntar a diferentes direcciones de memoria
            }
        }

        return mejor.size() > 0 ? true : false;
    }
}
