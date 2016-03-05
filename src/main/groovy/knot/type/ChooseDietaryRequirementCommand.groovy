package knot.type

import grails.validation.Validateable
import knot.tyer.DietaryRequirement

class ChooseDietaryRequirementCommand implements Validateable {

    String guestId

    DietaryRequirement chosenRequirement
}
