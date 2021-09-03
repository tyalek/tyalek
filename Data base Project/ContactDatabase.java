import java.util.Scanner; 
import java.util.LinkedList;
/**
 * Represents the DataBase that stores and collects contacts
 * @author   Tyale Kitambi
 */ 
public class ContactDatabase<T> extends DataBase<Contact> {
  
  /**
   * Creates a list of Contacts that is ordered within the ContactDatabase
   * @param  it:  an iterable that iterates through the list of contacts
   */
  public void printList(Iterable<Contact> it) {
    int i = 1; 
    for (Contact contact: it) {
      System.out.println(i + ":" + contact.toString()); 
      i++;
    }
  }
  
  /**
   * Main Method that allows users to create contacts and organize them using different inputs
   */
  public static void main(String args[]) {
    //a contact database that stores contacts
    ContactDatabase<Contact> cbd = new ContactDatabase<Contact>();
    //an empty linked list that stores contacts 
    LinkedList<Contact> list = new LinkedList<Contact>();
    //scanner that reads inputs
    Scanner sc = new Scanner(System.in); 
    System.out.print("Enter your command please :)");
    //stores a null contact 
    Contact c = null;
    //loops through the scanned inputs
    while (sc.hasNext()) {
       //stores the scanned inputs
       String input = sc.nextLine(); 
      //splits the string input into an array 
      String[] splitString = input.split(" "); 
      //when the user inputs add
      if (splitString[0].equals("add")) {
        String name = splitString[1];
        String phoneNum = splitString[2];
        String email = splitString[3]; 
        //creates a new contact using the name, phoneNum, and email
        Contact contact = new Contact(name, phoneNum, email); 
        //contact added to the contact database
        cbd.add(contact); 
        System.out.println(contact);     
      }
      //when the user inputs listby
      else if (splitString[0].equals("listby")) {
        //stores the trait 
        String trait = splitString[1];
        //creates an array list using the trait
        cbd.getList(trait);
        cbd.printList(cbd.getList(trait));
      }
      //when the user inputs find
      else if (splitString[0].equals("find")) {
        String expectedTrait = splitString[1];
        String value = splitString[2];
      //creates a new contact that will be used to compare inputs
        if ("name".equals(expectedTrait)) 
          c = new Contact(value, null, null); 
        else if ("phone".equals(expectedTrait)) 
          c = new Contact(null, value, null);
        else 
          c = new Contact(null, null, value); 
        //new linked list with the expected trait and contact
        list = cbd.lookup(expectedTrait, c);
        cbd.printList(list); 
      }
      //when user inputs delete 
      else if (splitString[0].equals("delete")) {
        String num = splitString[1]; 
        //stores the numeric value of the string input number
        int deleteIndex = Integer.parseInt(num); 
        System.out.println("Index: " + deleteIndex);
        //storing the head node 
        LLNode<Contact> nodeptr = cbd.getFirstNode(); 
        //counter
        int count = 0;
        //index
        int i = 0; 
        //iterates through the nodes and the string input number
        while ((i < deleteIndex) && (nodeptr != null)) {
         i++; 
         nodeptr = nodeptr.getNext(); 
        }
        //deletes the node from the contact database
        cbd.delete(nodeptr.getElement());
      }
      //creates an index
      else if (splitString[0].equals("makeindex")) {
        //stores the expected trait
        String expectedTrait = splitString[1]; 
        //makes an index
        cbd.makeIndex(expectedTrait); 
        System.out.println(cbd.getHashTable()); 
      }
      //quits the program
      else if ("quit".equals(splitString[0])) { 
        System.exit(0);
      }
      //msg for when the user puts an invalid input 
      else 
        System.out.println("Invalid Babes! Try again ;)");
    }
  }
}
      