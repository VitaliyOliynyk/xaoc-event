Welcome to xaoc-event project
-----------------------------

Current version is 1.0.1

Spring components can interact by simply calling each others methods. But to enable components to interact in a more loosely-coupled fashion than is possible when the components call each others methods directly, xaoc-event provides component-driven events


Quick start
===========

-   Step 1 add maven dependency:
```xml
    <dependency>   
        <artifactId>xaoc-event</artifactId>
        <groupId>eu.vitaliy</groupId>
        <artifactId>xaoc-event</artifactId>
        <version>1.0.1</version>
    </dependency>
```
-   Step 2 add <xe:init/>(with namespace declaration) to spring configuration file
```xml
    <?xml version="1.0" encoding="UTF-8"?>
    <beans xmlns="http://www.springframework.org/schema/beans"
           xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
           xmlns:xe="http://vitaliy.eu/schema/xaocevent"
     
           xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans-3.0.xsd
              http://vitaliy.eu/schema/xaocevent http://vitaliy.eu/schema/xaocevent/xaocevent.xsd">
        ...
        <xe:init/>
        ...
    </beans>
```
-   Use it like this:
```java
    public interface IBean1 {
        String eventSender(String s);
    }

    public interface IBean2 {}

    @Component("bean1")
    public class Bean1 implements IBean1{

        @Observable("event1")
        public String eventSender(String s){
           return  "Hello, world!";
        }

    }

    @Component("bean2")
    public class Bean2 implements IBean2{

        @Observer("event1")
        private void eventReceiverWithArguments(String s) {
            System.out.println("receiver event 'event1' with argument" + s);
        }

        @Observer("event1")
        private void eventReceiverWithoutArguments() {
            System.out.println("receiver event 'event1');
        }

    }
```
-   If you are not using Maven (Please refer to the Project Dependencies for exact list and version numbers)

```bash
    +- org.springframework:spring-beans:jar:3.0.5.RELEASE
    |  \- org.springframework:spring-core:jar:3.0.5.RELEASE
    |     \- commons-logging:commons-logging:jar:1.1.1
    +- org.springframework:spring-context:jar:3.0.5.RELEASE
    |  +- org.springframework:spring-aop:jar:3.0.5.RELEASE
    |  |  \- aopalliance:aopalliance:jar:1.0
    |  +- org.springframework:spring-expression:jar:3.0.5.RELEASE
    |  \- org.springframework:spring-asm:jar:3.0.5.RELEASE
    +- org.aspectj:aspectjweaver:jar:1.6.10
    +- org.aspectj:aspectjrt:jar:1.6.10
```
