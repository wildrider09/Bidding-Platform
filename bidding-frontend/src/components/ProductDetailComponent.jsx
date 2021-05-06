import React, { Component } from 'react';
import { Col, Container, Row } from 'react-grid-system';
import BidService from '../services/BidService';
import ProductService from '../services/ProductService';
import image from '../Product.png'
import Button from '@material-ui/core/Button' 
import GavelTwoToneIcon from '@material-ui/icons/GavelTwoTone';
import KeyboardArrowUpTwoToneIcon from '@material-ui/icons/KeyboardArrowUpTwoTone';

class ProductDetailComponent extends Component {
    constructor(props){
        super(props);

         this.state = {
            id: this.props.match.params.id,
            productName: '',
            description: '',
            minimum_bid: '',
            active: '',
            owner: '',
            bids : [],
            active: "Selected",
            inactive: ""
         }
    }

    componentDidMount(){
        ProductService.getProductById(this.state.id).then((res) => {
            let product = res.data;
            this.setState({
                productName: product.productName,
                description: product.description,
                minimum_bid: product.minimum_bid,
                active: product.active,
                owner: product.user,
                bids: product.bids                
            })
        })
    }

    updateProduct(id){
        this.props.history.push(`/update-product/${id}`)
    }

    selectBid(id, amount, bidOwnerId){
        let newBid = {
            bidAmount: amount,
            selected : true,
            bidProductId: this.state.id,
            bidOwnerId: bidOwnerId
        }
         let product = {
            productName: this.state.productName,
            description: this.state.description,
            minimum_bid: this.state.minimum_bid,
            active: false,
            ownerId: this.state.owner.id
        }
        BidService.selectBid(id, newBid).then((res) => {
            alert("Bid Selected Successfully");
        });
        ProductService.updateProduct(this.state.id, product).then(res => {
            this.props.history.push('/products');
        });
    }

    render() {
        return (
            <div className='bg-image' style={{
                backgroundImage:`url(${image})`,
                width: '100%',
                
                
            }}>
                <div className="product-details">
                    <Container style={{margin: "20px", paddingTop:'10px'}}>
                        <Row>
                            <Col xs = {6}>                                
                                 <div className="shadow p-3 mb-5 bg-white rounded">
                                     <h2>Product details</h2>
                                            <p>Product Name: {this.state.productName}</p>
                                            <p>Description: {this.state.description}</p>
                                            <p>Minimum Bid: {this.state.minimum_bid}</p>
                                            <p>Owner of the Product: {this.state.owner.firstName}</p>
                                            <p>Live: {this.state.active.toString()}</p>
                                            <Button variant='contained' color='secondary' endIcon={<KeyboardArrowUpTwoToneIcon/>} className="btn btn-success" onClick = {() => this.updateProduct(this.state.id)}>Update Product Details</Button>
                                        </div>
                            </Col>
                            <Col xs = {6}>
                                <div className="shadow p-3 mb-5 bg-white rounded">
                                        <h2>Product Owner details</h2>
                                                <p>Owner Name: {this.state.owner.firstName} {this.state.owner.lastName}</p>
                                                <p>Gender: {this.state.owner.gender}</p>
                                                <p>Email: {this.state.owner.email}</p>
                                                <p>Contact Number: {this.state.owner.contactNo}</p>
                                </div>
                                
                            </Col>
                        </Row>
                    </Container>
                    <center><h2>Bids on the Product</h2></center>
                    <table className="table table-striped table-bordered">
                        <thead>
                            <tr>
                                <th>Bid Owner Name</th>
                                <th>Contact Number</th>
                                <th>Email Address</th>
                                <th>Bid Amount</th>
                                <th>Selected</th>
                                <th>Action</th>
                            </tr>
                        </thead>
                        <tbody>
                            
                            {
                               this.state.bids.map(
                                    bid => 
                                    <tr>
                                        <td>{ bid.bidOwner.firstName} { bid.bidOwner.lastName }</td>
                                        <td>{ bid.bidOwner.contactNo }</td>
                                        <td>{ bid.bidOwner.email }</td>
                                        <td>{ bid.bidAmount }</td>
                                        <td style={{color:"blue"}}>{ bid.selected.toString() }</td>
                                        <td><Button variant='contained' color='primary' startIcon={<GavelTwoToneIcon/>}className="btn btn-info" onClick = {() => this.selectBid(bid.bidId, bid.bidAmount,bid.bidOwner.id)}>Select Bid</Button></td>
                                    </tr>
                                )
                            }

                        </tbody>
                    </table>
                </div>
            </div>
        );
    }
}

export default ProductDetailComponent;