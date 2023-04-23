import java.awt.*;
import java.util.*;
import java.util.Random;

public class RockPaperScissors extends DrawableImage{
    private int speed;
    private boolean movingRight, movingUp;



    public RockPaperScissors(DrawingPanel drawArea,String imageFile, int speed, boolean facingRight, boolean facingUp){
        super(drawArea, imageFile, 30, 25);

        this.setSpeed(speed);

        this.setMovingRight(facingRight);

        this.setMovingUp(facingUp);
    }

    @Override
    public Rectangle getBounds() {
        return null;
    }

    public void initialize(){

        int xPos, yPos;
        Set<Integer> set1 = new HashSet<>();
        Random rand = new Random();

        if (isMovingRight() && isMovingUp()) {
            xPos = rand.nextInt(this.getWindowWidth()/2);
            yPos = rand.nextInt(this.getWindowHeight()/2, this.getWindowHeight()-this.getHeight());

            // Otherwise, start the fish at the right edge of the window
        } else if(isMovingRight() && !isMovingUp()) {
            xPos = rand.nextInt(this.getWindowWidth()/2);
            yPos = rand.nextInt(this.getWindowHeight()/4);
        }
        else if(!isMovingRight() && !isMovingUp()){
            xPos = rand.nextInt(this.getWindowWidth()/2, this.getWindowWidth());
            yPos = yPos = rand.nextInt(this.getWindowHeight()/4);
        }
        else {
            xPos = rand.nextInt(this.getWindowWidth()/2, this.getWindowWidth());
            yPos = rand.nextInt(this.getWindowHeight()/2, this.getWindowHeight()-this.getHeight());
        }

        this.setX(xPos);
        this.setY(yPos);
    }

    public void moveOneStep(){

        int nextX = 0;
        int nextY = 0;
        int sign = 1;
        int yPos = getY();
        int xPos = getX();
        if(isMovingRight() && isMovingUp()){
            nextX=xPos+getSpeed();
            nextY=yPos+getSpeed();


            if(yPos >= this.getWindowHeight()- this.getHeight()){
                reverseDirectionVer();
            }

            if(xPos >= this.getWindowWidth() - this.getWidth()){
                reverseDirectionHor();
            }
        }
        else if(isMovingRight() && !isMovingUp()){
            nextX = xPos + getSpeed();
            nextY = yPos - getSpeed();
            if(xPos >= this.getWindowWidth() - this.getWidth()){
                reverseDirectionHor();
            }

            if(yPos<=this.getHeight()){
                reverseDirectionVer();
            }
        }
        else if(!isMovingRight() && movingUp){
            nextX = xPos - getSpeed();
            nextY = yPos + getSpeed();
            if(yPos >= this.getWindowHeight()- this.getHeight()){
                reverseDirectionVer();
            }

            if(xPos<=this.getWidth()){
                reverseDirectionHor();
            }
        }
        else if(!isMovingRight() && !isMovingUp()) {
            nextX = xPos - getSpeed();
            nextY = yPos - getSpeed();
            if(xPos<=this.getWidth()){
                reverseDirectionHor();
            }

            if(yPos<=this.getHeight()){
                reverseDirectionVer();
            }
        }

        this.setX(nextX);
        this.setY(nextY);

        if (checkCollisions()) {
//            changeImageOnCollisionR(getCollidedRock());
            if(nextX>=this.getWidth() && nextX<=this.getWindowWidth()-this.getWidth() &&
                    nextY>=this.getHeight() && nextY<=this.getWindowHeight()-this.getHeight()){
                horizontalFlip();
                verticalFlip();
                setMovingRight(!isMovingRight());
                setMovingUp(!isMovingUp());}
            else{
                boolean hitLeft = (nextX < this.getWidth());
                boolean hitRight = (nextX > this.getWindowWidth() - this.getWidth());
                boolean hitTop = (nextY < this.getHeight());
                boolean hitBottom = (nextY > this.getWindowHeight() - this.getHeight());

                if (hitLeft || hitRight) {
                    setMovingRight(!isMovingRight());
                }

                if (hitTop || hitBottom) {
                    setMovingUp(!isMovingUp());
                }

                if ((hitLeft || hitRight) && (hitTop || hitBottom)) {
                    horizontalFlip();
                    verticalFlip();
                }
            }
        }
    }

    protected void reverseDirectionHor() {

        // Flips the image to be rendered
        this.horizontalFlip();

        // Now change the direction of movement
        boolean movingRight = this.isMovingRight();
        this.setMovingRight(!movingRight);
    }

    protected void reverseDirectionVer() {

        // Flips the image to be rendered
        this.verticalFlip();

        boolean movingUp = this.isMovingUp();
        this.setMovingUp(!movingUp);
        // Now change the direction of movement
    }


    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public boolean isMovingRight() {
        return movingRight;
    }

    public boolean isMovingUp() {
        return movingUp;
    }
    public void setMovingRight(boolean movingRight) {
        this.movingRight = movingRight;
    }

    public void setMovingUp(boolean movingUp) {
        this.movingUp = movingUp;
    }

    public boolean checkCollisions() {
        ArrayList<DrawableItem> drawables = getDrawArea().getDrawableItems();
        Rectangle paperBounds = new Rectangle(getX(), getY(), getWidth(), getHeight());
        Image image1Rock = Toolkit.getDefaultToolkit().getImage("Rock.gif");
        Image image2Paper = Toolkit.getDefaultToolkit().getImage("Paper.gif");
        Image image3Scissors = Toolkit.getDefaultToolkit().getImage("Scissors.gif");
        for (DrawableItem item : drawables) {
            if (item instanceof DrawableImage && item != this) {
                DrawableImage drawable = (DrawableImage) item;
                Rectangle drawableBounds = new Rectangle(drawable.getX(), drawable.getY(),
                        drawable.getWidth(), drawable.getHeight());
                if (paperBounds.intersects(drawableBounds)) {
                        if(this.getImage()==image3Scissors && drawable.getImage()==image2Paper){
                            Image paper = Toolkit.getDefaultToolkit().getImage("Scissors.gif");
                            drawable.setImage(paper);
                        }
                        else if(this.getImage()==image3Scissors && drawable.getImage()==image1Rock){
                            Image paper = Toolkit.getDefaultToolkit().getImage("Rock.gif");
                            this.setImage(paper);
                        }
                        else if(this.getImage()==image2Paper && drawable.getImage()==image1Rock){
                            Image paper = Toolkit.getDefaultToolkit().getImage("Paper.gif");
                            drawable.setImage(paper);
                        }
                        else if(this.getImage()==image2Paper && drawable.getImage()==image3Scissors){
                            Image paper = Toolkit.getDefaultToolkit().getImage("Scissors.gif");
                            this.setImage(paper);
                        }
                        else if(this.getImage()==image1Rock && drawable.getImage()==image3Scissors){
                            Image paper = Toolkit.getDefaultToolkit().getImage("Rock.gif");
                            drawable.setImage(paper);
                        }
                        else if(this.getImage()==image1Rock && drawable.getImage()==image2Paper){
                            Image paper = Toolkit.getDefaultToolkit().getImage("Paper.gif");
                            this.setImage(paper);
                        }

                    return true; // collision detected
                }
            }
        }

        return false; // no collision detected
    }

}
