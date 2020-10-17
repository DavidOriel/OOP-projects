package filesprocessing.filter;
import java.io.File;

/**
 * A shell class for wrapping a filter class,
 */
public class Not_Filter implements Filter{
    Filter filter;

    /**
     * we want to return the opposite of the filter method output
     * @param filter an filter object for finding the output of that filter filter method.
     */
    public Not_Filter(Filter filter) {
        this.filter = filter;
    }

    /**
     * the wrapping method.
     * @param file file to check
     * @return the opposite of the output of the filter method.
     */
        public boolean filter(File file) {
        return (!this.filter.filter(file));
    }
}
