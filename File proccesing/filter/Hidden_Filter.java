package filesprocessing.filter;
import filesprocessing.Type1_Exceptions;

import java.io.File;

/**
 * hidden filter class, extends the condition class for checking if the input String is valid.
 */
public class Hidden_Filter extends Abstract_Condition_Class{


    /**
     * constructor for that class
     * @param str string for checking the condition of the filter, if we want to filter files that are
     * hidden or filter files that are not hidden.
     */
    public Hidden_Filter(String str)throws Type1_Exceptions {
        super(str);
    }

    /**
     * checks if a given file is hidden or not, compare to the input string from the constructor.
     * @param file the file to check.
     * @return true if the hidden condition is true compares to the string , false otherwise.
     */
    public boolean filter(File file) {
        if (this.str.equals(YES)) {
            return (file.isHidden());
        }
        if (this.str.equals(NO)) {
            return !(file.isHidden());
        }

        return true;
    }
}

