import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Animator implements ActionListener{

    private GameField gamefield;

    public Animator(GameField gamefield){
    this.setGameField(gamefield);
    }

    public void actionPerformed(ActionEvent arg0) {

        // Get the drawing panel from the aquarium
        GameField aquarium = this.getGameField();
        DrawingPanel dPanel = gamefield.getDrawingPanel();

        // Move everything one step, and repaint everything
        dPanel.moveEverythingOneStep();
        dPanel.repaint();
    }

    public GameField getGameField() {
        return gamefield;
    }

    public void setGameField(GameField gamefield) {
        this.gamefield = gamefield;
    }
}
