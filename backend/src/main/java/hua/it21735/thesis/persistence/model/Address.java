package hua.it21735.thesis.persistence.model;

import javax.persistence.*;

@Entity(name = "Address")
@Table(name = "ADDRESS")
@NamedQueries({
        @NamedQuery(name = "Address.findSameAddress", query = "select a from Address a where a.city = :city and a.number = :number" +
                " and a.postCode = :postCode and a.street = :street")
})
@SequenceGenerator(name = "ADDRESS_SEQ", sequenceName = "address_sequence", allocationSize = 1, initialValue = 1)
public class Address {

    @Id()
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ADDRESS_SEQ")
    @Column(name = "address_id")
    private Long id;

    private String street;

    private String number;

    private String city;

    private String postCode;

    public Address() {
    }

    public Address(String city, String number, String street, String postCode) {
        this.street = street;
        this.number = number;
        this.city = city;
        this.postCode = postCode;
    }

    public Long getId() {
        return id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", street='" + street + '\'' +
                ", number='" + number + '\'' +
                ", city='" + city + '\'' +
                ", postCode='" + postCode + '\'' +
                '}';
    }
}
