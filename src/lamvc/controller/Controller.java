package lamvc.controller;

import java.util.HashMap;

import android.app.Application;
import android.content.Context;

/**
 * Created by IntelliJ IDEA.
 * User: Norton
 * Date: 5/7/11
 * Time: 12:12 AM
 */
public abstract class Controller {
  protected Application _app;
  protected String _progressMessage;

  public Controller(Application app) {
    _app = app;
  }

  public String getProgressMessage() {
    return _progressMessage;
  }

  public abstract void execute(Context context, HashMap<String, Object> params) throws Exception;
  
  public void error(Context context)
  {
  }
}