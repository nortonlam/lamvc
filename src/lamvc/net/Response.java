package lamvc.net;

import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: Norton
 * Date: 4/30/11
 * Time: 10:01 PM
 */
public abstract class Response {
  protected final static String SUCCESS = "success";
  protected final static String ERROR = "error";

  protected String _status;
  protected String _responseCode;
  protected String _systemMessage;
  protected String _userMessage;

  public Response(String responseXml) {
    try {
      parseResponse(responseXml);
    }
    catch (IOException e) {
      e.printStackTrace();
    }
  }

  public abstract void parseResponse(String responseXml) throws IOException;

  public boolean success() {
    return SUCCESS.equals(_status);
  }

  public String getResponseCode() {
    return _responseCode;
  }

  public String getSystemMessage() {
    return _systemMessage;
  }

  public String getUserMessage() {
    return _userMessage;
  }
}