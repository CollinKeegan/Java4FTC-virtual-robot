package net.aspenk12.java4ftc;

public class SystemTimer implements Clock {

    public long time(){

        return(System.currentTimeMillis());

    }

}
