package filesprocessing.filter;
import filesprocessing.Type1_Exceptions;

import java.io.File;

/**
 * abstract class that implements the father filter class.
 * this abstract class checks legal inputs for the writable, executable and hidden class
 */
public class Abstract_Condition_Class implements Filter {
    String str;
    String YES = "YES";
    String NO = "NO";

    /**
     * the constructor of the class.
     * checks if the input string is legal, if not, the constructor throws an exception,
     * and the filter class throws it to the filter factory.
     * @param str the string to check.
     * @throws Type1_Exceptions throws an exception if the input is illegal.
     */
    public Abstract_Condition_Class(String str) throws Type1_Exceptions {
        this.str = str;

        if (!(str.equals(YES) || str.equals(NO))){
            throw new Type1_Exceptions();
        }

    }

    @Override
    public boolean filter(File filename) {
        return false;
    }
}
