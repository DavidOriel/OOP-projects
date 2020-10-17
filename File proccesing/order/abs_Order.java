package filesprocessing.order;
import java.io.File;

public class abs_Order implements Order {
    /**
     * compare between two files by the abs filesprocessing.order.
     * @param file1 file number 1 to compare.
     * @param file2 file number 2 to compare.
     * @return return int in which filesprocessing.order the files needs to be.
     */
    public int compare(File file1, File file2) {
        String file_path_1 = file1.getAbsolutePath();
        String file_path_2 = file2.getAbsolutePath();
        return file_path_1.compareTo(file_path_2);

    }
}

