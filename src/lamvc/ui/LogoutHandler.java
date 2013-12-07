package lamvc.ui;

import android.content.Context;
import android.content.DialogInterface;

import lamvc.ui.PopupDialog;
import lamvc.controller.ActivityController;

/**
 * Created by IntelliJ IDEA.
 * User: Norton
 * Date: 5/11/11
 * Time: 8:46 PM
 */

public class LogoutHandler implements DialogInterface.OnClickListener {
  private Context _context;
  private ActivityController _nextAction;

  public LogoutHandler(Context context, ActivityController nextAction) {
    _context = context;
    _nextAction = nextAction;
  }

  public void onClick(DialogInterface dialog, int which) {
    if (PopupDialog.POSITIVE == which) {
      ActivityController.start(_context, _nextAction);
    }
  }

  public static void showPrompt(Context context, ActivityController nextAction) {
    PopupDialog.showPrompt(context, "Are you sure you want to logout?",
            "OK", new LogoutHandler(context, nextAction), "Cancel", new LogoutHandler(context, nextAction));
  }
}