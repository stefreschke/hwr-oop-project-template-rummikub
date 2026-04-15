package hwr.oop.examples.template

import okio.FileSystem

class FileSystemPersistence(
	configuration: FileSystemPersistenceConfiguration,
	private val fileSystem: FileSystem = FileSystem.SYSTEM,
) {
	private val directory = configuration.directory
}

