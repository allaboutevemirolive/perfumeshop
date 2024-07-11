package com.perfume.haven.domain;

// import jakarta.persistence.*;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "perfumes")
public class Perfume {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "perfume_id_seq")
    @SequenceGenerator(name = "perfume_id_seq", sequenceName = "perfume_id_seq", initialValue = 109, allocationSize = 1)
    private Long id;

    @Column(name = "perfume_title", nullable = false)
    private String perfumeTitle;

    @Column(name = "perfumer", nullable = false)
    private String perfumer;

    @Column(name = "year", nullable = false)
    private Integer year;

    @Column(name = "country", nullable = false)
    private String country;

    @Column(name = "perfume_gender", nullable = false)
    private String perfumeGender;

    @Column(name = "fragrance_top_notes", nullable = false)
    private String fragranceTopNotes;

    @Column(name = "fragrance_middle_notes", nullable = false)
    private String fragranceMiddleNotes;

    @Column(name = "fragrance_base_notes", nullable = false)
    private String fragranceBaseNotes;

    @Column(name = "description")
    private String description;

    @Column(name = "filename")
    private String filename;

    @Column(name = "price", nullable = false)
    private Integer price;

    @Column(name = "volume", nullable = false)
    private String volume;

    @Column(name = "type", nullable = false)
    private String type;

    public Perfume() {
    }

    // TODO: Remove this? This need to be improve. Logically in ecommerce app, we
    // already insert product in database with sql, not during runtime. But maybe we stil need this
    public Perfume(Long id, String perfumeTitle, String perfumer, Integer year, String country, String perfumeGender,
            String fragranceTopNotes, String fragranceMiddleNotes, String fragranceBaseNotes, String description,
            String filename, Integer price, String volume, String type) {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPerfumeTitle() {
        return perfumeTitle;
    }

    public void setPerfumeTitle(String perfumeTitle) {
        this.perfumeTitle = perfumeTitle;
    }

    public String getPerfumer() {
        return perfumer;
    }

    public void setPerfumer(String perfumer) {
        this.perfumer = perfumer;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPerfumeGender() {
        return perfumeGender;
    }

    public void setPerfumeGender(String perfumeGender) {
        this.perfumeGender = perfumeGender;
    }

    public String getFragranceTopNotes() {
        return fragranceTopNotes;
    }

    public void setFragranceTopNotes(String fragranceTopNotes) {
        this.fragranceTopNotes = fragranceTopNotes;
    }

    public String getFragranceMiddleNotes() {
        return fragranceMiddleNotes;
    }

    public void setFragranceMiddleNotes(String fragranceMiddleNotes) {
        this.fragranceMiddleNotes = fragranceMiddleNotes;
    }

    public String getFragranceBaseNotes() {
        return fragranceBaseNotes;
    }

    public void setFragranceBaseNotes(String fragranceBaseNotes) {
        this.fragranceBaseNotes = fragranceBaseNotes;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Perfume perfume = (Perfume) o;
        return Objects.equals(id, perfume.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
