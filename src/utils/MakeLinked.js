import React from 'react';
import { Link } from 'react-router-dom';

const makeLinked = (element, link) => (
    <Link to={link}>{element} </Link>
);

export default makeLinked;