//package spring_reactive.com.utility;
//
//import reactor.core.publisher.ConnectableFlux;
//import reactor.core.publisher.Flux;
//import reactor.core.publisher.GroupedFlux;
//import reactor.core.publisher.Mono;
//
//import javax.xml.transform.sax.SAXSource;
//import java.time.Duration;
//import java.time.LocalDate;
//import java.util.List;
//import java.util.Map;
//import java.util.concurrent.CompletableFuture;
//import java.util.function.Function;
//
//public class Test {
//
//    public static void main(String [] arg) throws InterruptedException {
////        ConnectableFlux<String> connectableFlux = Flux.interval(Duration.ofMillis(100))
////                .map(i-> "Data-"+i)
////                .doOnNext(System.out::println).
////                publish();
////
////        connectableFlux.connect(); // emit is starting
////
////      Thread.sleep(2000);
////      connectableFlux.subscribe(pub1-> System.out.println("sub1="+pub1));
////
////      Thread.sleep(2200);
////
////      connectableFlux.subscribe(pub2-> System.out.println("sub2="+pub2));
////
////      Thread.sleep(3000);
////
////        // Cold Publisher
////       Flux<String> stringFlux= Flux.just("a","b","c","d");
////
////       stringFlux.subscribe(sub1-> System.out.println("sub1===="+sub1));
////       Thread.sleep(2000);
////
////       stringFlux.subscribe(sub2-> System.out.println("sub2===="+sub2));
////
////
////       // hot Publisher
////
////
////      ConnectableFlux<String> connectableFlux = Flux.interval(Duration.ofMillis(200))
////              .map(data-> "data=="+data).publish(); // ready the hot publisher
////
////        connectableFlux.connect();
////
////        Thread.sleep(2000);
////
////        connectableFlux.subscribe(sub1-> System.out.println("sub1====="+sub1));
////
////        Thread.sleep(3000);
////
////        connectableFlux.subscribe(sub2-> System.out.println("sub2===="+sub2));
////
////        Thread.sleep(3500);
//
////   String currentUser = "adminn";
////
////  Flux<String> sensitiveData = Flux.just("data-1","data-2","data-3")
////          .doOnSubscribe(subscription -> {
////              System.out.println("Subscription started by user "+currentUser);
////
////              if (!isAuthorized(currentUser)){
////                  System.out.println("Subscription user is not authorized"+currentUser);
////                  subscription.cancel();
////
////              }else {
////                  System.out.println("Subscription is authorized so that process the data");
////              }
////
////          }).doOnNext(data-> System.out.println("processing data: "+data))
////          .doOnComplete(()-> System.out.println("Complete the data"))
////          .doOnCancel(()-> System.out.println("Cancel the data"));
////
////
////    sensitiveData.subscribe();
//
//
//
//        // transformation operator
//        // group by operator
//
////
////         Flux.just("abc" ,"bcd", "adk", "bhk")
////                .groupBy(value-> value.charAt(0))
////                .flatMap(goup-> goup.map(String::toUpperCase)
////                        .collectList().map(d-> "Group-"+goup.key()+": "+d) )
////                .subscribe(System.out::println);
//
//
////        Flux<String> flux1 = Flux.just("apple", "apricot", "banana", "blueberry");
////        Flux<String> flux2 = Flux.just("avocado", "blackberry", "bluebell");
////
////        Flux<String> merged = Flux
////                .merge(flux1, flux2) // দুইটা flux merge করলাম
////                .groupBy(word -> word.charAt(0)) // প্রথম অক্ষর দিয়ে group করলাম
////                .flatMap(groupFlux ->
////                        groupFlux
////                                .collectList() // একই key এর value গুলো একত্র করলাম
////                                .map(list -> "Group " + groupFlux.key() + ": " + String.join(", ", list))
////                );
////
////
////        merged.subscribe(System.out::println);
//
//
////        Flux<String> flux1 = Flux.just("apple", "banana", "avocado", "blueberry");
////        Flux<String> flux2 = Flux.just("apricot", "blackberry", "almond", "banana");
////
////        Mono<Map<Character, List<String>>> merged1 = flux1
////                .groupBy(s -> s.charAt(0))  // Flux<GroupedFlux<Character,String>>
////                .flatMap(group ->
////                        group.collectList() // Mono<List<String>
////                                .map(list -> Map.entry(group.key(), list))// tahlole abar keno map korte hocche
////                )
////                .collectMap(Map.Entry::getKey, Map.Entry::getValue);
////
////
////        merged1.subscribe(map1-> {
////    map1.forEach((key,value)-> System.out.println("key="+key+"   value="+value));
//
//
//        Flux<String> stringFlux = Flux.just("abc","bde","akl", "bik");
//        Flux<GroupedFlux<Character,String>> groupedFluxFlux = stringFlux.groupBy(c-> c.charAt(0));
//
////        Mono<Map<Character,List<String>>> mapMono=
////                stringFlux.groupBy(c-> c.charAt(0))
////                        .flatMap(g-> g.collectList()
////                                .map(list-> Map.entry(g.key(),list)))
////                        .collectMap(Map.Entry::getKey, Map.Entry::getValue);
////
////        mapMono.subscribe(map->{
////            map.forEach((key,value)-> System.out.println(key+"====="+value));
////
////        });=
//
//        Mono<Map<Character, List<String>>> mapMono  = stringFlux.groupBy(c-> c.charAt(1))
//                .flatMap(g-> g.collectList()
//                        .map(list-> Map.entry(g.key(),list))
//                ).collectMap(Map.Entry::getKey,Map.Entry::getValue);
//
//        mapMono.subscribe(map->{
//            map.forEach((key,value)-> System.out.println(key+"  "+value));
//        });
//
////        groupedFluxFlux.flatMap(group->
////                group.map(list-> group.key()+"======"+list)).subscribe(System.out::println);
//
////        stringFlux.groupBy(c-> c.charAt(0))
////                .flatMap(Flux::collectList)
////                .subscribe(System.out::println);
//
//
////        groupedFluxFlux.subscribe(group-> {
////            System.out.println("Group key="+group.key());
////            group.subscribe(valu-> System.out.println("value="+valu));
////
////        });
//
//
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
//
//
//
//    }
//
//    public static boolean isAuthorized(String user){
//        return "admin".equalsIgnoreCase(user);
//    }
//}
