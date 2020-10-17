package filesprocessing.filter;
import filesprocessing.Type1_Exceptions;

import java.io.File;

/**
 * abstract class that implements the father filter class.
 */
public class Abstract_Name_Filter implements Filter {
    protected String str;

    /**
     * the constructor of that class, the throw exception is for future use
     * @param str
     * @throws Type1_Exceptions
     */
    public Abstract_Name_Filter(String str) throws Type1_Exceptions {
        this.str = str;
    }

    @Override
    public boolean filter(File filename) {
        return false;
    }
}
