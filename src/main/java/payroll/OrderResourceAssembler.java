package payroll;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.stereotype.Component;

@Component
class OrderResourceAssembler implements ResourceAssembler<Order, Resource<Order>> {
    @Override
    public Resource<Order> toResource(Order order) {
        Resource<Order> orderResource = new Resource<>(order,
                ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(OrderController.class).one(order.getId())).withSelfRel(),
                ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(OrderController.class).all()).withRel("orders")
        );

        if (order.getStatus() == Status.IN_PROGESS) {
            orderResource.add(
                    ControllerLinkBuilder
                            .linkTo(ControllerLinkBuilder
                                    .methodOn(OrderController.class)
                                    .cancel(order.getId()))
                            .withRel("cancel")
            );
            orderResource.add(
                    ControllerLinkBuilder
                            .linkTo(ControllerLinkBuilder
                                    .methodOn(OrderController.class)
                                    .complete(order.getId()))
                            .withRel("complete")
            );
        }
        return orderResource;
    }
}
