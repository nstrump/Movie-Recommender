
/**
 * Write a description of DirectorsFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DirectorsFilter implements Filter {
    private String directors;
    
    public DirectorsFilter(String directors){
        this.directors = directors;
    }
    
    public boolean satisfies(String id){
        //probably needs to be split by comma and put in an arraylist
        String movieDirectors = MovieDatabase.getDirector(id);
        if (movieDirectors.equals(directors)){
            return true;
        }
        if (directors.indexOf(movieDirectors)!=-1){
            return true;
        }
        if (movieDirectors.indexOf(directors)!=-1){
            return true;
        }
        return false;
    }
}
