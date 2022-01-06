package net.javaguides.springboot.controller;

import net.javaguides.springboot.entity.ButtonFake;
import net.javaguides.springboot.exception.ResourceNotFoundException;
import net.javaguides.springboot.repository.ButtonFakeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/")
public class ButtonFakeController {

    @Autowired
    private ButtonFakeRepository buttonfakeRepository;

    @GetMapping("/buttonfake")
    public List<ButtonFake> getAllButtonFake() {
        return buttonfakeRepository.findAll();
    }

    @PostMapping("/buttonfake")
    public ButtonFake createButtonfake(@RequestBody ButtonFake buttonfake) {
        return buttonfakeRepository.save(buttonfake);
    }

    @DeleteMapping("/buttonfake/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteButtonFake(@PathVariable int id) {
        ButtonFake button = buttonfakeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Menu not exist with id :" + id));
        buttonfakeRepository.delete(button);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/buttonfake/{id}")
    public ResponseEntity<ButtonFake> updateButtonFake(@PathVariable int id, @RequestBody ButtonFake buttonfake) {
        ButtonFake buttonfake1 = buttonfakeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Categories not exist with id :" + id));
        buttonfake1.setColor_background(buttonfake1.getColor_background());
        buttonfake1.setColor_icon(buttonfake1.getColor_icon());
        buttonfake1.setColor_text(buttonfake1.getColor_text());
        buttonfake1.setLink(buttonfake1.getLink());
        buttonfake1.setName_button(buttonfake1.getName_button());
        ButtonFake updatebuttonfake1 = buttonfakeRepository.save(buttonfake);
        return ResponseEntity.ok(updatebuttonfake1);
    }

}
