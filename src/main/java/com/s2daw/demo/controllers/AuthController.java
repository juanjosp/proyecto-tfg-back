package com.s2daw.demo.controllers;
import com.s2daw.demo.models.Profesor;
import com.s2daw.demo.services.ProfesorService;
import com.s2daw.demo.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthController {

    private final ProfesorService profesorService;



    @Autowired
    public AuthController(ProfesorService profesorService, JWTUtil jwtUtil) {
        this.profesorService = profesorService;

    }

    @RequestMapping(value="/login",method= RequestMethod.POST)
    public String login(@RequestBody Profesor profesor){



        if (profesorService.obtenerProfesorPorCredenciales(profesor)) {
            return "OK";
        }

        return "FAIL";
    }
}
