import hwr.oop.students.group4.rummikub.core.Game

interface Persistence {
    fun save(game: Game)
    fun load(gameId: String): Game
}

