//package dev.hugosiu.meetCode.service;
//
//import dev.hugosiu.meetCode.constant.CodeExecuteConstant;
//import dev.hugosiu.meetCode.dto.RunConsoleDTOTEst;
//import dev.hugosiu.meetCode.dto.TestConsoleDTO;
//import dev.hugosiu.meetCode.exception.CompilerFailException;
//import dev.hugosiu.meetCode.model.enumType.Result;
//import jakarta.validation.constraints.NotBlank;
//import org.springframework.stereotype.Service;
//
//import java.io.*;
//import java.nio.charset.StandardCharsets;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.nio.file.StandardOpenOption;
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Service
//public class CodeExecutionService {
//
////  public static void main(String[] args) throws IOException {
////    System.out.println("Project root location: " + Paths.get("").toAbsolutePath());
////
////    String code = "class Solution {\n" +
////            "  public int add(int a, int b) { return a + b; }\n" +
////            "  public int minus(int a, int b) { return a * b; }\n" +
////            "}";
////
////    String userId = "001";
////    Long idx = Long.valueOf(1);
////
////    String log = executeCode(userId, idx, code);
////
////    System.out.println(log);
////  }
//
////  public static TestConsoleDTO executeProblemSolutionAndTestCode(Long problemIdx, String solutionCode, String testCaseCode) throws IOException {
////    String[] directories = {
////            CodeExecuteConstant.TEMP_FILE_PATH,
////            CodeExecuteConstant.TEMP_FILE_PATH + "/classes",
////            CodeExecuteConstant.TEMP_FILE_PATH + "/classes/admin",
////            CodeExecuteConstant.TEMP_FILE_PATH + "/classes/admin" + "/problem_" + problemIdx,
////            CodeExecuteConstant.TEMP_FILE_PATH + "/test-classes/",
////            CodeExecuteConstant.TEMP_FILE_PATH + "/test-classes/admin",
////            CodeExecuteConstant.TEMP_FILE_PATH + "/test-classes/admin" + "/problem_" + problemIdx,
////    };
////
////    for (String directory : directories) {
////      createDirectory(Paths.get(directory));
////    }
////
////    String adminSolutionPath = CodeExecuteConstant.TEMP_FILE_PATH + "/classes/admin" + "/problem_" + problemIdx;
////    String adminTestCasePath = CodeExecuteConstant.TEMP_FILE_PATH + "/test-classes/admin" + "/problem_" + problemIdx;
////    String packageName = "package admin" + CodeExecuteConstant.PACKAGE_NAME_TAIL + problemIdx;
////
////    createJavaFile(adminSolutionPath, packageName, solutionCode);
////    createJavaTestFile(adminTestCasePath, packageName, testCaseCode);
////
////    String classPath = CodeExecuteConstant.TEMP_FILE_PATH + "/classes";
////    String testClassPath = CodeExecuteConstant.TEMP_FILE_PATH + "/test-classes";
////    String targetPackage = "admin.problem_" + problemIdx;
////
////    return runTestJarForAdmin(classPath, testClassPath, targetPackage);
////  }
//
//  private static void createDirectory(Path path) throws IOException {
//    if (!Files.exists(path)) {
//      Files.createDirectory(path);
//      System.out.println("Directory created: " + path);
//    } else {
//      System.err.println("Directory already existed: {" + path + "}");
//    }
//  }
//
//  private static void createFile(Path path) throws IOException {
//    if (!Files.exists(path)) {
//      Files.createFile(path);
//      System.out.println("File created: " + path);
//    } else {
//      System.err.println("File already existed: {" + path + "}");
//    }
//  }
//
//  private static void createJavaFile(String filePath, String packageName, String code) throws IOException, CompilerFailException {
//    String codePath = filePath + "/" + CodeExecuteConstant.CODE_CLASS_PREFIX
//            + CodeExecuteConstant.FILE_TYPE;
//    String content = packageName + ";\n" + CodeExecuteConstant.MAIN_CLASS_SAMPLE + code;
//
//    createFile(Paths.get(codePath));
//    writeJavaFile(codePath, content);
//    compilerJavaFile(codePath);
//    deleteJavaFile(Paths.get(codePath));
//  }
//
//  private static void createJavaTestFile(String filePath, String packageName, String code) throws IOException, CompilerFailException {
//    String codePath = filePath + "/" + CodeExecuteConstant.CODE_CLASS_PREFIX
//            + CodeExecuteConstant.TEST_CLASS_SUFFIX + CodeExecuteConstant.FILE_TYPE;
//
//    String content = packageName + ";\n" + code;
//
//    System.out.println("CodePen" + codePath);
//    createFile(Paths.get(codePath));
//    writeJavaFile(codePath, content);
//    compilerJavaTestFile(codePath);
//    deleteJavaFile(Paths.get(codePath));
//  }
//
//  private static void writeJavaFile(String filePath, String code) throws IOException {
//    try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
//      Files.writeString(Paths.get(filePath), code, StandardCharsets.UTF_8,
//              StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
//      writer.write(code);
//    } catch (Exception e) {
//      System.err.println("Failed to write to file: " + e.getMessage());
//    }
//  }
//
//  private static void deleteJavaFile(Path path) throws IOException {
//    if (Files.exists(path)) {
//      Files.delete(path);
//      System.out.println("File deleted: " + path);
//    }
//  }
//
//  private static void compilerJavaFile(String filePath) throws IOException, CompilerFailException {
//    ProcessBuilder pb = new ProcessBuilder("javac", filePath);
//    try {
//      Process process = pb.start();
//      int exitCode = process.waitFor(); // Wait for the progress to complete
//      if (exitCode == 0) {
//        System.out.println("Compilation successful.");
//      } else {
//        System.err.println("Compilation failed. Exit code: " + exitCode);
//        throw new CompilerFailException("Compilation failed. Exit code: " + exitCode);
//      }
//    } catch (IOException | InterruptedException e) {
//      e.printStackTrace();
//      throw new IOException("An error occurred during the compilation process.", e);
//    }
//  }
//
//  private static void compilerJavaTestFile(String filePath) throws IOException, CompilerFailException {
//
//    String classpath = CodeExecuteConstant.TEMP_FILE_PATH + "/classes";
//
//    if (!Files.exists(Paths.get(CodeExecuteConstant.JUNIT_STANDALONE_JAR))) {
//      System.err.println("Files doesn't exist: " + CodeExecuteConstant.JUNIT_STANDALONE_JAR);
//    }
//
//    if (!Files.exists(Paths.get(classpath))) {
//      System.err.println("Directory doesn't exist: " + classpath);
//    }
//
//    classpath += ":" + CodeExecuteConstant.JUNIT_STANDALONE_JAR;
//
//    System.out.println("javac " + "-classpath " + classpath + " " + filePath);
//
//
//
//    ProcessBuilder pb = new ProcessBuilder("javac", "-classpath", classpath, filePath);
//
//    try {
//      Process process = pb.start();
//      int exitCode = process.waitFor();
//      if (exitCode == 0) {
//        System.out.println("Compilation successful.");
//      } else {
//        System.err.println("Compilation failed. Exit code: " + exitCode);
//        throw new CompilerFailException("Compilation failed. Java Test File. Exit code: " + exitCode);
//      }
//    } catch (IOException | InterruptedException e) {
//      e.printStackTrace();
//    }
//  }
//
//  private static RunConsoleDTOTEst runTestJar(String classPath, String testClassPath, String targetPackage) throws IOException {
//    final String LOG_FILE_NAME = "log.txt";
//    final String logFilePath = classPath + "/" + LOG_FILE_NAME;
//
//    if (!Files.exists(Paths.get(classPath)) || !Files.exists(Paths.get(testClassPath))) {
//      System.err.println("Directory is missing");
//      return null;
//    }
//
//    String fullPath = classPath + ":" + testClassPath;
//
//    System.err.println("Command line HERE ==========");
//    System.out.println("java -jar " + CodeExecuteConstant.JUNIT_STANDALONE_JAR
//            + "--select-package" + targetPackage +
//            "--cp" + fullPath
//    );
//
//    ProcessBuilder pb = new ProcessBuilder("java",
//            "-jar", CodeExecuteConstant.JUNIT_STANDALONE_JAR,
//            "--select-package", targetPackage,
//            "--cp", fullPath);
//    pb.redirectOutput(new File(logFilePath));
//
//    int exitCode = Integer.MIN_VALUE;
//    try {
//      Process process = pb.start();
//      exitCode = process.waitFor();
//      System.out.println(
//              exitCode == 0
//                      ? "Run tests successful."
//                      : "Run tests failed. Exit code: " + exitCode
//      );
//    } catch (InterruptedException e) {
//      throw new RuntimeException(e);
//    }
//
//    StringBuilder output = new StringBuilder();
//    BufferedReader reader;
//
//    try {
//      reader = new BufferedReader(new FileReader(logFilePath));
//      String line = reader.readLine();
//
//      line = reader.readLine();
//      line = reader.readLine();
//      line = reader.readLine();
//
//      while (line != null) {
//        output.append(line).append("\n");
//        line = reader.readLine();
//      }
//      reader.close();
//    } catch (IOException e) {
//      e.printStackTrace();
//    }
//
//    return RunConsoleDTOTEst.builder()
//            .result(exitCode == 0 ? Result.PASS : Result.FAIL)
//            .message(String.valueOf(output)).build();
//  }
//
//  private static void moveClassFiles(String filePath, String targetDirectory) throws IOException {
//    List<Path> classes = Files.list(Paths.get(filePath))
//            .filter(file -> file.toString().endsWith(".class")).collect(Collectors.toList());
//
//    classes.forEach(System.err::println);
//
//    for (Path classFile : classes) {
//      moveClassFile(classFile.toString(), targetDirectory);
//    }
//  }
//
////  private static TestConsoleDTO runTestJarForAdmin(String classPath, String testClassPath, String targetPackage) throws IOException {
////    final String LOG_FILE_NAME = "log.txt";
////    final String logFilePath = classPath + "/" + LOG_FILE_NAME;
////
////    if (!Files.exists(Paths.get(classPath)) || !Files.exists(Paths.get(testClassPath))) {
////      System.err.println("Directory is missing");
////      return TestConsoleDTO.builder().consoleResult(Result.FAIL).build();
////    }
////
////    String fullPath = classPath + ":" + testClassPath;
////    ProcessBuilder pb = new ProcessBuilder("java",
////            "-jar", CodeExecuteConstant.JUNIT_STANDALONE_JAR,
////            "--select-package", targetPackage,
////            "--cp", fullPath);
////    pb.redirectOutput(new File(logFilePath));
////
////    try {
////      Process process = pb.start();
////      int exitCode = process.waitFor();
////      System.out.println(
////              exitCode == 0
////                      ? "Run tests successful."
////                      : "Run tests failed. Exit code: " + exitCode
////      );
////
////      return TestConsoleDTO.builder()
////              .consoleResult(exitCode == 0 ? Result.PASS : Result.FAIL)
////              .consoleMessage(null).build();
////
////    } catch (InterruptedException e) {
////      throw new RuntimeException(e);
////    }
////  }
//
//  private static void moveClassFile(String filePath, String targetDirectory) throws IOException {
//    System.out.println(filePath + ": " + targetDirectory);
//    ProcessBuilder pb = new ProcessBuilder("mv", filePath, targetDirectory);
//    pb.redirectErrorStream(true); // Combine stdout and stderr
//
//    try {
//      Process process = pb.start();
//      int exitCode = process.waitFor(); // Wait for the progress to complete
//      if (exitCode == 0) {
//        System.out.println("Moved class file to " + targetDirectory);
//      } else {
//        System.err.println("Failed to move class file. Exit code: " + exitCode);
//      }
//    } catch (IOException | InterruptedException e) {
//      e.printStackTrace();
//    }
//  }
//
//  public TestConsoleDTO executeCode(Long userId, Long problemIdx, @NotBlank String code, @NotBlank String testCode
//  ) throws IOException, CompilerFailException {
//    String[] directories = {
//            CodeExecuteConstant.TEMP_FILE_PATH,
//            CodeExecuteConstant.TEMP_FILE_PATH + "/classes",
//            CodeExecuteConstant.TEMP_FILE_PATH + "/classes/user_" + userId,
//            CodeExecuteConstant.TEMP_FILE_PATH + "/classes/user_" + userId + "/problem_" + problemIdx,
//            CodeExecuteConstant.TEMP_FILE_PATH + "/test-classes/",
//            CodeExecuteConstant.TEMP_FILE_PATH + "/test-classes/user_" + userId,
//            CodeExecuteConstant.TEMP_FILE_PATH + "/test-classes/user_" + userId + "/problem_" + problemIdx,
//    };
//
//    for (String directory : directories) {
//      createDirectory(Paths.get(directory));
//    }
//
//    String userSolutionPath = CodeExecuteConstant.TEMP_FILE_PATH + "/classes/user_" + userId + "/problem_" + problemIdx;
//    String sysTestCasePath = CodeExecuteConstant.TEMP_FILE_PATH + "/test-classes/user_" + userId + "/problem_" + problemIdx;
//    String packageName = CodeExecuteConstant.PACKAGE_NAME_HEAD + userId + CodeExecuteConstant.PACKAGE_NAME_TAIL + problemIdx;
//
//
//    createJavaFile(userSolutionPath, packageName, code);
//    createJavaTestFile(sysTestCasePath, packageName, testCode);
//
//    String classPath = CodeExecuteConstant.TEMP_FILE_PATH + "/classes";
//    String testClassPath = CodeExecuteConstant.TEMP_FILE_PATH + "/test-classes";
//    String targetPackage = "user_" + userId + ".problem_" + problemIdx;
//
//
//    RunConsoleDTOTEst runConsoleDTO = runTestJar(classPath, testClassPath, targetPackage);
//
//    assert runConsoleDTO != null;
//    System.out.println(runConsoleDTO.toString());
//
//    return TestConsoleDTO.builder()
//            .userId(userId)
//            .problemId(problemIdx)
//            .solution(code)
//            .testScript(testCode)
//            .consoleResult(runConsoleDTO.getResult())
//            .consoleMessage(runConsoleDTO.getMessage())
//            .build();
//  }
//}
