
/**
 * Write a description of MovieRunnerWithFilters here.
 * 
 * @author Nathan Trump
 * @version 1
 */
import java.util.*;

public class MovieRunnerWithFilters {
    public void printAverageRatings(){
        ThirdRatings rate3 = new ThirdRatings("ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        //ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        System.out.println("Movie database size/# of movies is: " + MovieDatabase.size() + ". Rating array size/number of raterss is  " + rate3.getRaterSize());
        ArrayList<Rating> ratingList = rate3.getAverageRatings(35);
        System.out.println(ratingList.size());
        double idxRating=10.00;
        String title = "";
        movSelectionSort(ratingList);
        for (Rating r : ratingList){
            System.out.println(r.getValue() + " " + MovieDatabase.getTitle(r.getItem()));
            double currRating = r.getValue();
            if (idxRating>currRating){
                idxRating = currRating;
                title = MovieDatabase.getTitle(r.getItem());
            }
        }
        System.out.println("Film with lowest rating in this range of minimum ratings is " + title + " and is rated " + idxRating);
    }
    
    private void movSelectionSort(ArrayList<Rating> ratingList){
        for (int k=0; k < ratingList.size()-1; k++){
            double minValue=ratingList.get(k).getValue();
            int startIdx = k;
            int currIdx = k;
            Rating minRating=ratingList.get(k);
            Rating currRating=ratingList.get(k);
            for (int n = k+1; n<ratingList.size(); n++){
                double currValue = ratingList.get(n).getValue();
                if (currValue<minValue){
                    //conditional narrows down to smallest rating and stores the position of that object's rating in currIdx 
                    minValue=currValue;
                    currIdx = n;
                    currRating = ratingList.get(n);
                }
            }
            ratingList.set(k, currRating); //places smallest value rating at 0 position
            ratingList.set(currIdx, minRating); //places supplanted rating object where the smallest rating was
        }
    }
    
    public void printAverageRatingsByYear(){
        ThirdRatings rate3 = new ThirdRatings("ratings.csv");
        ArrayList<Rating> avgRatings = rate3.getAverageRatingsByFilter(20, new YearAfterFilter(2000));
        System.out.println("# movies found: " + avgRatings.size());
        for (Rating r : avgRatings){
            String title = MovieDatabase.getTitle(r.getItem()); 
            int year = MovieDatabase.getYear(r.getItem());
            System.out.println(r.getValue() + " " + year + " " + title);            
        }
    }
    
    public void printAverageRatingsByGenre(){
        ThirdRatings rate3 = new ThirdRatings("ratings.csv");
        ArrayList<Rating> avgRatings = rate3.getAverageRatingsByFilter(20, new GenreFilter("Comedy"));
        System.out.println("# movies found: " + avgRatings.size());
        for (Rating r : avgRatings){
            String title = MovieDatabase.getTitle(r.getItem()); 
            String genres = MovieDatabase.getGenres(r.getItem());
            System.out.println(r.getValue() + " " + title);
            System.out.println("\t" + genres);
        }
    }
    
    public void printAverageRatingsByMinutes(){
        ThirdRatings rate3 = new ThirdRatings("ratings.csv");
        ArrayList<Rating> avgRatings = rate3.getAverageRatingsByFilter(5, new MinutesFilter(105, 135));
        System.out.println("# movies found: " + avgRatings.size());
        for (Rating r : avgRatings){
            String title = MovieDatabase.getTitle(r.getItem()); 
            int minutes = MovieDatabase.getMinutes(r.getItem());
            System.out.println(r.getValue() + " " + minutes + " " + title);
        }
    }
    
    public void printAverageRatingsByDirectors(){
        ThirdRatings rate3 = new ThirdRatings("ratings.csv");
        ArrayList<Rating> avgRatings = rate3.getAverageRatingsByFilter(4, new DirectorsFilter("Clint Eastwood,Joel Coen,Martin Scorsese,Roman Polanski,Nora Ephron,Ridley Scott,Sydney Pollack"));
        System.out.println("# movies found: " + avgRatings.size());
        for (Rating r : avgRatings){
            String title = MovieDatabase.getTitle(r.getItem()); 
            String directors = MovieDatabase.getDirector(r.getItem());
            System.out.println(r.getValue() + " " + title);
            System.out.println("\t" + directors);
        }
    }
    
    public void printAverageRatingsByYearAfterAndGenre(){
        ThirdRatings rate3 = new ThirdRatings("ratings.csv");
        AllFilters allFilters = new AllFilters();
        allFilters.addFilter(new GenreFilter("Drama"));
        allFilters.addFilter(new YearAfterFilter(1990));
        ArrayList<Rating> avgRatings = rate3.getAverageRatingsByFilter(8, allFilters);
        System.out.println("# movies found: " + avgRatings.size());
        for (Rating r : avgRatings){
            String title = MovieDatabase.getTitle(r.getItem()); 
            int year = MovieDatabase.getYear(r.getItem());
            String genres = MovieDatabase.getGenres(r.getItem());
            System.out.println(r.getValue() + " " + year + " " + title);
            System.out.println("\t" + genres);
        }
    }
    
    public void printAverageRatingsByDirectorsAndMinutes(){
        ThirdRatings rate3 = new ThirdRatings("ratings.csv");
        AllFilters allFilters = new AllFilters();
        allFilters.addFilter(new DirectorsFilter("Clint Eastwood,Joel Coen,Tim Burton,Ron Howard,Nora Ephron,Sydney Pollack"));
        allFilters.addFilter(new MinutesFilter(90, 180));
        ArrayList<Rating> avgRatings = rate3.getAverageRatingsByFilter(1, allFilters);
        System.out.println("# movies found: " + avgRatings.size());
        for (Rating r : avgRatings){
            String title = MovieDatabase.getTitle(r.getItem()); 
            String directors = MovieDatabase.getDirector(r.getItem());
            int minutes = MovieDatabase.getMinutes(r.getItem());
            System.out.println(r.getValue() + " " + minutes + " " + title);
            System.out.println("\t" + directors);
        }
    }
}
