

fun createDiceGameRandomizer(n: Int): Int {
    var seed: Int
    val rolls = IntArray(n * 2) { 0 }
    do {
        seed = Random.nextInt(0, Int.MAX_VALUE)
        val rand = Random(seed)
        for (i in rolls.indices) rolls[i] = rand.nextInt(1, 7)
    } while (rolls.take(n).reduce(Int::plus) > rolls.takeLast(n).reduce(Int::plus))
    return seed
}
