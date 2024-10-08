public class PrioChange implements Runnable {

  public void run() {
    System.out.println("updated :)");
  }
  
  public void change(int dest, int current, String name) {
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
    ActionItem a = (ActionItem) List.access(current, numChoice);
    //access(current, numChoice).prioChange(dest, current, name);
    a.prioChangeAI(dest, current, name);
    }
  
  
}