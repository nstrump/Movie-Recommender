
/**
 * Write a description of ThirdRatings here.
 * 
 * @author Nathan Trump
 * @version 1
 */
import java.util.*;

public class ThirdRatings {
    private ArrayList<EfficientRater> myRaters;
    
    public ThirdRatings() {
        // default constructor
        this("ratings_short.csv");
    }
    
    public ThirdRatings(String ratingsFile){
        //second constructor
        FirstRatings rateFirst = new FirstRatings();
        myRaters= rateFirst.loadRaters(ratingsFile);
    }
    
    public int getRaterSize(){
        return myRaters.size();
    }
    
    private double getAverageByID(String id, int minimalRaters){
        //returns average movie rating for the parameter id if the number of ratings match or exceed the parameter minimalRaters
        double movieAvg = 0.0;
        double count = 0.0;
        for (EfficientRater r : myRaters){
            //this loop builds the average
            ArrayList<String> movRateList = r.getItemsRated();
            for (String s : movRateList){
                if (s.equals(id)){
                    count++;
                    movieAvg += r.getRating(id);
                }
            }
        }
        if (minimalRaters <= count){
                return movieAvg/count;
        }
        return 0.0;
    }
    
    public void testgetAverageByID(){
        System.out.println("Average is " + getAverageByID("790636", 4));
    }
    
    public ArrayList<Rating> getAverageRatings(int minimalRaters){
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        ArrayList<Rating> avgRatings = new ArrayList<Rating>();
        for (String s : movies){
            double avg = getAverageByID(s, minimalRaters);
            if (avg != 0.0){
                Rating rtg = new Rating(s, avg);
                avgRatings.add(rtg);
            }
        }
        return avgRatings;
    }
    
    public void testgetAverageRatings(){
        ArrayList<Rating> ratingList = getAverageRatings(4);
        System.out.println(ratingList.size());
        for (Rating r : ratingList){
            System.out.println(r.getItem());
            System.out.println(r.getValue());
        }
    }
    
    public ArrayList<Rating> getAverageRatingsByFilter(int minimalRaters, Filter filterCriteria){
        ArrayList<Rating> filteredAvgRatings = new ArrayList<Rating>();
        ArrayList<String> filteredMovies = MovieDatabase.filterBy(filterCriteria);
        for (String s : filteredMovies){
            double avg = getAverageByID(s, minimalRaters);
            if (avg != 0.0){
                Rating rtg = new Rating(s, avg);
                filteredAvgRatings.add(rtg);
            }
        }
        return filteredAvgRatings;
    }
}
