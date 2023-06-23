package hua.it21735.thesis.persistence.model;

import javax.persistence.*;

@Entity(name = "Material")
@Table(name = "MATERIAL_BLEND")
@NamedQueries({
        @NamedQuery(name = "Material.findByGarmentCode", query = "select m from Material m where m.garmentCode = :garmentCode")
})
@SequenceGenerator(name = "MATERIAL_BLEND_SEQ", sequenceName = "material_sequence", allocationSize = 1, initialValue = 1)
public class Material {

    @Id
    @Column(name = "material_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MATERIAL_BLEND_SEQ")
    private Long id;

    private String material;
    private int percentage;
    private String garmentCode;

    public Material() {
    }

    public Material(final String material, final int percentage, final String garmentCode) {
        this.material = material;
        this.percentage = percentage;
        this.garmentCode = garmentCode;
    }

//    @OneToOne
//    private Garment garment;


    public String getMaterial() {
        return material;
    }

    public void setMaterial(final String material) {
        this.material = material;
    }

    public int getPercentage() {
        return percentage;
    }

    public void setPercentage(final int percentage) {
        this.percentage = percentage;
    }

    public String getGarmentCode() {
        return garmentCode;
    }

    public void setGarmentCode(final String garmentCode) {
        this.garmentCode = garmentCode;
    }
}
