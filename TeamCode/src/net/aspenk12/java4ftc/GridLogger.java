package net.aspenk12.java4ftc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class GridLogger {

    private final LogWriter writer;
    private Clock timer = new SystemTimer();
    long datumTime;

    public GridLogger(LogWriter writer, Clock timer) {
        this.writer = writer;
        this.timer = timer;
        long datumTime = timer.time();
    }

    public GridLogger(LogWriter writer) {
        this.writer = writer;
        this.timer = new SystemTimer();
        long datumTime = timer.time();
    }

    private final HashMap<String, String> rowData = new HashMap<>();
    private final ArrayList<String> columnHeaders = new ArrayList<>();
    private boolean firstLine = true;


    /**
     * Define grid column header names
     * @param columns
     */
    public void setColumnHeaders(String[] columns) {

        for(int i = 0; i < columns.length; i++){

            if(!columnHeaders.contains(columns[i])){

                columnHeaders.add(columns[i]);

            }

        }

    }

    /**
     * Add a value to the logger under the specified column
     *
     * @param column
     * @param value
     */
    public void add(String column, double value) {

        rowData.put(column, toString().valueOf(value));

        if(firstLine == true && !columnHeaders.contains(column)){

            columnHeaders.add(column);

        }

    }

    public void add(String column) {

        rowData.put(column, "");

        if(firstLine == true && !columnHeaders.contains(column)){

            columnHeaders.add(column);

        }

    }

    /**
     * Write a line of data to the log.  If this is the first call to writeRow, a row of comma-separated
     * column names are written first.  A row of comma-separated data values that were added with the add()
     * method is written next.  Once the data row is written, the logger is reset
     * and calls to add() will add values to the next line of data. Automatically adds Time column and data as well.
     */
    public void writeRow() {

        writeRow(true);

    }

    //Replicates functions of writeRow except gives option to disable automatic Time data creation.
    public void writeRow(boolean writeTime) {

        //Adds Time field if writeTime is enabled
        {
            if(writeTime == true){

                rowData.put("Time", toString().valueOf(timer.time() - datumTime));

                if(firstLine == true && !columnHeaders.contains("Time")){

                    columnHeaders.add("Time");

                }

            }

        }

        if(firstLine == true){

            StringBuilder builder = new StringBuilder();

            for(int i = 0; i < columnHeaders.size(); i++){

                builder.append(columnHeaders.get(i));

                if(i != columnHeaders.size() - 1){

                    builder.append(",");

                }

            }

            writer.writeLine(builder.toString());
            firstLine = false;

        }

        StringBuilder builder = new StringBuilder();

        for(int i = 0; i < columnHeaders.size(); i++){

            if(rowData.get(columnHeaders.get(i)) != null){

                builder.append(rowData.get(columnHeaders.get(i)));

                if(i != columnHeaders.size() - 1){

                    builder.append(",");

                }

            }

        }

        writer.writeLine(builder.toString());

        rowData.clear();

    }

    public void stop() {

        writer.stop();

    }

}
