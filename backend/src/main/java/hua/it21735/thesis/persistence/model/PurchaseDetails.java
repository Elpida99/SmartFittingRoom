package hua.it21735.thesis.persistence.model;

import javax.persistence.*;

@Entity(name = "PurchaseDetails")
@Table(name = "PURCHASE_DETAILS")
@SequenceGenerator(name = "PURCHASE_SEQ", sequenceName = "purchase_sequence", allocationSize = 1, initialValue = 1)
public class PurchaseDetails {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PURCHASE_SEQ")
    @Column(name = "purchase_id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "barcode")
    private Garment product;

    @Column(name = "current_price")
    private float price;

    //todo: payment method?

    public PurchaseDetails() {
    }



    public Garment getProduct() {
        return product;
    }

    public void setProduct(Garment product) {
        this.product = product;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice() {

        price += product.getPrice();

        if (product.getGarmentDetails().hasDiscount()) {
            price -= product.getPrice() * product.getGarmentDetails().getDiscountPercentage();
        }

    }
}
