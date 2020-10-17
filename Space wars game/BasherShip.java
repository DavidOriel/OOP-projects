public class BasherShip extends SpaceShip {
    /**
     * The basher ship class. This ship will be controlled by the computer, and it will always
     * try to bash other ships.
     */
    private double DISTANCEFROMENEY = 0.19;
    private int TURNLEFT = 1;
    private int TURNRIGHT = -1;
    private int STRAIT = 0;

    /**
     * the specific action loop for this ship, this method will be called by the doAction method in the
     * spaceship class that this class inherit.
     *
     * @param game the game object to which this ship belongs.
     */
    public void doSubAction(SpaceWars game) {
        SpaceShip enemy_ship = game.getClosestShipTo(this);
        double physic = physics.distanceFrom(enemy_ship.physics);
        double angleTo = physics.angleTo(enemy_ship.physics);
        if (angleTo < STRAIT) {
            physics.move(true, TURNRIGHT);}
        if (angleTo > STRAIT) {
            physics.move(true,TURNLEFT);
        }
        if (physic < DISTANCEFROMENEY) {
                shieldOn();
            }
        }
    }



