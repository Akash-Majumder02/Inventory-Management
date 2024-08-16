// import React, { useEffect, useState } from 'react';
// import axios from "axios";
// import './addProduct.css';
// import Environment from "../Environment";

// const AddProduct = () => {
  
//   const URL = Environment.INVENTORY_API_URL;

//   const [productId, setProductId] = useState('');
//   const [productName, setProductName] = useState('');
//   const [brand, setBrand] = useState('');
//   const [supplier, setSupplier] = useState('');
//   const [cost, setCost] = useState(0);
//   const [category, setCategory] = useState('');
//   const [subcategory, setSubcategory] = useState('');
//   const [excelSubmitMessage, setExcelSubmitMessage] = useState('');
//   const [saveProductMessage, setSaveProductMessage] = useState('');
//   const [fetchedCategories,setFetchedCategories] = useState([]);

//   useEffect (() => {
//     const fetchData = async () => {
//       var config = {
//         method: 'post',
//         url: URL+'/getAllProductCategory',
//         headers:{"Content-Type": "application/json"},
//         data : {}
//       };
//       axios(config).then((res) => {
//         setFetchedCategories(res.data.dataList);
//       }).catch((err) => {
//         setFetchedCategories([]);
//       });
//     };
//     fetchData();
//   },[]);

//   useEffect(() => {
//     const timerId = setTimeout(() => {
//       setExcelSubmitMessage('');
//       setSaveProductMessage('');
//     }, 5000);

//     return () => clearTimeout(timerId);
//   }, [excelSubmitMessage,saveProductMessage]);

//   const handleSubmit = (event) => {
//     event.preventDefault();

//     const newProduct = {
//       productId :productId,
//       productName : productName,
//       brandName : brand,
//       supplierName : supplier,
//       productCost : cost,
//       category : category, 
//       subCategory : subcategory,
//     };

//     var config = {
//       method: 'post',
//       url: URL+'/saveProductDetails',
//       headers:{'Content-Type': 'application/json'},
//       data : newProduct
//     };

//     axios(config).then((res) => {
//       setSaveProductMessage(res.data.message);
//     }).catch((err) => {
//       setSaveProductMessage(err.data.message);
//     });
    
//   };

//   const handleFileUpload = (event) => {
//     const formData = new FormData();
//     formData.append('file', event.target.files[0]);
  
//     var config = {
//       method: 'post',
//       url: URL+'/saveProductDetailsFromExcel',
//       headers:{'Content-Type': 'multipart/form-data'},
//       data : formData
//     };

//     axios(config).then((res) => {
//       setExcelSubmitMessage(res.response.data.message);
//     }).catch((err) => {
//       setExcelSubmitMessage(err.response.data.message);
//     });
//   };

//   return (
//     <div className="add-product-page">
      
//       <div className="upload-button-container">
//         <label htmlFor="fileUpload">Upload Excel File (Optional):</label>
//         <input
//           type="file"
//           id="fileUpload"
//           accept=".xlsx,.xlsm,.xlsb,.xls"
//           onChange={handleFileUpload}
//         />
//       </div>
//       {excelSubmitMessage && <p className="message">{excelSubmitMessage}</p>}
//       <h2>Add Product</h2>
//       <form onSubmit={handleSubmit}>
//         <label htmlFor="productId">Product ID:</label>
//         <input
//           type="text"
//           id="productId"
//           value={productId}
//           onChange={(e) => setProductId(e.target.value)}
//           required
//         />
//         <label htmlFor="productName">Product Name:</label>
//         <input
//           type="text"
//           id="productName"
//           value={productName}
//           onChange={(e) => setProductName(e.target.value)}
//           required
//         />
//         <label htmlFor="brand">Brand:</label>
//         <input
//           type="text"
//           id="brand"
//           value={brand}
//           onChange={(e) => setBrand(e.target.value)}
//           required
//         />
//         <label htmlFor="supplier">Supplier:</label>
//         <input
//           type="text"
//           id="supplier"
//           value={supplier}
//           onChange={(e) => setSupplier(e.target.value)}
//           required
//         />
//         <label htmlFor="cost">Cost:</label>
//         <input
//           type="number"
//           id="cost"
//           value={cost}
//           onChange={(e) => setCost(e.target.value)}
//           required
//         />
//         <label htmlFor="category">Category:</label>
//         <select
//           id="category"
//           value={category}
//           onChange={(e) => setCategory(e.target.value)}
//           required
//         >
//           <option value="">Select Category</option>
//           {fetchedCategories?.map((res) => (
//             <option key={res} value={res}>
//               {res}
//             </option>
//           ))}
//         </select>
//         <label htmlFor="subcategory">Subcategory:</label>
//         <input
//           type="text"
//           id="subcategory"
//           value={subcategory}
//           onChange={(e) => setSubcategory(e.target.value)}
//           required
//         />
         
//         <button type="submit">Add Product</button>
//         {saveProductMessage && <p className="message">{saveProductMessage}</p>}
//       </form>
//     </div>
//   );
// };

// export default AddProduct;
