package hua.it21735.thesis.rest.jpo;

import hua.it21735.thesis.persistence.model.PointOfSale;
import hua.it21735.thesis.persistence.model.StatusEnum;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PurchaseJpo {

    private String customerEmail;

    private String storeName;

    private StatusEnum statusEnum;

    private Map<Long,Float> barcodes=new HashMap<>();

    private LocalDateTime date;
    //todo: payment method?


    public PurchaseJpo() {
    }


    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(final String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(final String storeName) {
        this.storeName = storeName;
    }

    public StatusEnum getStatus() {
        return statusEnum;
    }

    public void setStatus(final StatusEnum statusEnum) {
        this.statusEnum = statusEnum;
    }

    public Map<Long, Float> getBarcodes() {
        return barcodes;
    }

    public void setBarcodes(final Map<Long, Float> barcodes) {
        this.barcodes = barcodes;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(final LocalDateTime date) {
        this.date = date;
    }
}
