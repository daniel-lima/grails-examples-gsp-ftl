package quick_start

abstract class AbstractTripController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 1000)
        [tripInstanceList: Trip.list(params), tripInstanceTotal: Trip.count()]
    }


    def create = {
        def tripInstance = new Trip()
        tripInstance.properties = params
        return [tripInstance: tripInstance]
    }

    def save = {
        def tripInstance = new Trip(params)
        if (tripInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'trip.label', default: 'Trip'), tripInstance.id])}"
            redirect(action: "show", id: tripInstance.id)
        }
        else {
            render(view: "create", model: [tripInstance: tripInstance])
        }
    }

    def show = {
        def tripInstance = Trip.get(params.id)
        if (!tripInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'trip.label', default: 'Trip'), params.id])}"
            redirect(action: "list")
        }
        else {
            [tripInstance: tripInstance]
        }
    }

    def edit = {
        def tripInstance = Trip.get(params.id)
        if (!tripInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'trip.label', default: 'Trip'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [tripInstance: tripInstance]
        }
    }

    def update = {
        def tripInstance = Trip.get(params.id)
        if (tripInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (tripInstance.version > version) {
                    
                    tripInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'trip.label', default: 'Trip')] as Object[], "Another user has updated this Trip while you were editing")
                    render(view: "edit", model: [tripInstance: tripInstance])
                    return
                }
            }
            tripInstance.properties = params
            if (!tripInstance.hasErrors() && tripInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'trip.label', default: 'Trip'), tripInstance.id])}"
                redirect(action: "show", id: tripInstance.id)
            }
            else {
                render(view: "edit", model: [tripInstance: tripInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'trip.label', default: 'Trip'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def tripInstance = Trip.get(params.id)
        if (tripInstance) {
            try {
                tripInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'trip.label', default: 'Trip'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'trip.label', default: 'Trip'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'trip.label', default: 'Trip'), params.id])}"
            redirect(action: "list")
        }
    }

    def bigList = {
        List list = servletContext.bigList
        render(view: 'list', model: [tripInstanceList: list, tripInstanceTotal: list.size()])
    }
}
