
/**
 * Write a description of GenreFilter here.
 * 
 * @author Nathan Trump
 * @version 1
 */
public class GenreFilter implements Filter{
    
    private String genre;
    
    public GenreFilter(String genre){
        this.genre=genre;
    }
    
    @Override
    public boolean satisfies(String id) {
	String genres = MovieDatabase.getGenres(id);
	if (genres.indexOf(genre) != -1){
	    return true;
	}
	return false;
    }
}
