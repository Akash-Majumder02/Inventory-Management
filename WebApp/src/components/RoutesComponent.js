import React from 'react';
import { Routes, Route } from 'react-router-dom';
import LoginPage from './login'; // Import LoginPage component
import RegisterPage from './register';
import Home from './home'; // Import HomePage component
import AddProduct from './addProduct';
import BuyProductPage from './buy';

function RoutesComponent() {
  return (
    <Routes>
      <Route path="/" element={<LoginPage/>} />
      <Route path="/register" element={<RegisterPage/>} /> 
      <Route path='/home' element={<Home/>}/>{/* Add registration route */}
      <Route path='/addProduct'element={<AddProduct/>}/>
      <Route path='/buy' element={<BuyProductPage/>}/>   
    </Routes>
  );
}

export default RoutesComponent;
