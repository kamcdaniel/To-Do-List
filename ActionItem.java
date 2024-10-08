import java.util.concurrent.ScheduledExecutorService;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.Executors;

public class ActionItem {
  
  private int priority;
  private String name;
  private ArrayList <History> h = new ArrayList <History>();
  private String comment;
  private int daysUntilUrgent = 0;
  private int daysUntilCurrent = 0;
  private int daysUntilEventual = 0;
  
  public ActionItem (int priority, String name) {
    this.priority = priority;
    this.name = name;
    comment = "";
  }

  public void setDaysUntilUrgent(int days) {
    daysUntilUrgent = days;
  }
  public int getDaysUntilUrgent() {
    return daysUntilUrgent;
  }

  public void setDaysUntilCurrent(int days) {
    daysUntilCurrent = days;
  }
  public int getDaysUntilCurrent() {
    return daysUntilCurrent;
  }

  public void setDaysUntilEventual(int days) {
    daysUntilEventual = days;
  }
  public int getDaysUntilEventual() {
    return daysUntilEventual;
  }

  public void setPriority (int n) {
    this.priority = n;
    History newH = new History (getPriority(), getName(), "prio", Integer.toString(n));
    List.add(newH);
  }

  public void changeName (String n) {
    name = n;
    History newH = new History (getPriority(), getName(), "name", n);
    List.add(newH);
  }

  public int getPriority() {
    return priority;
  }

  public String getName() {
    return name;
  }

  public void addComment(String add) {
    this.comment = comment + add;
    History newH = new History (getPriority(), getName(), "comment", add);
    h.add(newH);
  }

  public String getComment() {
    return comment;
  }

  public void print() {
    System.out.println("Name: " + getName());
    if (getPriority() == 1) {
      System.out.println("Priority: Urgent");
    } else if (getPriority() == 2) {
      System.out.println("Priority: Current");
    } else if (getPriority() == 3) {
      System.out.println("Priority: Eventual");
    } else {
      System.out.println("Priority: Inactive");
    }
    for (int i = 0; i < h.size(); i++) {
      h.get(i).print();
    }
  }

  public void prioChangeAI(int dest, int current, String name) {
    //setPriority(dest);
    int numChoice = -1;
    if (current == 1) {
      for (int i = 0; i < List.getList(1).size(); i++) {
        if (List.access(1, i).getName().equals(name)) {
          numChoice = i;
        }
      } 
    } else if (current == 2) {
       for (int i = 0; i < List.getList(2).size(); i++) {
          if (List.access(2, i).getName().equals(name)) {
           numChoice = i;
          }
        } 
      } else if (current == 3) {
        for (int i = 0; i < List.getList(3).size(); i++) {
          if (List.access(3, i).getName().equals(name)) {
            numChoice = i;
          }
        } 
      } else if (current == 4) {
        for (int i = 0; i < List.getList(4).size(); i++) {
          if (List.access(4, i).getName().equals(name)) {
            numChoice = i;
          }
        } 
      } else {
        for (int i = 0; i < List.getList(5).size(); i++) {
          if (List.access(5, i).getName().equals(name)) {
            numChoice = i;
          }
        } 
      }
    List.changePriority(List.getList(current), numChoice, List.getList(dest));
    
    //List.add(ActionItem); 
    //List.findAndDelete(current, name);
  }

  public void editOptions() {
    System.out.println("1. Change name");
    //System.out.println("2. Change priority");
    System.out.println("3. Set dates to update status");
    System.out.println("4. Add/edit comment"); //add, change, delete
    System.out.println("5. History");
    System.out.println("6. Print");
    Scanner scan = new Scanner(System.in);
    System.out.print("Select an option: ");
    int choice = scan.nextInt();
    if (choice == 1) {
      scan = new Scanner(System.in);
      System.out.print("Enter new name: ");
      String name = scan.next();
      changeName(name);
    } /* else if (choice == 2) {
      scan = new Scanner(System.in);
      System.out.print("Enter new priority level (1, 2, 3, or 4): ");
      int priority = Integer.valueOf(scan.next());
      prioChange(priority, getPriority(), getName());
    } */ else if (choice == 3) {
      scan = new Scanner(System.in);
      System.out.print("Input # of days until you want to elevate this task to urgent: ");
      int u = scan.nextInt();
      setDaysUntilUrgent(u);
      //trying something new
      ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(3);
      PrioChange p1 = new PrioChange();
      p1.run();
      p1.change(1, getPriority(), getName());
      scheduledExecutorService.schedule(p1, u, TimeUnit.DAYS);
      System.out.print("Input # of days until you want to elevate this task to current: ");
      scan = new Scanner(System.in);
      int c = scan.nextInt();
      setDaysUntilUrgent(c);
      PrioChange p2 = new PrioChange();
      p2.run();
      p2.change(2, getPriority(), getName());
      scheduledExecutorService.schedule(p2, c, TimeUnit.DAYS);
      System.out.print("Input # of days until you want to elevate this task to eventual: ");
      scan = new Scanner(System.in);
      int e = scan.nextInt();
      setDaysUntilUrgent(e);
      PrioChange p3 = new PrioChange();
      p3.run();
      p3.change(3, getPriority(), getName());
      scheduledExecutorService.schedule(p3, e, TimeUnit.DAYS);
    } 
    
      else if (choice == 4) {
      scan = new Scanner(System.in);
      System.out.print("Enter new comment: ");
      String comment = scan.next();
      addComment(comment);
    } else if (choice == 5) {
      for (int i = 0; i < h.size(); i++) {
        h.get(i).print();
      }
    } else {
      System.out.println(getName());
      System.out.println(getPriority());
      System.out.println("Days until urgent: " + getDaysUntilUrgent());
      System.out.println("Days until current: " + getDaysUntilCurrent());
      System.out.println("Days until eventual: " + getDaysUntilEventual());
      for (int i = 0; i < h.size(); i++) {
        h.get(i).print();
      }
    }
  }
  
}