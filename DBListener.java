
/**
 * Interface DBListener allows the DrawingBoard to keep track of and
 * notify any viewer that its state has changed.
 * 
 * @author Joel Sigo 
 * @version 1/21/2012
 */
public interface DBListener {
    
    /**
     * allows views to be notified of updates to model
     */
    public void DBchanged();
    
}
