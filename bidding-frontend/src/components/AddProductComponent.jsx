import React, { Component } from 'react';
import DropdownButton from 'react-bootstrap/DropdownButton';
import Dropdown from 'react-bootstrap/Dropdown'
import UserService from '../services/UserService';
import ProductService from '../services/ProductService';
import Button from '@material-ui/core/Button'
import SaveIcon from '@material-ui/icons/Save';
import CancelIcon from '@material-ui/icons/Cancel';
import { createMuiTheme } from '@material-ui/core/styles';
import Chip from '@material-ui/core/Chip';
import Divider from '@material-ui/core/Divider';
import Typography from '@material-ui/core/Typography';
import image from '../Product.png' 
import { Height } from '@material-ui/icons';


class AddProductComponent extends Component {

    constructor(props){
        super(props);

        this.state = {
            user: [],
            productName: '',
            description: '',
            minimum_bid: 0,
            active: true,
            ownerId: ''
        }

        

        this.changeBidHandler = this.changeBidHandler.bind(this);
        this.changeDescriptionHandler = this.changeDescriptionHandler.bind(this);
        this.changeOwnerHandler = this.changeOwnerHandler.bind(this);
        this.changeProductNameHandler = this.changeProductNameHandler.bind(this);
    }
    

    componentDidMount(){
        UserService.getUsers().then((res) => {
            this.setState({user : res.data});
        });
    }

    changeProductNameHandler = (e) => {
        this.setState({productName: e.target.value});
    }

    changeDescriptionHandler = (e) => {
        this.setState({description: e.target.value});
    }

    changeBidHandler = (e) => {
        this.setState({minimum_bid: e.target.value});
    }

    changeOwnerHandler = (e) => {
        this.setState({ownerId: e});
    }

    saveUser = (event) => {
        event.preventDefault();
        let newProduct = {
            productName: this.state.productName,
            description: this.state.description,
            minimum_bid: this.state.minimum_bid,
            active: this.state.active,
            ownerId: this.state.ownerId
        }

        ProductService.addProduct(newProduct).then(res => {
            this.props.history.push('/products');
        });
    }

    cancel(){
        this.props.history.push('/products');
    }

    render() {
        const theme = createMuiTheme({
            palette: {
              primary: {
                // Purple and green play nicely together.
                main: '#388e3c',
              },
              secondary: {
                // This is green.A700 as hex.
                main: '#d32f2f',
              },
            },
          });
        return (
            <div className='bg-image' style={{
                backgroundImage:`url(${image})`,
                width: '100%',
                
                
            }}>
                <div className="container">
                    <div className="row">
                        <div className = "card col-md-6 offset-md-3 offset-md-3">
                            <h3 className="text-center"><b>Add Product</b></h3>
                            <Divider variant="middle" />
                            <br></br>
                            <div className="card-body">
                                <form>
                                    
                                    <p><b>Add your new product here !</b></p>
                                    <div className="form-group">
                                        <label>Product Name:</label>
                                        <input placeholder="Product Name" name="productName" className="form-control"
                                            value={this.state.productName} onChange={this.changeProductNameHandler} />
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
                                    <br/>
                                    <br/>
                                    <DropdownButton
                                    alignRight
                                    title="Owner ID "
                                    id="dropdown-menu-align-right"
                                    onSelect = {this.changeOwnerHandler}   
                                    style = {{marginBottom: "10px"}}                                 
                                    >
                                        {
                                            this.state.user.map(
                                                user =>
                                                <Dropdown.Item eventKey={user.id}>{user.id}</Dropdown.Item>
                                            )
                                        }
                                
                                    </DropdownButton><br/>

                                    <Button startIcon={<SaveIcon/>} variant='contained' color='primary' className="btn btn-success" onClick={this.saveUser}>Save</Button>
                                    <Button startIcon={<CancelIcon/>} variant='contained' color='secondary' className="btn btn-danger" onClick={this.cancel.bind(this)} style={{marginLeft: "10px"}}>Cancel</Button>
                                        <br/>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}

export default AddProductComponent;
