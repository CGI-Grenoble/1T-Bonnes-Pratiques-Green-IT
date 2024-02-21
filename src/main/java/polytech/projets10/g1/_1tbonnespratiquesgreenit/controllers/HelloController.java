package polytech.projets10.g1._1tbonnespratiquesgreenit.controllers;

import polytech.projets10.g1._1tbonnespratiquesgreenit.security.CustomJwt;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.text.MessageFormat;

@RestController
@CrossOrigin(
        origins = "http://localhost:4200",
        allowedHeaders = "*",
        methods = { RequestMethod.GET }
)
public class HelloController {

    @GetMapping("/hello")
    @PreAuthorize("hasAuthority('ROLE_org-admin')")
    public Message hello() {
        var jwt = (CustomJwt) SecurityContextHolder.getContext().getAuthentication();
        var message = MessageFormat
                .format("Hello {0} {1}",
                        jwt.getFirstname(), jwt.getLastname());
        return new Message(message);
    }

    record Message(String message) {}
}
