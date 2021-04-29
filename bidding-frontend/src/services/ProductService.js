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

    updateProduct(id, product){
        return axios.put(BASE_URL+'/'+id, product);
    }
}

export default new ProductService()