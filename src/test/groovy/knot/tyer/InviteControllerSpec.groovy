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
        when:
        controller.add('Alice', 'Guest')

        then:
        Invitation.count() == 1
        def invitation = Invitation.list().first()
        invitation.guests.first().firstName == 'Alice'
        invitation.guests.first().lastName == 'Guest'

        and:
        view == '/invite/plusone'
        model.invitation
    }

    void 'adds a plus one'() {
        given:
        def invitation = new Invitation()
                .addToGuests(new Guest(firstName: 'Alice', lastName: 'Guest'))
                .save(failOnError: true)

        when:
        controller.addPlusOne(invitation.id, 'Bob', 'Guest')

        then:
        def saved = Invitation.get(invitation.id)
        saved.guests.last().firstName == 'Bob'
        saved.guests.last().lastName == 'Guest'

        and:
        view == '/invite/address'
        model.invitation.id == invitation.id
    }

    void 'adds an address'() {
        given:
        def invitation = new Invitation()
                .save()

        params.line1 = 'line 1'
        params.postalCode = 'postal'

        when:
        controller.addAddress(invitation.id)

        then:
        def saved = Invitation.get(invitation.id)

        saved.address.line1 == 'line 1'
        saved.address.postalCode == 'postal'

        and:
        view == '/invite/email'
        model.invitation.id == invitation.id
    }

    void 'adds an email address'() {
        given:
        def invitation = new Invitation().save()

        when:
        controller.addEmail invitation.id, 'a@b.c'

        then:
        def saved = Invitation.get(invitation.id)

        saved.emailAddress == 'a@b.c'

        and:
        view == '/invite/list'
        model.invitations*.id == [invitation.id]
    }
}
