
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * В этом задании вам нужно переписать предложенные методы с помощью Stream API таким образом,
 * чтобы вывод программы не изменился, но все методы были переписаны с помощью Stream API
 */
public class Main {
    private static final String TASK_SEPARATOR =
            "---------------------------------------------------";

    public static void main(String[] args) throws IOException {
        String[] fruits = {"apple", "lemon", "pineapple", "watermelon", "avocado", "apricot"};
        System.out.println("Задание 1");
        System.out.println("Фруктов начинающихся на а всего " + countAllFruitsWithFirstA(fruits));
        System.out.println(TASK_SEPARATOR);
        System.out.println("Задание 2");
        System.out.println("Распечатываем все фрукты по алфавиту в верхнем регистре");
        printFruits(Arrays.asList(Arrays.copyOf(fruits, fruits.length)));
        System.out.println(TASK_SEPARATOR);
        System.out.println("Задание 3");
        List<FruitWithId> fruit = Arrays.stream(fruits)
                .map(FruitWithId::new)
                .collect(Collectors.toList());
        System.out.println("2 фрукта с длинными именами:" + collectTwoFruitsWithLongNames(fruit));
        System.out.println(TASK_SEPARATOR);
        System.out.println("Задание 4");
        List<List<String>> fruitsFromFiles = Stream.of(
                        Files.readAllLines(Paths.get("fruits1.txt")),
                        Files.readAllLines(Paths.get("fruits2.txt")))
                .collect(Collectors.toList());
        System.out.println("Фрукты в списках: " + getAllFruitsFromFilesInLowerCase(fruitsFromFiles));
    }

    /**
     * Метод подсчитывает количество фруктов в массиве название которых начинается с буквы 'a'
     *
     * @param fruits Список фруктов
     * @return количество фруктов в массиве которые начинаются с буквы а
     */
  /* public static long countAllFruitsWithFirstA(String[] fruits) {
    long sum = 0;
    for (String fruit : fruits) {
      if (fruit.charAt(0) == 'a') {
        sum += 1;
      }
    }
    return sum;
  } */
    public static long countAllFruitsWithFirstA(String[] fruits) {
        return Arrays.stream(fruits).filter(f -> f.startsWith("a")).count();
    }


    /**
     * Метод распечатывает в UPPER_CASE названия всех фруктов по алфавиту
     *
     * @param fruits Список фруктов
     */
 /* public static void printFruits(List<String> fruits) {
    Collections.sort(fruits);
    for (String fruit : fruits) {
      System.out.println(fruit.toUpperCase());
    }
  } */
    public static void printFruits(List<String> fruits) {
        fruits.stream()
                .sorted()
                .forEach(f -> System.out.println(f.toUpperCase()));
    }


    /**
     * Метод принимает пару фрукт и его идентификатор, выбирает 2 фрукта с именами больше 5 символов
     * и собирает их в Map в которой ключом является id фрукта, а значением - сам фрукт
     *
     * @param fruits список фруктов с id
     * @return Ассоциативный массив фруктов и их идентификаторов
     */
 /* public static Map<Integer, FruitWithId> collectTwoFruitsWithLongNames(List<FruitWithId> fruits) {
    Map<Integer, FruitWithId> result = new HashMap<>();
    for (FruitWithId fruit : fruits) {
      if (fruit.getName().length() > 5) {
        result.put(fruit.getId(), fruit);
        if (result.size() > 1) {
          break;
        }
      }
    }
    return result;
  } */
    public static Map<Integer, FruitWithId> collectTwoFruitsWithLongNames(List<FruitWithId> fruits) {

        return (Map<Integer, FruitWithId>) fruits.stream()
                .filter(f -> f.getName().length() > 5)
                .skip(2)
                .collect(Collectors.toMap(FruitWithId::getId, fruit -> fruit));

    }


    /**
     * Метод получает считанные из файлов строки с фруктами,
     * приводит их в одинаковый вид и составляет из них единый список
     *
     * @param fruitsFromFiles строки с фруктами которые мы считали из файлов
     * @return список названий фруктов в нижнем регистре
     */
 /*   public static List<String> getAllFruitsFromFilesInLowerCase(List<List<String>> fruitsFromFiles) {
        List<String> result = new ArrayList<>();
        for (List<String> fruitList : fruitsFromFiles) {
            for (String fruit : fruitList) {
                result.add(fruit.toLowerCase());
            }
        }
        return result;
    } */
    public static List<String> getAllFruitsFromFilesInLowerCase(List<List<String>> fruitsFromFiles) {
        return fruitsFromFiles.stream()
                .flatMap(Collection::stream)
                .map(String::toLowerCase)
                .toList();
    }
}
