package net.javaguides.springboot.mapper;

import net.javaguides.springboot.model.response.DataOfCustomerAndModal;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DataOfCustomerAndModalMapper implements RowMapper<DataOfCustomerAndModal> {


    @Override
    public DataOfCustomerAndModal mapRow(ResultSet resultSet, int i) throws SQLException {
        DataOfCustomerAndModal dataOfCustomerAndModal = new DataOfCustomerAndModal();
        try {
            String fullname = resultSet.getString(("fullname"));
            dataOfCustomerAndModal.setFullname(fullname);
        } catch (Exception e) {

        }
        try {
            String phone = resultSet.getString(("phone"));
            dataOfCustomerAndModal.setPhone(phone);
        } catch (Exception e) {

        }
        try {
            String emailCustomer = resultSet.getString(("email_customer"));
            dataOfCustomerAndModal.setEmailCustomer(emailCustomer);
        } catch (Exception e) {

        }
        try {
            String address = resultSet.getString(("_address"));
            dataOfCustomerAndModal.setAddress(address);
        } catch (Exception e) {

        }
        try {
            String content = resultSet.getString(("content"));
            dataOfCustomerAndModal.setContent(content);
        } catch (Exception e) {

        }
        try {
            String notes = resultSet.getString(("notes"));
            dataOfCustomerAndModal.setNotes(notes);
        } catch (Exception e) {

        }
        try {
            String createDate = resultSet.getString(("create_date"));
            dataOfCustomerAndModal.setCreateDate(createDate);
        } catch (Exception e) {

        }
//        try {
//            String inputValue = resultSet.getString(("input_value"));
//            dataOfCustomerAndModal.setInputValue(inputValue);
//        }catch (Exception e){
//
//        }
//        try {
//            String inputName = resultSet.getString(("input_name"));
//            dataOfCustomerAndModal.setInputName(inputName);
//        }catch (Exception e){
//
//        }
        return dataOfCustomerAndModal;
    }
}
