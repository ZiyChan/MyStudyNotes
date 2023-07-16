- 环境搭建
  - java 8（jdk 1.8）
  
- SpringBoot 特性
  - 自动配置
  - 起步依赖
  - 辅助功能

- 快速入门
  1. 创建Maven项目
  2. 导入SpringBoot起步依赖（本质上是一个Maven项目对象模型（Project Object Model，POM））
  3. 定义Controller 
  4. 编写引导类（项目入口，运行main方法就可以启动项目）
  5. 启动测试

- **起步依赖**

  - 在spring-boot-starter-parent中定义了各种技术的版本信息，组合了一套最优搭配的技术版本。
  - 在各种starter中，定义了完成该功能需要的坐标合集，其中大部分版本信息来自于父工程。 
  - 我们的==工程继承==parent，引入starter后，通过==依赖传递==，就可以简单方便获得需要的jar包，并且==不会存在版本冲突==等问题。

- 配置

  - 配置文件类型
    - SpringBoot提供了2种配置文件类型：properteis和yml/yaml 
    - 默认配置文件名称：application 
    - 在同一级目录下优先级为：==.properties > .yml > .yaml==

  - **YAML**（ YAML Ain't Markup Language）

    - 一种数据序列化格式

    - 对比其他数据格式

      - .properties

        ```properties
        server.port=8080
        server.address=127.0.0.1
        ```

      - .xml

        ```xml
        <server>
        <port>8080</port>
        <address>127.0.0.1</address>
        </server>
        ```

      - .yaml/.yml（简洁，以数据为核心）

        ```yaml
        server:
        	port: 8080
        	address: 127.0.0.1
        ```

    - yaml语法

      -  大小写敏感
      -  ==数据值前边必须有空格，作为分隔符==
      -  使用缩进表示层级关系
      -  缩进时不允许使用Tab键，只允许使用空格（各个系统 Tab对应的 空格数目可能不同，导致层次混乱）
      -  缩进的空格数目不重要，只要相同层级的元素左侧对齐即可 
      -  '#' 表示注释，从这个字符一直到行尾，都会被解析器忽略

    - yaml数据格式

      - 对象(map)：键值对的集合

        ```yaml
        person:
        	name: zhangsan
        # 行内写法
        person: {name: zhangsan}、
        ```

      - 数组

        ```yaml
        address:
        	- beijing
        	- shanghai
        # 行内写法
        address: [beijing,shanghai]
        
        ```

      - 纯量：单个的、不可再分的值

        ```yaml
        msg1: 'hello \n world' # 单引忽略转义字符
        msg2: "hello \n world" # 双引识别转义字符
        ```

      - 参数引用

        ```yaml
        name: lisi
        person:
        	name: ${name} # 引用上边定义的name值
        ```

  - 读取配置文件内容（数据注入）
    - @Value
    - Environment
    - @ConfigurationProperties
  - profile
    - 动态切换运行环境：开发（dev）、测试（test）、生产（pro），方便
    - profile配置方式 
      - ==多profile文件方式==：提供多个配置文件，每个代表一种环境：
        - application-dev.properties/yml 开发环境 
        - application-test.properties/yml 测试环境 
        - application-pro.properties/yml 生产环境 
      - yml多文档方式： 在yml中使用 --- 分隔不同配置环境
    - profile激活方式 
      - ==配置文件==： 再配置文件中配置：spring.profiles.active=dev
      - 虚拟机参数：在VM options 指定：-Dspring.profiles.active=dev 
      - 命令行参数：java –jar xxx.jar --spring.profiles.active=dev

  - 配置加载顺序
    - 内部配置加载顺序（优先级）
    - classpath指的是？[SpringBoot项目中的classpath到底指什么? - 孔令翰 - 博客园 (cnblogs.com)](https://www.cnblogs.com/klhans/p/14900059.html)
      1. file:./config/：当前项目下的/config目录下 
      2. file:./ ：当前项目的根目录 
      3. classpath:/config/：classpath的/config目录 
      4. classpath:/ ：classpath的根目录
    - 外部配置加载顺序
      - [Core Features (spring.io)](https://docs.spring.io/spring-boot/docs/current/reference/html/features.html#features.external-config)

- **整合Junit**

  1. 搭建SpringBoot工程 
  2. 引入starter-test起步依赖 
  3. 编写测试类 
  4. 添加测试相关注解
     - @RunWith(SpringRunner.class) 
     - @SpringBootTest(classes = 启动类.class) 

  5. 编写测试方法


- **整合Redis（NoSQL）**
  1.  搭建SpringBoot工程 
  2. 引入redis起步依赖 
  3. 配置redis相关属性 
  4. 注入RedisTemplate模板 
  5. 编写测试方法

- **整合MyBatis（MySQL）**
  1.  搭建SpringBoot工程 
  2. 引入mybatis起步依赖，添加mysql驱动 
  3. 编写DataSource和MyBatis相关配置 
  4. 定义表和实体类 
  5. 编写dao和mapper文件/纯注解开发 
  6. 测试







- Controller层：用于接收和处理HTTP请求，并返回响应结果



注解

`@SpringBootApplication`



`@Component`：将类标识为 Spring 中的 Bean

`@Repository`：  将数据访问层（Dao 层）的类标识为 Spring 中的 Bean，其功能与 @Component 相同

`@Service`：将业务层（Service 层）的类标识为 Spring 中的 Bean，其功能与 @Component 相同

`@Controller`：将控制层（Controller层）的类标识为 Spring 中的 Bean，其中的方法通常返回一个视图对象，例如HTML页面。在Spring MVC中，需要通过视图解析器将视图对象解析为最终的响应内容。





`@RestController`：标识一个类为RESTful控制器，组合了`@Controller`和`@ResponseBody`。其中的方法通常返回数据对象，例如JSON或XML数据。它不需要通过视图解析器来处理视图，方法的返回值直接转为数据输出。





`@RequestMapping("/...")`：用于映射请求路径到特定的处理方法。可以用于类级别和方法级别的映射，常用于定义RESTful API的URI。



@Configuration



@Value("${chatboot-api.cookie}") // application.yml

@Autowired
