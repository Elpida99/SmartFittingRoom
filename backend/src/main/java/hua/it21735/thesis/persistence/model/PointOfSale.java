package hua.it21735.thesis.persistence.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "PointOfSale")
@Table(name = "POINT_OF_SALE")
@NamedQueries({
        @NamedQuery(name = "PointOfSale.findByStatus", query = "select p from PointOfSale p where p.statusEnum = :status"),
        @NamedQuery(name = "PointOfSale.findByStore", query = "select p from PointOfSale p where p.store.name = :storeName"),
        @NamedQuery(name = "PointOfSale.findbyCustomer", query = "select p from PointOfSale p where p.customer = :customer"),
        @NamedQuery(name = "PointOfSale.existsByDetails_Product_Barcode", query = "select (count(p) > 0) from PointOfSale p inner join p.details details where details.product.barcode = :barcode")
})
@SequenceGenerator(name = "POINT_OF_SALE_SEQ", sequenceName = "pointOfSale_sequence", allocationSize = 1, initialValue = 1)
public class PointOfSale {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "POINT_OF_SALE_SEQ")
    @Column(name = "pos_id")
    private Long id;

    @Column(name = "date")
    private LocalDateTime date;

    @OneToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToOne
    @JoinColumn(name = "store_id")
    private Store store;

    @Enumerated(EnumType.STRING)
    @Column(name = "purchase_status")
    private StatusEnum statusEnum;

    @OneToMany
    @JoinTable(name = "BASKET")
    private List<PurchaseDetails> details = new ArrayList<>();

    public PointOfSale() {
    }

    @PrePersist
    private void saveDate() {
        date = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public List<PurchaseDetails> getDetails() {
        return details;
    }

    public void setDetails(List<PurchaseDetails> details) {
        this.details = details;
    }

    public StatusEnum getStatus() {
        return statusEnum;
    }

    public void setStatus(StatusEnum statusEnum) {
        this.statusEnum = statusEnum;
    }
}
