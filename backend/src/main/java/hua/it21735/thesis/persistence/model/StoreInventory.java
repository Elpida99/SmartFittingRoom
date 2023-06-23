package hua.it21735.thesis.persistence.model;

import javax.persistence.*;

@Entity(name = "StoreInventory")
@Table(name = "STORE_INVENTORY")
@NamedQueries({
        @NamedQuery(name = "StoreInventory.findByStore", query = "select s from StoreInventory s where s.store = :store")
})
@SequenceGenerator(name = "STORE_INVENTORY_SEQ", sequenceName = "storeInventory_sequence", allocationSize = 1, initialValue = 1)
public class StoreInventory {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "STORE_INVENTORY_SEQ")
    @Column(name = "store_inventory_id")
    private Long id;
    @OneToOne
    @JoinColumn(name = "store_id")
    private Store store;

    @OneToOne
    @JoinColumn(name = "barcode")
    private Garment garment;

    public StoreInventory() {
    }

    public StoreInventory(Store store, Garment garment) {
        this.store = store;
        this.garment = garment;
    }

    public Long getId() {
        return id;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(final Store store) {
        this.store = store;
    }

    public Garment getGarment() {
        return garment;
    }

    public void setGarment(final Garment garment) {
        this.garment = garment;
    }
}
