package payroll;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.stereotype.Component;

@Component
class EmployeeResourceAssembler  implements ResourceAssembler<Employee, Resource<Employee>> {

    @Override
    public Resource<Employee> toResource(Employee employee) {
        return new Resource<>(employee,
                ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(EmployeeController.class).one(employee.getId())).withSelfRel(),
                ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(EmployeeController.class).all()).withRel("employees")
        );
    }
}
