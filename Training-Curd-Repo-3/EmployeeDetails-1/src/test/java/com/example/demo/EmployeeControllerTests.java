package com.example.demo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Optional;


import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;



@WebMvcTest
public class EmployeeControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeService employeeService;

   // @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void givenEmployeeObject_whenCreateEmployee_thenReturnSavedEmployee() throws Exception{
    	

        //given
    	Employee employee = new Employee();
    	employee.setFirstName("Alba");
    	employee.setLastName("Bravo");
    	employee.setEmail("albabravo@gmail.com");
        given(employeeService.saveEmployee(any(Employee.class)))
                .willAnswer((invocation)-> invocation.getArgument(0));

        //when
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter objectWriter = mapper.writer().withDefaultPrettyPrinter();
        
        ResultActions response = mockMvc.perform(post("/api/employees")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectWriter.writeValueAsString(employee)));

        // then
        response.andDo(print()).
                andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstName",
                        is(employee.getFirstName())))
                .andExpect(jsonPath("$.lastName",
                        is(employee.getLastName())))
                .andExpect(jsonPath("$.email",
                        is(employee.getEmail())));

    }

    @Test
    public void givenEmployeeId_whenGetEmployeeById_thenReturnEmployeeObject() throws Exception{
        // given 
        long employeeId = 1L;
        Employee employee = new Employee();
         employee.setFirstName("Alba");
         employee.setLastName("Bravo");
         employee.setEmail("albabravo@gmail.com");
        given(employeeService.getEmployeeById(employeeId)).willReturn(Optional.of(employee));

        // when 
        ResultActions response = mockMvc.perform(get("/api/employees/{id}", employeeId));

        // then 
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.firstName", is(employee.getFirstName())))
                .andExpect(jsonPath("$.lastName", is(employee.getLastName())))
                .andExpect(jsonPath("$.email", is(employee.getEmail())));

    }

    
    @Test
    public void givenInvalidEmployeeId_whenGetEmployeeById_thenReturnEmpty() throws Exception{
        // given 
        long employeeId = 1L;
        Employee employee = new Employee();
    	employee.setFirstName("Alba");
    	employee.setLastName("Bravo");
    	employee.setEmail("albabravo@gmail.com");
        given(employeeService.getEmployeeById(employeeId)).willReturn(Optional.empty());

        // when 
        ResultActions response = mockMvc.perform(get("/api/employees/{id}", employeeId));

        // then 
        response.andExpect(status().isNotFound())
                .andDo(print());

    }
    
        @Test
        public void givenUpdatedEmployee_whenUpdateEmployee_thenReturnUpdateEmployeeObject() throws Exception{
            // given
            long employeeId = 1L;
            Employee savedEmployee = new Employee ();
            savedEmployee.setFirstName("Alba");
            savedEmployee.setLastName("Bravo");
            savedEmployee.setEmail("albabravo@gmail.com");

            Employee updatedEmployee = new Employee();
        	updatedEmployee.setFirstName("Delta");
        	updatedEmployee.setLastName("Eco");
        	updatedEmployee.setEmail("deltaeco@gmail.com"); 
            given(employeeService.getEmployeeById(employeeId)).willReturn(Optional.of(savedEmployee));
            given(employeeService.updateEmployee(any(Employee.class)))
                    .willAnswer((invocation)-> invocation.getArgument(0));

            // when 
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
            ObjectWriter objectWriter = mapper.writer().withDefaultPrettyPrinter();
            
            ResultActions response = mockMvc.perform(put("/api/employees/{id}", employeeId)
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .content(objectWriter.writeValueAsString(updatedEmployee)));


            // then 
            response.andExpect(status().isOk())
                    .andDo(print())
                    .andExpect(jsonPath("$.firstName", is(updatedEmployee.getFirstName())))
                    .andExpect(jsonPath("$.lastName", is(updatedEmployee.getLastName())))
                    .andExpect(jsonPath("$.email", is(updatedEmployee.getEmail())));
        }

    
    @Test
    public void givenUpdatedEmployee_whenUpdateEmployee_thenReturn404() throws Exception{
        // given 
        long employeeId = 1L;
        Employee savedEmployee = new Employee ();
        savedEmployee.setFirstName("Alba");
        savedEmployee.setLastName("Bravo");
        savedEmployee.setEmail("albabravo@gmail.com");

        Employee updatedEmployee = new Employee();
    	updatedEmployee.setFirstName("Delta");
    	updatedEmployee.setLastName("Eco");
    	updatedEmployee.setEmail("deltaeco@gmail.com"); 
        given(employeeService.getEmployeeById(employeeId)).willReturn(Optional.empty());
        given(employeeService.updateEmployee(any(Employee.class)))
                .willAnswer((invocation)-> invocation.getArgument(0));

        // when 
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter objectWriter = mapper.writer().withDefaultPrettyPrinter();
        
        ResultActions response = mockMvc.perform(put("/api/employees/{id}", employeeId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectWriter.writeValueAsString(updatedEmployee)));

        // then 
        response.andExpect(status().isNotFound())
                .andDo(print());
    }


   

	@Test
    public void givenEmployeeId_whenDeleteEmployee_thenReturn200() throws Exception{
        // given 
        long employeeId = 1L
        willDoNothing().given(employeeService).deleteEmployee(employeeId);

        // when 
        ResultActions response = mockMvc.perform(delete("/api/employees/{id}", employeeId));

        // then 
        response.andExpect(status().isOk())
                .andDo(print());
    }
}


