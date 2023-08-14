package lightBulbSchedule

class Schedule(name: String) {
    private val lighbulbs = mutableSetOf<Lightbulb>()

    fun addLightbulb(lightbulb: Lightbulb) {
        lighbulbs.add(lightbulb)
    }

}
