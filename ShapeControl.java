import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import javax.swing.event.*;
/**
 * ShapeControl works as a controller. Allows user to interact with the shapes in
 * DrawingBoard utilizing buttons that represent colors and shapes. Allows user
 * to use the mouse to interact with colors draw on screen and draw colors.
 * 
 * @author Joel Sigo
 * @version 1/23/2013
 */
public class ShapeControl extends JPanel {
    
    // instance variables
    private DrawingBoard board;
    private GraphicView gView;
    private BoardButtonListener buttonListener;
    private BoardButtonListener boardMouseListener;
    private int shapeRef;
    private Color colorRef;
    
    /**
     * Constructs a ShapeControl object. Sets up buttons and overall layout of
     * JFrame.
     * @param db represents DrawingBoard obect or model that will be represented
     */
    public ShapeControl(DrawingBoard db) {
        // capture model
        board = db;
        
        // set border layout
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(800,600));
        
        // create pane with shapes display in the center
        gView = new GraphicView(board);
        add(gView, BorderLayout.CENTER);
        
        // register view with model
        board.addListener(gView);
        
        // create panel with circle, delta, tee buttons and add it to the top
        JButton circ = new JButton("Draw Circle");
        JButton delt = new JButton("Draw Delta");
        JButton te = new JButton("Draw Tee");
        JButton done = new JButton("Done Drawing");
        JPanel buttons = new JPanel();
        buttons.add(circ);
        buttons.add(delt);
        buttons.add(te);
        buttons.add(done);
        buttons.setLayout(new GridLayout(1,3));
        circ.setBackground(Color.lightGray);
        delt.setBackground(Color.lightGray);
        te.setBackground(Color.lightGray);
        done.setBackground(Color.lightGray);
        this.add(buttons, BorderLayout.NORTH);
        
        // create color buttons
        JButton blue = new JButton("Blue");
        JButton cyan = new JButton("Cyan");
        JButton gray = new JButton("Gray");
        JButton lightGray = new JButton("Light Gray");
        JButton darkGray = new JButton("Dark Gray");
        JButton green = new JButton("Green");
        JButton magenta = new JButton("Magenta");
        JButton orange = new JButton("Orange");
        JButton red = new JButton("Red");
        JButton white = new JButton("White");
        JButton pink = new JButton("Pink");
        JButton yellow = new JButton("Yellow");
        JPanel colorButtons = new JPanel();
        colorButtons.add(blue);
        colorButtons.add(cyan);
        colorButtons.add(gray);
        colorButtons.add(lightGray);
        colorButtons.add(darkGray);
        colorButtons.add(green);
        colorButtons.add(magenta);
        colorButtons.add(orange);
        colorButtons.add(red);
        colorButtons.add(white);
        colorButtons.add(pink);
        colorButtons.add(yellow);
        colorButtons.setLayout(new GridLayout(2,6));
        blue.setBackground(Color.BLUE);
        cyan.setBackground(Color.CYAN);
        yellow.setBackground(Color.YELLOW);
        gray.setBackground(Color.GRAY);
        lightGray.setBackground(Color.LIGHT_GRAY);
        darkGray.setBackground(Color.DARK_GRAY);
        green.setBackground(Color.GREEN);
        magenta.setBackground(Color.MAGENTA);
        orange.setBackground(Color.ORANGE);
        red.setBackground(Color.RED);
        white.setBackground(Color.WHITE);
        pink.setBackground(Color.PINK);
        this.add(colorButtons, BorderLayout.SOUTH);
        
        // set up listener for buttons
        buttonListener = new BoardButtonListener(board, this);
        circ.addActionListener(buttonListener);
        delt.addActionListener(buttonListener);
        te.addActionListener(buttonListener);
        done.addActionListener(buttonListener);
        blue.addActionListener(buttonListener);
        cyan.addActionListener(buttonListener);
        yellow.addActionListener(buttonListener);
        gray.addActionListener(buttonListener);
        lightGray.addActionListener(buttonListener);
        darkGray.addActionListener(buttonListener);
        green.addActionListener(buttonListener);
        magenta.addActionListener(buttonListener);
        orange.addActionListener(buttonListener);
        red.addActionListener(buttonListener);
        white.addActionListener(buttonListener);
        pink.addActionListener(buttonListener);
        
        // set up listener for mouse
        boardMouseListener = new BoardButtonListener(board, this);
        this.addMouseListener(boardMouseListener);
    }
    
    /**
     * sets ShapeRef to desired int value
     * @param x represents desired int value of ShapeRef
     */
    public void setShapeRef(int x) {
        shapeRef = x;
    }
    
    /**
     * returns int value of ShapeRef
     * @return int value of ShapeRef
     */
    public int getShapeRef() {
        return shapeRef;
    }
    
    /**
     * sets ColorRef to desired Color
     * @param x represents desired Color of ColorRef
     */
    public void setColorRef(Color x) {
        colorRef = x;
    }
    
    /**
     * returns Color of ColorRef
     * @return Color of ColorRef
     */
    public Color getColorRef() {
        return colorRef;
    }
}

//************************************************************************************

/**
 * BoardButtonListener provides actions to be performed when user clicks on 
 * certain buttons, or interacts with shapes with mouse.
 * 
 * @author Joel Sigo
 * @version 1/26/2013
 */
class BoardButtonListener implements ActionListener, MouseListener {
    // instance variables
    private DrawingBoard board;
    private int initX, initY, finalX, finalY;
    private ShapeControl con;
    
    // class constant
    private static int cal = -27; // THIS IS TO CALIBRATE Y-AXIS OF MOUSE POINTER
    
    /**
     * constructs BoardButtonListener object
     * @param db represents DrawingBoard that will be queried
     * @param sc represents ShapeControl that will use BoardButtonListener
     */
    public BoardButtonListener(DrawingBoard db, ShapeControl sc) {
        board = db;
        con = sc;
    }
    
    /**
     * defines action to be performed when user clicks on a button
     * @param e represents ActionEvent ie a button press
     */
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Draw Circle")) {
            con.setShapeRef(1); // 1 is reference for circle
        } else if (e.getActionCommand().equals("Draw Delta")) {
            con.setShapeRef(2); // 2 is reference for delta
        } else if (e.getActionCommand().equals("Draw Tee")) {
            con.setShapeRef(3); // 3 is reference for tee
        } else if (e.getActionCommand().equals("Done Drawing")) {
            con.setShapeRef(0); // 4 is reference for done drawing
        } else if (e.getActionCommand().equals("Blue")) {
            con.setColorRef(Color.BLUE);
        } else if (e.getActionCommand().equals("Cyan")) {
            con.setColorRef(Color.CYAN);
        } else if (e.getActionCommand().equals("Gray")) {
            con.setColorRef(Color.GRAY);
        } else if (e.getActionCommand().equals("Light Gray")) {
            con.setColorRef(Color.LIGHT_GRAY);
        } else if (e.getActionCommand().equals("Dark Gray")) {
            con.setColorRef(Color.DARK_GRAY);
        } else if (e.getActionCommand().equals("Green")) {
            con.setColorRef(Color.GREEN);
        } else if (e.getActionCommand().equals("Magenta")) {
            con.setColorRef(Color.MAGENTA);
        } else if (e.getActionCommand().equals("Orange")) {
            con.setColorRef(Color.ORANGE);
        } else if (e.getActionCommand().equals("Red")) {
            con.setColorRef(Color.RED);
        } else if (e.getActionCommand().equals("White")) {
            con.setColorRef(Color.WHITE);
        } else if (e.getActionCommand().equals("Pink")) {
            con.setColorRef(Color.PINK);
        } else if (e.getActionCommand().equals("Yellow")) {
            con.setColorRef(Color.YELLOW);
        }
    }
    
    /**
     * defines action to be performed when user clicks mouse
     * @param e represents a mouse event ie a mouse click
     */
    public void mouseClicked(MouseEvent e) {
        if (con.getShapeRef() == 0) {
            board.selectXY(e.getX(),e.getY() + cal);
        }
    }
    
    /**
     * defines action to be performed when user presses mouse
     * @param e represents a mouse event ie a mouse press
     */
    public void mousePressed(MouseEvent e) {
        initX = e.getX();
        initY = e.getY() + cal;
    }
    
    /**
     * defines action to be performed when user releases mouse
     * @param e represents a mouse event ie a mouse release
     */
    public void mouseReleased(MouseEvent e) {
        finalX = e.getX();
        finalY = e.getY() + cal;
        // determine difference between where user clicked and where user released
        int length = (int)(Math.sqrt(Math.pow(initX - finalX,2) +
            Math.pow(initY - finalY,2)));
        // based of ColorRef (which shape button is selected) draw shapes based off
        // where the user clicked, what color is selected, and where user released
        if (con.getColorRef() != null) { // if a color is selected
            if (con.getShapeRef() == 1 && (initX != finalX && initY != finalY)) {
                board.addShape(new Circle(initX,initY,length,con.getColorRef()));
            } else if (con.getShapeRef() == 2 && (initX != finalX && initY != finalY)) {
                if (length % 2 != 0) {
                    length += 1;
                }
                board.addShape(new Delta(initX,initY,length,con.getColorRef()));
            } else if (con.getShapeRef() == 3 && (initX != finalX && initY != finalY)) {
                int x = (int)(Math.round(length/10.0) * 10); // ROUND TO NEAREST 10
                board.addShape(new Tee(initX,initY,x,con.getColorRef()));
            }
        }
        // if ShapeRef is 0, allows user to click off shapes to deselect all or move
        // shapes around
            if (con.getShapeRef() == 0) { // MOVE SHAPES
            board.selectXY(initX,initY);
            Shape check = board.getReference();
            if (check != null) {
            board.moveShape(finalX - initX,finalY - initY);
            con.repaint();
            }
        }
    }
    
    // empty bodies
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
}