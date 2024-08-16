import React, { useState } from 'react';
import axios from 'axios';

const BuyProductPage = () => {
  const [productId, setProductId] = useState('');
  const [quantity, setQuantity] = useState(1);
  const [error, setError] = useState(null);

  const handleSubmit = async (event) => {
    event.preventDefault();

    const orderData = {
      productId,
      quantity,
    };

    try {
      const response = await axios.post('/api/buyProduct', orderData); // Replace with your API endpoint
      console.log('Order placed successfully:', response.data);
      // Handle successful order placement, e.g., show a success message
    } catch (error) {
      setError(error.response.data.message || 'Error placing order');
    }
  };

  return (
    <div className="buy-product-page">
      <h2>Buy Product</h2>
      <form onSubmit={handleSubmit}>
        <label htmlFor="productId">Product ID:</label>
        <input
          type="text"
          id="productId"
          value={productId}
          onChange={(e) => setProductId(e.target.value)}
          required
        />
        <label htmlFor="quantity">Quantity:</label>
        <input
          type="number"
          id="quantity"
          value={quantity}
          onChange={(e) => setQuantity(parseInt(e.target.value))}
          min="1"
          required
        />
        {error && <p className="error">{error}</p>}
        <button type="submit">Buy Product</button>
      </form>
    </div>
  );
};

export default BuyProductPage;