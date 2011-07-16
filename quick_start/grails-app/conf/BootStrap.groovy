import quick_start.Trip

class BootStrap {

    def init = { servletContext ->

        boolean isBigList = Thread.currentThread().contextClassLoader.getResource('bigList') != null
        List bigList = []

        Trip.withTransaction {
            int amount = 1
            if (isBigList) {
                amount = 1000
            }

            for (int i = 0; i < amount; i++) {
                Trip t = new Trip()
                t.name = "Trip ${i}"
                t.startDate = new Date()
                t.endDate = new Date()
                t.purpose = 'None'
                t.city = 'Nowhere'
                t.notes= ''
                bigList << t
            }

            if (isBigList) {
                servletContext.bigList = bigList
            }
            
            bigList[0].save()
            println "${amount} created"
        }

    }
    def destroy = {
    }
}
