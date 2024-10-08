class Inactive extends ActionItem {

  private String date;

  public Inactive (String date, int priority, String name) {
    super(priority, name);
    this.date = date;
  }

  public void setDate(String s) {
    date = s;
  }

  public String getDate() {
    return date;
  }
}