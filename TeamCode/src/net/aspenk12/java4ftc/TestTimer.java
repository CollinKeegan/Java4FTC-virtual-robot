package net.aspenk12.java4ftc;

public class TestTimer implements Clock{

    private long providedTime = 0;

    public long time() {

        return providedTime;

    }

    public void setTime(long time){

        providedTime = time;

    }
}
