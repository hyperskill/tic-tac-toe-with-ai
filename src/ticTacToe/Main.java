package ticTacToe;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main
{
    public static void main(String[] args)
    {
        Player playerOne = new Player(Element.O);
        Player playerTwo = new Player(Element.X);
        Board board = new Board();
        Game game = new Game.Builder()
                .board(board)
                .playerOne(playerOne)
                .playerTwo(playerTwo)
                .build();
        game.play();
        System.out.println(board);
    }

    public enum Element
    {
        X, O,
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
        private Board board;
        private Player playerOne;
        private Player playerTwo;

        private Game()
        {
        }

        private Game(Board board, Player playerOne, Player playerTwo)
        {
            this.board = board;
            this.playerOne = playerOne;
            this.playerTwo = playerTwo;
        }

        public void play()
        {
            board.setElementToPosition(0, 0, playerOne.getElement());
            board.setElementToPosition(0, 2, playerOne.getElement());
            board.setElementToPosition(1, 0, playerTwo.getElement());
            board.setElementToPosition(1, 1, playerTwo.getElement());
            board.setElementToPosition(1, 2, playerOne.getElement());
            board.setElementToPosition(2, 0, playerTwo.getElement());
            board.setElementToPosition(2, 1, playerTwo.getElement());

        }


        public Board getBoard()
        {
            return board;
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

        public void setElementToPosition(int lineIndex, int inLineIndex, Element element)
        {
            if (lineIndex < 0 || lineIndex >= lines.length) {
                throw new RuntimeException("Incorrect index");
            }
            lines[lineIndex].setElementToIndex(inLineIndex, element);
        }

        @Override
        public String toString()
        {
            return String.join(System.lineSeparator(),
                    Stream.of(lines)
                            .map(Line::toString)
                            .collect(Collectors.toList())
            );
        }
    }

    public static class Line
    {
        Element[] line;

        public Line()
        {
            this.line = new Element[]{Element.EMPTY, Element.EMPTY, Element.EMPTY};
        }

        public void setElementToIndex(int index, Element element)
        {
            if (index < 0 || index >= line.length) {
                throw new RuntimeException("Incorrect index");
            }
            line[index] = element;
        }

        @Override
        public String toString()
        {
            return String.join(" ",
                    Stream.of(line)
                            .map(Element::toString)
                            .collect(Collectors.toList())
            );
        }
    }
}