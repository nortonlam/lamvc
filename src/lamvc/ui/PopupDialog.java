package lamvc.ui;

import java.util.HashMap;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by IntelliJ IDEA.
 * User: Norton
 * Date: 5/11/11
 * Time: 8:18 PM
 */
public abstract class PopupDialog {
  public static int POSITIVE = DialogInterface.BUTTON_POSITIVE;
  public static int NEGATIVE = DialogInterface.BUTTON_NEGATIVE;

  private PopupDialog() {
  }

  public static String getString(Context context, int resourceId) {
    return getString(context, resourceId, null);
  }

  public static String getString(Context context, int resourceId, String tokenName, String tokenValue) {
    HashMap<String, String> tokenMap = new HashMap<String, String>();
    tokenMap.put(tokenName, tokenValue);

    return getString(context, resourceId, tokenMap);
  }

  public static String getString(Context context, int resourceId, HashMap<String, String> tokenMap) {
    String message = context.getString(resourceId);
    if (null != tokenMap) {
      for (String key : tokenMap.keySet()) {
        String tokenName = "$$" + key + "$$";
        message = message.replace(tokenName, tokenMap.get(key));
      }
    }

    return message;
  }

  public static void showAbout(Activity context, int aboutDialogLayoutId) {
    LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
    View layout = inflater.inflate(aboutDialogLayoutId, (ViewGroup) context.findViewById(aboutDialogLayoutId));

    AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
    dialogBuilder.setCancelable(false);
    dialogBuilder.setPositiveButton("OK", null);
    dialogBuilder.setView(layout);

    AlertDialog about = dialogBuilder.create();
    about.show();
  }

    public static void showIntro(Activity context, int introDialogLayoutId) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(introDialogLayoutId, (ViewGroup) context.findViewById(introDialogLayoutId));

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
        dialogBuilder.setCancelable(false);
        dialogBuilder.setPositiveButton("OK", null);
        dialogBuilder.setView(layout);

        AlertDialog intro = dialogBuilder.create();
        intro.show();
    }

  public static void showWarning(Context context, int resourceId) {
    showWarning(context, getString(context, resourceId), null);
  }

  public static void showWarning(Context context, int resourceId, DialogInterface.OnClickListener listener) {
    showWarning(context, getString(context, resourceId), listener);
  }

  public static void showWarning(Context context, String message) {
        showWarning(context, message, null);
    }

    public static void showWarning(Context context, String message, DialogInterface.OnClickListener listener) {
    AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
    dialogBuilder.setCancelable(false);
    dialogBuilder.setPositiveButton("OK", listener);
    dialogBuilder.setMessage(message);

    AlertDialog alert = dialogBuilder.create();
    alert.show();
  }

  public static void showPrompt(Context context, String prompt, String positiveString,
                                DialogInterface.OnClickListener positiveCallback,
                                String negativeString, DialogInterface.OnClickListener negativeCallback) {
    AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
    dialogBuilder.setCancelable(false);
    dialogBuilder.setPositiveButton(positiveString, positiveCallback);
    dialogBuilder.setNegativeButton(negativeString, negativeCallback);
    dialogBuilder.setMessage(prompt);

    AlertDialog alert = dialogBuilder.create();
    alert.show();
  }

  public static AlertDialog showCustomPrompt(Activity context, int dialogLayoutId, String positiveString,
                                DialogInterface.OnClickListener positiveCallback,
                                String negativeString, DialogInterface.OnClickListener negativeCallback) {
    LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
    View layout = inflater.inflate(dialogLayoutId, (ViewGroup) context.findViewById(dialogLayoutId));

    AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
    dialogBuilder.setCancelable(false);
    dialogBuilder.setPositiveButton(positiveString, positiveCallback);
    dialogBuilder.setNegativeButton(negativeString, negativeCallback);
    dialogBuilder.setView(layout);

    AlertDialog alert = dialogBuilder.create();
    alert.show();

    return alert;
  }
}

