package net.javaguides.springboot.repository;

import net.javaguides.springboot.entity.ActivityMenu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;

public interface ActivityMenuRepository extends JpaRepository<ActivityMenu, Integer> {
    @Query(value = "select c from ActivityMenu c")
    Page<ActivityMenu> findAll(Pageable pageable);

    //////////////////////////////////////////////////////////////////////////
    @Query(value = "select count(*) as TotalClickOnMenuEnable from activity_button join  button on button.id = activity_button.id_button \n" +
            "join menu on menu.id = button.id_menu join  users on users.email = menu.email \n" +
            "where users.email =:email and menu.id=:idMenu \n" +
            "and DATEPART(HOUR,activity_button.created_at) =:hour\n" +
            "and DAY(activity_button.created_at) =:day\n" +
            "and MONTH(activity_button.created_at) =:month\n" +
            "and YEAR(activity_button.created_at) =:year", nativeQuery = true)
    Integer statisticAllActionOnThisMenuEnable(@Param("email") String email, @Param("idMenu") Integer idMenu, @Param("hour") Integer hour, @Param("day") Integer day
            , @Param("month") Integer month, @Param("year") Integer year);


    @Query(value = "select count(*) as TotalClickOnMenuEnable from activity_button join  button on button.id = activity_button.id_button \n" +
            "join menu on menu.id = button.id_menu join  users on users.email = menu.email \n" +
            "where users.email =:email and menu.id =:idMenu and LEFT(CONVERT(VARCHAR, activity_button.created_at, 120), 10) LIKE CONCAT(:day,'%')", nativeQuery = true)
    Integer statisticAllActionOnThisMenu(@Param("email") String email, @Param("idMenu") Integer idMenu, @Param("day") String day);

    @Query(value = "select count(*) as TotalClickOnMenuEnable from activity_button join  button on button.id = activity_button.id_button \n" +
            "join menu on menu.id = button.id_menu join  users on users.email = menu.email \n" +
            "where users.email =:email and menu.id =:idMenu and LEFT(CONVERT(VARCHAR, activity_button.created_at, 120), 10) LIKE CONCAT(:day,'%')", nativeQuery = true)
    Integer statisticAllActionOnThisMenuByMenuId(@Param("email") String email, @Param("idMenu") Integer idMenu, @Param("day") String day);

    @Query(value = "select count(*) as TotalClickOnMenuEnable from activity_button join  button on button.id = activity_button.id_button \n" +
            "join menu on menu.id = button.id_menu join  users on users.email = menu.email \n" +
            "where users.email =:email and LEFT(CONVERT(VARCHAR, activity_button.created_at, 120), 10) LIKE CONCAT(:day,'%')", nativeQuery = true)
    Integer statisticAllActionOnAllMenu(@Param("email") String email, @Param("day") String day);

    @Query(value = "select count(*) as TotalClickOnMenuEnableWithId from activity_button join  button on button.id = activity_button.id_button \n" +
            "join menu on menu.id = button.id_menu join  users on users.email = menu.email \n" +
            "where users.email =:email and menu.id=:idMenu and activity_button.created_at LIKE CONCAT(:day,'%')", nativeQuery = true)
    Integer TotalClickOnMenuEnableWithId(@Param("email") String email, @Param("idMenu") Integer idMenu, @Param("day") String day);

    //////////////////////////////////////////////////////
    @Query(value = "select distinct menu.name_menu, count(*) as countNumberClickMenu from activity_button join button on button.id = activity_button.id_button \n" +
            "join menu on menu.id = button.id_menu join  users on users.email = menu.email and" +
            " users.email =:email  group by menu.name_menu", nativeQuery = true)
    Page<Object[]> getTotalNumberClickOnMenu(@Param("email") String email, Pageable pageable);

    @Query(value = "select count(*) as countNumberClickMenu from activity_button join button on button.id = activity_button.id_button \n" +
            "join menu on menu.id = button.id_menu join  users on users.email = menu.email and users.email =:email and activity_button.created_at\n" +
            "between CONVERT(date,:start,120) AND DATEADD(day,1,CONVERT(date,:end,120)) and menu.id =:menuId", nativeQuery = true)
    Page<Object[]> getTotalNumberClickOnMenuByTime(@Param("email") String email, @Param("start") String start,
                                                   @Param("end") String end, @Param("menuId") Integer menuId, Pageable pageable);


    @Query(value = "select distinct menu.name_menu, count(*) as countNumberActionMenu from activity_menu join menu on menu.id = activity_menu.id_menu join users\n" +
            "on users.email = menu.email where users.email =:email group by menu.name_menu order by countNumberActionMenu desc", nativeQuery = true)
    Page<Object[]> getTotalNumberActionDisplayOnMenu(@Param("email") String email, Pageable pageable);


    ///////////////////////////////////////////////////////////
    @Query(value = "select button.type_button, count(*) as countNumberActionButtonByType from button join menu on menu.id = button.id_menu join activity_menu\n" +
            "on menu.id = activity_menu.id_menu join users on users.email = menu.email where users.email =:email group by button.type_button order by countNumberActionButtonByType", nativeQuery = true)
    Page<Object[]> getTotalNumberActionDisplayOnButtonByType(@Param("email") String email, Pageable pageable);


    //    @Query(value = "select  button.type_button, count(*) as countNumberActionButtonByType \n" +
//            "from activity_button join button on button.id = activity_button.id_button\n" +
//            "join menu on menu.id = button.id_menu \n" +
//            "join users on users.email = menu.email \n" +
//            "where users.email =:email and button.id_menu in (select distinct activity_menu.id_menu from activity_menu)\n" +
//            "group by button.type_button",nativeQuery = true)
    @Query(value = "select  b.typeButton, count(ab) as countNumberActionButtonByType \n" +
            "from ActivityButton ab join Button b on b.id = ab.buttonId\n" +
            "join Menu m on m.id = b.menu.id \n" +
            "join User u on u.email = m.users.email \n" +
            "where u.email =:email and b.menu.id in (select am.menuId from ActivityMenu am)\n" +
            "group by b.typeButton order by countNumberActionButtonByType")
    Page<Object[]> getTotalNumberClickOnButtonByType(@Param("email") String email, Pageable pageable);


    @Query(value = "select  ab.CreatedAt as ClickTime,m.name_menu, b.name_button, b.link,ab.fromUrl from \n" +
            "ActivityButton ab join Button b on b.id = ab.buttonId\n" +
            "join Menu m on m.id = b.menu.id\n" +
            "join User u on u.email = m.users.email where u.email=:email and ab.CreatedAt between :start AND :end"
    )
    Page<Object[]> getStatisticInformationOfAction(@Param("email") String email, @Param("start") Date start,
                                                   @Param("end") Date end, Pageable pageable);


    @Query(value = "select  ab.CreatedAt as ClickTime,m.name_menu, b.name_button, b.link,ab.fromUrl from \n" +
            "ActivityButton ab join Button b on b.id = ab.buttonId\n" +
            "join Menu m on m.id = b.menu.id\n" +
            "join User u on u.email = m.users.email where u.email=:email and ab.CreatedAt between :start AND :end and m.id=:idMenu"
    )
    Page<Object[]> getStatisticInformationOfActionWithIdMenu(@Param("email") String email, @Param("start") Date start,
                                                             @Param("end") Date end, @Param("idMenu") Integer idMenu, Pageable pageable);

    @Query(value = "select  ab.CreatedAt as ClickTime,m.name_menu, b.name_button, b.link,ab.fromUrl from \n" +
            "ActivityButton ab join Button b on b.id = ab.buttonId\n" +
            "join Menu m on m.id = b.menu.id\n" +
            "join User u on u.email = m.users.email where u.email=:email and ab.CreatedAt between :start AND :end " +
            "and  b.name_button like %:search% or b.link like %:search% " +
            "or ab.fromUrl like %:search%"
    )
    Page<Object[]> getStatisticInformationOfActionWithSearch(@Param("email") String email, @Param("start") Date start,
                                                             @Param("end") Date end, @Param("search") String search, Pageable pageable);

    @Query(value = "select  ab.CreatedAt as ClickTime,m.name_menu, b.name_button, b.link,ab.fromUrl from \n" +
            "ActivityButton ab join Button b on b.id = ab.buttonId\n" +
            "join Menu m on m.id = b.menu.id\n" +
            "join User u on u.email = m.users.email where u.email=:email and ab.CreatedAt between :start AND :end " +
            " and m.id=:idMenu and ( b.name_button like %:search% or b.link like %:search% " +
            "or ab.fromUrl like %:search%)"
    )
    Page<Object[]> getStatisticInformationOfActionWithSearchAndIdMenu(@Param("email") String email, @Param("start") Date start,
                                                                      @Param("end") Date end, @Param("search") String search, @Param("idMenu") Integer idMenu, Pageable pageable);


//    @Query(value = "select distinct activity_button.from_url, count(*) as Total from activity_button join button on button.id = activity_button.id_button\n" +
//            "         join menu on menu.id = button.id_menu where menu.email=:email and menu.id=:idMenu and" +
//            " activity_button.created_at between CONVERT(date,:start,120) AND DATEADD(day,1,CONVERT(date,:end,120)) " +
//            "group by activity_button.from_url order by Total desc", nativeQuery = true)
//    Page<Object[]> countTotalClickBuFromUrl(@Param("email") String email,@Param("idMenu")Integer idMenu, @Param("start") Date start,
//                                            @Param("end") Date end, Pageable pageable);


    @Query(value = "select distinct ab.fromUrl, count(ab) as Total from ActivityButton ab join Button b on b.id = ab.buttonId\n" +
            "join Menu m on m.id = b.menu.id where m.users.email=:email and m.id=:idMenu and ab.CreatedAt between :start AND :end " +
            "group by ab.fromUrl order by Total desc")
    Page<Object[]> countTotalClickBuFromUrl(@Param("email") String email, @Param("idMenu") Integer idMenu, @Param("start") Date start,
                                            @Param("end") Date end, Pageable pageable);


    // This is query to Statistics for Page Dashboard
    @Query(value = "select count(*) as countNumberClickMenu from activity_button join button on button.id = activity_button.id_button \n" +
            "join menu on menu.id = button.id_menu where menu.email =:email ", nativeQuery = true)
    Integer getTotalNumberClickByUser(@Param("email") String email);


}
