fun main() {
    val input = readLine() ?: return
    val (first, second) = input.chunked(input.length / 2)
    val lambda = { v: Char -> v.toString().toInt() }
    println(if (first.sumOf(lambda) == second.sumOf(lambda)) "YES" else "NO")
}