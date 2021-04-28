import axios from 'axios';
const BASE_URL = "http://localhost:9090/user/"
class RegisterService{
    registerUser(user){
        return axios.post(BASE_URL, user);
    }
}
export default new RegisterService();