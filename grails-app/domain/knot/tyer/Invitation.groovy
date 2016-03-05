package knot.tyer

class Invitation {

    static constraints = {
    }

    static hasMany = [guests: Guest]

    static mapping = {
        id generator: 'knot.type.ShortRandomIdGenerator'
    }

    String id

    InvitationStatus status = InvitationStatus.PENDING

    List<Guest> guests
}
