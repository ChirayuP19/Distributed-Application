package com.pm.shippingservice.repository;

import com.pm.shippingservice.entity.ShippingInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShippingRepository extends JpaRepository<ShippingInfo,Long> {
}
