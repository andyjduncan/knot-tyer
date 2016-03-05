package knot.tyer

import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(RsvpController)
@Mock(Invitation)
class RsvpControllerSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void 'the invitation should be retrieved by rsvp code'() {
        given:
        new Invitation(code: 'mycode').save()

        when:
        controller.rsvp('mycode')

        then:
        view == '/rsvp/beginRsvp'
        model.invitation.code == 'mycode'
    }
}
