package filesprocessing.filter;

import filesprocessing.Type1_Exceptions;

import java.util.Arrays;

/**
 * the filter factory, here we will create a filter object from the input that came from the section class.
 */
public class FilterFactory {
    private String[] filterArray = new String[11];
    private String[] filterparts;
    Filter filter;
    String str;
    private static final String NOT = "NOT";
    private static final String GREATER_THAN = "greater_than";
    private static final String BETWEEN = "between";
    private static final String SMALLER_THAN = "smaller_than";
    private static final String FILE = "file";
    private static final String CONTAINS = "contains";
    private static final String PREFIX = "prefix";
    private static final String SUFFIX = "suffix";
    private static final String WRITABLE = "writable";
    private static final String EXECUTABLE = "executable";
    private static final String HIDDEN = "hidden";
    private static final String ALL = "all";
    private int MAX_BETWEEN = 4;
    private int MAX_REST = 3;

    /**
     * the constructor of the class. here an array will be created for storing all possible
     *
     */
    public FilterFactory() {
        filterArray[0] = GREATER_THAN;
        filterArray[1] = BETWEEN;
        filterArray[2] = SMALLER_THAN;
        filterArray[3] = FILE;
        filterArray[4] = CONTAINS;
        filterArray[5] = PREFIX;
        filterArray[6] = SUFFIX;
        filterArray[7] = WRITABLE;
        filterArray[8] = EXECUTABLE;
        filterArray[9] = HIDDEN;
        filterArray[10] = ALL;
    }

    /**
     * The main method for creating a filter object,
     * @param str the string with the instructions for creating filter object
     * @return the filter object that has been created
     * @throws Type1_Exceptions throws exception if there is a problem with the instruction string.
     */
    public Filter fined_filter_object(String str) throws Type1_Exceptions {
        this.str = str;
        this.filterparts = this.str.split("#");
        if (!(Arrays.asList(filterArray).contains(filterparts[0]))) {
            throw new Type1_Exceptions();
    }
        if (this.filterparts.length>= MAX_BETWEEN && !filterparts[0].equals(BETWEEN)) {
            throw new Type1_Exceptions();
        }
        if (this.filterparts.length >=MAX_REST &&!filterparts[0].equals(BETWEEN)
                && !filterparts[2].equals(NOT)){
            throw new Type1_Exceptions();
        }
        String suffix;
        String suffix2;
        try{
            suffix = filterparts[1];
        }catch (ArrayIndexOutOfBoundsException e){
            suffix = "";
        }
        try {
            suffix2 = filterparts[2];
        }catch (ArrayIndexOutOfBoundsException e){
            suffix2 = "";
        }
        switch (this.filterparts[0]) {
            case GREATER_THAN:
                filter = new greater_Than(suffix);
                break;
            case BETWEEN:
                filter = new Between_Filter(suffix, suffix2);
                break;
            case SMALLER_THAN:
                filter = new Smaller_Than(suffix);
                break;
            case FILE:
                filter = new File_Filter(suffix);
                break;
            case CONTAINS:
                filter = new Contains_Filter(suffix);
                break;
            case PREFIX:
                filter = new Prefix_Filter(suffix);
                break;
            case SUFFIX:
                filter = new Suffix_Filter(suffix);
                break;
            case WRITABLE:
                filter = new Writable_Filter(suffix);
                break;
            case EXECUTABLE:
                filter = new Executable_Filter(suffix);
                break;
            case HIDDEN:
                filter = new Hidden_Filter(suffix);
                break;
            case ALL:
                filter = new All_Filter();
                break;
        }
        if (filterparts[filterparts.length - 1].equals(NOT)) {
            filter = new Not_Filter(filter);
        }
        return filter;
    }


}


