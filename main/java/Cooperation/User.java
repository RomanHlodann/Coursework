package Cooperation;

import Tariff.BaseTariff;
import java.io.Serializable;
import java.util.Scanner;

public class User implements Serializable{
    String name;
    String surname;
    String telephoneNumber;
    public User(String name, String surname, String telephoneNumber){
        this.name = name;
        this.surname = surname;
        this.telephoneNumber = telephoneNumber;
    }
    public String getTelephoneNumber(){
        return telephoneNumber;
    }

    public String getName() {
        return name;
    }
    public String getSurname() {
        return surname;
    }
    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", telephoneNumber='" + telephoneNumber + '\'' +
                '}';
    }
}
