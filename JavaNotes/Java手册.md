Content

- [程序架构](# 程序架构)
- [面向对象](# 面向对象)
- [数据类型与数据结构](# 数据类型与数据结构)
- [Tricks](# Tricks)



#### 程序基础

JVM、JRE、JDK（java虚拟机、java运行环境、java开发工具）的关系

```ascii
  ┌─    ┌──────────────────────────────────┐
  │     │     Compiler, debugger, etc.     │    javac xx.java -> xx.class
  │     └──────────────────────────────────┘
 JDK ┌─ ┌──────────────────────────────────┐
  │  │  │                                  │
  │ JRE │      JVM + Runtime Library       │    java xx (no '.class' suffix)
  │  │  │                                  │
  └─ └─ └──────────────────────────────────┘
        ┌───────┐┌───────┐┌───────┐┌───────┐
        │Windows││ Linux ││ macOS ││others │
        └───────┘└───────┘└───────┘└───────┘
```

- Java是将代码编译成一种“字节码”，它类似于抽象的CPU指令，然后，针对不同平台编写虚拟机，不同平台的虚拟机（JVM）负责加载字节码并执行，这样就实现了“一次编写，到处运行”的效果。

一个程序

```java
// 包名：test 目录：test
// 文件名：Test.java (public类名.java)
// public类名：Test
package test;
public class Test {
    public static void main(String[] args){  // args接收命令行参数 java Test arg1 arg2
    }
}
```

命名规范

```ascii
类名：HelloMan | HelloMan (python)
方法名：goodMorning | good_morning (python)
变量名：
```

基本数据类型

```java
整数类型：byte(1B)，short(2B), int(4B)，long(8B)
浮点数类型：float(4B，需加上f后缀) ，double(8B)
字符类型：char(2B，ASCII和Unicode) 'c'和'中'
布尔类型：boolean(理论1bit，但JVM4B)
```

运算注意事项 ~~整数运算~~、~~浮点数运算~~

```java
// 异或(^)：相同为0，相反为1 

// 类型自动提升与强制转型：在运算过程中，如果参与运算的两个数类型不一致，那么计算结果为较大类型的整型。例如，short和int计算，结果总是int，原因是short首先自动被转型为int：

// 需要特别注意，在一个复杂的四则运算中，两个整数的运算不会出现自动提升的情况。
double d = 1.2 + 24 / 5; // 5.2

// 整数运算在除数为0时会报错，而浮点数运算在除数为0时，不会报错，但会返回几个特殊值：
double d1 = 0.0 / 0; // NaN not a number
double d2 = 1.0 / 0; // Infinity
double d3 = -1.0 / 0; // -Infinity

// 可以将浮点数强制转型为整数。在转型时，浮点数的小数部分会被丢掉。如果转型后超过了整型能表示的最大范围，将返回整型的最大值。
int n3 = (int) (12.7 + 0.5); // 13
int n4 = (int) 1.2e20; // 2147483647
```

流程控制注意事项

```java
// for each
int[] ns = { 1, 4, 9, 16, 25 };
for (int n : ns) {}
```



#### 面向对象

方法、构造方法 this、可变参数、方法重载

```java
public class Test{
    public static void main(String[] args){
        Puppy myPuppy = new Puppy( "tommy" );            // 下面的语句将创建一个Puppy对象
        myPuppy.setNames("name1", "name2")
        myPuppy.setNames(new String[] {"name1", "name2"}); // 传入1个String[]
    }
}

class Puppy{
    private String name;
    private String[] names;
	public Puppy(String name){                          // 构造方法 可以多个
        this.name = name;                               // this指代当前实例
    }
    public void setNames(String... names) {             // 可变参数 传入多个String
        this.names = names;
    }
    public void setNames(String[] names) {              // String[] 区别于可变参数 可传儒null
        this.names = names;                      // 方法重载：方法名相同，参数不同,返回值类型相同
    }
}
```



**继承** extends、protected、super

```java
// extends、protected、super
class Animal {
    private String name;           // 注意：子类无法访问父类的private字段或者private方法
    protected int age;             // protected关键字可以把字段和方法的访问权限控制在继承树内部
	public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
    void eat() {
    }
}
class Dog extends Animal {
    public Dog(String name, int age, String type){
        super(name, age);             // 调用父类的构造方法 否则编译器会自动加上super();发生报错
    }
    
    @Override
	void eat() {                      // 覆写
        System.out.println("dog");
    }
    void eatTest() {
    	this.eat();                   // this 调用自己的方法
    	super.eat();                  // super 调用父类方法
    }
}

// 向上转型与向下转型
Animal a = new Dog();                 // 向上转型：父变量指向子实例
if (a instanceof Dog) {               // 只有判断成功才会向下转型 instanceof
    Dog d = (Dog) a;                  // 向下转型
}

```



**多态** 覆写(Override)、重载(overload)、final

```java
// 覆写(Override)：在继承关系中，子类定义了一个与父类方法签名(方法名和参数)和返回类型完全相同的方法
class Person {
    public void run() {
    	System.out.println("Person.run");
    }
}

class Student extends Person {
    @Override
    public void run() {
        System.out.println("Student.run");
    }
   	@Overload
    public void run(String s) { … }          // 是Overload 不是Override，因为参数不同
    
    // public int run() { … }            // 报错 与void run()冲突 方法签名相同，但返回类型不同   
}

public class Main {
    public static void main(String[] args) {
        Person p = new Student();        // 向上转型 父变量指向子实例
        p.run();                         // 子实例覆写run()方法 打印"Student.run"
    }
}

// 区别：
// Override和Overload不同的是，如果方法签名不同，就是Overload，Overload方法是一个新方法；
// 如果方法签名相同，并且返回值也相同，就是Override。
// 方法名相同，方法参数相同，但方法返回值不同，编译器会报错。

// override：1.子类继承 2.外壳不变，核心重写
// 重写是子类对父类的方法的实现过程进行重新编写, 返回值和形参都不能改变。即外壳不变，核心重写！
// overload：1.同一个类或子类中 2.方法名字相同 3.形参必须改变、返回类型可以不同
// 重载是在一个类里面，方法名字相同，而参数不同。返回类型可以相同也可以不同。常见在构造方法（构造器）。
    
// final：不可覆写、不可继承、不可二次赋值

```

```java
// 多态例子：报税 给一个有普通收入、工资收入和享受国务院特殊津贴的小伙伴算税:
public class Main {
    public static void main(String[] args) {
        // 给一个有普通收入、工资收入和享受国务院特殊津贴的小伙伴算税
        Income[] incomes = new Income[] {
            new Income(3000),
            new Salary(7500),
            new StateCouncilSpecialAllowance(15000)
        };
        System.out.println(totalTax(incomes));
    }

    public static double totalTax(Income... incomes) {
        double total = 0;
        for (Income income: incomes) {
            total = total + income.getTax();
        }
        return total;
    }
}

class Income {
    protected double income;

    public Income(double income) {
        this.income = income;
    }

    public double getTax() {
        return income * 0.1; // 税率10%
    }
}

class Salary extends Income {
    public Salary(double income) {
        super(income);
    }

    @Override
    public double getTax() {
        if (income <= 5000) {
            return 0;
        }
        return (income - 5000) * 0.2;
    }
}

class StateCouncilSpecialAllowance extends Income {
    public StateCouncilSpecialAllowance(double income) {
        super(income);
    }
    
    @Override
    public double getTax() {
        return 0;
    }
}
```



抽象类 abstract 

```java
abstract class Person { // 抽象类 存在抽象方法必须定义为抽象类
    public abstract void run();   // 抽象方法
}

// 抽象类本身被设计成只能用于被继承，因此，抽象类可以强迫子类实现其定义的抽象方法，否则编译会报错

```



接口 interface implement

```java
// 如果一个抽象类没有字段，所有方法全部都是抽象方法，就可以把该抽象类改写为接口：interface。

interface Hello {
    void hello();             // 默认都是public abstract，因此可以不写这两个修饰符
}

interface Person extends Hello {     // 接口继承
    void run();
    String getName();
}

class Student implements Person, Hello {   // 接口实现
    private String name;

    public Student(String name) {
        this.name = name;
    }
	
    @override
    public void hello() {}
    
    @Override
    public void run() {
        System.out.println(this.name + " run");
    }

    @Override
    public String getName() {
        return this.name;
    }
}
```



静态变量与静态方法

```ascii
        ┌──────────────────┐
ming ──▶│Person instance   │
        ├──────────────────┤
        │name = "Xiao Ming"│
        │age = 12          │
        │number ───────────┼──┐    ┌─────────────┐
        └──────────────────┘  │    │Person class │
                              │    ├─────────────┤
                              ├───▶│number = 99  │
        ┌──────────────────┐  │    └─────────────┘
hong ──▶│Person instance   │  │
        ├──────────────────┤  │
        │name = "Xiao Hong"│  │
        │age = 15          │  │
        │number ───────────┼──┘
        └──────────────────┘
```

```java
// 在Java程序中，实例对象并没有静态字段。
// 在代码中，实例对象能访问静态字段只是因为编译器可以根据实例类型自动转换为类名.静态字段来访问静态对象。

public class Main {
    public static void main(String[] args) {
        Person.setNumber(99);                     // 调用
        System.out.println(Person.number);
        Person p = new Person();
    }
}

class Person {
    public static int number;                     // 静态变量
	
    public Person() {                             // 在构造方法里修改静态变量
        number++;
    }
    
    public static void setNumber(int value) {     // 静态方法
        number = value;
    }
}
```



包

```java
// Main.java
package test;

// 默认自动import当前package的其他class
// 默认自动import java.lang.*
import java.text.Format;

public class Main {
    public static void main(String[] args) {
        java.util.List list; // ok，使用完整类名 -> java.util.List
        Format format = null; // ok，使用import的类 -> java.text.Format
        String s = "hi"; // ok，使用java.lang包的String -> java.lang.String
        System.out.println(s); // ok，使用java.lang包的System -> java.lang.System
        MessageFormat mf = null; // 编译错误：无法找到MessageFormat
    }
}
```

```ascii
假设我们创建了如下的目录结构：
work
├── bin
└── src
    └── com
        └── itranswarp
            ├── sample
            │   └── Main.java
            └── world
                └── Person.java
其中，bin目录用于存放编译后的class文件，src目录按包结构存放Java源码，我们怎么一次性编译这些Java源码呢？
work目录：$ javac -d ./bin src/**/*.java
命令行-d指定输出的class文件存放bin目录，后面的参数src/**/*.java表示src目录下的所有.java文件，包括任意深度的子目录。注意：Windows不支持**这种搜索全部子目录的做法，在Windows下编译必须依次列出所有.java文件。
如果编译无误，则javac命令没有任何输出。可以在bin目录下看到如下class文件：
bin
└── com
    └── itranswarp
        ├── sample
        │   └── Main.class
        └── world
            └── Person.class
现在，我们就可以直接运行class文件了。根据当前目录的位置确定classpath，例如，当前目录仍为work，则classpath为bin或者./bin：
$ java -cp bin com.itranswarp.sample.Main 
> Hello, world!
```



作用域

```askii
public：定义为public的class、interface可以被其他任何类访问；
        定义为public的field、method可以被其他类访问，前提是首先有访问class的权限。
protected：作用于继承关系。定义为protected的字段和方法可以被子类访问，以及子类的子类。
无修饰符(包作用域)：只要在同一个包，就可以访问package权限的class、field和method。
private：定义为private的field、method无法被其他类访问
```



匿名类

```java
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        HashMap<String, String> map1 = new HashMap<>();
        HashMap<String, String> map2 = new HashMap<>() {}; // 匿名类!
        HashMap<String, String> map3 = new HashMap<>() {
            // 子类的构造方法
            {
                put("A", "1");
                put("B", "2");
            }
        };
        System.out.println(map3.get("A"));
    }
}

// 通过new AbcClass() {...}，等同于创建了一个继承自AbcClass父类的匿名子类的对象
```



JavaBean

```java
// JavaBean是一种符合命名规范的class，它通过getter和setter来定义属性
public class Person {
    // 若干private实例字段
    private String name;
    private int age;
	
	// 通过public方法来读写实例字段
    public String getName() { return this.name; }
    public void setName(String name) { this.name = name; }
    public int getAge() { return this.age; }
    public void setAge(int age) { this.age = age; }
}
```



classpath

```askii
https://www.liaoxuefeng.com/wiki/1252599548343744/1260466914339296

classpath是JVM用到的一个环境变量，它用来指示JVM如何搜索class。
classpath就是一组目录的集合，它设置的搜索路径与操作系统相关。
如果JVM在某个路径下找到了对应的class文件，就不再往后继续搜索。如果所有路径下都没有找到，就报错。
在系统环境变量中设置classpath环境变量，不推荐，因为会污染系统环境。
在启动JVM时设置classpath变量，推荐：
	java -cp .;C:\work\project1\bin;C:\shared abc.xyz.Hello
没有设置系统环境变量，也没有传入-cp参数，那么JVM默认的classpath为.，即当前目录：
	java abc.xyz.Hello
```



jar包

```askii
jar包就是zip包，压缩(zipped)文件夹制作zip文件，然后把后缀从.zip改为.jar，一个jar包就制作完成了。
假设编译输出的目录结构是这样：
package_sample
└─ bin
   ├─ hong
   │  └─ Person.class
   │  ming
   │  └─ Person.class
   └─ mr
      └─ jun
         └─ Arrays.class
这里需要特别注意的是，jar包里的第一层目录，不能是bin，而应该是hong、ming、mr，即把hong、ming、mr三个目录打包取名“hello.jar”
	java -cp ./hello.jar hong.Person
```



class版本

```askii
Java 8，Java 11，Java 17，是指JDK的版本，也就是JVM的版本，更确切地说，就是java.exe这个程序的版本：
$ java -version
> java version "17" 2021-09-14 LTS

每个版本的JVM，它能执行的class文件版本也不同。例如，Java 11对应的class文件版本是55，而Java 17对应的class文件版本是61。
如果用Java 11编译一个Java程序，输出的class文件版本默认就是55，这个class既可以在Java 11上运行，也可以在Java 17上运行，因为Java 17支持的class文件版本是61，表示“最多支持到版本61”。反之，则会报错
```



模块

```

```








- 封装

  ```java
  // 通过getter和setter方法访问类中private成员属性
  public class Person{
      private String name;                         // private属性
      public String getName(){                     // public方法 获取属性值
          return name;
      }
      public void setName(String name){            // public方法 设置属性值
       	this.name = name;
      }
  }
  ```

- 继承：extends

  ```java
  // extends、super、this
  class Animal {
  	void eat() {
      }
  }
  class Dog extends Animal {
  	void eat() {
      }
      void eatTest() {
      	this.eat();                   // this 调用自己的方法
      	super.eat();                  // super 调用父类方法
      }
  }
  
  ```

- 接口（interface）：implements 

  ```java
  // 声明定义 文件名：接口名称.java
  public interface 接口名称 [extends 其他的接口名] {  	// 修饰符只能是public，
                                                     // abstract默认忽略，不能用static修饰符
          // 声明变量，只能是public static final类型的                                 
          // 抽象方法，默认忽略abstract修饰符
  }
  
  // 例子
  public interface A {
  	public void eat();
  }
  public interface B {
      public void show();
  }
  public class C implements A,B {                    // implements
  }
  
  // 标记接口
  
  ```

- 重写与重载

  ```java
  // override：1.子类继承 2.外壳不变，核心重写
  // 重写是子类对父类的方法的实现过程进行重新编写, 返回值和形参都不能改变。即外壳不变，核心重写！
  
  // overload：1.同一个类或子类中 2.方法名字相同 3.形参必须改变、返回类型可以不同
  // 重载是在一个类里面，方法名字相同，而参数不同。返回类型可以相同也可以不同。常见在构造方法（构造器）。
  ```

- 向上转型，例子：抽象类与子类

  ```java
  // 向上转型：父类实例调用子类重写的方法
  abstract class Animal {                          // 抽象类无法直接实例化 需要子类继承
      abstract void eat();                         // 抽象方法 子类必须重写父类的抽象方法
  }  
    
  class Cat extends Animal {  
      public void eat() {  
          System.out.println("吃鱼");  
      }
      public void work() {
      }
  }  
  
  public class Test {
  	public static void main(String[] args) {
          Animal a = new Cat();                    // 向上转型  
          a.eat();                                 // 调用的是 Cat 的 eat
          Cat c = (Cat)a;                          // 向下转型  
          c.work();                                // 调用的是 Cat 的 work
      }   
  }
  ```





#### 数据类型与数据结构

常量

```java
public static final String DEPARTMENT = "开发人员";           // 大写
int max = Integer.MAX_VALUE;
int min = Integer.MIN_VALUE
```



数组

```java
dataType[] arrayRefVar = new dataType[arraySize];  // 数组声明与创建
int[] arr = new int[]{1, 2, 3};                    // 数组创建方式
int[] arr = {1, 2, 3};
int[][] res = new int[n][n];
int[][] ns = {
    { 1, 2, 3, 4 },
    { 5, 6 },
    { 7, 8, 9 }
};
                     ┌───┬───┬───┬───┐
         ┌───┐   ┌──▶│ 1 │ 2 │ 3 │ 4 │
ns ─────▶│░░░│──┘    └───┴───┴───┴───┘
         ├───┤      ┌───┬───┐
         │░░░│─────▶│ 5 │ 6 │
         ├───┤      └───┴───┘
         │░░░│──┐   ┌───┬───┬───┐
         └───┘  └──▶│ 7 │ 8 │ 9 │
                    └───┴───┴───┘

len = array.length                                 // 数组长度（访问数组的长度属性）

int[] array2 = array1.clone();                     // 数组克隆 浅拷贝

// 数组不可变
int[] ns = new int[] { 68, 79, 91, 85, 62 };
ns = new int[] { 1, 2, 3 };
     ns ──────────────────────┐
                              ▼
┌───┬───┬───┬───┬───┬───┬───┬───┬───┬───┬───┐
│   │68 │79 │91 │85 │62 │   │ 1 │ 2 │ 3 │   │
└───┴───┴───┴───┴───┴───┴───┴───┴───┴───┴───┘
```

```java
import java.util.Arrays;                      // 引入Arrays类

//1.填充数组
Arrays.fill(array, 5);                        // 填充数组的全部元素为5
Arrays.fill(array, 2, 4, 8);                  // 将数组的第2和第3个元素赋值为8

//2.数组排序 升序
Arrays.sort(array);                           // 对数组整体排序
Arrays.sort(array, 2, 7);                     // 对数组的第2个到第6个进行排序进行排序

//3.查找数组元素 binarySearch 有序数组
index = Arrays.binarySearch(array, 5);        // 返回索引值，查不到则返回-(插入点)-1
    
//4.数组比较
isEqual = Arrays.equals(array1, array2);      // 比较两个数组的元素是否相等，返回boolean值
    
// 数组扩容 默认补0 返回新数组
dataType[] newAarray = Arrays.copyOf(array, array.length); // 拷贝一份

// 查找数组最小/大值

// Arrays.asList
String[] myArray = { "Apple", "Banana", "Orange" }； 
List<String> myList = Arrays.asList(myArray);
// Arrays.toString
String str = Arrays.toString(array);
```



**String**

```java
// 声明与创建字符串
String str1 = "hello world";                   // String创建的字符串存储在公共池中
String str2 = new String("hello world");       // 而new创建的字符串对象在堆上

// 从Java 13开始，字符串可以用"""..."""表示多行字符串（Text Blocks）了。

// 字符串不可变
String s = "hello";
s = "world";
      s ──────────────┐
                      ▼
┌───┬───────────┬───┬───────────┬───┐
│   │  "hello"  │   │  "world"  │   │
└───┴───────────┴───┴───────────┴───┘
```

```java
// 长度
len = str.length();                                    // 字符串长度 

// 类型转换
str = String.valueOf(123);                             // "123"
str = String.valueOf(45.67);                           // "45.67"
str = String.valueOf(true);                            // "true"
int n1 = Integer.parseInt("123");                      // 123
int n2 = Integer.parseInt("ff", 16);                   // 按十六进制转换，255
boolean b1 = Boolean.parseBoolean("true");             // true

// 字符串与字符数组
char[] charArray = str.toCharArray();                  // String -> char[]
String str = new String(charArray)                     // char[] -> String
String str = Arrays.toString(array);

// 字符串处理
newstr = str + num                                     // '+'连接字符串和其他数据类型
newStr = str1 + str2;                                  // '+'拼接不生成新的字符串对象
newStr = str1.concat(str2);                            // concat()拼接返回新字符串对象
newstr = String.join("*", arr); // "A*B*C"             // 使用指定字符串连接字符数组 静态方法
str = str.trim();                                      // 忽略前导空白和尾部空白 适用英文
str = str.strip();                                     // 忽略前导空白和尾部空白 适用中英文
String[] strArray = str.split("and|or"/"\\.");         // 拆分字符串 根据正则表达式
str = str.replaceAll(String regex, String newStr);     // 替换字符串中的子串 根据正则表达式
str = str.replace(char searchChar, char newChar);      // 替换字符串中的字符
subStr = str.substring(int beginIndex, int endIndex);  // 获取子串
str = str.toUpperCase();                               // 转成大写
str = str.toLowerCase();                               // 转成小写
str=String.format("Hi %s, age %d", "Bob", 18);         // 生成格式化字符串

// 字符串判断
isEmpty = str.isEmpty();                               // 判空 “”
isBlank = "  \n".isBlank();                            // true，因为只包含空白字符
isContains = str.contains(substr);                     //判断是否包含某个字符或某个字符串
isEqual = str1.equals(str2);                           // 比较的是字面值，'=='比较的是地址
isEqual =str1.equalsIgnoreCase(str2);                  // 忽略大小写
isStartsWith = str.startsWith(prefix, int startIndex); // 判断字符串是否有指定的前缀
isEndsWith = str.endsWith(suffix);                     // 判断字符串是否有指定的后缀
isMatch = str.matches(String regex);                   // 判断字符是否匹配相应的正则表达式

// 查找
// 返回指定字符/字符串 在此字符串中 从startIndex处开始（可以不要这个参数）第一次出现处的索引
index = str.indexOf(ch/string, int startIndex); 
index = str.lastIndexOf(ch/string, int startIndex);    // 从startIndex开始反向查找 
char c = str.charAt(int index);                        // 返回指定索引处的char值
```

```askii
// 字符编码 Java的String和char在内存中总是以Unicode编码表示
ASCII：ANSI制定了一套英文字母、数字和常用符号的编码，占用一个字节，编码范围从0到127，最高位始终为0
GB2312：使用两个字节表示一个汉字    
Unicode：统一全球所有语言的编码，占用两个或者更多字节
UTF-8：因为英文字符的Unicode编码高字节总是00，包含大量英文的文本会浪费空间，所以，出现了UTF-8编码，它是一种变长编码，用来把固定长度的Unicode编码变成1～4字节的变长编码。通过UTF-8编码，英文字符'A'的UTF-8编码变为0x41，正好和ASCII码一致，而中文'中'的UTF-8编码为3字节0xe4b8ad。UTF-8编码的另一个好处是容错能力强。如果传输过程中某些字符出错，不会影响后续字符，因为UTF-8编码依靠高字节位来确定一个字符究竟是几个字节，它经常用来作为传输编码。

英文字符'A'的ASCII编码和Unicode编码：
         ┌────┐
ASCII:   │ 41 │
         └────┘
         ┌────┬────┐
Unicode: │ 00 │ 41 │
         └────┴────┘
英文字符的Unicode编码就是简单地在前面添加一个00字节。
中文字符'中'的GB2312编码和Unicode编码：
         ┌────┬────┐
GB2312:  │ d6 │ d0 │
         └────┴────┘
         ┌────┬────┐
Unicode: │ 4e │ 2d │
         └────┴────┘

在Java中，char类型实际上就是两个字节的Unicode编码。如果我们要手动把字符串转换成其他编码，可以这样做：
byte[] b1 = "Hello".getBytes(); // 按系统默认编码转换，不推荐
byte[] b2 = "Hello".getBytes("UTF-8"); // 按UTF-8编码转换
byte[] b2 = "Hello".getBytes("GBK"); // 按GBK编码转换
注意：转换编码后，就不再是char类型，而是byte类型表示的数组。

如果要把已知编码的byte[]转换为String，可以这样做：
byte[] b = ...
String s1 = new String(b, "GBK"); // 按GBK转换
String s2 = new String(b, "UTF-8"); // 按UTF-8转换
```

```
StringBuilder sb = new StringBuilder();       // 可变长字符串
sb.append("%20");
sb.toString(); // StringBuilder -> String
```



**StringBuilder**

```java
// 为了能高效拼接字符串，Java标准库提供了StringBuilder，它是一个可变对象，可以预分配缓冲区，这样，往StringBuilder中新增字符时，不会创建新的临时对象
StringBuilder sb = new StringBuilder(1024);    // 可变长字符串
for (int i = 0; i < 1000; i++) {
    sb.append(',').append(i);                  // 支持链式操作 return this
    // sb.append(i);
}
// sb.insert(0, "hello");                      // 插入0号索引位置
// sb.delete(sb.length() - 2, sb.length());    // 删除倒数两个字符 起始索引->结束索引
String s = sb.toString();                      // StringBuilder -> String

// 虽然可以直接拼接字符串，但是，在循环中，每次循环都会创建新的字符串对象，然后扔掉旧的字符串。这样，绝大部分字符串都是临时对象，不但浪费内存，还会影响GC效率
String s = "";
for (int i = 0; i < 1000; i++) {
    s = s + "," + i;
}

// StringBuilder和StringBuffer接口完全相同，现在完全没有必要使用StringBuffer
```



包装类

```java
// 基本类型与对应的引用类型
boolean	   java.lang.Boolean
byte	   java.lang.Byte
short	   java.lang.Short
int	       java.lang.Integer
long	   java.lang.Long
float	   java.lang.Float
double	   java.lang.Double
char	   java.lang.Character

// Auto Boxing 自动装箱 自动拆箱
Integer n = 100; // 编译器自动使用Integer.valueOf(int)
int x = n; // 编译器自动使用Integer.intValue()
// 创建Integer实例
Integer n1 = new Integer(i);           // 通过new操作符创建Integer实例(不推荐使用,会有编译警告)
Integer n2 = Integer.valueOf(i);       // 通过静态方法valueOf(int)创建Integer实例
Integer n3 = Integer.valueOf("100");   // 通过静态方法valueOf(String)创建Integer实例
// parse
int x1 = Integer.parseInt("100");      // 100
int x2 = Integer.parseInt("100", 16);  // 256,因为按16进制解析
str = Integer.toString(100);           // "100",表示为10进制
str = Integer.toHexString(100);        // "64",表示为16进制
// int可表示的最大/最小值:
int max = Integer.MAX_VALUE; // 2147483647
int min = Integer.MIN_VALUE; // -2147483648
```



- 字典

  ```java
  import java.util.HashMap;                                 // 引入HashMap类
  
  // 声明与创建字典（Map）
  HashMap<Integer, String> dict = new HashMap<Integer, String>();
  ```

  ```java
  // 查
  size = dict.size();                                      // 返回k-v的数量
  value = dict.get(key);                                   // 获取指定key对应对的value
  value = dict.getOrDefault(key, "not found");             // 找不到key，可自定义默认值返回
  for (Entry<String, Integer> entry : dict.entrySet()) {}  // entry(条目)(k-v) set集合视图
  for (int key : dict.keySet()) {}                         // key set集合视图
  for (int value : dict.values()) {}                       // value集合视图
  
  // 增
  dict.put(ket, value);                                   // 插入键值对
  dict2.putAll(dict1);                                    // 从另一个字典的键值对全部插入进来
  dict2 = dict1.clone();                                  // 浅拷贝
  
  // 改
  dict.replace(key, newValue);                            //替换指定的key对应的value
  dict.replaceAll((key, value) -> value.toUpperCase());   // 使用映射函数 与forEach()区别？
  dict.forEach((key, value) -> { value = value - value * 10/100; }); // k-v action 
  dict.compute(key, (key, value) -> value - value * 10/100);         // 指定key 重算value
  
  // 删
  dict.remove(key);                                       // 删除指定键key的映射关系
  dict = dict.clear();                                    // 清空
  
  // 判断
  isEmpty = dict.isEmpty();                               // 判空
  isContain = dict.containsKey(key);                      // 是否存在指定的key对应的映射关系
  isContain = dict.containsValue(value);                  // 是否存在指定Value对应的映射关系
  ```

  

- Set（集合）

  ```java
  import java.util.HashSet;                       // 引入HashSet类 
  
  HashSet<String> set = new HashSet<String>();    // 声明与创建Set
  ```

  ```java
  // 增
  isOk = set.add(data);                           // 如果指定元素尚不存在，则将其添加到此集合中
  
  // 删
  isOk = set.remove(data);                        // 删除指定元素
  set.clear();                                    // 清空
  
  // 查
  len = set.size();                               // 集合大小
  for (int i : set) {}                            // 遍历
   
  // 判断
  isContains = set.contains(data);                // 判断是否包含指定元素
  isEmpty = set.isEmpty();                        // 判空
  ```

  

- ArrayList（动态数组）

  ```java
  import java.util.ArrayList;                     // 引入 ArrayList 类
  
  ArrayList<E> objectName =new ArrayList<>();　   // 初始化
  ```

  ```java
  
  ```

  

- LinkedList（双向链表）

  ```java
  import java.util.LinkedList;                  // 引入LinkedList类
  
  // 声明与创建LinkedList
  LinkedList<E> list = new LinkedList<E>();      // 普通创建方法
  ```

  ```java
  // 链表与数组转换
  Object[] arr = list.toArray();               // 链表 -> 数组
  String[] arr = list.toArray(new String[0]);   // 指定类型
  Integer[] arr = list.toArray(new Integer[0]);
    
  // 增
  list.add(data);                               // 插入元素
  list.add(index, data);                        // 指定索引位置插入
  list.addFirst(data);                          // 插入头部
  list.addLast(data);                           // 插入尾部
  list.addAll(list2);                           // 插入一组集合元素
  isOk = list.offer(data);                      // 等同于add() 队列接口
  isOk = list.offerFirst(data);       
  isOk = list.offerLast(data);       
  
  // 删
  list.clear();                                 // 清空链表
  data = list.remove();                         // 删除第一个元素
  isOk = list.remove(Integer data);             // 删除指定元素 注：包装类
  data = list.remove(int index);                // 删除指定位置的元素
  data = list.removeFirst();                    // 等同于remove()
  data = list.removeLast();                     // 删除最后一个元素
  data = list.poll();                           // 等同于remove()，删除第一个元素 队列接口
  
  // 改
  list.set(index, data);             			  // 设置指定位置的元素
  
  // 查
  len = list.size();               			  // 链表长度
  data = list.get(index);          		      // 返回指定索引位置的元素
  data = list.getFirst();                       // 返回第一个元素
  data = list.getLast();				          // 返回最后一个元素
  data = list.element();                        // 等同于getFirst()
  data = list.peek();                           // 等同于element() 队列接口
  index = list.indexOf(data);                   // 查找指定元素从前往后第一次出现的索引
  index = list.lastIndexOf(data);               // 查找指定元素从后往前第一次出现的索引
  
  // 判断
  isContains = list.contains(data);             // 是否包含某一元素
  ```

  

- 栈

  ```java
  import java.util.Stack;                      // 引入Stack类
  
  Stack<Integer> st = new Stack<Integer>();    // 声明与创建栈
  ```

  ```java
  // 栈的相关操作
  len = st.size();                             // 栈的长度
  isEmpty = st.empty();                        // 判空
  data = st.peek();                            // 访问顶部元素，不移除
  data = st.pop();                             // 出栈
  st.push(data);                               // 入栈
  str = st.toSting();                          // 转为字符串
  ```



- 队列

  ```java
  // LinkedList类实现了Queue接口，因此我们可以把LinkedList当成Queue来用
  
  // 引入LinkedList类、Queue类
  import java.util.LinkedList;
  import java.util.Queue;
  
  Queue<Integer> qu = new LinkedList<Integer>();   // 声明与创建队列
  ```
  
  ```java
  // 队的相关操作
  qu.offer(data);                                  // 入队                   add()
  data = qu.poll();                                // 出队                   remove()
  data = qu.peek();                                // 返回第一个元素，不出队    element()
  ```
  



- 自定义数据结构

  ```java
  // 单链表定义
  public class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }
  ```



枚举类

```java
// https://www.liaoxuefeng.com/wiki/1252599548343744/1260473188087424

public enum Color {                               // 枚举类定义
    RED, GREEN, BLUE;                             // RED, GREEN, BLUE其实都是Color的实例
}

// 编译器编译出的class大概就像这样
// public final class Color extends Enum { // 继承自Enum，标记为final class
//    // 每个实例均为全局唯一:
//    public static final Color RED = new Color();
//    public static final Color GREEN = new Color();
//    public static final Color BLUE = new Color();
//    // private构造方法，确保外部无法调用new操作符:
//    private Color() {}
// }

public class Test {
    // 执行输出结果
    public static void main(String[] args) {
        Color c1 = Color.RED;
        System.out.println(c1);
    }
}

String s = Weekday.SUN.name();         // "SUN" 返回常量名
int n = Weekday.MON.ordinal();         // 1 返回定义常量的顺序

```



BigInteger

```java
// 使用的整数范围超过了long型
// BigInteger内部用一个int[]数组来模拟一个非常大的整数
// 对BigInteger做运算的时候，只能使用实例方法，例如，加法big.add(3)
```



BigDecimal

```

```



Math类

```java
// Math类
import java.util.Math
    
x_abs = Math.abs(x);                     // |x|
x_sqrt = Math.sqrt(x);                   // 根号x
x_pow3 = Math.pow(x, 3);                 // x^3

x = Math.round(x);                       // 四舍五入：Math.floor(x + 0.5)
x = Math.ceil(x);                        // 向上取整
x = Math.floor(x);                       // 向下取整

min = Math.min(x, y);                    // 两者的最小值
max = Math.max(x, y);                    // 两者的最大值

double x_random = Math.random();         // 返回一个[0,1]随机数
```

- Number类

  ```java
  // Number类：Integet、Double、Float
  str = Number.toSting(x);                 // 以字符串形式返回值
  dtype x = Number.praseDtype(str);        // 将字符串解析为dtype，如Double.praseDouble(str)
  
  Number x;
  dtype x = x.dtypeValue();                // 拆箱
  
  dtype x; 
  Number x = Number.valueOf(x);            // 装箱
  ```



#### 异常

```java
// try...catch
import java.io.UnsupportedEncodingException;               // 异常类
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        byte[] bs = toGBK("中文");
        System.out.println(Arrays.toString(bs));
    }

    static byte[] toGBK(String s) {
        try {
            // 用指定编码转换String为byte[]:
            return s.getBytes("GBK");
        } catch (UnsupportedEncodingException e) {
            // 如果系统不支持GBK编码，会捕获到UnsupportedEncodingException:
            System.out.println(e); // 打印异常信息
            return s.getBytes(); // 尝试使用用默认编码
        }
    }
}

// 如果我们不捕获UnsupportedEncodingException，会出现编译失败的问题
// 像UnsupportedEncodingException这样的Checked Exception，必须被捕获。
// 这是因为String.getBytes(String)方法定义是：
public byte[] getBytes(String charsetName) throws UnsupportedEncodingException {
    ...
}
// 在方法定义的时候，使用throws Xxx表示该方法可能抛出的异常类型。调用方在调用的时候，必须强制捕获这些异常，否则编译器会报错。
```

```java
// try...catch...catch...finally
public static void main(String[] args) {
    try {
        process1();
        process2();
        process3();
    } catch (UnsupportedEncodingException e) {  
    	// UnsupportedEncodingException是IOException的子类，必须写在它前面 
        System.out.println("Bad encoding");
    } catch (IOException e) {
        System.out.println("IO error");
    } finally {
        // finally最后执行
        System.out.println("END");
    }
}
```

```java
// 异常屏蔽
// 在catch中抛出异常，不会影响finally的执行。JVM会先执行finally，然后抛出异常。

public class Main {
    public static void main(String[] args) {
        try {
            Integer.parseInt("abc");
        } catch (Exception e) {
            System.out.println("catched");
            throw new RuntimeException(e);      // 该异常不会被抛出，因为fianlly处抛出了异常
        } finally {
            System.out.println("finally");
            throw new IllegalArgumentException(); 
        }
    }
}

```

```java
// 自定义异常
public class BaseException extends RuntimeException {
    public BaseException() {
        super();
    }

    public BaseException(String message, Throwable cause) {
        super(message, cause);
    }

    public BaseException(String message) {
        super(message);
    }

    public BaseException(Throwable cause) {
        super(cause);
    }
}
```

```java
// 养成良好编程规范，避免NullPointerException
// 变量初始化：
private String name = "";
// 返回空数组而不是null:
if (getFileSize(file) == 0) {
	return new String[0];
}
```

```java
// 断言 assert
```

```java
// 日志
```



#### 泛型

```java
// 泛型是一种“代码模板”，可以用一套代码套用各种类型，例如ArrayList<T>
// T可以是任何class。这样一来，我们就实现了：编写一次模版，可以创建任意类型的ArrayList
ArrayList<String> strList = new ArrayList<String>();

// 注意泛型的继承关系：可以把ArrayList<Integer>向上转型为List<Integer>（T不能变！），但不能把ArrayList<Integer>向上转型为ArrayList<Number>（T不能变成父类）。
// 可以省略后面的Number，编译器可以自动推断泛型类型：
List<Number> list = new ArrayList<>();
```

```java
// 编写泛型
public class Pair<T, K> {
    private T first;
    private K last;
    public Pair(T first, K last) {
        this.first = first;
        this.last = last;
    }
    public T getFirst() { ... }
    public K getLast() { ... }
}
```

```java
// Java泛型的实现方式：擦拭法
// Java的泛型是由编译器在编译时实行的，编译器内部永远把所有类型T视为Object处理，但是，在需要转型的时候，编译器会根据T的类型自动为我们实行安全地强制转型。

```

```java
// Pair<Integer>不是Pair<Number>的子类

// 使用类似<T extends Number>定义泛型类时表示：泛型类型限定为Number以及Number的子类。
// 使用extends通配符表示可以读，不能写。

// 使用类似<T super Number>定义泛型类时表示：泛型类型限定为Number以及Number的父类。
// 即使用super通配符表示只能写不能读。

// 如果需要返回T，它是生产者（Producer），要使用extends通配符；如果需要写入T，它是消费者（Consumer），要使用super通配符。
public class Collections {
    public static <T> void copy(List<? super T> dest, List<? extends T> src) {
        for (int i=0; i<src.size(); i++) {
            T t = src.get(i);                         // src是producer
            dest.add(t);                              // dest是consumer
        }
    }
}

```





#### 反射

```
JVM为每个加载的class及interface创建了对应的Class实例来保存class及interface的所有信息；
获取一个class对应的Class实例后，就可以获取该class的所有信息；
通过Class实例获取class信息的方法称为反射（Reflection）；
JVM总是动态加载class，可以在运行期根据条件来控制加载class。

```



#### 注解

```java
public class Hello {
    @Check(min=0, max=100, value=55)
    public int n;

    @Check(value=99) // @Check(99)
    public int p;

    @Check
    public int y;
}
```



#### 多线程

```
Java语言内置了多线程支持：一个Java程序实际上是一个JVM进程，JVM进程用一个主线程来执行main()方法，在main()方法内部，我们又可以启动多个线程。此外，JVM还有负责垃圾回收的其他工作线程等。

因此，对于大多数Java程序来说，我们说多任务，实际上是说如何使用多线程实现多任务。
```

```java
// 创建线程 t.start();
public class Main {
    public static void main(String[] args) {
        System.out.println("main start...");
        Thread t = new Thread() {
            public void run() {
                System.out.println("thread run...");
                System.out.println("thread end.");
            }
        };
        t.start();
        System.out.println("main end...");
    }
}
我们用蓝色表示主线程，也就是main线程，main线程执行的代码有4行，首先打印main start，然后创建Thread对象，紧接着调用start()启动新线程。当start()方法被调用时，JVM就创建了一个新线程，我们通过实例变量t来表示这个新线程对象，并开始执行。
除了可以肯定，main start会先打印外，main end打印在thread run之前、thread end之后或者之间，都无法确定。
```

```
// 线程状态
状态转移图
         ┌─────────────┐
         │     New     │
         └─────────────┘
                ▼
┌ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ┐
 ┌─────────────┐ ┌─────────────┐
││  Runnable   │ │   Blocked   ││
 └─────────────┘ └─────────────┘
│┌─────────────┐ ┌─────────────┐│
 │   Waiting   │ │Timed Waiting│
│└─────────────┘ └─────────────┘│
 ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─
                ▼
         ┌─────────────┐
         │ Terminated  │
         └─────────────┘
运行中的线程：
Runnable：正在执行run()方法的Java代码；
Blocked：因为某些操作被阻塞而挂起；
Waiting：因为某些操作在等待中；
Timed Waiting：因为执行sleep()方法正在计时等待；
```

```
// 一个线程还可以等待另一个线程直到其运行结束。例如，main线程在启动t线程后，可以通过t.join()等待t线程结束后再继续运行：
public class Main {
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(() -> {
            System.out.println("hello");
        });
        System.out.println("start");
        t.start();
        t.join();
        System.out.println("end");
    }
}
当main线程对线程对象t调用join()方法时，主线程将等待变量t表示的线程运行结束，即join就是指等待该线程结束，然后才继续往下执行自身线程。所以，上述代码打印顺序可以肯定是main线程先打印start，t线程再打印hello，main线程最后再打印end。
```

```
// 中断线程
中断线程就是其他线程给该线程发一个信号，该线程收到信号后结束执行run()方法，使得自身线程能立刻结束运行。
对目标线程调用interrupt()方法可以请求中断一个线程，目标线程通过检测isInterrupted()标志获取自身是否已中断。如果目标线程处于等待状态，该线程会捕获到InterruptedException，按异常处理；

// main线程通过调用t.interrupt()从而通知t线程中断，而此时t线程正位于hello.join()的等待中，此方法会立刻结束等待并抛出InterruptedException。由于我们在t线程中捕获了InterruptedException，因此，就可以准备结束该线程。在t线程结束前，对hello线程也进行了interrupt()调用通知其中断。如果去掉这一行代码，可以发现hello线程仍然会继续运行，且JVM不会退出。

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Thread t = new MyThread();
        t.start();
        Thread.sleep(1000);
        t.interrupt(); // 中断t线程
        t.join(); // 等待t线程结束 hello.interrupt();
        System.out.println("end");
    }
}

class MyThread extends Thread {
    public void run() {
        Thread hello = new HelloThread();
        hello.start(); // 启动hello线程
        try {
            hello.join(); // 等待hello线程结束
        } catch (InterruptedException e) {
            System.out.println("interrupted!");
        }
        hello.interrupt();
        System.out.println("MyThread end!");    // 捕获InterruptedException后，正常运行至结束
    }
}

class HelloThread extends Thread {
    public void run() {
        int n = 0;
        while (!isInterrupted()) {
            n++;
            System.out.println(n + " hello!");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                break;
            }
        }
    }
}


通过标志位判断需要正确使用volatile关键字；volatile关键字解决了共享变量在线程间的可见性问题。
┌ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ┐
           Main Memory
   ┌───────┐┌───────┐┌───────┐
│  │ var A ││ var B ││ var C │  │
   └───────┘└───────┘└───────┘
│     │ ▲               │ ▲     │
 ─ ─ ─│─│─ ─ ─ ─ ─ ─ ─ ─│─│─ ─ ─
┌ ─ ─ ┼ ┼ ─ ─ ┐   ┌ ─ ─ ┼ ┼ ─ ─ ┐
      ▼ │               ▼ │
│  ┌───────┐  │   │  ┌───────┐  │
   │ var A │         │ var C │
│  └───────┘  │   │  └───────┘  │
   Thread 1          Thread 2
└ ─ ─ ─ ─ ─ ─ ┘   └ ─ ─ ─ ─ ─ ─ ┘
```

```
// 守护线程
守护线程是为其他线程服务的线程（处于无限循环）；
所有非守护线程都执行完毕后，虚拟机退出（不在乎是否存在守护线程）；
守护线程不能持有需要关闭的资源（如打开文件等）。
Thread t = new MyThread();
t.setDaemon(true);
t.start();
```

```
// 线程同步
这说明多线程模型下，要保证逻辑正确，对共享变量进行读写时，必须保证一组指令以原子方式执行：即某一个线程执行时，其他线程必须等待：
保证一段代码的原子性就是通过加锁和解锁实现的。Java程序使用synchronized关键字对一个对象进行加锁：

多线程同时读写共享变量时，会造成逻辑错误，因此需要通过synchronized同步；
同步的本质就是给指定对象加锁，加锁后才能继续执行后续代码；
注意加锁对象必须是同一个实例；
synchronized(Counter.lock) { // 获取锁
    ...
} // 释放锁
```

```
如果一个类被设计为允许多线程正确访问，我们就说这个类就是“线程安全”的（thread-safe）
用synchronized修饰的方法就是同步方法，它表示整个方法都必须用this实例加锁
public class Counter {
    private int count = 0;

    public void add(int n) {
        synchronized(this) {
            count += n;
        }
    }
    
    // 等价写法 
	public synchronized void add(int n) { // 锁住this
        count += n;
    } // 解锁
    
    public void dec(int n) {
        synchronized(this) {
            count -= n;
        }
    }

    public int get() {
        return count;
    }
}

如果对一个静态方法添加synchronized修饰符，它锁住的是哪个对象？
public synchronized static void test(int n) {
    ...
}
public static void test(int n) {
    synchronized(Counter.class) {
   		...
    }
}
对于static方法，是没有this实例的，因为static方法是针对类而不是实例。但是我们注意到任何一个类都有一个由JVM自动创建的Class实例，因此，对static方法添加synchronized，锁住的是该类的Class实例。
```

```
// 死锁
JVM允许同一个线程重复获取同一个锁，这种能被同一个线程反复获取的锁，就叫做可重入锁。
死锁产生的条件是多线程各自持有不同的锁，并互相试图获取对方已持有的锁，导致无限等待；
避免死锁的方法是多线程获取锁的顺序要一致。
```

```
// wait和notify 用于多线程协调运行：
在synchronized内部可以调用wait()使线程进入等待状态；必须在已获得的锁对象上调用wait()方法；
在synchronized内部可以调用notify()或notifyAll()唤醒其他等待线程；必须在已获得的锁对象上调用notify()或notifyAll()方法；
用notifyAll()将唤醒所有当前正在this锁等待的线程，而notify()只会唤醒其中一个（具体哪个依赖操作系统，有一定的随机性）
```

```
// ReentrantLock
ReentrantLock可以替代synchronized进行同步；
必须先获取到锁，再进入try {...}代码块，最后使用finally保证释放锁；
ReentrantLock获取锁更安全；
可以使用tryLock()尝试获取锁，线程在tryLock()失败的时候不会导致死锁。
```



#### Maven

```
https://www.liaoxuefeng.com/wiki/1252599548343744/1255945359327200
1.标准目录结构
2.标准且自动的包依赖管理
3.标准化的构建流程，可以自动化实现编译，打包，发布，等等

Maven是一个Java项目的管理和构建工具
Maven使用pom.xml定义项目内容，并使用预设的目录结构；
在Maven中声明一个依赖项可以自动下载并导入classpath；
Maven使用groupId，artifactId和version唯一定位一个依赖。
```

```
一个使用Maven管理的普通的Java项目，它的目录结构默认如下：
a-maven-project
├── pom.xml
├── src
│   ├── main
│   │   ├── java
│   │   └── resources
│   └── test
│       ├── java
│       └── resources
└── target
项目的根目录a-maven-project是项目名，它有一个项目描述文件pom.xml，存放Java源码的目录是src/main/java，存放资源文件的目录是src/main/resources，存放测试源码的目录是src/test/java，存放测试资源的目录是src/test/resources，最后，所有编译、打包生成的文件都放在target目录里。

<project ...>
	<modelVersion>4.0.0</modelVersion>
	<groupId>com.itranswarp.learnjava</groupId>
	<artifactId>hello</artifactId>
	<version>1.0</version>
	<packaging>jar</packaging>
	<properties>
        ...
	</properties>
	<dependencies>
        <dependency>
            <groupId>commons-logging</groupId>
            <artifactId>commons-logging</artifactId>
            <version>1.2</version>
        </dependency>
	</dependencies>
</project>
groupId类似于Java的包名，通常是公司或组织名称，artifactId类似于Java的类名，通常是项目名称，再加上version，一个Maven工程就是由groupId，artifactId和version作为唯一标识。我们在引用其他第三方库的时候，也是通过这3个变量确定。例如，依赖commons-logging
```

```
Maven通过解析依赖关系确定项目所需的jar包，常用的4种scope有：compile（默认），test，runtime和provided；
Maven从中央仓库下载所需的jar包并缓存在本地；
可以通过镜像仓库加速下载。
```

```
Maven通过lifecycle、phase和goal来提供标准的构建流程。

最常用的构建命令是指定phase，然后让Maven执行到指定的phase：
mvn clean：清理所有生成的class和jar；
mvn clean compile：先清理，再执行到compile；
mvn clean test：先清理，再执行到test，因为执行test前必须执行compile，所以这里不必指定compile；
mvn clean package：先清理，再执行到package。
通常情况，我们总是执行phase默认绑定的goal，因此不必指定goal。
lifecycle相当于Java的package，它包含一个或多个phase；
phase相当于Java的class，它包含一个或多个goal；
goal相当于class的method，它其实才是真正干活的。
```

```
实际上，执行每个phase，都是通过某个插件（plugin）来执行的
```

```
// 模块管理
Maven支持模块化管理，可以把一个大项目拆成几个模块：

可以通过继承在parent的pom.xml统一定义重复配置；
可以通过<modules>编译多个模块。
```

```
// 发布
```



#### 网络编程

```
只有使用TCP/IP协议的计算机才能够联入互联网

IP地址
IPv4 8*4
IPv6 16*8
IP地址又分为公网IP地址和内网IP地址。公网IP地址可以直接被访问，内网IP地址只能在内网访问。内网IP地址类似于：
192.168.x.x
10.x.x.x
有一个特殊的IP地址，称之为本机地址，它总是127.0.0.1。

IP = 101.202.99.2
Mask = 255.255.255.0
Network = IP & Mask = 101.202.99.0

如果两台计算机计算出的网络号相同，说明两台计算机在同一个网络，可以直接通信。如果两台计算机计算出的网络号不同，那么两台计算机不在同一个网络，不能直接通信，它们之间必须通过路由器或者交换机这样的网络设备间接通信，我们把这种设备称为网关。
网关的作用就是连接多个网络，负责把来自一个网络的数据包发到另一个网络，这个过程叫路由。

域名
因为直接记忆IP地址非常困难，所以我们通常使用域名访问某个特定的服务。域名解析服务器DNS负责把域名翻译成对应的IP，客户端再根据IP地址访问服务器。
有一个特殊的本机域名localhost，它对应的IP地址总是本机地址127.0.0.1。

网络模型
应用层，提供应用程序之间的通信；
表示层：处理数据格式，加解密等等；
会话层：负责建立和维护会话；
传输层：负责提供端到端的可靠传输；
网络层：负责根据目标地址选择路由来传输数据；
链路层和物理层负责把数据进行分片并且真正通过物理网络传输，例如，无线网、光纤等。
```

```
Socket是一个抽象概念，一个应用程序通过一个Socket来建立一个远程连接，而Socket内部通过TCP/IP协议把数据传输到网络
一个Socket就是由IP地址和端口号（范围是0～65535）组成
为什么需要Socket进行网络通信？因为仅仅通过IP地址进行通信是不够的，同一台计算机同一时间会运行多个网络应用程序，例如浏览器、QQ、邮件客户端等。当操作系统接收到一个数据包的时候，如果只有IP地址，它没法判断应该发给哪个应用程序，所以，操作系统抽象出Socket接口，每个应用程序需要各自对应到不同的Socket，数据包才能根据Socket正确地发到对应的应用程序。
使用Socket进行网络编程时，本质上就是两个进程之间的网络通信。其中一个进程必须充当服务器端，它会主动监听某个指定的端口，另一个进程必须充当客户端，它必须主动连接服务器的IP地址和指定端口，如果连接成功，服务器端和客户端就成功地建立了一个TCP连接，双方后续就可以随时发送和接收数据。
因此，当Socket连接成功地在服务器端和客户端之间建立后：

对服务器端来说，它的Socket是指定的IP地址和指定的端口号；
对客户端来说，它的Socket是它所在计算机的IP地址和一个由操作系统分配的随机端口号。

Java标准库提供了ServerSocket来实现对指定IP和指定端口的监听。


Socket流
连接建立后，通过使用 I/O 流在进行通信，每一个socket都有一个输出流和一个输入流，客户端的输出流连接到服务器端的输入流，而客户端的输入流连接到服务器端的输出流。

服 out ---------------> in 客
务                         户
器 in <--------------- out 端

Java标准库使用InputStream和OutputStream来封装Socket的数据流，这样我们使用Socket的流，和普通IO流类似
// 用于读取网络数据:
InputStream in = sock.getInputStream();
// 用于写入网络数据:
OutputStream out = sock.getOutputStream();
```

```
和TCP编程相比，UDP编程就简单得多，因为UDP没有创建连接，数据包也是一次收发一个，所以没有流的概念。

使用UDP协议通信时，服务器和客户端双方无需建立连接：

服务器端用DatagramSocket(port)监听端口；
客户端使用DatagramSocket.connect()指定远程地址和端口；
双方通过receive()和send()读写数据（包）；
DatagramSocket没有IO流接口，数据被直接写入byte[]缓冲区。
```

```
我们把类似Outlook这样的邮件软件称为MUA：Mail User Agent，意思是给用户服务的邮件代理；邮件服务器则称为MTA：Mail Transfer Agent，意思是邮件中转的代理；最终到达的邮件服务器称为MDA：Mail Delivery Agent，意思是邮件到达的代理。电子邮件一旦到达MDA，就不再动了。实际上，电子邮件通常就存储在MDA服务器的硬盘上，然后等收件人通过软件或者登陆浏览器查看邮件。
MTA和MDA这样的服务器软件通常是现成的，我们不关心这些服务器内部是如何运行的。要发送邮件，我们关心的是如何编写一个MUA的软件，把邮件发送到MTA上。

MUA到MTA发送邮件的协议就是SMTP协议，它是Simple Mail Transport Protocol的缩写，使用标准端口25，也可以使用加密端口465或587。

MUA --> MTA  发送邮件至SMTP服务器


```

```
MDA --> MUA  从POP3服务器接收邮件
接收Email则相反，因为邮件最终到达收件人的MDA服务器，所以，接收邮件是收件人用自己的客户端把邮件从MDA服务器上抓取到本地的过程。

接收邮件使用最广泛的协议是POP3：Post Office Protocol version 3，它也是一个建立在TCP连接之上的协议。POP3服务器的标准端口是110，如果整个会话需要加密，那么使用加密端口995。

另一种接收邮件的协议是IMAP：Internet Mail Access Protocol，它使用标准端口143和加密端口993。IMAP和POP3的主要区别是，IMAP协议在本地的所有操作都会自动同步到服务器上，并且，IMAP可以允许用户在邮件服务器的收件箱中创建文件夹。
```

```
HTTP
HTTP请求的格式是固定的，它由HTTP Header和HTTP Body两部分构成。
如果是GET请求，那么该HTTP请求只有HTTP Header，没有HTTP Body。如果是POST请求，那么该HTTP请求带有Body

```



#### IO

```java
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // 创建Scanner对象
        System.out.print("Input your name: "); // 打印提示
        String name = scanner.nextLine(); // 读取一行输入并获取字符串
        System.out.print("Input your age: "); // 打印提示
        int age = scanner.nextInt(); // 读取一行输入并获取整数
        System.out.printf("Hi, %s, you are %d\n", name, age); // 格式化输出
    }
}

// Input your name: Bob
// Input your age: 12
// Hi, Bob, you are 12
```



```java
// 格式化输出
System.out.printf("%.2f\n", d); // 显示两位小数3.14
System.out.printf("Hi, %s, you are %d\n", name, age); 

```



#### Tricks

```java
a / 2 ==> a >> 1                             // 右移 ÷2
a * 2 ==> a << 1                             // 左移 ×2
(a + b) / 2;   ==>   a + ((b - a) >> 1);      // ÷2 注：移位运算符优先级低于加减运算符
swap(a, b);    ==>   a ^= b;                  // a = a ^ b
                     b ^= a;                  // b = b ^ (a ^ b)= a ^ (b ^ b) = a ^ 0 = a
                     a ^= b;                  // a = a ^ (a ^ b) = 0 ^ b = b
1 - x                                        // 0变1 1变0
```





#### 

```shell
# java删除
java -version                                    # 查看预安装的java
whereis java                                     # 查询 Java 被安装在哪些环境下
sudo rm -rf /usr/bin/java                        # 逐一删除
sudo rm -rf /usr/share/java
sudo rm -rf /usr/lib/jvm/java-8-openjdk-amd64/bin/java
sudo rm -rf /usr/share/man/man1/java.1.gz

# java安装
sudo apt-get update                              # 更新apt-get(advanced package tool)
sudo apt-get install openjdk-8-jre               # apt-get install

# java程序运行 
javac HelloWorld.java                            # 编译 .java -> .class
java HelloWorld                                  # 运行 .class

```



其他

```java
Iterator<String> it2 =  tm.keySet().iterator();
it2.next();
```



通识

- java解释器的运行过程

  - [(14条消息) JAVA初学：错误: 找不到或无法加载主类 test_ncc1995的博客-CSDN博客_java test 找不到或无法加载主类](https://blog.csdn.net/ncc1995/article/details/84932759)

  

- 公共池与堆
  - [(15条消息) Java-共享池概念、堆和栈_linchaobeams的博客-CSDN博客_java公共池是什么](https://blog.csdn.net/linchaobeams/article/details/80045153)
  - <img src="./image_fold/Java手册.assets/image-20220429153317872.png" alt="image-20220429153317872" style="zoom:33%;" />

-  String对象不可变 
  - String str = "abc";   // str只是一个引用名，而不是对象本身
  - [Java String 类 | 菜鸟教程 (runoob.com)](https://www.runoob.com/java/java-string.html)

- length() 方法，length 属性和 size() 方法的区别
  -  1、**length()** 方法是针对字符串来说的，要求一个字符串的长度就要用到它的length()方法；
  -  2、**length 属性**是针对 Java 中的数组来说的，要求数组的长度可以用其 length 属性；
  -  3、Java 中的 **size()** 方法是针对泛型集合说的, 如果想看这个泛型有多少个元素, 就调用此方法来查看!

- String 类中 **concat()** 方法和 **+** 号的区别
  - 'concat()'会返回新的字符串对象
  -  ’+‘ 把后面的字符串连到第一个字符串上，不生成新的对象

- Java 转义
  - [java - 为什么在 Java Regex 中需要两个斜杠才能找到 "+"符号？ - IT工具网 (coder.work)](https://www.coder.work/article/859390)

- 集合视图
  - [Java 基础-集合的视图和包装器 - 简书 (jianshu.com)](https://www.jianshu.com/p/462a56f7e349)
  - [(15条消息) java 集合和视图的概念_Paroxetiner的博客-CSDN博客_java视图是什么](https://blog.csdn.net/liyuzhe1998/article/details/86533656)

- java匿名函数

- replaceAll() 与 forEach() 区别
  - ![image-20220502194437324](./image_fold/Java手册.assets/image-20220502194437324.png)
  - [Arraylist.forEach() 和 Arraylist.replaceAll() 的区别 - 堆栈内存溢出 (stackoom.com)](https://stackoom.com/question/4Ic3I)
  - action动作 与 替换

- java 浅拷贝与深拷贝
  - [Java 浅拷贝和深拷贝 - 简书 (jianshu.com)](https://www.jianshu.com/p/94dbef2de298)
  - [学了这么久的java,才知道"深拷贝,浅拷贝"的意思,受益匪浅!02_哔哩哔哩_bilibili](https://www.bilibili.com/video/BV1Lc411h7m7/)
  - 浅拷贝：地址（引用）的拷贝
  
- java的值传递与地址传递
  - [Java中的值传递和地址传递（传值、传引用） - sweet6 - 博客园 (cnblogs.com)](https://www.cnblogs.com/sweet6/p/10510408.html)

- add/offer 

  - [(15条消息) java Queue中 add/offer，element/peek，remove/poll区别_行者小朱的博客-CSDN博客_peek poll](https://blog.csdn.net/u012050154/article/details/60572567)

  - 当超出队列界限的时候，前者**抛出异常让你处理**，后者是**直接返回false**







[对线面试官 (gitee.io)](http://javainterview.gitee.io/luffy/)



题目

```
public class Main {
    public static void main(String[] args) {
        String[] names = {"ABC", "XYZ", "zoo"};
        String s = names[1];
        names[1] = "cat";
        System.out.println(s); // s是"XYZ"还是"cat"?  "XYZ"
    }
}
```



单例

![image-20230414112632649](image_fold/Java手册.assets/image-20230414112632649.png)
