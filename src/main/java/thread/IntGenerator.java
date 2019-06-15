package thread;

public abstract class IntGenerator {
	private volatile boolean canceled = false;
	public void cancel() { this.canceled = true; }
	public boolean isCanceled() { return canceled; }
	public abstract int next();
}
