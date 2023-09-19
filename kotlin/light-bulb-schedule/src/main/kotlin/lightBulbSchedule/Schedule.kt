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

    private fun shouldGoOn(currentTime: LocalDateTime): Boolean {
        return if (hourOn < hourOff) {
            // |---ON---|---OFF---|---MIDNIGHT---|
            currentTime.hour in hourOn until hourOff
        } else {
            // |---ON---|---MIDNIGHT---|---OFF---|
            currentTime.hour < hourOff || currentTime.hour >= hourOn
        }
    }

    fun showLightBulbsStatus() {
        if (lightBulbs[0].isOn) {
            println("Light bulbs are ON")
        } else {
            println("Light bulbs are OFF")
        }
    }
}
