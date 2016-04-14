package knot.tyer

import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(InviteController)
@Mock(Invitation)
class InviteControllerSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "starts a new invite"() {
        when:
        controller.start()

        then:
        view == '/invite/start'
    }

    void 'adds a new invite'() {
        given:
        params.'guests[0]' = [firstName: 'first', lastName: 'last']

        when:
        controller.add()

        then:
        Invitation.count() == 1
    }
}
