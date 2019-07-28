package payroll;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
class LoadDatabase {
    @Bean
    CommandLineRunner initDatabase(EmployeeRepository repository, OrderRepository orderRepository) {
        orderRepository.save(new Order("Macbook Pro", Status.COMPLETED));
        orderRepository.save(new Order("Iphone", Status.IN_PROGESS));
        orderRepository.findAll().forEach(order -> {
            log.info("Preloaded" + order);
        });
        return args -> {
            log.info("Preloading" + repository.save(new Employee("Bilbo", "Baggin", "burglar")));
            log.info("Preloading" + repository.save(new Employee("Frodo", "Baggin", "thief")));
        };
    }
}
