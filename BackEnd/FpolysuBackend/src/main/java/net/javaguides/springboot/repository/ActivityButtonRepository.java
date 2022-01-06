package net.javaguides.springboot.repository;


import net.javaguides.springboot.entity.ActivityButton;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ActivityButtonRepository extends JpaRepository<ActivityButton, Integer> {
    @Query(value = "select * from activityButton", nativeQuery = true)
    Page<ActivityButton> findAll(Pageable pageable);


    @Query(value = "select distinct button.name_button, count(*) as countNumberClickButton from activity_button join button on button.id = activity_button.id_button \n" +
            "join menu on menu.id = button.id_menu join  users on users.email = menu.email and" +
            " users.email =:email  group by button.name_button", nativeQuery = true)
    List<Object[]> getTotalNumberClickOnButton(@Param("email") String email);


    @Query(value = "select count(*) as countNumberClickButtonByRangeTimeSelect from activity_button join button on button.id = activity_button.id_button \n" +
            "join menu on menu.id = button.id_menu join  users on users.email = menu.email and users.email =:email and activity_button.created_at\n" +
            "between CONVERT(date,:start,120) AND DATEADD(day,1,CONVERT(date,:end,120)) and button.id =:buttonId", nativeQuery = true)
    Page<Object[]> countNumberClickButtonByRangeTimeSelect(@Param("email") String email, @Param("start") String start,
                                                           @Param("end") String end, @Param("buttonId") Integer buttonId, Pageable pageable);


    @Query(value = "select count(*) as TotalClickOnButton from activity_button join  button on button.id = activity_button.id_button \n" +
            "join menu on menu.id = button.id_menu join  users on users.email = menu.email \n" +
            "where users.email =:email and button.id =:idButton and LEFT(CONVERT(VARCHAR, activity_button.created_at, 120), 10) LIKE CONCAT(:day,'%')", nativeQuery = true)
    Integer statisticAllActionOnThisButton(@Param("email") String email, @Param("idButton") Integer idButton, @Param("day") String day);


    @Query(value = "select distinct button.name_button, count(*) as countNumberActionByButton from button join menu on menu.id=button.id_menu\n" +
            "    join activity_menu on menu.id=activity_menu.id_menu where menu.email =:email" +
            "    group by button.name_button", nativeQuery = true)
    List<Object[]> statisticActionButtonByRangeTimeSelect(@Param("email") String email);

    @Query(value = "select equipment ,count(*) from activity_button join button on button.id = activity_button.id_button " +
            "join menu on menu.id=button.id_menu where menu.email=:email group by equipment", nativeQuery = true)
    List<Object> statisticsActivityByEquipment(@Param("email") String email);

    @Query(value = "select activity_button.supplier, count(*) from activity_button join button on button.id = activity_button.id_button\n" +
            "           join menu on menu.id = button.id_menu join  users on users.email = menu.email and\n" +
            "           users.email =:email  group by activity_button.supplier", nativeQuery = true)
    List<Object> statisticsActivityBySupplier(@Param("email") String email);

    @Query(value = "select activity_button.user_address, count(*) from activity_button join button on button.id = activity_button.id_button\n" +
            "           join menu on menu.id = button.id_menu join  users on users.email = menu.email and\n" +
            "           users.email =:email  group by activity_button.user_address", nativeQuery = true)
    List<Object> statisticsActivityByAddress(@Param("email") String email);

    @Query(value = "select distinct button.name_button,COUNT(*) as Total from activity_button join button on button.id = activity_button.id_button\n" +
            "join menu on menu.id = button.id_menu where menu.email=:email\n" +
            "and activity_button.from_url=:url and menu.id=:idMenu\n" +
            "group by button.name_button order by Total desc", nativeQuery = true)
    List<Object[]> statisticsActivityOfOneButtonByUrlAndMenu(@Param("email") String email, @Param("url") String url,@Param("idMenu") Integer idMenu);

    @Query(value = "select distinct ab.userAddress from ActivityButton ab")
    List<String> getListLocation(@Param("email") String email);

    @Query(value = "select ab.ipAddress,ab.userAddress, count(ab) from ActivityButton ab join Button b on b.id = ab.buttonId\n" +
            "           join Menu m on m.id = b.menu.id join User u on u.email = m.users.email and\n" +
            "           u.email =:email group by ab.ipAddress,ab.userAddress order by count(ab) desc ")
    Page<Object> statisticsActivityByIp(@Param("email") String email, Pageable pageable);


    @Query(value = "select ab.ipAddress,ab.userAddress, count(ab) from ActivityButton ab join Button b on b.id = ab.buttonId\n" +
            "           join Menu m on m.id = b.menu.id join User u on u.email = m.users.email and\n" +
            "           u.email =:email and ab.userAddress=:userAddress group by ab.ipAddress,ab.userAddress order by count(ab) desc ")
    Page<Object> statisticsActivityByIpAndLocation(@Param("email") String email, @Param("userAddress") String userAddress, Pageable pageable);
}
