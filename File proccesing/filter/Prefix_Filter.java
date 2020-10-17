package filesprocessing.filter;

import java.io.File;

/**
 * prefix_Filter class
 */
public class Prefix_Filter implements Filter {
    String subString;

    /**
     * the constructor for that class
     * @param subString the substring to check.
     */
    public Prefix_Filter(String subString) {
        this.subString = subString;

    }
    /**
     * checks if the file name starts with the given substring
     * @param file the file object
     * @return True if the name of the files start with the substring, False if not.
     */
    public boolean filter(File file) {
        String file_true_name = file.getName();
        return (file_true_name.startsWith(this.subString));


    }

}

