package lamvc.widget;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

/**
 * Created by IntelliJ IDEA.
 * User: NortonL
 * Date: 4/29/12
 * Time: 12:35 PM
 * <p/>
 * Copyright (c) 2012 Norton Lam. All Rights Reserved
 */
public class TextView extends android.widget.TextView {
  public TextView(Context context) {
    super(context);
  }

  public TextView(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  public TextView(Context context, AttributeSet attrs, int defStyle) {
    super(context, attrs, defStyle);
  }

  public void init() {
    Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/Roboto-Regular.ttf");
    setTypeface(tf);
  }
}
