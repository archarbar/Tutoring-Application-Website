import React from 'react';
import { MuiThemeProvider, createMuiTheme } from '@material-ui/core/styles';

export class ThemeWrapperComponent extends React.Component {

  render() {
    const theme = createMuiTheme({
      palette: {
        primary: {
          main: '#3f51b5',
        },
        secondary: {
          main:'#1EC523',
          error: '#C51E1E'
        },
        error: {
          main: '#3f51b5',
        },
      },
    });

    return (
      <MuiThemeProvider theme={theme}>
        <div>
          {this.props.children}
        </div>
      </MuiThemeProvider>
    )
  }
}

export default ThemeWrapperComponent;
