import axios from 'axios';
const BASE_URL = "http://localhost:9090/bids/"
class BidService {
   addBid(bid){
       return axios.post(BASE_URL,bid);
   }

   selectBid(id, bid){
       return axios.put(BASE_URL+'/'+id, bid)
   }
}

export default new BidService();