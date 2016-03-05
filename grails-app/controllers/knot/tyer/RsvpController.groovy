package knot.tyer

import knot.type.ChooseDietaryRequirementCommand

class RsvpController {

    static defaultAction = "rsvp"

    def rsvp(String id) {
        def invitation = Invitation.get(id)

        switch (invitation.status) {
            case InvitationStatus.PENDING:
                render view: 'beginRsvp', model: [invitation: invitation]
                break
            case InvitationStatus.DECLINED:
                render view: 'commiserate', model: [invitation: invitation]
                break
        }

    }

    def decline(String id) {
        def invitation = Invitation.get(id)

        invitation.status = InvitationStatus.DECLINED

        invitation.save()

        redirect action: 'rsvp', params: [id: id]
    }

    def accept(String id) {
        def invitation = Invitation.get(id)

        invitation.status = InvitationStatus.ACCEPTED

        invitation.save()

        if (invitation.guests.size() == 1) {
            render view: 'plusone', model: [invitation: invitation]
        } else {
            redirect action: 'dietary', params: [id: invitation.id]
        }
    }

    def dietary(String id) {
        def invitation = Invitation.get(id)

        def firstGuestWithoutDietaryChoice = invitation.guests.find({ it.dietaryChoice == null })

        if (firstGuestWithoutDietaryChoice) {
            render view: 'dietaryChoice', model: [invitation: invitation, guest: firstGuestWithoutDietaryChoice]
        } else {
            redirect action: 'summary', params: [id: invitation.id]
        }
    }

    def chooseDietary(String id, ChooseDietaryRequirementCommand command) {
        def guest = Guest.get(command.guestId)

        guest.dietaryChoice = command.chosenRequirement

        guest.save()

        redirect action: 'dietary', params: [id: id]
    }
}
