package lamvc.controller;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import lamvc.ui.Params;

/**
 * Created by IntelliJ IDEA.
 * User: Norton
 * Date: 5/24/11
 * Time: 6:28 PM
 */
public class ActivityController {
  private String _action;

  protected ActivityController(String action) {
    _action = action;
  }

  public String getAction() {
    return _action;
  }

  public void start(Context context) {
    start(context, this);
  }

  public static void start(Context context, ActivityController action) {
    Intent activityIntent = new Intent();
    activityIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    activityIntent.setAction(action.getAction());

    context.startActivity(activityIntent);
  }

  /*public void start(Context context, String errorMessage) {
    start(context, _action, errorMessage);
  }*/

  public static void start(Context context, ActivityController action, String errorMessage) {
    Intent activityIntent = new Intent();
    activityIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    activityIntent.setAction(action.getAction());
    activityIntent.putExtra(Params.ERROR_MESSAGE, errorMessage);

    context.startActivity(activityIntent);
  }

  public void start(Context context, Bundle params) {
    start(context, this, params);
  }

  public static void start(Context context, ActivityController action, Bundle params) {
    Intent activityIntent = new Intent();
    activityIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    activityIntent.setAction(action.getAction());
    activityIntent.putExtras(params);

    context.startActivity(activityIntent);
  }

  public void startForResult(Activity context, int requestCode) {
    startForResult(context, this, requestCode);
  }

  public static void startForResult(Activity context, ActivityController action, int requestCode) {
    Intent activityIntent = new Intent();
    activityIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    activityIntent.setAction(action.getAction());

    context.startActivityForResult(activityIntent, requestCode);
  }
}
