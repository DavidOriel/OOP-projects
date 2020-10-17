package filesprocessing.filter;

/**
 * Smaller_Than filter class.
 */

import filesprocessing.Type1_Exceptions;

import java.io.File;

public class Smaller_Than extends Abstract_Size_Filter {
    double number;

    /**
     * create constructor for that class.
     */
    public Smaller_Than(String str) throws Type1_Exceptions {
        super(str);
    }

    /**
     * checks if the size of the file is smaller then a given size.
     *
     * @param file the file object
     * @return True if it is smaller, false if not.
     */
    public boolean filter(File file) {
        double value = Double.parseDouble(str1)* KILOBYTES;
        double file_length = file.length();
        return (value > file_length);

    }

}
