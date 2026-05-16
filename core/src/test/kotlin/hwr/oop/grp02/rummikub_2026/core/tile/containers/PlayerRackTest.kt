package hwr.oop.grp02.rummikub_2026.core.tile.containers

import hwr.oop.grp02.rummikub_2026.core.TileValue
import hwr.oop.grp02.rummikub_2026.core.tile.Tile
import hwr.oop.grp02.rummikub_2026.core.tile.TileColor
import hwr.oop.grp02.rummikub_2026.core.tile.TileNumber
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class PlayerRackTest {

    lateinit var rack : PlayerRack

    @BeforeEach
    fun setUp() {
        rack = PlayerRack()
    }


    val blueOne = Tile(TileNumber.One, TileColor.Blue)
    val blueTwo = Tile(TileNumber.Two, TileColor.Blue)
    val blueThree = Tile(TileNumber.Three, TileColor.Blue)

    @Test
    fun `tiles method returns a list`() {
        val list = rack.tiles()
        assertThat(list).isInstanceOf(List::class.java)
    }

    @Test
    fun `tiles method returns an empty list`() {
        val list = rack.tiles()
        assertThat(list).isEmpty()
    }

    @Test
    fun `draw method adds exactly one tile to the list`() {
        rack.draw(blueTwo)
        assertThat(rack.tiles()).containsExactly(blueTwo)
    }

    @Test
    fun `drawing nothing does not change the list`() {
        val tiles = rack.tiles()
        rack.draw()
        assertThat(rack.tiles()).isEqualTo(tiles)
    }

    @Test
    fun `playing a tile removes the tile from the list`() {
        rack.draw(blueOne)
        rack.play(blueTwo)
        assertThat(rack.tiles()).containsExactly(blueOne)
    }

    @Test
    fun `play method only allows removing tiles that exist`() {
        rack.draw(blueOne)
        rack.play(blueTwo)
        assertThat(rack.tiles()).containsExactly(blueOne)
    }

}
