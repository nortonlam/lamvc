package lamvc.ui;

import android.content.Context;
import android.content.DialogInterface;
import lamvc.controller.ActivityController;

/**
 * Created by IntelliJ IDEA.
 * User: NortonL
 * Date: 2/28/12
 * Time: 11:58 AM
 * <p/>
 * Copyright (c) 2012 Norton Lam. All Rights Reserved
 */
public class ExitHandler implements DialogInterface.OnClickListener {
  private Context _context;

  public ExitHandler(Context context) {
    _context = context;
  }

  public void onClick(DialogInterface dialog, int which) {
    if (PopupDialog.POSITIVE == which) {
      ActivityController.start(_context, (ActivityController)null);                 // This is not done.
    }
  }

  public static void showPrompt(Context context, String nextAction) {
    PopupDialog.showPrompt(context, "Are you sure you want to exit?",
            "Yes", new ExitHandler(context), "Cancel", new ExitHandler(context));
  }
}
