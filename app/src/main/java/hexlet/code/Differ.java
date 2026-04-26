package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Differ {
    public static String generate() {
        return "";
    }

    //Преобразует файлы в Map и печатает на экран
    public static void printFiles(String filepath1, String filepath2) throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        // Считываем файлы в Map
        Map<String, Object> map1 = mapper.readValue(new File(filepath1),
                new TypeReference<Map<String, Object>>() {});
        Map<String, Object> map2 = mapper.readValue(new File(filepath2),
                new TypeReference<Map<String, Object>>() {});

        var result = compareJsonMaps(map1, map2);

        //Сортируем Map по 3 символу(т.е. по первой букве в ключе)
        var n = 3;
        Map<String, Object> sortedMap = result.entrySet().stream()
                .sorted(Comparator.comparing(e -> e.getKey().length() >= n ? e.getKey().charAt(n - 1) : '\0'))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new
                ));

        System.out.println("{");
        sortedMap.forEach((key, value) -> System.out.println("  " + key + ": " + value));
        System.out.println("}");
    }

    //Сравнивает Map между собой и вычисляет отличия
    public static Map<String, Object> compareJsonMaps(Map<String, Object> map1, Map<String, Object> map2) {
        Set<String> allKeys = new HashSet<>(map1.keySet());
        allKeys.addAll(map2.keySet());

        var result = new HashMap<String, Object>();

        for (String key : allKeys) {
            if (!map1.containsKey(key)) {
                result.put("+ " + key, map2.get(key));
            } else if (!map2.containsKey(key)) {
                result.put("- " + key, map1.get(key));
            } else if (!map1.get(key).equals(map2.get(key))) {
                result.put("- " + key, map1.get(key));
                result.put("+ " + key, map2.get(key));
            } else {
                result.put("  " + key, map1.get(key));
            }
        }

        return result;
    }
}
