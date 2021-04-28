import './App.css';
import ListProductsComponent from './components/ListProductsComponent';
import Header from './components/Header';
import { Route, BrowserRouter as Router, Switch } from 'react-router-dom';
import RegisterComponent from './components/RegisterComponent';
import AddProductComponent from './components/AddProductComponent';
import ProductDetailComponent from './components/ProductDetailComponent';
function App() {
  return (
    <div>
      <Router>
          <Header/>
          <div className="container">
            <Switch>
              <Route path="/" exact component={ListProductsComponent}></Route>
              <Route path="/products" component={ListProductsComponent}></Route>
              <Route path="/register" component={RegisterComponent}></Route>
              <Route path="/add-product" component={AddProductComponent}></Route>
              <Route path="/product-detail/:id" component={ProductDetailComponent}></Route>
            </Switch>         
        </div>
      </Router>
    </div>
  );
}

export default App;
