# Introduction

-----------------------------------------> Show Image of Application Flow <----------------------------------------------------------
TLDR;
For Running the Application:
In the command line type
```
sh sam-local.sh
```

## Break down of Micronaut-Spring Application:

### Main Class File:

Filename: *Application.java*
```
package com.amazonaws.micronaut.demo;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import io.micronaut.runtime.Micronaut; 

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        Logger rootLogger = (ch.qos.logback.classic.Logger)org.slf4j.LoggerFactory.getLogger(org.slf4j.Logger.ROOT_LOGGER_NAME);
        rootLogger.setLevel(Level.TRACE);
        Micronaut.run(Application.class);
    }
}

```
**slf4j** :  The Simple Logging Facade for Java (SLF4J) serves as a simple facade or abstraction for various logging frameworks. Basically like hibernate, it can be used with any logging framework but has specific methods that translate to these frameworks.

**logback** : Using it with SLF4J for logging the application

**[Micronaut.run](https://docs.micronaut.io/snapshot/api/)** : The main entry point for running a Micronaut application. Run the application for the given arguments.


### Controller:

Filename: *EmpController.java*
```
package com.amazonaws.micronaut.demo;

import com.amazonaws.micronaut.demo.model.Emp;
import com.amazonaws.micronaut.demo.model.EmpData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.Optional;
import java.util.UUID;

@RestController
@EnableWebMvc
public class EmpController {

    private EmpData empData;

    @Autowired
    public EmpController(EmpData emData) {
        empData = emData;
    }


    @RequestMapping(path = "/emp", method = RequestMethod.POST)
    public Emp createEmp(@RequestBody Emp newEmp) {
        if(newEmp.getName() == null || newEmp.getDept() == null){
            return null;
        }

        Emp addEmp = newEmp;
        addEmp.setEmp_id(UUID.randomUUID().toString());
        return addEmp;
    }


    @RequestMapping(path =  "/sayhello ", method = RequestMethod.GET)
    public String sayHello(){
        return  "Hey Hi Bro";
    }

    @RequestMapping(path = "/emp/{emp_id}", method = RequestMethod.GET)
    public Emp singleEmp(@RequestParam("emp_id") String empid) {
        Emp singleEmp = new Emp();
        singleEmp.setDept(empData.getRandomDept());
        singleEmp.setName(empData.getRandomName());
        singleEmp.setEmp_id(empid);
        singleEmp.setSalary(54000);

        return  singleEmp;
    }

    @RequestMapping(path = "/emps", method = RequestMethod.GET)
    public Emp[] getEmpList(@RequestParam("limit") Optional<Integer> limit){
        int queryLimit = 10;
        if (limit.isPresent()) {
            queryLimit = limit.get();
        }

        Emp[] outputEmps = new Emp[queryLimit];
        for(int i =0 ;i< queryLimit; i++){
            Emp newEmp = new Emp();
            newEmp.setEmp_id(UUID.randomUUID().toString());
            newEmp.setName(empData.getRandomName());
            newEmp.setDept(empData.getRandomDept());
            newEmp.setSalary(200000);

            outputEmps[i] = newEmp;
        }

        return outputEmps;
    }
}

```

Our Controller is just like any other Spring Based Controller.

**@RestController**: It's a convenience annotation that combines @Controller and @ResponseBody – which eliminates the need to annotate every request handling method of the controller class with the @ResponseBody annotation. The @ResponseBody basically converts POJO to JSON, when sending back the response.
- Also, Only @RestController semantics are supported, by a Micronaut Application.

**@EnableWebMvc**: The @EnableWebMvc annotation is used for enabling Spring MVC in an application and works by importing the Spring MVC Configuration from WebMvcConfigurationSupport.

Behind the scene, Micronaut works in a very different way, our Spring MVC controllers are computed into Micronaut controllers at compilation time. [For more Info click on the link](https://micronaut-projects.github.io/micronaut-spring/latest/guide/index.html#springMvc)



### BEAN: 

Filename: *Emp.java*
```
package com.amazonaws.micronaut.demo.model;


import io.micronaut.core.annotation.Introspected;

@Introspected
public class Emp {
    private String name, dept, emp_id;
    private float salary;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getEmp_id() {
        return emp_id;
    }

    public void setEmp_id(String emp_id) {
        this.emp_id = emp_id;
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

}

```



**@Introspected**: It is a  **io.micronaut.core** annotation that indicates a type should produce a BeanIntrospection at compilation time. A BeanIntrospection is the result of the compile-time computation of beans properties and annotation metadata.



### Dummy Repository: 

Filename: *EmpData.java*
```

package com.amazonaws.micronaut.demo.model;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Component
public class EmpData {
    private static List<String> dept = new ArrayList<>();
    static {
        dept.add("CS");
        dept.add("Frontend");
        dept.add("DevOps");
        dept.add("Finance");
        dept.add("HR");
        dept.add("HRKC");
    }

    private static List<String> empNames = new ArrayList<>();
    static {
        empNames.add("Dhruvaj");
        empNames.add("Devashish");
        empNames.add("Kunal");
        empNames.add("Indrajeet");
        empNames.add("Tripti");
        empNames.add("Radha");
        empNames.add("Aziz");
        empNames.add("Aman");
        empNames.add("Frenny");
        empNames.add("Zainul");
        empNames.add("Abhay");
        empNames.add("Harsh");
        empNames.add("Jack");
        empNames.add("Sadie");
        empNames.add("Toby");
        empNames.add("Chloe");
        empNames.add("Cody");
        empNames.add("Bailey");
        empNames.add("Buster");
        empNames.add("Lola");
        empNames.add("Duke");
        empNames.add("Zoe");
        empNames.add("Cooper");
        empNames.add("Abby");
        empNames.add("Riley");
        empNames.add("Ginger");
        empNames.add("Harley");
        empNames.add("Roxy");
        empNames.add("Bear");
        empNames.add("Gracie");
        empNames.add("Tucker");
        empNames.add("Coco");
        empNames.add("Murphy");
        empNames.add("Sasha");
        empNames.add("Lucky");
        empNames.add("Lily");
        empNames.add("Oliver");
        empNames.add("Angel");
        empNames.add("Sam");
        empNames.add("Shubham");
        empNames.add("Oscar");
        empNames.add("Emma");
        empNames.add("Teddy");
        empNames.add("Annie");
        empNames.add("Winston");
        empNames.add("Rosie");
    }


    public static List<String> getDept() {
        return dept;
    }

    public static void setDept(List<String> dept) {
        EmpData.dept = dept;
    }

    public static List<String> getEmpNames() {
        return empNames;
    }

    public static void setEmpNames(List<String> empNames) {
        EmpData.empNames = empNames;
    }

    public String getRandomName(){
        return empNames.get(ThreadLocalRandom.current().nextInt(0, empNames.size() - 1));
    }

    public String getRandomDept() {
        return dept.get(ThreadLocalRandom.current().nextInt(0, dept.size() -1));
    }
}
```


**@Component**: Indicates that an annotated class is a "component". Such classes are considered as candidates for auto-detection when using annotation-based configuration and classpath scanning.

### Build Tool(Gradle):

Filename: *build.gradle*:
```
plugins {
    id "java"
    id "com.github.johnrengelman.shadow" version "5.0.0"
    id "application"
    id "net.ltgt.apt-eclipse" version "0.21"
    id "org.springframework.boot" version "2.1.12.RELEASE"
    id "io.spring.dependency-management" version "1.0.6.RELEASE"
}

version "0.1"
group "graal.spring.demo"

repositories {
    mavenCentral()
    maven { url "https://jcenter.bintray.com" }
}

configurations {
    // for dependencies that are needed for development only
    developmentOnly 
}

dependencies {
    annotationProcessor platform("io.micronaut:micronaut-bom:$micronautVersion")
    annotationProcessor "io.micronaut:micronaut-graal"
    annotationProcessor "io.micronaut:micronaut-inject-java"
    annotationProcessor "io.micronaut:micronaut-validation"
    annotationProcessor "io.micronaut.spring:micronaut-spring-boot"
    annotationProcessor "io.micronaut.spring:micronaut-spring-boot-annotation"
    annotationProcessor "io.micronaut.spring:micronaut-spring-web-annotation"
    testAnnotationProcessor "io.micronaut.spring:micronaut-spring-web-annotation"

    compileOnly "com.oracle.substratevm:svm" //Provides SubstrateVM which includes runtime components like he deoptimizer, garbage collector, thread scheduling etc. The resulting program has faster startup time and lower runtime memory overhead compared to a Java VM. We pass values to args to the native-image using the file native-image.properties to finetune the native-image generation.
    implementation platform("io.micronaut:micronaut-bom:$micronautVersion")
    implementation "io.micronaut:micronaut-http-client"
    implementation "io.micronaut:micronaut-inject"
    implementation "io.micronaut:micronaut-validation"
    implementation "io.micronaut:micronaut-runtime" //micronaut runtime gives us io.micronaut.function.aws.runtime.MicronautLambdaRuntime(native-image.properties) for running the application on aws Lambda

    // To deploy your Micronaut function as a GraalVM Native Image you need to select a custom runtime. -> https://micronaut-projects.github.io/micronaut-aws/latest/guide/index.html#applicationtyperuntimedependencies
    implementation("io.micronaut.aws:micronaut-function-aws-custom-runtime:1.3.2") { //for using custom runtime on aws lambda. A runtime is a program that runs a Lambda function’s handler method when the function is invoked. You can include a runtime in your function’s deployment package in the form of an executable file named bootstrap
        exclude group: "com.fasterxml.jackson.module", module: "jackson-module-afterburner"
    }
    implementation("io.micronaut.aws:micronaut-function-aws-api-proxy:1.3.2") { // for using aws-api-proxy 
        exclude group: "com.fasterxml.jackson.module", module: "jackson-module-afterburner"
    }
    developmentOnly "io.micronaut:micronaut-http-server-netty"
    runtimeOnly "ch.qos.logback:logback-classic:1.2.3"
    testAnnotationProcessor platform("io.micronaut:micronaut-bom:$micronautVersion")
    testAnnotationProcessor "io.micronaut:micronaut-inject-java"
    testImplementation platform("io.micronaut:micronaut-bom:$micronautVersion")
    testImplementation "org.junit.jupiter:junit-jupiter-api"
    testImplementation "io.micronaut.test:micronaut-test-junit5"
    testRuntimeOnly "org.junit.jupiter:junit-jupiter-engine"

    // spring support
    compile("org.springframework.boot:spring-boot-starter-web")
    runtime("io.micronaut.spring:micronaut-spring-boot:1.0.1")
    runtime("io.micronaut.spring:micronaut-spring-web:1.0.1")
}

test.classpath += configurations.developmentOnly

mainClassName = "graal.spring.demo.Application"
// use JUnit 5 platform
test {
    useJUnitPlatform()
}

shadowJar {
    mergeServiceFiles()
}

run.classpath += configurations.developmentOnly
run.jvmArgs('-noverify', '-XX:TieredStopAtLevel=1', '-Dcom.sun.management.jmxremote')
tasks.withType(JavaCompile){
    options.encoding = "UTF-8"
    options.compilerArgs.add('-parameters')
}

```

### This is where the magic happens:

Filename: *build.gradle*

**annotationProcessor("io.micronaut:micronaut-graal")**: Is an annotation processor to generate the reflection and resources information at compile time. It has compile dependencies on 
io.micronaut:micronaut-runtime and org.slf4j » slf4j-api.

**compileOnly "com.oracle.substratevm:svm"**:  Provides SubstrateVM which includes runtime components like he deoptimizer, garbage collector, thread scheduling etc. The resulting program has faster startup time and lower runtime memory overhead compared to a Java VM. We pass values to args to the native-image using the file native-image.properties to finetune the native-image generation.

**implementation "io.micronaut:micronaut-runtime"**: micronaut runtime gives us io.micronaut.function.aws.runtime.MicronautLambdaRuntime(native-image.properties) for running the application on aws Lambda


To deploy your Micronaut function as a GraalVM Native Image you need to select a [custom runtime]( https://micronaut-projects.github.io/micronaut-aws/latest/guide/index.html#applicationtyperuntimedependencies).
**implementation("io.micronaut.aws:micronaut-function-aws-custom-runtime:1.3.2")** :for using custom runtime on aws lambda. A runtime is a program that runs a Lambda function’s handler method when the function is invoked. You can include a runtime in your function’s deployment package in the form of an executable file named bootstrap

**implementation("io.micronaut.aws:micronaut-function-aws-api-proxy:1.3.2")**:for using aws-api-proxy 



Now we create a Docker container to run the the __emp-data-0.1-all.jar__ file from the *build/libs* folder on the SVM(SubstrateVM).


### DOCKERFILE

Filename: *Dockerfile*
```
FROM amazonlinux:2017.03.1.20170812 as graalvm 
# install graal to amazon linux base container.
ENV LANG=en_US.UTF-8

#Things required by graal for installation(https://www.graalvm.org/reference-manual/native-image/)
RUN yum install -y gcc gcc-c++ libc6-dev  zlib1g-dev curl bash zlib zlib-devel zip  \
#    && yum install -y libcxx libcxx-devel llvm-toolset-7 \
    && rm -rf /var/cache/yum

# Variables
ENV GRAAL_VERSION 19.2.0.1
ENV GRAAL_FILENAME graalvm-ce-linux-amd64-${GRAAL_VERSION}.tar.gz

# Download Image from github
RUN curl -4 -L https://github.com/oracle/graal/releases/download/vm-${GRAAL_VERSION}/graalvm-ce-linux-amd64-${GRAAL_VERSION}.tar.gz -o /tmp/${GRAAL_FILENAME}

# tar = unzip | -C = change directory | mv move
RUN tar -zxvf /tmp/${GRAAL_FILENAME} -C /tmp \
    && mv /tmp/graalvm-ce-${GRAAL_VERSION} /usr/lib/graalvm

# remove folder/tmp
RUN rm -rf /tmp/*

#install graalVM
RUN /usr/lib/graalvm/bin/gu install native-image

# Copy the from local script/graalvm-build.sh -> docker container's bin folder.
ADD scripts/graalvm-build.sh /usr/local/bin/

# Make the file an executable
RUN chmod +x /usr/local/bin/graalvm-build.sh
VOLUME ["/func"]
WORKDIR /func
ENTRYPOINT ["/usr/local/bin/graalvm-build.sh"]
```

We create a container using Dockerfile and install GraalVm so that we can use SubstrateVM to convert our java-micronaut code, which is compiled AOT to a native binary. It is hard for SVM to compile codes which are reflection-based.

### Building a native binary using SubstrateVM

Filename: *graalvm-build.sh*

```
#!/bin/sh
echo "Startin GraalVM build"

/usr/lib/graalvm/bin/native-image -H:+TraceClassInitialization --initialize-at-build-time=reactor.core.publisher.Mono --initialize-at-build-time=reactor.core.publisher.Flux --no-fallback --no-server -cp /func/build/libs/emp-data-*.jar
rm -rf /func/native-image/*

chmod 755 /func/server
mv /func/server /func/native-image/server
cp /func/scripts/bootstrap /func/native-image/bootstrap
cd /func/native-image && zip -j function.zip bootstrap server
```

The native-image command line needs to provide the classpath for all classes using the familiar option from the java launcher: -cp is followed by a list of directories or .jar files, separated by :. The name of the class containing the main method is the last argument, or you can use -jar and provide a .jar file that specifies the main method in its manifest.

The syntax of the native-image command is:

   - **native-image [options] class** to build an executable file for a class in the current working directory. Invoking it executes the native-compiled code of that class.

   - **native-image [options] -jar jarfile** to build an image for a jar file.


**--initialize-at-build-time**:  A comma-separated list of packages and classes (and implicitly all of their superclasses) that are initialized during image generation. An empty string designates all packages


**reactor.core.publisher.Flux**:  A Reactive Streams Publisher with rx operators that emits 0 to N elements, and then completes (successfully or with an error).

It is intended to be used in implementations and return types. Input parameters should keep using raw Publisher as much as possible.

If it is known that the underlying Publisher will emit 0 or 1 element, **reactor.core.publisher.Mono** should be used instead.

![flux.png](https://res.cloudinary.com/dhruvaj/image/upload/v1600058698/flux_8afb911678.png)

**--no-fallback**: build stand-alone image or report failure


All this


### MAKING THE APPLICATION RUN MICRONAUTLAMBDA RUNTIME TO SERVE USING LAMBDA RUNTIME

Filename: *src/main/resources/META-INF/native-image/graal.spring.demo/graal-spring-demo-application/native-image.properties*

```
Args = -H:IncludeResources=application.yml|log4j2.xml \
       -H:Name=server \
       -H:-AllowVMInspection \
       -H:Class=io.micronaut.function.aws.runtime.MicronautLambdaRuntime #Provides us with MicronautRuntime to Run the application on Lambda

```

 **-H:Class=io.micronaut.function.aws.runtime.MicronautLambdaRuntime**: Provides us with MicronautRuntime to Run the application on Lambda




### Running the Application on AWS Lambda & Local Docker Container.
