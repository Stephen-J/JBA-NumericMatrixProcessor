package processor

import java.util.Scanner

data class Matrix(val data: Array<Array<Int>>) {
    private val _data : Array<Array<Int>>
    val shape: Pair<Int,Int>

    init {
        _data = data.copyOf()
        shape = Pair(_data.size,_data.first().size)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Matrix

        if (!data.contentEquals(other.data)) return false

        return true
    }

    override fun hashCode(): Int {
        return data.contentHashCode()
    }

    override fun toString(): String {
        return data.map { it.joinToString(" ") }.joinToString("\n")
    }

    operator fun plus(other: Matrix): Matrix {
        if (this.shape != other.shape) throw Exception("Matrices must have same shape")
        val data = Array<Array<Int>>(this.shape.first) { row ->
            Array<Int>(this.shape.second) { col ->
                this._data[row][col] + other._data[row][col]
            }
        }
        return Matrix(data)
    }

    operator fun times(other: Int): Matrix {
        val data = Array<Array<Int>>(this.shape.first) { row ->
            Array<Int>(this.shape.second) { col ->
                this._data[row][col] * other
            }
        }
        return Matrix(data)
    }

    companion object {
        fun read(): Matrix {
            val (rows,columns) = readLine()!!.split(" ").map { it.toInt() }
            val data = Array<Array<Int>>(rows) {
                val scanner = Scanner(readLine()!!)
                Array<Int>(columns) { scanner.nextInt() }
            }
            return Matrix(data)
        }
    }
}



fun main() {
    try {
        val a = Matrix.read()
        val constant = readLine()!!.toInt()
        println(a * constant)
    } catch (ex: Exception) {
        println("ERROR")
    }
}
