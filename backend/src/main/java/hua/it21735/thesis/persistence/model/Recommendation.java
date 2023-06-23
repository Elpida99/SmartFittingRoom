package hua.it21735.thesis.persistence.model;

import javax.persistence.*;

@Entity(name = "Recommendation")
@Table(name = "RECOMMENDATION")
@SequenceGenerator(name = "RECOMMENDATION_SEQ", sequenceName = "recommendation_sequence", allocationSize = 1, initialValue = 1)
public class Recommendation {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "")
    @Column(name = "recommendation_id")
    private Long id;

    private String outfit;

    private String garment_sku;

    public Recommendation() {
    }

    public Recommendation(final String outfit, final String garment_sku) {
        this.outfit = outfit;
        this.garment_sku = garment_sku;
    }

    public Long getId() {
        return id;
    }

    public String getOutfit() {
        return outfit;
    }

    public void setOutfit(final String outfit) {
        this.outfit = outfit;
    }

    public String getGarment_sku() {
        return garment_sku;
    }

    public void setGarment_sku(final String garment_sku) {
        this.garment_sku = garment_sku;
    }
}
