package com.neutron.crm.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;


@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@Entity
@Table(name = "orders")
public class Order extends AbstractAuditedEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(insertable = true, updatable = false, unique = true, nullable = false)
    @EqualsAndHashCode.Include
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "customer_id", nullable = false)
    @EqualsAndHashCode.Include
    private Customer customer;

    @Column(nullable = false)
    @EqualsAndHashCode.Include
    private LocalDate date;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "order", cascade = CascadeType.ALL)
    private Set<OrderLine> orderLines;

    public Order(final Long id, final Customer customer, final LocalDate date) {
        this.id = id;
        this.customer = customer;
        this.date = date;
    }

    public Integer getTotalQuantity() {
        return getOrderLines().stream().mapToInt(OrderLine::getQuantity).sum();
    }

    public Double getTotalPrice() {
        return getOrderLines()
                .stream().mapToDouble(orderLine -> orderLine.getProduct().getPrice() * orderLine.getQuantity()).sum();
    }
}
