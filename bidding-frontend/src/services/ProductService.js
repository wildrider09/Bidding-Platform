import axios from 'axios';
const BASE_URL = "http://localhost:9090/product"
class ProductService{
    getProducts(){
        return axios.get(BASE_URL);
    }
}

export default new ProductService()