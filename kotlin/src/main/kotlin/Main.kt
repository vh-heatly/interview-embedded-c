import acuityIntegrationModule.AcuityAPI
import kotlinx.coroutines.runBlocking
import lightBulbSchedule.Schedule
import java.time.LocalDateTime
import java.util.*
import kotlin.system.measureTimeMillis

// TASK:
// SmartHome Inc. offers smart home devices and services. Your task is to develop
// a scheduling feature for a smart home app that controls smart light bulbs 💡

// CURRENT FUNCTIONALITY:
// - Create, edit, and delete light bulb schedules
// - Specify which light bulbs are included in a schedule
// - Function to toggle schedules on or off

fun main() {
    val acuityAPI = AcuityAPI()
    val schedule = Schedule(acuityAPI, "Simple schedule", 8, 18)
    acuityAPI.sampleLightBulbs.forEach { schedule.addLightBulb(it) }

    // CHANGE HERE FOR TIME MACHINE
    // scalingFactor == 1L --> Real time
    // scalingFactor == 60L --> 1 minute == 1 hour
    // scalingFactor == 3_600L --> 1 second == 1 hour
    val scalingFactor = 3_600L

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
                print("${time.hour}:${time.minute}: ")
                schedule.showLightBulbsStatus()
                time = time.plusSeconds(scalingFactor)
            }
            println("Ran successfully in $executionTime ms")
        }
    }, 0, periodInSeconds * 1000)
}
