package filesprocessing.filter;

import filesprocessing.Type1_Exceptions;

import java.io.File;

/**
 * abstract class for all filters that filtering by size parameters
 */
abstract public class Abstract_Size_Filter implements Filter {
    protected String str1;
    protected String str2;
    protected double KILOBYTES = 1024.0;

    /**
     * constructor 1 for between filter.
     * constructor number 2 for greater than and smaller than filters.
     *
     * @param str1 string to check.
     * @throws Type1_Exceptions if there is a problem with the values, we will
     *                          throws an exception and the filter class will throw it to the filter factory.
     */
   public Abstract_Size_Filter(String str1) throws Type1_Exceptions {
        this.str1 = str1;
        try {
            double value1 = Double.parseDouble(this.str1);
            if (value1 < 0) {
                throw new Type1_Exceptions();
            }
        } catch (NumberFormatException e) {
            throw new Type1_Exceptions();
        }
    }

    /**
     * constructor 1 for between filter.
     *
     * @param str1 first string to check.
     * @param str2 second string to check.
     * @throws IllegalArgumentException if there is a problem with the values, we will
     *                                  throws an exception and the filter class will throw it to the filter factory.
     */
    public Abstract_Size_Filter(String str1, String str2) throws Type1_Exceptions {
        this.str1 = str1;
        this.str2 = str2;
        double value1 = Double.parseDouble(this.str1);
        double value2 = Double.parseDouble(this.str2);
        if (value1 > value2) {
            throw new Type1_Exceptions();
        }
        if (value1 < 0 || value2 < 0) {
            throw new Type1_Exceptions();
        }

    }

    @Override
    public boolean filter(File filename) throws IllegalArgumentException {
        return false;
    }
}
