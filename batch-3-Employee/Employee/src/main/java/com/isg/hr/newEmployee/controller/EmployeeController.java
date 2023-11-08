package com.isg.hr.newEmployee.controller;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cglib.core.ReflectUtils;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.repository.query.Param;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.isg.hr.newEmployee.Responce.ResponseBean;
import com.isg.hr.newEmployee.entity.Employee;
import com.isg.hr.newEmployee.repository.EmployeeRepository;

import io.swagger.v3.oas.annotations.Operation;

@RestController

@RequestMapping("/Employee")
public class EmployeeController {
    private static final Logger log=LogManager.getLogger(EmployeeController.class);
    @Autowired EmployeeRepository emp;
    @Autowired ResponseBean response;


    public final MessageSource messageSource;
    public EmployeeController (MessageSource messageSource)
    {
        this.messageSource=messageSource;
    }

    @GetMapping("/welcome")
    public String welcome(){
        return "Welcome";
    }

    @GetMapping ("/getall")
     @Operation(summary = "returns  the employee information")
    public @ResponseBody ResponseEntity<Object> getEmployee()
    {
        log.info("Start of Get request to fetch list");
        List <Employee> list=emp.findAll();
        System.out.println(list);
        response.setErrorCode(null);
        response.setErrorDesc(null);
        response.setData(list);
        log.info("End of the Get request");
        return new ResponseEntity<Object>(list,HttpStatus.OK);
    }
/* 
@GetMapping("/{empcode}")
    public ResponseEntity<Object> findEmployee(@PathVariable("empcode")String empcode)
    {
        Optional<Employee>foundemp = emp.findById(empcode);
        return new ResponseEntity<Object>(foundemp,HttpStatus.OK);
     }
*/
/* 
@GetMapping("/getemp/{postinid}")
public ResponseEntity <Object> findEmployee(@PathVariable("postinid")Integer postinid)
{
    // Optional <Employee> p=emp.findById( empcode);
    Optional<Employee> find = emp.findBypostinid(postinid);
    return new ResponseEntity<Object>(find,HttpStatus.OK);
}
*/
@GetMapping("/hello")
public String hello()
{
    return "holoword";
}
@GetMapping("/getemp/{empcode}")
@Operation(summary = "Return the Particular Id Employee information")
public ResponseEntity <Object> findEmployee(@PathVariable("empcode")String empcode)
{
    log.info("Start of Get request to fetch employee from -- " +empcode+"empcode");
    // Optional <Employee> p=emp.findById( empcode);
    Optional<Employee> find = emp.findById(empcode);
   if(find.isPresent()){
            response.setErrorCode(null);
            response.setErrorCode(null);
            //response.setData(foundEmp);
            log.info("End of the Get Request");
           return new ResponseEntity<Object>(find,HttpStatus.OK); 
        }
        else{
           String deschMessage = messageSource.getMessage("ERROR_DESC01", null, LocaleContextHolder.getLocale());
           String messageCode = messageSource.getMessage("ERROR_ID01", null, LocaleContextHolder.getLocale());
           response.setErrorCode(messageCode);
           response.setErrorDesc(deschMessage+"---"+empcode); 
           log.info("End of the Get Request");
           return new ResponseEntity<Object>(response, HttpStatus.NOT_FOUND);
        }
}

@PostMapping("/addemployee")

   @Operation(summary = "Add the employee information")
        public  ResponseEntity<Object> addEmployee(@RequestBody Employee addemp ,
                @Param("Createdby") String Createdby){
             log.info("Start of Post request by -- ");
             System.out.println(addemp.toString());
            try{

                
                if(emp.findById(addemp.getEmpcode()).isPresent()){
                    String descMessage = messageSource.getMessage("ERROR_DESC01", null, LocaleContextHolder.getLocale());
                    String messageCode = messageSource.getMessage("ERROR_ID01", null, LocaleContextHolder.getLocale());
                    response.setErrorCode(messageCode);
                    response.setErrorDesc(descMessage);
                    response.setData(null);
                    // emp.save(addemp);
                    log.error(descMessage + " at --" + addemp.getEmpcode() + "empcode");
                    log.info("End of Post request by --"+Createdby );
                    return new ResponseEntity<Object>(response, HttpStatus.BAD_REQUEST);
                }
                else{
                   emp.save(addemp);
                   response.setErrorCode(null);
                   response.setErrorDesc(null);
                   response.setData(addemp);
                   log.debug("Created -- " + addemp);
                   log.info("End of Post request by --"+Createdby );
                   return new ResponseEntity<Object>(response, HttpStatus.CREATED);
                }
            } catch(Exception e){
                return new ResponseEntity<Object>(addemp, HttpStatus.BAD_REQUEST);
            }
    }



@PutMapping("/update/{empcode}")
@Operation(summary = "updates the employee information")
public ResponseEntity<Object>updateemployeee(@PathVariable String empcode , @RequestBody Employee employee,
@Param("modifiedBy")String modifiedBy , @Param("CreatedBy") String Createdby){
    log.info("Start the put Reques");
    try{
    Optional<Employee> foundEmp = emp.findById(empcode);
    if(foundEmp.isPresent()){
         
        Employee updateEmp = foundEmp.get();
        updateEmp.setOnsiteothercountery(employee.getOnsiteothercountery());
        updateEmp.setOnsiteothercounterydesc(employee.getOnsiteothercounterydesc());;
        updateEmp.setProbation(employee.getProbation());
        updateEmp.setStatus(employee.getStatus());
        updateEmp.setRole(employee.getRole());
        updateEmp.setReportingto(employee.getReportingto());
        updateEmp.setDesignationid(employee.getDesignationid());
        updateEmp.setDivision(employee.getDivision());
        updateEmp.setBandid(employee.getBandid());
        updateEmp.setCreatedby(employee.getCreatedby());
       
        response.setErrorCode(null);
        response.setErrorDesc(null);
        response.setData(emp);
        log.debug("Update Employee at"+empcode+"empcode by"+modifiedBy);
        log.info("End of the put request");

        emp.save(updateEmp);
        return new ResponseEntity<Object>("Data Updated" ,HttpStatus.OK);
     }
     else{
        String descMessage = messageSource.getMessage("ERROR_DESC01", null, LocaleContextHolder.getLocale());
        String messageCode = messageSource.getMessage("ERROR_ID01", null, LocaleContextHolder.getLocale());
        response.setErrorCode(messageCode);
        response.setErrorDesc(descMessage);
        response.setData(null);

        log.debug("new Employee created"+empcode+"empcode by"+Createdby);
        log.info("End of the put request");
        return new ResponseEntity<Object>(response ,HttpStatus.NOT_FOUND);
     }
} catch(Exception e){
    
   
    log.error(e.getMessage());
    log.info("End of the put request");
    return new ResponseEntity<Object>("Employee Not found", HttpStatus.NOT_FOUND);

}  
    
}
@DeleteMapping("/delete/{empcode}")
@Operation(summary = "Delete the employee information by its id") 
        public ResponseEntity<Object> deletEmployee(@PathVariable String empcode,
        @Param("modifiedBy") String modifiedBy)
        {  
            log.info("Start delete request");
            if(emp.findById(empcode).isPresent()){

                emp.deleteById(empcode);
                response.setErrorCode(null);
                response.setErrorDesc(null);
                response.setData("deleted");
                log.info("End of the delete Request");
                return new ResponseEntity<Object>(response, HttpStatus.OK);
            }
            else{
            String descMessage = messageSource.getMessage("ERROR_DESC02", null, LocaleContextHolder.getLocale());
            String messageCode = messageSource.getMessage("ERROR_ID02", null, LocaleContextHolder.getLocale());
            response.setErrorCode(messageCode);
            response.setErrorDesc(descMessage);
            log.error(descMessage+"---"+empcode);
            response.setData("deleted");
            log.info("End of the delete Request");

            return new ResponseEntity<Object>(response,HttpStatus.NOT_FOUND);
            }
        }


@PatchMapping("/patchnew/{empcode}")
@Operation(summary = "updates employee information partially")
        public ResponseEntity<Object> patchPerson(@PathVariable String empcode, @RequestBody java.util.Map<Object,Object> perMap,
        @Param("modifiedBy") String modifiedBy ){
            
            log.info("Start patch request");
            Optional<Employee> emOptional = emp.findById(empcode);
            
            if(emOptional.isPresent()){
                Employee employee =emOptional.get();

                        perMap.forEach((key, value)->{
                        Field field = ReflectionUtils.findRequiredField(Employee.class,(String) key);
                        field.setAccessible(true);
                        ReflectionUtils.setField(field, employee,value);
                        emp.save(employee);
                        
                    });
                
                        response.setErrorCode(null);
                        response.setErrorDesc(null);
                        response.setData(null);
                        log.debug("Updated employee present at "+empcode+"empcode by"+modifiedBy);
                        log.info("End of Patch request");
                        return new ResponseEntity<Object>("Update",HttpStatus.OK);
                 }   
                else {

                 String messageCode = messageSource.getMessage("ERROR_ID01", null, LocaleContextHolder.getLocale());
                 String descMessage = messageSource.getMessage("ERROR_DESC01", null, LocaleContextHolder.getLocale());
                 response.setErrorCode(messageCode);
                 response.setErrorDesc(descMessage +"-- "+empcode);
                 log.error(descMessage);
                 response.setData(null);
                 log.info("End of Patch request");
                 return new ResponseEntity<Object>(response,HttpStatus.NOT_FOUND);

                }
    
            }      


}
