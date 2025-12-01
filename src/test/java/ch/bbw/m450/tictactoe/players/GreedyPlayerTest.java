package ch.bbw.m450.tictactoe.players;

import ch.bbw.m450.tictactoe.TicTacToeMain;
import ch.bbw.m450.tictactoe.TicTacToePlayer.Stone;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class GreedyPlayerTest { //All made by myself

    private final GreedyPlayer player = new GreedyPlayer();

    @Test
    void playFirstMove() {
        Stone[] board = new Stone[TicTacToeMain.BOARD_SIZE];
        int move = player.play(board, Stone.CROSS);
        assertThat(move).isEqualTo(0);
    }

    @Test
    void playSecondMove() {
        Stone[] board = new Stone[TicTacToeMain.BOARD_SIZE];
        board[0] = Stone.CROSS;
        board[1] = Stone.CIRCLE;
        board[2] = Stone.CROSS;
        int move = player.play(board, Stone.CIRCLE);
        assertThat(move).isEqualTo(3);
    }

    @Test
    void playLastMove() {
        Stone[] board = new Stone[TicTacToeMain.BOARD_SIZE];
        for (int i = 0; i < board.length - 1; i++) {
            board[i] = (i % 2 == 0) ? Stone.CROSS : Stone.CIRCLE;
        }
        int move = player.play(board, Stone.CROSS);
        assertThat(move).isEqualTo(board.length - 1);
    }

    @Test
    void playThrowsIllegalStateException() {
        Stone[] board = new Stone[TicTacToeMain.BOARD_SIZE];
        for (int i = 0; i < board.length; i++) {
            board[i] = Stone.CROSS;
        }
        assertThatThrownBy(() -> player.play(board, Stone.CIRCLE))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("cannot play");
    }
}