import java.awt.*;
/**
 * Allows Tee objects to be constucted, stores data pertainable to 
 * Tee objects, and overrides abstract methods inherited from 
 * AbstractShape.
 * 
 * @author Joel Sigo
 * @version 01/12/2013
 */
public class Tee extends AbstractShape {

    // instance variables
    private int height;
    
    /**
     * Constructs Tee object
     * @param x represents x coordinate of upper left corner of Tee
     * @param y represents y coordinate of upper left corner of Tee
     * @param h represents height of Tee
     * @param c represents Color of Tee
     */
    public Tee(int x, int y, int h, Color c) {
        super(x, y, c);
        if(h % 10 != 0 && h <= 0) {
            throw new IllegalArgumentException("Parameter h must be multiple of 10 and positive");
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
        double botX = baseX + (height * .4);
        double botY = baseY + (height * .2);
        if (y >= baseY && y <= baseY + (height * .2) && x >= baseX && 
            x <= baseX + height) { // check upper rectangle
            return true;
        } else if (y >= botY && y <= botY + (height * .8) &&
            x >= botX && x <= botX + (height * .2)) { // check lower rectangle
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
    public void drawMe(Graphics g){
        int[] xPoints = {baseX, baseX + height, baseX + height, baseX + (int)((.6) * height),
            baseX + (int)((.6) * height), baseX + (int)((.4) * height), 
            baseX + (int)((.4) * height), baseX};
        int[] yPoints = {baseY, baseY, baseY + (int)((.2) * height),
            baseY + (int)((.2) * height), baseY + height, baseY + height, 
            baseY + (int)((.2) * height), baseY + (int)((.2) * height)};
        g.setColor(shapeColor);
        g.fillPolygon(xPoints,yPoints,8);
    }
    
    /**
     * provide a String representation of the shape's state
     * @return String representation of the shape's state
     */
    public String toString() {
        return "Tee Base = (" + baseX + "," + baseY +  
            "); Height = " + height + "; Color = " + shapeColor + 
            "; Selected = " + selected;
    }
}
