package processor

import java.util.Scanner

data class Matrix(val data: Array<Array<Double>>) {
    private val _data : Array<Array<Double>>
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
        val formatAsDouble = data.any { it.any { !it.toString().endsWith(".0") } }
        return if (formatAsDouble) {
            data.map { it.joinToString(" ") }.joinToString("\n")
        } else {
            data.map { it.map { it.toInt() }.joinToString(" ")}.joinToString("\n")
        }
    }

    operator fun plus(other: Matrix): Matrix {
        if (this.shape != other.shape) throw Exception("Matrices must have same shape")
        val data = Array<Array<Double>>(this.shape.first) { row ->
            Array<Double>(this.shape.second) { col ->
                this._data[row][col] + other._data[row][col]
            }
        }
        return Matrix(data)
    }

    operator fun times(other: Double): Matrix {
        val data = Array<Array<Double>>(this.shape.first) { row ->
            Array<Double>(this.shape.second) { col ->
                this._data[row][col] * other
            }
        }
        return Matrix(data)
    }

    operator fun times(other: Matrix): Matrix {
        if (this.shape.second != other.shape.first) throw Exception("Invalid shape")
        val data = Array<Array<Double>>(this.shape.first) { row ->
            Array<Double>(other.shape.second) { col ->
                (this._data[row] zip other._data.map { it[col] }).fold(0.0) {sum,e -> sum + e.first * e.second}
            }
        }
        return Matrix(data)
    }

    companion object {
        fun read(): Matrix {
            val (rows,columns) = readLine()!!.split(" ").map { it.toInt() }
            val data = Array<Array<Double>>(rows) {
                val scanner = Scanner(readLine()!!)
                Array<Double>(columns) { scanner.nextDouble() }
            }
            return Matrix(data)
        }

        fun prompt(shapePrompt: String, matrixPrompt: String): Matrix {
            print(shapePrompt)
            val (rows,columns) = readLine()!!.split(" ").map { it.toInt() }
            println(matrixPrompt)
            val data = Array<Array<Double>>(rows) {
                val scanner = Scanner(readLine()!!)
                Array<Double>(columns) { scanner.nextDouble() }
            }
            return Matrix(data)
        }
    }
}

fun getChoice(): String {
    println("1. Add matrices")
    println("2. Multiply matrix by a constant")
    println("3. Multiply matrices")
    println("0. Exit")
    print("Your choice: ")
    return readLine()!!
}

fun printResult(result: Matrix) {
    println("The result is:")
    println(result)
}

fun handleAddMatrices(): Matrix {
    val a = Matrix.prompt("Enter size of first matrix: ","Enter first matrix")
    val b = Matrix.prompt("Enter size of second matrix: ","Enter second matrix")
    return a + b
}

fun handleMultiplyMatrixByConstant(): Matrix {
    val a = Matrix.prompt("Enter size of matrix: ","Enter matrix")
    print("Enter constant: ")
    val constant = readLine()!!.toDouble()
    return a * constant
}

fun handleMultiplyMatrices(): Matrix {
    val a = Matrix.prompt("Enter size of first matrix: ","Enter first matrix")
    val b = Matrix.prompt("Enter size of second matrix: ","Enter second matrix")
    return a * b
}

fun main() {
    while (true) {
        try {
            val choice = getChoice()
            when (choice) {
                "1" -> printResult(handleAddMatrices())
                "2" -> printResult(handleMultiplyMatrixByConstant())
                "3" -> printResult(handleMultiplyMatrices())
                "0" -> break
            }
        } catch (ex: Exception) {
            println("The operation cannot be performed")
        }
    }
}
