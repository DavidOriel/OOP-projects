import java.util.Random;

public class DrunkardShip extends SpaceShip {
    /**
     * The drunkard ship class. This ship will be controlled by the computer, last night the
     * pilot went to a party and he still drunk. Only the fate will decide what action
     * he will preform next.
     *
     */
    /**
     * Global variables for the random function.
     */
    Random random = new Random();
    private double DISTANCETOENEMY = 0.21;
    private int ACCELCHANCE = 1;
    private int TELECHANCE = 15;
    private int TURNCHANCE = 3;
    private int NUMFORREACT = 0;

    /**
     * the specific action loop for this ship, this method will be called by the doAction method in the
     * spaceship class that this class inherit.
     * @param game the game object to which this ship belongs.
     */
    public void doSubAction(SpaceWars game) {
        int accel = random.nextInt(ACCELCHANCE);
        int tele = random.nextInt(TELECHANCE);
        int turn = random.nextInt(TURNCHANCE) - 1;
        SpaceShip enemy_ship = game.getClosestShipTo(this);
        double angleTo = physics.angleTo(enemy_ship.physics);
        if (tele == NUMFORREACT) {
            teleport();
        }
        physics.move((accel == NUMFORREACT), turn);
        if (angleTo < DISTANCETOENEMY) {
            fire(game);
        }
    }
}
