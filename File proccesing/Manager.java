package filesprocessing;

import filesprocessing.filter.Filter;
import filesprocessing.order.Order;

import java.io.File;
import java.util.ArrayList;

/**
 * The manager of the program, here the file filtering and the file ordering will happen.
 */
public class Manager {
    private int MAX_ARGS = 2;
    /**
     * the manager method that prints the files
     *
     * @param args arguments, containing the command file and the file that needs to be filtered.
     */
    public void manage(String[] args) throws Type2_Exception {
        if (args.length != MAX_ARGS) {
            throw new Type2_Exception("ERROR: ILLEGAL NUMBER OF INPUTS ");
        }
        File directory = new File(args[0]);
        File[] oldFileArray = directory.listFiles();
        if (oldFileArray == null) {
            throw new Type2_Exception("ERROR: DIRECTORY IS EMPTY");
        }
        ArrayList<File> fileArrayList = new ArrayList<>();
        for (File file : oldFileArray) {
            if (!file.isDirectory()) {
                fileArrayList.add(file);
            }
        }
        File[] fileArray = fileArrayList.toArray(new File[fileArrayList.size()]);
        File commandFile = new File(args[1]);
        Parsing parsing = new Parsing(commandFile);
        parsing.creator();
        Section[] sectionList = parsing.getSectionArray();
        for (Section section : sectionList) {
            section.creator();
            Filter filter = section.getFilter();
            Order order = section.getOrder();
            File[] output = filterFiles(fileArray, filter);
            File[] fileToPrint = orderFiles(output, order);
            for (String string : section.errorString) {
                System.err.println(string);
            }
            for (File file : fileToPrint) {
                System.out.println(file.getName());
            }
        }

    }


    /**
     * filtering files by the filter object.
     *
     * @param files the files array which needs to be filtered.
     * @return file array of the filtered array.
     */
    private File[] filterFiles(File[] files, Filter filter) {
        ArrayList<File> filteredFiles = new ArrayList<>();
        for (File file : files) {
            if (filter.filter(file)) {
                filteredFiles.add(file);
            }
        }
        File[] filesArray = new File[filteredFiles.size()];
        filesArray = filteredFiles.toArray(filesArray);
        return filesArray;
    }

    /**
     * method for calling the quick sort method that will use the order object for ordering
     * a given file array.
     * @param files files to order
     * @param order the method we are using for ordering
     * @return an order file array.
     */
    private File[] orderFiles(File[] files, Order order) {
        quickSort(files, 0, files.length - 1, order);
        return files;
    }

    /**
     * The quicksort method for sorting the filtered file array.
     *
     * @param files the file array.
     * @param low   the first index of the sub array.
     * @param high  the last index of the subarray.
     * @param order the specific filesprocessing.order type we use for ordering.
     */
    private  void quickSort(File[] files, int low, int high, Order order) {
        if (low < high) {
            int pi = partition(files, low, high, order);
            quickSort(files, low, pi - 1, order);
            quickSort(files, pi + 1, high, order);
        }
    }

    /**
     * the partition for the quicksort, we will use it for filesprocessing.order the sub arrays.
     */
    private static int partition(File[] files, int low, int high, Order order) {
        File pivot = files[high];
        int i = (low - 1);
        for (int j = low; j <= high - 1; j++) {
            if (order.compare(files[j], pivot) <= 0) {
                i++;
                File file = files[i];
                files[i] = files[j];
                files[j] = file;
            }
        }
        File file = files[i + 1];
        files[i + 1] = files[high];
        files[high] = file;
        return i + 1;


    }

}
