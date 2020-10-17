package filesprocessing.order;

import java.io.File;

/**
 * class for the type Order.
 */
public class Type_Order implements Order {

    /**
     * @param file1 file number 1 to compare.
     * @param file2 file number 2 to compare.
     * @return int represent in which filesprocessing.order the two files needs to be ordered.
     */
    public int compare(File file1, File file2) {
        String Type1 = parseType(file1);
        String Type2 = parseType(file2);
        int a = Type1.compareTo(Type2);
        if(Type1.compareTo(Type2) == 0) {
            return file1.getAbsolutePath().compareTo(file2.getAbsolutePath());
        }
        else{
            return (Type1.compareTo(Type2));
        }
    }

    /**
     * getting the type of a file.
     * @param file the file to get his type.
     * @return String represent the type of the file.
     */
    private String parseType(File file) {
        String name = file.getName();
        int ind = name.lastIndexOf(".");
        try {
            String type = name.substring(ind);
            return type;
        } catch (StringIndexOutOfBoundsException e) {
         return "";
        }
    }
}

