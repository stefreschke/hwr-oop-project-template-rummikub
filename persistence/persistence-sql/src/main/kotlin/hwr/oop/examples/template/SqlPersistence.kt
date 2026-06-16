package hwr.oop.examples.template

import com.zaxxer.hikari.HikariDataSource
import hwr.oop.students.group4.rummikub.core.Game
import hwr.oop.students.group4.rummikub.core.Persistence
import kotlinx.serialization.json.Json
import liquibase.Liquibase
import liquibase.Scope
import liquibase.database.DatabaseFactory
import liquibase.database.jvm.JdbcConnection
import liquibase.logging.core.NoOpLogService
import liquibase.resource.ClassLoaderResourceAccessor
import liquibase.ui.LoggerUIService
import org.jetbrains.exposed.v1.jdbc.Database
import javax.sql.DataSource

class SqlPersistence(private val dataSource: DataSource) : Persistence {
	
	constructor(jdbcUrl: String, username: String, password: String) : this(
		HikariDataSource().apply {
			setJdbcUrl(jdbcUrl)
			setUsername(username)
			setPassword(password)
		}
	)
	
	init {
		runLiquibaseMigrations()
		Database.connect(dataSource)
	}
	
	private fun runLiquibaseMigrations() {
		System.setProperty("liquibase.command.update.showSummary", "OFF")
		val scopeAttrs = mapOf(
			Scope.Attr.logService.name to NoOpLogService(),
			Scope.Attr.ui.name to LoggerUIService(),
		)
		Scope.child(scopeAttrs) {
			dataSource.connection.use { connection ->
				val database = DatabaseFactory.getInstance()
					.findCorrectDatabaseImplementation(JdbcConnection(connection))
				Liquibase(
					"db/changelog/db.changelog-master.yaml",
					ClassLoaderResourceAccessor(),
					database
				).update("")
			}
		}
	}

	val json = Json { prettyPrint = true }

	override fun save(game: Game) {
		val json = json.encodeToString(game)
		dataSource.connection.use { connection ->
			connection.prepareStatement(
				"""
				INSERT INTO games (id, game) VALUES (?, ?::jsonb)
				ON CONFLICT (id) DO UPDATE SET game = EXCLUDED.game
				""".trimIndent()
			).use { preparedStatement ->
				preparedStatement.setString(1, game.gameId())
				preparedStatement.setString(2, json)
				preparedStatement.executeUpdate()
			}
		}
	}

	override fun load(gameId: String): Game {
		dataSource.connection.use { connection ->
			connection.prepareStatement(
				"""
				SELECT game FROM games WHERE id = ?
				""".trimIndent()
			).use { preparedStatement ->
				preparedStatement.setString(1, gameId)
				val response = preparedStatement.executeQuery()
				check(response.next()) { "Game not found: $gameId" }
				return json.decodeFromString(response.getString("game"))
			}
		}
	}
	
}

