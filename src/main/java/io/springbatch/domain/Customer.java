package io.springbatch.domain;

import java.util.Date;

/**
 * Created by willharmer on 09/10/2016.
 */
public class Customer {

    private final long id;
    private final String firstName;
    private final String lastName;
    private final Date birthdate;

    public Customer(long id, String fName, String lName, Date birthdate){
        this.id = id;
        this.firstName = fName;
        this.lastName = lName;
        this.birthdate = birthdate;
    }

    @Override
    public String toString(){
        return "Customer {" +
                "id=" + id +
                ", firstname='" + firstName + '\'' +
                ", lastname='" + lastName + '\'' +
                ", birthdate='" + birthdate + '\'' +
                "}";
    }
}
