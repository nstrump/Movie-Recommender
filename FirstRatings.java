
/**
 * The FirstRatings class contains two void methods - loadMovie and loadRaters, as well as test methods for these methods. 
 * The method loadMovie creates Movie objects from the Movie class and stores them in an arraylist. 
 * The method loadRaters also creates Rater objects and stores them in an arraylist.
 * Both methods are designed to work only on CSV files.
 * 
 * @author Nathan Trump
 * @version 1
 */

import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;
import java.io.*;

public class FirstRatings {
    private ArrayList<Movie> movieList;
    
    //constructor 
    public FirstRatings(){
        movieList = new ArrayList<Movie>();
    }
    
    public ArrayList<Movie> loadMovie(String filename){
        //parses CSV records in a file, gets movie data and sets it to Movie class methods, creates a Movie object, and stores Movie
        //object in an arraylist
        File f = new File(filename);
        FileResource fr = new FileResource(f);
        for (CSVRecord record : fr.getCSVParser()){
            String id = record.get("id");
            String title = record.get("title");
            String year = record.get("year");
            String genres = record.get("genre");
            String director = record.get("director");
            String country = record.get("country");
            String poster = record.get("poster");
            int minutes = Integer.parseInt(record.get("minutes"));
            Movie currMovie = new Movie(id, title, year, genres, director, country, poster, minutes);
            movieList.add(currMovie);
        }
        return movieList;
    }
    
    public void testLoadMovies(){
        //prints out the size of movieList arraylist and prints out each Movie record
        String fileName = "ratedmoviesfull.csv";
        ArrayList<Movie> movieList = loadMovie(fileName);
        System.out.println("# of movies = " + movieList.size());
        /*
        for (Movie m : movieList){
            System.out.println(m);
        }
        */
        //Iterates over arraylist and stores genre titles in a string variable; if string contains "Comedy", adds a count
        //also has a counter for movies over 150 minutes in length
        //Finally this loop adds directors' names as keys in a hashmap and maps a count of their occurence to the values
        int genreCount=0;
        int lengthCount=0;
        HashMap <String, Integer> directorMap = new HashMap<String, Integer>();
        for (int i=0; i<movieList.size(); i++){
            String genre = movieList.get(i).getGenres();
            int minutes = movieList.get(i).getMinutes();
            String director = movieList.get(i).getDirector();
            //must add code to differentiate multiple directors and add them as separate keys
            if (! directorMap.containsKey(director)){
                directorMap.put(director, 1);
            }
            else {
                directorMap.put(director, directorMap.get(director) +1);
            }
            if (genre.indexOf("Comedy") != -1){
                genreCount++;
            }
            if (minutes > 150){
                lengthCount++;
            }
        }
        System.out.println("The # of movies in the comedy genre is " + genreCount);
        System.out.println("The # of movies longer than 150 minutes is " + lengthCount);
        
        int startCount = 0;
        String maxDirector = "";
        for (String d : directorMap.keySet()){
            int currCount = directorMap.get(d);
            if (currCount> startCount){
                startCount = currCount;
                maxDirector = d;
            }
            //System.out.println("The # of films " + d + " has directed in this file is " + directorMap.get(d));
        }
        System.out.println("Max # of films directed by one director is " + startCount + " and that director is " + maxDirector);
    }
    
    //creates Rater objects and stores them in an arraylist
    public ArrayList<EfficientRater> loadRaters(String filename){
        File f = new File("data/" + filename);
        FileResource fr = new FileResource(f);
        ArrayList<EfficientRater> rateList = new ArrayList<EfficientRater>();
        ArrayList<String> idList = new ArrayList<String>();
        for (CSVRecord record : fr.getCSVParser()){
            if (!idList.contains(record.get("rater_id"))){
                idList.add(record.get("rater_id"));
                EfficientRater rater = new EfficientRater(record.get("rater_id"));
                rater.addRating(record.get("movie_id"), Double.parseDouble(record.get("rating")));
                rateList.add(rater);
            }
            else {
                for (int i= 0; i<rateList.size(); i++){
                    EfficientRater currRater = rateList.get(i);
                    if (currRater.getID().equals(record.get("rater_id"))){
                        Double num = Double.parseDouble(record.get("rating"));
                        currRater.addRating(record.get("movie_id"), num);
                    }
                }
            }
         }
        return rateList;
    }
    
    public void testloadRaters(){
        ArrayList<EfficientRater> testRL = loadRaters("ratings.csv");
        System.out.println("The total # of raters is " + testRL.size());
        /*
        for (Rater r : testRL){
            System.out.println("Rater ID #" + r.getID() + " has rated " + r.numRatings() + " movies:");
            System.out.println("Movie ID" + "\t" + "Rating");
            for (int i = 0; i<r.getItemsRated().size(); i++){
                ArrayList<String> movieRatings = r.getItemsRated();
                System.out.println(movieRatings.get(i) + "\t" + "\t" + r.getRating(movieRatings.get(i)));
            }
        }
        */
        int startCount= 0;
        String raterID = "";
        for (EfficientRater r : testRL){
            int currCount = r.numRatings();
            if (r.getID().equals("193")){
                //this if statement finds the # of ratings for a particular rater given in the condition statement
                System.out.println(r.numRatings());
            }
            if (currCount>startCount){
                //finds the number of ratings for each rater object and stores the maximum value in startCount
                startCount= currCount;
            }
        }
        //System.out.println("The maximum number of ratings per rater in this file is " + startCount);
        ArrayList<String> numMaxRaters= new ArrayList<String>();
        for (EfficientRater r : testRL){
            if (r.numRatings()==startCount){
                //finds rater objects with a number of ratings equal to the startCount variable 
                //and adds them to the numMaxRaters arraylist
                raterID = r.getID();
                numMaxRaters.add(raterID);
            }
        }
        System.out.println(numMaxRaters.size() + " rater(s) hold(s) the max # of ratings at " + startCount + " total.");
        System.out.println("Those raters are:");
        for (int i=0; i<numMaxRaters.size(); i++){
            //iterates over arraylist with rater ids who have the max # of ratings and prints their ids out
            System.out.println(numMaxRaters.get(i));
        }
        int numRatings = 0;
        String movie = "1798709";
        for (EfficientRater r: testRL){
            //counts number of times a movie has been rated and stores count in numRatings
            if (r.hasRating(movie) == true){
                numRatings++;
            }
        }
        System.out.println("The number of times " + movie + " has been rated is " + numRatings);
        ArrayList<String> moviesRated = new ArrayList<String>();
        for (EfficientRater r: testRL){
            ArrayList<String> movInR= r.getItemsRated();
            for (int i = 0; i<movInR.size(); i++){
                if (!moviesRated.contains(movInR.get(i))){
                    moviesRated.add(movInR.get(i));
                }
            }
        }
        System.out.println("The number of movies rated is " + moviesRated.size());
    }
}
