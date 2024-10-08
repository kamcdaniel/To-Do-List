import java.util.ArrayList;
import java.util.Scanner;
public class List {

  private static ActionItem a = new ActionItem(-1, "test");
  private static ArrayList <ActionItem> urgent = new ArrayList <ActionItem>();
  private static ArrayList <ActionItem> current = new ArrayList <ActionItem>();
  private static ArrayList <ActionItem> eventual = new ArrayList <ActionItem>();
  private static ArrayList <Inactive> inactive = new ArrayList <Inactive>();
  private static ArrayList <ActionItem> completed = new ArrayList <ActionItem>();

  //public List() {
   // urgent = new ArrayList <ActionItem>();
   // current = new ArrayList <ActionItem>();
   // eventual = new ArrayList <ActionItem>();
   // inactive = new ArrayList <Inactive>();
   // completed = new ArrayList <ActionItem>();
  //}

  public static ActionItem access(int list, int num) {
    if (list == 1) {
      return urgent.get(num);
    } else if (list == 2) {
      return current.get(num);
    } else if (list == 3) {
      return eventual.get(num);
    } //still can't figure out how to return an inactive item as an actionitem
      else {
      return completed.get(num);
    }
  } 

  public static void add(ActionItem i) {
    int p = i.getPriority();
    if (p == 1) {
      urgent.add(i);
    } else if (p == 2) {
      current.add(i);
    } else if (p == 3) {
      eventual.add(i);
    } //else if (p == 4) {
      //inactive.add((Inactive)i);
    //} 
    else {
      completed.add(i);
    }
  }

  public static void findAndDelete(int cur, String name) {
    if (cur == 1) {
      for (int i = 0; i < urgent.size(); i++) {
        if (urgent.get(i).getName().equals(name)) {
          urgent.remove(i);
        }
      }
    } else if (cur == 2) {
      for (int i = 0; i < current.size(); i++) {
        if (current.get(i).getName().equals(name)) {
          current.remove(i);
        }
      }
    } else if (cur == 3) {
      for (int i = 0; i < eventual.size(); i++) {
        if (eventual.get(i).getName().equals(name)) {
          eventual.remove(i);
        }
      }
    } else {
      for (int i = 0; i < inactive.size(); i++) {
        if (inactive.get(i).getName().equals(name)) {
          inactive.remove(i);
        }
      }
    }
  }
  
  public static void print() {
    System.out.println("test");
    System.out.println("Urgent");
    for (int i = 0; i < urgent.size(); i++) {
      System.out.println(urgent.get(i).getName());
    }
    System.out.println("Current");
    for (int i = 0; i < current.size(); i++) {
      System.out.println(current.get(i).getName());
    }
    System.out.println("Eventual");
    for (int i = 0; i < eventual.size(); i++) {
      System.out.println(eventual.get(i).getName());
    }
    System.out.println("Inactive");
    for (int i = 0; i < inactive.size(); i++) {
      System.out.println(inactive.get(i).getDate() + " " + inactive.get(i).getName());
      //If multiple inactive items activate on the same day, the date should not be included twice.
    }
    System.out.println();
    System.out.println("1. Change order within priority");
    System.out.println("2. Change priority");
    System.out.println("3. Edit action item");
    System.out.println("4. Add action item");
    System.out.println("5. Scroll closed action items");
    System.out.println("6. Quit");
    Scanner scan = new Scanner(System.in);
    System.out.print("Select an option: ");
    int num = scan.nextInt();
   
    if (num == 1) {
      scan = new Scanner(System.in);
      System.out.print("What position is the item you want to move? ");
      int i = scan.nextInt() - 1;
      scan = new Scanner(System.in);
      System.out.print("Where do you want to move this item? ");
      int d = scan.nextInt() - 1;
      scan = new Scanner(System.in);
      System.out.print("What priority level would you like to edit? (1. Urgent, 2. Current, 3. Eventual, 4. Inactive): ");
      int choice = scan.nextInt();
      if (choice == 1) {
        changeWithinPriority(urgent, i, d);
      } else if (choice == 2) {
        changeWithinPriority(current, i, d);
      } else if (choice == 3) {
        changeWithinPriority(eventual, i, d);
      } else {
        changeWithinPriority(inactive, i, d);
      }
      
    } else if (num == 2) {
        scan = new Scanner(System.in);
        System.out.print("What priority is the item currently? (1. Urgent, 2. Current, 3. Eventual, 4. Inactive): ");
        int init = scan.nextInt();
        ArrayList <ActionItem> initial;
        if (init == 1) {
          initial = urgent;
        } else if (init == 2) {
          initial = current;
        } else {
          initial = eventual;
        } //else {
          //initial = inactive;
        //} FIX LATER!!
        scan = new Scanner(System.in);
        System.out.print("What position is the item you want to move? ");
        int pos = scan.nextInt() - 1;
        scan = new Scanner(System.in);
        System.out.print("Where do you want to move the item? (1. Urgent, 2. Current, 3. Eventual, 4. Inactive): ");
        int location = scan.nextInt();
        ArrayList <ActionItem> loc;
        if (location == 1) {
          changePriority(initial, pos, urgent);
        } else if (location == 2) {
          changePriority(initial, pos, current);
        } else if (location == 3) {
          changePriority(initial, pos, eventual);
        } else {
          changePriority(initial, pos, inactive);
        }
      } 
    
      else if (num == 3) {
      scan = new Scanner(System.in);
      System.out.print("What position is the item you want to move? ");
      int position = scan.nextInt() - 1;
      System.out.print("What priority is the item currently? (1. Urgent, 2. Current, 3. Eventual, 4. Inactive): ");
      int priority = scan.nextInt();
      if (priority == 1) {
        urgent.get(position).editOptions();
      } else if (priority == 2) {
        current.get(position).editOptions();
      } else if (priority == 3) {
        eventual.get(position).editOptions();
      } else {
        inactive.get(position).editOptions();
      }
    } else if (num == 4) {
      scan = new Scanner(System.in);
      System.out.print("Enter new action item: ");
      String name = scan.next();
      newActionItem(name);
    } else if (num == 5) {
      printInactive();
    } else {
      quit();
    }
  }
  
  private static void printInactive() {
    for (int i = 0; i < inactive.size(); i++) {
      for (int j = 0; j < inactive.size(); j++) {
        if (inactive.get(i).getDate() == inactive.get(j).getDate()) {
          inactive.get(j).setDate(null);
        }
      }
      System.out.println(inactive.get(i).getDate() + inactive.get(i).getName());
    }
  }

  private static void changeWithinPriority(ArrayList a, int i, int d) {
    a.add(d, a.get(i));
    a.remove(a.get(i));
  }

  private static ArrayList<ActionItem> numToAL (int num) {
    if (num == 1) {
      return urgent;
    } else if (num == 2) {
      return current;
    } else {
      return eventual;
      //FIX INACTIVE
    }
  }

  public static void changePriority(ArrayList init, int i, ArrayList fin) {
    fin.add(init.get(i));
    init.remove(init.get(i));
    if (fin.equals(urgent)) {
      urgent.get(urgent.size() - 1).setPriority(1);
    } else if (fin.equals(current)) {
      current.get(current.size() - 1).setPriority(2);
    } else if (fin.equals(eventual)) {
      eventual.get(eventual.size() - 1).setPriority(3);
    } else {
      inactive.get(inactive.size() - 1).setPriority(4);
    }
    //make sure it actually edits that object, not just the pointer
  }

  //private void delete(ArrayList a, int i) {
  //  a.remove(i);
  //} 

  private static void quit() {
    System.exit(0);
  }

  public static ArrayList getList(int i) {
    if (i == 1) {
      return urgent;
    } else if (i == 2) {
      return current;
    } else if (i == 3) {
      return eventual;
    } else if (i == 4) {
      return inactive;
    } else {
      return completed;
    }
  }

  private static void newActionItem(String name) {
    ActionItem a = new ActionItem(1, name);
    urgent.add(0, a);
  }

  private static void printCompleted() {
    for (int i = 0; i < completed.size(); i++) {
      System.out.println(completed.get(i).getName());
    }
    System.out.println("1. Delete");
    System.out.println("2. Change priority");
    System.out.println("3. Edit action item");
    Scanner scan = new Scanner(System.in);
    System.out.print("Select an option: ");
    int num = scan.nextInt();
    
    if (num == 1) {
      scan = new Scanner(System.in);
      System.out.print("What position in its category is the item you want to delete? ");
      int place = scan.nextInt();
      scan = new Scanner(System.in);
      System.out.print("What priority level is the item you want to delete? (1. Urgent, 2. Current, 3. Eventual, 4. Inactive): ");
      int del = scan.nextInt();
      if (del == 1) {
        urgent.remove(place);
      } else if (del == 2) {
        current.remove(place);
      } else if (del == 3) {
        eventual.remove(place);
      } else {
        inactive.remove(place);
      }
      
    } else if (num == 2) {
      scan = new Scanner(System.in);
      System.out.print("What priority is the item currently? (1. Urgent, 2. Current, 3. Eventual, 4. Inactive): ");
      int init = scan.nextInt();
      ArrayList <ActionItem> initial;
      if (init == 1) {
        initial = urgent;
      } else if (init == 2) {
        initial = current;
      } else {
        initial = eventual;
      } //else {
        //initial = inactive;
      //} - STILL CAN'T FIGURE OUT
      scan = new Scanner(System.in);
      System.out.print("What position is the item you want to move? ");
      int pos = scan.nextInt() - 1;
      scan = new Scanner(System.in);
      System.out.print("Where do you want to move the item? (1. Urgent, 2. Current, 3. Eventual, 4. Inactive): ");
      int location = scan.nextInt();
      ArrayList <ActionItem> loc;
      if (location == 1) {
        changePriority(initial, pos, urgent);
      } else if (location == 2) {
        changePriority(initial, pos, current);
      } else if (location == 3) {
        changePriority(initial, pos, eventual);
      } else {
        changePriority(initial, pos, inactive);
      }
    } else {
      scan = new Scanner(System.in);
      System.out.print("In which list is the item  you want to edit? (1. Urgent, 2. Current, 3. Eventual, 4. Inactive): ");
      int choice = scan.nextInt();
      scan = new Scanner(System.in);
      System.out.print("Where is the item in the list? ");
      int numChoice = scan.nextInt();
      access(choice, numChoice).editOptions();
    }
  }
  
}