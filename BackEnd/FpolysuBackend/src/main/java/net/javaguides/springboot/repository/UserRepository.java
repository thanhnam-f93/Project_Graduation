package net.javaguides.springboot.repository;

import net.javaguides.springboot.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    User findOneByEmail(String email);

    @Query(value = "from User u where u.role = 'admin' or u.role = 'employee'")
    List<User> getUserByRole();

    @Query(value = "select distinct u.role from User u where u.role not in ('customer')")
    List<String> getRole();

    @Query(value = "from User u where u.role = 'customer'")
    List<User> getUserByRoleCustomer();

    @Query(value = "select distinct u.status from User u ")
    List<String> getStatus();

    int countByRole(String role);

    /************ Thống kê khách hàng theo thời gian *******************/
    @Query(value = "select count(u.email)  from User u where u.role = 'customer' and u.createdDate between :start and :end")
    Integer getTotalCustomer(@Param("start") Date start, @Param("end") Date end);

    @Query(value = "select count(*) from users where _role = 'customer' and created_date like CONCAT(:day, '%')", nativeQuery = true)
    Integer statisticCustomerByAllDay(@Param("day") String day);

    @Query(value = "select count(distinct payment_history.email) from users join payment_history on users.email = payment_history.email where users.created_date between :start and :end and payment_history._status = 1 ", nativeQuery = true)
    Integer getTotalCustomerPro(@Param("start") Date start, @Param("end") Date end);

    @Query(value = "select count(distinct payment_history.email) from users join payment_history on users.email = payment_history.email where users.created_date like CONCAT(:day, '%') and payment_history._status = 1 ", nativeQuery = true)
    Integer statisticCustomerProByAllDay(@Param("day") String day);

    @Query(value = "select count(email) from users where _role = 'customer' and email not in (select distinct payment_history.email from users join payment_history on users.email = payment_history.email where users.created_date between :start and :end and payment_history._status = 1)", nativeQuery = true)
    Integer getTotalCustomerBasic(@Param("start") Date start, @Param("end") Date end);

    @Query(value = "select count(email) from users where _role = 'customer' and email not in (select distinct payment_history.email from users join payment_history on users.email = payment_history.email where users.created_date like CONCAT(:day, '%') and payment_history._status = 1)", nativeQuery = true)
    Integer statisticCustomerBasicByAllDay(@Param("day") String day);

    /************ Thống kê doanh thu theo thời gian *******************/
    @Query(value = "select sum(payment_history.total_price) from users join payment_history on users.email = payment_history.email where users.created_date like CONCAT(:day, '%') ", nativeQuery = true)
    Double statisticRevenueByAllDay(@Param("day") String day);

    @Query(value = "select sum(payment_history.total_price) from users join payment_history on users.email = payment_history.email where users.created_date between :start and :end", nativeQuery = true)
    Double getTotalRevenue(@Param("start") Date start, @Param("end") Date end);

}
