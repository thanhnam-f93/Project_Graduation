package net.javaguides.springboot.controller;


import com.google.gson.Gson;
import net.javaguides.springboot.dao.PaymentDao;
import net.javaguides.springboot.entity.Book;
import net.javaguides.springboot.entity.DataOfCustomer;
import net.javaguides.springboot.entity.Modal;
import net.javaguides.springboot.model.response.DataOfCustomerAndModal;
import net.javaguides.springboot.repository.DataOfCustomerRepository;
import net.javaguides.springboot.repository.ModalRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class DataOfCustomerController {
    Logger logger = LoggerFactory.getLogger(PaypalController.class);
    @Autowired
    PaymentDao paymentDao;
    @Autowired
    private DataOfCustomerRepository dataOfCustomerRepository;
    @Autowired
    private ModalRepository modalRepository;

    @GetMapping("/dataofcustomer")
    public List<DataOfCustomer> getAllDataOfCustomer() {
        return dataOfCustomerRepository.findAll();
    }

    @PostMapping("/dataofcustomer")
    public void createDataOfCustomer(@RequestBody Object object) {
        Gson gson = new Gson();
        String json = gson.toJson(object);
        Book book = gson.fromJson(json, Book.class);
        DataOfCustomer dataOfCustomer = book.getDataOfCustomers().get(0);
//        String idDataOfCustomer = UUID.randomUUID().toString();
//<<<<<<< HEAD
//        Random rand = new Random();
//        int code = (int) Math.floor(((Math.random() * 899999) + 100000));
//        String idDataOfCustomer = String.valueOf("CU" + code);
//        dataOfCustomer.setId(idDataOfCustomer);
//        dataOfCustomerRepository.save(dataOfCustomer);
//=======
//
//        dataOfCustomer.setId(idDataOfCustomer);  Random rand = new Random();
////        int code = (int) Math.floor(((Math.random() * 899999) + 100000));
////        String idDataOfCustomer = String.valueOf("CU" +code);
        DataOfCustomer idDataOfCustomer = new DataOfCustomer();
        idDataOfCustomer = dataOfCustomerRepository.save(dataOfCustomer);

        List<Modal> modal = book.getModal();
        if (modal != null & modal.size() > 0) {
            for (Modal modals : modal) {
                int updateValueInput = 0;
//<<<<<<< HEAD
//                updateValueInput = paymentDao.updateValueInput(modals, idDataOfCustomer);
//=======
                updateValueInput = paymentDao.updateValueInput(modals, idDataOfCustomer.getId());

                if (updateValueInput == 0) {
                    logger.info("Update khong thanh thong");
                } else {
                    logger.info("Update thanh thong");
                }
            }
        }

//        return dataOfCustomerRepository.save(dataOfCustomer);
    }

    @GetMapping("/dataofcustomerbyid/{id}")
    public Optional<DataOfCustomer> getDataOfCustomersById(@PathVariable int id) {
        return dataOfCustomerRepository.findById(id);
    }

    @GetMapping("/dataofcustomerbyuser/{email}")
    public List<DataOfCustomer> getDataOfCustomersByUsers(@PathVariable String email) {
        return dataOfCustomerRepository.getDataOfCustomersByUsers(email);
    }

    @GetMapping("/search")
    public List<DataOfCustomer> getDataOfCustomersByFullName(@RequestParam(name = "keyword", required = false, defaultValue = "") String fullname) {
        return dataOfCustomerRepository.getDataOfCustomerByFullName(fullname);
    }

    @PostMapping("/create")
    public DataOfCustomer createDataOfCustom(@RequestBody DataOfCustomer data) {
        return dataOfCustomerRepository.save(data);
    }

    @PutMapping("/update")
    public DataOfCustomer updateDataOfCustomer(@RequestBody DataOfCustomer dataOfCustomer) {
        DataOfCustomer existingData = dataOfCustomerRepository.findById(dataOfCustomer.getId()).orElse(null);
        existingData.setFullName(dataOfCustomer.getFullName());
        existingData.setPhone(dataOfCustomer.getPhone());
        existingData.setEmailCustomer(dataOfCustomer.getEmailCustomer());
        existingData.setAddress(dataOfCustomer.getAddress());
        existingData.setConTent(dataOfCustomer.getConTent());
        existingData.setNotes(dataOfCustomer.getNotes());
//        existingData.setUsers(dataOfCustomer.getUsers());
        return dataOfCustomerRepository.save(existingData);
    }

    @GetMapping("/datacustomerandmodal/{id}")
    public List<DataOfCustomerAndModal> dataOfCustomerAndModal(@PathVariable String id) {
        List<DataOfCustomerAndModal> lis = new ArrayList<DataOfCustomerAndModal>();
        lis = paymentDao.dataOfCustomerAndModal(id);
        return lis;
    }

}
