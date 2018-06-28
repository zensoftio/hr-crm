import React, { Component } from 'react';
import PropTypes from 'prop-types';
import { withStyles } from '@material-ui/core/styles';
import AppBar from '@material-ui/core/AppBar';
import Tabs from '@material-ui/core/Tabs';
import Tab from '@material-ui/core/Tab';
import Typography from '@material-ui/core/Typography';
import Grid from '@material-ui/core/Grid';
import Input from '../../components/ui/Input';
import TextArea from '../../components/ui/TextArea';

function TabContainer(props) {
  return (
    <Typography component="div" style={{ padding: 8 * 3 }}>
      {props.children}
    </Typography>
  );
}

TabContainer.propTypes = {
  children: PropTypes.node.isRequired,
};

const styles = theme => ({
  root: {
    flexGrow: 1,
    width: '100%',
    backgroundColor: theme.palette.background.paper,
  },
});

class CreateVacancy extends Component {
  state = {
    value: 0,
  };

  handleChange = (event, value) => {
    this.setState({ value });
  };

  render() {
    const { classes } = this.props;
    const { value } = this.state;

    return (
      <div className={classes.root}>
        <AppBar position="static" color="default">
          <Tabs
            value={value}
            onChange={this.handleChange}
            indicatorColor="primary"
            textColor="primary"
            scrollable
            scrollButtons="auto"
          >
            <Tab label="Шаблон" />
            <Tab label="Общий" />
          </Tabs>
        </AppBar>
        {value === 0 && <TabContainer>
            <Grid container spacing={16}>
                <Grid item lg={8}>
                   Шаблон: <TextArea />
                </Grid>
            </Grid>
            </TabContainer>}
        {value === 1 && <TabContainer>
            <Grid container spacing={16}>
                <Grid item lg={8}>
                   <Input label="Название темы" placeholder="введите название темы"/>
                </Grid>
                <Grid item lg={8}>
                   <Input label="Название вакансии" placeholder="введите название вакансии" />
                </Grid>
            </Grid>
            </TabContainer>}
      </div>
    );
  }
}

CreateVacancy.propTypes = {
  classes: PropTypes.object.isRequired,
};

export default withStyles(styles)(CreateVacancy);