import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.awt.Point;
import java.util.Arrays;

public class FantasmaOptimo extends Fantasma {
    public FantasmaOptimo(int posX, int posY, int velocidad, Color color, int[][] mapa) {
        super(posX, posY, velocidad,color,mapa);
    }
    public void buscar(Point p) {

        if (!mejor.isEmpty() && mejor.size()>1) {
            pos = mejor.remove(1);
            //calcular(p);

        } /*else {
            //pos = mejor.remove(0);
            calcular(p);
        }*/
        calcular(p);
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
    }

    @Override
    public boolean rutear(ArrayList<Point> ruta, Point p, Point aux, boolean res) {
        double mejorDistancia = Double.MAX_VALUE;
        double distancia1=calcularDistancia((int)p.getX(),(int)p.getY(),(int)aux.getX() + 1,(int)aux.getY(),ruta);
        double distancia2=calcularDistancia((int)p.getX(),(int)p.getY(),(int)aux.getX() ,(int)aux.getY()+1,ruta);
        double distancia3=calcularDistancia((int)p.getX(),(int)p.getY(),(int)aux.getX() -1,(int)aux.getY(),ruta);
        double distancia4=calcularDistancia((int)p.getX(),(int)p.getY(),(int)aux.getX() ,(int)aux.getY()-1,ruta);
        //int [] ordenado= {distancia1,distancia2,distancia3,distancia4};
        //Arrays.sort(ordenado);
        int nuevox=(int)aux.getX();
        int nuevoy=(int)aux.getY();
        if (!p.equals(aux)) {
            //if (mejor.size() == 0 || mejor.size() >= ruta.size()) {
                if(distancia1<mejorDistancia) {
                    nuevox = (int) aux.getX() + 1;
                    nuevoy = (int) aux.getY();
                    mejorDistancia=distancia1;
                }
                if(distancia2<mejorDistancia) {
                    nuevox = (int) aux.getX();
                    nuevoy = (int) aux.getY()+1;
                    mejorDistancia=distancia2;
                }
                if(distancia3<mejorDistancia) {
                    nuevox = (int) aux.getX() - 1;
                    nuevoy = (int) aux.getY();
                    mejorDistancia=distancia3;
                }
                if(distancia4<mejorDistancia){
                    nuevox = (int) aux.getX();
                    nuevoy = (int) aux.getY()-1;
                    mejorDistancia=distancia4;
                }
                if (mejorDistancia!=Double.MAX_VALUE) {
                    ruta.add(new Point(nuevox, nuevoy));
                    res = rutear(ruta, p, new Point(nuevox, nuevoy), res);
                    //ruta.remove(ruta.size() - 1);
                }

            //}
        } else {
            res = true;
            //if (mejor.size() == 0 || mejor.size() > ruta.size()) {
                mejor = new ArrayList<>(ruta);
     
            //}*/
        }

        return mejor.size() > 0 ? true : false;
    }
    /*public void setPos(Point a) {
        pos = a;
        mejor.clear();
    }*/
}
