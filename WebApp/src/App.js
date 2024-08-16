import logo from './logo.svg';
import './App.css';
import './components/homepage'
import './components/login'
import HomePage from './components/homepage';
import { BrowserRouter } from 'react-router-dom';
import RoutesComponent from "./components/RoutesComponent";

function App() {
  return (
    // <div className="App">
    //   <header className="App-header">
    //     <img src={logo} className="App-logo" alt="logo" />
    //     <p>
    //       Edit <code>src/App.js</code> and save to reload.
    //     </p>
    //     <a
    //       className="App-link"
    //       href="https://reactjs.org"
    //       target="_blank"
    //       rel="noopener noreferrer"
    //     >
    //       Learn React
    //     </a>
    //   </header>
    // </div>
    <BrowserRouter>
    
      <div>
        <HomePage/>
      <RoutesComponent/>
      
      </div>
      </BrowserRouter>
    

  );
}

export default App;
