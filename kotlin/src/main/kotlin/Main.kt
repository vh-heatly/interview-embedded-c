import acuityIntegrationModule.AcuityAPI
import kotlinx.coroutines.runBlocking
import lightBulbSchedule.Schedule
import java.time.LocalDateTime
import java.util.*
import kotlin.system.measureTimeMillis

// TASK:
// SmartHome Inc. offers smart home devices and services. Your task is to develop
// a scheduling feature for a smart home app that controls smart light bulbs 💡

// GOALS:
// - Create, edit, and delete light bulb schedules
// - Specify which light bulbs are included in a schedule
// - Function to toggle schedules on or off
// - Unit tests for the scheduling system

fun main() {
    val acuityAPI = AcuityAPI()
    val schedule = Schedule(acuityAPI, "Simple schedule", 8, 18)
    acuityAPI.sampleLightBulbs.forEach { schedule.addLightBulb(it) }

    // CHANGE HERE FOR TIME MACHINE
    val scalingFactor = 1L

    // DON'T TOUCH
    val timer = Timer()
    val oneHourInSeconds: Long = 3_600
    val periodInSeconds = oneHourInSeconds / scalingFactor
    var time = LocalDateTime.now()

    // THE TIMER. You can touch again
    timer.scheduleAtFixedRate(object : TimerTask() {
        override fun run() = runBlocking {
            val executionTime = measureTimeMillis {
                schedule.tick(time)
                schedule.showLightBulbsStatus()
                time = time.plusSeconds(scalingFactor)
            }
            println("Ran successfully in $executionTime ms")
        }
    }, 0, periodInSeconds * 1000)
}
