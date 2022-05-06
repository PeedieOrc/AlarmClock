package clock;

/**
 * Main programme 
 * @author Ray Banks
 * @version 1 06/05/2022
 *  
 */
public class Clock {
    
    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        Model model = new Model();
        View view = new View(model);
        model.addObserver(view);
        Controller controller = new Controller(model, view);
    }
}
