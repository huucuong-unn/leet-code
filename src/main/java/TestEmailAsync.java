import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

public class TestEmailAsync {

    @Async("cuongCustomAsyncExecutor")
    public void sendEmail(String message) {
        try {
            System.out.println("📨 Sending email on thread: " + Thread.currentThread().getName());
            Thread.sleep(2000);
            System.out.println("✅ Email sent with message: " + message +
                    " | Thread: " + Thread.currentThread().getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
