package net.javaguides.springboot.controller;


import net.javaguides.springboot.entity.User;
import net.javaguides.springboot.repository.ButtonRepository;
import net.javaguides.springboot.repository.MenuRepository;
import net.javaguides.springboot.repository.PaymentHistoryRepository;
import net.javaguides.springboot.repository.UserRepository;
import net.javaguides.springboot.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1")
public class UserController {

    private final UserService userService;
    @Autowired
    PasswordEncoder encoder;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MenuRepository menuRepository;
    @Autowired
    private PaymentHistoryRepository paymentHistoryRepository;
    @Autowired
    private ButtonRepository buttonRepository;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    public List<User> getAllUser() {
        return userRepository.findAll();
    }


    @PostMapping("/user")
    public ResponseEntity createUser(@Valid @RequestBody User user) {
        if (userRepository.existsById(user.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body("Error: Email đã tồn tại");
        }
        user.setPassword(encoder.encode(user.getPassword()));
        User dto = userRepository.save(user);
        return new ResponseEntity(dto, HttpStatus.OK);

    }

    @GetMapping("/user/{id}")
    public Optional<User> getUserById(@PathVariable String id) {
        return userRepository.findById(id);
    }

    @GetMapping("/admin/users")
    public ResponseEntity getUserByRole() {
        List<User> listUsers = userService.getUserByRole();
        ResponseEntity response = new ResponseEntity(listUsers, HttpStatus.OK);
        return response;
    }

    @GetMapping("/admin/users/role")
    public ResponseEntity getRoleOfUsers() {
        List<String> listRole = userRepository.getRole();
        return new ResponseEntity(listRole, HttpStatus.OK);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity updateUser(@RequestBody User user) {
        User dto = userRepository.save(user);
        return new ResponseEntity(dto, HttpStatus.OK);
    }

    @GetMapping("/admin/users/role-customer")
    public ResponseEntity getUserByRoleCustomer() {
        List<User> listUsers = userService.getUserByRoleCustomer();
        ResponseEntity response = new ResponseEntity(listUsers, HttpStatus.OK);
        return response;
    }

    @GetMapping("/admin/users/status")
    public ResponseEntity getStatusOfUsers() {
        List<String> listStatus = userRepository.getStatus();
        return new ResponseEntity(listStatus, HttpStatus.OK);
    }

    @GetMapping("/sum-employee")
    public Integer sumEmplyee() {
        return userRepository.countByRole("employee");
    }

    @GetMapping("/sum-customer")
    public Integer sumCustomer() {
        return userRepository.countByRole("customer");
    }

    @GetMapping("/sum-menu")
    public Integer sumMenu() {
        return menuRepository.countAll();
    }

    @GetMapping("/total-price")
    public double totalPrice() {
        return paymentHistoryRepository.getTotalSumPrice();
    }

    @GetMapping("/sum-button")
    public Integer sumButtons() {
        return buttonRepository.countAllButton();
    }

    @GetMapping("/admin/statistical-customers")
    public ResponseEntity getTotalCustomer(@RequestParam("start") Date start, @RequestParam("end") Date end) {
        Integer customer = null;
        try {
            customer = userRepository.getTotalCustomer(start, end);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @GetMapping("/admin/statistical-customers-pro")
    public ResponseEntity getTotalCustomerPro(@RequestParam("start") Date start, @RequestParam("end") Date end) {
        Integer customer = null;
        try {
            customer = userRepository.getTotalCustomerPro(start, end);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @GetMapping("/admin/statistical-customers-basic")
    public ResponseEntity getTotalCustomerBasic(@RequestParam("start") Date start, @RequestParam("end") Date end) {
        Integer customer = null;
        try {
            customer = userRepository.getTotalCustomerBasic(start, end);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @GetMapping("/admin/statisticCustomerByAllDay")
    ResponseEntity<?> statisticCustomerByAllDay(
            @RequestParam(name = "start") String s,
            @RequestParam(name = "end") String e
    ) {
        try {
            List<Integer> list = new ArrayList<>();
            LocalDate start = LocalDate.parse(s);
            LocalDate end = LocalDate.parse(e);
            List<LocalDate> totalDates = new ArrayList<>();
            while (!start.isAfter(end)) {
                totalDates.add(start);
                start = start.plusDays(1);
            }
            list = totalDates.stream().map(item -> userRepository.statisticCustomerByAllDay(item.toString())).collect(Collectors.toList());
            return new ResponseEntity<>(list, new HttpHeaders(), HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.badRequest().body("Page Empty" + e);
        }

    }

    @GetMapping("/admin/statisticCustomerProByAllDay")
    ResponseEntity<?> statisticCustomerProByAllDay(
            @RequestParam(name = "start") String s,
            @RequestParam(name = "end") String e
    ) {
        try {
            List<Integer> list = new ArrayList<>();
            LocalDate start = LocalDate.parse(s);
            LocalDate end = LocalDate.parse(e);
            List<LocalDate> totalDates = new ArrayList<>();
            while (!start.isAfter(end)) {
                totalDates.add(start);
                start = start.plusDays(1);
            }
            list = totalDates.stream().map(item -> userRepository.statisticCustomerProByAllDay(item.toString())).collect(Collectors.toList());
            return new ResponseEntity<>(list, new HttpHeaders(), HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.badRequest().body("Page Empty" + e);
        }

    }

    @GetMapping("/admin/statisticCustomerBasicByAllDay")
    ResponseEntity<?> statisticCustomerBasicByAllDay(
            @RequestParam(name = "start") String s,
            @RequestParam(name = "end") String e
    ) {
        try {
            List<Integer> list = new ArrayList<>();
            LocalDate start = LocalDate.parse(s);
            LocalDate end = LocalDate.parse(e);
            List<LocalDate> totalDates = new ArrayList<>();
            while (!start.isAfter(end)) {
                totalDates.add(start);
                start = start.plusDays(1);
            }
            list = totalDates.stream().map(item -> userRepository.statisticCustomerBasicByAllDay(item.toString())).collect(Collectors.toList());
            return new ResponseEntity<>(list, new HttpHeaders(), HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.badRequest().body("Page Empty" + e);
        }

    }


    @GetMapping("/getListCalenderByRangeTimeOfAdminStatistical")
    ResponseEntity<?> getListCalenderByRangeTimeOfAdminStatistical(
            @RequestParam(name = "start") String s,
            @RequestParam(name = "end") String e
    ) {
        try {
            LocalDate start = LocalDate.parse(s);
            LocalDate end = LocalDate.parse(e);
            List<LocalDate> totalDates = new ArrayList<>();
            while (!start.isAfter(end)) {
                totalDates.add(start);
                start = start.plusDays(1);
            }
            return new ResponseEntity<>(totalDates, new HttpHeaders(), HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.badRequest().body("Page Empty");
        }
    }

    @GetMapping("/admin/statisticRevenueByAllDay")
    ResponseEntity<?> statisticRevenueByAllDay(
            @RequestParam(name = "start") String s,
            @RequestParam(name = "end") String e
    ) {
        try {
            List<Double> list = new ArrayList<>();
            LocalDate start = LocalDate.parse(s);
            LocalDate end = LocalDate.parse(e);
            List<LocalDate> totalDates = new ArrayList<>();
            while (!start.isAfter(end)) {
                totalDates.add(start);
                start = start.plusDays(1);
            }
            list = totalDates.stream().map(item -> userRepository.statisticRevenueByAllDay(item.toString())).collect(Collectors.toList());
            return new ResponseEntity<>(list, new HttpHeaders(), HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.badRequest().body("Page Empty" + e);
        }

    }

    @GetMapping("/admin/statistical-revenue")
    public ResponseEntity getTotalRevenue(@RequestParam("start") Date start, @RequestParam("end") Date end) {
        Double revenue = null;
        try {
            revenue = userRepository.getTotalRevenue(start, end);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(revenue, HttpStatus.OK);
    }
}
