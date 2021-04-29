import axios from 'axios';
const BASE_URL = "http://localhost:9090/bids/"
class BidService {
   addBid(bid){
       return axios.post(BASE_URL,bid);
   }
}

export default new BidService();