package net.javaguides.springboot.repository;

import net.javaguides.springboot.entity.ServiceFee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceFeeRepository extends JpaRepository<ServiceFee, Integer> {
    ServiceFee findServiceFeeByPrice(double price);

}
