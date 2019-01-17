package ticTacToe;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main
{
    public static void main(String[] args)
    {
//        X X X
//        O O
//        O
//        X wins
//        play(
//                new Position(1, 0),
//                new Position(0, 0),
//                new Position(1, 1),
//                new Position(0, 1),
//                new Position(2, 1),
//                new Position(0, 2)
//        );
//        X O O
//        O X O
//        X X O
//        O wins
//        play(
//                new Position(1, 0),
//                new Position(0, 0),
//                new Position(2, 2),
//                new Position(2, 0),
//                new Position(0, 2),
//                new Position(1, 1),
//                new Position(0, 1),
//                new Position(2, 1),
//                new Position(1, 2)
//        );
        play();
    }

    public static void play(Position... positions)
    {
        Player playerOne = new Player(Element.O);
        Player playerTwo = new Player(Element.X);
        Scanner scanner = new Scanner(System.in);
        Board board = new Board();
        Game game = new Game.Builder()
                .board(board)
                .playerOne(playerOne)
                .playerTwo(playerTwo)
                .build();

        Gui gui = new Gui(game);
        game.addSubcriber(gui);

        while (!game.isFinished()) {
            game.turn(new Turn(scanner).readCoordinates().toPosition());
        }
        gui.printResult();
    }

    public static class Turn
    {
        private Scanner scanner;
        private int readRow;
        private int readColumn;
        private static final int MAX_NUMBER = 3;

        public Turn(Scanner scanner)
        {
            this.scanner = scanner;
        }

        public Turn readCoordinates()
        {
            System.out.print("Enter the coordinates: ");
            String[] coords = scanner.nextLine().split(" ");
            readRow = Integer.valueOf(coords[0]);
            readColumn = Integer.valueOf(coords[1]);
            return this;
        }

        public Position toPosition()
        {
            return new Position(MAX_NUMBER - readRow, readColumn - 1);
        }

    }

    public interface Subscription
    {
        void update();
    }

    public static class Gui implements Subscription
    {
        private Game game;

        public Gui(Game game)
        {
            this.game = game;
        }

        @Override
        public void update()
        {
            printCurrentBoardState();
        }

        private void printCurrentBoardState()
        {
            print(game.getBoard());
        }

        public void printResult()
        {
            printCurrentBoardState();
            String result;
            switch (game.getStatus()) {
                case FINISHED:
                    result = game.getWinnerElement() + " wins";
                    break;
                case DRAW:
                    result = "Draw";
                    break;
                default:
                    result = "Game not finished";
            }
            print(result);
        }

        private void print(Object object)
        {
            System.out.println(object);
        }
    }

    public static class Position
    {
        private int row;
        private int column;

        public Position(int row, int column)
        {
            this.row = row;
            this.column = column;
        }

        public int getRow()
        {
            return row;
        }

        public int getColumn()
        {
            return column;
        }

        @Override
        public boolean equals(Object o)
        {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Position position = (Position) o;
            return row == position.row &&
                    column == position.column;
        }

        @Override
        public int hashCode()
        {
            return Objects.hash(row, column);
        }

        @Override
        public String toString()
        {
            return "P" + row + "," + column;
        }
    }

    public static class WinnerPosition
    {
        private List<Position> positions;
        private boolean isWinner;
        private Element winnerElement;

        public WinnerPosition(Position p1, Position p2, Position p3)
        {
            this.positions = new ArrayList<>(Arrays.asList(p1, p2, p3));
        }

        public boolean isWinnerForBoard(Board board)
        {
            Set<Element> elements = positions.stream()
                    .map(board::getElementByPosition)
                    .collect(Collectors.toSet());
            if (elements.size() == 1 && !elements.contains(Element.EMPTY)) {
                isWinner = true;
                winnerElement = board.getElementByPosition(positions.get(0));
            }
            return isWinner;
        }

        public Element getWinnerElement()
        {
            if (isWinner) {
                isWinner = false;
                Element cloneWinner = Element.valueOf(winnerElement.name());
                winnerElement = Element.EMPTY;
                return cloneWinner;
            }
            throw new RuntimeException("There is no winner");
        }

        @Override
        public String toString()
        {
            return Arrays.toString(positions.toArray());
        }
    }

    public static class WinnerPositions
    {
        private static WinnerPositions INSTANCE;
        private List<WinnerPosition> possibleWinnerPositions;

        private WinnerPositions()
        {
            this.possibleWinnerPositions = new ArrayList<>(
                    Arrays.asList(
                            new WinnerPosition(
                                    new Position(0, 0),
                                    new Position(1, 0),
                                    new Position(2, 0)
                            ),
                            new WinnerPosition(
                                    new Position(0, 1),
                                    new Position(1, 1),
                                    new Position(2, 1)
                            ),
                            new WinnerPosition(
                                    new Position(0, 2),
                                    new Position(1, 2),
                                    new Position(2, 2)
                            ),

                            new WinnerPosition(
                                    new Position(0, 0),
                                    new Position(0, 1),
                                    new Position(0, 2)
                            ),
                            new WinnerPosition(
                                    new Position(1, 0),
                                    new Position(1, 1),
                                    new Position(1, 2)
                            ),
                            new WinnerPosition(
                                    new Position(2, 0),
                                    new Position(2, 1),
                                    new Position(2, 2)
                            ),

                            new WinnerPosition(
                                    new Position(0, 0),
                                    new Position(1, 1),
                                    new Position(2, 2)
                            ),
                            new WinnerPosition(
                                    new Position(2, 0),
                                    new Position(1, 1),
                                    new Position(0, 2)
                            )
                    )
            );
        }

        public static WinnerPositions getInstance()
        {
            if (INSTANCE == null) {
                INSTANCE = new WinnerPositions();
            }
            return INSTANCE;
        }

        public List<WinnerPosition> getPossibleWinnerPositions()
        {
            return this.possibleWinnerPositions;
        }
    }

    public enum Element
    {
        X,
        O,
        EMPTY {
            @Override
            public String toString()
            {
                return " ";
            }
        }
    }

    public static class Player
    {
        Element element;

        public Player(Element element)
        {
            this.element = element;
        }

        public Element getElement()
        {
            return element;
        }
    }


    public static class Game
    {
        public static final int MAX_TURNS_COUNT = 9;
        private Board board;
        private Player playerOne;
        private Player playerTwo;
        private boolean wasFirstPlayerTurn;
        private int turnsCount;
        private final static int MIN_TURNS_COUNT_FOR_CHECK = 5;
        private static final WinnerPositions winnerPositions = WinnerPositions.getInstance();
        private Element winnerElement;
        private GameStatus status;
        private List<Subscription> subscribers;

        private Game(Board board, Player playerOne, Player playerTwo)
        {
            this.board = board;
            this.playerOne = playerOne;
            this.playerTwo = playerTwo;
            status = GameStatus.NOT_FINISHED;
            subscribers = new ArrayList<>();
        }

        public void play(Position... turns)
        {
            for (Position position : turns) {
                if (isFinished()) {
                    break;
                }
                turn(position);
            }
        }

        public void addSubcriber(Subscription subscription)
        {
            subscribers.add(subscription);
        }

        public void removeSubcriber(Subscription subscription)
        {
            subscribers.remove(subscription);
        }

        public boolean isFinished()
        {
            return status == GameStatus.FINISHED || status == GameStatus.DRAW;
        }

        public void turn(Position position)
        {
            Player player = wasFirstPlayerTurn ? playerTwo : playerOne;
            board.setElementToPosition(position, player.getElement());
            wasFirstPlayerTurn = !wasFirstPlayerTurn;
            notifySubscribers();

            checkWinner();
        }

        private void notifySubscribers()
        {
            subscribers.forEach(Subscription::update);
        }

        private void checkWinner()
        {
            if (++turnsCount >= MIN_TURNS_COUNT_FOR_CHECK) {
                boolean isFinished = winnerPositions.getPossibleWinnerPositions().stream()
                        .anyMatch(winnerPosition -> {
                            boolean isWinnerForBoard = winnerPosition.isWinnerForBoard(board);
                            if (isWinnerForBoard) {
                                setWinnerElement(winnerPosition.getWinnerElement());
                            }
                            return isWinnerForBoard;
                        });
                if (isFinished) {
                    status = GameStatus.FINISHED;
                    return;
                }
            }

            if (turnsCount == MAX_TURNS_COUNT) {
                status = GameStatus.DRAW;
            }
        }

        public GameStatus getStatus()
        {
            return status;
        }

        public Board getBoard()
        {
            return board;
        }

        private void setWinnerElement(Element winner)
        {
            this.winnerElement = winner;
        }

        public Element getWinnerElement()
        {
            return winnerElement;
        }

        static class Builder
        {
            private Board board;
            private Player playerOne;
            private Player playerTwo;

            public Builder()
            {
            }

            public Builder board(Board board)
            {
                this.board = board;
                return this;
            }

            public Builder playerOne(Player playerOne)
            {
                this.playerOne = playerOne;
                return this;
            }

            public Builder playerTwo(Player playerTwo)
            {
                this.playerTwo = playerTwo;
                return this;
            }

            public Game build()
            {
                return new Game(board, playerOne, playerTwo);
            }
        }
    }

    public enum GameStatus
    {
        DRAW, NOT_FINISHED, FINISHED;
    }

    public static class Board
    {
        Line[] lines;

        public Board()
        {
            init();
        }

        private void init()
        {
            this.lines = new Line[]{new Line(), new Line(), new Line()};
        }

        public boolean hasEmptyPosition()
        {
            return Stream.of(lines)
                    .flatMap(line -> Stream.of(line.getElements()))
                    .anyMatch(element -> Element.EMPTY == element);
        }

        public void setElementToPosition(Position position, Element element)
        {
            if (position.getRow() < 0 || position.getRow() >= lines.length) {
                throw new RuntimeException("Incorrect index");
            }
            lines[position.getRow()].setElementToIndex(position.getColumn(), element);
        }

        public Element getElementByPosition(Position position)
        {
            return lines[position.getRow()].getElementByIndex(position.getColumn());
        }

        @Override
        public String toString()
        {
            String border = "---------";
            return border + System.lineSeparator() +
                    String.join(System.lineSeparator(),
                            Stream.of(lines)
                                    .map(Line::toString)
                                    .collect(Collectors.toList())
                    ) + System.lineSeparator() + border;
        }
    }

    public static class Line
    {
        Element[] line;

        public Line()
        {
            this.line = new Element[]{Element.EMPTY, Element.EMPTY, Element.EMPTY};
        }

        public Element[] getElements()
        {
            return line;
        }

        public void setElementToIndex(int index, Element element)
        {
            checkBounds(index);
            line[index] = element;
        }

        public Element getElementByIndex(int index)
        {
            checkBounds(index);
            return line[index];
        }

        private void checkBounds(int index)
        {
            if (index < 0 || index >= line.length) {
                throw new RuntimeException("Incorrect index: " + index);
            }
        }

        @Override
        public String toString()
        {
            return "| " +
                    String.join(" ",
                            Stream.of(line)
                                    .map(Element::toString)
                                    .collect(Collectors.toList())
                    )
                    + " |";
        }
    }
}