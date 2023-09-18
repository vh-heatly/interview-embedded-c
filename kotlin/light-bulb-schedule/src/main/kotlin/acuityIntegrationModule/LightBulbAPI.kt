package acuityIntegrationModule

import java.util.UUID
import kotlinx.coroutines.delay
import lightBulbSchedule.LightBulb
import kotlin.random.Random

// Simulated external service – don't touch!
class LightBulbAPI {
    private val sampleUUIDs = setOf(
        UUID.fromString("7fa1be5e-ac82-4cba-b364-051d209dcc6d"),
        UUID.fromString("dc92d773-c781-4e3a-9981-e8539599df56"),
        UUID.fromString("55c4bf72-909e-4575-9848-65ecca1455f6"),
        UUID.fromString("c35633e2-afa6-4904-aebf-39aa4b19ff15"),
        UUID.fromString("597ee699-11f3-453e-9936-2c7a781432e8"),
        UUID.fromString("73c6c30f-870c-467a-937f-6a428581b88f"),
        UUID.fromString("b3fba53f-4122-4548-806f-0fb50ee940ed"),
        UUID.fromString("e4086cbb-070a-48e7-aa41-04061b340552"),
        UUID.fromString("fe401920-6f2e-451b-a3cf-b97f8e5e86fb"),
        UUID.fromString("5cd0e76c-f283-4270-a5b1-b263c89b33c8"),
    )

    val sampleLightBulbs = sampleUUIDs.map { LightBulb(it, false) }

    fun generateRandomLightBulbs(count: Int): List<LightBulb> =
        (1..count).map { LightBulb(UUID.randomUUID(), false) }

    suspend fun powerOn(id: UUID): String {
        mimicNotFound(id)
        mimicDelay()
        mimicPlugNotWorking(id)

        return "OK"
    }

    suspend fun powerOff(id: UUID): String {
        mimicNotFound(id)
        mimicDelay()
        return "OK"
    }

    private suspend fun mimicDelay() = delay(Random.nextLong(2, 400))

    private fun mimicNotFound(id: UUID) {
        if (sampleUUIDs.contains(id)) {
            return
        } else {
            val firstCharInId = id.toString()[0]
            if (firstCharInId == '0') throw Error("LIGHT BULB API ERROR: ID NOT FOUND")
        }
    }

    private fun mimicPlugNotWorking(id: UUID) {
        if (sampleUUIDs.contains(id)) {
            return
        } else if (Random.nextDouble() < 0.01) throw Error("LIGHT BULB API ERROR: Plug not working")
    }
}
