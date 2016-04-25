package knot.tyer

import grails.plugin.springsecurity.annotation.Secured

@Secured('ROLE_ADMIN')
class InviteController {

    def index() { }

    def start() {
        render view: 'start'
    }

    def add(String firstName, String lastName) {
        def invitation = new Invitation()
        invitation.addToGuests(new Guest(firstName: firstName, lastName: lastName))

        if (invitation.save(failOnError:true)) {
            render view: '/invite/plusone', model: [invitation: invitation]
        } else {
            println invitation.errors.allErrors
        }
    }

    def addPlusOne(String id, String firstName, String lastName) {
        def invitation = Invitation.get(id)

        invitation.addToGuests(new Guest(firstName: firstName, lastName: lastName))

        invitation.save()

        render view: '/invite/address', model: [invitation: invitation]
    }

    def showAddAddress(String id) {
        def invitation = Invitation.get(id)

        render view: '/invite/address', model: [invitation: invitation]
    }

    def addAddress(String id) {
        def invitation = Invitation.get(id)

        invitation.address = new Address(params)

        invitation.save()

        render view: '/invite/email', model: [invitation: invitation]
    }

    def showAddEmail(String id) {
        def invitation = Invitation.get(id)

        render view: '/invite/email', model: [invitation: invitation]
    }

    def addEmail(String id, String emailAddress) {
        def invitation = Invitation.get(id)

        invitation.emailAddress = emailAddress

        invitation.save()

        redirect controller: 'invitation', action: 'index'
//        render view: '/invite/list', model: [invitations: Invitation.list()]
    }
}
