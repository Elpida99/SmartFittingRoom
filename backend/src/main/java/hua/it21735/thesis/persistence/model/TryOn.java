package hua.it21735.thesis.persistence.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "TryOn")
@Table(name = "TRYON")
@NamedQueries({
        @NamedQuery(name = "TryOn.findByCustomer", query = "select t from TryOn t where t.customer = :customer")
})
@SequenceGenerator(name = "TRYON_SEQ", sequenceName = "tryon_sequence", allocationSize = 1, initialValue = 1)
public class TryOn {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TRYON_SEQ")
    @Column(name = "tryon_id")
    private Long id;

    @Column(name = "timestamp")
    private LocalDateTime dateTime;

    @OneToOne
    @JoinColumn(name = "store_id")
    private Store store;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

//    @OneToMany
    @ManyToMany
//    @JoinTable(name = "tryon_garments")
    private List<Garment> garments = new ArrayList<>();

    public TryOn() {
    }

    public TryOn(LocalDateTime dateTime, Store store, Customer customer, List<Garment> garments) {
        this.dateTime = dateTime;
        this.store = store;
        this.customer = customer;
        this.garments = garments;
    }

    public Long getId() {
        return id;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public List<Garment> getGarments() {
        return garments;
    }

    public void setGarments(List<Garment> garments) {
        this.garments = garments;
    }

    @Override
    public String toString() {
        return "TryOn{" +
                "id=" + id +
                ", dateTime=" + dateTime +
                ", store=" + store +
                ", customer=" + customer +
                ", garments=" + garments +
                '}';
    }
}
