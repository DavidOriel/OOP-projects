import oop.ex2.*;
import java.math.*;
public class RunnerShip extends SpaceShip{
    /**
     * The runner ship class. This ship will be controlled by the computer, and it will always
     * try to run away from other ships.
     */
    private double RADIANT = 0.23;
    private double DISTANCETOENEMY = 0.25;
    private double PI = Math.PI;
    private int TURNLEFT = 1;
    private int TURNRIGHT = -1;
    private int STRAIT = 0;

    /**
     * the specific action loop for this ship, this method will be called by the doAction method in the
     * spaceship class that this class inherit.
     * @param game the game object to which this ship belongs.
     */
    public void doSubAction(SpaceWars game) {
        SpaceShip enemy_ship = game.getClosestShipTo(this);
        double physic = physics.distanceFrom(enemy_ship.physics);
        double angleTo = physics.angleTo(enemy_ship.physics);
        if (physic < DISTANCETOENEMY && angleTo < RADIANT) {
            teleport();
                }
        if (angleTo < STRAIT && angleTo > -PI ){
            physics.move(true, TURNLEFT);}
        if ( angleTo >STRAIT && angleTo < PI) {
            physics.move(true, TURNRIGHT);}

            }
        }


