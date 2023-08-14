package lightBulbSchedule

import acuityIntegrationModule.AcuityAPI
import java.time.LocalDateTime

class Schedule(
    val acuityAPI: AcuityAPI,
    val name: String,
    val hourOn: Int,
    val hourOff: Int,
) {
    private val lightBulbs = mutableSetOf<Lightbulb>()

    fun addLightBulb(lightBulb: Lightbulb) = lightBulbs.add(lightBulb)

    fun removeLightBulb(lightBulb: Lightbulb) = lightBulbs.remove(lightBulb)

    suspend fun tick(currentTime: LocalDateTime)  {
        if (shouldGoOn(currentTime)) {
            lightBulbs.forEach {
                it.isOn = true
                acuityAPI.powerOn(it.id)
            }
        } else {
            lightBulbs.forEach {
                it.isOn = false
                acuityAPI.powerOff(it.id)
            }
        }
    }

    private fun shouldGoOn(currentTime: LocalDateTime): Boolean =
        if (hourOn < hourOff) {
            currentTime.hour in hourOn until hourOff
        } else {
            currentTime.hour in hourOff until hourOn
        }

    fun showLightBulbsStatus() {
        lightBulbs.forEach { print(if (it.isOn) " 1 " else " 0 ") }
    }
}
