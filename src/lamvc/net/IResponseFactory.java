package lamvc.net;

/**
 * Created by IntelliJ IDEA.
 * User: Norton
 * Date: 4/30/11
 * Time: 10:03 PM
 */
public interface IResponseFactory {
  public Response getInstance(String requestType, String responseXml) throws InstantiationException;
}
