package spring.project.employee.service.impl;

import org.springframework.stereotype.Service;
import spring.project.employee.exception.ResourceNotFoundException;
import spring.project.employee.model.Employee;
import spring.project.employee.repository.EmployeeRepository;
import spring.project.employee.service.EmployeeService;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        super();
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getEmployeeById(Long id) {
//       Optional <Employee> employee= employeeRepository.findById(id);
//       if(employee.isPresent()){
//           return employee.get();
//       }
//       else {
//           throw new ResourceNotFoundException("Employee","Id",id);
//       }
        return employeeRepository.findById(id).orElseThrow(()->
                         new ResourceNotFoundException("Employee","Id",id));
    }

    @Override
    public Employee updateEmployee(Employee employee, Long id) {
        // we need to check wheather given id is exist in DB or not
        Employee existingEmployee = employeeRepository.findById(id).orElseThrow(() ->
                         new ResourceNotFoundException("Employee","Id",id));
        existingEmployee.setFirstName(employee.getFirstName());
        existingEmployee.setLastName(employee.getLastName());
        existingEmployee.setEmail(employee.getEmail());
        // save existing employee to DB
        employeeRepository.save(existingEmployee);
        return existingEmployee;
    }

    @Override
    public void deleteEmployee(Long id) {
        // check wheather a employee exist in a DB or not
        employeeRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Employee","Id",id));
        employeeRepository.deleteById(id);


    }
}
