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

    void 'a guest can decline a plus one'() {
        expect:
        assertForwardUrlMapping('/rsvp/mycode/noPlusOne', controller: 'rsvp', action: 'noPlusOne') {
            id = 'mycode'
        }
    }

    void 'a guest can add a plus one'() {
        expect:
        assertForwardUrlMapping('/rsvp/mycode/plusOne', controller: 'rsvp', action: 'plusOne') {
            id = 'mycode'
        }
    }

    void 'a guest can provide information about a plus one'() {
        expect:
        assertForwardUrlMapping('/rsvp/mycode/addPlusOne?firstName=Guy&lastName=Incognito',
                controller: 'rsvp', action: 'addPlusOne',
                params: [firstName: 'Guy', lastName: 'Incognito']) {
            id = 'mycode'
        }
    }

    void 'a couple can specific attendees'() {
        expect:
        assertForwardUrlMapping('/rsvp/mycode/chooseGuests?guestIds=1,2',
                controller: 'rsvp', action: 'chooseGuests', params: [guestIds: '1,2']) {
            id = 'mycode'
        }
    }

    void 'allows a guest to choose a dietary requirement'() {
        expect:
        assertForwardUrlMapping('/rsvp/mycode/chooseDietary', controller: 'rsvp', action: 'chooseDietary') {
            id == 'mycode'
        }
    }
}