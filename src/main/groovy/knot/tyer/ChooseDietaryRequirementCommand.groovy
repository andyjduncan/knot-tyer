package knot.tyer

import grails.validation.Validateable

class ChooseDietaryRequirementCommand implements Validateable {

    String guestId

    DietaryRequirement chosenRequirement
}
