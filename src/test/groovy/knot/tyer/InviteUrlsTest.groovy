package knot.tyer

import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import spock.lang.Specification

@TestFor(UrlMappings)
@Mock(InviteController)
class InviteUrlsTest extends Specification {

    void 'admins can begin to invite guests'() {
        expect:
        assertForwardUrlMapping('/invite/start', controller: 'invite', action: 'start')
    }
}