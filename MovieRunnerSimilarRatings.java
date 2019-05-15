
/**
 * Write a description of MovieRunnerSimilarRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class MovieRunnerSimilarRatings {
    
    public void printAverageRatings(){
        FourthRatings rate4 = new FourthRatings();
        MovieDatabase.initialize("ratedmovies_short.csv");
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        ArrayList<Rating> ratingList = rate4.getAverageRatings(1);
        System.out.println("Movie database size/# of movies is: " + MovieDatabase.size() + ". Rating array size/number of raters is  " + RaterDatabase.size());
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
    
    public void printAverageRatingsByYearAfterAndGenre(){
        FourthRatings rate4 = new FourthRatings();
        MovieDatabase.initialize("ratedmovies_short.csv");
        AllFilters allFilters = new AllFilters();
        allFilters.addFilter(new GenreFilter("Romance"));
        allFilters.addFilter(new YearAfterFilter(1980));
        ArrayList<Rating> avgRatings = rate4.getAverageRatingsByFilter(1, allFilters);
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
        FourthRatings rate4 = new FourthRatings();
        AllFilters allFilters = new AllFilters();
        allFilters.addFilter(new DirectorsFilter("Spike Jonze,Michael Mann,Charles Chaplin,Francis Ford Coppola"));
        allFilters.addFilter(new MinutesFilter(30, 170));
        ArrayList<Rating> avgRatings = rate4.getAverageRatingsByFilter(1, allFilters);
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
