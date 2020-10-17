package filesprocessing.order;

import java.io.File;

/**
 * class for size filesprocessing.order.
 */
public class size_Order implements Order {

    /**
     * compare the size of two given files
     *
     * @param file1 file 1
     * @param file2 file 2
     * @return 1 if file 1 is bigger then file2, -1 if file 1 is smaller then file 2, 0 if the size is equal.
     */
    public int compare(File file1, File file2) {
        long size1 = file1.length();
        long size2 = file2.length();
        if (size1 == size2) {
            String file_path_1 = file1.getAbsolutePath();
            String file_path_2 = file2.getAbsolutePath();
            return file_path_1.compareTo(file_path_2);
        } else {
            if (size1 - size2 < 0) {
                return -1;
            } else {
                return 1;
            }
        }
    }
}