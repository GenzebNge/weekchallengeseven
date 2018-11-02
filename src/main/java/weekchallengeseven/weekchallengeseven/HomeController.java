package weekchallengeseven.weekchallengeseven;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class HomeController {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    DepartmentRepository departmentRepository;


    @RequestMapping("/")
    public String homePage(){
        return "homepage";
    }

    @RequestMapping("/departmentform")
    public String addDepartment(Model model){
        model.addAttribute("department", new Department());
        return "departmentform";
    }

    @PostMapping("/processdepartment")
    public String processDepartment(@Valid Department department, BindingResult result){
        if (result.hasErrors()){
            return "departmentform";
        }
        departmentRepository.save(department);
        return "homepage";
    }

    @RequestMapping("/employeeform")
    public String addEmployee(Model model){
        model.addAttribute("thisemployee", new Employee());
        model.addAttribute("departments", departmentRepository.findAll());
        return "employeeform";
    }

    @PostMapping("/processemployee")
    public String processEmployee(@Valid Employee employee, BindingResult result){
        if (result.hasErrors()){
            return "employeeform";
        }
        employeeRepository.save(employee);
        return "homepage";
    }

    @RequestMapping("/employeelist")
    public String listEmployees(Model model){
        model.addAttribute("departments", departmentRepository.findAll());
        model.addAttribute("employees", employeeRepository.findAll());
        return "employeelist";
    }

    @RequestMapping("/departmentlist")
    public String listDepartment(Model model){
        model.addAttribute("departments",departmentRepository.findAll());
        return "departmentlist";
    }

    @RequestMapping("/detail{id}")
    public String showdetail(@PathVariable("id") long id, Model model){
        model.addAttribute("thisemployee", employeeRepository.findById(id));
        return "employeedetail";
    }

    @RequestMapping("/update/{id}")
    public String updateCourse(@PathVariable("id") long id, Model model){
        model.addAttribute("departments", departmentRepository.findAll());
        model.addAttribute("employee", employeeRepository.findById(id));
        return "employeeform";
    }

    @RequestMapping("/delete/{id}")
    public String delCourse(@PathVariable("id") long id){
        employeeRepository.deleteById(id);
        return "employeelist";
    }

}
