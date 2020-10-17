package filesprocessing.filter;
import filesprocessing.Type1_Exceptions;

import java.io.File;

/**
 * Between_Filter class
 */
public class Between_Filter extends Abstract_Size_Filter {

    /**
     * constructor for that class
     */
    public Between_Filter(String str1, String  str2) throws Type1_Exceptions {
        super(str1, str2);
    }

    /**
     * checks if a given file size is between two given numbers.
     * @param file the given file
     * @return true if the size is between those numbers, False if not.
     */
    public boolean filter(File file) {
        double value1 = Double.parseDouble(this.str1) * KILOBYTES;
        double value2 = Double.parseDouble(this.str2) * KILOBYTES;
        double file_length = file.length();
        return (value1 <= file_length && value2>= file_length);

    }

}

