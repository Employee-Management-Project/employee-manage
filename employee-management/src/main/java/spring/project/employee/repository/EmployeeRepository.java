package spring.project.employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.project.employee.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {
}
