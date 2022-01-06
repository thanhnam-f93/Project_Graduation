package net.javaguides.springboot.controller;


import com.google.gson.Gson;
import net.javaguides.springboot.entity.Book;
import net.javaguides.springboot.entity.Button;
import net.javaguides.springboot.entity.Menu;
import net.javaguides.springboot.entity.Modal;
import net.javaguides.springboot.exception.ResourceNotFoundException;
import net.javaguides.springboot.repository.ButtonRepository;
import net.javaguides.springboot.repository.MenuRepository;
import net.javaguides.springboot.repository.ModalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin()
@RestController
@RequestMapping("/api/v1/")
public class ButtonController {

    Gson gson = new Gson();
    @Autowired
    private ButtonRepository buttonRespository;
    @Autowired
    private MenuRepository menuRespository;

    @Autowired
    private ModalRepository modalRespository;


    @GetMapping("/button")
    public List<Button> getAllButton() {
        return buttonRespository.findAll();
    }

    @PostMapping("/button")
    public void createButton(@RequestBody Object object) {
        String json = gson.toJson(object);
        Book book = gson.fromJson(json, Book.class);
        Menu menu1 = book.getMenu().get(0);
        menuRespository.save(menu1);
        List<Button> button1 = book.getButton();
        List<Modal> modal = book.getModal();
        Button button = new Button();
        for (int i = 0; i < button1.size(); i++) {
            button1.get(i).setMenu(menu1);
            button = buttonRespository.save(button1.get(i));
            for (int j = 0; j < modal.size(); j++) {
                if (button1.get(i).getId() == modal.get(j).getId()) {
                    modal.get(j).setButtons(button);
                    modalRespository.save(modal.get(j));
                }
            }
        }
    }

    @PostMapping("/createbutton")
    public Button createButton(@RequestBody Button button) {

        return buttonRespository.save(button);
    }

    @GetMapping("/getButtonByIDMenu/{id_menu}")
    public List<Button> listButtonByIdMenu(@PathVariable int id_menu) {
        return buttonRespository.listButtonByIdMenu(id_menu);
    }

    @PutMapping("/button")
    public ResponseEntity<Button> updateButton(@RequestBody Button button) {
        System.out.println(button);
        Button button1 = buttonRespository.findById(button.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Categories not exist with id :" + button.getId()));
//        System.out.println(button.getMenu());
        button1.setColor_background(button.getColor_background());
        button1.setColor_icon(button.getColor_icon());
        button1.setColor_text(button.getColor_text());
        button1.setLink(button.getLink());
        button1.setName_button(button.getName_button());
//        button1.setMenu(button.getMenu());
        button1.setIcon(button.getIcon());
        button1.setCaptionContent(button.getCaptionContent());
        button1.setTypeButton(button.getTypeButton());
        Button updatebutton = buttonRespository.save(button1);
        return ResponseEntity.ok(updatebutton);
    }


    @GetMapping("countSumButtonCreated")
    ResponseEntity<?> countSumButtonCreated(@RequestParam(value = "email", required = true) String email) {
        Integer value = buttonRespository.countSumButtonCreated(email);
        return (value != null) ? ResponseEntity.ok(value) : (ResponseEntity<?>) ResponseEntity.badRequest();
    }

    @GetMapping("statisticsClickByUrl")
    ResponseEntity<?> statisticsClickByUrl(@RequestParam(value = "email", required = true) String email) {
        List<Object> list = buttonRespository.statisticsClickByUrl(email);
        return (list != null) ? ResponseEntity.ok(list) : (ResponseEntity<?>) ResponseEntity.badRequest();
    }

    @GetMapping("statisticsClickByButton")
    ResponseEntity<?> statisticsClickByButton(@RequestParam(value = "email", required = true) String email) {
        List<Object> list = buttonRespository.statisticsClickByButton(email);
        return (list != null) ? ResponseEntity.ok(list) : (ResponseEntity<?>) ResponseEntity.badRequest();
    }

//	public static class Data{
//		private Menu menu;
//		private Button button;
//	}
//	@PostMapping("/button")
//	public Object createButton(@RequestBody Object data) {
////	buttonRespository.save(data);
////		System.out.println(data.getMenu().getMaMN());
////		menu.setMaMN(data.getMenu().getMaMN());
////		menu.setTenMN(data.getMenu().getTenMN());
////		menu.setStatus(false);
//		System.out.println(data);
//	
////		menuRespository.save(menu);
//		
////		System.out.println("s"+data.button);
//		return null;
//	}


}
