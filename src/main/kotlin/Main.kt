fun main() {
    print("Enter how many zeros the hash must start with: ")
    val difficultyLevel = readln().toInt()
    val blockchain = Blockchain(difficultyLevel)

    repeat(5) {
        blockchain.generateNewBlock()
    }

    blockchain.printBlockchain()
}