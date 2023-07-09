package hua.it21735.thesis.rest.jpo;

import hua.it21735.thesis.persistence.model.*;

import java.util.List;

public class GarmentJpo {

    private String name;
    private String code;
    private String description;
    private float price;

    private String categoryName;
    private String categoryCode;

    private byte[] image;

    private SizeEnum size;

    private String colorName;
    private String colorCode;

    private float discountPercentage;

    private TargetConsumer targetConsumer;

    private List<Material> material;

    private boolean isActive;

    public GarmentJpo() {
    }

    public GarmentJpo(final String name, final String code, final String description, final float price, final String categoryName,
                      final String categoryCode, final byte[] image, final SizeEnum size, final String colorName, final String colorCode,
                      final float discountPercentage, final TargetConsumer targetConsumer, final boolean isActive) {
        this.name = name;
        this.code = code;
        this.description = description;
        this.price = price;
        this.categoryName = categoryName;
        this.categoryCode = categoryCode;
        this.image = image;
        this.size = size;
        this.colorName = colorName;
        this.colorCode = colorCode;
        this.discountPercentage = discountPercentage;
        this.targetConsumer = targetConsumer;
        this.isActive = isActive;
    }

    public Garment toGarment() {

        return new Garment(name, description, price, new Category(categoryName, categoryCode),
                new GarmentDetails(image, size, new Colour(colorName, colorCode), discountPercentage != 0.0, discountPercentage), targetConsumer, code);
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(final String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(final float price) {
        this.price = price;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(final String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(final String categoryCode) {
        this.categoryCode = categoryCode;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(final byte[] image) {
        this.image = image;
    }

    public SizeEnum getSize() {
        return size;
    }

    public void setSize(final SizeEnum size) {
        this.size = size;
    }

    public String getColorName() {
        return colorName;
    }

    public void setColorName(final String colorName) {
        this.colorName = colorName;
    }

    public String getColorCode() {
        return colorCode;
    }

    public void setColorCode(final String colorCode) {
        this.colorCode = colorCode;
    }

    public float getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(final float discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public TargetConsumer getTargetConsumer() {
        return targetConsumer;
    }

    public void setTargetConsumer(final TargetConsumer targetConsumer) {
        this.targetConsumer = targetConsumer;
    }

    public List<Material> getMaterial() {
        return material;
    }

    public void setMaterial(final List<Material> material) {
        this.material = material;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(final boolean active) {
        isActive = active;
    }

}
