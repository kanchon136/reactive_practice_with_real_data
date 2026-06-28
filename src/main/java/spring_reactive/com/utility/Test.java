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
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiFunction;
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

    //    System.out.println("================switchIfEmpty==if the first Flux or Mono full source is Empty then other can execute for value ");
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

//        System.out.println("====publish + publishOn Or share that mena conld ke hot kore scheduler e run kora");
//
//           Flux<Long> integerConnectableFlux =    Flux.interval(Duration.ofMillis(100))
//           .doOnSubscribe(s-> System.out.println("subscription Start "))
//                        .map(up->{
//                          //  System.out.println("upstream==> "+Thread.currentThread().getName());
//                            return  up;
//
//                        }).publishOn(Schedulers.boundedElastic()).map(down-> {
//                          //  System.out.println("downstream======> "+Thread.currentThread().getName());
//                            return down;
//
//                      }).share();
//
//   integerConnectableFlux.subscribe(s-> System.out.println("sub1===> "+s));
//   Thread.sleep(2000);
//   integerConnectableFlux.subscribe(s-> System.out.println("Sub2===> "+s));

//        System.out.println("========onErrorComplete=====error holew stream stop hobena,, but subscriber er kache error jabena mone hobe jeno kuno error E hou nai...");
//
//        Flux.just("A","B","C","D").map( v->{
//          if (v.equalsIgnoreCase("C")) throw new RuntimeException("Error");
//           return  v;
//        }).onErrorComplete().subscribe(value-> System.out.println("value==>"+value)
//                , error-> System.out.println("error==>"+error),
//                ()-> System.out.println("completed stream"));


//        System.out.println("=========onErrorContinue ==== error hole oi value ta skip korbe and porer stram cholbe and ki error hoiche and kun value ta subscriber jante parbe");
//        Flux.just("10", "20","xx","30").map(Integer::parseInt)
//                        .onErrorContinue((err, value)-> System.out.println("error and value===> "+err +" =========  "+ value))
//                                .subscribe(s-> System.out.println("value =="+s));


//        System.out.println("=========onErrorStop ===== error hole sathe sathe stream stop hoye jabe and error propegatekorbe ,, other error handaling call kora thaklew kaj korbena");
//
//        Flux.just(1,3,5,6,0).map( i -> 1000/i)
//                        .onErrorStop().subscribe(va->
//                        System.out.println("val==>"+va) ,
//                        err-> System.out.println("error==>"+err));
//


//        System.out.println("======onErrorMap====error ke custom exception ba onno exception e convert kora");
//
//        Flux.just("1","2","3","z","5","6").map(Integer::parseInt).
//                onErrorMap(ex-> new Throwable("Parsing Error===>"+ex))
//                .subscribe(System.out::println);

//        System.out.println("=========onErrorResume =====error hole fallback publisher return kora jabe , new publisher return korte parbo");
//
//        Flux.just("1","2","x","4","5")
//                        .map(Integer::parseInt).onErrorResume(e-> Flux.just(8,9)).subscribe(System.out::println);
//
//
//        System.out.println("======onErrorReturn ===Error hole ekta fixed value return kore Flux complete kore dey ");
//        Flux.range(1,20).map(m->{
//            if (m== 3) throw new RuntimeException("Not suppoort ");
//            return  m;
//        }).onErrorReturn(-99).subscribe(v-> System.out.println("value==>"+v),
//                err-> System.out.println("error==>"+err),()-> System.out.println("Completed==>"));


//        System.out.println("======merge=== multiple publisher ke asyncronously meger korte chaile , je data age emmit korbe , shei data age show hobe[ Deferent  type supported] ");
//        Flux<Integer> flux1 = Flux.just(1,2,4,5).delayElements(Duration.ofMillis(100));
//        Flux<Integer> flux2 = Flux.just(6,7,8,9,10).delayElements(Duration.ofMillis(100));
//        Flux<String> flux3 = Flux.just("11","12","13","14","15").delayElements(Duration.ofMillis(100));
//
//        Flux.merge(flux1,flux2,flux3).subscribe(System.out::println);

//        System.out.println("====mergeWith holo , amra jodi duita publisher ke merge korte chai , r sob logic merge er moto but same data type hote hobe");
//
//        Flux<Integer> flux4 = Flux.just(6,7,8,9,10).delayElements(Duration.ofMillis(100));
//        Flux<Integer> flux5 = Flux.just(11,12,13,14,15).delayElements(Duration.ofMillis(100));
//
//        flux4.mergeWith(flux5).subscribe(System.out::println);

//        System.out.println("========materialized=== protita publisher  event guloke ke signal object e wrap kore, onNext , onError , OnComplete sob object ke ekta object e rupantor kore");
//
//        Flux<Signal<Integer>> integerFlux = Flux.range(1,10).materialize();
//        integerFlux.subscribe(signal-> System.out.println(signal.getType()+"  "+signal.get()+ " "+signal.getThrowable()));


//        System.out.println("==========hasElement===কোনো parameter নেয় না।;  Flux-এ কমপক্ষে একটা element আসলে true, না থাকলে false emit করে।");
//        Flux<Integer> integerFlux = Flux.range(1,5);
//        integerFlux.hasElements().subscribe(System.out::println);
//
//        Flux<String> stringFlux = Flux.empty();
//        stringFlux.hasElements().subscribe(System.out::println);
//
//
//        System.out.println("=======handle == map + filter + error একসাথে control করার জন্য");
//      //  BiFunction

//
//        System.out.println("===doOnSubscribe====ই operator টা subscription শুরু হবার সাথে সাথেই trigger হয়।\n" +
//                "অর্থাৎ যখন কোনো Subscriber (মানে তুমি যখন subscribe() call করো) upstream Publisher এ subscribe করে, তখনই এটা চলবে।" +
//                "Backpressure Control – subscription এর উপর কত data request হবে, সেটা manual control করা যায়।");

//        Flux.range(1, 20)
//                .doOnSubscribe(subscription -> {
//                    System.out.println("=== Subscription started ===");
//                    subscription.request(2); // প্রথমে শুধু ২টা
//                })
//                .doOnNext(v -> System.out.println("value => " + v))
//                .subscribe();

//                .doOnNext(v-> System.out.println("value==>"+v)).doOnError(eer-> System.out.println("Error==>"+eer.getMessage()))
//                        .doOnComplete(()-> System.out.println("Flux Completed")).elapsed()
//                                .subscribe(time-> {
//                                    System.out.println(time.getT1()+"  "+ time.getT2());
//                                });


//        System.out.println("=====doOnRequest====এটা হলো এমন একটা callback যা trigger হয় যখনই subscriber upstream থেকে কত data চাইলো (request(n))।\n" +
//                "\n" +
//                "\uD83D\uDCCC মানে: subscriber যখন subscription.request(n) কল করবে, doOnRequest সেটা ধরবে।");
//
//
//
//        Flux.range(1,10).doOnRequest(n-> System.out.println("Subscriber request data ==>"+ n +" data"))
//                        .subscribe(new BaseSubscriber<Integer>() {
//                            @Override
//                            protected void hookOnSubscribe(Subscription subscription) {
//                                System.out.println("Subscribed");
//                                subscription.request(3);
//                            }
//
//                            @Override
//                            protected void hookOnNext(Integer value) {
//                                System.out.println("Got value"+value);
//                                request(2);
//                            }
//                        });


//        System.out.println("doAfterTerminate======> eta trigger complete ba error gotar por");
//
//
//        Flux<Integer> integerFlux = Flux.just(1,1,2,3,4,3,0,6).map(v-> 10/v)
//                        .doOnTerminate(()-> System.out.println("DoOnTerminate call"))
//                                .doAfterTerminate(()-> System.out.println("doAfterTerminate call"))
//                .distinctUntilChanged();
//
//        integerFlux.subscribe(con-> System.out.println("value ==="+ con), error-> System.out.println("error====>"+error.getMessage()),()-> System.out.println("Stream is completeted"));

//        Flux<Employee> employeeFlux = Flux.just(new Employee(1,"kanchon") , new Employee(2,"kanchon"),
//                new Employee(3,"kanchon"),new Employee(4,"numan"))
//                .doOnTerminate(()-> System.out.println("calling the doOnTerminate==>"))
//                .doAfterTerminate(()-> System.out.println("calling doAfterTerminate==>"))
//                .filter(v-> v.getName() != null)
//                .distinctUntilChanged(Employee::getName, String::equals);
//
//
//        System.out.println("================================================================================");
//
//        Flux<Employee> employeeFluxnew = Flux.just(new Employee(1,"kanchon") , new Employee(2,"numan"),
//                        new Employee(3,"kanchon"),new Employee(4,"numan"))
//                .doOnTerminate(()-> System.out.println("calling the doOnTerminate==>"))
//                .doAfterTerminate(()-> System.out.println("calling doAfterTerminate==>"))
//                .filter(v-> v.getName() != null)
//                .distinct(Employee::getName, HashSet::new, (set,key)-> set.add(key.toLowerCase()), Collection::clear);
//        employeeFlux.subscribe(value-> System.out.println("value==>"+value.getName()),
//                err-> System.out.println("error=>"+err.getMessage()),()-> System.out.println("Stream is completed"));

        System.out.println("=======================================CombineLatest=========================================================");

//        // ২ সেকেন্ড পর পর Required Headcount আপডেট হচ্ছে (ধরি ডাটাবেস থেকে আসছে)
//        Flux<Integer> requiredFlux = Flux.just(10, 12)
//                .delayElements(Duration.ofSeconds(2));
//
//      // ১ সেকেন্ড পর পর নতুন ক্যান্ডিডেট সিলেক্ট হচ্ছে
//        Flux<Integer> selectedFlux = Flux.just(1, 2, 3, 4)
//                .delayElements(Duration.ofSeconds(1));
//
//     // combineLatest ব্যবহার করে রিয়েল-টাইম স্ট্যাটাস
//        Flux<String> statusFlux = Flux.combineLatest(
//                requiredFlux,
//                selectedFlux,
//                (req, sel) -> "Remaining: " + (req - sel) + " [Total: " + req + ", Selected: " + sel + "]"
//        );
//
//        statusFlux.subscribe(System.out::println);




//        System.out.println("===========================normal Flux for test Deafer====================================");
//
//        Mono<Long> clock = Mono.just(System.currentTimeMillis());
//
//       // ৫ সেকেন্ড পর সাবস্ক্রাইব করলেন
//        Thread.sleep(1000);
//        clock.subscribe(t -> System.out.println("Time 1: " + t));
//
//       // আরও ২ সেকেন্ড পর আবার সাবস্ক্রাইব করলেন
//        Thread.sleep(1000);
//        clock.subscribe(t -> System.out.println("Time 2: " + t));
//
//        System.out.println("===============deafer============================");
//
//        // defer ছাড়া (একবারই টাইম ফিক্সড হয়ে যাবে)
//        Mono<Long> staticTime = Mono.just(System.currentTimeMillis());
//
//// defer দিয়ে (প্রতিবার নতুন টাইম দিবে)
//        Mono<Long> lazyTime = Mono.defer(() -> Mono.just(System.currentTimeMillis()));
//
//// ২ সেকেন্ড পর পর সাবস্ক্রাইব করলে:
//        staticTime.subscribe(t -> System.out.println("Static: " + t)); // রেজাল্ট: ১০০
//        Thread.sleep(1000);
//
//        staticTime.subscribe(t -> System.out.println("Static: " + t)); // রেজাল্ট: ১০০ (পুরনো)
//        Thread.sleep(1000);
//
//
//        lazyTime.subscribe(t -> System.out.println("Lazy: " + t));   // রেজাল্ট: ১০০
//        Thread.sleep(1000);
//
//        lazyTime.subscribe(t -> System.out.println("Lazy: " + t));   // রেজাল্ট: ১০২ (নতুন করে তৈরি)
//
//        System.out.println("===================================MergePriority=========================================================");
//
//        Flux<Employee> employeeFlux1 = Flux.just(new Employee(1,"a"), new Employee(2,"b"),
//                new Employee(3,"c")).delayElements(Duration.ofMillis(10));
//
//        Flux<Employee> employeeFlux2 = Flux.just(new Employee(5, "d"), new Employee(6,"e"),
//                new Employee(4,"f")).delayElements(Duration.ofMillis(10));
//
//           Flux.mergeComparing(Comparator.comparingInt(Employee::getId),employeeFlux2, employeeFlux1)
//                        .subscribe(employee -> System.out.println(employee.getId()));
//
//           Flux.merge(employeeFlux2,employeeFlux1).sort(Comparator.comparing(Employee::getId))
//                           .subscribe(pri-> System.out.println(pri.getId()));
//
//
//
//
//
//
//
//
//
//
//
//
//
//    Flux<String> slowStream = Flux.interval(Duration.ofMillis(300))
//            .map(v-> "Slow"+(v))
//            .doOnCancel(() -> System.out.println("❌ --> Slow Stream কে CANCEL করে দেওয়া হয়েছে!"));
//
//    Flux<String> fastStream = Flux.interval(Duration.ofMillis(400))
//            .map(val-> "fast"+(val));
//
//    Flux<Flux<String>> mainStream = Flux.just(slowStream,fastStream)
//            .delayElements(Duration.ofMillis(400));
//
//    Flux.switchOnNext(mainStream).subscribe(data-> System.out.println("data =>"+data),
//                                          err-> System.out.println("errot"+ err),
//            ()-> System.out.println("Stream End"));


//        System.out.println("============================blockFirst========================================");
//
//        Flux<String> blockFirtsTest = Flux.just("Step 1 first value ", "Step 2 second value")
//                        .delayElements(Duration.ofMillis(200))
//                                .doOnCancel(()-> System.out.println("Flux ke cacel kore hoiche==============>"));
//
//        String getFirstVlaue = blockFirtsTest.blockFirst();
//        System.out.println(getFirstVlaue);
//
//        System.out.println("==============Buffer================================");
//
//
//   DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("hh:mm:ss a");
//
//
//   Flux.range(1, 100)
//                   .map(data -> "data==>"+ data)
//                           .buffer(10)
//                                   .delayElements(Duration.ofSeconds(5))
//           .subscribe(batch-> {
//               String startTime = LocalTime.now().format(dateTimeFormatter);
//               System.out.println("=================Batch data shuro holo==========================="+startTime);
//               System.out.println(batch);
//               System.out.println("==================Batch Data ent holo============================="+LocalTime.now().format(dateTimeFormatter)+"\n\n");
//           },error-> System.out.println("=============error====="+error)
//                   , ()-> System.out.println("==========Flux is Completed ===========")
//
//           );
//
//        System.out.println("========================bufferTimeout====================================================");
//
//        Flux<String> emainStreams = Flux.concat(
//                Flux.just("Email-1", "Email-2","Email-3").delayElements(Duration.ofSeconds(1)),
//                Flux.just("Email-4","Email-5").delayElements(Duration.ofSeconds(7))
//        );
//        emainStreams.bufferTimeout(10,Duration.ofSeconds(5))
//                        .subscribe(time-> {
//                                    System.out.println("================Start the buffer Time Out Data=================");
//                                    System.out.println(time);
//
//                        },
//                                err-> System.out.println("errror==>"+err)
//                            , ()-> System.out.println("Flux is Completed")
//                        );


//        System.out.println("==================================bufferUntil==========================================");
//
//
//        Flux<String> candidateStream = Flux.just("Candidate-A", "CandiDate-B","Candidate-C","HR-1","Candidate-D","HR-2");
//        candidateStream.bufferUntil(buf-> buf.startsWith("HR"))
//                        .subscribe(consume-> {
//                                    System.out.println("=================this method take value when the condition is matchded and match value take with box");
//                                    System.out.println(consume);
//                        },error-> System.out.println("Error===>"+error)
//                          ,()-> System.out.println("bufferUntil is completed")
//                        );


//        System.out.println("========================================withLatestFrom =====================================");
//
//        Flux<String> boardStatusStream = Flux.interval(Duration.ofSeconds(1))
//                        .map(b-> "BordStatus===="+b).share();
//
//        Flux<String> candidateStream = Flux.interval(Duration.ofSeconds(25))
//                .just("Candidate-Ripon", "Candidate-Asif")
//                .delayElements(Duration.ofMillis(2500));
//
//        candidateStream
//                .withLatestFrom(boardStatusStream, (candidate, status) -> {
//                    return candidate + " 🤝 যুক্ত হলো কারেন্ট [" + status + "] এর সাথে";
//                })
//                .subscribe(
//                        result -> System.out.println("📦 আউটপুট: " + result),
//                        error -> System.err.println("💥 এরর: " + error),
//                        () -> System.out.println("\n--- প্রসেস সমাপ্ত ---")
//                );








        Thread.sleep(60000);


    } // it is main method





    public static boolean isAuthorized(String user){
        return "admin".equalsIgnoreCase(user);
    }
}

class  Employee{
    private int id;
    private String name;

    public Employee(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Employee(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
