package filesprocessing.filter;
import filesprocessing.Type1_Exceptions;

import java.io.File;

public class Executable_Filter extends Abstract_Condition_Class {


    /**
     * constructor for that class
     * @param str string for checking the condition of the filter, if we want to filter files that does
     * have execute option or filter files that does not have have that option.
     */
    public Executable_Filter(String str) throws Type1_Exceptions {
        super(str);
    }

    /**
     * checks if a given file is execute or not, compare to the input string from the constructor.
     * @param file the file to check.
     * @return true if the execute condition is true compares to the string , false otherwise.
     */
    public boolean filter(File file) {
        if (this.str.equals(YES)) {
            return (file.canExecute());
        }
        if (this.str.equals(NO)) {
            return !(file.canExecute());
        }

        return true;
    }
}

