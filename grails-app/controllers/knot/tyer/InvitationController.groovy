package knot.tyer

import grails.plugin.springsecurity.annotation.Secured

@Secured('ROLE_ADMIN')
class InvitationController {

    static scaffold = Invitation

}
