package com.validity.monolithstarter.rest;

import com.validity.monolithstarter.domain.Person;
import com.validity.monolithstarter.service.FileDataService;
import com.validity.monolithstarter.service.HelloService;

import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import javax.inject.Inject;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class HelloController {

    @Inject
    private HelloService helloService;

    @Inject
    private FileDataService fileDataService;

    @GetMapping("/hello")
    public String getHelloMessage() {
        return helloService.getHelloMessage();
    }

    @GetMapping("/data")
    public List<Person> getData(){
        JSONArray jsonArray = new JSONArray(fileDataService.getData());

        return fileDataService.getData();
    }

    @GetMapping("/duplicates")
    public List<Person> getDuplicates(){
        fileDataService.getData();

        JSONArray jsonArray = new JSONArray(fileDataService.getDuplicates());
        return fileDataService.getDuplicates();
    }


}
