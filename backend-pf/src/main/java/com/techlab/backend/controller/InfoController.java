package com.techlab.backend.controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.techlab.backend.Error;

import java.util.List;
import java.util.ArrayList;

@RestController
public class InfoController {
    private List<Error> errores;

    
    
    public InfoController() {
        this.errores=new ArrayList<>();
        this.addError(1, "Mensaje de error por defecto.");
    }

    public void addError(int id, String message){
        errores.add(new Error(id,message));
    }

    public Error getErrorById(int id){
        return errores.stream()
            .filter(p->p.getId() == id)
            .findFirst()
            .orElse(null);
    }

    private Error guardarErrorMessage(Error error){
        this.errores.add(error);
        return error;
    }

    @PostMapping("info")
    public Error postError(@RequestBody Error error){
        return guardarErrorMessage(error);
    }

    @GetMapping("info/{id}")
    public Error getError(@PathVariable int id){
        return this.getErrorById(id);
    }

    // @GetMapping("info")
    // public String error(){
    //     return "Hola mundo!";
    // }

    


}
