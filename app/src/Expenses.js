import React, {Component} from 'react';
import AppNav from './AppNav';

class Expenses extends Component {
    state = {}

    render() {
        return (
            <div>
                <AppNav/>
                <h2>Expenses Page</h2>
            </div>
        );
    }
}

export default Expenses;