import cz.cizek.edu.trans.config.AppConfig;
import cz.cizek.edu.trans.service.JdbcService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author jiricizek <jiri.cizek@cleverlance.com>
 */
public class Main {

    public static void main(String[] args){

        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        final JdbcService bean = context.getBean(JdbcService.class);

        Thread thread1 = new Thread(new Runnable() {
            public void run() {
                bean.increment();
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            public void run() {
                try {
                    Thread.sleep(2000);
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
                bean.increment();
            }
        });


        thread1.start();
        thread2.start();
    }
}
