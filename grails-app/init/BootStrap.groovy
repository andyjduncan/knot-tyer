import knot.tyer.Invitation

class BootStrap {

    def init = { servletContext ->
        environments {
            development {
                new Invitation(code: 'hello').save()
            }
        }
    }
    def destroy = {
    }
}
