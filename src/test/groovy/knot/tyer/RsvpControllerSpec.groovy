package knot.tyer

import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import knot.type.ChooseDietaryRequirementCommand
import spock.lang.Specification

import static knot.tyer.InvitationStatus.ACCEPTED
import static knot.tyer.InvitationStatus.DECLINED

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
        Invitation.get(invitation.id).status == DECLINED
        response.redirectedUrl == "/rsvp/$invitation.id"
    }

    void 'allows guests to accept their invitation'() {
        given:
        def invitation = new Invitation().save()

        when:
        controller.accept(invitation.id)

        then:
        Invitation.get(invitation.id).status == ACCEPTED
        response.redirectedUrl == "/rsvp/$invitation.id"
    }

    void "commiserates with guests who can't come"() {
        given:
        def invitation = new Invitation(status: DECLINED).save()

        when:
        controller.rsvp(invitation.id)

        then:
        view == '/rsvp/commiserate'
        model.invitation.id == invitation.id
    }

    void 'asks single guests who are coming if they are bringing a plus one'() {
        given:
        def invitation = new Invitation(status: ACCEPTED)
                .addToGuests(new Guest(firstName: 'Alice', lastName: 'Guest'))
                .save()

        when:
        controller.rsvp(invitation.id)

        then:
        view == '/rsvp/plusone'
        model.invitation.id == invitation.id
    }

    void 'asks couples who are coming about their dietary requirements'() {
        given:
        def invitation = new Invitation(status: ACCEPTED)
                .addToGuests(new Guest(firstName: 'Alice', lastName: 'Guest'))
                .addToGuests(new Guest(firstName: 'Bob', lastName: 'Guest'))
                .save()

        when:
        controller.rsvp(invitation.id)

        then:
        view == '/rsvp/dietaryChoice'
        model.invitation.id == invitation.id
        model.guest.firstName == 'Alice'
    }

    void 'asks for the dietary requirements of the first guest without a choice'() {
        given:
        def invitation = new Invitation(status: ACCEPTED)
                .addToGuests(new Guest(firstName: 'Alice', lastName: 'Guest', dietaryChoice: DietaryRequirement.NONE))
                .addToGuests(new Guest(firstName: 'Bob', lastName: 'Guest'))
                .save()

        when:
        controller.rsvp(invitation.id)

        then:
        view == '/rsvp/dietaryChoice'
        model.invitation.id == invitation.id
        model.guest.firstName == 'Bob'
    }

    void 'allows a guest to choose a dietary requirement'() {
        given:
        def guest = new Guest(firstName: 'Alice', lastName: 'Guest')
        def invitation = new Invitation(status: ACCEPTED)
                .addToGuests(guest)
                .save(flush: true)

        when:
        controller.chooseDietary(invitation.id,
                new ChooseDietaryRequirementCommand(
                        guestId: guest.id,
                        chosenRequirement: DietaryRequirement.NONE))

        then:
        Guest.get(guest.id).dietaryChoice == DietaryRequirement.NONE
        response.redirectedUrl == "/rsvp/$invitation.id"
    }

    void 'shows the rsvp summary when there are no guests without a dietary choice'() {
        given:
        def invitation = new Invitation(status: ACCEPTED)
                .addToGuests(new Guest(firstName: 'Alice', lastName: 'Guest', dietaryChoice: DietaryRequirement.NONE))
                .addToGuests(new Guest(firstName: 'Bob', lastName: 'Guest', dietaryChoice: DietaryRequirement.VEGETARIAN))
                .save()

        when:
        controller.rsvp(invitation.id)

        then:
        view == '/rsvp/summary'
        model.invitation.id == invitation.id
    }
}
