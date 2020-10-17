import java.util.Random;

public class SpecialShip extends SpaceShip {
    /**
     * The special ship ship class. This ship will be controlled by the computer,
     *
     */
    Random random = new Random();
    private int ROUNDPERSHOOT = 6;
    private int TURNLEFT = -1;
    private int TELECHANCE = 12;
    private int NUMFORTELE = 0;
    private int counter = 0;

    /**
     * the specific action loop for this ship, this method will be called by the doAction method in the
     * spaceship class that this class inherit.
     * @param game the game object to which this ship belongs.
     */
    public void doSubAction(SpaceWars game) {
        int tele = random.nextInt(TELECHANCE);
        if (tele == NUMFORTELE) {
            teleport(); }
        physics.move(true, TURNLEFT);
        if ( counter == ROUNDPERSHOOT) {
            fire(game);}
        counter += 1;
    }
    public void fire(SpaceWars game) {
        game.addShot(getPhysics());
        counter = 0;
    }
}

