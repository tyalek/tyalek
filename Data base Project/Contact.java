import java.util.*;

/**
 * A Contact class that stores and creates a contact
 * @author Tyale Kitambi
 */
public class Contact implements DataBaseType<Contact> {
  
  /** stores the name of the contact*/
  private String name; 
  
  /** stores the phone number of the contact*/
  private String phoneNum; 
  
  /** stores the e-Mail of the contact*/
  private String eMail; 
  
  /**
   * Creates the Contact using the name, phone, and email
   * @param name        the Contact's name
   * @param phoneNum    the Contact's phone number 
   * @param eMail       the Contact's e-mail
   */
  public Contact(String name, String phoneNum, String eMail) {
    this.name = name; 
    this.phoneNum = phoneNum; 
    this.eMail = eMail; 
  }
  
  /**
   * Retrieves a comparator that compares the Contacts using a specific trait. 
   * @param trait         the trait that the contacts will be compared by
   * @return Comparator   the comparator that will compare said contacts
   */
  public Comparator<Contact> getComparatorByTrait(String trait) {
    if (trait.equals("name"))
      return new CompareByName(); 
    else if (trait.equals("phone"))
      return new CompareByPhone(); 
    else if (trait.equals("email"))
      return new CompareByEmail();
    else
      return null;
  }
  
  /** 
   * Retrieves the Contact's name 
   * @return the contact's name
   */
  public String getName() {
    return name; 
  }
  
  /** 
   * Sets the Contact's name 
   * @param name: the contact's name
   */
  public void setName(String name) {
    this.name = name;
  }
  
  /** 
   * Retrieves the Contact's phone number 
   * @return the contact's phone number
   */
  public String getPhoneNumber() {
    return phoneNum; 
  }
  
  /** 
   * Sets the Contact's phone number 
   * @param phoneNum: the contact's phone number
   */
  public void setPhoneNumber(String phoneNum) {
    this.phoneNum = phoneNum; 
  }
  
  /** 
   * Retrieves the Contact's e-Mail 
   * @return the contact's e-Mail
   */
  public String getEMail() {
    return eMail; 
  }
  
  /** 
   * Sets the Contact's e-Mail  
   * @param eMail: the contact's e-Mail 
   */
  public void setEMail(String eMail) {
    this.eMail = eMail; 
  }
  
  
  /** 
   * Private nested class that compares the contacts by names 
   * Uses/implements the Comparator and overrides the compare method 
   */
  private static class CompareByName implements Comparator<Contact> {
    public int compare(Contact contact1, Contact contact2) {
      return contact1.getName().compareTo(contact2.getName());
    }
  }
  
  /** 
   * Private nested class that compares the contacts by phone numbers 
   * Uses/implements the Comparator and overrides the compare method  
   */
  private static class CompareByPhone implements Comparator<Contact> {
    public int compare(Contact contact1, Contact contact2) {
      return contact1.getPhoneNumber().compareTo(contact2.getPhoneNumber());
    }
  }
  
  /** 
   * Private nested class that compares the contacts by emails
   * Uses/implements the Comparator and overrides the compare method  
   */
  private static class CompareByEmail implements Comparator<Contact> {
    public int compare(Contact contact1, Contact contact2) {
      return contact1.getEMail().compareTo(contact2.getEMail());
    }
  } 
  
  /**
   * an overridden toString method that -->
   * creates a string representation of the class
   * @return   String representation of the class
   */
  @Override 
  public String toString() {
    
    return "[ Name: " + this.getName() + ", Phone #: " + this.getPhoneNumber() + ", Email: " + this.getEMail() + " ]"; 
  }
}