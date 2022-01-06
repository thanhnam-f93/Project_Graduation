package net.javaguides.springboot.repository;

import net.javaguides.springboot.entity.Menu;
import net.javaguides.springboot.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface MenuRepository extends JpaRepository<Menu, Integer> {
    @Query(" from Menu m  where m.menuCode=:menucode and m.status=true")
    List<Menu> getMenuByMenuCode(@Param("menucode") String menucode);

    @Query("  from Menu")
    List<Menu> listmenu();

    //	@Query(" from Menu m  where m.users.email like CONCAT('%',:email,'%')")
    @Query(value = "select * from menu where menu.email=:email", nativeQuery = true)
    List<Menu> getMenuByEmail(@Param("email") String email);

    @Query(" from Menu m where m.status=true and m.users.email=:email ")
    List<Menu> getMenuByStatus(@Param("email") String email);

    //	@Query(value = "select m from Menu m where m.users.email=:email")
//	Page<Menu> findAllByUser(@Param("email") String email, PageRequest pageRequest);
    //function to get total menu created by users
    @Query("select count(m) from Menu m where m.users.email=:email")
    Integer countSumMenuCreated(@Param("email") String email);

    @Query(value = "select menu.name_menu,count(*) as Total from activity_button \n" +
            "join button on button.id=activity_button.id_button \n" +
            "join menu on menu.id = button.id_menu \n" +
            "where menu.email=:email \n" +
            "group by  menu.name_menu \n" +
            "order by Total desc", nativeQuery = true)
    List<Object> statisticsClickByMenu(@Param("email") String email);


    @Query(value = "select m from Menu m where m.users.email=:email and m.status=true")
    List<Menu> findAllByStatusTrue(@Param("email") String email);


    @Query("select count(m.id) from Menu m inner join User u on m.users = u.email where m.users = :p_email " +
            "and u.email not in (select us.email from User us inner join PaymentHistory ph on us.email = ph.users where " +
            "ph.users = :p_email and ph.status = true)")
    Integer getBasicPro(User p_email);


    @Query("select count(m.id) from Menu m")
    Integer countAll();


}
