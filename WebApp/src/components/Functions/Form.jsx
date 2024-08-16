import React from 'react';


function Form({props,detail,type,list}) {
  const [prop,setProp] = props
  const detailName = detail.toUpperCase();
  const handleChange = (event) => {
    const { name, value } = event.target;
    setProp({ ...prop, [name]: value });
  };

  return (
    <>
      {type === "text" || type === "number" || type === "password"? (
      <>
        <label htmlFor={detail} className="label">{detailName}:</label>
        <input type={type} name={detail} value={prop.detail} onChange={handleChange} required className="input"/>
      </>
    ) : (
      <>
        <label htmlFor={detail} >{detailName}:</label>
        <select name={detail} value={prop.detail} onChange={handleChange} required>
        <option value="">Select Category</option>
          {list?.map((res) => (
            <option key={res} value={res}>
              {res}
            </option>
          ))}
        </select>
      </>
    )}
    </>
  );
}

export default Form;
