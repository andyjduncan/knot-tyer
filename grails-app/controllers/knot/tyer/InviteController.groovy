package knot.tyer

class InviteController {

    def index() { }

    def start() {
        render view: 'start'
    }

    def add() {
        def invitation = new Invitation(params)

        if (invitation.save()) {

        } else {
            println invitation.errors.allErrors
        }
    }
}
