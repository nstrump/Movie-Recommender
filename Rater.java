
/**
 * Interface that implements method signature from PlainRater.java.
 * 
 * @author Nathan Trump
 * @version 1
 */
import java.util.ArrayList;
public interface Rater {
    public void addRating(String item, double rating);
    public boolean hasRating(String item);
    public String getID();
    public double getRating(String item);
    public int numRatings();
    public ArrayList<String> getItemsRated();
}
