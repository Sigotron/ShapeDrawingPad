import java.util.*;
import java.awt.*;
/**
 * DrawingBoard keeps track of a list of Shapes, as well as keeps track
 * of a selected Shape. The Shapes are stored in order, from index 0
 * to the last index. The selected shape will always be at end of list
 * 
 * @author Joel Sigo 
 * @version 01/14/2013
 */
public class DrawingBoard {
    
    // instance variables
    private ArrayList<Shape> shapes;
    private ArrayList<DBListener> listeners;
    private Shape selectedShape = null;
    
    /**
     * Constructs a DrawingBoard object
     */
    public DrawingBoard() {
        shapes = new ArrayList<Shape>();
        listeners = new ArrayList<DBListener>();
    }
    
    /**
     * Adds a DBListener to ArrayList listeners
     * @param c DBListener to be added
     */
    public void addListener(DBListener c) {
        if (c != null) {
            listeners.add(c);
        }
    }
    
    /**
     * 
     */
    private void notifyAllListeners() {
        Iterator<DBListener> it = listeners.iterator();
        while (it.hasNext()) {
            DBListener c = it.next();
            c.DBchanged();
        }
    }
    
    /**
     * adds shape to ArrayList shapes and selects new shape
     * @param x shape to be added to ArrayList shapes
     */
    public void addShape(Shape x) {
       deselectAll();
       x.setSelected(true);
       selectedShape = x;
       shapes.add(x);
       notifyAllListeners();
    }
    
    /**
     * allows client to pass in (x,y) coordinate and selects the topmost
     * shape with that coordinate, if no shape contains (x,y) then deselects
     * all shapes
     * @param x represents the x coordinate of the shape
     * @param y represents the y coordinate of the shape
     */
    public void selectXY(int x, int y) {
        ListIterator<Shape> lit = shapes.listIterator(shapes.size());
        boolean flag = true; // initialize flag variable
        while (lit.hasPrevious() && flag) { //iterate through list backwards
            Shape s = lit.previous();
            if (s.isOn(x,y)) {// if shape contains point
                deselectAll();
                selectedShape = s;
                s.setSelected(true);
                shapes.remove(s);
                shapes.add(selectedShape); // move shape to end of list
                flag = false; // kill loop
            }
        }
        if (flag == true) { // if no shapes contain coordinates
            deselectAll();
            selectedShape = null;
        }
        notifyAllListeners();
    }
    
    /**
     * returns a reference to the selected Shape. returns null if no Shape
     * is currently selected
     * @return currently selected shape or null
     */
    public Shape getReference() {
        return selectedShape;
    }
    
    
    /**
     * allows client to pass in a Color and changes the selected shape to
     * that color
     * @param c Color that shape is to be changed to
     */
    public void changeColor(Color c) {
        checkForSelected();
        selectedShape.setColor(c);
        notifyAllListeners();
    }
    
    /**
     * allows client to remove the selected Shape from the DrawingBoard. 
     * if selected Shape is removed, topmost shape becomes the selected
     * shape. if after removal the DrawingBoard is empty, nothing is 
     * selected
     */
    public void deleteSelected() {
        checkForSelected();
        shapes.remove(selectedShape);
        if (shapes.size() > 0) { // if list contains atleast one shape
            Shape s = shapes.get(shapes.size() - 1);
            selectedShape = s; // set selected to shape at end of list
            s.setSelected(true);
        } else {
            selectedShape = null;
        }
        notifyAllListeners();
    }
    
    /**
     * allows client to pass in a change in x value, and a change in
     * y value and DrawingBoard moves selected shape by those amounts.
     * @param x represents the change in x value
     * @param y represents the change in y value
     */
    public void moveShape(int x, int y) {
        checkForSelected();
        selectedShape.shiftBy(x,y);
        notifyAllListeners();
    }
    
    /**
     * returns a copy of the List of shapes in the DrawingBoard
     * @return ArrayList copy of List of shapes
     */
    public ArrayList getCopy() {
        ArrayList<Shape> newList = new ArrayList<Shape>(shapes);
        return newList;
    }
    
    /**
     * checks to see if a shape is selected, if not, throws exception
     */
    private void checkForSelected() {
        if (selectedShape == null) {
            throw new IllegalStateException("No shape selected");
        } 
    }
    
    /**
     * ensures all shapes are deselected
     */
    private void deselectAll() {
        Iterator<Shape> it = shapes.iterator();
        while (it.hasNext()) { // iterate through shapes
            Shape s = it.next(); // deselect all shapes
            s.setSelected(false);
        }
    }
}
