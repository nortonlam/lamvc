package lamvc.net;

import java.io.IOException;

import org.xmlpull.v1.XmlSerializer;

/**
 * Created by IntelliJ IDEA.
 * User: Norton
 * Date: 4/21/11
 * Time: 12:37 AM
 */
public abstract class Request {
  protected String _requestType;
  protected String _url;

  protected String _response;

  public Request() {
  }

  public String getRequestType() {
    return _requestType;
  }

  public String getUrl() {
    return _url;
  }

  public abstract String build() throws IOException;
  public abstract String getOfflineResponseStr();
  protected abstract void addDataXml(XmlSerializer serializer) throws IOException;
}