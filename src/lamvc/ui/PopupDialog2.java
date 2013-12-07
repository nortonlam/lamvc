package lamvc.ui;

import android.app.Activity;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created with IntelliJ IDEA.
 * User: norton
 * Date: 3/8/13
 * Time: 2:41 PM
 */
public abstract class PopupDialog2 extends DialogFragment {
  private Activity _context;
  private int _layoutId;
  private String _title;

  public PopupDialog2(Activity context, int layoutId) {
    this(context, layoutId, null);
  }

  public PopupDialog2(Activity context, int layoutId, String title) {
    _context = context;
    _layoutId = layoutId;
    _title = title;
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    View view = inflater.inflate(_layoutId, container);
    if (null != _title) {
      getDialog().setTitle(_title);
    }

    return view;
  }
}
