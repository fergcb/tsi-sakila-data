package uk.fergcb.sakila.data.resources.filmcategory;

import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface FilmCategoryRepository extends CrudRepository<FilmCategory, FilmCategoryKey> {
    Set<FilmCategory> findByFilmCategoryKeyFilmId(Integer filmId);
}
