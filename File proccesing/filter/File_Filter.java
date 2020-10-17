package filesprocessing.filter;
import filesprocessing.Type1_Exceptions;

import java.io.File;

/**
 * Contains_Filter class, filters all files that does not contains a given string.
 */
public class File_Filter extends Abstract_Name_Filter {

    /**
     *  the constructor of the file filter class. the constructor have throw option for
     *  future use.
     * @param str The string to check.
     */
    public File_Filter(String str) throws Type1_Exceptions {
        super(str);

    }
    /**
     * checks if the file name equals to the given substring
     * @param file the file object
     * @return True if the name of the files equals to the substring, False if not.
     */
    public boolean filter(File file) {
        String file_true_name = file.getName();
        return (file_true_name.equals(this.str));


    }

}
