package hua.it21735.thesis.persistence.model;

import javax.persistence.*;

@Entity(name = "Store")
@Table(name = "STORE")
@NamedQueries({
        @NamedQuery(name = "Store.findByName", query = "select s from Store s where s.name = :name"),
})
@SequenceGenerator(name = "STORE_SEQ", sequenceName = "store_sequence", allocationSize = 1, initialValue = 1)
public class Store {

    @Id()
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "STORE_SEQ")
    @Column(name = "store_id")
    private Long id;

    @Column(name = "store_name")
    private String name;

    @OneToOne
    @JoinColumn(name = "address_id")
    private Address address;

    @Column(name = "phoneNumber")
    private String phoneNumber;

    @Column(name = "email")
    private String email;

    public Store() {
    }

    public Store(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
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
}
