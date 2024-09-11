package dev.hugosiu.meetCode.model.enumType;

import lombok.Getter;

@Getter
public enum Result {
  PASS(0),
  FAIL(1);

  private final int value;

  Result(int value) {
    this.value = value;
  }

  public static Result fromValue(int value) {
    for (Result result : Result.values()) {
      if (result.getValue() == value) {
        return result;
      }
    }
    throw new IllegalArgumentException("Unknown value: " + value);
  }
}
