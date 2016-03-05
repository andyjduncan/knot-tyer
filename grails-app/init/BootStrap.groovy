import knot.tyer.Guest
import knot.tyer.Invitation

class BootStrap {

    def init = { servletContext ->
        environments {
            development {
                new Invitation()
                        .addToGuests(new Guest(firstName: 'Eve', lastName: 'Name'))
                        .save(flush: true)

                new Invitation()
                        .addToGuests(new Guest(firstName: 'Alice', lastName: 'Guest'))
                        .addToGuests(new Guest(firstName: 'Bob', lastName: 'Guest'))
                        .save(flush: true)
            }
        }
    }
    def destroy = {
    }
}
