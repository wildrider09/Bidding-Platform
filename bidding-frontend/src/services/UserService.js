import axios from 'axios';
const BASE_URL = "http://localhost:9090/user";

class UserService{
     getUsers(){
        return axios.get(BASE_URL);
    }
}
export default new UserService();