package com.project.inventory.controller;

import com.project.inventory.entity.DataWrapper;
import com.project.inventory.entity.ProductDetails;
import com.project.inventory.service.ManageProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

@RequestMapping("/api")
@RestController
public class ManageProductController {

    @Autowired
    ManageProductService manageProductService;

    @Value("${target.folderpath}")
    private String folderPath;

    @PostMapping(value = "/saveProductDetails" , consumes = "application/json")
    @ResponseBody
    public ResponseEntity<?> saveProductDetails(@RequestBody ProductDetails productDetails){
        DataWrapper dataWrapper = new DataWrapper();
        try{
            String message = manageProductService.saveProductDetails(productDetails);
            if (message.contains("success")) {
                dataWrapper.setMessage(message);
                dataWrapper.setStatus("SUCCESS");
            } else {
                dataWrapper.setMessage(message);
                dataWrapper.setStatus("ERROR");
            }
            return new ResponseEntity<>(dataWrapper, HttpStatus.OK);
        }catch (Exception e){
            dataWrapper.setMessage("ERROR:" + e.getMessage());
            dataWrapper.setStatus("ERROR");
            return new ResponseEntity<>(dataWrapper, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/getAllProductCategory")
    @ResponseBody
    public ResponseEntity<?> getAllProductCategory(){
        DataWrapper dataWrapper = new DataWrapper();
        try{
            List<String> productCategoryList = manageProductService.getAllProductCategory();
            if (!productCategoryList.isEmpty()) {
                dataWrapper.setDataList(productCategoryList);
                dataWrapper.setMessage("Categories fetched successfully!!");
                dataWrapper.setStatus("SUCCESS");
            } else {
                dataWrapper.setDataList(null);
                dataWrapper.setMessage("No Categories found!!");
                dataWrapper.setStatus("ERROR");
            }
            return new ResponseEntity<>(dataWrapper, HttpStatus.OK);
        }catch (Exception e){
            dataWrapper.setMessage("ERROR:" + e.getMessage());
            dataWrapper.setStatus("ERROR");
            return new ResponseEntity<>(dataWrapper, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/getAllProductDetails")
    @ResponseBody
    public ResponseEntity<?> getAllProductDetails(){
        DataWrapper dataWrapper = new DataWrapper();
        try{
            List<ProductDetails> productList = manageProductService.getAllProductDetails();
            if (!productList.isEmpty()) {
                dataWrapper.setDataList(productList);
                dataWrapper.setMessage("Products fetched successfully!!");
                dataWrapper.setStatus("SUCCESS");
            } else {
                dataWrapper.setDataList(null);
                dataWrapper.setMessage("No Products found!!");
                dataWrapper.setStatus("ERROR");
            }
            return new ResponseEntity<>(dataWrapper, HttpStatus.OK);
        }catch (Exception e){
            dataWrapper.setMessage("ERROR:" + e.getMessage());
            dataWrapper.setStatus("ERROR");
            return new ResponseEntity<>(dataWrapper, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/saveProductDetailsFromExcel")
    @ResponseBody
    public ResponseEntity<?> saveProductDetailsFromExcel(@RequestParam(value = "file") MultipartFile xmlFile){
        DataWrapper dataWrapper = new DataWrapper();
        String path = folderPath;
        StringBuilder message = new StringBuilder();
        File file = new File(path + xmlFile.getOriginalFilename());
        try{
            xmlFile.transferTo(file);
            List<ProductDetails> productDetailsList = manageProductService.readProductDetailsFromExcel(file);
            message.append(manageProductService.saveProductDetailsList(productDetailsList));
            dataWrapper.setMessage(String.valueOf(message));
            dataWrapper.setStatus("SUCCESS");
            return new ResponseEntity<>(dataWrapper, HttpStatus.OK);
        }catch (Exception e){
            dataWrapper.setMessage("Error reading file!!");
            dataWrapper.setStatus("ERROR");
            return new ResponseEntity<>(dataWrapper, HttpStatus.BAD_REQUEST);
        }finally {
            file.delete();
        }
    }

}
