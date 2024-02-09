package com.neutron.crm.config.liquibase;

import com.neutron.crm.model.Customer;
import com.neutron.crm.model.Order;
import com.neutron.crm.model.OrderLine;
import com.neutron.crm.model.Product;
import com.neutron.crm.repository.CustomerRepository;
import com.neutron.crm.repository.OrderLineRepository;
import com.neutron.crm.repository.OrderRepository;
import com.neutron.crm.repository.ProductRepository;
import com.neutron.crm.util.CsvDataUtil;
import liquibase.change.custom.CustomTaskChange;
import liquibase.database.Database;
import liquibase.exception.ValidationErrors;
import liquibase.resource.ResourceAccessor;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Slf4j
@Getter
@Setter
@Component
public class OrdersDataLoader implements CustomTaskChange {

    private static final Character CSV_FILE_DELIMITER = ',';

    private String dataFilePath;
    private ResourceAccessor resourceAccessor;

    private ProductRepository productRepository;
    private CustomerRepository customerRepository;
    private OrderLineRepository orderLineRepository;
    private OrderRepository orderRepository;

    public OrdersDataLoader() {
        this.productRepository = BeanAwareSpringLiquibase.getBean(ProductRepository.class);
        this.customerRepository = BeanAwareSpringLiquibase.getBean(CustomerRepository.class);
        this.orderLineRepository = BeanAwareSpringLiquibase.getBean(OrderLineRepository.class);
        this.orderRepository = BeanAwareSpringLiquibase.getBean(OrderRepository.class);
    }

    @SneakyThrows
    @Override
    public void execute(Database database) {
        final InputStream inputStream = getClass().getResourceAsStream(dataFilePath);
        final List<String[]> csvData = CsvDataUtil.getCsvDataFromInputStream(inputStream, CSV_FILE_DELIMITER);
        csvData.removeFirst();
        csvData.forEach(row -> {
            final Long productId = Long.valueOf(row[4]);
            final String productName = String.valueOf(row[5]);
            final Double totalPrice = Double.valueOf(row[7]);
            final Integer quantity = Integer.valueOf(row[6]);
            final Double price = totalPrice / quantity;

            final Optional<Product> optionalProduct = productRepository.findByName(productName);
            final Product product = optionalProduct.orElseGet(() -> productRepository.save(new Product(productId, productName, price)));
            final String customerExternalId = String.valueOf(row[2]);
            final String customerName = String.valueOf(row[3]);
            final Optional<Customer> optionalCustomer = customerRepository.findByName(customerName);
            final Customer customer = optionalCustomer.orElseGet(() -> customerRepository.save(new Customer(customerExternalId, customerName)));
            final Long orderId = Long.valueOf(row[1]);
            final LocalDate orderDate = LocalDate.parse(row[8]);
            final Optional<Order> optionalOrder =
                    orderRepository.findById(orderId);
            final Order order = optionalOrder.orElseGet(() -> orderRepository.save(new Order(orderId, customer, orderDate)));
            final Optional<OrderLine> optionalOrderLine =
                    orderLineRepository.findByOrderAndProduct(order, product);
            optionalOrderLine.orElseGet(() -> orderLineRepository.save(new OrderLine(order, product, quantity)));
        });
    }

    @Override
    public String getConfirmationMessage() {
        return null;
    }

    @Override
    public void setUp() {
    }

    @Override
    public void setFileOpener(ResourceAccessor resourceAccessor) {
        this.resourceAccessor = resourceAccessor;
    }

    @Override
    public ValidationErrors validate(Database database) {
        return null;
    }
}
