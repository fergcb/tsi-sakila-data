package uk.fergcb.sakila.film;

import uk.fergcb.sakila.language.Language;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;

@Entity
@Table(name="film")
public class Film {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="film_id")
    private int filmId;

    @Column(name="title")
    private String title;

    @Column(name="description")
    private String description;

    @Column(name="release_year")
    private Date releaseYear;

    @ManyToOne
    @JoinColumn(name="language_id", nullable=false)
    private Language language;

    @ManyToOne
    @JoinColumn(name="original_language_id")
    private Language originalLanguage;

    @Column(name="rental_duration")
    private Integer rentalDuration;

    @Column(name="rental_rate")
    private BigDecimal rentalRate;

    @Column(name="length")
    private Integer length;

    @Column(name="replacement_cost")
    private BigDecimal replacementCost;

    @Column(name="rating")
    private String rating;

    @Column(name="special_features")
    private String specialFeatures;

    public Film(FilmDTO filmDTO) {
        this.updateFromDTO(filmDTO);
    }

    public Film() {}

    public void updateFromDTO(FilmDTO filmDTO) {
        this.title = filmDTO.getTitle().orElse(title);
        this.description = filmDTO.getDescription().orElse(description);
        this.releaseYear = filmDTO.getReleaseYear().orElse(releaseYear);
        this.language = filmDTO.getLanguage().orElse(language);
        this.originalLanguage = filmDTO.getOriginalLanguage().orElse(originalLanguage);
        this.rentalDuration = filmDTO.getRentalDuration().orElse(rentalDuration);
        this.rentalRate = filmDTO.getRentalRate().orElse(rentalRate);
        this.length = filmDTO.getLength().orElse(length);
        this.replacementCost = filmDTO.getReplacementCost().orElse(replacementCost);
        this.rating = filmDTO.getRating().orElse(rating);
        this.specialFeatures = filmDTO.getSpecialFeatures().orElse(specialFeatures);
    }

    public int getFilmId() {
        return filmId;
    }

    public void setFilmId(int filmId) {
        this.filmId = filmId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(Date releaseYear) {
        this.releaseYear = releaseYear;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public Language getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguage(Language originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public Integer getRentalDuration() {
        return rentalDuration;
    }

    public void setRentalDuration(Integer rentalDuration) {
        this.rentalDuration = rentalDuration;
    }

    public BigDecimal getRentalRate() {
        return rentalRate;
    }

    public void setRentalRate(BigDecimal rentalRate) {
        this.rentalRate = rentalRate;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public BigDecimal getReplacementCost() {
        return replacementCost;
    }

    public void setReplacementCost(BigDecimal replacementCost) {
        this.replacementCost = replacementCost;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getSpecialFeatures() {
        return specialFeatures;
    }

    public void setSpecialFeatures(String specialFeatures) {
        this.specialFeatures = specialFeatures;
    }
}