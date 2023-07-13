package com.app.shopsmart.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value="/api", consumes = "multipart/form-data", produces = { "application/json", "application/xml" })
public class FileController {

  @Value("${file.upload-dir}")
  String FILE_DIRECTORY;


  @PostMapping("/upload")
  public ResponseEntity<?> upload (
    @RequestParam("id") Long id,
    @RequestPart(name = "archivo", required = true) MultipartFile archivo
  ) throws IOException {

    Map<String, Object> response = new HashMap<>();


    if(!archivo.isEmpty()) {
      String name = archivo.getOriginalFilename();
      String userHome = System.getProperty("user.home");
      Path ruta = Paths.get(userHome, "Documentos", "ImagesShopsmart", name).toAbsolutePath();

      try {
        Files.copy(archivo.getInputStream(), ruta);
      } catch(IOException e) {
        response.put("mensaje", "Error al subir la imagen "+ e.getMessage());
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
      }

      response.put("mensaje", "Has subido correctamente la imagen");
    }
    return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
  }


}
