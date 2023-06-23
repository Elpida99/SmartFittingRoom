package hua.it21735.thesis.persistence.model;

import javax.persistence.*;

@Entity(name = "Customer")
@Table(name = "CUSTOMER")
@NamedQueries({
        @NamedQuery(name = "Customer.findByFirstNameAndLastName", query = "select c from Customer c where c.firstName = :firstName and c.lastName =" +
                " :lastName"),
        @NamedQuery(name = "Customer.findByPhoneNumber", query = "select c from Customer c where c.phoneNumber = :phoneNumber"),
        @NamedQuery(name = "Customer.findByEmail", query = "select c from Customer c where c.email = :email")
})
@SequenceGenerator(name = "CUSTOMER_SEQ", sequenceName = "customer_sequence", allocationSize = 1, initialValue = 1)
public class Customer {

    @Id()
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CUSTOMER_SEQ")
    @Column(name = "customer_id")
    private Long id;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "phoneNumber")
    private String phoneNumber;

    @Column(name = "email")
    private String email;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "address_id")
    private Address address;

    public Customer() {
    }

    public Customer(final String firstName, final String lastName, final String phoneNumber, final String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", address=" + address +
                '}';
    }
}
