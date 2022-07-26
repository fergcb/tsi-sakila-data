package uk.fergcb.sakila.data.resources.film;

import org.springframework.data.domain.Page;
import org.springframework.hateoas.Link;
import uk.fergcb.sakila.data.hateoas.PagedCollection;

import java.util.Collection;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

public class FilmCollection extends PagedCollection<Film> {

    private final String title;

    public FilmCollection(Page<Film> page, String title) {
        super("films", page);
        this.title = title;
    }

    public FilmCollection(Page<Film> page) {
        this(page, null);
    }

    @Override
    protected Link getPreviousLink() {
        return linkTo(methodOn(FilmController.class).getFilms(title, page.getNumber() - 1, page.getSize())).withRel("previous").expand();
    }

    @Override
    protected Link getNextLink() {
        return linkTo(methodOn(FilmController.class).getFilms(title, page.getNumber() + 1, page.getSize())).withRel("next").expand();
    }

    @Override
    protected Collection<Link> getCollectionLinks() {
        return List.of(
                linkTo(methodOn(FilmController.class).getFilms(title, page.getNumber(), page.getSize())).withSelfRel().expand(),
                linkTo(methodOn(FilmController.class).getFilmById(null)).withRel("find")
        );
    }
}
