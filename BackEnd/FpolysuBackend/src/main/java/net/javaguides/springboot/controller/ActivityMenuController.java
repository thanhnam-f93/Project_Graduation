package net.javaguides.springboot.controller;

import net.javaguides.springboot.entity.ActivityMenu;
import net.javaguides.springboot.repository.ActivityMenuRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/")
public class ActivityMenuController {


    private final ActivityMenuRepository activityMenuRepository;

    public ActivityMenuController(ActivityMenuRepository activityMenuRepository) {
        this.activityMenuRepository = activityMenuRepository;
    }


    @GetMapping("/activityMenu")
    public List<ActivityMenu> getAllUser() {
        return activityMenuRepository.findAll();
    }

    //this is commnet testgit
    @PostMapping("/activityMenu")
    public ResponseEntity createActivity(@RequestBody ActivityMenu activity) {

        return new ResponseEntity(activityMenuRepository.save(activity), HttpStatus.OK);

    }

    @GetMapping("/activityMenu/{id}")
    public Optional<ActivityMenu> getUserById(@PathVariable String id) {
        return activityMenuRepository.findById(Integer.valueOf(id));
    }

    @GetMapping("/activityyMenu")
    ResponseEntity<?> search(
//            @RequestParam(name = "input") String input,
            @RequestParam(name = "pageNo", defaultValue = "0", required = false) String pageNo,
            @RequestParam(name = "limit", defaultValue = "5", required = false) String limit
    ) {
        try {
            PageRequest pageRequest = PageRequest.of(Integer.parseInt(pageNo), Integer.parseInt(limit), Sort.by("id").descending());
            Page<ActivityMenu> dtoPage = activityMenuRepository.findAll(pageRequest);
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

    @GetMapping("/statisticAllActionOnThisMenuEnable")
    ResponseEntity<?> statisticAllActionOnThisMenuEnable(
            @RequestParam(name = "email", required = true) String email,
            @RequestParam(name = "idMenu", required = true) String idMenu,
            @RequestParam(name = "day", required = true) String day,
            @RequestParam(name = "month", required = true) String month,
            @RequestParam(name = "year", required = true) String year
    ) {
        try {
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < 24; i++) {
                Integer dtoPage = activityMenuRepository.statisticAllActionOnThisMenuEnable(email, Integer.parseInt(idMenu), Integer.parseInt(String.valueOf(i)),
                        Integer.parseInt(day), Integer.parseInt(month), Integer.parseInt(year));
                list.add(dtoPage);
            }

//            int dtoPage = activityMenuRepository.statisticAllActionOnThisMenuEnable(email,Integer.parseInt(hour),
//                    Integer.parseInt(day),Integer.parseInt(month),Integer.parseInt(year));
            return new ResponseEntity<>(list, new HttpHeaders(), HttpStatus.OK);
            //       return new ResponseEntity<>(dtoPage, new HttpHeaders(), HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Page Empty" + e);
        }

    }

    @GetMapping("/TotalClickOnMenuEnableWithId")
    ResponseEntity<?> TotalClickOnMenuEnableWithId(
            @RequestParam(name = "email", required = true) String email,
            @RequestParam(name = "idMenu", required = true) String idMenu,
            @RequestParam(name = "day", required = true) String day
    ) {
        try {
            Integer dtoPage = activityMenuRepository.TotalClickOnMenuEnableWithId(email, Integer.parseInt(idMenu), day);
            return new ResponseEntity<>(dtoPage, new HttpHeaders(), HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Page Empty" + e);
        }

    }

    @GetMapping("/statisticAllActionOnThisMenuByDay")
    ResponseEntity<?> statisticAllActionOnThisMenuByDay(
            @RequestParam(name = "email", required = true) String email,
            @RequestParam(name = "start") String s,
            @RequestParam(name = "end") String e,
            @RequestParam(name = "idMenu", required = false) String idMenu


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
            if (idMenu != null) {
                list = totalDates.stream().map(item -> activityMenuRepository.statisticAllActionOnThisMenu
                        (email, Integer.parseInt(idMenu), item.toString())).collect(Collectors.toList());
            } else {
                list = totalDates.stream().map(item -> activityMenuRepository.statisticAllActionOnAllMenu
                        (email, item.toString())).collect(Collectors.toList());
            }

            return new ResponseEntity<>(list, new HttpHeaders(), HttpStatus.OK);
            //       return new ResponseEntity<>(dtoPage, new HttpHeaders(), HttpStatus.OK);

        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.badRequest().body("Page Empty" + e);
        }

    }


    @GetMapping("/statisticAllActionOnThisMenu")
    ResponseEntity<?> statisticAllActionOnThisMenu(
            @RequestParam(name = "email", required = true) String email,
            @RequestParam(name = "idMenu", required = true) String idMenu,
            @RequestParam(name = "day", required = true) String day

    ) {
        try {

            int list = activityMenuRepository.statisticAllActionOnThisMenu(email, Integer.parseInt(idMenu), day);
            return new ResponseEntity<>(list, new HttpHeaders(), HttpStatus.OK);
            //       return new ResponseEntity<>(dtoPage, new HttpHeaders(), HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Page Empty" + e);
        }

    }


    @GetMapping("/getTotalNumberClickOnMenu")
    ResponseEntity<?> getTotalNumberClickOnMenu(
            @RequestParam(name = "email") String email,
            @RequestParam(name = "start", required = false) String start,
            @RequestParam(name = "end", required = false) String end,
            @RequestParam(name = "menuId", required = false) String menuId,
            @RequestParam(name = "pageNo", defaultValue = "0", required = false) String pageNo,
            @RequestParam(name = "limit", defaultValue = "5", required = false) String limit
    ) {
        try {
            PageRequest pageRequest = PageRequest.of(Integer.parseInt(pageNo), Integer.parseInt(limit));
            Page<Object[]> dtoPage = null;
            if (start != null && end != null) {
                dtoPage = activityMenuRepository.getTotalNumberClickOnMenuByTime(email, start, end, Integer.parseInt(menuId), pageRequest);
            } else {
                dtoPage = activityMenuRepository.getTotalNumberClickOnMenu(email, pageRequest);
            }

            if (dtoPage != null) {
                return new ResponseEntity<>(dtoPage, new HttpHeaders(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Data need search not exist", new HttpHeaders(), HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();

            return ResponseEntity.badRequest().body("Page Empty");
        }

    }

    @GetMapping("/getTotalNumberActionDisplayOnMenu")
    ResponseEntity<?> getTotalNumberActionDisplayOnMenu(
            @RequestParam(name = "email") String email,
            @RequestParam(name = "start", required = false) String start,
            @RequestParam(name = "end", required = false) String end,
            @RequestParam(name = "pageNo", defaultValue = "0", required = false) String pageNo,
            @RequestParam(name = "limit", defaultValue = "5", required = false) String limit
    ) {
        try {
            PageRequest pageRequest = PageRequest.of(Integer.parseInt(pageNo), Integer.parseInt(limit));
            Page<Object[]> dtoPage = null;
            if (start != null && end != null) {

            } else {
                dtoPage = activityMenuRepository.getTotalNumberActionDisplayOnMenu(email, pageRequest);
            }

            if (dtoPage != null) {
                return new ResponseEntity<>(dtoPage, new HttpHeaders(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Data need search not exist", new HttpHeaders(), HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Page Empty");
        }

    }


    @GetMapping("/getTotalNumberActionDisplayOnButtonByType")
    ResponseEntity<?> getTotalNumberActionDisplayOnButtonByType(
            @RequestParam(name = "email") String email,
            @RequestParam(name = "pageNo", defaultValue = "0", required = false) String pageNo,
            @RequestParam(name = "limit", defaultValue = "5", required = false) String limit
    ) {
        try {
            PageRequest pageRequest = PageRequest.of(Integer.parseInt(pageNo), Integer.parseInt(limit));
            Page<Object[]> dtoPage = activityMenuRepository.getTotalNumberActionDisplayOnButtonByType(email, pageRequest);
            if (dtoPage != null) {
                return new ResponseEntity<>(dtoPage, new HttpHeaders(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Data need search not exist", new HttpHeaders(), HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();

            return ResponseEntity.badRequest().body("Page Empty");
        }

    }

    @GetMapping("/getTotalNumberClickOnButtonByType")
    ResponseEntity<?> getTotalNumberClickOnButtonByType(
            @RequestParam(name = "email") String email,
            @RequestParam(name = "pageNo", defaultValue = "0", required = false) String pageNo,
            @RequestParam(name = "limit", defaultValue = "5", required = false) String limit
    ) {
        try {
            PageRequest pageRequest = PageRequest.of(Integer.parseInt(pageNo), Integer.parseInt(limit));
            Page<Object[]> dtoPage = activityMenuRepository.getTotalNumberClickOnButtonByType(email, pageRequest);
            if (dtoPage != null) {
                return new ResponseEntity<>(dtoPage, new HttpHeaders(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Data need search not exist", new HttpHeaders(), HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();

            return ResponseEntity.badRequest().body("Page Empty");
        }

    }

    @GetMapping("/getStatisticInformationOfAction")
    ResponseEntity<?> getStatisticInformationOfAction(
            @RequestParam(name = "email") String email,
            @RequestParam(name = "start", required = false) String start,
            @RequestParam(name = "end", required = false) String end,
            @RequestParam(name = "search", required = false) String search,
            @RequestParam(name = "idMenu", required = false) String idMenu,
            @RequestParam(name = "pageNo", defaultValue = "0", required = false) String pageNo,
            @RequestParam(name = "limit", defaultValue = "5", required = false) String limit
    ) {
        try {
            SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
            Date startd = dateFormatter.parse(start);
            Date endd = dateFormatter.parse(end);
            System.out.println(startd.toString());
            System.out.println(endd.toString());
            PageRequest pageRequest = PageRequest.of(Integer.parseInt(pageNo), Integer.parseInt(limit), Sort.by("id").descending());
            Page<Object[]> dtoPage = null;
            if (search.length() > 0 && idMenu.length() > 0) {
                dtoPage = activityMenuRepository.getStatisticInformationOfActionWithSearchAndIdMenu(email, startd, endd, search, Integer.parseInt(idMenu), pageRequest);
            } else if (search.length() == 0 && idMenu.length() == 0) {
                dtoPage = activityMenuRepository.getStatisticInformationOfAction(email, startd, endd, pageRequest);
            } else if (search.length() > 0 && idMenu.length() == 0) {
                dtoPage = activityMenuRepository.getStatisticInformationOfActionWithSearch(email, startd, endd, search, pageRequest);
            } else {
                dtoPage = activityMenuRepository.getStatisticInformationOfActionWithIdMenu(email, startd, endd, Integer.parseInt(idMenu), pageRequest);
            }

            if (dtoPage != null) {
                return new ResponseEntity<>(dtoPage, new HttpHeaders(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Data need search not exist", new HttpHeaders(), HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();

            return ResponseEntity.badRequest().body("Page Empty");
        }

    }

    @GetMapping("/getListCalenderByRangeTime")
    ResponseEntity<?> getStatisticInformationOfActions(
            //  @RequestParam(name = "email") String email,
            @RequestParam(name = "start") String s,
            @RequestParam(name = "end") String e,
            @RequestParam(name = "pageNo", defaultValue = "0", required = false) String pageNo,
            @RequestParam(name = "limit", defaultValue = "5", required = false) String limit
    ) {
        try {

//            String s = "2014-05-01";
//            String e = "2014-09-10";
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

    @GetMapping("/countTotalClickBuFromUrl")
    ResponseEntity<?> countTotalClickBuFromUrl(
            @RequestParam(name = "email") String email,
            @RequestParam(name = "idMenu") String idMenu,
            @RequestParam(name = "start", required = false) String start,
            @RequestParam(name = "end", required = false) String end,
            @RequestParam(name = "pageNo", defaultValue = "0", required = false) String pageNo,
            @RequestParam(name = "limit", defaultValue = "5", required = false) String limit
    ) {
        try {
            SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
            Date startd = dateFormatter.parse(start);
            Date endd = dateFormatter.parse(end);
            PageRequest pageRequest = PageRequest.of(Integer.parseInt(pageNo), Integer.parseInt(limit));
            Page<Object[]> dtoPage = null;
            if (start.length() > 0 && end.length() > 0) {
                dtoPage = activityMenuRepository.countTotalClickBuFromUrl(email, Integer.parseInt(idMenu), startd, endd, pageRequest);
            } else {

            }

            if (dtoPage != null) {
                return new ResponseEntity<>(dtoPage, new HttpHeaders(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Data need search not exist", new HttpHeaders(), HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Page Empty");
        }

    }

    @GetMapping("getTotalNumberClickByUser")
    ResponseEntity<?> getTotalNumberClickByUser(@RequestParam(value = "email", required = true) String email) {
        int sum = activityMenuRepository.getTotalNumberClickByUser(email);
        return new ResponseEntity<>(sum, new HttpHeaders(), HttpStatus.OK);
    }
}