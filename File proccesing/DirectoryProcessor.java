package filesprocessing;

public class DirectoryProcessor {
    /**
     * main method for running the program
     * @param args arguments from the command line.
     */
    public static void main(String[] args){
            try {
                Manager manager = new Manager();
                manager.manage(args);
            }
            catch(Type2_Exception e){
                System.err.println(e.getMessage());
            }
        }
    }



