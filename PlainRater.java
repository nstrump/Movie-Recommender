
/**
 * PlainRater creates objects containing info for movie ratings and raters from csv files.
 * 
 * @author Nathan Trump 
 * @version 1
 */

import java.util.*;

public class PlainRater implements Rater{
    private String myID;
    private ArrayList<Rating> myRatings;

    public PlainRater(String id) {
        myID = id;
        myRatings = new ArrayList<Rating>();
    }
    
    @Override
    public void addRating(String item, double rating) {
        //creates a Rating object and stores it in myRatings arraylist member variable
        myRatings.add(new Rating(item,rating));
    }
    
    @Override
    public boolean hasRating(String item) {
        //iterates over myRatings arraylist for each entry, looks at the movie id/item, and returns true if the argument matches the
        //movie id?
        for(int k=0; k < myRatings.size(); k++){
            if (myRatings.get(k).getItem().equals(item)){
                return true;
            }
        }
        return false;
    }
    
    @Override
    public String getID() {
        return myID;
    }
    
    @Override
    public double getRating(String item) {
        //iterates over myRatings, gets the item(movie id), and checks to see if argument matches item(movie id),
        //if true, it returns the movie's rating
        //if false, returns -1
        for(int k=0; k < myRatings.size(); k++){
            if (myRatings.get(k).getItem().equals(item)){
                return myRatings.get(k).getValue();
            }
        }
        return -1;
    }
    
    @Override
    public int numRatings() {
        return myRatings.size();
    }
    
    @Override
    public ArrayList<String> getItemsRated() {
        ArrayList<String> list = new ArrayList<String>();
        for(int k=0; k < myRatings.size(); k++){
            list.add(myRatings.get(k).getItem());
        }
        return list;
    }
}
