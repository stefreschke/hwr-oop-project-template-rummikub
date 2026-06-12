package hwr.oop.examples.template

import Persistence
import hwr.oop.students.group4.rummikub.core.Game
import kotlinx.serialization.json.Json
import okio.FileSystem
import kotlin.io.path.exists

class FileSystemPersistence(
	configuration: FileSystemPersistenceConfiguration,
	private val fileSystem: FileSystem = FileSystem.SYSTEM,
) : Persistence {
	private val directory = configuration.directory
	val json = Json { prettyPrint = true }

	override fun save(game: Game) {
		fileSystem.write(directory/game.gameId()) {
			writeUtf8(string = json.encodeToString<Game>(game))
		}
	}

	override fun load(gameId: String): Game {
		require(fileSystem.exists(directory/gameId)) { "Game with id: $gameId not found" }
		return fileSystem.read(directory/gameId) {
			json.decodeFromString<Game>(readUtf8())
		}
	}
}

