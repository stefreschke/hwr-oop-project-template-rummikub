package hwr.oop.examples.template.config

import kotlinx.serialization.json.Json
import java.io.File

object ConfigLoader {
	private val json = Json { ignoreUnknownKeys = true }
	
	// Default path is relative to the repository root, as the application
	// is always expected to be launched from there (e.g. via ./mvnw or an IDE run config).
	fun load(path: String = "applications/config.json"): AppConfig {
		val file = File(path)
		require(file.exists()) { "Config file not found: ${file.absolutePath}" }
		return json.decodeFromString<AppConfig>(file.readText())
	}
}
