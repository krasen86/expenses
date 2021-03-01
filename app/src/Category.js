import React, { Component } from 'react';
import AppNav from "./AppNav";

class Category extends Component {
    state = {  
        isLauding : true,
        Categories: []
    }
    async componentDidMount() {
        const response =  await fetch('/api/categories');
        const body = await response.json();
        this.setState({Categories: body, isLauding: false});
    }
    render() { 
        const {Categories, isLauding} = this.state;
        if(isLauding) {
            return (<div>Loading....</div>);
        } 
        return (  
            <div>
                <AppNav/>
                <h2>Categories</h2>
                {
                    Categories.map(category => 
                        <div id={category.categoryID}>
                            {category.categoryName}
                        </div>)
                }
            </div>
        );
    }
}
 
export default Category;