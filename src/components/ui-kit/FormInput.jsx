import React from 'react';

export default class FormInput extends React.Component {
    render() {
        return (
            <div>
                <div>{this.props.title}</div>
                <div>{this.props.children}</div>
            </div>
        )
    }
}