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
