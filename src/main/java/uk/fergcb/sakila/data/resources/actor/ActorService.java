package uk.fergcb.sakila.data.resources.actor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ActorService implements IActorService {

    @Autowired
    private final ActorRepository actorRepository;
    public ActorService (ActorRepository actorRepository) {
        this.actorRepository = actorRepository;
    }

    /**
     * Read all Actor resources from the collection
     * @return an iterable collection of Actors
     */
    @Override
    public ActorCollection readActors(Pageable pageable) {
        return new ActorCollection(actorRepository.findAll(pageable));
    }
    /**
     * Read all Actors resources from the collection whose names match a partial name string
     * @return an iterable collection of Actors
     */
    @Override
    public ActorCollection readActorsByName(String name, Pageable pageable) {
        return new ActorCollection(actorRepository.findByFullNameContainingIgnoreCase(name, pageable));
    }

    /**
     * Read a single Actor from the collection, if it exists
     * @param id The unique ID of the Actor resource to read
     * @return The Actor entity
     * @throws ResponseStatusException with 404 status code if the Actor doesn't exist
     */
    @Override
    public Actor readActor(Integer id) {
        return actorRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No actor exists with that id."));
    }

    /**
     * Create a new Actor resource from a Data Transfer Object
     *
     * @param actorDTO the data to create the Actor with
     * @return The ID of the created Actor record
     */
    @Override
    public Integer createActor(ActorDTO actorDTO) {
        final Actor actor = actorRepository.save(new Actor(actorDTO));
        return actor.getActorId();
    }

    /**
     * Update an existing Actor resource given a partial DTO
     * @param id The ID of the Actor resource to update
     * @param actorDTO The data to overwrite the Actor with
     */
    @Override
    public void updateActor(Integer id, ActorDTO actorDTO) {
        final Actor actor = actorRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No actor exists with that id."));
        actor.updateFromDTO(actorDTO);
        actorRepository.save(actor);
    }

    /**
     * Delete a Film resource
     * @param id The ID of the Film resource to delete
     */
    @Override
    public void deleteActor(Integer id) {
        actorRepository.deleteById(id);
    }
}
