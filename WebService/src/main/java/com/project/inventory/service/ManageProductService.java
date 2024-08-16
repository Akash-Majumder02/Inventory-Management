package com.project.inventory.service;

import com.project.inventory.dao.ManageProductDao;
import com.project.inventory.entity.ProductDetails;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class ManageProductService {

    @Autowired
    ManageProductDao manageProductDao;

    public String saveProductDetails(ProductDetails productDetails) {
        int retVal = manageProductDao.saveProductDetails(productDetails);
        if(retVal>0)
            return "Product saved successfully";
        else
            return "Error while saving product";
    }

    public List<String> getAllProductCategory() {
        return manageProductDao.getAllProductCategory();
    }

    public List<ProductDetails> getAllProductDetails() {
        return manageProductDao.getAllProductDetails();
    }

    public List<ProductDetails> readProductDetailsFromExcel(File file) throws IOException {
        List<ProductDetails> productDetailsList = new ArrayList<>();
        try(FileInputStream inputStream = new FileInputStream(file);
            Workbook workbook = new XSSFWorkbook(inputStream);){
            Sheet sheet = workbook.getSheetAt(1);
            for (Row row : sheet) {
                if (row.getRowNum() == 0)
                    continue;
                ProductDetails productDetails = new ProductDetails();
                for (Cell cell : row){
                    int columnIndex = cell.getColumnIndex();
                    switch (columnIndex){
                        case 0:
                            continue;
                        case 1:
                            productDetails.setProductName(cell.getStringCellValue());
                            break;
                        case 2:
                            productDetails.setBrandName(cell.getStringCellValue());
                            break;
                        case 3:
                            productDetails.setSupplierName(cell.getStringCellValue());
                            break;
                        case 4:
                            productDetails.setProductCost(cell.getNumericCellValue());
                            break;
                        case 5:
                            productDetails.setCategory(cell.getStringCellValue());
                            break;
                        case 6:
                            productDetails.setSubCategory(cell.getStringCellValue());
                            break;
                    }
                }
                productDetailsList.add(productDetails);
            }
            workbook.close();
            inputStream.close();
            return productDetailsList;
        }catch (FileNotFoundException fileEx){
            throw new FileNotFoundException("Unable to find file: "+fileEx.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public String saveProductDetailsList(List<ProductDetails> productDetailsList) {
            AtomicInteger retVal = new AtomicInteger();
            productDetailsList.forEach(productDetail -> {
               retVal.set(manageProductDao.saveProductDetails(productDetail));
            });
            if(retVal.get() >0)
                return "Products saved successfully!! ";
            else
                return "Error while saving product details ";

    }

}
