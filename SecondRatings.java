
/**
 * Write a description of SecondRatings here.
 * 
 * @author Nathan Trump
 * @version 4/5/2019
 */

import java.util.*;

public class SecondRatings{
    private ArrayList<Movie> myMovies;
    private ArrayList<EfficientRater> myRaters;
    
    public SecondRatings() {
        // default constructor
        this("ratedmovies_short.csv", "ratings_short.csv");
    }
    
    public SecondRatings(String movieFile, String ratingsFile){
        //second constructor
        FirstRatings rateFirst = new FirstRatings();
        myMovies = rateFirst.loadMovie(movieFile);
        myRaters= rateFirst.loadRaters(ratingsFile);
    }
    
    public int getMovieSize(){
        return myMovies.size();
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
        
        ArrayList<Rating> avgRatings = new ArrayList<Rating>();
        double currRating = 0.0;
        double count = 0.0;
        ArrayList<String> movieList = new ArrayList<String>();
        for (EfficientRater r : myRaters){
            ArrayList<String> movRateList = r.getItemsRated();
            for (int i=0; i < movRateList.size(); i++){
                if(!movieList.contains(movRateList.get(i))){
                    movieList.add(movRateList.get(i));
                }
            }
        }
        for (String s : movieList){
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
    
    public String getTitle(String id){
        //id is the ID # of movie
        String result = "ID not found";
        for (Movie m : myMovies){
            if (m.getID().equals(id)){
                return m.getTitle();
            }
        }
        return result;
    }
    
    public String getID(String title){
        //id is the ID # of movie
        String result = "NO SUCH TITLE";
        for (Movie m : myMovies){
            if (m.getTitle().equals(title)){
                return m.getID();
            }
        }
        return result;
    }
}
