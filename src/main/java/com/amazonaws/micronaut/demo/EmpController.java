
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
