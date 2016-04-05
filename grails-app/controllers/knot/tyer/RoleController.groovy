package knot.tyer

import grails.plugin.springsecurity.annotation.Secured

@Secured('ROLE_ADMIN')
class RoleController {

    static scaffold = Role

}
