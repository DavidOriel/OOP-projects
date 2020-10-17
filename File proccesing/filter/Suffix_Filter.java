package filesprocessing.filter;
import java.io.File;

/**
 * suffix_Filter class
 */
public class Suffix_Filter implements Filter {
    String subString;

    /**
     * the constructor for that class
     * @param subString the substring to check.
     */
    public Suffix_Filter(String subString) {
        this.subString = subString;

    }

    /**
     * checks if the file name ends with the given substring
     * @param file the file object
     * @return True if the name of the files ends with the substring, False if not.
     */
    public boolean filter(File file) {
        String file_true_name = file.getName();
        return (file_true_name.endsWith(this.subString));


    }

}

