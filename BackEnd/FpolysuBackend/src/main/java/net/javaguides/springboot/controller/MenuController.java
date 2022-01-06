package net.javaguides.springboot.controller;

import net.javaguides.springboot.entity.Menu;
import net.javaguides.springboot.exception.ResourceNotFoundException;
import net.javaguides.springboot.repository.MenuRepository;
import net.javaguides.springboot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin()
@RestController
@RequestMapping("/api/v1/")
public class MenuController {
    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/menu")
    public List<Menu> getAllMenu() {
        return menuRepository.findAll();
    }

    @PostMapping("/menu")
    public Menu createMenu(@RequestBody Menu menu) {
        return menuRepository.save(menu);
    }

    @GetMapping("/menu/{id}")
    public Optional<Menu> getUserById(@PathVariable int id) {
        return menuRepository.findById(id);
    }

    @DeleteMapping("/menu/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteMenu(@PathVariable int id) {
        Menu menu = menuRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Menu not exist with id :" + id));
        menuRepository.delete(menu);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/getMenuByEmail/{email}")
    public List<Menu> getButtonByEmail(@PathVariable String email) {
        return menuRepository.getMenuByEmail(email);
    }

    @PostMapping("/getMenuByMenuCode/{menucode}")
    public List<Menu> getMenuByMenuCode(@PathVariable String menucode) {
        return menuRepository.getMenuByMenuCode(menucode);
    }


    @PutMapping("/menu")
    public ResponseEntity<Menu> updateMenu(@RequestBody Menu menu) {
        Menu menu1 = menuRepository.findById(menu.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Categories not exist with id :" + menu.getId()));
        menu1.setStatus(menu.isStatus());
        menu1.setColor_menu(menu.getColor_menu());
        menu1.setMenuCode(menu.getMenuCode());
        menu1.setMenuType(menu.getMenuType());
        menu1.setFromDisplayTime(menu.getFromDisplayTime());
        menu1.setToDisplayTime(menu.getToDisplayTime());
        menu1.setOpacity(menu.getOpacity());
        menu1.setMenuLocation(menu.getMenuLocation());
        menu1.setName_menu(menu.getName_menu());
        Menu updateMenu = menuRepository.save(menu1);
        return ResponseEntity.ok(updateMenu);
    }

    @GetMapping("/getMenuByStatus/{email}")
    public List<Menu> getMenuByStatus(@PathVariable String email) {
        return menuRepository.getMenuByStatus(email);
    }

    @GetMapping("countSumMenuCreated")
    ResponseEntity<?> countSumMenuCreated(@RequestParam(value = "email", required = true) String email) {
        Integer value = menuRepository.countSumMenuCreated(email);
        return (value != null) ? ResponseEntity.ok(value) : (ResponseEntity<?>) ResponseEntity.badRequest();
    }

    @GetMapping("statisticsClickByMenu")
    ResponseEntity<?> statisticsClickByButton(@RequestParam(value = "email", required = true) String email) {
        List<Object> list = menuRepository.statisticsClickByMenu(email);
        return (list != null) ? ResponseEntity.ok(list) : (ResponseEntity<?>) ResponseEntity.badRequest();

    }

    @GetMapping("findAllByStatusTrue")
    ResponseEntity<?> findAllByStatusTrue(@RequestParam(value = "email", required = true) String email) {
        try {
            List<Menu> list = menuRepository.findAllByStatusTrue(email);
            return ResponseEntity.ok().body(list);
        } catch (Exception e) {
            return (ResponseEntity<String>) ResponseEntity.badRequest().body("Erorr CMNR!");
        }

    }

    @GetMapping("/getBasicPro/{email}")
    public String getBasicPro(@PathVariable String email) {
        int countBasic = menuRepository.getBasicPro(userRepository.findOneByEmail(email));
//        int countHistory = menuRepository.getCountHistory(userRepository.findOneByEmail(email));
        System.out.println("AAAAAAAA      " + countBasic);
        if (countBasic > 5) {
            return "Lỗi, tài khoản của bạn chưa nâng cấp";
        }
        return "OK";
    }

    @PutMapping("/updateStatusMenu/{id}")
    public ResponseEntity<Menu> updateMenu(@PathVariable int id, @RequestBody Menu menu) {
        Menu menu1 = menuRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Menu not exist with id :" + id));
        menu1.setStatus(menu.isStatus());
        Menu updateMenu = menuRepository.save(menu1);
        return ResponseEntity.ok(updateMenu);
    }


}
