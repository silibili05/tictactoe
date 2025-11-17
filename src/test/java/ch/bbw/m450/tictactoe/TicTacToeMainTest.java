package ch.bbw.m450.tictactoe;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.stream.Stream;

public class TicTacToeMainTest {

    final List<TicTacToePlayer.Stone[]> boards = List.of(
            new TicTacToePlayer.Stone[]{
                    TicTacToePlayer.Stone.CIRCLE, TicTacToePlayer.Stone.CIRCLE, TicTacToePlayer.Stone.CIRCLE,
                    null, null, null,
                    null, null, null
            },
            new TicTacToePlayer.Stone[]{
                    null, null, null,
                    TicTacToePlayer.Stone.CIRCLE, TicTacToePlayer.Stone.CIRCLE, TicTacToePlayer.Stone.CIRCLE,
                    null, null, null
            },
            new TicTacToePlayer.Stone[]{
                    null, null, null,
                    null, null, null,
                    TicTacToePlayer.Stone.CIRCLE, TicTacToePlayer.Stone.CIRCLE, TicTacToePlayer.Stone.CIRCLE,
            },
            new TicTacToePlayer.Stone[]{
                    TicTacToePlayer.Stone.CIRCLE, null, null,
                    TicTacToePlayer.Stone.CIRCLE, null, null,
                    TicTacToePlayer.Stone.CIRCLE, null, null
            },
            new TicTacToePlayer.Stone[]{
                    null, TicTacToePlayer.Stone.CIRCLE, null,
                    null, TicTacToePlayer.Stone.CIRCLE, null,
                    null, TicTacToePlayer.Stone.CIRCLE, null
            },
            new TicTacToePlayer.Stone[]{
                    null, null, TicTacToePlayer.Stone.CIRCLE,
                    null, null, TicTacToePlayer.Stone.CIRCLE,
                    null, null, TicTacToePlayer.Stone.CIRCLE
            },
            new TicTacToePlayer.Stone[]{
                    TicTacToePlayer.Stone.CIRCLE, null, null,
                    null, TicTacToePlayer.Stone.CIRCLE, null,
                    null, null, TicTacToePlayer.Stone.CIRCLE
            },
            new TicTacToePlayer.Stone[]{
                    null, null, TicTacToePlayer.Stone.CIRCLE,
                    null, TicTacToePlayer.Stone.CIRCLE, null,
                    TicTacToePlayer.Stone.CIRCLE, null, null
            }
    );

    final List<Object[]> testCasesList = List.of(
            new Object[]{boards.get(0), TicTacToePlayer.Stone.CIRCLE, true},
            new Object[]{boards.get(1), TicTacToePlayer.Stone.CIRCLE, true},
            new Object[]{boards.get(2), TicTacToePlayer.Stone.CIRCLE, true},
            new Object[]{boards.get(3), TicTacToePlayer.Stone.CIRCLE, true},
            new Object[]{boards.get(4), TicTacToePlayer.Stone.CIRCLE, true},
            new Object[]{boards.get(5), TicTacToePlayer.Stone.CIRCLE, true},
            new Object[]{boards.get(6), TicTacToePlayer.Stone.CIRCLE, true},
            new Object[]{boards.get(7), TicTacToePlayer.Stone.CIRCLE, true},
            new Object[]{boards.get(0), TicTacToePlayer.Stone.CROSS, false},
            new Object[]{boards.get(1), TicTacToePlayer.Stone.CROSS, false},
            new Object[]{boards.get(2), TicTacToePlayer.Stone.CROSS, false},
            new Object[]{boards.get(3), TicTacToePlayer.Stone.CROSS, false},
            new Object[]{boards.get(4), TicTacToePlayer.Stone.CROSS, false},
            new Object[]{boards.get(5), TicTacToePlayer.Stone.CROSS, false},
            new Object[]{boards.get(6), TicTacToePlayer.Stone.CROSS, false},
            new Object[]{boards.get(7), TicTacToePlayer.Stone.CROSS, false}
    );

    public static Stream<Arguments> testCases() {
        TicTacToeMainTest testInstance = new TicTacToeMainTest();
        return testInstance.testCasesList.stream()
                .map(arr -> Arguments.of(arr[0], arr[1], arr[2]));
    }

    @ParameterizedTest
    @MethodSource("testCases")
    void isWinTest(TicTacToePlayer.Stone[] board, TicTacToePlayer.Stone color, boolean expected) {
        var result = TicTacToeMain.isWin(board, color);
        assertThat(result).isEqualTo(expected);
    }
}