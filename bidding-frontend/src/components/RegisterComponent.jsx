import React, { Component } from 'react';
import RegisterService from '../services/RegisterService';

class RegisterComponent extends Component {
    constructor(props){
        super(props);

        this.state = {
            firstName: '',
            lastName: '',
            email: '',
            contactNo: '',
            gender: ''
        }

        this.changeContactHandler = this.changeContactHandler.bind(this);
        this.changeEmailHandler = this.changeEmailHandler.bind(this);
        this.changeFirstNameHandler = this.changeFirstNameHandler.bind(this);
        this.changeGenderHandler = this.changeGenderHandler.bind(this);
        this.changeLastNameHandler = this.changeLastNameHandler.bind(this);
        this.saveUser = this.saveUser.bind(this);
    }

    changeFirstNameHandler = (e) => {
        this.setState({firstName: e.target.value});
    }

    changeContactHandler = (e) => {
        this.setState({contactNo: e.target.value});
    }

    changeEmailHandler = (e) => {
        this.setState({email: e.target.value});
    }

    changeLastNameHandler= (e) => {
        this.setState({lastName: e.target.value});
    }

    changeGenderHandler= (e) => {
        this.setState({gender: e.target.value});
    }

    saveUser = (event) => {
        event.preventDefault();
        let newUser = {
            firstName: this.state.firstName,
            lastName: this.state.lastName,
            email: this.state.email,
            contactNo: this.state.contactNo,
            gender: this.state.gender
        }

        RegisterService.registerUser(newUser).then(res => {
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
                            <h3 className="text-center">Register User</h3>
                            <div className="card-body">
                                <form>
                                    <div className="form-group">
                                        <label>First Name:</label>
                                        <input placeholder="First Name" name="firstName" className="form-control"
                                            value={this.state.firstName} onChange={this.changeFirstNameHandler} />
                                    </div>
                                    <div className="form-group">
                                        <label>Last Name:</label>
                                        <input placeholder="Last Name" name="lastName" className="form-control"
                                            value={this.state.lastName} onChange={this.changeLastNameHandler} />
                                    </div>
                                    <div className="form-group">
                                        <label>Email:</label>
                                        <input type="email" placeholder="Email Address" name="email" className="form-control"
                                            value={this.state.email} onChange={this.changeEmailHandler} />
                                    </div>
                                    <div className="form-group">
                                        <label>Contact Number:</label>
                                        <input placeholder="Contact Number" name="contact" className="form-control"
                                            value={this.state.contactNo} onChange={this.changeContactHandler} />
                                    </div>
                                    <div className="form-group">
                                        <input type="radio" name="gender" 
                                            value="Male" 
                                            onChange={this.changeGenderHandler}/> Male
                                            <br />
                                         <input type="radio" name="gender" 
                                            value="Female" 
                                            onChange={this.changeGenderHandler}/> Female
                                    </div>

                                    <button className="btn btn-success" onClick={this.saveUser}>Save</button>
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

export default RegisterComponent;