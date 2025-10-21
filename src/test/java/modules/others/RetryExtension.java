package modules.others;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestExecutionExceptionHandler;

public class RetryExtension  implements TestExecutionExceptionHandler {
    private static int maxRetries = 2;

    public static void setMaxRetries(int retries) {
        maxRetries = retries;
    }

    private int attempt = 0;

    @Override
    public void handleTestExecutionException(ExtensionContext context, Throwable throwable) throws Throwable {
        if (attempt++ < maxRetries) {
            System.out.println("Attempt " + attempt + " Operation Failed – Retrying...");
            // Re-executa o método de teste
            context.getTestMethod().ifPresent(testMethod -> {
                try {
                    testMethod.invoke(context.getTestInstance().get());
                } catch (Throwable ex) {
                    ex.printStackTrace();
                }
            });
        } else {
            throw throwable;
        }
    }
}


