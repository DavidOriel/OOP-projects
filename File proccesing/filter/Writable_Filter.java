package filesprocessing.filter;
import filesprocessing.Type1_Exceptions;

import java.io.File;

/**
 *  writable filter class. extends the condition class for checking if the input String is valid.
 */
public class Writable_Filter extends Abstract_Condition_Class {

    /**
     * constructor for that class
     */
    public Writable_Filter(String str) throws Type1_Exceptions {
        super(str);
    }

    /**
     * checks if a given file is writable or not, compare to the input string from the constructor.
     * @param file the file to check.
     * @return true if the the writable condition is true  compares to the string , false otherwise.
     */
    public boolean filter(File file) {
        if (this.str.equals(YES)) {
            return (file.canWrite());
        }
        if (this.str.equals(NO)) {
            return !(file.canWrite());
        }

        return true;
    }
}
