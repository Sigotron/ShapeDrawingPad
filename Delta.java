import java.awt.*;
/**
 * Allows Delta objects to be constucted, stores data pertainable to 
 * Delta objects, and overrides abstract methods inherited from 
 * AbstractShape.
 * 
 * @author Joel Sigo 
 * @version 01/12/2013
 */
public class Delta extends AbstractShape {
    
    // instance variables
    private int height;

    /**
     * Constructs Delta object
     * @param apexX represents x coordinate of apex of Delta
     * @param apexY represents y coordinate of apex of Delta
     * @param h represents height of Delta
     * @param c represents color of Delta
     */
    public Delta(int apexX, int apexY, int h, Color c) {
        super(apexX, apexY, c);
        if(h % 2 != 0 && h <= 0) {
            throw new IllegalArgumentException("Parameter h must be even and positive");
        }
        height = h;
    }

     /**
     * returns true if the point (x,y) is on the Shape, false if not
     * @param x represents x coordinate to be tested against shape
     * @param y represents y coordinate to be tested against shape
     * @return true if point is on shape, false if not
     */
    public boolean isOn(int x, int y){
        if (x >= baseX && x <= baseX + (height / 2) && // split delta into two trianges
            y >= 2 * (x - baseX) + baseY && y <= baseY + height) { // check first triangle
            return true;                                // check second triangle
        } else if (x >= baseX - (height / 2) && x <= baseX && // uses formula for line
            y >= 2 * (baseX - x) + baseY && y <= baseY + height) {
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * returns height of shape
     * @return height of shape
     */
    public int getHeight() {
        return height;
    }
    
    /**
     * draws shape on a graphics editor
     */
    public void drawMe(Graphics g) {
        int[] xPoints = {baseX, baseX + height / 2, baseX - height / 2};
        int[] yPoints = {baseY, baseY + height, baseY + height};
        g.setColor(shapeColor);
        g.fillPolygon(xPoints,yPoints,3);
    }
    
    /**
     * provide a String representation of the shape's state
     * @return String representation of the shape's state
     */
    public String toString() {
        return "Delta Base = (" + baseX + "," + baseY +  
            "); Height = " + height + "; Color = " + shapeColor + 
            "; Selected = " + selected;
    }
    
}
