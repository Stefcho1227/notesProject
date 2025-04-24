package org.example.notesproject.service.contracts;

import org.example.notesproject.dtos.in.UserInDTO;
import org.example.notesproject.models.User;

import java.util.List;

public interface UserService {
    User create(UserInDTO userInDTO);
    List<User> findAll();
    User find(Integer id);
    User update(Integer id, UserInDTO userInDTO);
    void delete(Integer id);
}
