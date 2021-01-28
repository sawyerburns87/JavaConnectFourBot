package connectsrc;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.WindowConstants;
import javax.swing.event.MouseInputAdapter;
import javax.swing.event.MouseInputListener;

public class Drawing extends Board{
    MyPanel panel;

    Drawing(){
        JFrame frame = new JFrame("Connect Four");

        panel = new MyPanel();
        panel.addMouseListener(new MouseInputAdapter(){
            public void mousePressed(MouseEvent e) {
                clicked(e.getX(), e.getY());
            }
        });

        frame.add(panel);
        frame.pack();

        frame.setResizable(false);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    void addCircle(int x, int y, int player){
        Color col = Color.black;
        if(player == 2){
            col = Color.decode("#FFFF00");
        }
        else if(player == 1){
            col = Color.decode("#30a0e6");
        }
        
        panel.newCircle(panel.getGraphics(), x, y, col);
    }

    void clicked(int x, int y){
        super.attemptDrop(x, y);
    }
}

class MyPanel extends JPanel {

    public MyPanel() {
        setBorder(BorderFactory.createLineBorder(Color.black));
        setBackground(Color.decode("#eb1a3d"));
    }

    public Dimension getPreferredSize() {
        return new Dimension(Vals.sizeX, Vals.sizeY);
    }

    public void newCircle(Graphics g, int x, int y, Color c){
        g.setColor(c);
        g.fillOval(x * Vals.diaCircle, (Vals.numHeight - y - 1) * Vals.diaCircle, (int)(Vals.diaCircle * 0.95), (int)(Vals.diaCircle * 0.95));
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);       
        g.setColor(Color.WHITE);
        for(int x = 0; x < Vals.numWidth; x++){
            for(int y = 0; y < Vals.numHeight; y++){
                g.fillOval(x * Vals.diaCircle, y * Vals.diaCircle, (int)(Vals.diaCircle * 0.95), (int)(Vals.diaCircle * 0.95));
            }
        }  
    }  
}