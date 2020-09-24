package com.musicallcommunity.musicallback.service.impl;

import com.musicallcommunity.musicallback.dto.UserDto;
import com.musicallcommunity.musicallback.dto.util.UserUtil;
import com.musicallcommunity.musicallback.exception.ForbiddenException;
import com.musicallcommunity.musicallback.exception.ResourceNotFoundException;
import com.musicallcommunity.musicallback.model.*;
import com.musicallcommunity.musicallback.payload.SignUpRequest;
import com.musicallcommunity.musicallback.repository.PasswordResetTokenRepository;
import com.musicallcommunity.musicallback.repository.ProfileRepository;
import com.musicallcommunity.musicallback.repository.RoleRepository;
import com.musicallcommunity.musicallback.repository.UserRepository;
import com.musicallcommunity.musicallback.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service(value = "userService")
public class UserServiceImpl implements UserService {

    private final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    private UserRepository userRepository;

    private RoleRepository roleRepository;

    private ProfileRepository profileRepository;

    private PasswordResetTokenRepository passwordTokenRepository;

    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository
            , ProfileRepository profileRepository, PasswordResetTokenRepository passwordTokenRepository
            , PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.profileRepository = profileRepository;
        this.passwordTokenRepository = passwordTokenRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User getByMail(String mail) throws ResourceNotFoundException {
        if (userRepository.findByMail(mail).equals(null)){
            throw new ResourceNotFoundException("User", "mail", mail);
        }
        return userRepository.findByMail(mail);
    }

    @Override
    public User getById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
    }

    @Override
    public User getByToken(String token) throws ResourceNotFoundException {
        PasswordResetToken passToken = passwordTokenRepository.findByToken(token);
        if(passToken == null) {
            throw new ResourceNotFoundException("User", "token", passToken.getUser());
        }
        return passToken.getUser();
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User save(User user){
        LOGGER.info("Saving user : " + user.toString());
        return userRepository.save(user);
    }

    @Override
    public User createUser(SignUpRequest signup) {
        LOGGER.info("Creating user : " + signup.toString());
        Profile profileTransient = new Profile();
        Profile profile = profileRepository.save(profileTransient);
        User user = new User(signup);
        Role userRole = roleRepository.findByName(RoleName.USER_ROLE).
                orElseThrow(() -> new ResourceNotFoundException("Role", "name", RoleName.USER_ROLE));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setProfileId(profile.getId());
        user.setRoles(Collections.singletonList(userRole));
        user.setEnabled(true);
        user.setProfile(profile);
        user.setUserFriends(new LinkedHashSet<>());
        user.setConversations(new ArrayList<>());
        connectUser(user);
        return save(user);
    }

    /**
     * Update the user
     * @param id
     * @param current
     * @return
     * @throws ResourceNotFoundException
     */
    @Override
    public User updateUser(Long id, UserDto current) throws ResourceNotFoundException {
        User existingUser = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "id", id));

        existingUser.setFirstName(current.getFirstName());
        existingUser.setLastName(current.getLastName());
        existingUser.setProfile(existingUser.getProfile());
        existingUser.setUserFriends(existingUser.getUserFriends());
        existingUser.setConversations(existingUser.getConversations());
        existingUser.setModificationDate(new Date());
        return userRepository.save(existingUser);
    }

    @Override
    public void createPasswordResetTokenForUser(final User user, final String token) {
        final PasswordResetToken myToken = new PasswordResetToken(token, user);
        passwordTokenRepository.save(myToken);
    }

    @Override
    public String validatePasswordResetToken(Long id, String token) throws ForbiddenException {
        final PasswordResetToken passToken = passwordTokenRepository.findByToken(token);
        if((passToken == null) || (passToken.getUser().getId() != id)) {
            throw new ForbiddenException("User", "cause", "invalidToken");
        }

        final Calendar cal = Calendar.getInstance();
        if((passToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0 ) {
            throw new ForbiddenException("User", "cause", "tokenExpired");
        }

        /*final User user = passToken.getUser();
        final Authentication auth = new UsernamePasswordAuthenticationToken(user, null, Arrays.asList(new SimpleGrantedAuthority("CHANGE_PASSWORD_PRIVILEGE")));
        SecurityContextHolder.getContext().setAuthentication(auth);*/
        return "Authorize";
    }

    @Override
    public void changeUserPassword(User user, String password) {
        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);
    }

    @Override
    public boolean checkIfValidOldPassword(final User user, final String oldPassword) {
        return passwordEncoder.matches(oldPassword, user.getPassword());
    }

    @Override
    public boolean isUserExist(String mail) throws ResourceNotFoundException {
        return getByMail(mail) != null;
    }

    @Override
    public boolean isAdmin(Long id, String mail) throws ResourceNotFoundException {
        User user = userRepository.findByIdAndMail(id,mail);
        Role userRole = roleRepository.findByName(RoleName.ADMIN_ROLE).
                orElseThrow(() -> new ResourceNotFoundException("Role", "RoleName", RoleName.ADMIN_ROLE));
        boolean isAdmin = false;
        if(user.getRoles().contains(userRole)) {
            isAdmin = true;
        }
        return isAdmin;
    }

    @Override
    public UserDto fetchUserAfterAuth(String mail) throws ResourceNotFoundException {
        User user = getByMail(mail);
        connectUser(user);
        UserDto userDto = UserUtil.toUser(user);
        return userDto;
    }

    @Override
    public User connectUser(User user) throws ResourceNotFoundException {
        LOGGER.info("Connecting user : " + user.toString());
        user.setConnected(true);
        return user;
    }

    @Override
    public User disconnectUser(User user) throws ResourceNotFoundException {
        LOGGER.info("Disconnecting user : " + user.toString());
        user.setConnected(false);
        return user;
    }
}
