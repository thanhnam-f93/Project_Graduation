package net.javaguides.springboot.repository;

import net.javaguides.springboot.entity.Button;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ButtonRepository extends JpaRepository<Button, Integer> {
    @Query("  from Button")
    List<Button> listbutton();

    Optional<Button> findById(Integer id);
//	@Query(" from Button b where b.menu.id_menu=:id_menu")
//	List<Button> listButtonByIdMenu(@Param("id_menu") int id_menu);

    @Query(" from Button b where b.menu.id=:id_menu")
    List<Button> listButtonByIdMenu(@Param("id_menu") int id_menu);

    @Query("select count(b) from Button b where b.menu.users.email=:email")
    Integer countSumButtonCreated(@Param("email") String email);

    @Query(value = "select activity_button.from_url,count(*) as Total from activity_button \n" +
            "join button on button.id=activity_button.id_button \n" +
            "join menu on menu.id = button.id_menu \n" +
            "where menu.email=:email\n" +
            "group by  activity_button.from_url \n" +
            "order by Total desc", nativeQuery = true)
    List<Object> statisticsClickByUrl(@Param("email") String email);

    @Query(value = "select button.name_button,count(*) as Total from activity_button \n" +
            "join button on button.id=activity_button.id_button \n" +
            "join menu on menu.id = button.id_menu \n" +
            "where menu.email=:email\n" +
            "group by  button.name_button \n" +
            "order by Total desc", nativeQuery = true)
    List<Object> statisticsClickByButton(@Param("email") String email);


    @Query("select count(b.id) from Button b")
    Integer countAllButton();

}
