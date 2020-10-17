package filesprocessing.filter;
import java.io.File;

/**
 * Contains_Filter class
 */
public class Contains_Filter implements Filter{
    String subString;

    /**
     * the constructor for that class
     * @param subString the substring to check.
     */
    public Contains_Filter(String subString) {
        this.subString = subString;

    }
    /**
     * checks if the file name contains the given substring
     * @param file the file object
     * @return True if the name of the files contains the substring, False if not.
     */
    public boolean filter(File file) {
        String file_true_name = file.getName();
        return (file_true_name.contains(this.subString));


    }

}

