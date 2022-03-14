import React from 'react'
import { func, string } from 'prop-types';


const Toggle = ({theme,  toggleTheme, id }) => {
    return (
        <button className='toggle-button' onClick={toggleTheme} id={id} >
          Switch Theme
        </button>
    );
};

Toggle.propTypes = {
    theme: string.isRequired,
    toggleTheme: func.isRequired,
}

export default Toggle;