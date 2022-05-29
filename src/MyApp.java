import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Set;

import static util.utils.getRandomPuzzle;

public class MyApp extends JFrame {

    public static JLabel[][] generatedPuzzle;
    public static int N = 3;
    public static int x = N - 1;
    public static int y = N - 1;

    static {
        try {
            generatedPuzzle = getRandomPuzzle(N);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static GridLayout layout = new GridLayout(N, N);
    public static JFrame window = new JFrame("JPuzzle");
    public static JFrame mainMenu = new JFrame("Main-menu");
    public static JFrame popup = new JFrame("");

    public static void main(String[] args){

        mainMenu.setSize(600, 600);
        VerticalFlowLayout vfl = new VerticalFlowLayout();
        JPanel jPanel = new JPanel();
        vfl.setHGap(2);
        vfl.setVGap(20);

        JLabel jLabel = new JLabel("Welcome to JPuzzle");
        jLabel.setFont(new Font("Arial", Font.PLAIN, 30));

        JButton jButton1 = new JButton("3x3");
        jButton1.setFont(new Font("Arial", Font.PLAIN, 15));
        jButton1.addActionListener(e -> {
            N = 3;
            mainMenu.setVisible(false);
            window.setVisible(true);
        });

        JButton jButton2 = new JButton("4x4");
        jButton2.setFont(new Font("Arial", Font.PLAIN, 15));
        jButton2.addActionListener(e -> {
            N = 4;
            mainMenu.setVisible(false);
            window.setVisible(true);
        });

        jPanel.add(jLabel);
        jPanel.add(jButton1);
        jPanel.add(jButton2);

        mainMenu.getContentPane().add(BorderLayout.CENTER, jPanel);

        mainMenu.setResizable(false);
        mainMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainMenu.pack();
        mainMenu.setVisible(true);

        window.setLayout(layout);
        populateGridLayout(generatedPuzzle, window);
        window.addKeyListener(new CustomKeyListener());
        window.setResizable(false);

        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.pack();
//        window.setVisible(true);
    }



    public static void populateGridLayout(JLabel[][] grid, JFrame window){
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++) {
                window.add(grid[row][col]).setLocation(row, col);
            }
        }
    }

    static class CustomKeyListener implements KeyListener {
        public void keyTyped(KeyEvent e) {
        }

        public void keyPressed(KeyEvent e) {
            int keyCode = e.getKeyCode();
            Point p = new Point(generatedPuzzle[x][y].getX(), generatedPuzzle[x][y].getY());

            switch (keyCode) {
                case KeyEvent.VK_UP:
                    if(x >= 0 && x < 4){
                        JLabel j = generatedPuzzle[x - 1][y];
                        Point t = new Point(j.getX(), j.getY());
                        generatedPuzzle[x][y].setLocation(t.x, t.y);
                        j.setLocation(p.x, p.y);
                        JLabel temp = generatedPuzzle[x][y];
                        generatedPuzzle[x][y]= j;
                        generatedPuzzle[x - 1][y] = temp;
                        System.out.println(x + " " + y);
                        x--;
                    }
                    break;
                case KeyEvent.VK_DOWN:
                    if(x >= 0 && x < 4){
                        JLabel j = generatedPuzzle[x + 1][y];
                        Point t = new Point(j.getX(), j.getY());
                        generatedPuzzle[x][y].setLocation(t.x, t.y);
                        j.setLocation(p.x, p.y);
                        JLabel temp = generatedPuzzle[x][y];
                        generatedPuzzle[x][y]= j;
                        generatedPuzzle[x + 1][y] = temp;
                        System.out.println(x + " " + y);
                        x++;
                    }
                    break;
                case KeyEvent.VK_LEFT:
                    if(y >= 0 && y < 4){
                        JLabel j = generatedPuzzle[x][y - 1];
                        Point t = new Point(j.getX(), j.getY());
                        generatedPuzzle[x][y].setLocation(t.x, t.y);
                        j.setLocation(p.x, p.y);
                        JLabel temp = generatedPuzzle[x][y];
                        generatedPuzzle[x][y]= j;
                        generatedPuzzle[x][y - 1] = temp;
                        System.out.println(x + " " + y);
                        y--;
                    }
                    break;
                case KeyEvent.VK_RIGHT:
                    if(y >= 0 && y < 4){
                        JLabel j = generatedPuzzle[x][y + 1];
                        Point t = new Point(j.getX(), j.getY());
                        generatedPuzzle[x][y].setLocation(t.x, t.y);
                        j.setLocation(p.x, p.y);
                        JLabel temp = generatedPuzzle[x][y];
                        generatedPuzzle[x][y]= j;
                        generatedPuzzle[x][y + 1] = temp;
                        System.out.println(x + " " + y);
                        y++;
                    }
                    break;
            }
        }

        public void keyReleased(KeyEvent e) {
        }
    }

    static class VerticalFlowLayout implements LayoutManager2 {

        final private Set<Component> components = new LinkedHashSet();
        private int hgap = 0;
        private int vgap = 0;

        public void setHGap(int hgap) { this.hgap = hgap; }
        public void setVGap(int vgap) { this.vgap = vgap; }

        @Override public void addLayoutComponent(Component comp, Object constraints) {
            this.components.add(comp);
        }

        /* these 3 methods need to be overridden properly */
        @Override public float getLayoutAlignmentX(Container target) {
            // TODO Auto-generated method stub
            return 0;
        }

        @Override public float getLayoutAlignmentY(Container target) {
            // TODO Auto-generated method stub
            return 0;
        }

        @Override public void invalidateLayout(Container target) {
            // TODO Auto-generated method stub

        }


        @Override public void addLayoutComponent(String name, Component comp) {
            this.components.add(comp);
        }

        @Override public void layoutContainer(Container parent) {
            int x = 0;
            int y = 0;
            int columnWidth = 0;
            for (Component c : this.components)
            {
                if (c.isVisible())
                {
                    Dimension d = c.getPreferredSize();
                    columnWidth = Math.max(columnWidth, d.width);
                    if (y+d.height > parent.getHeight())
                    {
                        x += columnWidth + this.hgap;
                        y = 0;
                    }
                    c.setBounds(x, y, d.width, d.height);
                    y += d.height + this.vgap;
                }
            }
        }

        /* these 3 methods need to be overridden properly */
        @Override public Dimension minimumLayoutSize(Container parent) {
            return new Dimension(0,0);
        }

        @Override public Dimension preferredLayoutSize(Container parent) {
            return new Dimension(200,200);
        }

        @Override public Dimension maximumLayoutSize(Container target) {
            return new Dimension(600,600);
        }


        @Override public void removeLayoutComponent(Component comp) {
            this.components.remove(comp);
        }
    }
}