package knot.tyer

class RsvpController {

    def rsvp(String code) {
        def invitation = Invitation.findByCode(code)

        render view: 'beginRsvp', model: [invitation: invitation]
    }
}
