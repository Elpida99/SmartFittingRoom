package hua.it21735.thesis.persistence.model;

import javax.persistence.*;

@Entity(name = "Category")
@Table(name = "CATEGORY")
@NamedQueries({
        @NamedQuery(name = "Category.findByCategoryName", query = "select c from Category c where c.categoryName = :categoryName"),
        @NamedQuery(name = "Category.findByCategoryCode", query = "select c from Category c where c.categoryCode = :categoryCode")
})
@SequenceGenerator(name = "CATEGORY_SEQ", sequenceName = "category_sequence", allocationSize = 1, initialValue = 1)
public class Category {

    @Id()
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CATEGORY_SEQ")
    @Column(name = "category_id")
    private Long id;

    @Column(name = "category_name")
    private String categoryName;

    @Column(name = "code")
    private String categoryCode;

    public Category() {
    }

    public Category(String categoryName, String categoryCode) {
        this.categoryName = categoryName;
        this.categoryCode = categoryCode;
    }

    public Long getId() {
        return id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }
}
