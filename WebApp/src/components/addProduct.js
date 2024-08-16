import React, { useEffect, useState } from 'react';
import axios from "axios";
import './addProduct.css';
import Environment from "../Environment";
import Form from './Functions/Form';

const AddProduct = () => {
  
  const URL = Environment.INVENTORY_API_URL;
  const [product, setProduct] = useState({
    productId: '',
    productName: '',
    brandName: '',
    supplierName: '',
    productCost: 0,
    category: [],
    subCategory: '',
  });

  const [excelError, setExcelError] = useState('');
  const [productError, setProductError] = useState('');
  const [fetchedCategories,setFetchedCategories] = useState([]);

  
  useEffect (() => {
    const fetchData = async () => {
      var config = {
        method: 'post',
        url: URL+'/getAllProductCategory',
        headers:{"Content-Type": "application/json"},
        data : {}
      };
      axios(config).then((res) => {
        setFetchedCategories(res.data.dataList);
      }).catch((err) => {
        setFetchedCategories([]);
      });
    };
    fetchData();
  }, [URL]);

  useEffect(() => {
    const timerId = setTimeout(() => {
      setExcelError('');
      setProductError('');
    }, 5000);

    return () => clearTimeout(timerId);
  }, [excelError,productError]);

  const handleSubmit = (event) => {
    event.preventDefault();

    var config = {
      method: 'post',
      url: URL+'/saveProductDetails',
      headers:{'Content-Type': 'application/json'},
      data : product
    };

    axios(config).then((res) => {
      setProductError(res.data.message);
    }).catch((err) => {
      setProductError(err.data.message);
    });
    
  };

  const handleFileUpload = (event) => {
    const formData = new FormData();
    formData.append('file', event.target.files[0]);
  
    var config = {
      method: 'post',
      url: URL+'/saveProductDetailsFromExcel',
      headers:{'Content-Type': 'multipart/form-data'},
      data : formData
    };

    axios(config).then((res) => {
       setExcelError(res.response.data.message);
       event.target.value = '';
    }).catch((err) => {
       setExcelError(err.response.data.message);
    });
  };

  return (
    <div className="add-product-page">
      
      <div className="upload-button-container">
        <label htmlFor="fileUpload">Upload Excel File (Optional):</label>
        <input
          type="file"
          id="fileUpload"
          accept=".xlsx,.xlsm,.xlsb,.xls"
          onChange={handleFileUpload}
        />
      </div>
      {excelError && <p className="error">{excelError}</p>}
      <h2>ADD PRODUCT</h2>
      <form onSubmit={handleSubmit}>
      <Form props={[product, setProduct]} detail={"productId"} type={"text"}/>
      <Form props={[product, setProduct]} detail={"productName"} type={"text"}/>
      <Form props={[product, setProduct]} detail={"brandName"} type={"text"}/>
      <Form props={[product, setProduct]} detail={"supplierName"} type={"text"}/>
      <Form props={[product, setProduct]} detail={"productCost"} type={"number"}/>
      <Form props={[product, setProduct]} detail={"category"} type={"dropdown"} list={fetchedCategories}/>
      <Form props={[product, setProduct]} detail={"subCategory"} type={"text"}/>
      <button type="submit">Add Product</button>
      {productError && <p className="error">{productError}</p>}
      </form>
    </div>
  );
};

export default AddProduct;
