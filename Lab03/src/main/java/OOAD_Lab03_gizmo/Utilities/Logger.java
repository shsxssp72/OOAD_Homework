package OOAD_Lab03_gizmo.Utilities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Logger {


	private static boolean saved = true;
	private static List<String> logList = new ArrayList<>();

	public static void LogWriteLine(String input) {
		logList.add("Log: " + new Date().toString() + " :" + input);
	}

	public static List<String> getLogList() {
		return logList;
	}

	public static void removeAll() {
		logList.removeAll(getLogList());
		setSavedBoard(false);
	}

	public static boolean getSavedBoard() {
		return saved;
	}

	public static void setSavedBoard(boolean save) {
		saved = save;
	}
}
