package com.musicallcommunity.musicallback.controller;

import com.musicallcommunity.musicallback.config.AppProperties;
import com.musicallcommunity.musicallback.dto.MailDto;
import com.musicallcommunity.musicallback.dto.ResetPasswordDto;
import com.musicallcommunity.musicallback.model.User;
import com.musicallcommunity.musicallback.payload.ApiResponse;
import com.musicallcommunity.musicallback.service.EmailSenderService;
import com.musicallcommunity.musicallback.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Locale;
import java.util.UUID;

@RestController
@Validated
@RequestMapping(value = "/api/password")
public class PasswordController {

    private final static Logger LOGGER = LoggerFactory.getLogger(PasswordController.class);

    private UserService userService;

    private EmailSenderService emailSenderService;

    private AppProperties appProperties;

    @Autowired
    public PasswordController(UserService userService, EmailSenderService emailSenderService, AppProperties appProperties) {
        this.userService = userService;
        this.emailSenderService = emailSenderService;
        this.appProperties = appProperties;
    }

    @GetMapping(value = "", consumes = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ApiResponse<User> getUserbyMail(@RequestBody final MailDto pasDto) {
        LOGGER.info("Fetching User with mail {}", pasDto.getMail());
        User user = userService.getByMail(pasDto.getMail());
        if (user == null) {
            LOGGER.error("User with mail {} is not found.", pasDto.getMail());
            return new ApiResponse<>(HttpStatus.NOT_FOUND.value(), "User with mail " + pasDto.getMail()
                    + " not found.", userService.getByMail(pasDto.getMail()));

        }
        return new ApiResponse<>(HttpStatus.OK.value(), "User with mail " + pasDto.getMail()
                + " fetched successfully.", userService.getByMail(pasDto.getMail()));

    }

    @PostMapping(value = "/forgot", consumes = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ApiResponse<SimpleMailMessage> forgotPassword(final HttpServletRequest request, @RequestBody final MailDto pasDto) {
        LOGGER.info("Reset user password with mail {}", pasDto.getMail());
        final User user = userService.getByMail(pasDto.getMail());
        String token = null;
        if (user != null) {
            token = UUID.randomUUID().toString();
            userService.createPasswordResetTokenForUser(user, token);

            emailSenderService.sendEmail(constructResetTokenEmail(getAppUrl(request), request.getLocale(), token, user));
        }
        return new ApiResponse<>(HttpStatus.OK.value(), "Password reset link as be sent to " + pasDto.getMail(), constructResetTokenEmail(getAppUrl(request), request.getLocale(), token, user));

    }

    @GetMapping(value = "/validate")
    public ResponseEntity<Object> validateChangePassword(@RequestParam("id") final Long id, @RequestParam("token") final String token) throws URISyntaxException {

        final String result = userService.validatePasswordResetToken(id, token);
        if (result.equals("Authorize")) {
            URI uri = new URI("http://localhost:4200/reset-password-form");
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(uri);
            return new ResponseEntity<>(headers, HttpStatus.FORBIDDEN);
        } else {
            URI uri = new URI("http://localhost:4200/retry-forgot-password");
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(uri);
            return new ResponseEntity<>(headers, HttpStatus.SEE_OTHER);
        }
    }

    @PostMapping(value = "/save", consumes = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ApiResponse<User> savePassword(@RequestParam("id") final Long id, @RequestParam("token") final String token,
                                          @RequestBody @Valid ResetPasswordDto passDto) {
        User user = userService.getByToken(token);
        userService.changeUserPassword(user, passDto.getPassword());
        return new ApiResponse<>(HttpStatus.OK.value(), "User password update successfully", user);
    }


    private SimpleMailMessage constructResetTokenEmail(final String contextPath, final Locale locale, final String token, final User user) {
        final String url = contextPath + "/password/validate?id=" + user.getId() + "&token=" + token;
        final String message = "To complete the password reset process, please click here: ";
        return constructEmail("Reset Password", message + " \r\n" + url, user);
    }

    private SimpleMailMessage constructEmail(String subject, String body, User user) {
        final SimpleMailMessage email = new SimpleMailMessage();
        email.setSubject(subject);
        email.setText(body);
        email.setTo(user.getMail());
        // LOGGER.error("env email : ", env.getProperty("support.email"));
        email.setFrom(appProperties.getSupport().getMail());
        return email;
    }

    private String getAppUrl(HttpServletRequest request) {
        return "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
    }

}