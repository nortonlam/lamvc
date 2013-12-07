package lamvc.controller;

import java.util.HashMap;

import android.content.Context;

/**
 * Created by IntelliJ IDEA.
 * User: Norton
 * Date: 5/7/11
 * Time: 12:10 AM
 */
public class Command {
  private static HashMap<Command, Controller> _commandMap;

  private String _name;

  protected Command(String name) {
    _name = name;
  }

  public static void addCommand(Command command, Controller controller) {
    if (null == _commandMap) {
      _commandMap = new HashMap<Command, Controller>();
    }

    _commandMap.put(command, controller);
  }

  public void execute(Context context, HashMap<String, Object> params) {
    execute(this, context, params);
  }

  public void executeLoading(Context context, HashMap<String, Object> params) {
    executeLoading(this, context, params);
  }

  public static void execute(Command command, Context context, HashMap<String, Object> params) {
    Controller controller = _commandMap.get(command);
    try
	{
		controller.execute(context, params);
	}
	catch (Exception e)
	{
		e.printStackTrace();
		
		controller.error(context);
	}
  }

  public static void executeLoading(Command command, Context context, HashMap<String, Object> params) {
    Controller controller = _commandMap.get(command);

    LoadingController loading = new LoadingController(context, controller, params);
    loading.execute();
  }

  public String toString() {
    return _name;
  }
}