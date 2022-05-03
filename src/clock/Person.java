package clock;

import java.util.Calendar;

/**
 * Minimal "person" class.
 *
 * At the moment a Person object just holds their name, but in a more realistic
 * system, there would obviously be more.
 */
public class Person {

    protected String name;
    protected int longtime;
    protected Calendar storedtime;

    public Person(String name, int longtime, Calendar storedtime) {
        this.name = name;
        this.longtime = longtime;
        this.storedtime = storedtime; 
    }

    public String getName() {
        return name;
    }
    public int getLongTime() {
        return longtime;
    }

    public Calendar getStoredTime() {
    return storedtime;
    }


    @Override
    public String toString() {
        //return Integer.toString(getLongTime());
        return  name;
    }
}
