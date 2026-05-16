package hwr.oop.grp02.rummikub_2026.core

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class TilesTest {
	
	@Test
	fun `color has all four possible values`() {
		val entries = TileColor.entries;
		assertThat(entries).containsExactlyInAnyOrder(
			TileColor.Red,
			TileColor.Black,
			TileColor.Blue,
			TileColor.Orange
		)
	}
	
	@Test
	fun `value has all 13 possible values`() {
		val entries = TileValue.entries;
		
		assertThat(entries).containsExactlyInAnyOrder(
			TileValue.One,
			TileValue.Two,
			TileValue.Three,
			TileValue.Four,
			TileValue.Five,
			TileValue.Six,
			TileValue.Seven,
			TileValue.Eight,
			TileValue.Nine,
			TileValue.Ten,
			TileValue.Eleven,
			TileValue.Twelve,
			TileValue.Thirteen,
		)
	}
	
	@Test
	fun `correct int mapping of TileValue`() {
		val entries = TileValue.entries;
		val numList = (1..13).toList()
		val values = entries.map { it.value() }
		assertThat(values).containsExactlyElementsOf(numList);
	}
}


