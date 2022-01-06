package net.javaguides.springboot.dao;

import net.javaguides.springboot.entity.Modal;
import net.javaguides.springboot.mapper.DataOfCustomerAndModalMapper;
import net.javaguides.springboot.mapper.ModalMapper;
import net.javaguides.springboot.model.response.DataOfCustomerAndModal;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Repository("PaymentDao")
@Transactional
public class PaymentDao {
    Logger logger = LoggerFactory.getLogger(PaymentDao.class);
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private EntityManager entityManager;

    private Session getSession() {
        return entityManager.unwrap(Session.class);
    }

    //    public void updateValueInput(Modal modal){
//        Session session = this.getSession();
//        Transaction transaction = null;
//        try {
//            transaction = session.beginTransaction();
//            modal.setInputValue(modal.getInputValue());
//            this.getSession().update(modal);
//            transaction.commit();
//        }catch (Exception e){
//            e.printStackTrace();
//            if ( transaction != null) {
//                transaction.rollback();
//            }
//        }
//    }
    public int updateValueInput(Modal modal, int idDataOfCustomer) {
        try {
            String sql = "update modal SET input_value = ? , id_dataofcustomer = ? where id = ?";
            int result = 0;
            result = jdbcTemplate.update(sql, modal.getInputValue(), idDataOfCustomer, modal.getId());

            return result;
        } catch (Exception e) {
            return 0;
        }
    }//end method


    //lay thong in dataOfCustomer and modal
    public List<DataOfCustomerAndModal> dataOfCustomerAndModal(String id) {
        logger.info("Begin DataOfCustomerAndModal");
//        String sql = "select * from dbo.dataofcustomer left JOIN dbo.modal ON modal.id_dataofcustomer = dataofcustomer.id WHERE dbo.dataofcustomer.id = ?";
        String sql = "select  * from dbo.dataofcustomer where dbo.dataofcustomer.id = ?";
        String sql1 = "SELECT * FROM dbo.modal where dbo.modal.id_dataofcustomer = ? ";
        List<DataOfCustomerAndModal> dataOfCustomerAndModal = null;
        List<Modal> modal = null;
        try {
            dataOfCustomerAndModal = jdbcTemplate.query(sql, new Object[]{id}, new DataOfCustomerAndModalMapper());
            modal = jdbcTemplate.query(sql1, new Object[]{id}, new ModalMapper());
            dataOfCustomerAndModal.get(0).setModal(modal);
        } catch (Exception e) {
            logger.error("Exception: " + e.toString());
        }
        logger.info("End DataOfCustomerAndModal");
        return dataOfCustomerAndModal;
    }
}




