package net.aspenk12.java4ftc;

import java.util.ArrayList;
import java.util.List;

public class TestWriter implements LogWriter{

    private boolean stopCalled = false;

    private List<String> lines = new ArrayList<>();

    public void writeLine(String line) {
        lines.add(line);
    }

    public void stop() {

        stopCalled = true;

    }

    public boolean isStopCalled() {

        return stopCalled;

    }

    public List<String> getLines() {
        return lines;
    }

}
