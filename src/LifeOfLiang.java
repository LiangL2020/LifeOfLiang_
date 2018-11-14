import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Created by lianglu on 11/29/17.
 */
public class LifeOfLiang extends JPanel{

    private Cell[][] loc;
    private Timer timer;
    private int count = 0;
    private int speed;

    /*
    The Rules
        For a space that is “populated”:
        Each cell with ONE or NO neighbors dies, as if by loneliness.
        Each cell with FOUR or MORE neighbors dies, as if by overpopulation.
        Each cell with TWO or THREE neighbors survives.
        For a space that is “empty” or “unpopulated”
        Each cell with three neighbors becomes populated.
     */

    public LifeOfLiang(int w, int h){
        setSize(w, h);
        loc = new Cell[20][20];
        for (int r = 0; r < loc.length; r++) {
            for (int c = 0; c < loc[0].length; c++) {
                loc[r][c] = new Cell(r, c, getWidth()/loc.length);
            }
        }
//        loc[5][3].spawn(); //for testing
//        loc[5][2].spawn();
//        loc[3][3].spawn();
//        loc[4][2].spawn();

//        add(start);

        System.out.println(" The Rules\n" +
                "        For a space that is “populated”:\n" +
                "        Each cell with ONE or NO neighbors dies, as if by loneliness.\n" +
                "        Each cell with FOUR or MORE neighbors dies, as if by overpopulation.\n" +
                "        Each cell with TWO or THREE neighbors survives.\n" +
                "        For a space that is “empty” or “unpopulated”\n" +
                "        Each cell with three neighbors becomes populated.");

        System.out.println(" How To Play\n" +
                "        Click on the the panel to spawn life. \n" +
                "        To Start the game, press SPACE BAR; to pause the game, press SPACE BAR again.\n" +
                "        Click 'Clear' to kill all of the cells. \n" +
                "        Press the UP and DOWN arrows to make the speed faster and slower \n" +
                "        Click 'Glider' or 'Exploder' for a preset shape. \n");

        speed = 1000;
        timer = new Timer(speed, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean[][] nextGen = new boolean [loc.length][loc[0].length];
                for (int r = 0; r < nextGen.length; r++) {
                    for (int c = 0; c < nextGen[0].length; c++) {
                        if(loc[r][c].numNeighbors(loc) == 3 || loc[r][c].numNeighbors(loc) == 2 && loc[r][c].getIsAlive() == true)
                            nextGen[r][c] = true;
                        if(loc[r][c].numNeighbors(loc) == 3 && loc[r][c].getIsAlive() == false)
                            nextGen[r][c] = true;
                        if(loc[r][c].numNeighbors(loc) >= 4 && loc[r][c].getIsAlive() == true)
                            nextGen[r][c] = false;
                        if(loc[r][c].numNeighbors(loc) <= 1 && loc[r][c].getIsAlive() == true)
                            nextGen[r][c] = false;
                    }
                }

                for (int r = 0; r < nextGen.length; r++) {
                    for (int c = 0; c < nextGen[0].length; c++) {
                        if(nextGen[r][c] == true)
                            loc[r][c].spawn();
                        else
                            loc[r][c].kill();
                    }
                }
                repaint();
            }
        });

//        timer.start();

        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_SPACE && count == 0) { //vk = virtual keyboard
                    timer.start();
                    count ++;
                }else if(e.getKeyCode() == KeyEvent.VK_SPACE && count == 1) {
                    timer.stop();
                    count --;
                }

                timer.setDelay(speed);
                if(e.getKeyCode() == KeyEvent.VK_UP && speed > 199)
                    speed -= 100; //faster
                else if(e.getKeyCode() == KeyEvent.VK_DOWN && speed < 2000)
                    speed += 100; //slower
                else
                    speed = speed;
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

        JButton clear = new JButton("Clear");
        clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                for (int r = 0; r < loc.length; r++) {
                    for (int c = 0; c < loc[0].length; c++) {
                        loc[r][c].kill();
                    }
                }
                repaint();
                grabFocus();
            }
        });
        add(clear);

        JButton glider = new JButton("Glider");
        glider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int r = 0; r < loc.length; r++) {
                    for (int c = 0; c < loc[0].length; c++) {
                        loc[2][2].spawn();
                        loc[3][3].spawn();
                        loc[4][3].spawn();
                        loc[4][2].spawn();
                        loc[4][1].spawn();
                    }
                }
                repaint();
                grabFocus();
            }
        });
        add(glider);

        JButton exploder = new JButton("Exploder");
        exploder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int r = 0; r < loc.length; r++) {
                    for (int c = 0; c < loc[0].length; c++) {
                        loc[2][5].spawn();
                        loc[2][6].spawn();
                        loc[2][7].spawn();
                        loc[2][11].spawn();
                        loc[2][12].spawn();
                        loc[2][13].spawn();

                        loc[7][5].spawn();
                        loc[7][6].spawn();
                        loc[7][7].spawn();
                        loc[7][11].spawn();
                        loc[7][12].spawn();
                        loc[7][13].spawn();

                        loc[9][5].spawn();
                        loc[9][6].spawn();
                        loc[9][7].spawn();
                        loc[9][11].spawn();
                        loc[9][12].spawn();
                        loc[9][13].spawn();

                        loc[14][5].spawn();
                        loc[14][6].spawn();
                        loc[14][7].spawn();
                        loc[14][11].spawn();
                        loc[14][12].spawn();
                        loc[14][13].spawn();

                        loc[4][3].spawn();
                        loc[5][3].spawn();
                        loc[6][3].spawn();
                        loc[10][3].spawn();
                        loc[11][3].spawn();
                        loc[12][3].spawn();

                        loc[4][8].spawn();
                        loc[5][8].spawn();
                        loc[6][8].spawn();
                        loc[10][8].spawn();
                        loc[11][8].spawn();
                        loc[12][8].spawn();

                        loc[4][10].spawn();
                        loc[5][10].spawn();
                        loc[6][10].spawn();
                        loc[10][10].spawn();
                        loc[11][10].spawn();
                        loc[12][10].spawn();

                        loc[4][15].spawn();
                        loc[5][15].spawn();
                        loc[6][15].spawn();
                        loc[10][15].spawn();
                        loc[11][15].spawn();
                        loc[12][15].spawn();
                    }
                }
                repaint();
                grabFocus();
            }

        });
        add(exploder);

        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();

                int w = getWidth()/loc.length;
                int h = getHeight()/loc[0].length;

                int r = y / h;
                int c = x / w;

                if(loc[r][c].getIsAlive() == false)
                    loc[r][c].spawn();
                else
                    loc[r][c].kill();

                repaint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

//        g2.setStroke(new BasicStroke(2));
//        Font font = new Font ("Verdana", Font.PLAIN, 20);
//        g2.setFont(font);
//        g2.drawString("For a space that is “populated”:", 10, 30);
//        g2.drawString("        Each cell with ONE or NO neighbors dies, as if by loneliness.", 10, 50);
//        g2.drawString("        Each cell with FOUR or MORE neighbors dies, as if by overpopulation.", 10, 70);
//        g2.drawString("        Each cell with TWO or THREE neighbors survives.", 10, 90);
//        g2.drawString("        For a space that is “empty” or “unpopulated”", 10, 110);
//        g2.drawString("        Each cell with three neighbors becomes populated.", 10, 130);
//        g2.drawString("For a space that is “populated”:", 10, 150);
//        g2.drawString("        Each cell with three neighbors becomes populated.", 10, 170);
//
//        start.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//
//                repaint();
//            }
//        });

        for (int r = 0; r < loc.length; r++) {
            for (int c = 0; c < loc[0].length; c++) {
                loc[r][c].draw(g2);
            }
        }

//        JButton start = new JButton("Start");
//        start.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//
////                repaint();
//                grabFocus();
//            }
//        });
//        add(start);

    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Life of LIANG");
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        int width = 800;
        int height = 800;
        frame.setPreferredSize(new Dimension(width, height));


        JPanel panel = new LifeOfLiang(width, height);
        panel.setFocusable(true);
        panel.grabFocus();

        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
    }
}
