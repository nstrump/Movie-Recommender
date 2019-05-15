
/**
 * Write a description of EfficientRater here.
 * 
 * @author Nathan Trump 
 * @version 1
 */

import java.util.*;
public class EfficientRater implements Rater {
    //key in the HashMap is a movie ID and value is a rating of the movie
    //myID is the rater ID
    private String myID;
    private HashMap<String, Rating> myRatings;

    public EfficientRater(String id) {
        myID = id;
        myRatings = new HashMap<String, Rating>();
    }
    
    @Override
    public void addRating(String item, double rating) {
        //creates a Rating object and stores it in myRatings HashMap member variable
        Rating r = new Rating(item,rating);
        myRatings.put(r.getItem(), r);
    }
    
    @Override
    public boolean hasRating(String item) {
        //looks for item in the keys of the HashMap and returns true if found
        if (myRatings.containsKey(item)){
            return true;
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
        for (String movieId : myRatings.keySet()){
            if (movieId.equals(item)){
                Rating currRating = myRatings.get(movieId);
                return currRating.getValue();
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
        for (String movieID : myRatings.keySet()){
            list.add(movieID);
        }
        return list;
    }
}
