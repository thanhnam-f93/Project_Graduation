package net.javaguides.springboot.controller;

import net.javaguides.springboot.entity.ActivityButton;
import net.javaguides.springboot.repository.ActivityButtonRepository;
import net.javaguides.springboot.repository.ButtonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/")
public class ActivityButtonController {
    @Autowired
    private ActivityButtonRepository activityButtonRepository;

    @Autowired
    private ButtonRepository buttonRepository;

    @GetMapping("/activityButton")
    public List<ActivityButton> getAllUser() {
        return activityButtonRepository.findAll();
    }

    //this is commnet testgit
    @PostMapping("/activityButton")
    public ResponseEntity createActivity(@RequestBody ActivityButton activity) {
        try {
            System.out.println(activity.toString());
            return new ResponseEntity(activityButtonRepository.save(activity), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity("Error" + e, HttpStatus.BAD_REQUEST);
        }


    }

    @GetMapping("/activityButton/{id}")
    public Optional<ActivityButton> getUserById(@PathVariable String id) {
        return activityButtonRepository.findById(Integer.valueOf(id));
    }

    @GetMapping("/activityyButton")
    ResponseEntity<?> search(
//            @RequestParam(name = "input") String input,
            @RequestParam(name = "pageNo", defaultValue = "0", required = false) String pageNo,
            @RequestParam(name = "limit", defaultValue = "5", required = false) String limit
    ) {
        try {
            PageRequest pageRequest = PageRequest.of(Integer.parseInt(pageNo), Integer.parseInt(limit), Sort.by("id").descending());
            Page<ActivityButton> dtoPage = activityButtonRepository.findAll(pageRequest);
            if (dtoPage != null) {
                return new ResponseEntity<>(dtoPage, new HttpHeaders(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Khách hàng cần tìm không tồn tại", new HttpHeaders(), HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();

            return ResponseEntity.badRequest().body("Page Empty");
        }

    }

    @GetMapping("/getTotalNumberClickOnButtonByRangeTimeSelect")
    ResponseEntity<?> getTotalNumberClickOnMenu(
            @RequestParam(name = "email") String email,
            @RequestParam(name = "start", required = false) String start,
            @RequestParam(name = "end", required = false) String end,
            @RequestParam(name = "buttonId", required = false) String menuId,
            @RequestParam(name = "pageNo", defaultValue = "0", required = false) String pageNo,
            @RequestParam(name = "limit", defaultValue = "5", required = false) String limit
    ) {
        try {
            PageRequest pageRequest = PageRequest.of(Integer.parseInt(pageNo), Integer.parseInt(limit));
            Page<Object[]> dtoPage;
            List<Object[]> list;
            if (start != null && end != null) {
                dtoPage = activityButtonRepository.countNumberClickButtonByRangeTimeSelect(email, start, end, Integer.parseInt(menuId), pageRequest);
                return new ResponseEntity<>(dtoPage, new HttpHeaders(), HttpStatus.OK);
            } else {
                list = activityButtonRepository.getTotalNumberClickOnButton(email);
                return new ResponseEntity<>(list, new HttpHeaders(), HttpStatus.OK);
            }


        } catch (Exception e) {
            e.printStackTrace();

            return ResponseEntity.badRequest().body("Page Empty");
        }

    }


    @GetMapping("/statisticAllActionOnThisButtonByDay")
    ResponseEntity<?> statisticAllActionOnThisMenuByDay(
            @RequestParam(name = "email", required = true) String email,
            @RequestParam(name = "start") String s,
            @RequestParam(name = "end") String e,
            @RequestParam(name = "idButton", required = true) String idButton
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
            list = totalDates.stream().map(item -> activityButtonRepository.statisticAllActionOnThisButton
                    (email, Integer.parseInt(idButton), item.toString())).collect(Collectors.toList());
            return new ResponseEntity<>(list, new HttpHeaders(), HttpStatus.OK);
            //       return new ResponseEntity<>(dtoPage, new HttpHeaders(), HttpStatus.OK);

        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.badRequest().body("Page Empty" + e);
        }

    }

    @GetMapping("statisticActionButtonByRangeTimeSelect")
    ResponseEntity<?> statisticActionButtonByRangeTimeSelect(
            @RequestParam(name = "email", required = true) String email,
            @RequestParam(name = "pageNo", defaultValue = "0", required = false) String pageNo,
            @RequestParam(name = "limit", defaultValue = "5", required = false) String limit
    ) {
        try {
            PageRequest pageRequest = PageRequest.of(Integer.parseInt(pageNo), Integer.parseInt(limit));
            List<Object[]> dtoPage = activityButtonRepository.statisticActionButtonByRangeTimeSelect(email);
            if (dtoPage != null) {
                return new ResponseEntity<>(dtoPage, new HttpHeaders(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Khách hàng cần tìm không tồn tại", new HttpHeaders(), HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();

            return ResponseEntity.badRequest().body("Page Empty");
        }

    }

    @GetMapping("statisticsActivityByEquipment")
    ResponseEntity<?> statisticsActivityByEquipment(
            @RequestParam(name = "email", required = true) String email
    ) {
        List<Object> list = null;
        list = activityButtonRepository.statisticsActivityByEquipment(email);
        if (list != null) {
            return new ResponseEntity<>(list, new HttpHeaders(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Data not Exist", new HttpHeaders(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("statisticsActivityBySupplier")
    ResponseEntity<?> statisticsActivityBySupplier(
            @RequestParam(name = "email", required = true) String email
    ) {
        List<Object> list = null;
        list = activityButtonRepository.statisticsActivityBySupplier(email);
        if (list != null) {
            return new ResponseEntity<>(list, new HttpHeaders(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Data not Exist", new HttpHeaders(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("statisticsActivityByAddress")
    ResponseEntity<?> statisticsActivityByAddress(
            @RequestParam(name = "email", required = true) String email
    ) {
        List<Object> list = null;
        list = activityButtonRepository.statisticsActivityByAddress(email);
        if (list != null) {
            return new ResponseEntity<>(list, new HttpHeaders(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Data not Exist", new HttpHeaders(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("getListLocation")
    ResponseEntity<?> getListLocation(
            @RequestParam(name = "email", required = true) String email) {

        List<String> page = new ArrayList<>();
        page = activityButtonRepository.getListLocation(email);

        if (page != null) {
            return new ResponseEntity<>(page, new HttpHeaders(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Data not Exist", new HttpHeaders(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("statisticsActivityByIp")
    ResponseEntity<?> statisticsActivityByIp(
            @RequestParam(name = "email", required = true) String email,
            @RequestParam(name = "userAddress", required = false) String userAddress,
            @RequestParam(name = "pageNo", defaultValue = "0", required = false) String pageNo,
            @RequestParam(name = "limit", defaultValue = "5", required = false) String limit
    ) {
        PageRequest pageRequest = PageRequest.of(Integer.parseInt(pageNo), Integer.parseInt(limit));
        Page<Object> page = null;
        if (userAddress != null) {
            page = activityButtonRepository.statisticsActivityByIpAndLocation(email, userAddress, pageRequest);
        } else {
            page = activityButtonRepository.statisticsActivityByIp(email, pageRequest);
        }
        if (page != null) {
            return new ResponseEntity<>(page, new HttpHeaders(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Data not Exist", new HttpHeaders(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("statisticsActivityOfOneButtonByUrlAndMenu")
    ResponseEntity<?> statisticsActivityOfOneButtonByUrlAndMenu(
            @RequestParam(name = "email", required = true) String email,
            @RequestParam(name = "url", required = true) String url,
            @RequestParam(name = "idMenu",required = true) String idMenu
    ) {
        List<Object[]> list = null;
        list = activityButtonRepository.statisticsActivityOfOneButtonByUrlAndMenu(email, url,Integer.parseInt(idMenu));
        if (list != null) {
            return new ResponseEntity<>(list, new HttpHeaders(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Data not Exist", new HttpHeaders(), HttpStatus.NOT_FOUND);
        }
    }

}
