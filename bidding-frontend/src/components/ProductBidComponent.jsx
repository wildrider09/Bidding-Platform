import React, { Component } from 'react';
import UserService from '../services/UserService';
import DropdownButton from 'react-bootstrap/DropdownButton';
import Dropdown from 'react-bootstrap/Dropdown'
import ProductService from '../services/ProductService';
import { Container, Row, Col } from 'react-grid-system';
import BidService from '../services/BidService';

class ProductBidComponent extends Component {
    constructor(props){
        super(props);

        this.state = {
            selected: false,
            bidAmount: '',
            bidProductId: this.props.match.params.id,
            bidOwnerId: '',
            productName: '',
            description: '',
            minimum_bid: '',
            active: '',
            ownerId: '',
            owner: '',
            user: []
        }
        this.changeBidOwnerHandler = this.changeBidOwnerHandler.bind(this);
        this.saveBid = this.saveBid.bind(this);
    }

    componentDidMount(){
        ProductService.getProductById(this.state.bidProductId).then((res) => {
            let product = res.data;
            this.setState({
                productName: product.productName,
                description: product.description,
                minimum_bid: product.minimum_bid,
                active: product.active,
                ownerId: product.user.id,  
                owner: product.user                          
            })
        });
        UserService.getUsers().then((res) => {
            this.setState({user : res.data});
        });
    }

    cancel(){
        this.props.history.push('/products');
    }

    changeBidOwnerHandler = (e) => {
        this.setState({bidOwnerId: e});
    }

    changeBidHandler = (e) => {
        this.setState({bidAmount: e.target.value});
    }

    saveBid = (event) =>{
        event.preventDefault();
        let newBid = {
            bidAmount: this.state.bidAmount,
            selected : this.state.selected,
            bidProductId: this.state.bidProductId,
            bidOwnerId: this.state.bidOwnerId
        }
        if(parseInt(newBid.bidAmount) < parseInt(this.state.minimum_bid)){
            alert("You have to bid more than the minimum amount");
            window.location.reload();
        }
        else{
            BidService.addBid(newBid).then((res) => {
                alert("Bid added successfully");
                this.props.history.push('/products');
            })
        }
        
    }

    render() {
        return (
            <div>
                <div className = "products">
                    <Container style={{margin: "20px"}}>
                        <Row>                                     
                                    <Col xs = {6}>
                                        <div className="shadow p-3 mb-5 bg-white rounded">
                                            <div>
                                                <p>Product Name: {this.state.productName}</p>
                                                <p>Description: {this.state.description}</p>
                                                <p>Minimum Bid: {this.state.minimum_bid}</p>
                                                <p>Owner of the Product: {this.state.owner.id} / {this.state.owner.firstName}</p>
                                                
                                                <p>Live: {this.state.active.toString()}</p>
                                            </div>                                            
                                        </div>
                                    </Col>  
                                    <Col xs = {6}>
                                        <div className = "card col-md-6 offset-md-3 offset-md-3">
                                            <h3 className="text-center">Bid on Product</h3>
                                            <div className="card-body">
                                                <form>
                                                    <div className="form-group">
                                                        <label>Bid Amount</label>
                                                        <input placeholder={this.state.minimum_bid} name="BidAmount" className="form-control"
                                                            value={this.state.bidAmount} onChange={this.changeBidHandler}/>
                                                    </div>
                                                    <DropdownButton
                                                    alignRight
                                                    title="Owner ID "
                                                    id="dropdown-menu-align-right"
                                                    onSelect = {this.changeBidOwnerHandler}   
                                                    style = {{marginBottom: "10px"}}                                 
                                                    >
                                                        {
                                                            this.state.user.map(
                                                                user =>
                                                                <Dropdown.Item eventKey={user.id}>{user.id}</Dropdown.Item>
                                                            )
                                                        }
                                                    </DropdownButton>
                                                    <button className="btn btn-success" onClick={this.saveBid}>Bid</button>
                                                    <button className="btn btn-danger" onClick={this.cancel.bind(this)} style={{marginLeft: "10px"}}>Cancel</button>
                                                </form>
                                            </div>
                                        </div>
                                    </Col>                           
                        </Row>
                    </Container>
                </div>
            </div>
        );
    }
}

export default ProductBidComponent;