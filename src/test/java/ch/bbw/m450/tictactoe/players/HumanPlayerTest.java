package ch.bbw.m450.tictactoe.players;

import ch.bbw.m450.tictactoe.TicTacToePlayer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.assertj.core.api.Assertions.assertThat;

public class HumanPlayerTest { //made by myself


    private InputStream originalIn;

    @BeforeEach
    void backupIn() {
        originalIn = System.in;
    }

    @AfterEach
    void restoreIn() {
        System.setIn(originalIn);
    }

    @Test
    void playGetInputRight() {
        System.setIn(new ByteArrayInputStream("4\n".getBytes()));
        TicTacToePlayer p = new HumanPlayer();
        TicTacToePlayer.Stone[] board = new TicTacToePlayer.Stone[9];
        int move = p.play(board, TicTacToePlayer.Stone.CROSS);
        assertThat(move).isEqualTo(4);
    }


}
