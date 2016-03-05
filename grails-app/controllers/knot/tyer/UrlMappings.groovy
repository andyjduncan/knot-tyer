package knot.tyer

class UrlMappings {

    static mappings = {
        "/rsvp/$code"(controller: 'rsvp', action: 'rsvp')
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/"(view:"/index")
        "500"(view:'/error')
        "404"(view:'/notFound')
    }
}
