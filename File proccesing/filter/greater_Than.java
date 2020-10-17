package filesprocessing.filter;
import filesprocessing.Type1_Exceptions;

import java.io.File;

/**
 * Greater_that filter class.
 */
public class greater_Than extends Abstract_Size_Filter {

    /**
     * create constructor for that class.
     */
    public greater_Than(String str) throws Type1_Exceptions {
        super(str);
    }

    /**
     * checks if the size of the file is greater then a given size.
     * @param file the file object
     * @return True if it is bigger, false if not.
     */
    public boolean filter(File file) {
        double value = Double.parseDouble(this.str1)* KILOBYTES;
        double file_length = file.length();
        return (value< file_length);

    }

}

