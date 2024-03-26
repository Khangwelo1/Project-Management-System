package com.algoExpert.demo.Service;

import com.algoExpert.demo.Dto.UserDto;
import com.algoExpert.demo.Entity.*;
import com.algoExpert.demo.Mapper.UserMapper;
import com.algoExpert.demo.Repository.MemberRepository;
import com.algoExpert.demo.Repository.ProjectRepository;
import com.algoExpert.demo.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    UserMapper userMapper;

//    create user
    public UserDto create(UserDto userDto){
        User user = userMapper.userDtoToUser(userDto);
        User userResults = userRepository.save(user);
        return userMapper.userToUserDto(userResults);
    }

// get all users
    public List<UserDto> getUsers(){
        List<User> users = userRepository.findAll();
        return userMapper.usersToUserDtos(users);
    }

//    delete user by id
    public List<UserDto> deleteUser(int userId){
        userRepository.deleteById(userId);
        return userRepository.findAll()
                .stream().map(user -> new UserDto(user.getUser_id(),
                        user.getUsername(),
                        user.getEmail())).collect(Collectors.toList());
    }


    public List<Project> getUserProjectIds(int userId) {
        // Find all members
        List<Member> memberList = memberRepository.findAll();

        // Filter members by user_id and map them to project ids
        List<Integer> userProjectIds = memberList.stream()
                .filter(member -> member.getUser_id() == userId)
                .map(Member::getProject_id) // Assuming you have a method getProject_id() in Member class
                .collect(Collectors.toList());

        return projectRepository.findAllById(userProjectIds);
    }



}
