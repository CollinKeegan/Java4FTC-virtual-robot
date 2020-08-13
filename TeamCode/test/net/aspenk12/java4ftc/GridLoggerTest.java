package net.aspenk12.java4ftc;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GridLoggerTest {

    @Test
    public void writeLn() {
        TestWriter writer = new TestWriter();
        GridLogger gridLogger = new GridLogger(writer);
        gridLogger.setColumnHeaders(new String[]{"RobotX", "RobotY"});
        gridLogger.add("RobotX", 2.4);
        gridLogger.add("RobotY", 3.2);
        gridLogger.writeRow(false);

        List<String> lines = writer.getLines();
        assertEquals("RobotX,RobotY", lines.get(0));
        assertEquals("2.4,3.2", lines.get(1));
    }

    @Test
    public void writeMultipleLns() {
        TestWriter writer = new TestWriter();
        GridLogger gridLogger = new GridLogger(writer);
        gridLogger.setColumnHeaders(new String[]{"RobotX", "RobotY"});
        gridLogger.add("RobotX", 2.4);
        gridLogger.add("RobotY", 3.2);
        gridLogger.writeRow(false);
        gridLogger.add("RobotX", 4.5);
        gridLogger.add("RobotY", 1.2);
        gridLogger.writeRow(false);

        List<String> lines = writer.getLines();
        assertEquals("RobotX,RobotY", lines.get(0));
        assertEquals("2.4,3.2", lines.get(1));
        assertEquals("4.5,1.2", lines.get(2));
    }

    @Test
    public void writeEmptyData() {
        TestWriter writer = new TestWriter();
        GridLogger gridLogger = new GridLogger(writer);
        gridLogger.setColumnHeaders(new String[]{"RobotX", "RobotY"});
        gridLogger.add("RobotX");
        gridLogger.add("RobotY", 3.2);
        gridLogger.writeRow(false);

        List<String> lines = writer.getLines();
        assertEquals("RobotX,RobotY", lines.get(0));
        assertEquals(",3.2", lines.get(1));
    }

    @Test
    public void writeEmptyHeader() {
        TestWriter writer = new TestWriter();
        GridLogger gridLogger = new GridLogger(writer);
        gridLogger.setColumnHeaders(new String[]{"RobotX", "RobotY"});
        gridLogger.add("RobotX");
        gridLogger.add("RobotY", 3.2);
        gridLogger.writeRow(false);
        gridLogger.add("Robot", 3.2);

        List<String> lines = writer.getLines();
        assertEquals("RobotX,RobotY", lines.get(0));
        assertEquals(",3.2", lines.get(1));
    }

    @Test
    public void writeEmptyLn() {
        TestWriter writer = new TestWriter();
        GridLogger gridLogger = new GridLogger(writer);
        gridLogger.setColumnHeaders(new String[]{"RobotX", "RobotY"});
        gridLogger.writeRow(false);

        List<String> lines = writer.getLines();
        assertEquals("RobotX,RobotY", lines.get(0));
        System.out.println(lines.get(1));
    }

    @Test
    public void isStopCalled() {
        TestWriter writer = new TestWriter();
        GridLogger gridLogger = new GridLogger(writer);
        gridLogger.stop();

        assertEquals(true, writer.isStopCalled());
    }

    @Test
    public void fileWriteTest() {
        LogWriter writer = new FileLogWriter("/Documents/GridLogTest.csv");
        GridLogger gridLogger = new GridLogger(writer);
        gridLogger.setColumnHeaders(new String[]{"RobotX", "RobotY"});
        gridLogger.add("RobotX", 2.4);
        gridLogger.add("RobotY", 3.2);
        gridLogger.writeRow(false);
        gridLogger.stop();
    }

    @Test
    public void lazyWriteLn() {
        TestWriter writer = new TestWriter();
        GridLogger gridLogger = new GridLogger(writer);
        gridLogger.add("RobotX", 2.4);
        gridLogger.add("RobotY", 3.2);
        gridLogger.writeRow(false);

        List<String> lines = writer.getLines();
        assertEquals("RobotX,RobotY", lines.get(0));
        assertEquals("2.4,3.2", lines.get(1));
    }

    @Test
    public void lazyDuplicates() {
        TestWriter writer = new TestWriter();
        GridLogger gridLogger = new GridLogger(writer);
        gridLogger.add("RobotX", 2.4);
        gridLogger.add("RobotX", 1.2);
        gridLogger.add("RobotY", 3.2);
        gridLogger.writeRow(false);

        List<String> lines = writer.getLines();
        assertEquals("RobotX,RobotY", lines.get(0));
        assertEquals("1.2,3.2", lines.get(1));
    }

    @Test
    public void timeWrite() {
        TestWriter writer = new TestWriter();
        TestTimer timer = new TestTimer();
        timer.setTime(1);
        GridLogger gridLogger = new GridLogger(writer, timer);
        timer.setTime(4);
        gridLogger.writeRow(true);

        List<String> lines = writer.getLines();
        assertEquals("Time", lines.get(0));
        assertEquals("4", lines.get(1));
    }

    @Test
    public void systemTimeTest() {
        TestWriter writer = new TestWriter();
        GridLogger gridLogger = new GridLogger(writer);
        gridLogger.writeRow(true);
        List<String> lines = writer.getLines();
        System.out.println(lines.get(1));
    }


}