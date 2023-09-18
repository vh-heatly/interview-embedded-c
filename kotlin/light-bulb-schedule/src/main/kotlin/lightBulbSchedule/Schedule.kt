package lightBulbSchedule

import acuityIntegrationModule.LightBulbAPI
import java.time.LocalDateTime

class Schedule(
    private val acuityAPI: LightBulbAPI,
    private val name: String,
    private val hourOn: Int,
    private val hourOff: Int,
) {
    private val lightBulbs = mutableListOf<LightBulb>()

    fun addLightBulb(lightBulb: LightBulb) = lightBulbs.add(lightBulb)

    fun removeLightBulb(lightBulb: LightBulb) = lightBulbs.remove(lightBulb)

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
        if (lightBulbs[0].isOn) {
            print("Light bulbs are ON | ")
        } else {
            print("Light bulbs are OFF | ")
        }
    }
}
