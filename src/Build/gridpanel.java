//------------imports------------//
package Build;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
//-------------------------------//




public class gridpanel extends JPanel implements ActionListener, KeyListener{
//------------variables------------//
    public int[][] grid;
    private int squareSize;
    double degrees;
    float radian;

    static double VelX;
    static double VelY;

    static int pdx;
    static int pdy;
    int width = 10;
    int height = 10;
    int vel;
    int px;
    int py;
    double pi = 3.14159265359;
    double pi2 = Math.PI/2;
    double pi3 = 3 * Math.PI/2;
    private double speed;
    private int squareCenterX = 200;
    private int squareCenterY = 200;
    private static double angle = 0;
    static int pa = (int) angle;
    @SuppressWarnings("unused")
    private static double maxMovementSpeed = 1;
    private double minSpeed;
    private double maxSpeed;
    static double movementSpeed = Math.sqrt(VelX*VelX + VelY*VelY);

    
    
//------------timer------------//

    Timer tm = new Timer(5, this);

//----------------------------//

    int x;
    int y;
    
//---------------------------------//


//------------map definitions------------//
    public gridpanel(int[][] grid, int squareSize) {
        this.grid = grid;
        this.squareSize = squareSize;
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        tm.start();
    }
//---------------------------------------//



//------------squares------------//
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        Graphics2D g3 = (Graphics2D) g;

        for (int i =  0; i < grid.length; i++) {
            for (int j =  0; j < grid[i].length; j++) {
                if (grid[i][j] ==  1) {
                    g2d.setColor(Color.BLACK);
                } else {
                    g2d.setColor(Color.WHITE);
                }
                g2d.fillRect(j * squareSize, i * squareSize, squareSize, squareSize);
            
            }
        }
        
        AffineTransform transform = new AffineTransform();
        transform.rotate(angle, squareCenterX, squareCenterY);

        AffineTransform oldTransform = g3.getTransform();

        g3.transform(transform);

        g3.setColor(Color.GRAY);
        g3.fillRect(squareCenterX - 8, squareCenterY - 8, 16, 16); 

        g3.setTransform(oldTransform);

        angle += speed;
   
    }
//-------------------------------//


//------------angle speed check, use later------------//
    @SuppressWarnings("unused")
    private double speed() {
        if (angle < minSpeed) return minSpeed;
        if (angle > maxSpeed) return maxSpeed;
        return angle;
    }
//---------------------------------------------------//


//------------action and repaint set up------------//
    public void actionPerformed(ActionEvent e){


        squareCenterX = (int) (squareCenterX + VelX);
        squareCenterY = (int) (squareCenterY + VelY);

        repaint();
    }
//-------------------------------------------------//


//------------keybinds !!!------------//    
    @Override
    public void keyTyped(KeyEvent e) {}



    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
            VelX += Math.sin(angle);
            VelY -= Math.cos(angle);
            break;
        case KeyEvent.VK_S:
            VelX -= Math.sin(angle);
            VelY += Math.cos(angle);
            break;
        case KeyEvent.VK_A:
            VelX -= Math.cos(angle);
            VelY -= Math.sin(angle);
            break;
        case KeyEvent.VK_D:
            VelX += Math.cos(angle);
            VelY += Math.sin(angle);
            break;
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) speed = -0.1;
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) speed = 0.1;

    }



    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyChar()) {
            case 'a':   
                VelX = 0;
                VelY = 0;

            break;
            
            case 'w':
                VelX = 0;
                VelY = 0;

            break;

            case 'd':
                VelX = 0;
                VelY = 0;

            break;

            case 's':
                VelX = 0;
                VelY = 0;

            break;
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) speed = 0;
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) speed = 0;
    }
//------------------------------------//    


//------------raycaster-----------//
public void raycaster(){
    int r, mx, my, mp, dof;
    float rx = x, ry = y, ra, xo = 0, yo = 0;

    ra = (float) angle;

    for(r = 0; r<1; r++){
        dof = 0;
        float aTan = (float) (-1/Math.tan(ra));

        if(ra>Math.PI){
            ry = (float) (( ( (int) py >>6 ) >> 6) - 0.0001); 
            rx = (py-ry) * aTan + x; 
            yo = -64; 
            xo = yo * aTan;  
        }
        
        if(ra<Math.PI){
            ry = (float) (( ( (int) py >>6 ) >> 6) + 64); 
            rx = (py-ry) * aTan + x; 
            yo = 64; 
            xo = yo * aTan;  
        }

        if(ra==0 || ra==Math.PI){
            rx = px; 
            ry=py; 
            dof = 8;   
        }

        while(dof < 8){
            mx = (int) (rx) >> 6; 
            my = (int) (ry) >> 6; 
            mp = my * x + mx;
            if(mp < x * y && grid[my][mx] == 1) {
                dof = 8;
            } else {
                rx += xo;
                ry = yo; 
                dof += 1; 
            }
        }


        dof = 0;
        float nTan = (float) (-1/Math.tan(ra));

        if(ra > pi2 && ra < pi3){
            rx = (float) (( ( (int) px >>6 ) >> 6) - 0.0001); 
            ry = (px-rx) * nTan + y; 
            yo = -64; 
            xo = yo * nTan;  
        }
        
        if(ra < pi2 || ra > pi3){
            rx = (float) (( ( (int) px >>6 ) >> 6) + 64); 
            ry = (px-rx) * nTan + y; 
            yo = 64; 
            xo = yo * nTan;  
        }

        if(ra==0 || ra==Math.PI){
            rx = px; 
            ry=py; 
            dof = 8;   
        }

        while(dof < 8){
            mx = (int) (rx) >> 6; 
            my = (int) (ry) >> 6; 
            mp = my * x + mx;
            if(mp < x * y && grid[my][mx] == 1) {
                dof = 8;
            } else {
                rx += xo;
                ry = yo; 
                dof += 1; 
            }
        }
    }
}
//-------------------------------//


//------------map and frame setup------------//
    public static void main(String[] args) {
        int[][] grid = {
            {1,  1,  1,  1,  1,  1,  1,  1},
            {1,  0,  1,  0,  0,  0,  0,  1},
            {1,  0,  1,  0,  0,  0,  0,  1},
            {1,  0,  1,  0,  0,  0,  0,  1},
            {1,  0,  1,  0,  0,  1,  0,  1},
            {1,  0,  0,  0,  0,  0,  0,  1},
            {1,  0,  0,  0,  0,  0,  0,  1},
            {1,  1,  1,  1,  1,  1,  1,  1},
        };

        JFrame frame = new JFrame("2D Grid");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1280,  570);

        gridpanel gridPanel = new gridpanel(grid,  64);
        frame.add(gridPanel);


        frame.setVisible(true);


    }
}
//----------------------------------------//