package knot.tyer

import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import knot.type.ChooseDietaryRequirementCommand
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(RsvpController)
@Mock([Invitation, Guest])
class RsvpControllerSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void 'begins the rsvp process for new rsvps'() {
        given:
        def invitation = new Invitation().save()

        when:
        controller.rsvp(invitation.id)

        then:
        view == '/rsvp/beginRsvp'
        model.invitation.id == invitation.id
    }

    void 'allows guests to decline their invitation'() {
        given:
        def invitation = new Invitation().save()

        when:
        controller.decline(invitation.id)

        then:
        Invitation.get(invitation.id).status == InvitationStatus.DECLINED
        response.redirectedUrl == "/rsvp/$invitation.id"
    }

    void "commiserates with guests who can't come"() {
        given:
        def invitation = new Invitation(status: InvitationStatus.DECLINED).save()

        when:
        controller.rsvp(invitation.id)

        then:
        view == '/rsvp/commiserate'
        model.invitation.id == invitation.id
    }

    void 'asks single guests who are coming if they are bringing a plus one'() {
        given:
        def invitation = new Invitation()
                .addToGuests(new Guest(firstName: 'Alice', lastName: 'Guest'))
                .save()

        when:
        controller.accept(invitation.id)

        then:
        view == '/rsvp/plusone'
        model.invitation.id == invitation.id
        Invitation.get(invitation.id).status == InvitationStatus.ACCEPTED
    }

    void 'redirects to dietary requirements for a couple accepting'() {
        given:
        def invitation = new Invitation()
                .addToGuests(new Guest(firstName: 'Alice', lastName: 'Guest'))
                .addToGuests(new Guest(firstName: 'Bob', lastName: 'Guest'))
                .save()

        when:
        controller.accept(invitation.id)

        then:
        response.redirectedUrl == "/rsvp/$invitation.id/dietary"
        Invitation.get(invitation.id).status == InvitationStatus.ACCEPTED
    }

    void 'asks for the dietary requirements of the first guest without a choice'() {
        given:
        def invitation = new Invitation()
                .addToGuests(new Guest(firstName: 'Alice', lastName: 'Guest', dietaryChoice: DietaryRequirement.NONE))
                .addToGuests(new Guest(firstName: 'Bob', lastName: 'Guest'))
                .save()

        when:
        controller.dietary(invitation.id)

        then:
        view == '/rsvp/dietaryChoice'
        model.invitation.id == invitation.id
        model.guest.firstName == 'Bob'
    }

    void 'redirects to summary when there are no guests without a dietary choice'() {
        given:
        def invitation = new Invitation()
                .addToGuests(new Guest(firstName: 'Alice', lastName: 'Guest', dietaryChoice: DietaryRequirement.NONE))
                .addToGuests(new Guest(firstName: 'Bob', lastName: 'Guest', dietaryChoice: DietaryRequirement.VEGETARIAN))
                .save()

        when:
        controller.dietary(invitation.id)

        then:
        response.redirectedUrl == "/rsvp/$invitation.id/summary"
    }

    void 'allows a guest to choose a dietary requirement'() {
        given:
        def guest = new Guest(firstName: 'Alice', lastName: 'Guest')
        def invitation = new Invitation()
                .addToGuests(guest)
                .save(flush: true)

        when:
        controller.chooseDietary(invitation.id,
                new ChooseDietaryRequirementCommand(
                guestId: guest.id,
                chosenRequirement: DietaryRequirement.NONE))

        then:
        Guest.get(guest.id).dietaryChoice == DietaryRequirement.NONE
        response.redirectedUrl == "/rsvp/$invitation.id/dietary"
    }
}
