package hua.it21735.thesis.persistence.model;

import javax.persistence.*;

@Entity(name = "Colour")
@Table(name = "COLOUR")
@NamedQueries({
        @NamedQuery(name = "Colour.findByName", query = "select c from Colour c where c.name = :name"),
        @NamedQuery(name = "Colour.findByCode", query = "select c from Colour c where c.code = :code")
})
@SequenceGenerator(name = "COLOUR_SEQ", sequenceName = "colour_sequence", allocationSize = 1, initialValue = 1)
public class Colour {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "COLOUR_SEQ")
    @Column(name = "colour_id")
    private Long id;

    @Column(name = "colour")
    private String name;

    @Column(name = "code")
    private String code;

    public Colour() {
    }

    public Colour(String name, String code) {
        this.name = name;
        this.code = code;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String colour) {
        this.name = colour;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
