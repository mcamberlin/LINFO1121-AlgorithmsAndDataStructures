import java.util.concurrent.*;



public abstract class TimeLimitedCodeBlock {

    public boolean run(long time) {
        final Runnable stuffToDo = new Thread() {
            @Override
            public void run() {
                codeBlock();
            }
        };

        final ExecutorService executor = Executors.newSingleThreadExecutor();
        final Future future = executor.submit(stuffToDo);
        executor.shutdown(); // This does not cancel the already-scheduled task.
        boolean ok = true;
        try {
            future.get(time, TimeUnit.MILLISECONDS);
        } catch (InterruptedException ie) {
            ok = false;
        } catch (ExecutionException ee) {
            ok = false;
        } catch (TimeoutException te) {
            ok = false;
        }
        if (!executor.isTerminated()) {
            future.cancel(true);
            executor.shutdownNow();
            executor.shutdownNow(); // If you want to stop the code that hasn't finished.
        }
        return ok;
    }

    public abstract void codeBlock();
}

