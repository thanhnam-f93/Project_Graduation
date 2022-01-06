package net.javaguides.springboot.repository;

import net.javaguides.springboot.entity.PaymentHistory;
import net.javaguides.springboot.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentHistoryRepository extends JpaRepository<PaymentHistory, Integer> {
    List<PaymentHistory> findAllByUsers(User user);

//    @Query(" from PaymentHistory p where p.status = true and p.users = :p_user ")
//    PaymentHistory getPaymentHistoriesByAccountPro(User user);

    PaymentHistory getPaymentHistoriesByUsersAndStatus(User user, boolean status);

    @Query("select sum(ph.totalPrice) from PaymentHistory ph")
    double getTotalSumPrice();
}
