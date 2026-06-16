package hwr.oop.students.group4.rummikub.core

interface Persistence {
    fun save(game: Game)
    fun load(gameId: String): Game
}