fun main() {
    val input = readLine() ?: return
    val (first,second) = input.chunked(3)
    val sum = { sum: Int, ch: Char -> sum + ch.toInt() }
    val firstSum = first.fold(0,sum)
    val secondSum = second.fold(0,sum)
    println(if (firstSum == secondSum) "Lucky" else "Regular")
}
