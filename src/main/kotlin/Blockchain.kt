class Blockchain {
    private var blockId = java.util.concurrent.atomic.AtomicInteger(1)
    var blockchain = mutableListOf<Block>()

    init {
        while (blockchain.size != 5) {
            Block(blockId.getAndIncrement(), System.currentTimeMillis())
                .also { blockchain.add(it) }
                .let { it.setPrevHash(if (blockchain.size > 1) blockchain[blockchain.indexOf(it) - 1].blockHash else "0") }
        }
    }
}