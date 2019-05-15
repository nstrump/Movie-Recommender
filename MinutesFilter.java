
/**
 * Write a description of MinutesFilter here.
 * 
 * @author Nathan Trump 
 * @version 1
 */
public class MinutesFilter implements Filter{
    
    private int min;
    private int max;
    
    public MinutesFilter(int minMinutes, int maxMinutes){
        min = minMinutes;
        max = maxMinutes;        
    }
    
    @Override
    public boolean satisfies(String id){
        int time = MovieDatabase.getMinutes(id);
        if (time>=min && time<=max){
            return true;
        }
        return false;
    }
    
        

}
