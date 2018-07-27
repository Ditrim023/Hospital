package com.hospital.controller;

import com.hospital.model.Activity;
import com.hospital.model.HospitalUser;
import com.hospital.repository.ActivityRepository;
import com.hospital.repository.HospitalUserRepository;
import com.hospital.service.HospitalUserService;
import com.hospital.utils.Util;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author Nikita Krutoguz
 */
@Controller
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SecurityController {

    private final HospitalUserService hospitalUserService;
    private final ActivityRepository activityRepository;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "/system/login";
    }

    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public String error() {
        return "/system/403";
    }

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public String profile(Model model) {
        final HospitalUser currentUser = hospitalUserService.findUserByLogin();
        model.addAttribute("currentUser",currentUser);
        model.addAttribute("patients",currentUser.getPatients());
        return "/system/profile";
    }
    @RequestMapping(value = "/activity", method = RequestMethod.GET)
    public String activity(Model model) {
        final List<Activity> activities = activityRepository.findAll();
        model.addAttribute("activities",activities);
        return "/system/activity";
    }

    @ResponseBody
    @RequestMapping(value = "/is-duplicate-user-login/{login}", method = RequestMethod.GET, produces = "application/json")
    public final boolean isDuplicateUser(@PathVariable("login")  String login) {
        return hospitalUserService.isDuplicateUser(login);
    }
}
