package ticTacToe;

public class Game {
  private Field field;

  public Game() {
    this.field = new Field();
  }

  public void start() {
    field.draw();
  }

  public void over() {
    field.printResult();
  }

}
