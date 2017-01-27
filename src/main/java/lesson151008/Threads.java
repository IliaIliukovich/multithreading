package lesson151008;

public class Threads {

	public static void main(String[] args) {

		System.out.println(Thread.currentThread());

		new Thread(() -> {
            Thread currentThread = Thread.currentThread();
            System.out.println(currentThread);
            for (StackTraceElement el : currentThread.getStackTrace()) {
                System.out.println(el.getClassName() + " "
                        + el.getMethodName() + " : " + el.getLineNumber());
            }
        }).start();

	}

}
