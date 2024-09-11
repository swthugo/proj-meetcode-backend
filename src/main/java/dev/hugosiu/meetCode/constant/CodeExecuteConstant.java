package dev.hugosiu.meetCode.constant;

public class CodeExecuteConstant {
  public static final String JUNIT_STANDALONE_JAR = "./backend/src/main/resources/junit-platform-console-standalone-1.5.2.jar";
  public static final String SAMPLE_DATA_JSON = "/resources/problems.json";
  /* Directory */
  public static final String TEMP_FILE_PATH = "./../temp_MeetMeet";

  public static final String LOG_FILE_NAME = "log.txt";

  /* file name */
  public static final String CODE_CLASS_PREFIX = "Problem";
  public static final String TEST_CLASS_SUFFIX = "Test";
  public static final String FILE_TYPE = ".java";

  /* Content ProblemSet up */
  public static final String PACKAGE_NAME_HEAD = "package user_";
  public static final String PACKAGE_NAME_TAIL = ".problem_";
  public static final String MAIN_CLASS_SAMPLE = "public class Problem {}\n";


  public static final String JUNIT_OPENING = "Thanks for using JUnit! Support its development at https://junit.org/sponsoring";
  public static final String TEST_CODE = "import org.junit.jupiter.api.DisplayName;\n" +
          "import org.junit.jupiter.api.Test;\n" +
          "import org.junit.jupiter.params.ParameterizedTest;\n" +
          "import org.junit.jupiter.params.provider.CsvSource;\n" +
          "import static org.junit.jupiter.api.Assertions.assertEquals;\n" +
          "class ProblemTest {\n" +
          "    private final Solution calculator = new Solution();\n" +
          "    @Test\n" +
          "    @DisplayName(\"1 + 2 = 3\")\n" +
          "    void addsTwoNumbers() {\n" +
          "        assertEquals(3, calculator.add(1, 2), \"1 + 2 should equal 3\");\n" +
          "    }\n" +
          "    @Test\n" +
          "    @DisplayName(\"3 - 1 = 2\")\n" +
          "    void minusTwoNumbers() {\n" +
          "        assertEquals(2, calculator.minus(3, 1), \"3 - 1 should equal 2\");\n" +
          "    }\n" +
          "    @ParameterizedTest(name = \"{0} + {1} = {2}\")\n" +
          "    @CsvSource({\n" +
          "            \"0,    1,   1\",\n" +
          "            \"1,    2,   3\",\n" +
          "            \"49,  51, 100\",\n" +
          "            \"1,  100, 101\"\n" +
          "    })\n" +
          "    void add(int first, int second, int expectedResult) {\n" +
          "        assertEquals(expectedResult, calculator.add(first, second),\n" +
          "                () -> first + \" + \" + second + \" should equal \" + expectedResult);\n" +
          "    }\n" +
          "}";
}
