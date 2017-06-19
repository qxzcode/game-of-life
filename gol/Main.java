package gol;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Created by Quinn.Tucker18 on 6/18/2017.
 *
 * The main class; sets up the window, handles input, and draws the grid
 */
public class Main extends Component {
    
    private static Main instance;
    
    public static void main(String[] args) {
        // configure the window
        JFrame window = new JFrame("Game of Life");
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setResizable(false);
        
        // create the content and add it to the window
        instance = new Main();
        window.add(instance);
        
        // show the window and start
        window.pack();
        Point center = GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint();
        window.setLocation(center.x - window.getWidth()/2, center.y - window.getHeight()/2);
        window.setVisible(true);
    }
    
    
    private Grid grid;
    private int cellPixels;
    private boolean pen;
    private boolean autoPlay = false;
    
    private Main() {
        // initialize the simulation
        grid = new Grid(true);
        
        // get focus for key events
        setFocusable(true);
        requestFocus();
        
        // add event listeners
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_SPACE:
                        // step the simulation
                        grid.step();
                        repaint();
                        break;
                    case KeyEvent.VK_DELETE:
                    case KeyEvent.VK_BACK_SPACE:
                        // clear the grid
                        grid = new Grid(false);
                        repaint();
                        break;
                    case KeyEvent.VK_R:
                        // fill the grid with randomness
                        grid = new Grid(true);
                        repaint();
                        break;
                    case KeyEvent.VK_P:
                        // toggle autoPlay
                        autoPlay = !autoPlay;
                        if (autoPlay) repaint();
                        break;
                    case KeyEvent.VK_C:
                        // switch cellular automaton rules
                        Grid.nextRule();
                        break;
                }
            }
        });
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) {
                    int x = e.getX()/cellPixels;
                    int y = e.getY()/cellPixels;
                    pen = !grid.getCell(x, y);
                    grid.setCell(x, y, pen);
                    repaint();
                }
            }
        });
        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) {
                    int x = e.getX()/cellPixels;
                    int y = e.getY()/cellPixels;
                    grid.setCell(x, y, pen);
                    repaint();
                }
            }
        });
        
        // set the Component's size
        DisplayMode dm = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode();
        int screenSize = Math.min(dm.getWidth(), dm.getHeight());
        cellPixels = screenSize * 9/10 / Grid.SIZE;
        setPreferredSize(new Dimension(Grid.SIZE*cellPixels, Grid.SIZE*cellPixels));
    }
    
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        
        // fill with white
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, getWidth(), getHeight());
        
        // draw black cells
        g.setColor(Color.BLACK);
        for (int x = 0; x < Grid.SIZE; x++) {
            for (int y = 0; y < Grid.SIZE; y++) {
                if (grid.getCell(x, y))
                    g.fillRect(x*cellPixels, y*cellPixels, cellPixels, cellPixels);
            }
        }
        
        // step and repeat (if autoPlay)
        if (autoPlay) {
            grid.step();
            repaint();
        }
    }
    
}
