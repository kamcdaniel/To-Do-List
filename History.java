import java.util.ArrayList;
import java.util.Calendar;
public class History extends ActionItem {

  private String type;
  private String old;
  private String update;
  private String time;
  private String date;

  public History (int priority, String name, String type, String update) {
    super(priority, name);
    this.update = update;
    this.type = type;
    Calendar dateOfAction = Calendar.getInstance();
    time = dateOfAction.get(Calendar.HOUR_OF_DAY) + ":" + dateOfAction.get(Calendar.MINUTE);
    date = dateOfAction.get(Calendar.DAY_OF_WEEK) + " " + dateOfAction.get(Calendar.MONTH);
    
    if (type.equalsIgnoreCase("comment")) {
      old = getComment();
    } else if (type.equalsIgnoreCase("name")) {
      old = getName();
    } else {
      if (getPriority() == 1) {
        old = "Urgent";
      } else if (getPriority() == 2) {
        old = "Current";
      } else if (getPriority() == 3) {
        old = "Eventual";
      } else {
        old = "Inactive";
      }
      
    }
  }

  private String getType() {
    return type;
  }

  private String getOld() {
    return old;
  }

  private String getUpdate() {
    return update;
  }

  private String getTime() {
    return time;
  }

  private String getDate() {
    return date;
  }

  public void print() {
    System.out.println("Initial " + getType() + ": " + getOld());
    System.out.println("New " + getType() + ": " + getUpdate());
    System.out.println("Change made at: " + getTime() + " on " + getDate());
  }
  
}