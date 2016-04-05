package knot.tyer

class Guest {

    static constraints = {
        dietaryChoice nullable: true
        attending nullable: true
    }

    static mapping = {
        id generator: 'knot.tyer.ShortRandomIdGenerator'
    }

    static belongsTo = [invitation: Invitation]

    String id

    String firstName

    String lastName

    Boolean attending

    DietaryRequirement dietaryChoice

    String getName() {
        "$firstName $lastName"
    }
}

