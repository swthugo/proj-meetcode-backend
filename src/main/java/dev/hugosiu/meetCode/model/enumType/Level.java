package dev.hugosiu.meetCode.model.enumType;

import lombok.Getter;

@Getter
public enum Level {
  EASY("Easy"), MEDIUM("Medium"), HARD("Hard");

  private final String value;

  Level(String value) {
    this.value = value;
  }

  public static Level of(String value) {
    for (Level level : Level.values()) {
      if (level.value.equalsIgnoreCase(value)) {
        return level;
      }
    }
    throw new IllegalArgumentException("No level found for value: " + value);
  }

  @Override
  public String toString() {
    return value;
  }
}
