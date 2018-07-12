import React from 'react';
import {Route} from 'react-router-dom';

const SpecifyTheRoute = (props) => {
    return props.route.map((item, index) => (
        <Route key={index} path={item.path} component={item.component}/>
    ))
}

export default SpecifyTheRoute;
