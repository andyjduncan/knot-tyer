import knot.tyer.Address
import knot.tyer.Guest
import knot.tyer.Invitation
import knot.tyer.Role
import knot.tyer.User
import knot.tyer.UserRole

class BootStrap {

    def init = { servletContext ->
        environments {
            development {
                new Invitation(address: new Address(line1: 'address 1', postalCode: 'SW1A 1AA'))
                        .addToGuests(new Guest(firstName: 'Eve', lastName: 'Name'))
                        .save(flush: true)

                new Invitation(address: new Address(line1: 'address 1', postalCode: 'SW1A 1AA'))
                        .addToGuests(new Guest(firstName: 'Charlie', lastName: 'Nomates'))
                        .save(flush: true)

                new Invitation(address: new Address(line1: 'line 1', line2: 'line 2', postalCode: 'fff'))
                        .addToGuests(new Guest(firstName: 'Alice', lastName: 'Guest'))
                        .addToGuests(new Guest(firstName: 'Bob', lastName: 'Guest'))
                        .save(flush: true)

                new Invitation(address: new Address(line1: 'line 1', line2: 'line 2', postalCode: 'ggg'))
                        .addToGuests(new Guest(firstName: 'Fred', lastName: 'Rocky'))
                        .addToGuests(new Guest(firstName: 'Gladys', lastName: 'Rocky'))
                        .save(flush: true)

                def user = new User('admin', 'password').save()
                def role = new Role('ROLE_ADMIN').save()
                UserRole.create user, role, true
            }
        }
    }
    def destroy = {
    }
}
