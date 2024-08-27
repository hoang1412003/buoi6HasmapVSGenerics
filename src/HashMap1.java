import java.awt.*;
import java.awt.Point;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class HashMap1 {
    public static void test1() {
        HashMap<String, String> map = new HashMap<>();
        map.put("USA", "Washington, D>C.");
        map.put("France", "Paris");
        map.put("Japan", "Tokyo");

        // kiểm tra sự ồn tại của một khóa
        if(map.containsKey("France")) {
            System.out.println("Capital of France: "+map.get("France"));
        }
        // kiểm tra sự tồn tại của một giá trị
        if(map.containsValue("Tokyo")) {
            System.out.println("Tokyo is in the map.");
        }

        // xóa một phan tử
        map.remove("Japan");
        System.out.println("###############");
        //in ra các phần tử còn lại trong HashMap
        for (String key : map.keySet()) {
            System.out.println(key+": "+map.get(key));
        }
        System.out.println("#######");
        // lặp qua các cặp khóa-giá trị bằng cách sử dụng entrySet()
        for (Map.Entry<String, String> entry : map.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            System.out.println("Country: " + key +", Capital: "+value);
        }
        System.out.println("#########");
        //Lấy giá trị với khóa có sẵn
        System.out.println("Value for key 'USA': "+map.getOrDefault("USA", "Not Found") );

        // Lấy giá trị với khóa không có sẵn
        System.out.println("Value for key 'C': "+map.getOrDefault("C", "Not Found"));

        // Thêm hoặc cập nhật giá trị nếu khóa không có mặt ( thay đổi kiểu giá trị thành String)
        map.putIfAbsent("VietNam", "Ha Noi");
        System.out.println("Value of VietNam "+ map.get("VietNam"));

        //Cập nhật giá trị nếu khóa có mặt (thay đổi kiểu giá trị từ Paris -> Paris (Updated)
        map.computeIfPresent("France", (k, v) -> v + " (Updated)");
        System.out.println("Update France capital: " + map.get("France"));
    }
    public static void test2_countWords() {
        String s= "xin chào xin xin chào phố phường bao phường phố";
        HashMap<String, Integer> map = new HashMap<>();
        String [] words = s.split(" ");
        for (String x:words) {
            int count = map.getOrDefault(x, 0);
            map.put(x, count+1);
        }
        for(String key : map.keySet()) {
            System.out.println(key+": "+map.get(key));
        }
    }
    public static void test3_countWords() {
        String s = "xin chào xin xin chào phố phường bao phường phố";
        HashMap<String, Integer> map = new HashMap<>();
        StringTokenizer tokenizer = new StringTokenizer(s);
        while (tokenizer.hasMoreTokens()) {
            String word = tokenizer.nextToken();
            map.put(word,map.getOrDefault(word, 0)+1);
        }

        for (String key : map.keySet()) {
            System.out.println(key+": "+map.get(key));
        }
    }
    public static void test4_dictionary() {
        // tạo một HashMap để lưu trữ từ điển
        Map<String, String> dictionary = new HashMap<>();

        // thêm một số từ và định nghĩa vào từ điển
        dictionary.put("apple", "A fruit that is typically round and red, green, or yellow.");
        dictionary.put("banana", "A long, curved fruit with a yellow skin and soft, sweet, white flesh.");
        dictionary.put("cherry", "A small, round fruit that is typically red or black.");

        Scanner scanner = new Scanner(System.in);
        System.out.println("Mời bạn nhập cần tra cứu: ");
        String name = scanner.nextLine();
        String res = dictionary.get(name);
        if(res!=null) {
            System.out.println(name+"  : " + res);
        }
        else {
            System.out.println("Not found");
        }
    }
    public static void test5_stream() {
        Map<String, Integer> map = new HashMap<>();

        // Quốc gia -- Số lượng cty có vốn trên 10tr đô
        map.put("USA", 3);
        map.put("France", 7);
        map.put("Japan", 2);
        map.put("VietNam", 25);

        System.out.println("##########");
        //In ra các phần tử còn lại trong HashMap
        for(String key : map.keySet()) {
            System.out.println(key+": "+map.get(key));
        }

        System.out.println("##########");
        // In ra các phần tử còn lại trong HashMap
        map.entrySet().stream()
                .forEach(item-> System.out.println(item.getKey()+":"+item.getValue()));

        System.out.println("#############");
        // Tính tổng số luongj các công ty trên
        int kq = map.values().stream()
                .mapToInt(Integer::intValue)
                .sum();
        System.out.println("Sum ="+kq);
        System.out.println("############");
        // Lọc các quốc gia có số lượng cty > 5
        Map<String, Integer> newMap = map.entrySet().stream()
                .filter(item->item.getValue()>5)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        System.out.println(newMap);
        Map<String, Integer> newMap1 = map.entrySet().stream()
                .filter(item->item.getValue()>5)
                .collect(Collectors.toMap(
                        entry -> entry.getKey(),
                        entry -> entry.getValue()
                ));
        System.out.println(newMap1);

    }

    public static void test_linkedHashMap() {
        Map<String, Integer> linkedHashMap = new LinkedHashMap<>();

        // Thêm phần tử
        linkedHashMap.put("One", 1);
        linkedHashMap.put("two", 2);
        linkedHashMap.put("three", 3);
        System.out.println("linkedHashMap: "+linkedHashMap);

        // Cập nhật giá trị của khóa "two"
        linkedHashMap.put("two", 22);

        // Thêm một phần tử mới
        linkedHashMap.put("four", 4);

        // Truy cập các phần tu
        linkedHashMap.get("two");
        linkedHashMap.get("one");
        System.out.println("linkedHashMap.get(\"two\"): "+linkedHashMap.get("two"));
        // xóa phần tử với khóa "two"
        linkedHashMap.remove("two");

        // In ra linkedHashMap
        for(Map.Entry<String, Integer> entry : linkedHashMap.entrySet()) {
            System.out.println("entry: "+ entry);
            System.out.println(entry.getKey() + ": "+ entry.getValue());
        }
    }
    public static void test_treemap() {
        // tạo một treeMap với comparator tùy chình để sắp xếp theo thứ tự giảm dần
        // treeMap<String, Interger> treeMap = new TreeMap<>(Comparator.reserseOrder());
        Map<String, Integer> treeMap = new TreeMap<>();

        // Them các phần tử vào TreeMap

        treeMap.put("banana", 2);
        treeMap.put("apple", 3);
        treeMap.put("cherry", 1);

        // In ra TreeMap
        for(Map.Entry<String, Integer> entry : treeMap.entrySet()) {
            System.out.println(entry.getKey()+": "+entry.getValue());
        }

        // Tạo TreeMap với khóa là tên và giá trị là tuổi
        TreeMap<String, Integer> ageMap = new TreeMap<>();

        // Thêm các phần tử vào TreeMap
        ageMap.put("John", 30);
        ageMap.put("Jane", 25);
        ageMap.put("Paul", 35);
        ageMap.put("Anna", 28);

        System.out.println("########");
        for (Map.Entry<String, Integer> entry : ageMap.entrySet()) {
            System.out.println(entry.getKey()+": "+entry.getValue());
        }
        System.out.println("############");
        //Tìm kiếm các phần tử
        System.out.println("First Key: "+ageMap.firstKey()); // tên đầu tiên theo thứ tự
        System.out.println("Last key: "+ ageMap.lastKey()); // tên cuối cùng theo thứ tự
        System.out.println("SubMap from 'Anna' to 'Paul': " + ageMap.subMap("Anna", "Paul"));

    }
    // Ví dụ về hashcode
    public static void test_hashCodepoint(){
        Map<Point, String> pointMap = new HashMap<>();
        Point p1 = new Point(1, 2);
        Point p2 = new Point(1, 2); // Đối tượng giống p1
        Point p3 = new Point(3, 4);

        pointMap.put(p1, "Point 1");
        pointMap.put(p3, "Point 2");

        // In ra kết quả kiểm tra
        System.out.println("Map contains p2: " + pointMap.containsKey(p2)); // true, vì p1 và p2 bằng nhau
        System.out.println("Map content: " + pointMap);
    }
    public static void test_hashCode_person() {
        Person p1 = new Person("Alice", 30);
        Person p2 = new Person("Alice", 30);
        Person p3 = new Person("Bob", 25);

        // Sử dụng HashSet để kiểm tra equals và hashCode
        Map<Person, Integer> map = new HashMap<>();
        map.put(p1, 1);
        // map.put(2, p2);
        map.put(p3, 3);

        // In ra kết quả kiểm tra
        System.out.println("Map contains p2: " + map.containsKey(p2)); // True, vì p1 và p2 bằng nhau
        System.out.println("Map content: " + map);
    }

    public static void test_optional() {
        // tạo các đối tuongj Optional
        Optional<String> optionalVaule = Optional.of("Hello, World");
        Optional<String> emptyOptional = Optional.empty();

        // Sử dụng các phuong thức của Optional
        System.out.println("optionalValue is present: "+emptyOptional.isPresent());
        optionalVaule.ifPresent(value -> System.out.println("value: "+value));

        // Lấy giá trị hoặc giá trị thay thế
        String value = emptyOptional.orElse("Default Value");
        System.out.println("Value or Default: " + value);

        // giá trị thay the từ supplier
        String generatedValue = emptyOptional.orElseGet(() -> "Generated Default Value");
        System.out.println("Generated Value: " + generatedValue);

        // Giá trị từ Optional nếu có, hoặc ném ngoại lệ neu không có
        try {
            String result = emptyOptional.orElseThrow(() -> new IllegalArgumentException("Value not present"));
        } catch (Exception e) {
            System.out.println("Caught exception: " + e.getMessage());
        }
    }

    // GENERIC
    public static <T> void printArray (T[] array) {
        for (T element : array) {
            System.out.println(element + "  ");
        }
        System.out.println("");
    }
    public static void generic1(){
        Box<String> stringBox = new Box<>();
        stringBox.setContent("Hello Generics");
        System.out.println("String content: " + stringBox.getContent());

        // Tạo Box cho Integer
        Box<Integer> integerBox = new Box<>();
        integerBox.setContent(123);
        System.out.println("Integer content: " + integerBox.getContent());
        Integer[] intArray = {1, 2, 3, 4};
        String[] strArray = {"A", "B", "C"};

        // Gọi phương thức Generic
        printArray(intArray);
        printArray(strArray);

        // Tạo danh sách cho số nguyên
        List<Integer> integerList = new ArrayList<>();
        integerList.add(1);
        integerList.add(2);
        integerList.add(3);

        // In danh sách
        for(Integer item : integerList) {
            System.out.println(item);
        }

        Pair<String, Integer> pair = new OrderedPair<>("Age", 25);
        System.out.println("Key: " + pair.getKey() + ", Value: " + pair.getValue());
    }
    public  static void main(String[] args) {
//        test1();
//        test2_countWords();
//        test3_countWords();
//        test5_stream();
//        test_linkedHashMap();
//        test_treemap();
//        test_hashCode_person();
//        test_optional();
        generic1();
    }
}
