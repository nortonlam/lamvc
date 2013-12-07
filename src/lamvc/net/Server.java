package lamvc.net;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.*;
import java.net.*;

/**
 * Created by IntelliJ IDEA.
 * User: Norton
 * Date: 4/21/11
 * Time: 12:13 AM
 */
public class Server {
    protected String _server;
    private String _urlStr;
    private IResponseFactory _responseFactory;

    private static HttpClient _httpclient;

    private boolean _isOffline = false;

    public Server(Context context, String url, IResponseFactory responseFactory) throws MalformedURLException {
      SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(context);
      _server = settings.getString("server", "");
      _urlStr = _server + url;
      if (!_urlStr.startsWith("http://")) {
        _urlStr = "http://" + _urlStr;
      }
      Log.i("Server", "url: " + url);

      Log.i("Server", "full url: " + _urlStr);
      _responseFactory = responseFactory;

      if (null == _httpclient) {
        _httpclient = new DefaultHttpClient();
      }
    }

    public void setIsOffline(boolean flag) {
      _isOffline = flag;
    }

    public Response sendGetRequest(Request request) throws IOException {
      if (_isOffline) {
        try {
          return _responseFactory.getInstance(request.getRequestType(), request.getOfflineResponseStr());
        }
        catch (InstantiationException e) {
          throw new IOException(e.toString());
        }
      }

      HttpGet httpGet = new HttpGet(_urlStr);

      HttpResponse response = _httpclient.execute(httpGet);

      int serverResponseCode = response.getStatusLine().getStatusCode();
      Log.i("Server", "Status code: " + serverResponseCode);
      String serverResponseMessage = response.getStatusLine().getReasonPhrase();
      Log.i("Server", "Server message: " + serverResponseMessage);

        if (200 == serverResponseCode) {
          LineNumberReader lnr = new LineNumberReader(new InputStreamReader(response.getEntity().getContent()));
          String line = "";
            String responseTxt = "";
            while (null != (line = lnr.readLine())) {
                responseTxt += line.trim() + "\n";
            }

            Log.i("Server", "Response received:\n" + responseTxt);

            try {
                return _responseFactory.getInstance(request.getRequestType(), responseTxt);
            }
            catch (InstantiationException e) {
                throw new IOException(e.toString());
            }
        }
        else {
            throw new IOException("Error communicating with server: " + serverResponseCode + " : " + serverResponseMessage);
        }
    }

  public Response sendPostRequest(Request request) throws IOException {
    if (_isOffline) {
      try {
        return _responseFactory.getInstance(request.getRequestType(), request.getOfflineResponseStr());
      }
      catch (InstantiationException e) {
        throw new IOException(e.toString());
      }
    }

    HttpPost httppost = new HttpPost(_urlStr);

    String requestData = request.build();
    StringEntity postData = new StringEntity(requestData);;

    httppost.setEntity(postData);
    HttpResponse response = _httpclient.execute(httppost);
    Log.i("Server", "Request sent.");

    // Responses from the server (code and message)
    int serverResponseCode = response.getStatusLine().getStatusCode();
    Log.i("Server", "Status code: " + serverResponseCode);
    String serverResponseMessage = response.getStatusLine().getReasonPhrase();
    Log.i("Server", "Server message: " + serverResponseMessage);

    if (200 == serverResponseCode) {
      LineNumberReader lnr = new LineNumberReader(new InputStreamReader(response.getEntity().getContent()));
      String line = "";
      String responseTxt = "";
      while (null != (line = lnr.readLine())) {
        responseTxt += line + "\n";
      }

      Log.i("Server", "Response received:\n" + responseTxt);

      try {
        return _responseFactory.getInstance(request.getRequestType(), responseTxt);
      }
      catch (InstantiationException e) {
        throw new IOException(e.toString());
      }
    }
    else {
      throw new IOException("Error communicating with server: " + serverResponseCode + " : " + serverResponseMessage);
    }
  }

  public Response sendMultiPartPostRequest(Request request) throws IOException {
    if (_isOffline) {
      try {
        return _responseFactory.getInstance(request.getRequestType(), request.getOfflineResponseStr());
      }
      catch (InstantiationException e) {
        throw new IOException(e.toString());
      }
    }

    HttpPost httppost = new HttpPost(_urlStr);

    MultipartEntity multipartEntity = new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE);
    multipartEntity.addPart("subjectiveFile", new FileBody(new File(request.build())));
    Log.i("Server", "Content-type: " + multipartEntity.getContentType());

    httppost.setEntity(multipartEntity);
    HttpResponse response = _httpclient.execute(httppost);

    // Responses from the server (code and message)
    int serverResponseCode = response.getStatusLine().getStatusCode();
    Log.i("Server", "Status code: " + serverResponseCode);
    String serverResponseMessage = response.getStatusLine().getReasonPhrase();
    Log.i("Server", "Server message: " + serverResponseMessage);

      LineNumberReader lnr = new LineNumberReader(new InputStreamReader(response.getEntity().getContent()));
      String line = "";
      String responseStr = "";
      while (null != (line = lnr.readLine())) {
        responseStr += line + "\n";
      }

      Log.i("Server", "Response received:\n" + responseStr);

    if (200 == serverResponseCode) {
      try {
        return _responseFactory.getInstance(request.getRequestType(), responseStr);
      }
      catch (InstantiationException e) {
        throw new IOException(e.toString());
      }
    }
    else {
      throw new IOException("Error communicating with server: " + serverResponseCode + " : " + serverResponseMessage);
    }
  }
}