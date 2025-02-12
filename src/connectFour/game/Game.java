package connectFour.game;

import connectFour.*;
import connectFour.board.Board;
import connectFour.board.BoardConsoleRenderer;
import connectFour.circle.Color;
import connectFour.circle.RedCircle;
import connectFour.circle.YellowCircle;

import java.util.Scanner;

public class Game {
    private Board board;
    private GameState state = GameState.PLAYING;
    private Color colorToMove = Color.RED;
    private final static Scanner SCANNER = new Scanner(System.in);
    private final DetermineGameState determineGameState = new DetermineGameState();
    private final Validation validation = new Validation();
    private boolean isPlayAgain = true;
    private int turnCounter = 0;
    private static final String START_GAME = "1";
    private static final String EXIT_GAME = "2";

    public Game(Board board) {
        this.board = board;
    }

    public void startMenu() {
        while (isPlayAgain) {
            System.out.printf("Добро пожаловать в игру! Нажмите %s чтобы начать игру, %s - выйти из игры %n", START_GAME, EXIT_GAME);
            makeChoice(checkInput());
        }
    }

    private String checkInput() {
        while (true) {
            String input = SCANNER.nextLine();
            if (!input.equals(START_GAME) && !input.equals(EXIT_GAME)) {
                System.out.printf("Введите %s или %s %n", START_GAME, EXIT_GAME);
                continue;
            }
            return input;
        }
    }

    private void makeChoice(String input) {
        if (input.equals(START_GAME)) {
            resetGame();
            System.out.println();
            gameLoop();
        } else if (input.equals(EXIT_GAME)) {
            System.out.println("Игра окончена. До свидания!");
            SCANNER.close();
            isPlayAgain = false;
        }
    }

    private void gameLoop() {
        BoardConsoleRenderer renderer = new BoardConsoleRenderer();
        renderer.render(board);

        while (state == GameState.PLAYING) {
            printMessageForUser();
            Coordinates inputCoordinates = getCoordinatesFromUser();

            if (!validation.isCoordinatesCorrect(inputCoordinates, board)) {
                continue;
            }

            setCircle(inputCoordinates);
            System.out.println("-----------------------------------------------");
            renderer.render(board);
            state = determineGameState.checkGameState(board);
            colorToMove = colorToMove.opposite();
            turnCounter++;
            checkWinner();
        }
    }

    private void printMessageForUser() {
        if (colorToMove == Color.RED) {
            System.out.println("Красный игрок введите координаты (номер ряда и номер столбца без пробелов)");
        } else {
            System.out.println("Желтый игрок введите координаты (номер ряда и номер столбца без пробелов)");
        }
    }

    private Coordinates getCoordinatesFromUser() {
        String input = validation.isInputValid();
        String[] parts = input.split("");
        int printRow = Integer.parseInt(parts[0]);
        int printColumn = Integer.parseInt(parts[1]);
        Coordinates inputCoordinates = new Coordinates(printRow, printColumn);
        return inputCoordinates;
    }

    private void setCircle(Coordinates coordinates) {
        if (colorToMove == Color.RED) {
            board.setCircle(coordinates, new RedCircle(Color.RED));
        } else {
            board.setCircle(coordinates, new YellowCircle(Color.YELLOW));
        }
    }


    private void checkWinner() {
        int maximumPossibleTurns = board.getHeight() * board.getWidth();
        if (state == GameState.RED_WINS) {
            System.out.println("Красный игрок победил!");
        } else if (state == GameState.YELLOW_WINS) {
            System.out.println("Желтый игрок победил!");
        } else if(turnCounter == maximumPossibleTurns) {
            System.out.println("Эта игра завершилась вничью!");
            state = GameState.DRAW;
        }
    }

    private void resetGame() {
        state = GameState.PLAYING;
        this.board = new Board(board.getHeight(), board.getWidth());
        this.colorToMove = Color.RED;
        this.turnCounter = 0;
    }

}