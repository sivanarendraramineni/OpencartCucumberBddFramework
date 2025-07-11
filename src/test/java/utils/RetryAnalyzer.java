package utils;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {

    private int retryCount = 0;
    private static final int maxRetryCount = 2;


    private static final ThreadLocal<Boolean> isRetrying = ThreadLocal.withInitial(() -> false);

    public static boolean isRetryingNow() {
        return isRetrying.get();
    }

    @Override
    public boolean retry(ITestResult result) {
        if (retryCount < maxRetryCount) {
            retryCount++;
            isRetrying.set(true); // mark this attempt as a retry
            System.out.println(" RETRYING FAILED TEST: " + result.getName() + " | Attempt " + retryCount + " of " + maxRetryCount);
            return true;
        }
        isRetrying.set(false); // final attempt
        return false;
    }
}
