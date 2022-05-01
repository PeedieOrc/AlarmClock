package clock;

/**
 * Minimal "person" class.
 *
 * At the moment a Person object just holds their name, but in a more realistic
 * system, there would obviously be more.
 */
public class Person {

    protected String name;
    protected int longtime;

    public Person(String name, int longtime) {
        this.name = name;
        this.longtime = longtime;
    }

    public String getName() {
        return name;
    }
    public int getLongTime() {
        return longtime;
    }

    @Override
    public String toString() {
        return getName();
    }
}
