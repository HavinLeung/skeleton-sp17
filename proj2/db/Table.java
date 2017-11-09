package db;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by havinleung on 2017-11-04.
 */
class Table {
    //Map of column names with their column number
    private Map<String, Integer> columns = new LinkedHashMap<>();
    private final int NUM_COLS;
    private List<String> toStringAppend = new ArrayList<>();


    //2D list representing table data
    //1st index indicates which row
    //2nd index indicates column number
    private List<List<String>> rows = new ArrayList<>();

    /**
     * Table constructor
     *
     * @param cols names of the columns
     */
    Table(String[] cols) {
        for (int i = 0; i < cols.length; i++) {
            columns.put(cols[i], i);
            if (cols[i].split("\\s+")[1].equals("string")) {
                toStringAppend.add("'");
            } else {
                toStringAppend.add("");
            }
        }
        NUM_COLS = cols.length;
    }

    /**
     * Getter function for NUM_COLS
     *
     * @return number of columns in table
     */
    int numCols() {
        return NUM_COLS;
    }

    /**
     * Adds an array of data in to a new row in the table
     *
     * @param data String array of the data to be put into a new row
     * @return true if row was added, false otherwise
     */
    boolean addRow(String[] data) {
        //TODO: check if correct type
        int index = rows.size();
        List<String> temp = new ArrayList<>();
        for (String item : data) {
            temp.add(item);
        }
        rows.add(temp);
        return true;
    }

    @Override
    public String toString() {
        StringBuilder stringRep = new StringBuilder();
        //add columns
        List<String> cols = new ArrayList<>();
        columns.forEach((k, v) -> {
            cols.add(k);
        });
        stringRep.append(String.join(",", cols));
        stringRep.append("\n");
        for (List<String> row : rows) {
            for (int i = 0; i < row.size(); i++) {
                stringRep.append(toStringAppend.get(i));
                stringRep.append(row.get(i));
                stringRep.append(toStringAppend.get(i));
                stringRep.append(',');
            }
            stringRep.deleteCharAt(stringRep.length() - 1);
            stringRep.append("\n");
        }
        stringRep.deleteCharAt(stringRep.length() - 1);

        return stringRep.toString();
    }

    //debugging
    public static void main(String[] args) {
        Table tb = new Table(new String[]{"Lastname string", "Firstname string", "Age int"});
        tb.addRow(new String[]{"Ho", "Venus", "18"});
        tb.addRow(new String[]{"Leung", "Havin", "19"});
        tb.addRow(new String[]{"Nair", "Arjun", "20"});
        System.out.println(tb);
    }
}
