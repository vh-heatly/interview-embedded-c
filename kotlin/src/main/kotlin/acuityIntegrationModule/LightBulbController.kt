package acuityIntegrationModule

import java.util.UUID
import kotlinx.coroutines.delay
import kotlin.random.Random

class LightBulbController {
    suspend fun turnBulbOn(id: UUID): String {
        mimicNotFound(id)
        mimicDelay()

        if (Random.nextDouble() < 0.1) throw Error("This bulb is not working")

        return "OK"
    }

    suspend fun turnBulbOff(id: UUID): String {
        mimicNotFound(id)
        mimicDelay()
        return "OK"
    }

    private suspend fun mimicDelay() = delay(Random.nextLong(2, 400))

    private fun mimicNotFound(id: UUID) {
        if (id.toString()[0] == '0') throw Error("This bulb does not exist")
    }
}
