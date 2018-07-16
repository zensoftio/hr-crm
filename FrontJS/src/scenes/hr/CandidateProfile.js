import React, { Component } from 'react';
import Header from '../general/Header';
import {withStyles} from '@material-ui/core/styles';
import ProfileContainer from '../../components/containers/hr/ProfileContainer';
import Paper from '@material-ui/core/Paper'

const styles = {
    paperBox: {
      margin: '1em 1.5em',
      padding: '1.5em 1em'
    }
  };

  class Profile extends Component {
      render() {
          const {classes} = this.props;

          return (
            <div>
              <Header title="Профиль" />
              <Paper className={classes.paperBox}>
                <ProfileContainer profileId={this.props.match.params.id}/>
              </Paper>
            </div>
          );
      }
  }

  export default withStyles(styles)(Profile);
