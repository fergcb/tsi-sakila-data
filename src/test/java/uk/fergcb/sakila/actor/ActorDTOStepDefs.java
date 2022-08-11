package uk.fergcb.sakila.actor;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import uk.fergcb.sakila.resources.actor.Actor;
import uk.fergcb.sakila.resources.actor.ActorDTO;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class ActorDTOStepDefs {

    Actor actor;
    ActorDTO actorDTO;
    Exception exception;

    @Given("the DTO has values for all not-null fields")
    public void createFullDTO() {
        actorDTO = new ActorDTO();
        actorDTO.setFirstName("First");
        actorDTO.setLastName("Last");
    }

    @Given("the DTO doesn't include a lastName")
    public void createPartialDTO() {
        actorDTO = new ActorDTO();
        actorDTO.setFirstName("First");
    }

    @When("I create a new Actor")
    public void createActor() {
        try {
            actor = new Actor(actorDTO);
        } catch (Exception ex) {
            exception = ex;
        }
    }

    @Then("the Actor should have all not-null fields")
    public void actorHasAllNotNullFields() {
        assertNotNull("firstName should not be null", actor.getFirstName());
        assertNotNull("lastName should not be null", actor.getLastName());
    }

    @Then("the Actor should not have a lastName")
    public void unsetFieldsAreNull() {
        assertNull("lastName should be null", actor.getLastName());
    }
}
