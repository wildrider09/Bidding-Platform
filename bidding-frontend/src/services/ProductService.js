import axios from 'axios';
const BASE_URL = "http://localhost:9090/product"
class ProductService{
    getProducts(){
        return axios.get(BASE_URL);
    }

    addProduct(product){
        return axios.post(BASE_URL, product);
    }

    getProductById(id){
        return axios.get(BASE_URL+'/'+id);
    }
}

export default new ProductService()