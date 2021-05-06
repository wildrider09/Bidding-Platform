import React, { Component } from 'react';
import ProductService from '../services/ProductService';
import { Container, Row, Col } from 'react-grid-system';
import Button from '@material-ui/core/Button'
import PersonAdd from '@material-ui/icons/PersonAdd';
import AddBoxIcon from '@material-ui/icons/AddBox';
import Chip from '@material-ui/core/Chip';
import Divider from '@material-ui/core/Divider';
import Typography from '@material-ui/core/Typography';
import image from '../Home.png'
import { withWidth } from '@material-ui/core';
import GavelRoundedIcon from '@material-ui/icons/GavelRounded';
import {green} from '@material-ui/core/colors'
import { createMuiTheme } from '@material-ui/core/styles';
import {  withStyles, makeStyles, ThemeProvider } from '@material-ui/core/styles';

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

    bidProduct(id, activeStatus){
        if(activeStatus === false){
            alert("Can not bid inactive product");
            this.props.history.push('/products')
        }
        else{
            this.props.history.push(`/bid-product/${id}`)
        }        
    }

    render() {
        
        const theme = createMuiTheme({
            palette: {
              primary: green
    
            },
          });

        return (  
              
                 
            <div className='bg-image' style={{
                backgroundImage:`url(${image})`,
                width: 'relative'
                
            }}>
                
                <h2 className="text-center"> <b>Products List</b> </h2>
                <Divider variant="middle" />
                <br></br>
                <div className="row">
                    <Button endIcon={<PersonAdd/>} variant='contained' color='secondary' className="btn btn-primary" id="registerUser" onClick={this.registerUser}>Register User</Button>                
                    <Button endIcon={<AddBoxIcon/>} variant='contained' color='secondary' className="btn btn-primary" id="addProduct" onClick={this.addProduct}>Add Product</Button>
                </div>
                <div className = "products">
                    <Container style={{margin: "20px"}}>
                        <Row>
                            {
                                this.state.products.map(
                                    product => 
                                                                 
                                    <Col xs = {4}>
                                        <div className="shadow p-3 mb-5 bg-white rounded" key={product.productId}>
                                            <div onClick = {() => this.productDetail(product.productId)} >
                                                <p>Product Name: {product.productName}</p>
                                                <p>Description: {product.description}</p>
                                                <p>Minimum Bid: {product.minimum_bid}</p>
                                                <p>Owner of the Product: {product.user.firstName}</p>
                                                <p>Live: {product.active.toString()}</p>
                                            </div>                                            
                                            <center><Button variant='contained' style={{
        borderRadius: 7,
        backgroundColor: "#27c72f",
        padding: "5px 6px",
        fontSize: "15px"
    }} startIcon={<GavelRoundedIcon/>} className="btn btn-success" onClick = {() => this.bidProduct(product.productId, product.active)}>Bid</Button></center> 
                                        </div>
                                    </Col>                                 
                                )
                            }
                        </Row>
                    </Container>
                </div>
            </div>
            // </div>
        );
    }
}

export default ListProductsComponent;