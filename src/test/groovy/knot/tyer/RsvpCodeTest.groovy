package knot.tyer

import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import spock.lang.Specification

@TestFor(UrlMappings)
@Mock(RsvpController)
class RsvpCodeTest extends Specification {

    void 'guests can respond with their custom rsvp code'() {
        expect:
        assertForwardUrlMapping('/rsvp/mycode', controller: 'rsvp', action: 'rsvp') {
            code = 'mycode'
        }
    }
}