package hua.it21735.thesis.persistence.model;

import javax.persistence.*;
import java.util.Random;

@Entity(name = "Garment")
@Table(name = "GARMENT")
@NamedQueries({
        @NamedQuery(name = "Garment.findByName", query = "select g from Garment g where g.name = :name"),
        @NamedQuery(name = "Garment.findByBarcode", query = "select g from Garment g where g.barcode = :barcode"),
        @NamedQuery(name = "Garment.findBySkuNumber", query = "select g from Garment g where g.skuNumber = :skuNumber"),
        @NamedQuery(name = "Garment.findByCode", query = "select g from Garment g where g.code = :code")
})
public class Garment {


    @Column(name = "name")
    private String name;

    @Column(name = "garmentCode")
    private String code;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private float price;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne//(cascade = CascadeType.ALL)
    @JoinColumn(name = "garment_details_id")
    private GarmentDetails garmentDetails;

    @Column(name = "target_consumer")
    @Enumerated(EnumType.STRING)
    private TargetConsumer targetConsumer;

    @Id
    @Column(name = "barcode")
    private Long barcode;

    @Column(name = "skuNumber")
    private String skuNumber;

    @Column(name = "isActive")
    private boolean isActive = true;

    public Garment() {
    }

    public Garment(String name, String description,
                   float price, Category category,
                   GarmentDetails garmentDetails,
                   TargetConsumer targetConsumer, String code) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
        this.garmentDetails = garmentDetails;
        this.targetConsumer = targetConsumer;
        this.code = code;
    }

    public Garment(Garment g) {
        this.name = g.getName();
        this.description = g.getDescription();
        this.price = g.getPrice();
        this.category = g.getCategory();
//        this.garmentDetails = g.getGarmentDetails();
//        this.garmentDetails = new GarmentDetails(g.getGarmentDetails().getImage(), g.getGarmentDetails().getSize(), g.getGarmentDetails().getColour(), g.getGarmentDetails().hasDiscount(), g.getGarmentDetails().getDiscountPercentage());
        this.targetConsumer = g.getTargetConsumer();
        this.code = g.getCode();
    }

    @PrePersist
    private void saveSkuNumberAndBarcode() {
        skuNumber = category.getCategoryCode() + "-" + code + "-" + garmentDetails.getSize().name() + "-" + garmentDetails.getColour().getCode();

        Random random = new Random();
        char[] digits = new char[12];
        digits[0] = (char) (random.nextInt(9) + '1');
        for (int i = 1; i < 12; i++) {
            digits[i] = (char) (random.nextInt(10) + '0');
        }
        barcode = Long.valueOf(String.valueOf(digits));
        System.out.println(barcode);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }


    public TargetConsumer getTargetConsumer() {
        return targetConsumer;
    }

    public void setTargetConsumer(TargetConsumer targetConsumer) {
        this.targetConsumer = targetConsumer;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public GarmentDetails getGarmentDetails() {
        return garmentDetails;
    }

    public void setGarmentDetails(GarmentDetails garmentDetails) {
        this.garmentDetails = garmentDetails;
    }

    public Long getBarcode() {
        return barcode;
    }

    public void setBarcode(Long barcode) {
        this.barcode = barcode;
    }

    public String getSkuNumber() {
        return skuNumber;
    }

    public void setSkuNumber(String skuNumber) {
        this.skuNumber = skuNumber;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
