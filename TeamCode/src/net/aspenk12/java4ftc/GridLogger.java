package net.aspenk12.java4ftc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class GridLogger {

    private TestWriter writer;
    private HashMap<String, String> rowData = new HashMap<>();
    private ArrayList<String> columnHeaders;
    private boolean firstLine = true;

    public GridLogger(TestWriter writer) {
        this.writer = writer;
    }

    /**
     * Define grid column header names
     * @param columns
     */
    public void setColumnHeaders(String[] columns) {

        columnHeaders = (ArrayList<String>) Arrays.asList(columns);

    }

    /**
     * Add a value to the logger under the specified column
     *
     * @param column
     * @param value
     */
    public void add(String column, double value) {

        rowData.put("column", toString().valueOf(value));

    }

    /**
     * Write a line of data to the log.  If this is the first call to writeRow, a row of comma-separated
     * column names are written first.  A row of comma-separated data values that were added with the add()
     * method is written next.  Once the data row is written, the logger is reset
     * and calls to add() will add values to the next line of data.
     */
    public void writeRow() {

        if(firstLine == true){

            for(int i = 0; i < columnHeaders.size(); i++){

                StringBuilder builder = new StringBuilder();
                builder.append(columnHeaders.get(i));

                if(i != columnHeaders.size() - 1){

                    builder.append(",");

                }

                writer.writeLine(builder.toString());

            }

        }

        for(int i = 0; i < columnHeaders.size(); i++){

            StringBuilder builder = new StringBuilder();
            builder.append(rowData.get(columnHeaders.get(i)));

            if(i != columnHeaders.size() - 1){

                builder.append(",");

            }

            writer.writeLine(builder.toString());

        }

    }

    public void stop() {
    }

}
