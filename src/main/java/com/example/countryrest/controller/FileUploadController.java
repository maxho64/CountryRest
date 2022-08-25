package com.example.countryrest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;

@Controller
public class FileUploadController {

    @RequestMapping(value = "upload", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<String> handleUpload(@RequestParam("name") String name,
                                       @RequestParam("file") MultipartFile file) {
        String result;
        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                BufferedOutputStream stream =
                        new BufferedOutputStream(new FileOutputStream(name + "-uploaded"));
                stream.write(bytes);
                stream.close();
                result = "Вы удачно загрузили " + name + " в " + name + "-uploaded !";
                return new ResponseEntity<>(result, HttpStatus.CREATED);
            } catch (Exception e) {
                result = "Вам не удалось загрузить " + name + " => " + e.getMessage();
                return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            result = "Вам не удалось загрузить " + name + " потому что файл пустой.";
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
    }
}
