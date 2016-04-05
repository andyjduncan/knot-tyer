package knot.tyer

import static knot.tyer.InvitationStatus.*

class RsvpController {

    static defaultAction = "rsvp"

    def rsvp(String id) {
        def invitation = Invitation.get(id)

        switch (invitation.status) {
            case PENDING:
                beginRsvp(invitation)
                break
            case DECLINED:
                commiserate(invitation)
                break
            case ACCEPTED:
                handleAccepted(invitation)
                break
            case ATTENDEES_RESOLVED:
                handleDietaryRequirements(invitation)
                break

        }

    }

    private beginRsvp(invitation) {
        render view: 'beginRsvp', model: [invitation: invitation]
    }

    private commiserate(invitation) {
        render view: 'commiserate', model: [invitation: invitation]
    }

    private void handleAccepted(invitation) {
        if (invitation.guests.size() == 1) {
            offerPlusOne(invitation)
        } else {
            render view: 'chooseGuests', model: [invitation: invitation]
        }
    }

    private offerPlusOne(invitation) {
        render view: 'plusOne', model: [invitation: invitation]
    }

    private void handleDietaryRequirements(invitation) {
        def firstGuestWithoutDietaryChoice = invitation.guests.find({ it.attending && it.dietaryChoice == null })

        if (firstGuestWithoutDietaryChoice) {
            render view: 'dietaryChoice', model: [invitation: invitation, guest: firstGuestWithoutDietaryChoice]
        } else {
            render view: 'summary', model: [invitation: invitation]
        }
    }

    def decline(String id) {
        def invitation = Invitation.get(id)

        invitation.status = DECLINED

        invitation.save()

        redirect action: 'rsvp', id: id
    }

    def accept(String id) {
        def invitation = Invitation.get(id)

        invitation.status = ACCEPTED

        invitation.save()

        redirect action: 'rsvp', id: id
    }

    def noPlusOne(String id) {
        def invitation = Invitation.get(id)

        invitation.guests.first().attending = true
        invitation.status = ATTENDEES_RESOLVED

        invitation.save()

        redirect action: 'rsvp', id: id
    }

    def plusOne(String id) {
        def invitation = Invitation.get(id)

        render view: 'plusOneDetails', model: [invitation: invitation]
    }

    def addPlusOne(String id, String firstName, String lastName) {
        def invitation = Invitation.get(id)

        invitation.addToGuests(new Guest(firstName: firstName, lastName: lastName))

        invitation.status = ATTENDEES_RESOLVED

        invitation.guests.each { it.attending = true }

        redirect action: 'rsvp', id: id
    }

    def chooseGuests(String id, String guestIds) {
        def ids = guestIds.split(',')

        def invitation = Invitation.get(id)

        invitation.guests.each {
            it.attending = ids.contains(it.id)
        }

        invitation.status = ATTENDEES_RESOLVED

        invitation.save()

        redirect action: 'rsvp', id: id
    }

    def chooseDietary(String id, ChooseDietaryRequirementCommand command) {
        def guest = Guest.get(command.guestId)

        guest.dietaryChoice = command.chosenRequirement

        guest.save()

        redirect action: 'rsvp', id: id
    }
}
