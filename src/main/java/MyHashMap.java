import java.util.Arrays;
import java.util.HashMap;
import java.util.Objects;

public class MyHashMap<K, V> {
    private static final int CAPACITY = 16;
    private Node<K, V>[] table;

    static class Node<K, V> {
        final K key;
        V value;
        final int hash;
        Node<K, V> next;
        Node(K key, V value, int hash, Node<K, V> next) {
            this.key = key;
            this.value = value;
            this.hash = hash;
            this.next = next;
        }
    }

    public MyHashMap() {
        table = new Node[CAPACITY];
    }

    private int hash(K key) {
        return (key == null) ? 0 : Math.abs(key.hashCode() % CAPACITY);
    }

    public void put(K key, V value) {
        int index = hash(key);
        Node<K, V> node = table[index];
        if (node == null) {
            table[index] = new Node<>(key, value, index, null);
        } else {
            Node<K, V> prev = null;
            while (node != null) {
                if (node.key.equals(key)) {
                    node.value = value;
                    return;
                }
                prev = node;
                node = node.next;
            }
            prev.next = new Node<>(key, value, index, null);
        }
    }

    public V get(K key) {
        int index = hash(key);
        Node<K, V> node = table[index];
        while (node != null) {
            if (node.key.equals(key)) return node.value;
            node = node.next;
        }
        return null;
    }

/*
    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;
        Person p = (Person) o;
        return age == p.age && Objects.equals(name, p.name);
    }

    public static int hash(Object... values) {
        return Arrays.hashCode(values);
    }

    public static int hashCode(Object a[]) {
        if (a == null)
            return 0;

        int result = 1;

        for (Object element : a)
            result = 31 * result + (element == null ? 0 : element.hashCode());

        return result;
    }
    */




    /*

    1️⃣ Chia lấy dư

🔹 Tiếng Anh: modulo hoặc remainder

🔹 Toán học: “the remainder of division”

🔹 Trong code: dùng toán tử %


    🧩 1️⃣ Giả sử ta có HashMap
Map<Person, String> map = new HashMap<>();


    🔹 Bước 1: Java gọi hashCode() của Person("Alice",25)
   → Giả sử ra 12345.

🔹 Bước 2: Dựa vào hashCode này, HashMap tính bucket index:
index = hashCode % capacity

🔹 Bước 3: Nếu bucket đó trống → lưu object mới.
Nếu đã có phần tử khác cùng hashCode → kiểm tra tiếp bằng equals().

⚖️ 3️⃣ Khi ta get()
map.get(new Person("Alice", 25));


➡️ HashMap lại:

Gọi hashCode() của key mới → 12345

Đi đến bucket 12345 % capacity

So sánh từng node trong bucket đó bằng equals()

Nếu equals() trả về true → trả về giá trị "Engineer"

🧩 4️⃣ Khi bị trùng hashCode() (collision)

Ví dụ:

Person("Alice",25)  -> hash = 12345
Person("Bob",30)    -> hash = 12345

➡️ Cả hai cùng rơi vào bucket 1, nhưng HashMap sẽ lưu chúng dạng linked list hoặc balanced tree (Java 8+)

Bucket 1
 ↓
[Node] -> key=Alice -> value="Engineer"
   ↓
[Node] -> key=Bob   -> value="Manager"


➡️ Khi get(), HashMap tìm đúng bucket, rồi so sánh equals() từng node để chọn đúng entry.

🧩 5️⃣ Tóm tắt bằng sơ đồ luồng:
Put(key, value):
    ├─> Gọi key.hashCode()
    ├─> Tính bucket = hashCode % capacity
    ├─> Nếu bucket rỗng → thêm Node mới
    └─> Nếu có Node cùng hash → so sánh equals()
         ├─ true → update value
         └─ false → thêm vào danh sách bucket

Get(key):
    ├─> Gọi key.hashCode()
    ├─> Tìm bucket
    ├─> Duyệt từng Node trong bucket
    └─> Nếu equals() → trả về value


    🧩 1️⃣ Luật cơ bản trong Java (rất quan trọng):

Nếu bạn override equals(), thì phải override hashCode().

Nếu không, các collection như HashMap, HashSet sẽ hoạt động sai..

🧠 2️⃣ Lý do: HashMap và HashSet dựa trên hashCode + equals

Khi em thêm object vào HashMap hoặc HashSet, Java sẽ làm 2 bước:

1️⃣ Gọi hashCode() để xác định bucket (vị trí lưu)
2️⃣ Nếu có nhiều object trong cùng bucket, gọi equals() để xác định object nào thực sự bằng nhau

⚙️💬 6️⃣ Cách nói trong phỏng vấn (chuẩn tiếng Anh)

In Java, whenever you override equals(), you must also override hashCode() because collections like HashMap and HashSet use the hash code to determine the bucket location and then use equals() to check equality within the same bucket.

If you don’t override hashCode(), two logically equal objects may have different hash codes, causing lookup failures and incorrect behavior in hash-based collections.


Theo JDK specification:

“The hashCode method for class Object returns a distinct integer for each object. This is typically implemented by converting the internal memory address to an integer, but this is not required.”

Nói cách khác:

hashCode mặc định thường tương quan với địa chỉ bộ nhớ,
nhưng không phải chính xác địa chỉ đó (vì địa chỉ thật là native pointer, còn hashCode chỉ là int).

Mỗi JVM có thể implement khác nhau.


LRU viết tắt của Least Recently Used —
Một cache có dung lượng giới hạn (ví dụ 3 item).
Khi thêm phần tử mới mà cache đã đầy → ta phải xóa phần tử ít được dùng nhất gần đây để nhường chỗ.

class LRUCache<K, V> extends LinkedHashMap<K, V> {
    private final int capacity;

    public LRUCache(int capacity) {
        super(capacity, 0.75f, true); // 'true' => access order
        this.capacity = capacity;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return size() > capacity;
    }
}


Stream API
Intermediate operations (trả về Stream, lazy)

map — ánh xạ phần tử → stream.map(x -> f(x))

filter — lọc → stream.filter(pred)

flatMap — ánh xạ thành stream và flaten → dùng với nested lists

distinct — loại trùng

sorted — sắp xếp

limit, skip — lấy/bỏ số phần tử

peek — để debug / side-effect (cẩn thận)

Terminal operations (kết thúc pipeline, eager)

collect — gom lại (ra Collection, Map, summary)

forEach / forEachOrdered

reduce — gộp

count, anyMatch, allMatch, noneMatch, findFirst, findAny, min, max

Stream là gì? — Declarative pipeline xử lý data, lazy, có intermediate & terminal operations.

map vs flatMap? — map chuyển 1->1, flatMap 1->many rồi flatten.

ParallelStream khác gì? — Thực thi pipeline song song, dùng ForkJoinPool; lưu ý thread-safety và overhead.

collect vs reduce? — reduce immutable single value; collect mutable, builder-style, tốt cho collections.

Why Optional + stream? — tránh NPE; có stream() trên Optional để integrate with pipeline.

When does stream evaluate? — khi terminal operation gọi (lazy evaluation).

Why is mapToInt used? — avoid boxing, performance.


reduce(identity, accumulator, combiner)
identity — giá trị khởi đầu (giống như biến sum = 0).

accumulator — hàm gộp hai phần tử (lambda có 2 tham số).

“Functional” nghĩa là em truyền hành vi (behavior) — tức là một hàm (function) — vào trong code, thay vì viết từng vòng lặp thủ công.

🔹 Trước đây (imperative): em nói “LÀM THẾ NÀO để duyệt list”.
🔹 Với functional: em nói “TÔI MUỐN lọc, map, hay tính tổng” — còn cách làm để cho Stream lo.

Functional Interface	Chức năng	Ví dụ dùng trong Stream
Predicate<T>	Trả về boolean	.filter(x -> x > 10)
Function<T, R>	Biến đổi T → R	.map(x -> x * 2)
Consumer<T>	Thực hiện hành động, không trả về	.forEach(System.out::println)
Supplier<T>	Cung cấp giá trị	Stream.generate(() -> Math.random())
BinaryOperator<T>	Kết hợp 2 phần tử cùng kiểu	.reduce((a, b) -> a + b)

//🧩 1️⃣ Lambda là gì?
//
//Lambda là một cách viết ngắn gọn cho một interface chỉ có 1 method duy nhất
//👉 nghĩa là thay vì viết class/anonymous class dài dòng, ta viết ngắn lại.

🔸 Ví dụ trước khi có lambda (Java 7):
Runnable r = new Runnable() {
    @Override
    public void run() {
        System.out.println("Hello");
    }
};
new Thread(r).start();

🔹 Sau khi có lambda (Java 8 trở lên):
Runnable r = () -> System.out.println("Hello");
new Thread(r).start();

🧠 2️⃣ Cú pháp Lambda cơ bản

Cấu trúc tổng quát:

(parameters) -> { body }


// Multithread
🧩 1️⃣ Multithreading là gì?

Multithreading = “chạy nhiều luồng (threads) cùng lúc trong cùng 1 process”.

➡️ Một Thread là đơn vị nhỏ nhất của CPU có thể thực thi song song.
➡️ JVM cho phép nhiều thread cùng chạy trên cùng vùng nhớ → giúp tăng tốc độ xử lý.

Ví dụ:

1 thread đọc file

1 thread gửi email

1 thread ghi log
→ tất cả chạy song song 🏃‍♂️🏃‍♀️

⚙️ 2️⃣ Cách tạo thread trong Java
✅ Cách 1: extends Thread
class MyThread extends Thread {
    @Override
    public void run() {
        System.out.println("Running in " + Thread.currentThread().getName());
    }
}

public class Example {
    public static void main(String[] args) {
        new MyThread().start(); // Tạo 1 thread mới
    }
}


✅ Cách 2: implements Runnable
class MyTask implements Runnable {
    @Override
    public void run() {
        System.out.println("Task running in " + Thread.currentThread().getName());
    }
}

public class Example {
    public static void main(String[] args) {
        Thread t = new Thread(new MyTask());
        t.start();
    }
}

✅ Cách 3: dùng ExecutorService (thực tế nhất)
ExecutorService executor = Executors.newFixedThreadPool(3);

for (int i = 0; i < 5; i++) {
    int taskId = i;
    executor.submit(() -> {
        System.out.println("Task " + taskId + " by " + Thread.currentThread().getName());
    });
}

executor.shutdown();

Câu hỏi	Câu trả lời gợi ý
What is a thread in Java?	A thread is a lightweight sub-process that runs concurrently within a program. Each thread has its own call stack but shares the same memory.
What’s the difference between process and thread?	A process has its own memory space; threads within a process share memory and resources.
How to create a thread in Java?	By extending Thread, implementing Runnable, or using ExecutorService (recommended).
What is thread-safety?	Thread-safety means multiple threads can access shared resources without causing data inconsistency.
How to make code thread-safe?	Use synchronized, Lock, Atomic classes, or thread-safe collections.
What is a race condition?	When multiple threads modify shared data concurrently, leading to unpredictable results.
Difference between synchronized and Lock?	Lock gives more control (tryLock, fair lock, interruptible), while synchronized is simpler and implicit.
What is a deadlock?	When two or more threads wait on each other’s locks and none can proceed.
What is ExecutorService?	A framework that manages a pool of threads and executes submitted tasks efficiently.
What is CompletableFuture?	A class used for asynchronous programming and combining multiple tasks non-blockingly.


🧩 1️⃣ @Async là gì?

Annotation @Async giúp Spring chạy một method ở background thread (nằm trong thread pool), không chặn luồng chính (main thread).
Nó được Spring quản lý thông qua TaskExecutor (mặc định là SimpleAsyncTaskExecutor, nhưng bạn nên cấu hình ThreadPoolTaskExecutor để kiểm soát tốt hơn).


=====
Isolation Levels in Database Transactions
defines how/when the changes made by one transaction become visible to other concurrent transactions.
It defines how/when the changes made by one transaction become visible to other concurrent transactions.

The main goal: avoid unexpected interference between concurrent transactions.

Common problems in concurrent transactions:

Dirty Read – reading uncommitted data from another transaction

Non-Repeatable Read – reading the same row twice in a transaction, but value changes because another transaction modified it

Phantom Read – reading a set of rows matching a condition, but new rows appear (or disappear) due to other transactions

3️⃣ Explanation with Examples
1️⃣ READ UNCOMMITTED

T1 updates balance=100 but not committed

T2 reads balance=100 → if T1 rolls back → T2 read wrong value (dirty read)

2️⃣ READ COMMITTED

T1 commits balance=100

T2 reads only committed data → avoids dirty read

But if T1 later updates the same row → T2 can see a different value on next read (non-repeatable read)

3️⃣ REPEATABLE READ

T2 reads balance=50

Even if T1 updates the row later, T2 still sees 50 for that row

But new rows inserted by T1 matching T2’s query → phantom read

4️⃣ SERIALIZABLE

T2 cannot see any changes from T1 until T1 commits and finishes

Transactions behave as if executed sequentially → no dirty/non-repeatable/phantom reads
     */
}
