import acuityIntegrationModule.LightBulbAPI
import kotlinx.coroutines.runBlocking
import lightBulbSchedule.Schedule
import java.time.LocalDateTime
import java.util.*
import kotlin.system.measureTimeMillis

fun main() {
    val lightBulbAPI = LightBulbAPI()
    val schedule = Schedule(lightBulbAPI, "Simple schedule", 18, 11)
    lightBulbAPI.sampleLightBulbs.forEach { schedule.addLightBulb(it) }

    var time = LocalDateTime.now()
    time = time.minusMinutes(time.minute.toLong())

    while (true) {
        runBlocking {
            val hour = if (time.hour < 10) "0${time.hour}" else "${time.hour}"
            val minute = if (time.minute < 10) "0${time.minute}" else "${time.minute}"
            schedule.tick(time)
            print("${hour}:${minute}: ")
            schedule.showLightBulbsStatus()
            time = time.plusHours(1)
            Thread.sleep(200)
        }
    }
}
