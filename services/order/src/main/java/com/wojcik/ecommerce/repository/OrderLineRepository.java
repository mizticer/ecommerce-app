package com.wojcik.ecommerce.repository;

import com.wojcik.ecommerce.model.OrderLine;
import com.wojcik.ecommerce.model.dto.OrderLineDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderLineRepository extends JpaRepository<OrderLine, Long> {
    List<OrderLine> findAllByOrderId(Long id);
}
