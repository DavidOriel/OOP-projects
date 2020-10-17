import java.awt.Image;
import java.security.KeyStore;

import oop.ex2.*;

/**
 * The main spaceship class, every ship we will create will inherit from that class.
 * Here we will define the main methods that every ship will use.
 * @author oop
 */
public class SpaceShip {
    /**
     * The amount of health the ship starts with.
     */
    private int HEALTH = 22;

    /**
     * The maximal energy the ship starts with.
     */
    private int MAXIMALENERGY = 210;

    /**
     * the current energy the ship starts with.
     */
    private int CURRENTENERGY = 190;

    /**
     * The parameter that indicates if the shield is off.
     * the ship starts without a shield.
     */
    private int SHIELDOFF = 0;

    /**
     * The parameter that indicates that the shield is on.
     */
    private int SHIELDON = 1;

    /**
     * counter for the game rounds.
     */
    private int ROUNDCOUNTER = 0;

    /**
     * Rounds that the ship needs to wait to preform another shot.
     */
    private int CANNOTSHOOT = 7;
    /**
     * The amount of energy that takes to teleport.
     */
    private int TELEPORTENERGY = 140;

    /**
     * The amount of energy that that takes to active the shield for one round.
     */
    private int SHIELDENERGY = 3;

    /**
     * The amount of life lost if the ship got hit.
     */
    private int LOOSELIFE = 1;

    /**
     * The amount of energy goes up by bashing an enemy ship.
     */
    private int PLUSENERGY = 18;

    /**
     * The amount of energy being lost by getting hit by other ship.
     */
    private int MINUSENERGY = 10;

    /**
     * The amount of energy needed to fire a torpedo.
     */
    private int FIREENREGY = 19;
    /**
     * Creation of space physics object.
     */


    SpaceShipPhysics physics;
    private int health;
    private int currentenergy;
    private int maxenergy;
    private int roundcounter;
    public int shield;


    /**
     * The constructor of the spaceship class, we will define here the parameters that change
     * in time.
     * the health, the max and current energy
     */
    public SpaceShip() {
        this.physics = new SpaceShipPhysics();
        this.health = HEALTH;
        this.maxenergy = MAXIMALENERGY;
        this.currentenergy = CURRENTENERGY;
        this.roundcounter = ROUNDCOUNTER;
        this.shield = SHIELDOFF;
    }

    /**
     * Does the actions of this ship for this round.
     * This is called once per round by the SpaceWars game driver.
     *
     * @param game the game object to which this ship belongs.
     */
    public void doAction(SpaceWars game) {
        this.shield = SHIELDOFF;
        doSubAction(game);
        this.currentenergy += 1;
        this.roundcounter += 1;
    }

    public void doSubAction(SpaceWars game) {
    }

    /**
     * This method is called every time a collision with this ship occurs
     */
    public void collidedWithAnotherShip() {
        if (this.shield == SHIELDON) {
            this.maxenergy += PLUSENERGY;
            this.maxenergy += PLUSENERGY;
        } else {
            this.health -= LOOSELIFE;
            if (this.maxenergy >= MINUSENERGY) {
                this.maxenergy -= MINUSENERGY;
            }
        }
    }

    /**
     * This method is called whenever a ship has died. It resets the ship's
     * attributes, and starts it at a new random position.
     */
    public void reset() {
        this.health = HEALTH;
        this.currentenergy = CURRENTENERGY;
        this.maxenergy = MAXIMALENERGY;
        this.physics = new SpaceShipPhysics();
        this.roundcounter = ROUNDCOUNTER;
    }

    /**
     * Checks if this ship is dead.
     *
     * @return true if the ship is dead. false otherwise.
     */
    public boolean isDead() {
        if (this.health == 0) {
            return true;
        }
        return false;
    }

    /**
     * Gets the physics object that controls this ship.
     *
     * @return the physics object that controls the ship.
     */
    public SpaceShipPhysics getPhysics() {
        return physics;
    }

    /**
     * This method is called by the SpaceWars game object when ever this ship
     * gets hit by a shot.
     */
    public void gotHit() {
        if (this.shield == SHIELDOFF) {
            this.health -= LOOSELIFE;
            if (this.maxenergy - MINUSENERGY <= this.currentenergy) {
                this.maxenergy = this.currentenergy;
            } else {
                this.maxenergy -= MINUSENERGY;
            }
        }
    }

    /**
     * Gets the image of this ship. This method should return the image of the
     * ship with or without the shield. This will be displayed on the GUI at
     * the end of the round.
     *
     * @return the image of this ship.
     */
    public Image getImage() {
        if (this.shield == SHIELDON) {
            return GameGUI.ENEMY_SPACESHIP_IMAGE_SHIELD;
        } else {
            return GameGUI.ENEMY_SPACESHIP_IMAGE;
        }
    }

    /**
     * Attempts to fire a shot. If the counter roundcounter didnt get to atleast 8 (because we add plus one
     * at the same game loop.
     *
     * @param game the game object.
     */
    public void fire(SpaceWars game) {
        if (this.currentenergy >= FIREENREGY) {
            if (this.roundcounter > CANNOTSHOOT)
                game.addShot(getPhysics());
            this.currentenergy -= FIREENREGY;
            this.roundcounter = ROUNDCOUNTER;
        }
    }

    /**
     * Attempts to turn on the shield.
     */
    public void shieldOn() {
        if (this.currentenergy >= SHIELDENERGY) {
            this.shield = SHIELDON;
        } else {
            this.shield = SHIELDOFF;
        }
        this.currentenergy -= SHIELDENERGY;
    }

    /**
     * Attempts to teleport.
     */
    public void teleport() {
        if (this.currentenergy >= TELEPORTENERGY) {
            physics = new SpaceShipPhysics();
            this.currentenergy -= TELEPORTENERGY;
        }
    }
}
