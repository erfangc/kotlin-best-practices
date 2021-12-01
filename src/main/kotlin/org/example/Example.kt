package org.example

enum class Specialty {
    Backend, Frontend, Fullstack
}

data class Developer(
    val name: String,
    val specialty: Specialty,
    val age: Int,
)

fun main() {

    val team1 = listOf(
        Developer(name = "John", age = 21, specialty = Specialty.Backend),
        Developer(name = "Jill", age = 25, specialty = Specialty.Frontend),
    )

    val team2 = listOf(
        Developer(name = "Jones", age = 31, specialty = Specialty.Backend),
        Developer(name = "Jack", age = 36, specialty = Specialty.Fullstack),
    )

    // Find average developer age for team1
    println(
        team1.sumOf { it.age } / team1.size
    )

    // Find average age for both teams
    println(
        (team1 + team2).sumOf { it.age } / (team1 + team2).size
    )

    // Find the average age for each specialty
    val allDevelopers = team1 + team2
    println(
        allDevelopers
            .groupBy { it.specialty }
            .mapValues { (_, developers) ->
                developers.sumOf { it.age } / developers.size
            }
    )

    // Find the oldest developer who is a backend developer
    println(
        allDevelopers.filter { it.specialty == Specialty.Backend }.maxByOrNull { it.age }
    )
    
    // Create a lookup map of Developer by name
    println(
        allDevelopers.associateBy { it.name }
    )

    // Concatenate a third team's members (that could be null)
    val team3 = listOf(
        null,
        Developer(name = "Bob", age = 45, specialty = Specialty.Backend),
        Developer(name = "Sri", age = 21, specialty = Specialty.Backend),
    )
    val allDevelopersPlusTeam3 = allDevelopers + team3.filterNotNull()
    println(allDevelopersPlusTeam3)
    
    // Print the name of Team 3's developer and avoid null pointers
    for (developer in team3) {
        println(developer?.name)
    }
    
    // Or ...
    for (developer in team3) {
        developer?.also { println(it.name) }
    }

}

