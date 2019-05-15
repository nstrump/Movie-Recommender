
/**
 * Write a description of MovieRunnerAverage here.
 * 
 * @author Nathan Trump
 * @version 1
 */

import java.util.*;

public class MovieRunnerAverage {

    public void printAverageRatings(){
        SecondRatings rateSecond = new SecondRatings("ratedmoviesfull.csv","ratings.csv");
        System.out.println("Movie array size is: " + rateSecond.getMovieSize() + ". Rating array size is  " + rateSecond.getRaterSize());
        ArrayList<Rating> ratingList = rateSecond.getAverageRatings(12);
        System.out.println(ratingList.size());
        double idxRating=10.00;
        String title = "";
        for (Rating r : ratingList){
            System.out.println(r.getValue() + " " + rateSecond.getTitle(r.getItem()));
            double currRating = r.getValue();
            if (idxRating>currRating){
                idxRating = currRating;
                title = rateSecond.getTitle(r.getItem());
            }
        }
        System.out.println("Film with lowest rating in this range of minimum ratings is " + title + " and is rated " + idxRating);
    }
    
    public void getAverageRatingOneMovie(){
        SecondRatings sr = new SecondRatings("ratedmoviesfull.csv","ratings.csv");
        ArrayList<Rating> ratingList = sr.getAverageRatings(1);
        for (Rating r : ratingList){
            String movTitle = "The Maze Runner";
            if (sr.getID(movTitle).equals(r.getItem())){
                System.out.println(movTitle + " " + r.getValue());
            }
        }
    }
}
