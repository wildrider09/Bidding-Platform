import { BorderLeft } from '@material-ui/icons';
import React, { Component } from "react";
import logo from './bidIcon.png';
import './style.css';
import { makeStyles } from '@material-ui/core/styles';
import Avatar from '@material-ui/core/Avatar';
// import { View, Image, StyleSheet, Text } from "react-native";
class Header extends Component {
  render() {
    return (
      <div>
        <header>
          <nav className="navbar navbar-expand-md navbar-dark bg-dark">
            <div className="navbar-nav">
              {/* <img src={logo} width='40' height='30' style={{margin:'5px'}}></img> */}
              <Avatar alt="Remy Sharp" src={logo} />
              {/* <div > */}

              <a id="title" href="#" className="navbar-brand" style={{marginLeft:'12px'}}>Bidding Platform</a>
              {/* </div> */}
            </div>
          </nav>
        </header>
      </div>
    );
    }
    }

export default Header;