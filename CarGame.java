
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class CarGame extends JPanel implements Runnable, KeyListener {

    private static final long serialVersionUID = 1L;
    private JFrame frame;
    private boolean running = false;
    private Thread thread;
    private int score = 0;
    private int carX = 175;
    private int carY = 600;
    private int carSpeed = 5;
    private int roadX = 100;
    private int roadY = 0;
    private int roadSpeed = 10;
    private int obstacleX;
    private int obstacleY = -100;
    private int obstacleSpeed = 10;
    private int obstacleWidth = 50;
    private int obstacleHeight = 50;
    private Random random = new Random();

    public CarGame() {
        frame = new JFrame("Montor");
        frame.setSize(400, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.add(this);
        frame.addKeyListener(this);
        frame.setVisible(true);
    }

    public void start() {
        running = true;
        thread = new Thread(this);
        thread.start();
    }

    public void stop() {
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        while (running) {
            update();
            repaint();
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void update() {
        score++;
        roadY += roadSpeed;
        if (roadY > 800) {
            roadY = 0;
        }
        obstacleY += obstacleSpeed;
        if (obstacleY > 800) {
            obstacleX = random.nextInt(300) + 50;
            obstacleY = -100;
        }
        if (carX < obstacleX + obstacleWidth && carX + 50 > obstacleX && carY < obstacleY + obstacleHeight && carY + 100 > obstacleY) {
            stop();
        }
        carX += carSpeed;
        if (carX < 0) {
            carX = 0;
        }
        if (carX > 450) {
            carX = 450;
        }
    }

    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, 400, 700);
        g.setColor(Color.WHITE);
        for (int i = 0; i < 6; i++) {
            g.fillRect(roadX, roadY + (i * 100), 25, 50);
        }
        g.setColor(Color.BLUE);
        g.fillRect(obstacleX, obstacleY, obstacleWidth, obstacleHeight);
        g.setColor(Color.GREEN);
        g.fillRect(carX, carY, 50, 100);
        g.setColor(Color.WHITE);
        g.drawString("Score: " + score, 30, 40);
    }

    public static void main(String[] args) {
        CarGame game = new CarGame();
        game.start();
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            carSpeed = -5;
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            carSpeed = 5;
        }
    }
    
    public void keyReleased(KeyEvent e) {
        carSpeed = 0;
    }
    
    public void keyTyped(KeyEvent e) {
    }
}