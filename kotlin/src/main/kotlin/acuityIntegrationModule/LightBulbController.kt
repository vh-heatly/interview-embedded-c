package acuityIntegrationModule

import java.util.Random
import java.util.UUID
import kotlinx.coroutines.delay

class LightBulbController {
    suspend fun turnBulbOn(id: UUID): String {
        delay(250)


        if (Random().nextDouble() < 0.1) throw Error("This bulb does not exist")
        if (Random().nextDouble() < 0.1) throw Error("This bulb is not working")

        return "OK"
    }

    suspend fun turnBulbOff(id: UUID): String {
        delay(250)

        if (Random().nextDouble() < 0.1) throw Error("This bulb does not exist")

        return "OK"
    }
}