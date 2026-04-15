package hwr.oop.examples.template.config

import kotlinx.serialization.Serializable

@Serializable
data class AppConfig(
	val persistence: PersistenceType = PersistenceType.FILE_SYSTEM,
	val fileSystem: FileSystemConfig = FileSystemConfig(),
	val sql: SqlConfig = SqlConfig(),
)

@Serializable
data class FileSystemConfig(
	val directory: String = "./games",
)

@Serializable
data class SqlConfig(
	val jdbcUrl: String = "jdbc:postgresql://localhost:5432/template_db",
	val username: String = "template_user",
	val password: String = "TOTALLY_RANDOM_PASSWORD_1337_42",
)

@Serializable
enum class PersistenceType {
	FILE_SYSTEM,
	SQL
}
