package hua.it21735.thesis.rest.jpo;

import hua.it21735.thesis.persistence.model.SizeEnum;

public class StoreAvailabilityJpo {

    private String storeId;
    private SizeEnum size;
    private String colour;
    private int quantity;

    public StoreAvailabilityJpo(String storeId, SizeEnum size, String colour, int quantity) {
        this.storeId = storeId;
        this.size = size;
        this.colour = colour;
        this.quantity = quantity;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(final String storeId) {
        this.storeId = storeId;
    }

    public SizeEnum getSize() {
        return size;
    }

    public void setSize(final SizeEnum size) {
        this.size = size;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(final String colour) {
        this.colour = colour;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(final int quantity) {
        this.quantity = quantity;
    }
}
