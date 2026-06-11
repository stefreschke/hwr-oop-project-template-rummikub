package hwr.oop.examples.template

import okio.FileSystem
import okio.Path.Companion.toPath
import okio.fakefilesystem.FakeFileSystem
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test

class FileSystemPersistenceTest {
	
	private val fakeFileSystem = FileSystem.SYSTEM
	private val tempDir = "/target".toPath()
	private val sut: FileSystemPersistence
	
	init {
		fakeFileSystem.createDirectories(tempDir)
		sut = FileSystemPersistence(
			FileSystemPersistenceConfiguration(tempDir),
			fakeFileSystem
		)
	}
	
//	@AfterEach
//	fun tearDown() {
//		fakeFileSystem.checkNoOpenFiles()
//	}
//
//	@Test
//	fun `do nothing`() {
//		// given
//		// when
//		// then
//	}
	
}

