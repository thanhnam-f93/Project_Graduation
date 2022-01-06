package net.javaguides.springboot.repository;

import net.javaguides.springboot.entity.Modal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ModalRepository extends JpaRepository<Modal, Integer> {
    @Query(value = "from Modal m where m.buttons.id=:idButton")
    List<Modal> getModalByIdButton(int idButton);
}
