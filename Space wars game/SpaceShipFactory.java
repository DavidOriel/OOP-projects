import oop.ex2.*;

public class SpaceShipFactory {
    /**
     * The factory, here we will get arguments from the user, and from those arguments the factory
     *              will built the requested ships.
     * @param args The arguments that the user had entered. from those arguments the factory
     *             will built the requested ships.
     * @return return an array of ship objects.
     */
    public static SpaceShip[] createSpaceShips(String[] args) {
        SpaceShip[] ship = new SpaceShip[args.length];
        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "h":
                    ship[i] = new HumanShip();
                    break;
                case "r":
                    ship[i] = new RunnerShip();
                    break;
                case "b":
                    ship[i] = new BasherShip();
                    break;
                case "a":
                    ship[i] = new AggressiveShip();
                    break;
                case "d":
                    ship[i] = new DrunkardShip();
                    break;
                case "s":
                    ship[i] = new SpecialShip();
                    break;
            }

        }
        return ship;
    }
}


