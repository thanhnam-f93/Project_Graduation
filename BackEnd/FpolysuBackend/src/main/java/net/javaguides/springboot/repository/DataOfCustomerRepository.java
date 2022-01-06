package net.javaguides.springboot.repository;

import net.javaguides.springboot.entity.DataOfCustomer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository


public interface DataOfCustomerRepository extends JpaRepository<DataOfCustomer, Integer> {

//    @Query("from DataOfCustomer")
//    List<DataOfCustomer> listDataOfCustomer();

    @Query(value = "from DataOfCustomer where fullName like concat('%',:fullname,'%') ")
    List<DataOfCustomer> getDataOfCustomerByFullName(@Param("fullname") String fullname);

    @Query(value = "from DataOfCustomer d where d.users.email=:email")
    List<DataOfCustomer> getDataOfCustomersByUsers(String email);


}
