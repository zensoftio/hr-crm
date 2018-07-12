import React, {Component} from 'react';
import { withStyles } from '@material-ui/core/styles';
import { Paper } from '@material-ui/core';
import PositionListContainer from '../../components/containers/hr/ListOfPositionsContainer';
import Header from '../../scenes/general/Header';

const styles = {
    paperBox: {
      margin: '1em',
      padding: '1em'
    }
  };

class PositionList extends Component {
    render() {
        const { classes } = this.props;

        return (
            <div>
                <Header title="Список заявок на подбор персонала"/>
                <Paper className={classes.paperBox}> 
                    <PositionListContainer/>
                </Paper>
            </div>
        )
    }
}

export default withStyles(styles) (PositionList);