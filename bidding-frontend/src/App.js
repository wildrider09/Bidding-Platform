import './App.css';
// import React, {useState} from 'react';
// import {ThemeProvider} from 'styled-components';
// import {getTheme} from "./getTheme";
// import THEMES from './constants/themes'

// import { Header, AppLink } from './styles';
import ListProductsComponent from './components/ListProductsComponent';
import NavBar from './components/Header';
import { Route, BrowserRouter as Router, Switch } from 'react-router-dom';
import RegisterComponent from './components/RegisterComponent';
import AddProductComponent from './components/AddProductComponent';
import ProductDetailComponent from './components/ProductDetailComponent';
import UpdateProductComponent from './components/UpdateProductComponent';
import ProductBidComponent from './components/ProductBidComponent';
import Container from '@material-ui/core/Container'
import Footer from './components/footer'
import image from './Homebid.jpg'
function App() {

  // const styles = {
  //   paperContainer: {
  //       backgroundImage: `url(${image})`
        
  //       }
  //       };
  // const [themeName, setThemeName] = useState(THEMES.BASIC);
  return (
    // <ThemeProvider theme={getTheme(themeName)}>
  
    <div>
      <Router>
      <div className="fixed-container">
          <NavBar />
      </div>
          <div >
          
          <div className="container">
            <Switch>
              <Route path="/" exact component={ListProductsComponent}></Route>
              <Route path="/products" component={ListProductsComponent}></Route>
              <Route path="/register" component={RegisterComponent}></Route>
              <Route path="/add-product" component={AddProductComponent}></Route>
              <Route path="/product-detail/:id" component={ProductDetailComponent}></Route>
              <Route path="/update-product/:id" component={UpdateProductComponent}></Route>
              <Route path="/bid-product/:id" component={ProductBidComponent}></Route>
            </Switch>         
          </div>
          
          </div>
      </Router>
      <div id='footer'>

          <Footer/>
          </div>
    
    </div>
    // <div>
    // <button onClick={() => setThemeName(THEMES.APPLE)}>Apple</button>
    //       <button onClick={() => setThemeName(THEMES.DARCULA)}>Darcula</button>
    // </div>
    // </ThemeProvider>
  );
}

export default App;
