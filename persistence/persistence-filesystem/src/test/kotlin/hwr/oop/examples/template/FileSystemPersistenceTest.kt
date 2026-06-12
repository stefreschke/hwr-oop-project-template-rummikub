package hwr.oop.examples.template

import hwr.oop.students.group4.rummikub.core.Game
import hwr.oop.students.group4.rummikub.core.PlayerId
import okio.FileSystem
import okio.Path.Companion.toPath
import okio.fakefilesystem.FakeFileSystem
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class FileSystemPersistenceTest {
	
	private val fakeFileSystem = FakeFileSystem()
	private val tempDir = "tmp/template-test".toPath()
	private val sut: FileSystemPersistence
	
	init {
		fakeFileSystem.createDirectories(tempDir)
		sut = FileSystemPersistence(
			FileSystemPersistenceConfiguration(tempDir),
			fakeFileSystem
		)
	}
	
	@AfterEach
	fun tearDown() {
		fakeFileSystem.checkNoOpenFiles()
	}

	@Test
	fun `save game and load game successfully`() {
		// given
		val newGame = Game.createNewGame(listOf(PlayerId("player 1"), PlayerId("player 2")))
		val gameId = newGame.gameId()
		// when
		sut.save(newGame)
		val loadedGame= sut.load(gameId)
		// then
		assertThat(loadedGame).isEqualTo(newGame)
	}

	@Test
	fun `load game unsuccessfully`() {
		//given
		val newGame = Game.createNewGame(listOf(PlayerId("player 1"), PlayerId("player 2")))
		val gameId = "fake Game ID"
		//when
		//then
		assertThatThrownBy{sut.load(gameId)}.hasMessageContaining("Game with id: $gameId not found" )
	}

	}


