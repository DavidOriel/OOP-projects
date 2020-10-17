import oop.ex2.GameGUI;
import java.awt.*;

public class HumanShip extends SpaceShip {

    /**
     * A class for the human spaceship, that spaceship is been controlled by the user.
     */
    private int LEFTPRESSED = 1;
    private int RIGHTPRESSED = -1;
    private int BOTHPRESSED = 0;
    private int SHIELDON = 1;

    /**
     * the specific action loop for this ship, this method will be called by the doAction method in the
     * spaceship class that this class inherit.
     * @param game the game object to which this ship belongs.
     */
    public void doSubAction(SpaceWars game) {
        int turn = BOTHPRESSED;
        if (game.getGUI().isTeleportPressed()) {
            teleport();
        }
        if (game.getGUI().isRightPressed() && game.getGUI().isLeftPressed()) {
            turn = BOTHPRESSED;
        }
        if (game.getGUI().isRightPressed() && !game.getGUI().isLeftPressed()) {
            turn = RIGHTPRESSED;
        }
        if (!game.getGUI().isRightPressed() && game.getGUI().isLeftPressed()) {
            turn = LEFTPRESSED;
        }
        if (!game.getGUI().isRightPressed() && !game.getGUI().isLeftPressed()) {
            turn = BOTHPRESSED;
        }
        getPhysics().move(game.getGUI().isUpPressed(), turn);
        if (game.getGUI().isShieldsPressed()) {
            shieldOn();
        }
        if (game.getGUI().isShotPressed()) {
            fire(game);
        }
    }

    /**
     * Method for returning the specific image for the human ship.
     * @return Ship with shield image if the shield is activated, or if the shield is off-
     * ship without shield image.
     */
    public Image getImage() {
        if (this.shield == SHIELDON) {
            return GameGUI.SPACESHIP_IMAGE_SHIELD;
        } else {
            return GameGUI.SPACESHIP_IMAGE;
        }
    }
}