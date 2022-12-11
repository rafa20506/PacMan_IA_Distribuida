import java.awt.Color;
import java.awt.Event;
import java.awt.Font;
import java.awt.FontMetrics;
import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Tablero extends JPanel implements ActionListener {

    private final int[][] tabla;

    private final Timer timer;
    private int posPacAcX, posPacAcY, teclaX, teclaY, antX, antY;
    private int numVidas, direccion;
    private boolean inicio, murio;
    private final PacMan pacman;
    private final ArrayList<Fantasma> fantasmas;
    private Clip Minicio,pacmanMueve,pacmanMuere;
    public Tablero(PacMan pacman, ArrayList<Fantasma> fantasmas,int [][]mapa) {
        tabla=mapa;
        this.setBackground(Color.orange);
        posPacAcX = pacman.getPos().x;
        posPacAcY = pacman.getPos().y;
        teclaX = 0;
        teclaY = 0;
        antX = 0;
        antY = 0;
        numVidas = 3;
        direccion = 2;
        inicio = false;
        murio = false;
        this.pacman = pacman;
        this.fantasmas=fantasmas;

        timer = new Timer(400, this);
        timer.start();
        addKeyListener(new Teclado());
        setFocusable(true);
        setDoubleBuffered(true);

        try {
            Minicio = AudioSystem.getClip();
            pacmanMueve = AudioSystem.getClip();
            pacmanMuere = AudioSystem.getClip();
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("sounds/pacman_beginning.wav").getAbsoluteFile());
            Minicio.open(audioInputStream);
            audioInputStream = AudioSystem.getAudioInputStream(new File("sounds/pacman_chomp.wav").getAbsoluteFile());
            pacmanMueve.open(audioInputStream);
            audioInputStream = AudioSystem.getAudioInputStream(new File("sounds/pacman_death.wav").getAbsoluteFile());
            pacmanMuere.open(audioInputStream);
            Minicio.loop(Integer.MAX_VALUE);
        } catch (IOException | LineUnavailableException | UnsupportedAudioFileException ex) {
            JOptionPane.showMessageDialog(null, "Error en audio:\n" + ex.getMessage());
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        dibujar(g2d);

    }

    private void dibujarTablero(Graphics2D g2) {
        for (int i = 0; i < tabla.length; i++) {
            for (int j = 0; j < tabla[i].length; j++) {
                if (tabla[i][j] == 1) {
                    g2.setColor(Color.black);
                    g2.fillRect(j * 22, i * 22, 22, 22);
                    g2.setColor(Color.black);
                    g2.drawRect(j * 22, i * 22, 22, 22);
                } else {
                    g2.setColor(Color.gray);
                    g2.fillRect(j * 22, i * 22, 22, 22);
                    g2.setColor(Color.gray);
                    g2.drawRect(j * 22, i * 22, 22, 22);

                }
            }
        }
    }

    private void pantallaInicio(Graphics2D g2d) {

        g2d.setColor(new Color(0, 32, 48));
        g2d.fillRect(50, 380 / 2 - 30, 380 - 100, 50);
        g2d.setColor(Color.white);
        g2d.drawRect(50, 380 / 2 - 30, 380 - 100, 50);

        String s = "Presiona s para empezar.";
        Font small = new Font("Helvetica", Font.BOLD, 15);
        FontMetrics metr = this.getFontMetrics(small);

        g2d.setColor(Color.white);
        g2d.setFont(small);
        g2d.drawString(s, (390 - metr.stringWidth(s)) / 2, 380 / 2);
    }

    private void iniciarJuego(Graphics2D g2d) {
        dibujarTablero(g2d);
        dibujarPacMan(g2d);
        dibujarFantasmas(g2d);
        Toolkit.getDefaultToolkit().sync();
        g2d.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }

    @Override
    public void addNotify() {
        super.addNotify();
        //iniciarJuego();
    }

    private void dibujar(Graphics2D g2d){
        if (inicio) {
            iniciarJuego(g2d);
        } else {
            pantallaInicio(g2d);
        }
    }

    private void dibujarPacMan(Graphics2D g2d) {
        int aux = posPacAcX + teclaX;
        int auy = posPacAcY + teclaY;
        if (esValido(aux, auy)) {

            posPacAcX += teclaX;
            posPacAcY += teclaY;
            antX = teclaX;
            antY = teclaY;
        } else {

            if (esValido(posPacAcX + antX, posPacAcY + antY)) {
                posPacAcX += antX;
                posPacAcY += antY;
                //teclaX = antX;
                //teclaY = antY;

            }
        }

        pacman.setPos(new Point(posPacAcX, posPacAcY));
        pacmanMueve.loop(Integer.MAX_VALUE);
        pacman.dibujar(g2d);
    }

    private void dibujarFantasmas(Graphics2D g2d) {
        Point posActualPacman = new Point(posPacAcX, posPacAcY);
        for (Fantasma f: fantasmas) {
            if (f.capturo(posActualPacman)) {
                murio = true;
                pacmanMueve.stop();
                pacmanMuere.start();

                numVidas--;
                timer.stop();

            } else {
                f.buscar(posActualPacman);
                f.dibujar(g2d);

            }
        }


    }

    private boolean esValido(int aux, int auy) {
        return (aux >= 0 && aux < tabla[0].length) && (auy >= 0 && auy < tabla.length) && tabla[auy][aux] != 0;
    }

    class Teclado extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {

            int key = e.getKeyCode();

            if (inicio) {
                if (key == KeyEvent.VK_LEFT) {
                    direccion = 4;
                    teclaX = -1;
                    teclaY = 0;
                } else if (key == KeyEvent.VK_RIGHT) {
                    direccion = 2;
                    teclaX = 1;
                    teclaY = 0;
                } else if (key == KeyEvent.VK_UP) {
                    direccion = 1;
                    teclaX = 0;
                    teclaY = -1;
                } else if (key == KeyEvent.VK_DOWN) {
                    direccion = 3;
                    teclaX = 0;
                    teclaY = 1;
                } else if (key == KeyEvent.VK_ESCAPE && timer.isRunning()) {
                    inicio = false;
                } else if (key == KeyEvent.VK_PAUSE) {
                    if (timer.isRunning()) {
                        timer.stop();
                    } else {
                        timer.start();
                    }
                }
            } else {
                if (key == 's' || key == 'S') {
                    inicio = true;
                    Minicio.stop();
                    //iniciarJuego();
                }
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {

            int key = e.getKeyCode();

            if (key == Event.LEFT || key == Event.RIGHT
                    || key == Event.UP || key == Event.DOWN) {
                teclaX = 0;
                teclaY = 0;
            }
        }

    }
}
