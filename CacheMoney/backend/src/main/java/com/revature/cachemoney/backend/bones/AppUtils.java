package com.revature.cachemoney.backend.bones;

public class AppUtils {

	public static void Log() {
		System.out.println("");
	}

	public static void Log(String l) {
		System.out.println(l);
	}

	public static void Log(Object o) {
		if (o == null)
			System.out.println(o);
		else
			System.out.println(o.toString());
	}

	public static void Page() {
		for (int i = 0; i < 8; i++) {
			Log();
		}
	}

	public static void Clear() {
		Page();
		Page();
	}

	// prints stack trace with message
	public static void ThrowFancyException(String messOut) {
		ThrowFancyException(messOut, false);
	}

	public static void ThrowFancyException(String messOut, boolean breakIt) {
		for (StackTraceElement ste : Thread.currentThread().getStackTrace()) {
			Log(ste);
		}
		Log(messOut);

		if (breakIt) {
			System.exit(0);
		}
	}
}
