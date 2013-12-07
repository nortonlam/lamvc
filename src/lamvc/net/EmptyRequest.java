package lamvc.net;

import java.io.IOException;

import org.xmlpull.v1.XmlSerializer;

/**
 * Created by IntelliJ IDEA.
 * User: NortonL
 * Date: 7/11/11
 * Time: 9:34 AM
 */
public class EmptyRequest extends Request {
  @Override
  public String build() throws IOException {
    return "";
  }

  public String getOfflineResponseStr() {
    return "";
  }

  @Override
  protected void addDataXml(XmlSerializer serializer) throws IOException {
  }
}
