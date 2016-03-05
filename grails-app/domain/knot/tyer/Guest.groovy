package knot.tyer

class Guest {

    static constraints = {
        dietaryChoice nullable: true
    }

    static mapping = {
        id generator: 'knot.type.ShortRandomIdGenerator'
    }

    static belongsTo = [invitation: Invitation]

    String id

    String firstName

    String lastName

    DietaryRequirement dietaryChoice

    String getName() {
        "$firstName $lastName"
    }
}
