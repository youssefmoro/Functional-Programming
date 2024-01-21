import com.sun.org.apache.bcel.internal.generic.BIPUSH;

import javax.swing.text.html.Option;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {
//    @FunctionalInterface
//    public Class Supplier<T>{
//        public T get();
//    }
public static Optional<Double> average(int... scores)
{
    if(scores.length==0) return Optional.empty();
    int sum=0;
    for(int score:scores)
    {
        sum+=score;
    }
    return Optional.of((double)sum/scores.length);
}
    public static void main(String[] args) {
        Supplier<LocalDate> s1=LocalDate::now;
        Supplier<LocalTime> s2=()->{return LocalTime.now();};
        LocalDate d1=s1.get();
        LocalTime d2=s2.get();
        System.out.println(d1);
        System.out.println(d2);
        // used also to create new objects and return it's reference to your var
        Supplier<StringBuilder> s3=StringBuilder::new;
        Supplier<StringBuilder> s4=()->{return new StringBuilder();};
        System.out.println(s3.get());
        System.out.println(s4.get());
        Supplier<ArrayList<String>> s5=ArrayList<String>::new;
        System.out.println(s5.get());
        //Consumer and BiConsumer
        Consumer<String> c1=System.out::println;
        Consumer<String> c2=x->System.out.println(x);
        c1.accept("Yousef");
        c2.accept("Moro");
        Map<String,Integer> map=new HashMap<>();
        BiConsumer<String,Integer> b1=map::put;
        BiConsumer<String,Integer> b2= (k,v) -> map.put(k,v);
        b1.accept("Yousef",26);
        b2.accept("Mahmoud",30);
        System.out.println(map);
        //Predicates and BiPredicates
        Predicate<String> p1=String::isEmpty;
        Predicate<String> p2=x->x.isEmpty();
        System.out.println(p1.test("Ali"));
        System.out.println(p2.test(""));
        BiPredicate<String,String> bP1=String::startsWith;
        BiPredicate<String,String> bP2=(x,y)->x.startsWith(y);
        System.out.println(bP1.test("Ali","A"));
        System.out.println(bP1.test("Ali","a"));
        //function and bifunction
        Function<String,Integer> f1=String::length;
        Function<String,Integer>f2=x->x.length();
        System.out.println(f1.apply("Yousef"));
        System.out.println(f2.apply("Ahmed"));
        //The first two types in the BiFunction are the input types. The third is the result type.
        //For the method reference, the first parameter is the instance that concat() is called on and
        //the second is passed to concat()
        BiFunction<String,String,String> bF1=String::concat;
        BiFunction<String,String,String> bF2=(x,toAdd)->x.concat(toAdd);
        System.out.println(bF2.apply("Yousef","Mohamed"));
        System.out.println(bF1.apply("Mahmoud","Moro"));
        //Unary and binary operators
        //@FunctionalInterface public class UnaryOperator<T>
        // extends Function<T, T> { }
        //@FunctionalInterface public class BinaryOperator<T>
        // extends BiFunction<T, T, T> { }
        //This means that method signatures look like this:
        //T apply(T t);
        //T apply(T t1, T t2);
        UnaryOperator<String> u1 = String::toUpperCase;
        UnaryOperator<String> u2 = x -> x.toUpperCase();
        System.out.println(u1.apply("chirp"));
        System.out.println(u2.apply("chirp"));
        BinaryOperator<String> bO1 = String::concat;
        BinaryOperator<String> bO2 = (string, toAdd) -> string.concat(toAdd);
        System.out.println(bO1.apply("baby ", "chick"));
        System.out.println(bO2.apply("baby ", "chick"));
        //Optionals
        System.out.println(average(90,95));
        System.out.println(average());
        Optional<Double> opt1=average(10,20,30,40,50,60);
        Optional<Double> opt2=average();
        System.out.println(opt2.isPresent());
        System.out.println(opt1.isPresent());
        System.out.println(opt1.get());
        Optional opt3 = (opt2== null) ? Optional.empty(): Optional.of(opt2);
        opt1.ifPresent(X->System.out.println(X));
        System.out.println(opt1.orElse((double)50));
        System.out.println(opt2.orElse((double)50));
        System.out.println(opt2.orElseGet(()->(double)50));
        //System.out.println(opt3==Optional.empty());
        //Optional o = Optional.ofNullable(opt3);
        //System.out.println(opt2.orElseThrow(()->new ArithmeticException()));
        Stream<String> empty1 = Stream.empty(); // count = 0
        Stream<Integer> singleElement1 = Stream.of(1); // count = 1
        Stream<Integer> fromArray1 = Stream.of(1, 2, 3); // count = 2
        List<String> list= Arrays.asList("A","B","C");
        Stream<String> fromlist=list.stream();
        Stream<String> parallelone=list.parallelStream();
        // create random stream using generate,iterate and math.random that's all
         Stream<Double> randoms = Stream.generate(Math::random);
         //iterate() takes a seed or starting value as the first
        //parameter. This is the fi rst element that will be part of the stream. The other parameter is a
        //lambda expression that gets passed the previous value and generates the next value. As with
        //the random numbers example, it will keep on producing odd numbers as long as you need
        //them
         Stream<Integer> oddNumbers = Stream.iterate(1, n -> n + 2);
         //reduction methods
        Stream<String> s10=Stream.of("Yousef","Mohamed","Moro");
        Optional<String>min=s10.min((s11, s12)->s11.length()-s12.length());
        System.out.println(min);
        Stream<String> s17=Stream.of("Yousef","Mohamed","Moro");
        Optional<String>max=s17.max((s14, s15)->s14.length()-s15.length());
        max.ifPresent(System.out::println);
        Stream<String> h = Stream.of("monkey", "gorilla", "bonobo");
        Stream<String> infinite = Stream.generate(() -> "chimp");
        h.findAny().ifPresent(System.out::println); // monkey
        infinite.findAny().ifPresent(System.out::println); // chimp
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie");
        Optional<String> anyName = names.stream().findAny();  // Could return "Alice", "Bob", or "Charlie"
        anyName.ifPresent(System.out::println);
        //allMatch() , anyMatch() and noneMatch()
        List<String> listex= Arrays.asList("Yousef","2","Moro");
        Stream<String> infinit=Stream.generate(()->"Yousef");
        Predicate<String> pred=x->Character.isLetter(x.charAt(0));
        System.out.println(listex.stream().anyMatch(pred));
        System.out.println(listex.stream().allMatch(pred));
        System.out.println(listex.stream().noneMatch(pred));
        System.out.println(infinit.anyMatch(pred));
        Stream<String> sss=Stream.of("Yousef","Mohamed","Moro -");
//        sss.forEach(System.out::print);
        sss.forEach(x->System.out.print(x));
        //reduce reduction to one object
        Stream<String> sds=Stream.of("Ahmed","Mohamed","Moro");
//        String obj=sds.reduce("",(x,y)->(x+y));
        String obj=sds.reduce("",String::concat);
        System.out.println(obj);
        Stream<Integer> sds2=Stream.of(10,10,10);
        int res=sds2.reduce(1,(x,y)->x*y);
        System.out.println(res);
        Stream<String> streamss = Stream.of("w", "o", "l", "f");
//        StringBuilder word = streamss.collect(StringBuilder::new,StringBuilder::append, StringBuilder:append);
//        System.out.println(word);
//        TreeSet<String> set=streamss.collect(TreeSet::new,TreeSet::add,TreeSet::addAll);
//        System.out.println(set);
        TreeSet<String> set1=streamss.collect(Collectors.toCollection(TreeSet::new));
        System.out.println(set1);
        Stream<String> s90 = Stream.of("monkey", "gorilla", "bonobo","gorilla");
        //s90.filter(x -> x.startsWith("g")).forEach(System.out::print);
        //s90.distinct().forEach(System.out::print);
        Stream<Integer> s95=Stream.iterate(1,n->(1+n));
        s95.skip(5).limit(3).forEach(System.out::print);
        s90.map(String::length).forEach(System.out::print);
        List<String> zero=Arrays.asList();
        List<String> one= Arrays.asList("Yousef");
        List<String> two=Arrays.asList("Mohamed","Moro");
        Stream<List<String>> animals=Stream.of(zero,one,two);
        animals.flatMap(a->a.stream()).forEach(System.out::println);
        Stream<String> myFullName=Stream.of("Yousef","Mohamed","Moro");
        myFullName.sorted().forEach(System.out::print);
//        myFullName.sorted(Comparator.reverseOrder()).forEach(System.out::print);
        Stream<String> animals2=Stream.of("Bird","Pigeon","crocodile");
        long count=animals2.filter(a->a.startsWith("c")).peek(System.out::println).count();
        System.out.println(count);
        List<String> list50 = Arrays.asList("Toby", "Anna", "Leroy", "Alex");
        list50.stream().filter(n -> n.length() == 4).sorted().limit(2).forEach(System.out::println);
        IntStream sfs=IntStream.of(1,2,3);
        OptionalDouble avg=sfs.average();
        System.out.println(avg.getAsDouble());
        DoubleStream random = DoubleStream. generate (Math::random);
        DoubleStream fractions = DoubleStream.iterate (.5, d -> d / 2);
        random.limit(3).forEach(System.out::println);
        System.out.println();
        fractions.limit(3).forEach(System.out::println);
        IntStream rangeClosed = IntStream.rangeClosed(1, 5);
        rangeClosed.forEach(System.out::println);
        Stream<String> objStream = Stream.of("penguin", "fish");
        IntStream intStream = objStream.mapToInt(s -> s.length());
        System.out.println();
        intStream.forEach(System.out::print);










    }
}