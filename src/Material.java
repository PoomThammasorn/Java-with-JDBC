
public class Material {
    // attribute
    private int id;
    private String name;
    private int day;
    private String month;
    private int year;

    // constructor
    public Material(int id, String name, int day, String month, int year) {
        this.id = id;
        this.name = name;
        this.day = day;
        this.month = month;
        this.year = year;
    }

    // getter-setter
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getDay() {
        return day;
    }

    public String getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDay(int day) {
        if (day < 0) {
            this.day = java.time.LocalDate.now().getDayOfMonth();
        } else {
            this.day = day;
        }
    }

    public void setMonth(String month) {
        if (month.isEmpty()) {
            this.month = java.time.Month.of(java.time.LocalDate.now().getMonthValue()).toString();
        } else {
            this.month = month;
        }
    }

    public void setName(String name) {
        if (name.isEmpty()) {
            this.name = "No name";
        } else {
            this.name = name;
        }
    }

    public void setYear(int year) {
        if (year < 0) {
            this.year = java.time.Year.now().getValue();
        } else {
            this.year = year;
        }
    }
}
