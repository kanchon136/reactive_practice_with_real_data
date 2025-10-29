package spring_reactive.com.utility;

public class Practice {

    public static void main(String[] arg) throws InterruptedException {

        System.out.println(Runtime.getRuntime().availableProcessors());

// SharedFkag sharedFkag = new SharedFkag();
// Thread readerThread = new Thread(sharedFkag::reader,"Reader");
// Thread writerThread = new Thread(()->{
//     try {
//         Thread.sleep(100);
//
//     } catch (Exception e) {
//         throw new RuntimeException(e);
//     }
//
//     sharedFkag.writer();
// });
//
// readerThread.start();
// writerThread.start();
//
// readerThread.join();
// writerThread.join();


    }

}


class SharedFkag {
  //  volatile boolean flag = false;
     boolean flag = false;

   public void  writer(){
       System.out.println(Thread.currentThread().getName()+ " thread writer mehtod e dukeche=== tokhon flag er man ==> "+flag);
       flag = true;

       System.out.println(Thread.currentThread().getName()+" thread flag er man changed kore feleche===> "+flag);

   }

   public void reader(){
       System.out.println(Thread.currentThread().getName()+" thread reader method e dukeche==> tokhon flag er man===> "+flag);
       while (!flag) System.out.println(Thread.currentThread().getName()+" thread e Falg er man False ache bolei while loop gurteche.."+flag);
       System.out.println(Thread.currentThread().getName()+" thread e while loop off hoye geche karon writer thread Flag er man changed kore feleche=== "+flag);
   }


}




//class SharedFlag{
//volatile boolean flag = false;
//
//
//public void writer(){
//    System.out.println(Thread.currentThread().getName()+ " Thread flag changed korar jonno writer method e dukeche"+flag);
//     flag= true;
//    System.out.println(Thread.currentThread().getName()+" Thread Flag  changed kore feleche===> "+flag);
//}
//
//public void reader(){
//
//    System.out.println(Thread.currentThread().getName()+" Thread  reader method e dukeche 500 milisecond wait korar por flag==>  "+flag);
//    while (!flag){
//        System.out.println(Thread.currentThread().getName()+" Thread e flag false ache bole loop gurteche==> "+flag);
//    }
//    System.out.println(Thread.currentThread().getName() + "writer theread flag er man change korar por reader thread detect koreche flage ke tai while loop off hoye geche "+flag);
//}
//
//}