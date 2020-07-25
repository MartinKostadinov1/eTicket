package com.mkostadinov.eticketbackend.web.auth;

import com.mkostadinov.eticketbackend.config.AuthConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.mkostadinov.eticketbackend.constants.GlobalConstants.API_URL;

@RestController
public class LogoutController {
}