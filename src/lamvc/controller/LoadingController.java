package lamvc.controller;

import java.util.HashMap;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

/**
 * Created by IntelliJ IDEA.
 * User: Norton
 * Date: 5/9/11
 * Time: 8:29 PM
 */
public class LoadingController extends AsyncTask<Void, Void, Boolean> {
  private Context _context;
  private ProgressDialog _progressDialog;
  private Controller _controller;
  private HashMap<String, Object> _params;

  public LoadingController(Context context, Controller controller, HashMap<String, Object> params) {
    _context = context;
    _controller = controller;
    _params = params;
  }

  @Override
  public void onPreExecute() {
    _progressDialog = ProgressDialog.show(_context, "", _controller.getProgressMessage());
  }

  @Override
  protected Boolean doInBackground(Void... params) {
    try
	{
		_controller.execute(_context, _params);
	}
	catch (Exception e)
	{
		e.printStackTrace();
		
		return false;
	}

    return true;
  }

  @Override
  protected void onPostExecute(Boolean result)
  {
    _progressDialog.hide();
    
    if (false == result)
    {
    	_controller.error(_context);
    }
  }
}
