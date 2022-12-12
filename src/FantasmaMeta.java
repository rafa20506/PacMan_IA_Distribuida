import java.awt.*;
import java.util.ArrayList;

public class FantasmaMeta extends Fantasma{
    public FantasmaMeta(int posX, int posY, int velocidad, Color color, int[][] mapa) {
        super(posX, posY, velocidad,color,mapa);
    }
    public void buscar(Point p) {

        if (!mejor.isEmpty()) {
            pos = mejor.remove(0);
        } else {
            calcular(p);
            pos = mejor.remove(0);
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
    protected boolean rutear(ArrayList<Point> ruta, Point p, Point aux, boolean res) {
        if (!p.equals(aux)) {
            if (mejor.size() == 0) {
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
            mejor = new ArrayList<>(ruta);
            System.out.println(ruta.size());//deben apuntar a diferentes direcciones de memoria
        }
        return res;
    }
}
