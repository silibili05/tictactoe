package ch.bbw.m450.tictactoe.players;

import ch.bbw.m450.tictactoe.TicTacToeMain;
import ch.bbw.m450.tictactoe.TicTacToePlayer;
import ch.bbw.m450.tictactoe.TicTacToePlayer.Stone;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class GreedyPlayerTest {

    static class SequencePlayer implements TicTacToePlayer {
        private final int[] moves;
        private int idx = 0;
        SequencePlayer(int... moves) { this.moves = moves; }
        @Override
        public int play(Stone[] board, Stone currentColor) {
            if (idx >= moves.length) {
                throw new IllegalStateException("keine weiteren Züge");
            }
            return moves[idx++];
        }
    }

    @Test
    void playXWins() {
        var x = new SequencePlayer(0, 1, 2);
        var o = new SequencePlayer(3, 4);
        var result = TicTacToeMain.play(x, o);
        assertThat(result).isEqualTo(Stone.CROSS);
    }

    @Test
    void playOWins() {
        var x = new SequencePlayer(0, 8, 7);
        var o = new SequencePlayer(4, 2, 6);
        var result = TicTacToeMain.play(x, o);
        assertThat(result).isEqualTo(Stone.CIRCLE);
    }

    @Test
    void playTie() {
        var x = new SequencePlayer(0, 2, 3, 7, 8);
        var o = new SequencePlayer(1, 4, 5, 6);
        var result = TicTacToeMain.play(x, o);
        assertThat(result).isNull();
    }

    @Test
    void playThrowsIllegalArgumentException() {
        var p = new SequencePlayer(0);
        assertThatThrownBy(() -> TicTacToeMain.play(p, p))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("players must differ");
    }

    @Test
    void playThrowsIllegalStateException() {
        var x = new SequencePlayer(0, 1, 2);
        var o = new SequencePlayer(0); // Feld 0 bereits belegt
        assertThatThrownBy(() -> TicTacToeMain.play(x, o))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("cannot play");
    }
}