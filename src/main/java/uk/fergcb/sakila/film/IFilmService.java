package uk.fergcb.sakila.film;

public interface IFilmService {
    FilmCollection readFilms();
    Film readFilm(Integer id);
    Integer createFilm(FilmDTO filmDTO);
    void updateFilm(Integer id, FilmDTO filmDTO);
    void deleteFilm(Integer id);
}
