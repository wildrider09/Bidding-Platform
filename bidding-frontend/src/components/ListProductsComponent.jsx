import React, { Component } from 'react';
import ProductService from '../services/ProductService';
import { Container, Row, Col } from 'react-grid-system';


class ListProductsComponent extends Component {
    constructor(props){
        super(props)
        this.state = {
            products: []

        }
        this.registerUser = this.registerUser.bind(this);
        this.addProduct = this.addProduct.bind(this);
    }

    componentDidMount(){
        ProductService.getProducts().then((res) => {
            this.setState({products : res.data});
        });
    }

    registerUser(){
        this.props.history.push('/register');
    }

    addProduct(){
        this.props.history.push("/add-product");
    }

    productDetail(id){
        this.props.history.push(`/product-detail/${id}`)
    }

    render() {
        return (
            <div>
                <h2 className="text-center"> Products List </h2>
                <div className="row">
                    <button className="btn btn-primary" id="registerUser" onClick={this.registerUser}>Register User</button>
                
                    <button className="btn btn-primary" id="addProduct" onClick={this.addProduct}>Add Product</button>
                </div>
                <div className = "products">
                    <Container style={{margin: "20px"}}>
                        <Row>
                            {
                                this.state.products.map(
                                    product =>
                                    
                                    <Col xs = {4}>
                                        <div className="shadow p-3 mb-5 bg-white rounded" key={product.productId} onClick = {() => this.productDetail(product.productId)} >
                                            <p>Product Name: {product.productName}</p>
                                            <p>Description: {product.description}</p>
                                            <p>Minimum Bid: {product.minimum_bid}</p>
                                            <p>Owner of the Product: {product.user.firstName}</p>
                                            <p>Live: {product.active.toString()}</p>
                                            <center><button className="btn btn-success">Buy</button></center> 
                                        </div>
                                    </Col> 
                                
                                )
                            }
                        </Row>
                    </Container>
                </div>
            </div>
        );
    }
}

export default ListProductsComponent;