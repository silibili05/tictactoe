package ch.bbw.m450.tictactoe.players;

public class PerfectPlayer {
    private static final int SIZE = 3;
    private static final char EMPTY = ' ';

    public int[] nextMove(char[][] board, char mySymbol) {
        char opponent = opponentOf(mySymbol);
        int bestScore = Integer.MIN_VALUE;
        int[] best = null;
        for (int r = 0; r < SIZE; r++) {
            for (int c = 0; c < SIZE; c++) {
                if (board[r][c] == EMPTY) {
                    board[r][c] = mySymbol;
                    int score = minimax2D(board, 0, false, mySymbol, opponent, Integer.MIN_VALUE, Integer.MAX_VALUE);
                    board[r][c] = EMPTY;
                    if (score > bestScore) {
                        bestScore = score;
                        best = new int[]{r, c};
                    }
                }
            }
        }
        return best;
    }

    public int nextMoveLinear(char[] board, char mySymbol) {
        char opponent = opponentOf(mySymbol);
        int bestScore = Integer.MIN_VALUE;
        int bestIndex = -1;
        for (int i = 0; i < 9; i++) {
            if (board[i] == EMPTY) {
                board[i] = mySymbol;
                int score = minimaxLinear(board, 0, false, mySymbol, opponent, Integer.MIN_VALUE, Integer.MAX_VALUE);
                board[i] = EMPTY;
                if (score > bestScore) {
                    bestScore = score;
                    bestIndex = i;
                }
            }
        }
        return bestIndex;
    }

    private int minimax2D(char[][] board, int depth, boolean isMax, char me, char opp, int alpha, int beta) {
        Integer terminal = evaluate2D(board, me, opp, depth);
        if (terminal != null) return terminal;

        if (isMax) {
            int best = Integer.MIN_VALUE;
            for (int r = 0; r < SIZE; r++) {
                for (int c = 0; c < SIZE; c++) {
                    if (board[r][c] == EMPTY) {
                        board[r][c] = me;
                        int score = minimax2D(board, depth + 1, false, me, opp, alpha, beta);
                        board[r][c] = EMPTY;
                        best = Math.max(best, score);
                        alpha = Math.max(alpha, best);
                        if (beta <= alpha) return best;
                    }
                }
            }
            return best;
        } else {
            int best = Integer.MAX_VALUE;
            for (int r = 0; r < SIZE; r++) {
                for (int c = 0; c < SIZE; c++) {
                    if (board[r][c] == EMPTY) {
                        board[r][c] = opp;
                        int score = minimax2D(board, depth + 1, true, me, opp, alpha, beta);
                        board[r][c] = EMPTY;
                        best = Math.min(best, score);
                        beta = Math.min(beta, best);
                        if (beta <= alpha) return best;
                    }
                }
            }
            return best;
        }
    }

    private int minimaxLinear(char[] board, int depth, boolean isMax, char me, char opp, int alpha, int beta) {
        Integer terminal = evaluateLinear(board, me, opp, depth);
        if (terminal != null) return terminal;

        if (isMax) {
            int best = Integer.MIN_VALUE;
            for (int i = 0; i < 9; i++) {
                if (board[i] == EMPTY) {
                    board[i] = me;
                    int score = minimaxLinear(board, depth + 1, false, me, opp, alpha, beta);
                    board[i] = EMPTY;
                    best = Math.max(best, score);
                    alpha = Math.max(alpha, best);
                    if (beta <= alpha) return best;
                }
            }
            return best;
        } else {
            int best = Integer.MAX_VALUE;
            for (int i = 0; i < 9; i++) {
                if (board[i] == EMPTY) {
                    board[i] = opp;
                    int score = minimaxLinear(board, depth + 1, true, me, opp, alpha, beta);
                    board[i] = EMPTY;
                    best = Math.min(best, score);
                    beta = Math.min(beta, best);
                    if (beta <= alpha) return best;
                }
            }
            return best;
        }
    }

    private Integer evaluate2D(char[][] board, char me, char opp, int depth) {
        if (isWin2D(board, me)) return 10 - depth;
        if (isWin2D(board, opp)) return -(10 - depth);
        if (isFull2D(board)) return 0;
        return null;
    }

    private Integer evaluateLinear(char[] board, char me, char opp, int depth) {
        if (isWinLinear(board, me)) return 10 - depth;
        if (isWinLinear(board, opp)) return -(10 - depth);
        if (isFullLinear(board)) return 0;
        return null;
    }

    private boolean isFull2D(char[][] board) {
        for (int r = 0; r < SIZE; r++)
            for (int c = 0; c < SIZE; c++)
                if (board[r][c] == EMPTY) return false;
        return true;
    }

    private boolean isWin2D(char[][] b, char s) {
        for (int i = 0; i < SIZE; i++) {
            if (b[i][0] == s && b[i][1] == s && b[i][2] == s) return true;
            if (b[0][i] == s && b[1][i] == s && b[2][i] == s) return true;
        }
        return (b[0][0] == s && b[1][1] == s && b[2][2] == s) ||
               (b[0][2] == s && b[1][1] == s && b[2][0] == s);
    }

    private boolean isFullLinear(char[] board) {
        for (char c : board) if (c == EMPTY) return false;
        return true;
    }

    private boolean isWinLinear(char[] b, char s) {
        int[][] lines = {
                {0,1,2},{3,4,5},{6,7,8},
                {0,3,6},{1,4,7},{2,5,8},
                {0,4,8},{2,4,6}
        };
        for (int[] line : lines)
            if (b[line[0]] == s && b[line[1]] == s && b[line[2]] == s) return true;
        return false;
    }

    private char opponentOf(char me) {
        return me == 'X' ? 'O' : 'X';
    }
}
