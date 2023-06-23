package hua.it21735.thesis.persistence.model;

import javax.persistence.*;

    @Entity(name = "Size")
    @Table(name = "SIZE")
    @NamedQueries({
            @NamedQuery(name = "Size.findBySizeAndGender", query = "select s from Size s where s.size = :size and s.gender = :gender"),
            @NamedQuery(name = "Size.findBySize", query = "select s from Size s where s.size = :size"),
            @NamedQuery(name = "Size.findByGender", query = "select s from Size s where s.gender = :gender")
    })
    @SequenceGenerator(name = "SIZE_SEQ", sequenceName = "size_sequence", allocationSize = 1, initialValue = 1)
    public class Size {

        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SIZE_SEQ")
        @Column(name = "size_id")
        private Long id;
        @Column(name = "size")
        @Enumerated(EnumType.STRING)
        private SizeEnum size;
        @Column(name = "waist")
        private String waist;
        @Column(name = "hips")
        private String hips;
        @Column(name = "bust")
        private String bust;
        @Column(name = "gender")
        @Enumerated(EnumType.STRING)
        private TargetConsumer gender;

        public Size() {
        }

    public Size(final SizeEnum size, final String waist, final String hips, final String bust, final TargetConsumer gender) {
        this.size = size;
        this.waist = waist;
        this.hips = hips;
        this.bust = bust;
        this.gender = gender;
    }

    public Long getId() {
        return id;
    }

    public SizeEnum getSize() {
        return size;
    }

    public void setSize(SizeEnum size) {
        this.size = size;
    }

    public TargetConsumer getGender() {
        return gender;
    }

    public void setGender(TargetConsumer sex) {
        this.gender = sex;
    }

    public String getWaist() {
        return waist;
    }

    public void setWaist(final String waist) {
        this.waist = waist;
    }

    public String getHips() {
        return hips;
    }

    public void setHips(final String hips) {
        this.hips = hips;
    }

    public String getBust() {
        return bust;
    }

    public void setBust(final String bust) {
        this.bust = bust;
    }
}
