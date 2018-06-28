import React, { Component } from 'react';
import PropTypes from 'prop-types';
import { withStyles } from '@material-ui/core/styles';
import AppBar from '@material-ui/core/AppBar';
import Tabs from '@material-ui/core/Tabs';
import Tab from '@material-ui/core/Tab';
import Typography from '@material-ui/core/Typography';
import Grid from '@material-ui/core/Grid';
import Input from '../../ui/Input';
import TextArea from '../../ui/TextArea';
import ButtonSubmit from '../../ui/ButtonSubmit';

function TabContainer(props) {
  return (
    <Typography component="div" style={{ padding: 8 * 3 }}>
      {props.children}
    </Typography>
  );
}

function tabChange(value){ 
    return(
        <TabContainer>
        <Grid container spacing={16}>
            <Grid item lg={8}>
                {value === 0 ? (
                    <div>
                        Шаблон: <TextArea />
                        <ButtonSubmit>Сохранить</ButtonSubmit>
                    </div>
                ):(
                    <div>
                        <Input label="Название темы" placeholder="введите название темы"/>
                        <Input label="Название вакансии" placeholder="введите название вакансии" />
                    </div> 
                )}
            </Grid>
        </Grid>
        </TabContainer>
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

class CreateVacancyContainer extends Component {
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
        {tabChange(value)}
      </div>
    );
  }
}

CreateVacancyContainer.propTypes = {
  classes: PropTypes.object.isRequired,
};

export default withStyles(styles)(CreateVacancyContainer);