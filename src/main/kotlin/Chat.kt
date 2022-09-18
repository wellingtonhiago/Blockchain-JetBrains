import java.util.*
import java.util.concurrent.CountDownLatch

class Chat {
    private val names = arrayOf("Adam", "Bob", "Cecile", "Damian", "Greg", "Mark", "Mike", "Rob")
    private val words = arrayOf("will kill", "smoke with", "hate", "love", "looking for", "miss", "like")
    private var messages = mutableListOf<Message>()

    fun creatingMessages() {
        val latch = CountDownLatch(Random().nextInt(5))
        while (latch.count != 0L) {
            messages.add(Message(names[Random().nextInt(8)], words[Random().nextInt(7)]))
            latch.countDown()
        }
    }

    @Synchronized
    fun addMessagesToBlock(): List<Message> {
        val toSend = messages
        messages.clear()
        creatingMessages()
        return toSend
    }
}