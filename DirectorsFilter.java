
/**
 * Write a description of DirectorsFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DirectorsFilter implements Filter {
    private String[] directors;
    
    public DirectorsFilter(String directors){
        this.directors = directors.split(",");
    }
    
    @Override
    public boolean satisfies(String id){
        //probably needs to be split by comma and put in an arraylist
        String movieDirectors = MovieDatabase.getDirector(id);
        for (int n = 0; n < directors.length; n++){
            String currDirector = directors[n];
            if(movieDirectors.indexOf(currDirector) != -1){
                return true;
            }
        }
        return false;
    }
    }

