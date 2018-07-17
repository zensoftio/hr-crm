import React, { Component } from 'react';
import Header from '../general/Header';
import {withStyles} from '@material-ui/core/styles';
import TemplateContainer from '../../components/containers/hr/TemplateContainer';
import Paper from '@material-ui/core/Paper'

const styles = {
    paperBox: {
      margin: '1em 1.5em',
      padding: '1.5em 1em'
    }
  };

  class Template extends Component {
      render() {
          const {classes} = this.props;

          return (
            <div>
              <Header title="Шаблон" />
              <Paper className={classes.paperBox}>
                <TemplateContainer templateId={this.props.match.params.id}/>
              </Paper>
            </div>
          );
      }
  }

  export default withStyles(styles)(Template);
