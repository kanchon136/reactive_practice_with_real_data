package spring_reactive.com.utility;

 import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class levelTree {

    public static void main(String []arg) throws InterruptedException {

        UnsafeCounter  unsafeCounter = new UnsafeCounter();

        ThreadSaferCounter threadSaferCounter = new ThreadSaferCounter();

        ExecutorService executorService = Executors.newFixedThreadPool(5);

        int ThreadIncrement = 5000;

        Runnable f = ()->{
            for (int i=0; i<ThreadIncrement; i++ ){
                threadSaferCounter.increment();
                unsafeCounter.increment();
            }

        } ;

     for (int i = 0; i< 5; i++){
         executorService.submit(f);
     }
     executorService.shutdown();
     executorService.awaitTermination(1,TimeUnit.MINUTES);

        System.out.println(" total increment unsafe  value ===="+unsafeCounter.getValue());
        System.out.println(" total increment  safe value ===="+threadSaferCounter.getValue());
    }




}

  class ThreadSaferCounter{

    private final AtomicInteger value = new AtomicInteger(0);

    public void increment(){
        value.getAndIncrement();
    }

    public void decrement(){
        value.getAndDecrement();
    }

    public int getValue(){
        return value.get();
    }
 }

 class UnsafeCounter{

     int value = 0;


    public void  increment (){
         value ++;
    }

    public void  decrement(){
        value --;
     }


     public int getValue(){
        return  value;
     }
 }