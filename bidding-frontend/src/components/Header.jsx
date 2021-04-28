import React, { Component } from 'react';

class Header extends Component {
  render() {
    return (
      <div>
        <header>
          <nav className="navbar navbar-expand-md navbar-dark bg-dark">
            <div className="navbar-nav">
              <a href="#" className="navbar-brand">Bidding Platform</a>
            </div>
          </nav>
        </header>
      </div>
    );
  }
}

export default Header;