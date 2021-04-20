import React, { Component } from 'react';
import { Link, withRouter } from 'react-router-dom';
import ShowData from '../components/ShowData';

class HomePage extends Component {
  render() {
    return (
        <div className="home-page">
          <Link to="/hello">Click to see hello message</Link>
          <ShowData />
        </div>
    );
  }
}

export default withRouter(HomePage);
