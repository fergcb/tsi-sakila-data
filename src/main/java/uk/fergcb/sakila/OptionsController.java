package uk.fergcb.sakila;

import org.springframework.hateoas.Links;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.mediatype.hal.HalModelBuilder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import uk.fergcb.sakila.actor.ActorController;
import uk.fergcb.sakila.film.FilmController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class OptionsController {

    private static class Options extends RepresentationModel<Options> {}

    @RequestMapping(
        method = {
            RequestMethod.OPTIONS,
            RequestMethod.GET
        }
    )
    public Options getOptions() {
        Options options = new Options();

        options.add(linkTo(methodOn(ActorController.class).getActors(null)).withRel("actors").expand());
        options.add(linkTo(methodOn(FilmController.class).getFilms()).withRel("films").expand());

        return options;
    }
}