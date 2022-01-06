package net.javaguides.springboot.mapper;

import net.javaguides.springboot.entity.Modal;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ModalMapper implements RowMapper<Modal> {
    @Override
    public Modal mapRow(ResultSet resultSet, int i) throws SQLException {
        Modal modal = new Modal();
        try {
            String inputValue = resultSet.getString(("input_value"));
            modal.setInputValue(inputValue);
        } catch (Exception e) {

        }
        try {
            String inputName = resultSet.getString(("input_name"));
            modal.setInputName(inputName);
        } catch (Exception e) {

        }
        return modal;
    }
}
