package dev.hugosiu.meetCode.service.CodeExecution;

public class FileEditor {
  private String path;
  private String message;

  public FileEditor() {
  }

  public FileEditor(String path, String message) {
    this.path = path;
    this.message = message;
  }

  public String getPath() {
    return path;
  }

  public void setPath(String path) {
    this.path = path;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  @Override
  public String toString() {
    return "FileEditor[" +
            "path='" + path + '\'' +
            ", message='\n" + "]\n"
            + message;
  }
}
