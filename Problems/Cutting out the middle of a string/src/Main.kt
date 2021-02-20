fun main() {
    val input = readLine() ?: return
    val result  = if (input.length % 2 == 0) {
        input.removeRange(input.length / 2 - 1 .. input.length / 2)
    } else {
        input.removeRange(input.length / 2 .. input.length / 2)
    }
    println(result)
}