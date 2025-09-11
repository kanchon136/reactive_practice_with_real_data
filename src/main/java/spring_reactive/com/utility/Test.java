package spring_reactive.com.utility;

import org.reactivestreams.Subscription;
import org.springframework.boot.autoconfigure.ssl.SslProperties;
import org.springframework.http.converter.json.GsonBuilderUtils;
import reactor.core.publisher.*;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;
import reactor.util.retry.Retry;
import spring_reactive.com.exception.ResourceNotFoundException;

import javax.xml.transform.sax.SAXSource;
import java.time.Duration;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

public class Test {

    public static void main(String [] arg) throws InterruptedException {
//        ConnectableFlux<String> connectableFlux = Flux.interval(Duration.ofMillis(100))
//                .map(i-> "Data-"+i)
//                .doOnNext(System.out::println).
//                publish();
//
//        connectableFlux.connect(); // emit is starting
//
//      Thread.sleep(2000);
//      connectableFlux.subscribe(pub1-> System.out.println("sub1="+pub1));
//
//      Thread.sleep(2200);
//
//      connectableFlux.subscribe(pub2-> System.out.println("sub2="+pub2));
//
//      Thread.sleep(3000);
//
//        // Cold Publisher
//       Flux<String> stringFlux= Flux.just("a","b","c","d");
//
//       stringFlux.subscribe(sub1-> System.out.println("sub1===="+sub1));
//       Thread.sleep(2000);
//
//       stringFlux.subscribe(sub2-> System.out.println("sub2===="+sub2));
//
//
//       // hot Publisher
//
//
//      ConnectableFlux<String> connectableFlux = Flux.interval(Duration.ofMillis(200))
//              .map(data-> "data=="+data).publish(); // ready the hot publisher
//
//        connectableFlux.connect();
//
//        Thread.sleep(2000);
//
//        connectableFlux.subscribe(sub1-> System.out.println("sub1====="+sub1));
//
//        Thread.sleep(3000);
//
//        connectableFlux.subscribe(sub2-> System.out.println("sub2===="+sub2));
//
//        Thread.sleep(3500);

//   String currentUser = "adminn";
//
//  Flux<String> sensitiveData = Flux.just("data-1","data-2","data-3")
//          .doOnSubscribe(subscription -> {
//              System.out.println("Subscription started by user "+currentUser);
//
//              if (!isAuthorized(currentUser)){
//                  System.out.println("Subscription user is not authorized"+currentUser);
//                  subscription.cancel();
//
//              }else {
//                  System.out.println("Subscription is authorized so that process the data");
//              }
//
//          }).doOnNext(data-> System.out.println("processing data: "+data))
//          .doOnComplete(()-> System.out.println("Complete the data"))
//          .doOnCancel(()-> System.out.println("Cancel the data"));
//
//
//    sensitiveData.subscribe();



        // transformation operator
        // group by operator

//
//         Flux.just("abc" ,"bcd", "adk", "bhk")
//                .groupBy(value-> value.charAt(0))
//                .flatMap(goup-> goup.map(String::toUpperCase)
//                        .collectList().map(d-> "Group-"+goup.key()+": "+d) )
//                .subscribe(System.out::println);


//        Flux<String> flux1 = Flux.just("apple", "apricot", "banana", "blueberry");
//        Flux<String> flux2 = Flux.just("avocado", "blackberry", "bluebell");
//
//        Flux<String> merged = Flux
//                .merge(flux1, flux2) // দুইটা flux merge করলাম
//                .groupBy(word -> word.charAt(0)) // প্রথম অক্ষর দিয়ে group করলাম
//                .flatMap(groupFlux ->
//                        groupFlux
//                                .collectList() // একই key এর value গুলো একত্র করলাম
//                                .map(list -> "Group " + groupFlux.key() + ": " + String.join(", ", list))
//                );
//
//
//        merged.subscribe(System.out::println);


//        Flux<String> flux1 = Flux.just("apple", "banana", "avocado", "blueberry");
//        Flux<String> flux2 = Flux.just("apricot", "blackberry", "almond", "banana");
//
//        Mono<Map<Character, List<String>>> merged1 = flux1
//                .groupBy(s -> s.charAt(0))  // Flux<GroupedFlux<Character,String>>
//                .flatMap(group ->
//                        group.collectList() // Mono<List<String>
//                                .map(list -> Map.entry(group.key(), list))// tahlole abar keno map korte hocche
//                )
//                .collectMap(Map.Entry::getKey, Map.Entry::getValue);
//
//
//        merged1.subscribe(map1-> {
//    map1.forEach((key,value)-> System.out.println("key="+key+"   value="+value));

//
//        Flux<String> stringFlux = Flux.just("abc","bde","akl", "bik");
//        Flux<GroupedFlux<Character,String>> groupedFluxFlux = stringFlux.groupBy(c-> c.charAt(0));

//        Mono<Map<Character,List<String>>> mapMono=
//                stringFlux.groupBy(c-> c.charAt(0))
//                        .flatMap(g-> g.collectList()
//                                .map(list-> Map.entry(g.key(),list)))
//                        .collectMap(Map.Entry::getKey, Map.Entry::getValue);
//
//        mapMono.subscribe(map->{
//            map.forEach((key,value)-> System.out.println(key+"====="+value));
//
//        });=

//        Mono<Map<Character, List<String>>> mapMono  = stringFlux.groupBy(c-> c.charAt(1))
//                .flatMap(g-> g.collectList()
//                        .map(list-> Map.entry(g.key(),list))
//                ).collectMap(Map.Entry::getKey,Map.Entry::getValue);
//
//        mapMono.subscribe(map->{
//            map.forEach((key,value)-> System.out.println(key+"  "+value));
//        });

//        groupedFluxFlux.flatMap(group->
//                group.map(list-> group.key()+"======"+list)).subscribe(System.out::println);

//        stringFlux.groupBy(c-> c.charAt(0))
//                .flatMap(Flux::collectList)
//                .subscribe(System.out::println);


//        groupedFluxFlux.subscribe(group-> {
//            System.out.println("Group key="+group.key());
//            group.subscribe(valu-> System.out.println("value="+valu));
//
//        });


//        System.out.println("===============zip and zipWith==========================");
//
//        Flux<String> customerName = Flux.just("customer-1","customer-2","customer-3");
//        Flux<String> productName = Flux.just("product-1","product-2","product-3");
//         Flux<LocalDate>  orderDate = Flux.just(LocalDate.of(2025,6,1)
//                 ,LocalDate.of(2025,6,2),LocalDate.of(2025,6,3));
//
//
//         Flux<String> orderSummery = Flux.zip(customerName,productName,orderDate)
//                 .map(tuple3-> {
//                     String name = tuple3.getT1();
//                     String product = tuple3.getT2();
//                     LocalDate date = tuple3.getT3();
//
//                     return  name+ " ordered  "+ product + " on  "+ date;
//
//                 });
//
//        orderSummery.subscribe(System.out::println);


//        Flux<?> genericFlux = Flux.just(1,2,3,4,5);
//        Flux<Integer> integerFlux = genericFlux.cast(Integer.class)
//                .doOnSubscribe(p-> System.out.println("subscribe the first time"))
//                .cache();
//
//        integerFlux.subscribe(i-> System.out.println("first subscriber==>"+i));
//
//        System.out.println("-----------------------------------------------------------");
//        integerFlux.subscribe(i-> System.out.println("second subscriber ==========>"+i));
//
//
//        Flux<Integer> integerCacheInvalidateIf= genericFlux.cast(Integer.class)
//                .doOnSubscribe(p-> System.out.println("subscribe the first time"));


//

//
//        Flux.range(1,5)
//                .delayElements(Duration.ofMillis(1000))
//                .timed().doOnNext(signal->{
//                    System.out.println("Value only: " + signal.get());  // আসল value
//                    System.out.println("Elapsed nanos since last: " + signal.elapsed());
//                    System.out.println("Elapsed nanos since subscribe: " + signal.elapsedSinceSubscription());
//                    System.out.println("Timestamp epoch millis: " + signal.timestamp());
//                }).blockLast();
//
//        System.out.println("============takeUntilOther===================================");
//
//      Flux<Long> souce = Flux.interval(Duration.ofMillis(500));
//      Mono<Long> stopper = Mono.delay(Duration.ofSeconds(5));
//
//      souce.takeUntilOther(stopper).subscribe(System.out::println);

        System.out.println("================switchIfEmpty==if the first Flux or Mono full source is Empty then other can execute for value ");
//
//        Flux<Integer> integerFlux = Flux.empty();
//         integerFlux.switchIfEmpty(Flux.just(1,3,4,5)).subscribe(System.out::println);


//        System.out.println("========================SubsCribOn====================================");
////        Flux.range(1,10).map(i->{
////            System.out.println("thread name ==>  "+i+ "   "+Thread.currentThread().getName());
////            return  i;
////        }).subscribeOn(Schedulers.boundedElastic()).subscribe();
//        Flux.range(1,10).map(i->{
//            System.out.println("thread name ==>  "+i+ "   "+Thread.currentThread().getName());
//            return  i;
//        }).subscribeOn(Schedulers.parallel()).subscribe();

//        System.out.println("============================retry when occur any error then subscriber the start first again==========================");
//
//
//        Flux<Integer> retryFlux = Flux.range(1,5).map(m->{
//
//            if (m==2) throw new RuntimeException("Jur kore ami error Ghotalam.....!!!");
//            return  m;
//        }).retry(1);
//
//        retryFlux.subscribe(v-> System.out.println("value==>"+v),
//                e-> System.err.println("Error==>"+e.getMessage()),
//                ()-> System.out.println("Complete==>") );

//        System.out.println("==========RetryWhen ===================================");
//        Flux<Integer> retryWhenFlux = Flux.range(1,20).map(i-> {
//            if (i==10)throw new ResourceNotFoundException("It is Custom Exception==>");
//           return i;
//        }).retryWhen(Retry.fixedDelay(3,Duration.ofSeconds(3)));
//
//        retryWhenFlux.subscribe(v-> System.out.println("valie"+v) ,
//                e-> System.out.println("error=>"+e),
//                ()-> System.out.println("Completed") );


//        System.out.println("====================share ===========normally it is cold publisher ke Hot publisher rupantor kore");
//
//
//        Flux<Long> coldPublisher = Flux.interval(Duration.ofMillis(500)).take(20)
//                .doOnSubscribe(s-> System.out.println("Subscribes the Flux============="))
//                .share();
//
//        coldPublisher.subscribe(s1-> System.out.println("Sub1==>"+s1));
//        Thread.sleep(2000);
//        coldPublisher.subscribe(s2-> System.out.println("Sub2===>"+s2));
//
//        Flux<Long> testHot = Flux.interval(Duration.ofMillis(500)).take(50).publish().autoConnect(); //create the hot publisher
//
//        testHot.doOnSubscribe(test-> System.out.println("subscriber One The Join ===>"))
//                .subscribe(s-> System.out.println("SubscriberOne==>"+ s));
//        Thread.sleep(2000);
//
//        testHot.doOnSubscribe(t-> System.out.println("Subscriber two is the join"))
//                .subscribe(s-> System.out.println("SubscriberTwo==>"+s));


//        System.out.println("========================single ===exact one element is mandatory ");
//
//        Mono<Integer> monoSingle = Mono.just(10);
//
//        monoSingle.single().subscribe(System.out::println, err-> System.out.println("Error=="+err));
//
//        Mono<Integer> monoSigleEpty = Mono.empty();
//
//        monoSigleEpty.single().subscribe(System.out::println,err-> System.out.println("error when empty"+err));
//
//        Flux<Integer> fluxSingle = Flux.just(1,2,3,5,5);
//        fluxSingle.single().subscribe(System.out::println,
//                err-> System.out.println("Error when try to Flux to single ==>"+err.getMessage()));
//
//
//        System.out.println(" ===============SingleOptional  =====Zero Or One Element Optional");
//
//        Mono<Integer> monoSingleOptional = Mono.empty();
//
//        monoSingleOptional.singleOptional().subscribe(System.out::println,
//                error-> System.out.println("Optional Error ==>"+error.getMessage()));


//        System.out.println("===========repeat =====Retry Same task X time after completion[simple repeat]");
//
//        Flux<Integer> fluxRepeat = Flux.range(1,5).doOnSubscribe(s->
//                        System.out.println("User subscriber is started"))
//                        .repeat(2);
//          fluxRepeat.subscribe(System.out::println);


//        System.out.println("===========repeatWhen==== Retry task the custom logic ,delay , schedulers, condition");
//
//        Flux<Integer> integerFluxRepeatWhen = Flux.range(10,20)
//
//                .doOnSubscribe(s-> System.out.println("Subscribe the started"))
//
//                        .repeatWhen(completd-> completd.delayElements(Duration.ofMillis(1000))).take(2);
//
//        integerFluxRepeatWhen.subscribe(System.out::println);


//        System.out.println("=================Publish =========ConnectableFlux create kore that mean cold publisher theke hot publisher e rupantor kore");
//        Flux<Integer> connectableFlux = Flux.range(1,10).publish().autoConnect(2);// autoconnet 2 that mean duijon subscribe korar por upstream run korbe
//
//        connectableFlux.subscribe(s-> System.out.println("First Subscriber ===>"+s));
//        connectableFlux.subscribe(second-> System.out.println("Second Subscriber====>"+second));


//        System.out.println("===========PublishOn(Scheduler)  upstream main thread e chollew downStream onno thread a cholbe ");
//        List<Integer> fluxLIist = List.of(1,2,3,4,5,7,8,9,10);
//        Flux<Integer> fluxForPublishOn = Flux.fromIterable(fluxLIist).map(main->{
//            System.out.println("upstream thread name ===="+Thread.currentThread().getName());
//            return main;
//        }).publishOn(Schedulers.boundedElastic()).map(dwon->{
//            System.out.println("donwStream thread name =="+ Thread.currentThread().getName());
//            return  dwon;
//        });
//e
//        fluxForPublishOn.subscribe();

//        System.out.println("=====SubscribeOn(Scheduler) puro" +
//                " chain kuthai subscribe hobe sheta controll kore [that mean all stream and down team new thread e run hobe]");
//
//          Flux.range(1,20).map(main-> {
//
//                      System.out.println("upstream==>" + Thread.currentThread().getName());
//                      return main;
//                  })
//                          .subscribeOn(Schedulers.boundedElastic())
//                                  .map(down-> {
//                                      System.out.println("downStream==>" + Thread.currentThread().getName());
//                                      return down;
//
//                                  })
//                  .subscribe();

        System.out.println("====publish + publishOn Or share that mena conld ke hot kore scheduler e run kora");

   Flux<Long> integerConnectableFlux =    Flux.interval(Duration.ofMillis(100))
           .doOnSubscribe(s-> System.out.println("subscription Start "))
                        .map(up->{
                          //  System.out.println("upstream==> "+Thread.currentThread().getName());
                            return  up;

                        }).publishOn(Schedulers.boundedElastic()).map(down-> {
                          //  System.out.println("downstream======> "+Thread.currentThread().getName());
                            return down;

                      }).share();

   integerConnectableFlux.subscribe(s-> System.out.println("sub1===> "+s));
   Thread.sleep(2000);
   integerConnectableFlux.subscribe(s-> System.out.println("Sub2===> "+s));




        Thread.sleep(20000);

    }

    public static boolean isAuthorized(String user){
        return "admin".equalsIgnoreCase(user);
    }
}
