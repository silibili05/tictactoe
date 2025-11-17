package ch.bbw.m450.tictactoe;

import org.junit.jupiter.api.Test;
import org.junitpioneer.jupiter.StdIn;
import org.junitpioneer.jupiter.StdIo;
import org.junitpioneer.jupiter.StdOut;

import static org.assertj.core.api.Assertions.assertThat;

public class TicTacToeConsoleTest {

    @Test
    @StdIo({ "1", "2", "3" })
    void test(StdIn in, StdOut out) {
        String[] lines = in.capturedLines();
        String outStr = out.capturedString();
    }
}
