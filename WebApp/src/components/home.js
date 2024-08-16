import React from 'react';
import { Link, useNavigate } from 'react-router-dom'; // Import Link for routing
import './home.css';


const Home = () => {
    const navigate=useNavigate()
    const addProductClick = () => {
        // Get navigate function within component
       navigate("/addProduct");
     };
     const viewstock= () => {
        // Get navigate function within component
       navigate("/viewStock");
     };
  return (
    <div className="home-page">
      <header>
        <nav>
          <ul> 
            <button className="addProduct-button" onClick={addProductClick}>
              Add Product
            </button>
            <button className="view-button" onClick={viewstock}>
              Add Product
            </button>
          </ul>
        </nav>
      </header>
      <main>
        {/* <h1>Welcome to the Inventory Management System!</h1> */}
        <p>This system allows you to manage your product inventory, including adding new products and viewing existing stock levels.</p>
      </main>
    </div>
  );
};

export default Home;
