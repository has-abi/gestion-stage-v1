package com.gestion.stage.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Hassan ABIDA & Aicha ELABDELLAOUI
 * @version 1.0
 */
public class CodeSession {
	private static Map<String, CodeSessionItem> codeSessions = new HashMap<String, CodeSessionItem>();

	public static void addSessionCode(String username, String code, Long duration) {
		CodeSessionItem c = new CodeSessionItem(username, code, duration);
		codeSessions.put(username, c);
	}

	public static void removeSessionCode(String username) {
		codeSessions.remove(username);
	}

	public static CodeSessionItem getCodeSession(String username) {
		return codeSessions.get(username);
	}

}
