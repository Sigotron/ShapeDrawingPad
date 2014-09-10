import java.awt.*;
import javax.swing.*;

public class GraphicsEditorApp {
    public static void main(String[] args) {
        DrawingBoard b = new DrawingBoard();
        PrintView p = new PrintView(b);
        JFrame win = new JFrame("DRAW SOME SHAPES ITS SO FUN");
        win.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        ShapeControl disp = new ShapeControl(b);
        win.getContentPane().add(disp);
        win.pack();
        win.setVisible(true);
    }
}
