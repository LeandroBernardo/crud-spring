package br.com.growintech.crud.service;

import br.com.growintech.crud.dto.UserDTO;
import br.com.growintech.crud.entity.User;
import br.com.growintech.crud.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<UserDTO> findAll() {
        List<User> users = userRepository.findAll();
        return users.stream().map(this::toDTO).collect(Collectors.toList());
    }

    public Optional<UserDTO> findById(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.map(this::toDTO);
    }

    public UserDTO save(UserDTO userDTO) {
        User user = toEntity(userDTO);
        user = userRepository.save(user);
        return toDTO(user);
    }

    public Optional<UserDTO> update(Long id, UserDTO userDTO) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            User user = toEntity(userDTO);
            user.setId(id);
            user = userRepository.save(user);
            return Optional.of(toDTO(user));
        } else {
            return Optional.empty();
        }
    }

    public boolean delete(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            userRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    private UserDTO toDTO(User user) {
        return new UserDTO(user.getId(), user.getFirstName(), user.getLastName(), user.getAge(), null);
    }

    private User toEntity(UserDTO userDTO) {
        return new User(null, userDTO.getFirstName(), userDTO.getLastName(), userDTO.getAge(), userDTO.getPassword());
    }
}
