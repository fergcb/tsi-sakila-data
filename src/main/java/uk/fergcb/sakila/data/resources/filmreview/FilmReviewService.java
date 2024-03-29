package uk.fergcb.sakila.data.resources.filmreview;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
public class FilmReviewService {
    @Autowired
    private final FilmReviewRepository filmReviewRepository;

    public FilmReviewService(FilmReviewRepository filmReviewRepository) {
        this.filmReviewRepository = filmReviewRepository;
    }

    /**
     * Read all FilmReviews
     * @return an iterable collection of FilmReviews
     */
    public Page<FilmReview> readReviews(Pageable pageable) {
        return filmReviewRepository.findAll(pageable);
    }

    /**
     * Read all the FilmReviews left for a specific Film
     * @param filmId The unique ID of the Film to get reviews for
     * @return an iterable collection of FilmReviews for the given Film
     */
    public Page<FilmReview> readReviewsForFilm(Integer filmId, Pageable pageable) {
        return filmReviewRepository.findAllByFilmId(filmId, pageable);
    }

    /**
     * Read a single FilmReview from the collection, if it exists
     * @param filmReviewId The unique ID of the FilmReview resource to read
     * @return The FilmReview entity
     * @throws ResponseStatusException with 404 status code if the FilmReview doesn't exist
     */
    public FilmReview readFilmReviewById(Integer filmReviewId) {
        return filmReviewRepository.findById(filmReviewId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No FilmReview exists with that ID."));
    }

    /**
     * Create a new FilmReview resource from a Data Transfer Object
     *
     * @param filmReviewDTO the data to create the FilmReview with
     * @return The ID of the created FilmReview record
     */
    public Integer createActor(FilmReviewDTO filmReviewDTO) {
        final FilmReview filmReview = filmReviewRepository.save(new FilmReview(filmReviewDTO));
        return filmReview.getFilmReviewId();
    }

    /**
     * Update an existing FilmReview resource given a partial DTO
     * @param id The ID of the FilmReview resource to update
     * @param filmReviewDTO The data to overwrite the FilmReview with
     */
    public void updateActor(Integer id, FilmReviewDTO filmReviewDTO) {
        final FilmReview filmReview = filmReviewRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No FilmReview exists with that id."));
        filmReview.updateFromDTO(filmReviewDTO);
        filmReviewRepository.save(filmReview);
    }

    /**
     * Delete a FilmReview resource
     * @param id The ID of the FilmReview resource to delete
     */
    public void deleteActor(Integer id) {
        filmReviewRepository.deleteById(id);
    }
}
