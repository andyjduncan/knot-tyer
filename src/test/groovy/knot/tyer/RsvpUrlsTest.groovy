package knot.tyer

import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import spock.lang.Specification

@TestFor(UrlMappings)
@Mock(RsvpController)
class RsvpUrlsTest extends Specification {

    void 'guests can respond with their custom rsvp code'() {
        expect:
        assertForwardUrlMapping('/rsvp/mycode', controller: 'rsvp', action: 'rsvp') {
            id = 'mycode'
        }
    }

    void 'guests can decline the invitation'() {
        expect:
        assertForwardUrlMapping('/rsvp/mycode/decline', controller: 'rsvp', action: 'decline') {
            id = 'mycode'
        }
    }

    void 'guests can accept the invitation'() {
        expect:
        assertForwardUrlMapping('/rsvp/mycode/accept', controller: 'rsvp', action: 'accept') {
            id = 'mycode'
        }
    }

    void 'asks guests about dietary requirements'() {
        expect:
        assertForwardUrlMapping('/rsvp/mycode/dietary', controller: 'rsvp', action: 'dietary') {
            id == 'mycode'
        }
    }

    void 'allows a guest to choice a dietary requirement'() {
        expect:
        assertForwardUrlMapping('/rsvp/mycode/chooseDietary', controller: 'rsvp', action: 'chooseDietary') {
            id == 'mycode'
        }
    }
}