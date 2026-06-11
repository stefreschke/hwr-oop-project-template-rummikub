package hwr.oop.examples.template

import Persistence
import hwr.oop.students.group4.rummikub.core.Game
import kotlin.collections.set

class InMemoryPersistence(
    private val games : MutableMap<String, Game>,
) : Persistence {

    companion object {
        fun createEmptyStore(): InMemoryPersistence {
            val games = emptyMap<String, Game>().toMutableMap()
            return InMemoryPersistence(games)
        }

        fun createWithGames(listOfGames : List<Game>): InMemoryPersistence {
            val games = emptyMap<String, Game>().toMutableMap()
            listOfGames.forEach { games[it.gameId()] = it }
            return InMemoryPersistence(games)
        }

    }


    override fun save(game: Game) {
        games[game.gameId()] = game
    }

    override fun load(gameId: String): Game {
        return games[gameId] ?: throw IllegalArgumentException("Game with id $gameId not found")
    }
}