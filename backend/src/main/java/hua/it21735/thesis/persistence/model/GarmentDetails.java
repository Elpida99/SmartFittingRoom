package hua.it21735.thesis.persistence.model;

import javax.persistence.*;

@Entity(name = "GarmentDetails")
@Table(name = "GARMENT_DETAILS")
@SequenceGenerator(name = "GARMENT_DETAILS_SEQ", sequenceName = "garmentDetails_sequence", allocationSize = 1, initialValue = 1)
public class GarmentDetails {

    @Id
    @Column(name = "garment_details_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GARMENT_DETAILS_SEQ")
    private Long id;
    @Lob
    private byte[] image;
    @Enumerated(EnumType.STRING)
    private SizeEnum size;
    @OneToOne
    @JoinColumn(name = "colour_id")
    private Colour colour;
    private boolean discount;
    @Column(name = "discount_percentage")
    private float discountPercentage;

    public GarmentDetails() {
    }

    public GarmentDetails(byte[] image, SizeEnum size, Colour colour, boolean discount, float discountPercentage) {
        this.image = image;
        this.size = size;
        this.colour = colour;
        this.discount = discount;
        this.discountPercentage = discountPercentage;
    }

    public Long getId() {
        return id;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public SizeEnum getSize() {
        return size;
    }

    public void setSize(SizeEnum size) {
        this.size = size;
    }

    public Colour getColour() {
        return colour;
    }

    public void setColour(Colour colour) {
        this.colour = colour;
    }

    public boolean hasDiscount() {
        return discount;
    }

    public void setDiscount(boolean discount) {
        this.discount = discount;
    }

    public float getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(float discountPercentage) {
        this.discountPercentage = discountPercentage;
    }
}
