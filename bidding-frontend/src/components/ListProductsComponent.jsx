import React, { Component } from 'react';
import ProductService from '../services/ProductService';

class ListProductsComponent extends Component {
    constructor(props){
        super(props)
        this.state = {
            products: []

        }
    }

    componentDidMount(){
        ProductService.getProducts().then((res) => {
            this.setState({products : res.data});
        });
    }
    render() {
        return (
            <div>
                <h2 className="text-center"> Products List </h2>
                <div className = "row">
                    <table className = "table table-striped table-bordered">
                        <thead>
                            <tr>
                                <th>Product Name</th>
                                <th>Description</th>
                                <th>Minimum Bid Amount</th>
                                <th>Product Owner</th>
                                <th>Active</th>
                            </tr>
                        </thead>
                        <tbody>
                            {
                                this.state.products.map(
                                    product =>
                                    <tr key = {product.productId}>
                                        <td>{product.productName}</td>
                                        <td>{product.description}</td>
                                        <td>{product.minimum_bid}</td>
                                        <td>{product.user.firstName}</td>
                                        <td>{product.active.toString()}</td>
                                        <td><button className="btn btn-success" >Buy</button></td>
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

export default ListProductsComponent;