package knot.tyer

class Invitation {

    static constraints = {
        address nullable: true
    }

    static hasMany = [guests: Guest]

    static mapping = {
        id generator: 'knot.tyer.AdjectiveColourNounIdGenerator'
    }

    static embedded = ['address']

    String id

    InvitationStatus status = InvitationStatus.PENDING

    List<Guest> guests = []

    Address address
}

class Address {

    static constraints = {
        line1 blank: false
        line2 nullable: true
        line3 nullable: true
        line4 nullable: true
        line5 nullable: true
        postalCode blank: false
        country nullable: true
    }

    String line1

    String line2

    String line3

    String line4

    String line5

    String postalCode

    String country
}
