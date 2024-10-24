package vitaly.sadikov.tours.models;

import jakarta.validation.constraints.NotEmpty;

public class Person {
    @NotEmpty
    private String name;
    @NotEmpty
    private String number;
    @NotEmpty
    private String tour;
    public Person(){

    }
    public Person(String name, String number){
        this.name = name;
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getTour() {
        return tour;
    }

    public void setTour(String tour) {
        this.tour = tour;
    }
}