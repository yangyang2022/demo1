package testHibernate;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.yangyang.util.StaticUtils.printMap;

public class TestDemo {

    interface handleBufferedReader{
        String apply(BufferedReader br) throws IOException;
    }

    public String hand(String path,handleBufferedReader bb) {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(path));
            return bb.apply(br);
        } catch (Exception ioException){

        }finally {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "";
    }

    @Test
    public void testDemo() {

        String filePath = "F:\\ideaCode\\demo1\\src\\main\\resources\\files\\abc.txt";
        String s = hand(filePath,br -> br.readLine()+"\n"+br.readLine()+"\n"
                +br.readLine()+"\n"+br.readLine()+"\n"+br.readLine());
        System.out.println(s);
    }

    private static void print(String text){
        Optional.ofNullable(text).ifPresent(System.out::println);
    }
    private static int getLength(String text){
       return Optional.ofNullable(text).map(String::length).orElse(-1);
    }
    @Test
    public void testDemo2() {

        //(new Random()).ints(10).forEach(System.out::println);

        Stream<String> stream = Stream.of("a","b","c","d","e");
        //String s = stream.collect(Collectors.joining(", ", "[ ", " ]"));
        //System.out.println(s);
        //List<String> lists1 = stream.collect(Collectors.toList());
        //List<String> lists2 = stream.collect(Collectors.toCollection(ArrayList::new));

        //lists2.forEach(println);

        //print(null);
        //System.out.println(getLength(null));

        //Random random = new Random();
        //Stream.generate(()->random.nextInt(10)).limit(10).forEach(print);

        //Stream.iterate(0,n->n+1).limit(10).forEach(println);

        String s = "Stream.generate(()->random.nextInt(10)).limit(10).forEach(print);";
        Map<String, Long> collect = Arrays.stream(s.split(""))
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        collect.forEach(printMap);


    }

    @Test
    public void testDemo3() {


    }
}
