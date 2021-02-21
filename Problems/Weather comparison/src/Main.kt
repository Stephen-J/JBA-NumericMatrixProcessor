class City(val name: String) {
    var degrees: Int = when (name.toLowerCase()) {
        "moscow" -> 5
        "hanoi" -> 20
        "dubai" -> 30
        else -> 0
    }
        set(value) {
            if (value in -92..57) field = value
        }

    override fun toString(): String = "[$name $degrees]"
}        

fun main() {
    val first = readLine()!!.toInt()
    val second = readLine()!!.toInt()
    val third = readLine()!!.toInt()
    val firstCity = City("Dubai")
    firstCity.degrees = first
    val secondCity = City("Moscow")
    secondCity.degrees = second
    val thirdCity = City("Hanoi")
    thirdCity.degrees = third
    var cities = listOf(firstCity, secondCity, thirdCity)
    cities  = cities.sortedBy { it.degrees }.reversed()
    val result = if (cities[cities.lastIndex].degrees == cities[cities.lastIndex - 1].degrees) {
        "neither"
    } else cities[cities.lastIndex].name
    print(result)
}