package filesprocessing.filter;

import java.io.File;

public class All_Filter implements Filter {


    /**
     * checks if the file name ends with the given substring
     *
     * @param file the file object
     * @return True if the name of the files ends with the substring, False if not.
     */
    public boolean filter(File file) {
        return true;
    }
}
