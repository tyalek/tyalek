import java.util.Iterator;
import java.util.NoSuchElementException;

/** The iterator for the linked list class */
public class LinkedListIterator<S> implements Iterator<S> {
  
  // keeps track of where we are in the list
  private LLNode<S> nodeptr;
  
  /**
   * Create an iterator that starts at the input node
   * @param firstNode the node where the iteration of the list begins
   */
  public LinkedListIterator(LLNode<S> firstNode) {
    nodeptr = firstNode;
  }
  
  /**
   * Returns true if there are more elements in this iteration of the list
   * @return true if there are more elements in the list
   */
  public boolean hasNext() {
    return nodeptr != null;
  }
  
  /**
   * Returns the next element in our iteration of the list
   * @return the next element in the list
   * @throws NoSuchElementException if there are no more elements when next is called
   */
  public S next() throws NoSuchElementException {
    if (!hasNext())
      throw new NoSuchElementException();
    
    S save = nodeptr.getElement();
    nodeptr = nodeptr.getNext();
    return save;
  }
}