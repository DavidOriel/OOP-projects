package filesprocessing;
import filesprocessing.order.*;
import filesprocessing.filter.*;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.io.BufferedReader;

public class Parsing {
    File myFile;
    private ArrayList<String> stringArrayList = new ArrayList<String>();
    private ArrayList<Section> sectionArrayList = new ArrayList<Section>();
    private Section[] sectrionArray;
    private String[] stringarray;
    private String str;
    private String TYPE_2_EXCEPTION = "ERROR: PROBLEM WITH INPUTS IN FILE";

    public Parsing(File file) throws Type2_Exception {
        this.myFile = file;
        try {
            BufferedReader br = new BufferedReader(new FileReader(this.myFile));
            while ((str = br.readLine()) != null) {
                stringArrayList.add(str);
            }
            stringarray = stringArrayList.toArray(new String[stringArrayList.size()]);

        } catch (IOException e) {
            throw new Type2_Exception("ERROR: PROBLEM WITH FILE READER");
        }
    }

    /**
     * creates array of sections which will be used in the manager for filtering and ordering files.
     *
     * @throws Type2_Exception
     */
    public void creator() throws Type2_Exception {
        for (int i = 0; i < stringarray.length; i++) {
            int counter = i;
            String[] sectionList = new String[4];
            if (!stringarray[i].equals("FILTER"))
                throw new Type2_Exception(TYPE_2_EXCEPTION);
            try {
                sectionList[0] = stringarray[i + 1];
                if (!stringarray[i + 2].equals("ORDER")) {
                    throw new Type2_Exception(TYPE_2_EXCEPTION);
                }
                if (i + 3 < stringarray.length && !stringarray[i + 3].equals("FILTER")) {
                    sectionList[1] = stringarray[i + 3];
                    i += 3;
                    counter += 2;
                } else {
                    sectionList[1] = "";
                    i += 2;
                    counter += 2;
                }
                sectionArrayList.add(new Section(sectionList[0], sectionList[1], counter));
            } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
                throw new Type2_Exception(TYPE_2_EXCEPTION);
            }
        }

    }

    /**
     * getter for the array of section. the manager will use that.
     * @return am array of sections.
     */
    public Section[] getSectionArray() {
        sectrionArray = sectionArrayList.toArray(new Section[sectionArrayList.size()]);
        return sectrionArray;
    }
}
