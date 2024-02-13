import React from 'react';
import {BrowserRouter as Router, NavLink, Route} from 'react-router-dom';
import Orders from './Orders';
import FollowUpOrders from './FollowUpOrders';

const App = () => {
    return (
        <Router>
            <div>
                <nav className="navbar navbar-expand-lg navbar-light bg-light">
                    <ul className="navbar-nav mr-auto">
                        <li className="nav-item">
                            <NavLink to="/" className="nav-link" activeClassName="active" exact>
                                Orders
                            </NavLink>
                        </li>
                        <li className="nav-item">
                            <NavLink to="/follow-up" className="nav-link" activeClassName="active">
                                Follow-Up Orders
                            </NavLink>
                        </li>
                    </ul>
                </nav>

                <div className="container mt-3">
                    <Route path="/" exact component={Orders}/>
                    <Route path="/follow-up" component={FollowUpOrders}/>
                </div>
            </div>
        </Router>
    );
};

export default App;
