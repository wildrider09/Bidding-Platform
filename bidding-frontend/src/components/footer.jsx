import React from 'react';
import { makeStyles } from '@material-ui/core/styles';
import BottomNavigation from '@material-ui/core/BottomNavigation';
import BottomNavigationAction from '@material-ui/core/BottomNavigationAction';
import ContactsIcon from '@material-ui/icons/Contacts';
import InfoIcon from '@material-ui/icons/Info';
import LocationOnIcon from '@material-ui/icons/LocationOn';
import Popover from '@material-ui/core/Popover';
import Typography from '@material-ui/core/Typography'

const useStyles = makeStyles({

  root: {width: "100%",
  position: "fixed",        
  padding: "10px 0",
  bottom: "0"
  },
});

export default function SimpleBottomNavigation() {
  const classes = useStyles();
  const [value, setValue] = React.useState(0);
  const [anchorEl, setAnchorEl] = React.useState(null);
  const open = Boolean(anchorEl);
  const id = open ? 'simple-popover' : undefined;
  const id1 = open ? 'simple-popover' : undefined;
  const id2 = open ? 'simple-popover' : undefined;
 
  const handleClick = (event) => {
    setAnchorEl(event.currentTarget);
  };

  const handleClose = () => {
    setAnchorEl(null);
  };
  return (
    <div class='sss'>
    <BottomNavigation
      value={value}
      onChange={(event, newValue) => {
        setValue(newValue);
      }}
      showLabels
      className={classes.root}
    >
      <BottomNavigationAction aria-describedby={id} label="Contact" icon={<ContactsIcon />} onClick={handleClick}
   />
      <BottomNavigationAction aria-describedby={id1} label="About us" icon={<InfoIcon />} onClick={handleClick}  />
      <BottomNavigationAction aria-describedby={id2} label="Location" icon={<LocationOnIcon />} onClick={handleClick} />
    </BottomNavigation>

    <Popover
        id={id}
        open={open}
        anchorEl={anchorEl}
        onClose={handleClose}
        anchorOrigin={{
          vertical: 'bottom',
          horizontal: 'center',
        }}
        transformOrigin={{
          vertical: 'top',
          horizontal: 'center',
        }}
      >
              <Typography className={classes.typography}> Nitesh Jain-7415609165<br/> Abhishek Garg- 8979925143 <br/>  K.V. Lasya- 9945688244 </Typography>
      </Popover>

    
      
      </div>
  );
}

