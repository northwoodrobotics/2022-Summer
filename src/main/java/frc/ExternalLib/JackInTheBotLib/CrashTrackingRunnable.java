package frc.ExternalLib.JackInTheBotLib;

public abstract class CrashTrackingRunnable implements Runnable {
	@Override
	public final void run() {
		try {
			runCrashTracked();
		} catch (Throwable t) {
			Logger.getAnonymousLogger().error(t);
			throw t;
		}
	}

	public abstract void runCrashTracked();
}
