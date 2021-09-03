/**
 * Class that represents DataBase
 * @author  Tyale Kitambi
 */
import java.util.LinkedList;
import java.util.Iterator; 
import java.util.Hashtable;
import java.util.ArrayList; 
import java.util.NoSuchElementException;
import java.util.Comparator;
import java.util.Collections;

public class DataBase<T extends DataBaseType<T>> implements Iterable<T> { 
  
  /** stores the different indexes available to sort the data*/
  private Hashtable<String, ArrayList<T>> hashtable = new Hashtable<String, ArrayList<T>>(); 
  
  /** stores the nodes within the DataBase*/
  private LLNode<T> node; 
  
  /** stores the number of elements within the DataBase */
  private int numOfElements = 0; 
  
  /**
   * Retrieves the first node of the database
   * @return : the first node
   */
  protected LLNode<T> getFirstNode() {
    return node;
  }
  
  /**
   * Changes the front node.
   * @param node  the node that will be the first node of the new linked list
   */
  protected void setFirstNode(LLNode<T> node) {
    this.node = node;
  }
  
  /**
   * Return whether the list is empty
   * @return true if the list is empty
   */
  public boolean isEmpty() {
    return (getFirstNode() == null);
  }
  
  /**
   * Retrieves the hashtable
   * @return the hashtable
   */
  public Hashtable<String, ArrayList<T>> getHashTable() {
    return hashtable;
  }
  
  /**
   * Returns an iterator that iterates, or loops, over the data of the list
   * @return an iterator for the linked list
   */
  public LinkedListIterator<T> iterator() {
    return new LinkedListIterator<T>(getFirstNode()); 
  }
  
  
  /** 
   * Adds an element to the database
   * @param element  an element to be added to database.
   */
  public void add(T element) {  
    if (isEmpty()) {
      setFirstNode(new LLNode<T>(element, null));
    }
    else {
      //node that acts a pointer for the different nodes
      LLNode<T> nodeptr = getFirstNode();
      //iterates through the nodes 
      while (nodeptr.getNext() != null) {
        nodeptr = nodeptr.getNext();
      }
      nodeptr.setNext(new LLNode<T>(element, null));
      hashtable.clear();
    }
    numOfElements++;
  }
  
  /** 
   * Deletes an element from the database
   * @param element  an element to be deleted from database.
   */
  public void delete(T element) {
    if (!isEmpty()) {
      //storing the head node
      LLNode<T> nodeptr = getFirstNode(); 
      //iterates through the nodes 
      while (nodeptr.getNext() != null) {
        if (!nodeptr.getNext().getElement().equals(element)) {
          //sets the next node to the current node pointer
          nodeptr = nodeptr.getNext(); 
        }
        else if (nodeptr.getNext().getElement().equals(element)) {
          //sets the next node to the node after the next node. 
          nodeptr.setNext(nodeptr.getNext().getNext());  
        }
      }
      //checks if the element is the first node 
      LLNode<T> first = getFirstNode(); 
      if (first.getElement().equals(element)) 
        setFirstNode(first.getNext()); 
        hashtable.clear();
        numOfElements--;
    } 
  }
  
  /**
   * Retrieves the number of elements in the database
   * @return numOfElements   the number of elements in the database
   */
  public int size() {
    return numOfElements;
  }
  
  /**
   * Creates a list of elements that match the input element
   * @param element:       the element that will be compared
   * @param comparator:    the comparator that will compare the element
   * @return: the list of matching elements
   */
  public LinkedList<T> lookupInList(T element, Comparator<T> comparator) {
    //linked list that'll store the matched elements
    LinkedList<T> list = new LinkedList<T>(); 
    if(!isEmpty()) {
      //iterates through the database to find the matching element's trait
      for (LLNode<T> nodeptr = getFirstNode(); nodeptr != null; nodeptr = nodeptr.getNext()) { 
        if (comparator.compare(element, nodeptr.getElement()) == 0) { 
          //adds the matching element to the linked list
          list.add(nodeptr.getElement()); 
        }
      }
    }
    return list;
  }
  
  /**
   * Creates a list of elements that match the input element
   * @param element:      the element that will be compared
   * @param index:        the arraylist that the elements will be stored in 
   * @param comparator:   the comparator that will compare the element 
   * @return: the list of matching elements
   */
  public LinkedList<T> lookupInIndex(T element, ArrayList<T> index, Comparator<T> comparator) {
    //linked list that'll store the matched elements
    LinkedList<T> list = new LinkedList<T>(); 
    if (!isEmpty()) {
      //stores the position of the element in the database 
      int i = Collections.binarySearch(index, element, comparator); 
      if (i < 0) 
        return list; 
      else
        //iterates through the arraylist 
        for (int j = 0; j < index.size(); j++) {
        if (comparator.compare(element, index.get(j)) == 0) {
          list.add(index.get(j)); 
        }
      }
    }
    return list; 
  }
  
  /** 
   * Adds an index that makes a hashtable sorted by the trait input
   * @param trait:  used to organize the database by name, phone number, or email
   */
  public void makeIndex(String trait) {
    //comparator from the first node's element
    Comparator<T> comparator = getFirstNode().getElement().getComparatorByTrait(trait);
    //arraylist that will be sorted 
    ArrayList<T> arraylist = new ArrayList<T>();
    //will be used to iterate through the database's elements
    LinkedListIterator<T> iterator = this.iterator(); 
    if (!isEmpty()) {
      //will be used to iterate through the database's elements
      for (T element: this) {
        arraylist.add(element);
      }
    }
    //sorts through the arraylist using the comparator 
    Collections.sort(arraylist, comparator); 
    getHashTable().put(trait, arraylist);
  }
  
  /**
   * Returns a linked list with all the elements that were specified
   * @param trait         the trait that the contact will be compared by
   * @param element       the element that is being searched for 
   * @return list of matching elements 
   */
  public LinkedList<T> lookup(String trait, T element) {
    if (!isEmpty()) {
      //if the hashtable contains the trait
      if(getHashTable().containsKey(trait)) { 
        //retrieves the matched elements in a linked list using an arraylist
        return lookupInIndex(element, getHashTable().get(trait), element.getComparatorByTrait(trait)); 
      }
      else {
        //retrieves the matched elements in a linked list
        return lookupInList(element, element.getComparatorByTrait(trait));
      } 
    }
    else
      //returns an empty linked list
      return new LinkedList<T>();
  } 
  
  /**
   * Returns an arraylist that has a hashtable organized by the traits
   * @param trait  used to organize the database by name, phone number, or email
   */
  public ArrayList<T> getList(String trait) {
    //if the hashtable contains the trait
    if (getHashTable().containsKey(trait)) {
      //retrieves the hashtable with that trait
      return getHashTable().get(trait);
    }
    else {
      if (!isEmpty()) {
        //creates a linked list with the trait
        makeIndex(trait); 
        //retrieves the hashtable with that trait        
        return getHashTable().get(trait);
      }
      else {
        //returns an empty arraylist
        return new ArrayList<T>();
      }
    }
  } 
  
  /**
   * an overridden toString method that -->
   * creates a string representation of the class
   * @return   String representation of the class
   */
  @Override
  public String toString() {
    String result = "";
    LLNode<T> current = this.getFirstNode(); 
    while (current != null) {
      result += current.getElement(); 
      if (current.getNext() != null) {result += ", ";
      }
      current = current.getNext(); 
    }
    return "Database: " + result; 
  }
}