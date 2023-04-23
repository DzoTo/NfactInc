import java.awt.*;
import java.util.ArrayList;

import javax.swing.JPanel;

public class DrawingPanel extends JPanel{
    private ArrayList<DrawableItem> drawableItems = new ArrayList();

    public DrawingPanel() { }

    public void initializeEverything() {

        // Go through and initialize each item
        for (DrawableItem item : this.getDrawableItems()) {
            item.initialize();
        }

    }

    public void moveEverythingOneStep() {

        // Go through and move each item one step
        for (DrawableItem item : this.getDrawableItems()) {
            item.moveOneStep();
        }
    }

    public void paint(Graphics g) {

        // This does the default painting first
        super.paint(g);

        // Go through and draw each item
        for (DrawableItem item : this.getDrawableItems()) {
            item.draw(g);
        }
    }

    public void addDrawableItem(DrawableItem item) {
        this.getDrawableItems().add(item);
    }

    public ArrayList<DrawableItem> getDrawableItems() {
        return drawableItems;
    }
    public void setDrawableItems(ArrayList<DrawableItem> drawableItems) {
        this.drawableItems = drawableItems;
    }


    public void removeDrawableImage(DrawableItem item) {
            this.getDrawableItems().remove(item);
        }

    public void addDrawableImage(DrawableImage image) {
        this.getDrawableImages().add(image);
        this.repaint();
    }

    public ArrayList<DrawableImage> getDrawableImages() {
        ArrayList<DrawableImage> drawableImages = new ArrayList<DrawableImage>();
        for (DrawableItem item : this.getDrawableItems()) {
            if (item instanceof DrawableImage) {
                drawableImages.add((DrawableImage) item);
            }
        }
        return drawableImages;
    }

//    public void gameOver(Graphics g){
//        g.setColor(Color.red);
//        g.drawString("Game Over", );
//    }
    }

