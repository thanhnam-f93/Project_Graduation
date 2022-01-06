package net.javaguides.springboot.repository;

import net.javaguides.springboot.entity.ButtonFake;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ButtonFakeRepository extends JpaRepository<ButtonFake, Integer> {

}
