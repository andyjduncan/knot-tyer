package knot.tyer

import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import spock.lang.Specification

import static knot.tyer.InvitationStatus.*

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
        view == '/rsvp/plusOne'
        model.invitation.id == invitation.id
    }

    void 'allows single guests to decline a plus one'() {
        given:
        def invitation = new Invitation(status: ACCEPTED)
                .addToGuests(new Guest(firstName: 'Alice', lastName: 'Guest'))
                .save()

        when:
        controller.noPlusOne(invitation.id)


        then:
        def saved = Invitation.get(invitation.id)
        saved.status == ATTENDEES_RESOLVED
        saved.guests[0].attending == true
        response.redirectedUrl == "/rsvp/$invitation.id"
    }

    void 'allows single guests to add a plus one'() {
        given:
        def invitation = new Invitation(status: ACCEPTED)
                .addToGuests(new Guest(firstName: 'Alice', lastName: 'Guest'))
                .save()

        when:
        controller.plusOne(invitation.id)

        then:
        view == '/rsvp/plusOneDetails'
        model.invitation.id == invitation.id
    }

    void 'saves the plus one details'() {
        given:
        def invitation = new Invitation(status: ACCEPTED)
                .addToGuests(new Guest(firstName: 'Alice', lastName: 'Guest'))
                .save()

        when:
        controller.addPlusOne(invitation.id, 'Bob', 'Name')

        then:
        def saved = Invitation.get(invitation.id)
        def plusOne = saved.guests[1]
        plusOne.firstName == 'Bob'
        plusOne.lastName == 'Name'

        and:
        saved.guests.every { it.attending }
        saved.status == ATTENDEES_RESOLVED

        and:
        response.redirectedUrl == "/rsvp/$invitation.id"
    }

    void 'asks couples who is attending'() {
        given:
        def invitation = new Invitation(status: ACCEPTED)
                .addToGuests(new Guest(firstName: 'Alice', lastName: 'Guest'))
                .addToGuests(new Guest(firstName: 'Bob', lastName: 'Guest'))
                .save()

        when:
        controller.rsvp(invitation.id)

        then:
        view == '/rsvp/chooseGuests'
        model.invitation.id == invitation.id
    }

    void 'marks both members of a couple as attending'() {
        given:
        def guest1 = new Guest(firstName: 'Alice', lastName: 'Guest')
        def guest2 = new Guest(firstName: 'Bob', lastName: 'Guest')
        def invitation = new Invitation(status: ACCEPTED)
                .addToGuests(guest1)
                .addToGuests(guest2)
                .save()

        when:
        controller.chooseGuests(invitation.id, "$guest1.id,$guest2.id")

        then:
        def saved = Invitation.get(invitation.id)
        saved.status == ATTENDEES_RESOLVED

        and:
        saved.guests.every { it.attending }

        and:
        response.redirectedUrl == "/rsvp/$invitation.id"
    }

    void 'marks only a single member of a couple as attending'() {
        given:
        def guest1 = new Guest(firstName: 'Alice', lastName: 'Guest')
        def guest2 = new Guest(firstName: 'Bob', lastName: 'Guest')
        def invitation = new Invitation(status: ACCEPTED)
                .addToGuests(guest1)
                .addToGuests(guest2)
                .save()

        when:
        controller.chooseGuests(invitation.id, "$guest1.id")

        then:
        def saved = Invitation.get(invitation.id)
        saved.status == ATTENDEES_RESOLVED

        and:
        saved.guests[0].attending == true
        saved.guests[1].attending == false

        and:
        response.redirectedUrl == "/rsvp/$invitation.id"
    }

    void 'asks for the dietary requirements of the first guest without a choice'() {
        given:
        def invitation = new Invitation(status: ATTENDEES_RESOLVED)
                .addToGuests(new Guest(firstName: 'Alice', lastName: 'Guest', attending: true, dietaryChoice: DietaryRequirement.NONE))
                .addToGuests(new Guest(firstName: 'Bob', lastName: 'Guest', attending: true))
                .save()

        when:
        controller.rsvp(invitation.id)

        then:
        view == '/rsvp/dietaryChoice'
        model.invitation.id == invitation.id
        model.guest.firstName == 'Bob'
    }

    void 'asks for the dietary requirements of the first attending guest without a choice'() {
        given:
        def invitation = new Invitation(status: ATTENDEES_RESOLVED)
                .addToGuests(new Guest(firstName: 'Alice', lastName: 'Guest', attending: false))
                .addToGuests(new Guest(firstName: 'Bob', lastName: 'Guest', attending: true))
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
        def invitation = new Invitation(status: ATTENDEES_RESOLVED)
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
