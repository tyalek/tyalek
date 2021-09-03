import java.util.Comparator;
/**
 * Interface that is the type of Database 
 * @author  Tyale Kitambi
 */
public interface DataBaseType<T> {
 
 /**
  * Retrieves a comparator specified by the traits
  * @param  trait    will be used to return a specific type of comparator 
  * @return          a comparator that compares different objects
  */
 public Comparator<T> getComparatorByTrait(String trait);
  
}