package hwr.oop.examples.template
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import hwr.oop.students.group4.rummikub.core.Game
import hwr.oop.students.group4.rummikub.core.PlayerId
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers

@Disabled("Requires Docker")
@Testcontainers
class SqlPersistenceTest {
	
	companion object {
		@Container
		@JvmStatic
		val postgres = PostgreSQLContainer("postgres:17-alpine")
	}
	
	private lateinit var adapter: SqlPersistence
	private lateinit var dataSource: HikariDataSource
	
	@BeforeEach
	fun setUp() {
		val config = HikariConfig().apply {
			jdbcUrl = postgres.jdbcUrl
			username = postgres.username
			password = postgres.password
		}
		dataSource = HikariDataSource(config)
		adapter = SqlPersistence(dataSource)
	}
	
	@AfterEach
	fun tearDown() {
		if (::dataSource.isInitialized) {
			dataSource.close()
		}
	}

	@Test
	fun `save game and load game successful`() {
		// given
		val game = Game.createNewGame(listOf(PlayerId("player1"), PlayerId("player2")))
		val gameId = game.gameId()
		// when
		adapter.save(game)
		val savedGame = adapter.load(gameId)
		// then
		assertThat(savedGame).isEqualTo(game)
	}

	@Test
	fun `load game unsuccessful`() {
		// given
		val game = Game.createNewGame(listOf(PlayerId("player1"), PlayerId("player2")));
		// when
		adapter.save(game);
		// then
		assertThatThrownBy {adapter.load("trollId")}.hasMessageContaining("Game not found: trollId")
	}

}

