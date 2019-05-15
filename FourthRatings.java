
/**
 * Write a description of FourthRatings here.
 * 
 * @author Nathan Trump
 * @version 1
 */
import java.util.*;

public class FourthRatings {
    private double getAverageByID(String id, int minimalRaters){
        //returns average movie rating for the parameter id if the number of ratings match or exceed the parameter minimalRaters
        double movieAvg = 0.0;
        double count = 0.0;
        RaterDatabase.initialize("ratings_short.csv");
        ArrayList <Rater> raterDB = RaterDatabase.getRaters();
        for (Rater r : raterDB){
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
        System.out.println("Average is " + getAverageByID("0790636", 1));
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
        RaterDatabase.initialize("ratings_short.csv");
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
    
    private double dotProduct(Rater me, Rater r){
        double dotProd = 0;
        double adjustor = -5.00;
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        for (String mov : movies){
            if (me.hasRating(mov) && r.hasRating(mov)){
                double scaleMe = me.getRating(mov) - adjustor;
                double scaleR = r.getRating(mov) - adjustor;
                double currDot = scaleMe*scaleR;
                dotProd += currDot;
            }
        }        
        return dotProd;
    }
    
    private ArrayList<Rating> getSimilarities(String id){
        ArrayList<Rating> list = new ArrayList<Rating>();
        Rater me = RaterDatabase.getRater(id);
        for (Rater r : RaterDatabase.getRaters()){
            if (!me.getID().equals(r.getID())){
                double dotProd = dotProduct(me, r);
                if (dotProd > 0){
                    Rating currRating = new Rating(r.getID(), dotProd);
                    list.add(currRating);
                }
            }
        }
        Collections.sort(list, Collections.reverseOrder());
        return list;
    }
    
    public ArrayList<Rating> getSimilarRatings(String id, int numSimilarRaters, int minimalRaters){
        ArrayList<Rating> result = new ArrayList<Rating>();
        
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        
        ArrayList<Rating> dotProdRatings = getSimilarities(id);
        ArrayList<Rating> avgRatings = getAverageRatings(minimalRaters);
       
             
        for (int i = 0; i <= numSimilarRaters; i++){
            Rating numSimRating = dotProdRatings.get(i);
            String numSimRaterID = numSimRating.getItem();
            Double numSimDot = numSimRating.getValue();
                  
        }
        return result;
    }
     
}

