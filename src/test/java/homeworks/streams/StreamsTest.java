package homeworks.streams;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class StreamsTest {


    private List<Product> products = new ArrayList<>();
    private static final Logger log = LoggerFactory.getLogger(StreamsTest.class);
    private static IntStream intStream;

    @BeforeEach
    public void setUp() {
        intStream = IntStream.range(0, 100);
        this.products.add(new Product("sausages", "meat", 12.42));
        this.products.add(new Product("cheese", "dairy products", 16.12));
        this.products.add(new Product("ham", "meat", 15.92));
    }

    /**
     * Выведите на экран только те элементы списка, которые больше заданного числа(47).
     */
    @Test
    void testMoreThan() {
        intStream.filter(n -> n > 47).peek(n -> log.debug("{}", n)).toArray();
    }

    /**
     * Собрать продукты в Stream в таблицу, где каждая строка — это информация о продукте
     * (название, категория, стоимость), отсортированную по категории продукта.
     */
    @Test
    void testSortedTable() {
        Map<String, List<Product>> productMap = products.stream().collect(Collectors.groupingBy(Product::getCategory));
        log.debug("{}", productMap);
    }

    /**
     * Создать два стрима: один из чисел от 0 до 10, другой из чисел от 10 до 20.
     * Объединить их в один стрим и вывести на экран числа больше 10.
     */
    @Test
    void joinStreams() {
        IntStream first = IntStream.range(0, 10);
        IntStream second = IntStream.range(10, 20);
        IntStream.concat(first, second).filter(n -> n > 10).peek(n -> log.debug("{}", n)).toArray();
    }

    /**
     * Разделить элементы Stream на две группы: четные и нечетные, вывести результаты.
     */
    @Test
    void splitStreams() {
        Map<Boolean, List<Integer>> collect = intStream.boxed().collect(Collectors.partitioningBy(n -> (n % 2) == 0));
        log.debug("{}", collect);
    }

    /**
     * Создание двух Stream из массивов целых чисел и объединение их в один,
     * затем вывод на экран суммы квадратов элементов этого Stream.
     */
    @Test
    void joinArrays() {
        int[] array = IntStream.range(0, 50).toArray();
        int[] secondArray = IntStream.range(50, 100).toArray();
        int sum = IntStream.concat(IntStream.of(array), IntStream.of(secondArray))
                .map(n -> n * n)
                .sum();
        log.debug("sum: {}", sum);
    }

    /**
     * Сгруппировать элементы Stream по их чётности, посчитать размер каждой группы и вывести результаты.
     */
    @Test
    void countStreams() {
        Map<Boolean, Long> count = intStream.boxed().collect(Collectors.partitioningBy(n -> n % 2 == 0, Collectors.counting()));
        log.debug("count odd and even:{}", count);
    }

    /**
     * Сгруппировать продукты в Stream по категории, посчитать стоимость продуктов в каждой категории
     * и вывести результаты в виде таблицы, где столбец — категория продукта, а строка — стоимость продуктов
     * в этой категории.
     */
    @Test
    void getPriceByCategory() {
        Map<String, Double> collect = products.stream()
                .collect(Collectors
                        .groupingBy(Product::getCategory, Collectors.summingDouble(Product::getPrice)));
        log.debug("{}", collect);
    }

    /**
     * Соберите продукты в Stream в один продукт, сложив цены и категории и выведите результат.
     */
    @Test
    void collectProducts() {
        String collect = products.stream().map(p -> p.getCategory() + ": " + p.getPrice())
                .collect(Collectors.joining(", "));
        log.debug("{}", collect);
    }

    /**
     * Создать стрим, который выводит числа от 1 до 10.
     * Найти среднее арифметическое этих чисел и вывести его на экран.
     */
    @Test
    void getAverage() {
        OptionalDouble average = IntStream.range(1, 10).average();
        log.debug("{}", average);
    }

    /**
     * Создание Stream целых чисел и поиск всех чисел, у которых сумма цифр равна заданному числу(7).
     */
    @Test
    void sumNumbers() {
        intStream.filter(n -> String.valueOf(n).chars()
                .map(Character::getNumericValue)
                .sum() == 7).peek(n -> log.debug("{}", n)).toArray();
    }



    static class Product {
        String name;
        String category;
        double price;

        Product(String name, String category, double price) {
            this.name = name;
            this.category = category;
            this.price = price;
        }

        String getCategory() {
            return category;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof Product product)) return false;
            return Double.compare(price, product.price) == 0 && Objects.equals(name, product.name) && Objects.equals(category, product.category);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, category, price);
        }

        @Override
        public String toString() {
            return "Product{" +
                    "name='" + name + '\'' +
                    ", category='" + category + '\'' +
                    ", price=" + price +
                    '}';
        }
    }
}