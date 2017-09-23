import java.rmi.activation.ActivationGroup;

public class Person {

    private String first;
    private String last;


    private String sex;
    private String race;
    private int age;
    private int numChildren;
    private String zipcode;
    private boolean firstClass;

    public Person(String first, String last, String sex, String race, int age, int numChildren,
                  String zipcode, boolean firstClass){
        this.first = first;
        this.last = last;
        this.sex = sex;
        this.race = race;
        this.age = age;
        this.numChildren = numChildren;
        this.zipcode = zipcode;
        this.firstClass = firstClass;

    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getNumChildren() {
        return numChildren;
    }

    public void setNumChildren(int numChildren) {
        this.numChildren = numChildren;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public boolean isFirstClass() {
        return firstClass;
    }

    public void setFirstClass(boolean firstClass) {
        this.firstClass = firstClass;
    }

}