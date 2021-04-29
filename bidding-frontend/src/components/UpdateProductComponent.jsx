import React, { Component } from 'react';
import ProductService from '../services/ProductService';

class UpdateProductComponent extends Component {
    constructor(props){
        super(props);

        this.state = {
            id: this.props.match.params.id,
            productName: '',
            description: '',
            minimum_bid: 0,
            active: true,
            ownerId: ''
        }
        this.changeBidHandler = this.changeBidHandler.bind(this);
        this.changeDescriptionHandler = this.changeDescriptionHandler.bind(this);
        this.changeActiveHandler = this.changeActiveHandler.bind(this);
    }

    componentDidMount(){
        ProductService.getProductById(this.state.id).then((res) => {
            let product = res.data;
            this.setState({
                productName: product.productName,
                description: product.description,
                minimum_bid: product.minimum_bid,
                active: product.active,
                ownerId: product.user.id,                            
            })
        })
    }

    changeActiveHandler = (e) => {
        e.preventDefault();
        this.setState({active: false});
        alert("Product will be deactivated");
    }

    changeDescriptionHandler = (e) => {
        this.setState({description: e.target.value});
    }

    changeBidHandler = (e) => {
        this.setState({minimum_bid: e.target.value});
    }

    updateProduct = (e) => {
        e.preventDefault();
        let product = {
            productName: this.state.productName,
            description: this.state.description,
            minimum_bid: this.state.minimum_bid,
            active: this.state.active,
            ownerId: this.state.ownerId
        }

        ProductService.updateProduct(this.state.id, product).then(res => {
            this.props.history.push('/products');
        });
    }

    cancel(){
        this.props.history.push('/products');
    }

    render() {
        return (
                   <div>
                <div className="container">
                    <div className="row">
                        <div className = "card col-md-6 offset-md-3 offset-md-3">
                            <h3 className="text-center">Add Product</h3>
                            <div className="card-body">
                                <form>
                                    <div className="form-group">
                                        <label>Product Name:</label>
                                        <input placeholder="Product Name" name="productName" className="form-control"
                                            value={this.state.productName} readOnly/>
                                    </div>
                                    <div className="form-group">
                                        <label>Description: </label>
                                        <input type="textarea" placeholder="Product Description" name="description" className="form-control"
                                            value={this.state.description} onChange={this.changeDescriptionHandler} />
                                    </div>
                                    <div className="form-group">
                                        <label>Minimum Bid:</label>
                                        <input type="number" placeholder="Minimum Bid" name="bid" className="form-control"
                                            value={this.state.minimum_bid} onChange={this.changeBidHandler} />
                                    </div>
                                    <button className="btn btn-danger" onClick={this.changeActiveHandler} style={{margin: "10px"}}>Deactivate Product</button>
                                    <br/>
                                    <button className="btn btn-success" onClick={this.updateProduct}>Uodate Product</button>
                                    <button className="btn btn-danger" onClick={this.cancel.bind(this)} style={{marginLeft: "10px"}}>Cancel</button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}

export default UpdateProductComponent;