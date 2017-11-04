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

    //2D list representing table data
    //1st index indicates which row
    //2nd index indicates column number
    private List<List<String>> rows = new ArrayList<>();
    /**
     * Table constructor
     * @param cols names of the columns
     * */
    Table(String[] cols){
        for(int i = 0; i < cols.length; i++){
            columns.put(cols[i], i);
        }
    }
}
