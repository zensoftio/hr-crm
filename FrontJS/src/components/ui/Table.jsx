import React from 'react';
import PropTypes from 'prop-types';
import {withStyles} from '@material-ui/core/styles';
import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableHead from '@material-ui/core/TableHead';
import TableRow from '@material-ui/core/TableRow';
import Paper from '@material-ui/core/Paper';


const CustomTableCell = withStyles(theme => ({
    head: {
        backgroundColor: theme.palette.common.black,
        color: theme.palette.common.white,
    },
    body: {
        fontSize: 13,
    },
}))(TableCell);

const styles = theme => ({
    root: {
        width: '100%',
        marginTop: theme.spacing.unit * 3,
        overflowX: 'auto',
    },
    table: {
        minWidth: 700,
    },
    row: {
        '&:nth-of-type(odd)': {
            backgroundColor: theme.palette.background.default,
        },
    },
});


function TableList(props) {
    const {classes, header, data} = props;

    return (
        <Paper className={classes.root}>
            <Table className={classes.table}>

                <TableHead>
                    <TableRow>
                        {header.map((n, i) => (
                            <CustomTableCell key={i}>{n}</CustomTableCell>
                        ))}
                    </TableRow>
                </TableHead>
                <TableBody>
                    {data.map((rows, i) => (
                        <TableRow className={classes.row} key={i}>
                            <CustomTableCell> {++i} </CustomTableCell>
                            {rows.map((element, k) => (
                                <CustomTableCell key={k}>{element}</CustomTableCell>
                            ))}
                        </TableRow>
                    ))}
                </TableBody>
            </Table>
        </Paper>
    );
}

TableList.propTypes = {
    classes: PropTypes.object.isRequired,
};

export default withStyles(styles)(TableList);